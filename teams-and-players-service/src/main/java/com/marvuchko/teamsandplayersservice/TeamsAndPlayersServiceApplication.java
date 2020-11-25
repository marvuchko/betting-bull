package com.marvuchko.teamsandplayersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TeamsAndPlayersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamsAndPlayersServiceApplication.class, args);
	}

}
