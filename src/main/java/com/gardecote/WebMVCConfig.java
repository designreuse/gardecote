package com.gardecote;


import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration

public class WebMVCConfig extends WebMvcConfigurerAdapter {
	private static final int SEVEN_DAYS_IN_SECONDS = 604800;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("login");
		registry.addViewController("/creerDoc").setViewName("index");
	}


}
