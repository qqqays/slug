package com.qays;

import com.qays.controller.ProController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SlugApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SlugApplication.class, args);

//		String[] beanNames =  ctx.getBeanDefinitionNames();
//		System.out.println("beanNames个数："+beanNames.length);
//		for(String bn:beanNames){
//			System.out.println(bn);
//		}

        ProController proController = ctx.getBean(ProController.class);

//        Gains news content from web site and later stores some of info into db.
/*        proController.refine(new String[]{"http://www.pv-ledzm.com/article", "http://www.pv-ledzm.com/category"},
                ".newscon",
                "http://www.pv-ledzm.com");*/

//        Gains all links of a website
//        proController.getLinks(new String[]{"http://www.swpv.net"}, "", "http://www.swpv.net");

//        Gains links of one page
//        proController.linksOfPage("http://www.swpv.net");

//        Statistics words of one page
//        proController.wordsOfPage("http://www.swpv.net");

//        Counts keywords of one page.
//        proController.keywordOfPage("http://www.swpv.net", "太阳能");

//        Counts keywords of website
//        proController.keywordsOfWebsite(new String[]{"http://www.swpv.net"}, "太阳能", "http://www.swpv.net");

//        Counting alt of images of website
        proController.imgAltOfWebsite(new String[]{"http://www.pv-ledzm.com"}, "太阳能路灯", "http://www.pv-ledzm.com");
    }
}
