package com.qays;

import com.qays.entity.ConstParam4Crawler;
import com.qays.service.CrawlerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SlugApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SlugApplication.class, args);

//		String[] beanNames =  ctx.getBeanDefinitionNames();
//		System.out.println("beanNames个数："+beanNames.length);
//		for(String bn:beanNames){
//			System.out.println(bn);
//		}

        CrawlerService crawler = ctx.getBean(CrawlerService.class);

//        Gains news content from web site and later stores some of info into db.
//        crawler.refine(
//                new ConstParam4Crawler(
//                        new String[]{"http://www.pv-ledzm.com/article", "http://www.pv-ledzm.com/category"},
//                        ".newscon",
//                        "http://www.pv-ledzm.com")
//        );

//        Gains all links of a website
//        crawler.getLinks(
//                new ConstParam4Crawler(
//                        new String[]{"http://www.swpv.net"},
//                        "",
//                        "http://www.swpv.net")
//        );

//        Gains links of one page
//        crawler.linksOfPage("http://www.swpv.net");

//        Statistics words of one page
//        crawler.wordsOfPage("http://www.swpv.net");

//        Counts keywords of one page.
//        crawler.keywordOfPage("http://www.swpv.net", "太阳能");

//        Counts keywords of website
//        crawler.keywordsOfWebsite(
//                new ConstParam4Crawler(
//                        new String[]{"http://www.swpv.net"},
//                        "太阳能",
//                        "http://www.swpv.net"
//                )
//        );

//        Counting alt of images of website
//        crawler.imgAltOfWebsite(
//                new ConstParam4Crawler(
//                        new String[]{"http://www.pv-ledzm.com"},
//                        "太阳能路灯",
//                        "http://www.pv-ledzm.com"
//                )
//        );

//        flexible crawler
//        crawler.flexible(
//                new ConstParam4Crawler(
//                        new String[]{"http://www.pv-ledzm.com"},
//                        "",
//                        "http://www.pv-ledzm.com"
//                )
//        );
    }
}
