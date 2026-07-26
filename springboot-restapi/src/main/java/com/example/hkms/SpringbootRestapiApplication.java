package com.example.hkms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootRestapiApplication {
	
//	@Autowired
//	DataSourceConfig dataSourceConfig;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestapiApplication.class, args);
	}
	
}
