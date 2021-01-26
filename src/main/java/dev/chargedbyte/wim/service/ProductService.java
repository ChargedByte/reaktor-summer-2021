package dev.chargedbyte.wim.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.chargedbyte.wim.exception.LegacyException;
import dev.chargedbyte.wim.exception.NotFoundException;
import dev.chargedbyte.wim.exception.OutOfRetriesException;
import dev.chargedbyte.wim.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service for {@link Product}, {@link LegacyProduct} and {@link LegacyAvailability} manipulation
 */
@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private static final Integer retryCount = 5;

    private final ConcurrentHashMap<Category, String> categoryETags;
    private final ConcurrentHashMap<String, String> manufacturerETags;

    public ProductService() {
        this.categoryETags = new ConcurrentHashMap<>();
        this.manufacturerETags = new ConcurrentHashMap<>();
    }

    /**
     * Get a list of products from the legacy api
     *
     * @param category Product category
     * @return On success {@link List} of {@link LegacyProduct} or {@code null} (keep cached), On failure {@link LegacyException}
     */
    @Async
    public CompletableFuture<List<LegacyProduct>> getLegacyProductsByCategory(Category category) {
        String eTag = categoryETags.getOrDefault(category, "");

        RestTemplate restTemplate = new RestTemplateBuilder().build();

        String url = "https://bad-api-assignment.reaktor.com/v2/products/" + category.name().toLowerCase();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setIfNoneMatch(eTag);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        for (int i = 0; i < retryCount; i++) {
            try {
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

                if (response.getStatusCode() == HttpStatus.NOT_MODIFIED) {
                    return CompletableFuture.completedFuture(null);
                }

                if (!response.hasBody()) {
                    // If present, clear ETag as the category no longer exists
                    categoryETags.remove(category);

                    log.warn("Products API [{}] responded with no body, assuming {}", category.name().toLowerCase(), HttpStatus.NOT_FOUND);
                    return CompletableFuture.failedFuture(new NotFoundException());
                }

                if (response.getStatusCode() == HttpStatus.OK) {
                    try {
                        ObjectMapper mapper = new ObjectMapper();

                        List<LegacyProduct> list = mapper.readValue(response.getBody(), mapper.getTypeFactory().constructCollectionType(List.class, LegacyProduct.class));

                        String header = response.getHeaders().getETag();
                        categoryETags.put(category, header != null ? header : "");

                        return CompletableFuture.completedFuture(list);
                    } catch (JsonProcessingException ex) {
                        log.warn("Products API [{}] failed response body parsing: {}", category.name().toLowerCase(), ex.getMessage());
                    }
                }
            } catch (RestClientException ex) {
                if (ex instanceof HttpStatusCodeException) {
                    log.warn("Products API [{}] responded with status: {}", category.name().toLowerCase(), ((HttpStatusCodeException) ex).getStatusCode());

                    if (((HttpStatusCodeException) ex).getStatusCode() == HttpStatus.NOT_FOUND) {
                        // If present, clear ETag as the category no longer exists
                        categoryETags.remove(category);

                        return CompletableFuture.failedFuture(new NotFoundException());
                    }

                    continue;
                }

                log.warn("Products API [{}] request errored: {}", category.name().toLowerCase(), ex.getMessage());
            }
        }

        log.warn("Products API [{}] failed after {} retries", category.name().toLowerCase(), retryCount);
        return CompletableFuture.failedFuture(new OutOfRetriesException());
    }

    /**
     * Get a list of availabilities from the legacy api
     *
     * @param manufacturer Product manufacturer
     * @return On success {@link List} of {@link LegacyAvailability} or {@code null} (keep cached), On failure {@link LegacyException}
     */
    @Async
    public CompletableFuture<List<LegacyAvailability>> getLegacyAvailabilitiesByManufacturer(String manufacturer) {
        String eTag = manufacturerETags.getOrDefault(manufacturer, "");

        RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(25)) // Account for the slow api
            .build();

        String url = "https://bad-api-assignment.reaktor.com/v2/availability/" + manufacturer;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setIfNoneMatch(eTag);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        for (int i = 0; i < retryCount; i++) {
            try {
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

                if (response.getHeaders().containsKey("X-Error-Modes-Active")) {
                    // Won't be null, checked above
                    if (!Objects.requireNonNull(response.getHeaders().getFirst("X-Error-Modes-Active")).isEmpty()) {
                        continue;
                    }
                }

                if (response.getStatusCode() == HttpStatus.NOT_MODIFIED) {
                    return CompletableFuture.completedFuture(null);
                }

                if (!response.hasBody()) {
                    // If present, clear ETag as the manufacturer no longer exists
                    manufacturerETags.remove(manufacturer);

                    log.warn("Availability API [{}] responded with no body, assuming {}", manufacturer, HttpStatus.NOT_FOUND);
                    return CompletableFuture.failedFuture(new NotFoundException());
                }

                if (response.getStatusCode() == HttpStatus.OK) {
                    try {
                        ObjectMapper mapper = new ObjectMapper();

                        AvailabilityResponse object = mapper.readValue(response.getBody(), AvailabilityResponse.class);

                        String header = response.getHeaders().getETag();
                        manufacturerETags.put(manufacturer, header != null ? header : "");

                        return CompletableFuture.completedFuture(object.getResponse());
                    } catch (JsonProcessingException ex) {
                        log.warn("Availability API [{}] failed response body parsing: {}", manufacturer, ex.getMessage());
                    }
                }
            } catch (RestClientException ex) {
                if (ex instanceof HttpStatusCodeException) {
                    log.warn("Availability API [{}] responded with status: {}", manufacturer, ((HttpStatusCodeException) ex).getStatusCode());

                    if (((HttpStatusCodeException) ex).getStatusCode() == HttpStatus.NOT_FOUND) {
                        // If present, clear ETag as the manufacturer no longer exists
                        manufacturerETags.remove(manufacturer);

                        return CompletableFuture.failedFuture(new NotFoundException());
                    }

                    continue;
                }

                log.warn("Availability API [{}] request errored: {}", manufacturer, ex.getMessage());
            }
        }

        log.warn("Availability API [{}] failed after {} retries", manufacturer, retryCount);
        return CompletableFuture.failedFuture(new OutOfRetriesException());
    }

    /**
     * Extracts an {@link Availability} from the payload of a {@link LegacyAvailability}
     *
     * @param legacy {@link LegacyAvailability}
     * @return An {@link Availability}
     */
    private Availability convertLegacyAvailability(@Nullable LegacyAvailability legacy) {
        if (legacy == null)
            return Availability.Unknown;

        // Very bruteforce but it works
        String value = legacy.getPayload()
            .split("<INSTOCKVALUE>")[1]
            .split("</INSTOCKVALUE>")[0];

        return Availability.find(value);
    }

    /**
     * Combine a {@link LegacyProduct} and a {@link LegacyAvailability} to a {@link Product}
     *
     * @param lp {@link LegacyProduct}
     * @param la {@link LegacyAvailability}
     * @return A {@link Product}
     */
    public CompletableFuture<Product> convertLegacyProduct(LegacyProduct lp, @Nullable LegacyAvailability la) {
        Availability availability = convertLegacyAvailability(la);
        Category category = Category.find(lp.getType());

        Product result = new Product(lp.getId(), category, lp.getName(), lp.getColor(), lp.getPrice(), lp.getManufacturer(), availability);
        return CompletableFuture.completedFuture(result);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class AvailabilityResponse {
        private Integer code;
        private List<LegacyAvailability> response;
    }
}
