package com.itp.ITPAugustSpringboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration// extends WebSecurityConfigurerAdapter
{
	//Authentication
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		 auth.inMemoryAuthentication()
//			.withUser("jetha")
//			.password("jetha123")		// cleartext
//	  		.authorities("ADMIN")
//	  		.and()
//	  		.withUser("bagha")
//	  		.password("bagha123")		// cleartext
//	  		.authorities("USER")
//	  		.and()
//	  		.withUser("nattu")
//	  		.password("nattu123")		// cleartext
//	  		.authorities("USER");
	
	
	
//		auth.authenticationProvider(myAuthenticationProvider());
//	}
	
	@Bean
	public AuthenticationProvider myAuthenticationProvider() {
		DaoAuthenticationProvider daoProvider=new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(myUserDetailsService());
		daoProvider.setPasswordEncoder(myPasswordEncoder());
		return daoProvider;
	}

	@Bean
	public PasswordEncoder myPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService myUserDetailsService() {
		return new ITPAugustUserDetailsService();
	}

	//Authorisation
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//        .antMatchers("/flipkart/allProducts","/flipkart/add-product-form").hasAnyAuthority("USER","ADMIN")
//        .antMatchers("/flipkart/update-product-form/**","/flipkart/delete-product/**").hasAuthority("ADMIN")
//        .anyRequest().authenticated()
//        .and()
//        .formLogin().loginProcessingUrl("/login").successForwardUrl("/flipkart/allProducts").permitAll()
//        .and()
//        .logout().logoutSuccessUrl("/login").permitAll()
//        .and()
//        .exceptionHandling().accessDeniedPage("/flipkart/403")
//        .and()
//        .cors().and().csrf().disable();
//
//	}
	
//	@Bean
// 	public PasswordEncoder getPasswordEncoder()
// 	{
// 		return NoOpPasswordEncoder.getInstance();
// 	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(myAuthenticationProvider());
        
        http.authorizeRequests()
      .requestMatchers("/flipkart/allProducts","/flipkart/add-product-form").hasAnyAuthority("USER","ADMIN")
      .requestMatchers("/flipkart/update-product-form/**","/flipkart/delete-product/**").hasAuthority("ADMIN")
      .anyRequest().authenticated()
      .and()
      .formLogin().loginProcessingUrl("/login").successForwardUrl("/flipkart/allProducts").permitAll()
      .and()
      .logout().logoutSuccessUrl("/login").permitAll()
      .and()
      .exceptionHandling().accessDeniedPage("/flipkart/403")
      .and()
      .cors().and().csrf().disable();
        
        return http.build();
    }

}
