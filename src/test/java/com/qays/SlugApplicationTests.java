package com.qays;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
		HttpGet httpGet = new HttpGet("http://www.swkj.net.cn");

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

}
