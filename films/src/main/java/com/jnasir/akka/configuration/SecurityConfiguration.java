package com.jnasir.akka.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CustomLoginSuccessHandler sucessHandler;



	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.authorizeRequests()
				.antMatchers(HttpMethod.TRACE, "/**").denyAll()
				.antMatchers("/admin/**").authenticated()
		        //.antMatchers("/resources/**").permitAll()
				//.antMatchers("/templates/**").permitAll()
				.anyRequest().permitAll()
				.and().httpBasic()
				.and().headers().frameOptions().disable()
				.and().csrf().disable()
				.headers()
				// the headers you want here. This solved all my CORS problems!
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST, GET"))
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");


	}

}
