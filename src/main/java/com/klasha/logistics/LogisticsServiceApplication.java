package com.klasha.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories(basePackages ={"com.klasha.logistics.*"})
@EntityScan("com.klasha.logistics.*")
public class LogisticsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(LogisticsServiceApplication.class, args);
	}

}
