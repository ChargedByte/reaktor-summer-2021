package dev.chargedbyte.wim.service;

import dev.chargedbyte.wim.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final LegacyApiService apiService;

    public ProductService(LegacyApiService apiService) {
        this.apiService = apiService;
    }

    private Availability convertLegacyAvailability(LegacyAvailability legacy) {
        // Very bruteforce but it works
        String value = legacy.getPayload()
            .split("<INSTOCKVALUE>")[1]
            .split("</INSTOCKVALUE>")[0];

        return Availability.find(value);
    }

    private Product convertLegacyProduct(LegacyProduct lp, LegacyAvailability la) {
        Availability availability;

        if (la != null)
            availability = convertLegacyAvailability(la);
        else
            availability = Availability.Unknown;

        Category category = Category.find(lp.getType());

        return new Product(lp.getId(), category, lp.getName(), lp.getColor(), lp.getPrice(), lp.getManufacturer(), availability);
    }
}
