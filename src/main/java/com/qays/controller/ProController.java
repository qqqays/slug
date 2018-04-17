package com.qays.controller;

import com.qays.entity.ConstParam4Crawler;
import com.qays.service.CrawlerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Building restful api for crawler")
@RestController
public class ProController {

    @Autowired
    CrawlerService crawler;

    /**
     * refine some information form website
     * @param cpc
     * @return
     */
    @ApiOperation(
            value = "refine the news info from specific website",
            notes = "also used to refine other info which are appointed by tag, class or id"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "urls", value = "array of url group", required = true, paramType = "query"),
            @ApiImplicitParam(name = "option", value = "specify param", required = true, paramType = "query"),
            @ApiImplicitParam(name = "seed", value = "which website", required = true, paramType = "query")
    })
    @PostMapping("/refine")
    public String refine(ConstParam4Crawler cpc) {

        return crawler.refine(cpc);
    }

    /**
     * Gets links of whole website
     * @param cpc
     * @return
     */
    @ApiOperation(value = "Gets links of whole website", notes = "ditto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "urls", value = "array of url group", required = true, paramType = "query"),
            @ApiImplicitParam(name = "option", value = "specify param", required = true, paramType = "query"),
            @ApiImplicitParam(name = "seed", value = "which website", required = true, paramType = "query")
    })
    @GetMapping("/getLinks")
    public Integer getLinks(ConstParam4Crawler cpc) {
        return crawler.getLinks(cpc);
    }

    /**
     * Gets links of one page of website
     * @param url
     * @return
     */
    @ApiOperation(value = "Gets links of whole website", notes = "ditto")
    @ApiImplicitParam(name="url", value = "which page would you like to count", required = true, paramType = "query")
    @GetMapping("/linksOfPage")
    public Integer linksOfPage(@RequestParam String url) {
        return crawler.linksOfPage(url);
    }

}
