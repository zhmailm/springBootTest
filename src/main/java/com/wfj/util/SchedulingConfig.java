package com.wfj.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {
	/**
	 * 定时任务
	 * 
	 */
	 @Scheduled(cron = "0/1000 * * * * ?") // 每1000秒执行一次
//	 @Scheduled(cron = "0 30 12 * * ?") // 每天12点30执行
	public void scheduler() {
		System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
	}
}
