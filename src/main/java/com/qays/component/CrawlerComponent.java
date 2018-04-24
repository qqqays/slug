package com.qays.component;

import com.qays.crawler.MyCrawler;
import com.qays.entity.ConstParam4Crawler;
import com.qays.factory.MyFactory;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-17-2018 9:28
 */
@Component
@Scope("prototype")
public class CrawlerComponent {
    @Autowired
    CrawlController controller;

    private static final int numberOfCrawlers = 7;

    public <T extends MyCrawler> List exec(Class<T> clazz, ConstParam4Crawler cpc) {
        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed(cpc.getSeed());

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */

        MyFactory<T> myFactory = new MyFactory<>(clazz, cpc.getUrls(), cpc.getOption());

//        controller.startNonBlocking(myfactory, numberOfCrawlers);
        controller.start(myFactory, numberOfCrawlers);

            return controller.getCrawlersLocalData();

    }

    /**
     * public method to get object of document
     *
     * @param url
     * @return
     */
    public Document getDoc(String url) {
        Connection con = Jsoup.connect(url);

        con.header(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36"
        );

        Document doc = null;
        try {
            doc = con.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

}
