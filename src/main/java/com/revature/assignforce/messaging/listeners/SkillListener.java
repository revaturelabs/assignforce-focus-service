package com.revature.assignforce.messaging.listeners;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;

import com.rabbitmq.client.Channel;
import com.revature.assignforce.beans.Focus;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.service.FocusService;

public class SkillListener {
	
	private FocusService focusService;
	
	private final String focusQueue;
	
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "focus-queue", durable = "true"),
			exchange = @Exchange(value = "assignforce", ignoreDeclarationExceptions = "true"),
			key = "assignforce.skills.delete")
	)
	
	public void receiveMessage(final Integer skillId, Channel channel, 
				@Header(AmqpHeaders.DELIVERY_TAG) long tag) {
		try {
			channel.basicAck(tag, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Focus> focusList = focusService.getAll();
		
		for(Focus focus: focusList) {
			Set<SkillIdHolder> skillList = focus.getSkills();
			skillList.removeIf(skill -> skill.getSkillId() == skillId);
			focus.setSkills(skillList);
			focusService.update(focus);
		}
	}
	
	public SkillListener(FocusService focusService, 
			@Value("${spring.rabbitmq.focus-queue:focus-queue}") String focusQueue) {
		super();
		this.focusService = focusService;
		this.focusQueue = focusQueue;
	}

}
