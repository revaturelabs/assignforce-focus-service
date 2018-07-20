package com.revature.assignforce;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class FocusServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(FocusServiceApplication.class).run(args);
	}
}
