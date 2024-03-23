package ar.edu.iua.iw3.backend;

import java.util.Date;
import java.util.TimeZone;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer",
		paramName = "bearer"
)

@SpringBootApplication
@Slf4j
public class BackendApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private PasswordEncoder pEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Value("${spring.profiles.active:sinperfil}")
	private String profile;

	@Value("${spring.jackson.time-zone:-}")
	private String backendTimezone;

	@Override
	public void run(String... args) throws Exception {

		String tzId = backendTimezone.equals("-") ? TimeZone.getDefault().getID() : backendTimezone;
		TimeZone.setDefault(TimeZone.getTimeZone(tzId));

		log.info(
				"-------------------------------------------------------------------------------------------------------------------");
		log.info("- Initial TimeZone: {} ({})", TimeZone.getDefault().getDisplayName(), TimeZone.getDefault().getID());
		log.info("- Perfil activo {}", profile);
		log.info(
				"-------------------------------------------------------------------------------------------------------------------");
		log.info("-------------------------------------------------------------------------------------------------------------------");
		log.info("pass: sangunesa -> = {} ", pEncoder.encode("sangunesa"));
		log.info("pass: user123 -> = {} ", pEncoder.encode("user123"));
		log.info("-------------------------------------------------------------------------------------------------------------------");
	}


}