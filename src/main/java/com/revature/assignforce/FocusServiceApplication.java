package com.revature.assignforce;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
@EnableRabbit
public class FocusServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(FocusServiceApplication.class).run(args);
	}
}
