package com.twg.SpringBoot_Mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling//在启动类上面加上@EnableScheduling即可开启定时
public class SpringBootMailApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("开始...");
	}

}
