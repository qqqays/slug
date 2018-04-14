package com.qays.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-14-2018 22:44
 */

public class Crawler4Keywords extends MyCrawler {

    protected Integer counter = 0;

    public Crawler4Keywords(String[] urls, String option) {
        super(urls, option);
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();

            Document doc = Jsoup.parse(html);

            Element body = doc.body();

            Pattern p = Pattern.compile(option);

            Matcher m = p.matcher(body.text());

            while (m.find()) {
                counter++;
            }
        }

        }

    @Override
    public Object getMyLocalData() {
        return counter;
    }
}

