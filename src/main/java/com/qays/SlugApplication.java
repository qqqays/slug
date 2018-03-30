package com.qays;

import com.qays.config.MyConfiguration;
import com.qays.crawler.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SlugApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlugApplication.class, args);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);

		Controller controller = context.getBean(Controller.class);



		try {
			controller.exec();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
