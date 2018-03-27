package com.qays;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlugApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void httpclientTest(){
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

	@Test
	public void jsoupTest(){
		Connection con = Jsoup.connect("http://www.swpv.net");

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

		System.out.printf("finished");
	}

}
