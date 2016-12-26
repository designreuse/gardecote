package com.gardecote;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private SimpleUrlAuthenticationSuccessHandler cust;
@Autowired
private SimpleUrlAuthenticationSuccessHandler cust;
	@Autowired
public  void globalConfig(AuthenticationManagerBuilder auth,DataSource dataSource)  throws Exception{

		
auth.jdbcAuthentication()
 .dataSource(dataSource)
 .usersByUsernameQuery("select UserName as principal,Password as credentiel, 1 from Users where UserName=?")
 .authoritiesByUsernameQuery("select users_username as principal,roles_role as role from users_roles where users_username= ?")
	.rolePrefix("ROLE_");


	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
	
	  .csrf() .disable()
	  
		.authorizeRequests()
		  .antMatchers("/gcmcss/**","/gcmjs/**","/licence.html","/error.html", "/ css/**","/assets/**","/angular/**","/bootstrap-3.3.6-dist/**","/js/**").permitAll()
		  .anyRequest()
		 
		   .authenticated()
		   
	            .and()
	     .formLogin()
	    .successHandler(cust)
	     
	        .loginPage("/login")
	        
	        .permitAll()
	      
	//   .defaultSuccessUrl("/start")
	        
	   .failureUrl("/errors")
	  
	 //  .successHandler(cust)
	   .and()
	 .logout()
	  .invalidateHttpSession(true)
 	  .logoutUrl("/logout")
	  .permitAll();
	  
	  
	  
	 
	}
	
}

