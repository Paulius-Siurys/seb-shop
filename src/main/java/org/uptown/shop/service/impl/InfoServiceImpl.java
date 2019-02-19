package org.uptown.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uptown.shop.config.Configuration;
import org.uptown.shop.service.InfoService;
import org.uptown.shop.util.info.Info;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private Configuration config;

    public InfoServiceImpl(Configuration config) {
        this.config = config;
    }

    @Override
    public List<Info> list() {
        return config.getInfoList();
    }
}
