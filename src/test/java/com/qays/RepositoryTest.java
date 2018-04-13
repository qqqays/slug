package com.qays;

import com.qays.crawler.Controller;
import com.qays.crawler.ProController;
import com.qays.entity.PageEntity;
import com.qays.repository.PageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;

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
    ProController proController;

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
        proController.refine(new String[]{"http://www.swpv.net/news"},".detail-body", "http://www.swpv.net/news");
    }

}
