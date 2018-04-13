package com.qays;

import com.qays.crawler.ProController;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

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

		proController.refine(new String[]{"http://www.pv-ledzm.com/article","http://www.pv-ledzm.com/category"}, ".newscon", "http://www.pv-ledzm.com");
	}
}
