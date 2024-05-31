package com.scholarly;

import com.scholarly.security.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyProperties.class)
public class ScholarlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScholarlyApplication.class, args);
	}

}
