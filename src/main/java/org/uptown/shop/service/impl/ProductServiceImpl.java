package org.uptown.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uptown.shop.config.Configuration;
import org.uptown.shop.service.ProductService;
import org.uptown.shop.util.info.Info;
import org.uptown.shop.util.info.Option;
import org.uptown.shop.util.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private Configuration config;

    public ProductServiceImpl(Configuration config) {
        this.config = config;
    }

    @Override
    public List<Product> list(List<Integer> selectedOptionIds) {
        List<Option> selectedOptions = new ArrayList<>();
        List<Info> selectedInfos = new ArrayList<>();
        selectedOptionIds.removeAll(Collections.singleton(null));
        for (Integer selectedOptionId : selectedOptionIds) {
            Optional<Option> selectedOptionOptional = config.getOptionList().stream()
                    .filter(o -> Integer.valueOf(o.getId()).equals(selectedOptionId))
                    .findFirst();
            if (selectedOptionOptional.isEmpty()) {
                throw new RuntimeException(String.format("Option (id=%d) does not exists", selectedOptionId));
            } else {
                Option selectedOption = selectedOptionOptional.get();
                if (selectedInfos.contains(selectedOption.getInfo())) {
                    throw new RuntimeException(String.format("Info (id=%d) already has selected option",
                            selectedOption.getInfo().getId()));
                }
                selectedInfos.add(selectedOption.getInfo());
                selectedOptions.add(selectedOption);
            }
        }
        return config.getProductList().stream()
                .filter(product -> product.getRule().test(selectedOptions))
                .collect(Collectors.toList());
    }
}
