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

import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qPageCarnetRepository;
import com.gardecote.data.repository.jpa.qEspeceRepository;
import com.gardecote.data.repository.jpa.qModelJPRepository;
import com.gardecote.data.repository.jpa.qCapturesRepository;
import com.gardecote.data.repository.jpa.qMareeRepository;
import com.gardecote.data.repository.jpa.qJourMereRepository;
import java.util.List;
import java.util.List;
import java.util.ArrayList;
import com.gardecote.data.repository.jpa.qLicenceRepository;
@SpringBootApplication
public class GardecoteApplication {
    public static void main(String[] args) {
     ApplicationContext ctx= SpringApplication.run(GardecoteApplication.class, args);

       qCategRessourceRepository qcategressourcerepository= ctx.getBean(qCategRessourceRepository.class);
        //-------------------------------------------------------------------------------------------
        // creer les categories de ressource 5 PA
        qTypeConcession paCeph=new qTypeConcessionArtisanal(enumTypeConcessionArtisanal.Cephalopode);
        qTypeConcession paCrust=new qTypeConcessionArtisanal(enumTypeConcessionArtisanal.Crustaces);
        qTypeConcession paDem=new qTypeConcessionArtisanal(enumTypeConcessionArtisanal.Poissons_demersaux);
        qTypeConcession paPel=new qTypeConcessionArtisanal(enumTypeConcessionArtisanal.Poissons_Pelagique);
        qTypeConcession paAlAut=new qTypeConcessionArtisanal(enumTypeConcessionArtisanal.Algues_et_autres_Mollusques);
        qEnginPeche qEng1=new qEnginPeche("Pots",70);
        qEnginPeche qEng2=new qEnginPeche("Pots",30);
        List<qEnginPeche> qEngins=new ArrayList<qEnginPeche>();
        qEngins.add(qEng1); qEngins.add(qEng2);
        qEnginPecheRepository qenginpecherepository= ctx.getBean(qEnginPecheRepository.class);
        qenginpecherepository.save(qEng1);
        qenginpecherepository.save(qEng2);
        qCategRessource qPACep=new qCategRessource(paCeph,enumSupport.Collectif,qEngins);
        qCategRessource qPACrust=new qCategRessource(paCrust,enumSupport.Collectif,qEngins);
        qCategRessource qPADem=new qCategRessource(paDem,enumSupport.Collectif,qEngins);
        qCategRessource qPAPel=new qCategRessource(paPel,enumSupport.Collectif,qEngins);
        qCategRessource qPAAlAut=new qCategRessource(paAlAut,enumSupport.Collectif,qEngins);
        qcategressourcerepository.save(qPACep);
        qcategressourcerepository.save(qPACrust);
        qcategressourcerepository.save(qPADem);
        qcategressourcerepository.save(qPAPel);
        qcategressourcerepository.save(qPAAlAut);
     //-------------------------------------------------------------------------------------------
        // creer les categories de ressource 10 PC
        qTypeConcessionCotiere  pcNPCeph=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Cepholopode,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcNPCrust=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Crustaces,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcNPDem=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Poissons_demersaux,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcNPPelSenneursM26=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Poissons_Pelagiques_Seg1,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcNPPelSenneurs26A40=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Poissons_Pelagiques_Seg2,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcNPPelSenneurs40A60=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Poissons_Pelagiques_Seg3,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcNPAutreMol=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Autres_Mollusques,enumTypePechCotiere.NON_PONTEE);
        qCategRessource qPCNPCep=new qCategRessource(pcNPCeph,enumSupport.Collectif,qEngins);
        qCategRessource qPCNPCrust=new qCategRessource(pcNPCrust,enumSupport.Collectif,qEngins);
        qCategRessource qPCNPDem=new qCategRessource(pcNPDem,enumSupport.Collectif,qEngins);
        qCategRessource qPCNPPel1=new qCategRessource(pcNPPelSenneursM26,enumSupport.Collectif,qEngins);
        qCategRessource qPCNPPel2=new qCategRessource(pcNPPelSenneurs26A40,enumSupport.Collectif,qEngins);
        qCategRessource qPCNPPel3=new qCategRessource(pcNPPelSenneurs40A60,enumSupport.Collectif,qEngins);
        qCategRessource qPCNPAlAut=new qCategRessource(pcNPAutreMol,enumSupport.Collectif,qEngins);
        qcategressourcerepository.save(qPCNPCep);
        qcategressourcerepository.save(qPCNPCrust);
        qcategressourcerepository.save(qPCNPDem);
        qcategressourcerepository.save(qPCNPPel1);
        qcategressourcerepository.save(qPCNPPel2);
        qcategressourcerepository.save(qPCNPPel3);
        qcategressourcerepository.save(qPCNPAlAut);
// peche cotier pontee

        qTypeConcessionCotiere  pcPCeph=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Cepholopode,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcPCrust=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Crustaces,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcPDem=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Poissons_demersaux,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcPPelSenneursM26=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Poissons_Pelagiques_Seg1,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcPPelSenneurs26A40=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Poissons_Pelagiques_Seg2,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcPPelSenneurs40A60=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Poissons_Pelagiques_Seg3,enumTypePechCotiere.NON_PONTEE);
        qTypeConcessionCotiere  pcPAutreMol=new qTypeConcessionCotiere(enumTypeConcessionCotiere.Autres_Mollusques,enumTypePechCotiere.NON_PONTEE);
        qCategRessource qPCPCep=new qCategRessource(pcPCeph,enumSupport.Collectif,qEngins);
        qCategRessource qPCPCrust=new qCategRessource(pcPCrust,enumSupport.Collectif,qEngins);
        qCategRessource qPCPDem=new qCategRessource(pcPDem,enumSupport.Collectif,qEngins);
        qCategRessource qPCPPel1=new qCategRessource(pcPPelSenneursM26,enumSupport.Collectif,qEngins);
        qCategRessource qPCPPel2=new qCategRessource(pcPPelSenneurs26A40,enumSupport.Collectif,qEngins);
        qCategRessource qPCPPel3=new qCategRessource(pcPPelSenneurs40A60,enumSupport.Collectif,qEngins);
        qCategRessource qPCPAlAut=new qCategRessource(pcPAutreMol,enumSupport.Collectif,qEngins);

        qcategressourcerepository.save(qPCPCep);
        qcategressourcerepository.save(qPCPCrust);
        qcategressourcerepository.save(qPCPDem);
        qcategressourcerepository.save(qPCPPel1);
        qcategressourcerepository.save(qPCPPel2);
        qcategressourcerepository.save(qPCPPel3);
        qcategressourcerepository.save(qPCPAlAut);

        // creer les categories de ressource 9 PH
        qTypeConcessionHautiriere  phNCeph=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Cephalopode,enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere  phNAutres=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Autres_Mollusques,enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere  phNCrab=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Crabe_profond,enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere  phNCrv=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Crevettes,enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere  phNLangRose=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Langouste_rose,enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere  phNMerlu=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Merlus,enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere  phNPel=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Pelagique,enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere  phNDemAQM=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Poissons_demersaux_autre_que_merlu,enumTypePecheHautiriere.National);
        qTypeConcessionHautiriere  phNThon=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Thons,enumTypePecheHautiriere.National);
        qCategRessource qRCphNCeph=new qCategRessource(phNCeph,enumSupport.Individuel,qEngins);
        qCategRessource qRCphNAutres=new qCategRessource(phNAutres,enumSupport.Individuel,qEngins);
        qCategRessource qRCphNCrab=new qCategRessource(phNCrab,enumSupport.Individuel,qEngins);
        qCategRessource qRCphNCrv=new qCategRessource(phNCrv,enumSupport.Individuel,qEngins);
        qCategRessource qRCphNLangRose=new qCategRessource(phNMerlu,enumSupport.Individuel,qEngins);
        qCategRessource qRCphNMerlu=new qCategRessource(phNPel,enumSupport.Individuel,qEngins);
        qCategRessource qRCphNPel=new qCategRessource(phNDemAQM,enumSupport.Individuel,qEngins);
        qCategRessource qRCphNDemAQM=new qCategRessource(phNThon,enumSupport.Individuel,qEngins);
        qcategressourcerepository.save(qRCphNCeph);
        qcategressourcerepository.save(qRCphNAutres);
        qcategressourcerepository.save(qRCphNCrab);
        qcategressourcerepository.save(qRCphNCrv);
        qcategressourcerepository.save(qRCphNLangRose);
        qcategressourcerepository.save(qRCphNMerlu);
        qcategressourcerepository.save(qRCphNPel);
        qcategressourcerepository.save(qRCphNDemAQM);


        qTypeConcessionHautiriere  phACeph=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Cephalopode,enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere  phAAutres=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Autres_Mollusques,enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere  phACrab=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Crabe_profond,enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere  phACrv=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Crevettes,enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere  phALangRose=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Langouste_rose,enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere  phAMerlu=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Merlus,enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere  phAPel=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Pelagique,enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere  phADemAQM=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Poissons_demersaux_autre_que_merlu,enumTypePecheHautiriere.Affraite);
        qTypeConcessionHautiriere  phAThon=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Thons,enumTypePecheHautiriere.Affraite);

        qCategRessource qRCphACeph=new qCategRessource(phACeph,enumSupport.Individuel,qEngins);
        qCategRessource qRCphAAutres=new qCategRessource(phAAutres,enumSupport.Individuel,qEngins);
        qCategRessource qRCphACrab=new qCategRessource(phACrab,enumSupport.Individuel,qEngins);
        qCategRessource qRCphACrv=new qCategRessource(phACrv,enumSupport.Individuel,qEngins);
        qCategRessource qphALangRose=new qCategRessource(phALangRose,enumSupport.Individuel,qEngins);
        qCategRessource qRCphAMerlu=new qCategRessource(phAMerlu,enumSupport.Individuel,qEngins);
        qCategRessource qRCphAPel=new qCategRessource(phAPel,enumSupport.Individuel,qEngins);
        qCategRessource qRCphADemAQM=new qCategRessource(phADemAQM,enumSupport.Individuel,qEngins);
        qCategRessource qRCphAThon=new qCategRessource(phAThon,enumSupport.Individuel,qEngins);


        qcategressourcerepository.save(qRCphACeph);
        qcategressourcerepository.save(qRCphAAutres);
        qcategressourcerepository.save(qRCphACrab);
        qcategressourcerepository.save(qRCphACrv);
        qcategressourcerepository.save(qphALangRose);
        qcategressourcerepository.save(qRCphAMerlu);
        qcategressourcerepository.save(qRCphAPel);
        qcategressourcerepository.save(qRCphADemAQM);
        qcategressourcerepository.save(qRCphAThon);

        qTypeConcessionHautiriere  phLCeph=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Cephalopode,enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere  phLAutres=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Autres_Mollusques,enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere  phLCrab=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Crabe_profond,enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere  phLCrv=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Crevettes,enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere  phLLangRose=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Langouste_rose,enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere  phLMerlu=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Merlus,enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere  phLPel=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Pelagique,enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere  phLDemAQM=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Poissons_demersaux_autre_que_merlu,enumTypePecheHautiriere.Licence);
        qTypeConcessionHautiriere  phLThon=new qTypeConcessionHautiriere(enumTypeConcessionHautiriere.Thons,enumTypePecheHautiriere.Licence);
        qCategRessource qRCphLCeph=new qCategRessource(phLCeph,enumSupport.Individuel,qEngins);
        qCategRessource qRCphLAutres=new qCategRessource(phLAutres,enumSupport.Individuel,qEngins);
        qCategRessource qRCphLCrab=new qCategRessource(phLCrab,enumSupport.Individuel,qEngins);
        qCategRessource qRCphLCrv=new qCategRessource(phLCrv,enumSupport.Individuel,qEngins);
        qCategRessource qphLLangRose=new qCategRessource(phLLangRose,enumSupport.Individuel,qEngins);
        qCategRessource qRCLphALangRose=new qCategRessource(phLMerlu,enumSupport.Individuel,qEngins);
        qCategRessource qRCLphAMerlu=new qCategRessource(phLPel,enumSupport.Individuel,qEngins);
        qCategRessource qRCLphAPel=new qCategRessource(phLDemAQM,enumSupport.Individuel,qEngins);
        qCategRessource qRCLphADemAQM=new qCategRessource(phLThon,enumSupport.Individuel,qEngins);
        qcategressourcerepository.save(qRCphLCeph);
        qcategressourcerepository.save(qRCphLAutres);
        qcategressourcerepository.save(qRCphLCrab);
        qcategressourcerepository.save(qRCphLCrv);
        qcategressourcerepository.save(qphLLangRose);
        qcategressourcerepository.save(qRCLphALangRose);
        qcategressourcerepository.save(qRCLphAMerlu);
        qcategressourcerepository.save(qRCLphAPel);
        qcategressourcerepository.save(qRCLphADemAQM);
        //  creer les categories de ressource 9 PH


        //  creer le consignataires
        qConsignataireRepository qConsignataireRepository= ctx.getBean(qConsignataireRepository.class);
        qConsignataire qconsignataire1=new qConsignataire("Jelal eddine");
        qConsignataire qconsignataire2=new qConsignataire("Jelal eddine");
        qConsignataire qconsignataire3=new qConsignataire("Jelal eddine");

        //creer les engins de peches



        // creer la concession

        qConcessionRepository qconsignationrepository= ctx.getBean(qConcessionRepository.class);

        qConcession qConcession=new qConcession();

        // creer une licence bat last
        qLicenceRepository qlicencebatlastrepository= ctx.getBean(qLicenceRepository.class);
        qLicence qlicencebatlast=new qLicence();

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
        qMarree qmarree=new qMarree(enumTypeDoc.Journal_Peche,null);

        // accocier les jours avex les pages et les jours avec les captures
        qJourMereRepository qjourrepository=ctx.getBean(qJourMereRepository.class);


        // visualuser une maree avec les contenues.

    }
}