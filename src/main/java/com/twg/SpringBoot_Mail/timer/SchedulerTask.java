package com.twg.SpringBoot_Mail.timer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/*
	 	@Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
		@Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
		@Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按 fixedRate 的规则每6秒执行一次
	 */
	//@Scheduled(cron="*/6 * * * * ?")//6秒
	@Scheduled(fixedRate = 5*1000)
	public void timerTask(){
		System.out.println("现在时间:" + format.format(new Date()));
	}
}
