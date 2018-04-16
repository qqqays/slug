package com.qays.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-16-2018 9:27
 */
public class Crawler4ImgAlt extends MyCrawler{

    protected Integer altCounter = 0;

    public Crawler4ImgAlt(String[] urls, String option) {
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

            Elements elements = doc.select("img[alt]");

            Pattern p = Pattern.compile(option);

            for (Element img : elements) {

                Matcher m = p.matcher(img.attr("alt"));
                while (m.find()) {
                    altCounter++;
                }
            }
        }
    }

    @Override
    public Object getMyLocalData() {
        return altCounter;
    }
}
