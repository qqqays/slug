package com.qays;

import com.qays.util.GainLinks;
import com.qays.util.MyThreadPool;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlugApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void httpclientTest() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpGet httpGet = new HttpGet("http://www.baidu.com/s?ie=UTF-8&wd=太阳能电池");
        HttpGet httpGet = new HttpGet("http://www.swkj.net.cn/about-sw.html");
//		HttpGet httpGet = new HttpGet("http://www.swkj.net.cn");
//		HttpGet httpGet = new HttpGet("http://www.swpv.net");

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed:" + response.getStatusLine());
            } else {
                String resultStr = EntityUtils.toString(response.getEntity());
                System.out.println("result:" + resultStr);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //	statistics links
    @Test
    public void jsoupTest() {
//		Connection con = Jsoup.connect("http://www.swpv.net");
        Connection con = Jsoup.connect("http://www.sctaiyi.com/index.html");
//		Connection con = Jsoup.connect("http://www.jhzm88.com/");
//		Connection con = Jsoup.connect("http://www.jsgangyao.com");

        con.header(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36"
        );

        Document doc = null;
        try {
            doc = con.get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Elements links = doc.getElementsByTag("a");

        int i = 0;
        for (Element link : links) {
            System.out.println(link.attr("abs:href") + " 链接名：" + link.text() + " numbers: " + i++);
        }

//		Elements contents = doc.getElementsByTag("p");
//
//		Long number = 0L;
//		for (Element content : contents) {
//			number += content.toString().length();
//		}
//
//		System.out.println("number" + number);

    }

    //	statistics keywords
    @Test
    public void keywords() {

        Connection con = Jsoup.connect("http://www.jsgangyao.com");

        con.header(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36"
        );

        Document doc = null;
        try {
            doc = con.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element body = doc.body();

        Pattern p = Pattern.compile("太阳能路灯");

        Matcher m = p.matcher(body.text());

        int count = 0;
        while (m.find()) {
            count++;
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~The keywords of this page are : " + count);

    }

    @Test
    public void strLengthTest() {
        String a = "发电";
        String b = "abc";

        System.out.println(a.length());
        System.out.println(b.length());
    }

    @Test
    public void setTest() {
        Set<String> set = ConcurrentHashMap.newKeySet();

        set.add("a");
        System.out.println(set.add("b"));
        System.out.println(set.add("b"));
        set.add("c");
    }

    @Test
    public void methodTest() {

        GainLinks gainLinks = new GainLinks(ConcurrentHashMap.newKeySet(), new ConcurrentLinkedQueue<>());

        Runnable run = () -> gainLinks.operate("http://www.swpv.net");

        Thread thread = new Thread(run);

        thread.start();

        System.out.println("finish");

    }

    @Test
    public void threadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);


        System.out.println(fixedThreadPool.isTerminated());

        while (!fixedThreadPool.isTerminated()) {
//			fixedThreadPool.execute(new MyThreadPool());
        }

    }


}
