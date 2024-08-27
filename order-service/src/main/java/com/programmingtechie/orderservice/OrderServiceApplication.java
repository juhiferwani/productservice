package com.programmingtechie.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableJpaRepositories("com.programmingtechie.orderservice.*")
//@ComponentScan(basePackages = { "com.programmingtechie.orderservice.*" })
//@EntityScan(basePackageClasses = {"com.programmingtechie.orderservice.model.Order.class"})
//@EnableJpaRepositories("com.*")
//@ComponentScan(basePackages = { "com.*" })
//@EntityScan("com.*")

//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan
//@EnableTransactionManagement
//@EnableJpaRepositories
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
