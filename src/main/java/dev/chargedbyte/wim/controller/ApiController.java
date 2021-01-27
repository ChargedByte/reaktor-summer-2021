package dev.chargedbyte.wim.controller;

import dev.chargedbyte.wim.task.ProductUpdateTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final ProductUpdateTask productUpdateTask;

    public ApiController(ProductUpdateTask productUpdateTask) {
        this.productUpdateTask = productUpdateTask;
    }

    @GetMapping("/is-loading")
    public boolean isLoading() {
        return productUpdateTask.getIsFirstRun().get();
    }
}
