package com.wfj.util;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling

public class ActiveMqTest {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 300000)
	public void sendMq() {
		this.jmsMessagingTemplate.convertAndSend(this.queue, ">>>>>>>>>>>>>>>ActiveMqTest()");
	}

	@JmsListener(destination = "miao.queue")
	public void getMq(String text) {
		System.out.println(text);
	}

}
