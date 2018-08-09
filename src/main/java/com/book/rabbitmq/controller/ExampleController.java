package com.book.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.rabbitmq.constants.MessageQueueConstants;
import com.book.rabbitmq.enums.message.MessageTypeEnum;
import com.book.rabbitmq.message.QueueMessage;
import com.book.rabbitmq.service.IMessageQueueService;

@RestController
@RequestMapping("/example/*")
public class ExampleController {
	
	@Autowired
	private IMessageQueueService messageQueueService;

	@RequestMapping("/send")
	public String send(){
		QueueMessage message = new QueueMessage(MessageQueueConstants.QUEUE_HELLO_NAME, "测试及时消息...");
		messageQueueService.send(message);
		return "ok";
	}
	
	@RequestMapping("/send/{seconds}")
	public String send(@PathVariable("seconds") int seconds){
		System.out.println("发送延迟消息...");
		QueueMessage message = new QueueMessage(MessageQueueConstants.QUEUE_HELLO_NAME, "测试延时消息...");
		message.setType(MessageTypeEnum.DELAYED.getIndex());
		message.setSeconds(seconds);
		messageQueueService.send(message);
		return "ok";
	}
}
