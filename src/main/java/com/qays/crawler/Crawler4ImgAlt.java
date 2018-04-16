package com.qays.crawler;

import com.qays.entity.ImageEntity;
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

    protected ImageEntity image;

    public Crawler4ImgAlt(String[] urls, String option) {
        super(urls, option);
        this.image = new ImageEntity();
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();

            Document doc = Jsoup.parse(html);

//            Elements elements = doc.select("img[alt]");
            Elements elements = doc.getElementsByTag("img");

            Pattern p = Pattern.compile(option);

            Integer imageNumber = 0;
            Integer altNumber = 0;
            Integer matchAltNumber = 0;

            for (Element img : elements) {

                imageNumber++;

                String alt = img.attr("alt");
                if (alt !=null && !alt.equals("")) {

                    altNumber++;

                    Matcher m = p.matcher(alt);

                    while (m.find()) {
                        matchAltNumber++;
                    }
                }
            }

            image.setImgNum(image.getImgNum() + imageNumber);
            image.setAltNum(image.getAltNum() + altNumber);
            image.setMatchAltNum(image.getMatchAltNum() + matchAltNumber);
        }
    }

    @Override
    public Object getMyLocalData() {
        return image;
    }
}
