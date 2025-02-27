package com.caio.uol;

import com.caio.uol.service.strategy.CustomConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Objects;

@EnableFeignClients
@SpringBootApplication
public class UolApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(UolApplication.class);

		String profileActive = System.getenv("SPRING_PROFILE_ACTIVE");
		springApplication.setAdditionalProfiles(Objects.requireNonNullElse(profileActive, CustomConstants.local));

		springApplication.run(args);
	}

}
