package dev.chargedbyte.wim.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.chargedbyte.wim.model.ErrorState;
import dev.chargedbyte.wim.service.ProductService;
import dev.chargedbyte.wim.task.ProductUpdateTask;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final TaskScheduler taskScheduler;

    private final ProductUpdateTask productUpdateTask;

    private final ProductService productService;

    public ApiController(TaskScheduler taskScheduler, ProductUpdateTask productUpdateTask, ProductService productService) {
        this.taskScheduler = taskScheduler;
        this.productUpdateTask = productUpdateTask;
        this.productService = productService;
    }

    @GetMapping("/errors")
    public ErrorState errors() {
        return productService.getErrorState();
    }

    @GetMapping("/status")
    public ObjectNode status() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        if (productUpdateTask.isFirstRun().get()) {
            node.put("status", "loading");
            return node;
        }

        node.put("status", "ready");
        return node;
    }

    @GetMapping("/update")
    public ObjectNode update(@RequestParam(required = false) boolean force) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        if (force) {
            taskScheduler.schedule(productUpdateTask, Instant.now());
            node.put("status", "ok");
        }

        return node;
    }
}
