package org.uptown.shop.service;

import org.uptown.shop.util.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> list(List<Integer> selectedOptionIds);
}
