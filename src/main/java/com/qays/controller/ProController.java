package com.qays.controller;

import com.qays.entity.ConstParam4Crawler;
import com.qays.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-13-2018 8:47
 */
@RestController
public class ProController {

    @Autowired
    CrawlerService crawler;

    @PostMapping("/refine")
    public String refine(ConstParam4Crawler cpc){

        return crawler.refine(cpc);
    }

    @GetMapping("/getLinks")
    public Integer getLinks(ConstParam4Crawler cpc){
        return crawler.getLinks(cpc);
    }

    @GetMapping("/linksOfPage")
    public Integer linksOfPage(@RequestParam String url) {
        return crawler.linksOfPage(url);
    }

}
