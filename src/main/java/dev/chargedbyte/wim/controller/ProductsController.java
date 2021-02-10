package dev.chargedbyte.wim.controller;

import dev.chargedbyte.wim.model.Category;
import dev.chargedbyte.wim.model.Product;
import dev.chargedbyte.wim.model.ProductStorage;
import dev.chargedbyte.wim.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final Logger log = LoggerFactory.getLogger(ProductsController.class);

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/{category}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public ResponseEntity<List<Product>> list(@PathVariable String category, @RequestHeader(value = HttpHeaders.IF_MODIFIED_SINCE, required = false) Instant ifModifiedSince) {
        Category item = Category.find(category);
        if (item == Category.Unknown)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        HttpHeaders headers = new HttpHeaders();

        Optional<Instant> lastModified = productService.getLastModified(item);

        lastModified.ifPresent(headers::setLastModified);

        if (lastModified.isPresent() && ifModifiedSince != null) {
            if (lastModified.get() == ifModifiedSince || lastModified.get().isBefore(ifModifiedSince)) {
                return new ResponseEntity<>(headers, HttpStatus.NOT_MODIFIED);
            }
        }

        Optional<ProductStorage> storage = productService.findByCategory(item);

        if (storage.isPresent()) {
            List<Product> products = storage.get().getProducts();
            return new ResponseEntity<>(products, headers, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
