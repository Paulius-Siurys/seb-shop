package org.uptown.shop.util.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Product {
    private String name;
    private ProductRule rule;

    public Product(String name, ProductRule rule) {
        this.name = name;
        this.rule = rule;
    }

    private Product() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public ProductRule getRule() {
        return rule;
    }

    public void setRule(ProductRule rule) {
        this.rule = rule;
    }
}
