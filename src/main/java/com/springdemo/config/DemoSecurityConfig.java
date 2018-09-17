package com.springdemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// Add a reference to our security data source

	@Autowired
	private DataSource myDataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Add Custome Login Form
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/customers").hasRole("EMPLOYEE")
				.antMatchers(HttpMethod.GET, "/api/customers/*").hasRole("EMPLOYEE")
				.antMatchers(HttpMethod.POST, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/customers/*").hasRole("ADMIN")
				.and()
				.httpBasic()
				.and()
				.csrf()
				.disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Use JDBC athentications
		auth.jdbcAuthentication().dataSource(myDataSource);

	}
}
