package com.gardecote;

import com.gardecote.business.service.qDocService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;

import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;



import java.util.*;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import org.springframework.web.filter.CharacterEncodingFilter;

import org.springframework.web.servlet.LocaleResolver;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
@Configuration
@ComponentScan
@EnableAutoConfiguration

@SpringBootApplication
@EnableBatchProcessing
 // this does the trick
public class GardecoteApplication extends SpringBootServletInitializer {
    private static final String PATH = "/errors";

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    public static void main(String[] args) {
     ApplicationContext ctx = SpringApplication.run(GardecoteApplication.class, args);


          }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<GardecoteApplication> applicationClass = GardecoteApplication.class;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            //route all errors towards /error .
            final ErrorPage errorPage=new ErrorPage(PATH);
            container.addErrorPages(errorPage);
        });
    }



    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.FRENCH);
        return slr;
    }
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:local/messages/messages");
        messageSource.setCacheSeconds(3600); //refresh cache once per hour
        return messageSource;
    }


 }