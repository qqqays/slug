package com.qays.factory;

import com.qays.crawler.Crawler4Links;
import com.qays.crawler.MyCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

import java.lang.reflect.Constructor;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-13-2018 9:59
 */
public class MyFactory<T extends MyCrawler> implements CrawlController.WebCrawlerFactory<T> {

    private final Class<T> clazz;
    private String[] urls;
    private String option;

    public MyFactory(Class<T> clazz, String[] urls, String option){
        this.clazz = clazz;
        this.urls = urls;
        this.option = option;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T newInstance() throws Exception {

        Constructor<T> con = clazz.getConstructor(String[].class, String.class);

        return con.newInstance(urls,option);
    }
}
