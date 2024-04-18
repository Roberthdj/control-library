package dev.briefcase.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ControlLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlLibraryApplication.class, args);
	}

}
