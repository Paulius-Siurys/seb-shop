package org.uptown.shop.util.product;

import org.uptown.shop.util.info.Option;

import java.util.List;

@FunctionalInterface
public interface ProductRule {
    boolean test(List<Option> selectedOptions);
}
