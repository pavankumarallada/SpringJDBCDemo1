package com.pavan.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controller", "Dao", "model"})
public class JdbCdemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(JdbCdemo2Application.class, args);
	}

}
