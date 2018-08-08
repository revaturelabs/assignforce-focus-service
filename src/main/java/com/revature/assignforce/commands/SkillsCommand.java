package com.revature.assignforce.commands;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.assignforce.beans.SkillIdHolder;

@Component
public class SkillsCommand {
	
	@Value("${environment.gateway-url:http://localhost:8765/}")
	String gatewayUrl;
	@Value("${enviroment.service.skills:skill-service/}")
	String skillUri;
	
	RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Sends a request to the skill-service to see if the skill is valid. If it is, true is returned
	 * If the skill is not valid, a fall back method is called to return false.
	 * 
	 * @param skillIdHolder
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "findSkillFallBack")
	public boolean findSkill(SkillIdHolder skillIdHolder) {
		restTemplate.getForEntity(gatewayUrl + skillUri + skillIdHolder.getSkillId(), String.class);
		return true;
	}
	
	public boolean findSkillFallBack(SkillIdHolder skillIdHolder) {
		return false;
	}
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

}
