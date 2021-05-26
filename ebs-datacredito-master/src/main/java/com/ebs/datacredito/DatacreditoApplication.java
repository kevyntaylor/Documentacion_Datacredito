package com.ebs.datacredito;

import com.ebs.datacredito.controller.filter.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class DatacreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatacreditoApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			final String[] AUTH_WHITELIST = {
					"/swagger-resources/**",
					"/swagger-ui.html",
					"/v2/api-docs",
					"/webjars/**",
					"/csrf"
			};

			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(AUTH_WHITELIST).permitAll()
					.antMatchers(HttpMethod.GET, "/datacredito/api/v1/ping").permitAll()
					.antMatchers(HttpMethod.POST, "/datacredito/api/v1/auth").permitAll()
					.anyRequest().authenticated();
		}
	}

}
