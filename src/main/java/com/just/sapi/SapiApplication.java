package com.just.sapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@PropertySource(value = "classpath:/env/default.yml", encoding = "UTF-8")
@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class SapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SapiApplication.class, args);
	}

}
