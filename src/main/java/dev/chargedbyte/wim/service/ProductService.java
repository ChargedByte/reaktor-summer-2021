package dev.chargedbyte.wim.service;

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
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

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
     * Get an array of products for the specified category
     *
     * @param category Product category
     * @return `LegacyProduct[?]` if success or `null` if not modified (304)
     * @throws IllegalArgumentException On not found (404) or empty body from the legacy API
     * @throws Exception On no result after max retries
     */
    @Async
    public CompletableFuture<LegacyProduct[]> getLegacyProductsByCategory(Category category) throws Exception {
        String eTag = categoryETags.getOrDefault(category, "");

        RestTemplate restTemplate = new RestTemplateBuilder().build();

        String url = "https://bad-api-assignment.reaktor.com/v2/products/" + category.name().toLowerCase();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setIfNoneMatch(eTag);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        for (int i = 0; i < retryCount; i++) {
            ResponseEntity<LegacyProduct[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, LegacyProduct[].class);

            if (response.getStatusCode() == HttpStatus.NOT_FOUND || response.getBody() == null) {
                // If present, clear ETag as the category no longer exists
                categoryETags.remove(category);

                throw new IllegalArgumentException();
            }

            if (response.getStatusCode() == HttpStatus.NOT_MODIFIED) {
                return CompletableFuture.completedFuture(null);
            }

            if (response.getStatusCode() == HttpStatus.OK) {
                // TODO: Save ETag

                return CompletableFuture.completedFuture(response.getBody());
            }

            HttpStatus status = response.getStatusCode();
            log.warn("Products API request failed: {} ({})", status.value(), status.getReasonPhrase());
        }

        log.error("Retry limit reached on products API calls with category: " + category.name());
        throw new Exception("out of retries");
    }


    /**
     * Get an array of availabilities for the specified manufacturer's products
     *
     * @param manufacturer Product manufacturer
     * @return `LegacyAvailability[?]` if success or `null` if not modified (304)
     * @throws IllegalArgumentException On not found (404) or empty body from the legacy API
     * @throws Exception On no result after max retries
     */
    @Async
    public CompletableFuture<LegacyAvailability[]> getLegacyAvailabilitiesByManufacturer(String manufacturer) throws Exception {
        String eTag = manufacturerETags.getOrDefault(manufacturer, "");

        RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(25))
            .build();

        String url = "https://bad-api-assignment.reaktor.com/v2/availability/" + manufacturer;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setIfNoneMatch(eTag);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        for (int i = 0; i < retryCount; i++) {
            ResponseEntity<AvailabilityResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, AvailabilityResponse.class);

            if (response.getHeaders().containsKey("X-Error-Modes-Active")) {
                //noinspection ConstantConditions
                if (!response.getHeaders().getFirst("X-Error-Modes-Active").isBlank()) { // Won't we null, checked above
                    continue;
                }
            }

            if (response.getStatusCode() == HttpStatus.NOT_FOUND || response.getBody() == null) {
                // If present, clear ETag as the category no longer exists
                manufacturerETags.remove(manufacturer);

                throw new IllegalArgumentException();
            }

            if (response.getStatusCode() == HttpStatus.NOT_MODIFIED) {
                return CompletableFuture.completedFuture(null);
            }

            if (response.getStatusCode() == HttpStatus.OK) {
                // TODO: Save ETag

                return CompletableFuture.completedFuture(response.getBody().getResponse());
            }

            HttpStatus status = response.getStatusCode();
            log.warn("Availability API request failed: {} ({})", status.value(), status.getReasonPhrase());
        }

        log.error("Retry limit reached on availability API calls with manufacturer: " + manufacturer);
        throw new Exception("out of retries");
    }

    /**
     * Extracts an Availability from the payload of a LegacyAvailability
     *
     * @param legacy LegacyAvailability
     * @return Availability
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
     * Combine a LegacyProduct and a LegacyAvailability to a Product
     *
     * @param lp LegacyProduct
     * @param la LegacyAvailability
     * @return Product
     */
    private Product convertLegacyProduct(LegacyProduct lp, @Nullable LegacyAvailability la) {
        Availability availability = convertLegacyAvailability(la);
        Category category = Category.find(lp.getType());

        return new Product(lp.getId(), category, lp.getName(), Arrays.asList(lp.getColor()), lp.getPrice(), lp.getManufacturer(), availability);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class AvailabilityResponse {
        private Integer code;
        private LegacyAvailability[] response;
    }
}
