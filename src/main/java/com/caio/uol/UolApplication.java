package com.caio.uol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Objects;

@EnableFeignClients
@SpringBootApplication
public class UolApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(UolApplication.class);
		String profileActive = System.getenv("PROFILE_ACTIVE");

		//1° forma, muito verbosa
//		Optional.ofNullable(profileActive).ifPresentOrElse(
//				springApplication::setAdditionalProfiles,
//				() -> springApplication.setAdditionalProfiles("local"));

		//2° forma, mais elegante e de fácil entendimento
		springApplication.setAdditionalProfiles(Objects.requireNonNullElse(profileActive, "local"));

		springApplication.run(args);
	}

}
