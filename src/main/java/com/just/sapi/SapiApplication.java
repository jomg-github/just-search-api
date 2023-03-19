package com.just.sapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:/env/default.yml", encoding = "UTF-8")
@SpringBootApplication
public class SapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SapiApplication.class, args);
	}

}
