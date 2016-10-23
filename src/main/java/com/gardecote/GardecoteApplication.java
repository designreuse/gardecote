package com.gardecote;
import com.gardecote.entities.*;
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
      // creer les categories de ressource 5 PA
     qCategRessource qcategressource1=new qCategRessource(enumSegPeche.PA, enumCategRessource.PA_Cephalopode,enumSupport.Collectif);
     qCategRessource qcategressource2=new qCategRessource(enumSegPeche.PA, enumCategRessource.PA_Crustaces,enumSupport.Collectif);
     qCategRessource qcategressource3=new qCategRessource(enumSegPeche.PA, enumCategRessource.PA_Poissons_demersaux,enumSupport.Collectif);
     qCategRessource qcategressource4=new qCategRessource(enumSegPeche.PA, enumCategRessource.PA_Poissons_Pelagique,enumSupport.Collectif);
     qCategRessource qcategressource5=new qCategRessource(enumSegPeche.PA, enumCategRessource.PA_Algues_et_autres_Mollusques,enumSupport.Collectif);

        // creer les categories de ressource 5 PC
        qCategRessource qcategressource6=new qCategRessource(enumSegPeche.PC, enumCategRessource.PC_Poissons_Pelagiques_Seg1,enumSupport.Individuel);
        qCategRessource qcategressource7=new qCategRessource(enumSegPeche.PC, enumCategRessource.PC_Poissons_Pelagiques_Seg2,enumSupport.Individuel);
        qCategRessource qcategressource8=new qCategRessource(enumSegPeche.PC, enumCategRessource.PC_Poissons_Pelagiques_Seg3,enumSupport.Individuel);
        qCategRessource qcategressource9=new qCategRessource(enumSegPeche.PC, enumCategRessource.PC_Autres_Mollusques,enumSupport.Collective_et_nombre_unites_autorises);
        qCategRessource qcategressource10=new qCategRessource(enumSegPeche.PC, enumCategRessource.PC_Autres_Mollusques,enumSupport.Collective_et_nombre_unites_autorises);

        //  creer les categories de ressource 9 PH
        qCategRessource qcategressource11=new qCategRessource(enumSegPeche.PH, enumCategRessource.PH_Pelagique,enumSupport.Individuel);
        qCategRessource qcategressource12=new qCategRessource(enumSegPeche.PH, enumCategRessource.PH_Thons,enumSupport.Individuel);
        qCategRessource qcategressource13=new qCategRessource(enumSegPeche.PH, enumCategRessource.PH_Cephalopode,enumSupport.Individuel);
        qCategRessource qcategressource14=new qCategRessource(enumSegPeche.PH, enumCategRessource.PH_Crevettes,enumSupport.Individuel);
        qCategRessource qcategressource15=new qCategRessource(enumSegPeche.PH, enumCategRessource.PH_Merlus,enumSupport.Individuel);
        qCategRessource qcategressource16=new qCategRessource(enumSegPeche.PH, enumCategRessource.PH_Poissons_demersaux_autre_que_merlu,enumSupport.Individuel);
        qCategRessource qcategressource17=new qCategRessource(enumSegPeche.PH, enumCategRessource.PH_Langouste_rose,enumSupport.Individuel);
        qCategRessource qcategressource18=new qCategRessource(enumSegPeche.PH, enumCategRessource.PH_Crabe_profond,enumSupport.Individuel);
        qCategRessource qcategressource19=new qCategRessource(enumSegPeche.PH, enumCategRessource.PH_Autres_Mollusques,enumSupport.Individuel);


        //  creer le consignataires
        qConsignataireRepository qConsignataireRepository= ctx.getBean(qConsignataireRepository.class);
        qConsignataire qconsignataire1=new qConsignataire("Jelal eddine");
        qConsignataire qconsignataire2=new qConsignataire("Jelal eddine");
        qConsignataire qconsignataire3=new qConsignataire("Jelal eddine");

        //creer les engins de peches
        qEnginPecheRepository qenginpecherepository= ctx.getBean(qEnginPecheRepository.class);

        qEnginPeche qenginpeche1=new qEnginPeche();
        qEnginPeche qenginpeche2=new qEnginPeche();
        qEnginPeche qenginpeche3=new qEnginPeche();

        // creer la concession
        qConcessionRepository qconsignationrepository= ctx.getBean(qConcessionRepository.class);
        qConcession qConcession=new qConcession();

        // creer une licence bat last
        qLicenceBatLastRepository qlicencebatlastrepository= ctx.getBean(qLicenceBatLastRepository.class);
        qLicenceBatLast qlicencebatlast=new qLicenceBatLast();

        // creer les carnet et les pages automatiques
        qCarnetRepository qcarnetrepository= ctx.getBean(qCarnetRepository.class);
        qPageCarnetRepository qPageCarnetRepository=ctx.getBean(qPageCarnetRepository.class);
        qCarnet qcarnet=new qCarnet();
        qPageCarnet qpagecarnet=new qPageCarnet();

        // distribuer les carnets et
       // qCarnet qcarnet=new qCarnet();


        // creer les especes
        qEspeceRepository qEspeceRepository=ctx.getBean(qEspeceRepository.class);
        qEspece qespece=new qEspece();

        // crer les modeles et les associer avec  les especes
        qModelJPRepository  qmodeljprepository=ctx.getBean(qModelJPRepository.class);
        qModelJP qmodeljp=new qModelJP();




        //creer les jours et leurs captures et les associer avec eux
        qCapturesRepository  qCapturesRepository=ctx.getBean(qCapturesRepository.class);
        qCapture qcapture=new qCapture();

        // creer les marees et les associer avec les pages du maree encvours
         qMareeRepository MareeRepository=ctx.getBean(qMareeRepository.class);
qMarree qmarree=new qMarree();

        // accocier les jours avex les pages et les jours avec les captures
        qJourRepository qjourrepository=ctx.getBean(qJourRepository.class);


        // visualuser une maree avec les contenues.

    }
}