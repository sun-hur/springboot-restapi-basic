package com.example.hkms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.hkms.config.DataSourceConfig;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootRestapiApplication {
	
	@Autowired
	DataSourceConfig dataSourceConfig;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestapiApplication.class, args);
	}
	
}
