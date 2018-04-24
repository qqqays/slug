package com.qays.service;

import com.qays.component.CrawlerComponent;
import com.qays.crawler.*;
import com.qays.entity.ConstParam4Crawler;
import com.qays.entity.ImageEntity;
import com.qays.entity.PageEntity;
import com.qays.repository.PageRepository;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 04-17-2018 10:05
 */
@Service
@Scope("prototype")
public class CrawlerService {

    @Autowired
    PageRepository pageRepository;

    @Autowired
    CrawlerComponent component;

    /**
     * refine news info of website
     * @param cpc
     */
    public String refine(ConstParam4Crawler cpc) {

        List list = component.exec(Crawler4Refine.class, cpc);

        for (Object set : list) {
            for (Object page : (Set) set) {
                pageRepository.save((PageEntity) page);
            }
        }

        return "execution success";
    }

    /**
     * Gains links from the whole of website
     * @param cpc
     */
    public int getLinks(ConstParam4Crawler cpc) {

        List list = component.exec(Crawler4Links.class, cpc);

        int i = 0;
        for (Object set : list) {
            for (Object link : (Set) set) {
                System.out.println("~counter: " + i++ + " link: " + link );
            }
        }

        return i;
    }

    /**
     * Gains links of one page
     * @param url
     */
    public int linksOfPage(String url){


        Elements links = component.getDoc(url).getElementsByTag("a");

        int i = 0;
        for (Element link : links) {
            System.out.println(link.attr("abs:href") + " 链接名：" + link.text() + " numbers: " + i++);
        }

        return i;
    }

    /**
     * Gains words of one page
     * @param url
     */
    public int wordsOfPage(String url) {
        Element body = component.getDoc(url).body();

        System.out.println("~~The number of words of page: " + body.text().length());

        return body.text().length();
    }

    /**
     * Gains words of one page
     * @param url
     * @param keyword
     */
    public int keywordOfPage(String url, String keyword) {
        Element body = component.getDoc(url).body();

        Pattern p = Pattern.compile(keyword);

        Matcher m = p.matcher(body.text());

        int count = 0;
        while (m.find()) {
            count++;
        }

        System.out.println("~~The keywords \'"+ keyword +"\' of this page are : " + count);

        return count;
    }

    /**
     * Counting keywords for website
     * @param cpc
     */
    public Integer keywordsOfWebsite(ConstParam4Crawler cpc) {

        List list = component.exec(Crawler4Keywords.class,cpc);

        Integer count = 0;

        for (Object num : list) {
            count += (Integer) num;
        }

        System.out.println("~~The keywords \'"+ cpc.getOption() +"\' of website: " + count);

        return count;
    }

    /**
     * Counting the tag of img, alt of img, match alt of img tag
     * @param cpc
     */
    public String imgAltOfWebsite(ConstParam4Crawler cpc) {
        List list = component.exec(Crawler4ImgAlt.class, cpc);

        Integer imgN = 0;
        Integer altN = 0;
        Integer matchN = 0;

        for (Object image : list) {
            imgN += ((ImageEntity) image).getImgNum();
            altN += ((ImageEntity) image).getAltNum();
            matchN += ((ImageEntity) image).getMatchAltNum();
        }

        System.out.println("~~Number of tag of image in website: " + imgN);
        System.out.println("~~Number of alt of image tag in website: " + altN);
        System.out.println("~~match the alt \'" + cpc.getOption() + "\' of website: " + matchN);

        JSONObject jsonObject = new JSONObject().put("imgNum",imgN).put("altNum",altN).put("matchNum",matchN);

        return jsonObject.toString();
    }

    /**
     * flexible interface
     * @param cpc
     */
    public Integer flexible(ConstParam4Crawler cpc) {
        List list = component.exec(CrawlerFlexible.class, cpc);

        Integer count = 0;

        for (Object src : list) {
            count += (Integer) src;
        }

        System.out.println("~~The empty src of img tag: " + count);

        return count;
    }

}
