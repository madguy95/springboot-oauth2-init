package com.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity(debug = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
	auth.inMemoryAuthentication()
	  .withUser("john").password(passwordEncoder.encode("123")).roles("USER").and()
	  .withUser("tom").password(passwordEncoder.encode("111")).roles("ADMIN").and()
	  .withUser("user1").password(passwordEncoder.encode("pass")).roles("USER").and()
	  .withUser("admin").password(passwordEncoder.encode("nimda")).roles("ADMIN");
    }// @formatter:on

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
		http.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/oauth/authorize").permitAll()
			.antMatchers("/oauth/token/revokeById/**").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
			.antMatchers("/tokens/**").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").permitAll()
			.and().csrf().disable();
		// @formatter:on
    }

//	/**
//	 * Let Security ignore these urls, do not intercept
//	 *
//	 * @param
//	 * @throws Exception
//	 */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers
//                ("/swagger-ui.html/**", "/webjars/**",
//                        "/swagger-resources/**", "/v2/api-docs/**",
//                        "/swagger-resources/configuration/ui/**",
//                        "/swagger-resources/configuration/security/**",
//                        "/images/**");
//    }
}
