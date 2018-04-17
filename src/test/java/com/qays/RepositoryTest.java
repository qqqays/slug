package com.qays;

import com.qays.controller.ProController;
import com.qays.entity.ConstParam4Crawler;
import com.qays.entity.PageEntity;
import com.qays.repository.PageRepository;
import com.qays.service.CrawlerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Q-ays.
 * whosqays@gmail.com
 * 03-30-2018 9:44
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    PageRepository pageRepository;

//    @Autowired
//    Controller controller;

    @Autowired
    CrawlerService proController;

    @Test
    public void repositorySave(){
        PageEntity pageEntity = new PageEntity();

        pageEntity.setId("abcdef");

        pageRepository.save(pageEntity);
    }

    @Test
    public void insertTest(){
        try {
//            controller.exec();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void refine(){
        proController.refine(new ConstParam4Crawler(new String[]{"http://www.swpv.net/news"},".detail-body", "http://www.swpv.net/news"));
    }

}
