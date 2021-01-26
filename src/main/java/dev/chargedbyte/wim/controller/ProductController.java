package dev.chargedbyte.wim.controller;

import dev.chargedbyte.wim.model.Category;
import dev.chargedbyte.wim.model.Product;
import dev.chargedbyte.wim.repository.ProductRepository;
import dev.chargedbyte.wim.task.ProductUpdateTask;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final TaskScheduler taskScheduler;
    private final ProductUpdateTask productUpdateTask;

    public ProductController(ProductRepository productRepository, TaskScheduler taskScheduler, ProductUpdateTask productUpdateTask) {
        this.productRepository = productRepository;
        this.taskScheduler = taskScheduler;
        this.productUpdateTask = productUpdateTask;
    }

    @GetMapping("/list/{category}")
    public List<Product> list(@PathVariable String category) {
        Category item = Category.find(category.toLowerCase());
        if (item == Category.Unknown)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return productRepository.findByCategory(item);
    }

    @GetMapping("/update")
    public void update(@RequestParam(required = false) boolean force) {
        if (force) {
            if (!productUpdateTask.getIsRunning().get()) {
                taskScheduler.schedule(productUpdateTask, new Date());
            }
        }
    }
}
