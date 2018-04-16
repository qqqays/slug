package com.qays.controller;

import com.qays.crawler.Crawler4Keywords;
import com.qays.crawler.Crawler4Links;
import com.qays.crawler.Crawler4Refine;
import com.qays.crawler.MyCrawler;
import com.qays.entity.PageEntity;
import com.qays.factory.MyFactory;
import com.qays.repository.PageRepository;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-13-2018 8:47
 */

@Component
public class ProController {

    @Autowired
    PageRepository pageRepository;

    @Autowired
    CrawlController controller;

    private static final int numberOfCrawlers = 7;

    public List exec(Class<? extends MyCrawler> clazz, String[] urls, String option, String seed) {
                /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed(seed);

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */

        MyFactory myfactory = new MyFactory(clazz, urls, option);

//        controller.startNonBlocking(myfactory, numberOfCrawlers);
        controller.start(myfactory, numberOfCrawlers);

        return controller.getCrawlersLocalData();
    }

//    public method to get object of document
    public Document getDoc(String url){
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

//    refine news info of websiete
    public void refine(String[] urls, String option, String seed) {

        List list = exec(Crawler4Refine.class, urls, option, seed);

        for (Object set : list) {
            for (Object page : (Set) set) {
                pageRepository.save((PageEntity) page);
            }
        }
    }

//    Gains links from the whole of website
    public void getLinks(String[] urls, String option, String seed) {

        List list = exec(Crawler4Links.class, urls, option, seed);

        int i = 0;
        for (Object set : list) {
            for (Object link : (Set) set) {
                System.out.println("~counter: " + i++ + " link: " + link );
            }
        }
    }

//    Gains links of one page
    public void linksOfPage(String url){


        Elements links = getDoc(url).getElementsByTag("a");

        int i = 0;
        for (Element link : links) {
            System.out.println(link.attr("abs:href") + " 链接名：" + link.text() + " numbers: " + i++);
        }

    }

//    Gains words of one page
    public void wordsOfPage(String url) {
        Element body = getDoc(url).body();

        System.out.println("~~The number of words of page: " + body.text().length());
    }

//    Gains words of one page
    public void keywordOfPage(String url, String keyword) {
        Element body = getDoc(url).body();

        Pattern p = Pattern.compile(keyword);

        Matcher m = p.matcher(body.text());

        int count = 0;
        while (m.find()) {
            count++;
        }

        System.out.println("~~The keywords \'"+ keyword +"\' of this page are : " + count);

    }

    public void keywordsOfWebsite(String[] urls, String keywords, String seed) {

        List list = exec(Crawler4Keywords.class, urls, keywords, seed);

        Integer count = 0;

        for (Object num : list) {
            count += (Integer) num;
        }

        System.out.println("~~The keywords \'"+ keywords +"\' of website: " + count);
    }

}
