package com.gardecote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;


@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {
	private static final int SEVEN_DAYS_IN_SECONDS = 604800;
	@Override
	public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {

		configurer.enable();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("login");
		registry.addViewController("/creerDoc").setViewName("index");
	}
	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {

		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
		resolver.setPrefix("classpath:jasperreports/");
		resolver.setSuffix(".jrxml");
		resolver.setReportDataKey("datasource");
		resolver.setViewNames("*rpt_*");
		resolver.setViewClass(JasperReportsMultiFormatView.class);
		resolver.setOrder(0);
		return resolver;
	}

}
