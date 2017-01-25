package com.gardecote;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.gardecote.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.gardecote.data.repository.jpa.qPageDebarquementRepository;
import org.joda.time.DateTime;
import com.gardecote.data.repository.jpa.qCategRessourceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.ApplicationContext;
import com.gardecote.data.repository.jpa.qConsignataireRepository;

import com.gardecote.data.repository.jpa.qConcessionRepository;
import com.gardecote.data.repository.jpa.qDocRepository;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qPageCarnetRepository;
import com.gardecote.data.repository.jpa.qEspeceRepository;
import com.gardecote.data.repository.jpa.qModelJPRepository;
import com.gardecote.data.repository.jpa.qCapturesRepository;

import com.gardecote.data.repository.jpa.qJourMereRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.List;
import com.gardecote.data.repository.jpa.qDebarquementRepository;
import com.gardecote.data.repository.jpa.qLicenceRepository;
import com.gardecote.data.repository.jpa.qTypeConcessionRepository;
import java.text.SimpleDateFormat;

import com.gardecote.data.repository.jpa.qTypeLicRepository;
import com.gardecote.data.repository.jpa.qZoneRepository;
import com.gardecote.data.repository.jpa.qNationRepository;
import com.gardecote.data.repository.jpa.qRegistreNavireRepository;
import com.gardecote.data.repository.jpa.qEspeceTypeeRepository;
import org.apache.poi.ss.usermodel.DateUtil;
import com.gardecote.data.repository.jpa.qJourDebRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.regex.*;

import com.gardecote.data.repository.jpa.qSeqRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
 // this does the trick
public class GardecoteApplication {
    private static final String PATH = "/errors";

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    public static void main(String[] args) {
     ApplicationContext ctx = SpringApplication.run(GardecoteApplication.class, args);
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