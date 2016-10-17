package com.gardecote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gardecote.data.repository.jpa.qCategRessourceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.gardecote.data.repository.jpa.qConsignataireRepository;
import com.gardecote.data.repository.jpa.qEnginPecheRepository;
import com.gardecote.data.repository.jpa.qConcessionRepository;
import com.gardecote.data.repository.jpa.qLicenceBatLastRepository;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qEspece;
import com.gardecote.data.repository.jpa.qPageCarnetRepository;
import com.gardecote.data.repository.jpa.qEspeceRepository;
import com.gardecote.data.repository.jpa.qModelJPRepository;
import com.gardecote.data.repository.jpa.qCapturesRepository;
import com.gardecote.data.repository.jpa.qMareeRepository;
import com.gardecote.data.repository.jpa.qJourRepository;

@SpringBootApplication
public class GardecoteApplication {
    public static void main(String[] args) {
     ApplicationContext ctx= SpringApplication.run(GardecoteApplication.class, args);
        qCategRessourceRepository qcategressourcerepository= ctx.getBean(qCategRessourceRepository.class);
        // creer les categories

        // creer le consignataires
        qConsignataireRepository qConsignataireRepository= ctx.getBean(qConsignataireRepository.class);

        //creer les engins de peches
        qEnginPecheRepository qenginpecherepository= ctx.getBean(qEnginPecheRepository.class);

        // creer la concession
        qConcessionRepository qconsignationrepository= ctx.getBean(qConcessionRepository.class);

        // creer une licence bat last
        qLicenceBatLastRepository qlicencebatlastrepository= ctx.getBean(qLicenceBatLastRepository.class);


        // creer les carnet et les pages automatiques
        qCarnetRepository qcarnetrepository= ctx.getBean(qCarnetRepository.class);
        qPageCarnetRepository qPageCarnetRepository=ctx.getBean(qPageCarnetRepository.class);

        // distribuer les carnets et
        qCarnet qcarnet=new qCarnet();


        // creer les especes
        qEspeceRepository qEspeceRepository=ctx.getBean(qEspeceRepository.class);
        qEspece qespece=new qEspece();

        // crer les modeles et les associer avec  les especes
        qModelJPRepository  qmodeljprepository=ctx.getBean(qModelJPRepository.class);


        //creer les jours et leurs captures et les associer avec eux
        qCapturesRepository  qCapturesRepository=ctx.getBean(qCapturesRepository.class);

        // creer les marees et les associer avec les pages du maree encvours
qMareeRepository MareeRepository=ctx.getBean(qMareeRepository.class);


        // accocier les jours avex les pages et es jours avec les captures
        qJourRepository qjourrepository=ctx.getBean(qJourRepository.class);

        // visualuser une maree avec les contenues.

    }
}