package com.qays.crawler;

import com.qays.entity.PageEntity;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-13-2018 15:36
 */
public class Crawler4Links extends MyCrawler{

    protected Set<String> linkSet;

    public Crawler4Links(String[] urls, String option) {
        super(urls, option);
        this.linkSet = new HashSet<>();
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        linkSet.add(url);
    }

    @Override
    public Object getMyLocalData() {
        return linkSet;
    }

}
