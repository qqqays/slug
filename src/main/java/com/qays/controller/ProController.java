package com.qays.controller;

import com.qays.entity.ConstParam4Crawler;
import com.qays.service.CrawlerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Statistics words of one page
     * @param url
     * @return
     */
    @ApiOperation(value = "Counts words of one page")
    @ApiImplicitParam(name="url", value = "The url of the page you want to statistics", required = true, paramType = "query")
    @GetMapping("/wordsOfPage")
    public Integer wordsOfPage(@RequestParam String url){
        return crawler.wordsOfPage(url);
    }

    /**
     * Statistics the number of the keywords appearance of one page
     * @param url
     * @param keywords
     * @return
     */
    @ApiOperation(value = "Searches the numbers of the keywords in one page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "The url of the page", required = true, paramType = "query"),
            @ApiImplicitParam(name = "keywords", value = "keywords", required = true, paramType = "query")
    })
    @GetMapping("/keywordsOfPage")
    public Integer keywordsOfPage(@RequestParam String url, @RequestParam String keywords){
        return crawler.keywordOfPage(url, keywords);
    }
}
