package com.qays.crawler;

import com.qays.entity.PageEntity;
import com.qays.repository.PageRepository;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.HttpStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 01-07-2018 17:18
 */

@Component
public class MyCrawler1 extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz|pdf|jpeg|txt|exe))$");

    private Set<PageEntity> pageEntitySet;

    public MyCrawler1() {
        this.pageEntitySet = new HashSet<>();
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
        return !FILTERS.matcher(href).matches()
//                && href.startsWith("http://www.jhzm88.com");
                && href.startsWith("http://www.socreat.cn/article/");

    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
/*        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();

            Document doc = Jsoup.parse(html);

            PageEntity pe = new PageEntity();

            pe.setId(url);
            pe.setTitle(doc.title());

//            Elements elements = doc.head().select("meta");

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

//            Element element = doc.getElementById("cntrBody");

            Elements elements1 = doc.getElementsByClass("new_content");

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

        }*/
    }

    @Override
    public Object getMyLocalData() {
        return pageEntitySet;
    }

    @Override
    protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
        if (statusCode != HttpStatus.SC_OK) {
            if (statusCode == HttpStatus.SC_NOT_FOUND) {
//                urlSet.add(webUrl.getURL());
                System.out.println("fuck");
            }
        }
    }
}
