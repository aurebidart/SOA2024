package ar.edu.iua.iw3.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ar.edu.iua.iw3.backend.auth.IUserBusiness;
import ar.edu.iua.iw3.backend.auth.custom.CustomAuthenticationManager;
import ar.edu.iua.iw3.backend.auth.filters.JWTAuthorizationFilter;
import ar.edu.iua.iw3.backend.util.Constants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").allowedOrigins("*");
			}
		};
	}

	@Autowired
	private IUserBusiness userBusiness;

	@Bean
	public AuthenticationManager authenticationManager() {
		return new CustomAuthenticationManager(bCryptPasswordEncoder(), userBusiness);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// CORS: https://developer.mozilla.org/es/docs/Web/HTTP/CORS
		// CSRF: https://developer.mozilla.org/es/docs/Glossary/CSRF
		http.cors().and().csrf().disable()
				.authorizeRequests().antMatchers(HttpMethod.POST, Constants.URL_LOGIN).permitAll()
				.antMatchers("/api-docs/**").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/swagger-ui/**").permitAll()
				.antMatchers("/chat").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}
}
