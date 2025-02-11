package com.caio.uol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class UolApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(UolApplication.class);
		String profileActive = System.getenv("PROFILE_ACTIVE");
		Optional.ofNullable(profileActive).ifPresentOrElse(
				springApplication::setAdditionalProfiles,
				() -> springApplication.setAdditionalProfiles("local"));
		springApplication.run(args);
	}

}
