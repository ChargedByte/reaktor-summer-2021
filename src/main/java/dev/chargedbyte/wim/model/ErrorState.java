package dev.chargedbyte.wim.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class ErrorState {
    private List<Category> failedCategories;
    private List<String> failedManufacturers;
    private AtomicBoolean productsTasksInterrupted;
    private AtomicBoolean availabilityTasksInterrupted;
    private AtomicBoolean productConversionTasksFailed;

    public ErrorState() {
        this.failedCategories = Collections.synchronizedList(new ArrayList<>());
        this.failedManufacturers = Collections.synchronizedList(new ArrayList<>());
        this.productsTasksInterrupted = new AtomicBoolean(false);
        this.availabilityTasksInterrupted = new AtomicBoolean(false);
        this.productConversionTasksFailed = new AtomicBoolean(false);
    }

    public void addFailedCategory(Category category) {
        this.failedCategories.add(category);
    }

    public void addFailedManufacturer(String manufacturer) {
        this.failedManufacturers.add(manufacturer);
    }
}
