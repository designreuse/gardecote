package com.gardecote;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Created by Dell on 08/02/2017.
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan
@ImportResource("classpath:spring-batch-context.xml")
@PropertySource("classpath:batch.properties")
public class Config {


}

