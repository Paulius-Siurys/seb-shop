package org.uptown.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uptown.shop.service.ProductService;
import org.uptown.shop.util.product.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> list(@RequestParam(value = "options") List<Integer> selectedOptionIds) {
        return productService.list(selectedOptionIds);
    }
}
