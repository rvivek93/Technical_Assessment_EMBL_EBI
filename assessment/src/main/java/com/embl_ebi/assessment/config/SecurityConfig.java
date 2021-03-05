package com.embl_ebi.assessment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * Spring security denied access to h2-console. This configuration will resolve
 * 403 forbidden error when accessing h2-console.
 * 
 * 
 * @author Vivek Rajendran
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // other public endpoints of your API may be appended to this array
            "/user",
            "/h2-console/**"
    };

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(AUTH_WHITELIST).permitAll()
				.anyRequest()
				.authenticated();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
	}

}
