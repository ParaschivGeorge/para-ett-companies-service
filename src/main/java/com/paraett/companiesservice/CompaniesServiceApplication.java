package com.paraett.companiesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.paraett.companiesservice")
public class CompaniesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompaniesServiceApplication.class, args);
	}

}
