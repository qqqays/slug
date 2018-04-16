package com.qays.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-16-2018 11:34
 */
public class CrawlerFlexible extends MyCrawler {

    private Integer count = 0;

    public CrawlerFlexible(String[] urls, String option) {
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

            Elements elements = doc.getElementsByTag("img");

            for (Element img : elements) {
                String src = img.attr("src");
                if (src == null || src.equals("") || src.equals("-")) {
                    count++;
                }
            }
        }
    }

    @Override
    public Object getMyLocalData() {
        return count;
    }
}
