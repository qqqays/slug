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
 * 04-13-2018 9:34
 */
public class Crawler4Refine extends MyCrawler {

    protected Set<PageEntity> pageEntitySet;

    public Crawler4Refine(String url, String option) {
        super(url, option);
        this.pageEntitySet = new HashSet<>();
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();

            Document doc = Jsoup.parse(html);

            PageEntity pe = new PageEntity();

            pe.setId(url);
            pe.setTitle(doc.title());

            Elements elements = doc.getElementsByTag("meta");

            for (Element element : elements) {
                String content = element.attr("content");

                if ("keywords".equalsIgnoreCase(element.attr("name"))) {
                    pe.setKeywords(content);
                }

                if ("description".equalsIgnoreCase(element.attr("name"))) {
                    pe.setDescription(content);
                }
            }

            if (option.startsWith("#")) {

                Element element = doc.getElementById(option.substring(1));
                if (element != null) {

                    pe.setHtmlContent(element.html());
                    pe.setTextContent(element.text());

                    pageEntitySet.add(pe);

                }

            } else {
                Elements elements1;

                if (option.startsWith(".")) {
                     elements1= doc.getElementsByClass(option.substring(1));
                }else {
                    elements1 = doc.getElementsByTag(option);
                }

                for (Element element1 : elements1) {
                    StringBuilder sb = new StringBuilder();
                    StringBuilder sb1 = new StringBuilder();
                    if (element1 != null) {
                        sb.append(element1.html());
                        sb1.append(element1.text());
                    }
                    pe.setHtmlContent(sb.toString());
                    pe.setTextContent(sb1.toString());

                    pageEntitySet.add(pe);
                }
            }
        }
    }

    @Override
    public Object getMyLocalData() {
        return pageEntitySet;
    }
}
