package com.qays.crawler;

import com.qays.entity.PageEntity;
import com.qays.repository.PageRepository;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 01-08-2018 8:40
 */

//@Component
public class Controller implements ApplicationContextAware{

    private static ApplicationContext applicationContext = null;

    @Autowired
    PageRepository pageRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Controller.applicationContext == null) {
            Controller.applicationContext = applicationContext;
        }
        System.out.println("-------------------------------------------");
        System.out.println("SpringUtil.applicationContext == " + Controller.applicationContext);
        System.out.println("-------------------------------------------");
    }

    public void exec() throws Exception{
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */

//        controller.addSeed("http://www.jhzm88.com");
        controller.addSeed("http://www.socreat.cn/article/qyzx.html");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler1.class, numberOfCrawlers);

        List list = controller.getCrawlersLocalData();

        for(Object set : list){
            for(Object page : (Set) set){
                pageRepository.save((PageEntity) page);
            }
        }

    }
}
