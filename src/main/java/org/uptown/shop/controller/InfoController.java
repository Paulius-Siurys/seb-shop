package org.uptown.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uptown.shop.service.InfoService;
import org.uptown.shop.util.info.Info;

import java.util.List;

@RestController
@RequestMapping("/infos")
public class InfoController {

    @Autowired
    private InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public List<Info> list() {
        return infoService.list();
    }
}
