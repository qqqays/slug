package com.qays.crawler;

import com.qays.entity.PageEntity;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.HttpStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-13-2018 10:04
 */
public class MyCrawler extends WebCrawler{
    protected final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz|pdf|jpeg|txt|exe))$");

    protected String[] urls;

    protected String option;

    public MyCrawler(String[] urls) {
        this.urls = urls;
    }

    public MyCrawler(String[] urls, String option) {
        this.urls = urls;
        this.option = option;
    }

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        boolean b = false;

        for (String url1 : urls) {
            b = b || href.startsWith(url1);
        }

        return !FILTERS.matcher(href).matches()
                && b;

    }


//    @Override
//    public Object getMyLocalData() {
//        return pageEntitySet;
//    }

    @Override
    protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
        if (statusCode != HttpStatus.SC_OK) {
            if (statusCode == HttpStatus.SC_NOT_FOUND) {
//                urlSet.add(webUrl.getURL());
            }
        }
    }
}
