package com.qays.config;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-13-2018 9:00
 */
@Configuration
public class CrawlerConfiguration {

    @Bean
    public CrawlConfig getCrawlConfig(){
        String crawlStorageFolder = "/data/crawl/root";

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        return config;
    }

    @Bean
    @Autowired
    public PageFetcher getPageFetcher(CrawlConfig crawlConfig){

        return new PageFetcher(crawlConfig);
    }

    @Bean
    @Scope("prototype")
    public CrawlController getCrawlController(){
        String crawlStorageFolder = "/data/crawl/root";

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setResumableCrawling(true);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return controller;
    }
}
