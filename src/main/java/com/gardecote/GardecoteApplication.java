package com.gardecote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gardecote.data.repository.jpa.qCategRessourceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GardecoteApplication {
    public static void main(String[] args) {
     ApplicationContext ctx= SpringApplication.run(GardecoteApplication.class, args);
        qCategRessourceRepository qcrrep= ctx.getBean(qCategRessourceRepository.class);
    }
}