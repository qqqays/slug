package com.qays.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GainLinks {

    private Set<String> set;

    private Queue<String> queue;

    public GainLinks(Set<String> set, Queue<String> queue) {
        this.set = set;
        this.queue = queue;
    }

    public void operate(String url) {

        try {
            Connection con = Jsoup.connect(url);

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

            for (Element link : links) {
                if (set.add(link.attr("abs:href"))) {
                    queue.add(link.attr("abs:href"));
                }
            }

            System.out.println("finished one page ;)" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        Set<String> set = ConcurrentHashMap.newKeySet();
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        GainLinks gainLinks = new GainLinks(set,queue);
//        queue.add("http://www.swpv.net");
//        queue.add("http://www.sctaiyi.com");
        queue.add("http://www.jhzm88.com/");

//        Runnable run = () -> gainLinks.operate("http://www.swpv.net");
        Runnable run = () -> {
            String temp;

            try {
                while ((temp = queue.poll()) != null) {
                    gainLinks.operate(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            int i = 1;
            for (String e:set){
                System.out.println(i++ + ". " + e);
            }
        };


        Thread thread = new Thread(run);

        thread.start();



    }
}