package dev.chargedbyte.wim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.chargedbyte.wim.meta.exception.*;
import dev.chargedbyte.wim.model.Category;
import dev.chargedbyte.wim.model.LegacyAvailability;
import dev.chargedbyte.wim.model.LegacyProduct;
import kong.unirest.HttpResponse;
import kong.unirest.HttpStatus;
import kong.unirest.Unirest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LegacyApiService {
    private static final Logger log = LoggerFactory.getLogger(LegacyApiService.class);

    private static final Integer retryCount = 5;

    private final HashMap<Category, String> categoryETags;
    private final HashMap<String, String> manufacturerETags;

    public LegacyApiService() {
        this.categoryETags = new HashMap<>();
        this.manufacturerETags = new HashMap<>();

        Unirest.config()
            .defaultBaseUrl("https://bad-api-assignment.reaktor.com/v2");
    }

    @Async
    public CompletableFuture<List<LegacyProduct>> getLegacyProductsByCategory(String category) throws LegacyApiException {
        String eTag = categoryETags.getOrDefault(Category.find(category), "");

        for (int i = 0; i < retryCount; i++) {
            HttpResponse<String> response = Unirest.get("/products/{category}")
                .accept("application/json")
                .header("If-None-Match", eTag)
                .routeParam("category", category)
                .asString();


            if (response.getStatus() == HttpStatus.NOT_FOUND || response.getBody().isEmpty()) {
                categoryETags.remove(Category.find(category));

                throw new CategoryNotFoundException(category);
            }

            if (response.getStatus() == HttpStatus.NOT_MODIFIED) {
                return CompletableFuture.completedFuture(null);
            }

            if (response.getStatus() == HttpStatus.OK) {
                ObjectMapper mapper = new ObjectMapper();

                try {
                    List<LegacyProduct> object = mapper.readValue(
                        response.getBody(),
                        mapper.getTypeFactory().constructCollectionType(List.class, LegacyProduct.class)
                    );

                    // TODO: Enable
                    String header = response.getHeaders().getFirst("Etag");
                    //categoryETags.put(Category.find(category), header);

                    return CompletableFuture.completedFuture(object);
                } catch (JsonProcessingException ex) {
                    throw new ParsingException("could not parse response", ex);
                }
            }

            log.warn(
                String.format("Products API request failed %d:%s", response.getStatus(), response.getStatusText())
            );
        }

        log.error("Exhausted all retries on Products API");
        throw new UnknownErrorException("products api request failed after " + retryCount + " retries");
    }

    @Async
    public CompletableFuture<List<LegacyAvailability>> getLegacyAvailabilitiesByManufacturer(String manufacturer) throws LegacyApiException {
        String eTag = manufacturerETags.getOrDefault(manufacturer, "");

        for (int i = 0; i < retryCount; i++) {
            HttpResponse<String> response = Unirest.get("/availability/{manufacturer}")
                .accept("application/json")
                .connectTimeout(1000 * 20)
                .header("If-None-Match", eTag)
                .routeParam("manufacturer", manufacturer)
                .asString();

            if (!response.getHeaders().getFirst("X-Error-Modes-Active").isBlank()) {
                continue;
            }

            if (response.getStatus() == HttpStatus.NOT_FOUND || response.getBody().isBlank()) {
                manufacturerETags.remove(manufacturer);

                throw new ManufacturerNotFoundException(manufacturer);
            }

            if (response.getStatus() == HttpStatus.NOT_MODIFIED) {
                return CompletableFuture.completedFuture(null);
            }

            if (response.getStatus() == HttpStatus.OK) {
                ObjectMapper mapper = new ObjectMapper();

                try {
                    AvailabilityResponse object = mapper.readValue(response.getBody(), AvailabilityResponse.class);

                    // TODO: Enable
                    String header = response.getHeaders().getFirst("Etag");
                    //manufacturerETags.put(manufacturer, header);

                    return CompletableFuture.completedFuture(object.getResponse());
                } catch (JsonProcessingException ex) {
                    throw new ParsingException("could not parse response", ex);
                }
            }

            log.warn(String.format("Availability API request failed %d:%s", response.getStatus(), response.getStatusText()));
        }

        log.error("Exhausted all retries on Availability API");
        throw new UnknownErrorException("availability api request failed after " + retryCount + " retries");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class AvailabilityResponse {
        private Integer code;
        private List<LegacyAvailability> response;
    }
}
