package com.gardecote;
import com.gardecote.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.joda.time.DateTime;
import com.gardecote.data.repository.jpa.qCategRessourceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.gardecote.data.repository.jpa.qConsignataireRepository;
import com.gardecote.data.repository.jpa.qEnginPecheRepository;
import com.gardecote.data.repository.jpa.qConcessionRepository;
import com.gardecote.data.repository.jpa.qDocRepository;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qPageCarnetRepository;
import com.gardecote.data.repository.jpa.qEspeceRepository;
import com.gardecote.data.repository.jpa.qModelJPRepository;
import com.gardecote.data.repository.jpa.qCapturesRepository;
import com.gardecote.data.repository.jpa.qMareeRepository;
import com.gardecote.data.repository.jpa.qJourMereRepository;

import java.text.ParseException;
import java.util.List;
import java.util.List;
import java.util.ArrayList;
import com.gardecote.data.repository.jpa.qLicenceRepository;
import com.gardecote.data.repository.jpa.qTypeConcessionRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.gardecote.data.repository.jpa.qTypeLicRepository;
import com.gardecote.data.repository.jpa.qZoneRepository;
import com.gardecote.data.repository.jpa.qNationRepository;
import com.gardecote.data.repository.jpa.qRegistreNavireRepository;
import com.gardecote.data.repository.jpa.qEspeceTypeeRepository;
@SpringBootApplication
public class GardecoteApplication {
    public static void main(String[] args) {
     ApplicationContext ctx = SpringApplication.run(GardecoteApplication.class, args);

     qCategRessourceRepository qcategressourcerepository = ctx.getBean(qCategRessourceRepository.class);
     qTypeConcessionRepository qtypeconcessionpository = ctx.getBean(qTypeConcessionRepository.class);
     //-------------------------------------------------------------------------------------------
     // creer les categories de ressource 5 PA  de 1 a 5

     qTypeConcession paCeph = new qTypeConcessionArtisanal(enumPrefix.PA,enumTypeConcessionArtisanal.Cephalopode);
     qTypeConcession paCrust = new qTypeConcessionArtisanal(enumPrefix.PA,enumTypeConcessionArtisanal.Crustaces);
     qTypeConcession paDem = new qTypeConcessionArtisanal(enumPrefix.PA,enumTypeConcessionArtisanal.Poissons_demersaux);
     qTypeConcession paPel = new qTypeConcessionArtisanal(enumPrefix.PA,enumTypeConcessionArtisanal.Poissons_Pelagique);
     qTypeConcession paAlAut = new qTypeConcessionArtisanal(enumPrefix.PA,enumTypeConcessionArtisanal.Algues_et_autres_Mollusques);

     qEnginPeche qEng1 = new qEnginPeche(enumEngin.Casier, 70);
     qEnginPeche qEng2 = new qEnginPeche(enumEngin.Chalut, 30);
     List<qEnginPeche> qEngins = new ArrayList<qEnginPeche>();
     qEngins.add(qEng1);
     qEngins.add(qEng2);
     qEnginPecheRepository qenginpecherepository = ctx.getBean(qEnginPecheRepository.class);
     qenginpecherepository.save(qEng1);
     qenginpecherepository.save(qEng2);
     qtypeconcessionpository.save(paCeph);
     qtypeconcessionpository.save(paCrust);
     qtypeconcessionpository.save(paDem);
     qtypeconcessionpository.save(paPel);
     qtypeconcessionpository.save(paAlAut);
     qCategRessource qPACep = new qCategRessource(paCeph, enumSupport.Collectif, qEngins);
     qCategRessource qPACrust = new qCategRessource(paCrust, enumSupport.Collectif, qEngins);
     qCategRessource qPADem = new qCategRessource(paDem, enumSupport.Collectif, qEngins);
     qCategRessource qPAPel = new qCategRessource(paPel, enumSupport.Collectif, qEngins);
     qCategRessource qPAAlAut = new qCategRessource(paAlAut, enumSupport.Collectif, qEngins);

     System.out.println(paCeph.getQtypeconcessionpk());
     qcategressourcerepository.save(qPACep);
     qcategressourcerepository.save(qPACrust);
     qcategressourcerepository.save(qPADem);
     qcategressourcerepository.save(qPAPel);
     qcategressourcerepository.save(qPAAlAut);
     //-------------------------------------------------------------------------------------------
     // creer les categories de ressource 10 PC de 6 a 12
     qTypeConcessionCotiere pcNPCeph = new qTypeConcessionCotiere(enumPrefix.CEPH,enumTypeConcessionCotiere.Cepholopode, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcNPCrust = new qTypeConcessionCotiere(enumPrefix.CRUS,enumTypeConcessionCotiere.Crustaces, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcNPDem = new qTypeConcessionCotiere(enumPrefix.DEM,enumTypeConcessionCotiere.Poissons_demersaux, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcNPPelSenneursM26 = new qTypeConcessionCotiere(enumPrefix.PE,enumTypeConcessionCotiere.Poissons_Pelagiques_Seg1, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcNPPelSenneurs26A40 = new qTypeConcessionCotiere(enumPrefix.PE,enumTypeConcessionCotiere.Poissons_Pelagiques_Seg2, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcNPPelSenneurs40A60 = new qTypeConcessionCotiere(enumPrefix.PE,enumTypeConcessionCotiere.Poissons_Pelagiques_Seg3, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcNPAutreMol = new qTypeConcessionCotiere(enumPrefix.IND,enumTypeConcessionCotiere.Autres_Mollusques, enumTypePechCotiere.NON_PONTEE);
     qtypeconcessionpository.save(pcNPCeph);
     qtypeconcessionpository.save(pcNPCrust);
     qtypeconcessionpository.save(pcNPDem);
     qtypeconcessionpository.save(pcNPPelSenneursM26);
     qtypeconcessionpository.save(pcNPPelSenneurs26A40);
     qtypeconcessionpository.save(pcNPPelSenneurs40A60);
     qtypeconcessionpository.save(pcNPAutreMol);

     qCategRessource qPCNPCep = new qCategRessource(pcNPCeph, enumSupport.Collectif, qEngins);
     qCategRessource qPCNPCrust = new qCategRessource(pcNPCrust, enumSupport.Collectif, qEngins);
     qCategRessource qPCNPDem = new qCategRessource(pcNPDem, enumSupport.Collectif, qEngins);
     qCategRessource qPCNPPel1 = new qCategRessource(pcNPPelSenneursM26, enumSupport.Collectif, qEngins);
     qCategRessource qPCNPPel2 = new qCategRessource(pcNPPelSenneurs26A40, enumSupport.Collectif, qEngins);
     qCategRessource qPCNPPel3 = new qCategRessource(pcNPPelSenneurs40A60, enumSupport.Collectif, qEngins);
     qCategRessource qPCNPAlAut = new qCategRessource(pcNPAutreMol, enumSupport.Collectif, qEngins);


     qcategressourcerepository.save(qPCNPCep);
     qcategressourcerepository.save(qPCNPCrust);
     qcategressourcerepository.save(qPCNPDem);
     qcategressourcerepository.save(qPCNPPel1);
     qcategressourcerepository.save(qPCNPPel2);
     qcategressourcerepository.save(qPCNPPel3);
     qcategressourcerepository.save(qPCNPAlAut);

        // peche cotier pontee 13 a 19

     qTypeConcessionCotiere pcPCeph = new qTypeConcessionCotiere(enumPrefix.PC,enumTypeConcessionCotiere.Cepholopode, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcPCrust = new qTypeConcessionCotiere(enumPrefix.PC,enumTypeConcessionCotiere.Crustaces, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcPDem = new qTypeConcessionCotiere(enumPrefix.PC,enumTypeConcessionCotiere.Poissons_demersaux, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcPPelSenneursM26 = new qTypeConcessionCotiere(enumPrefix.PC,enumTypeConcessionCotiere.Poissons_Pelagiques_Seg1, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcPPelSenneurs26A40 = new qTypeConcessionCotiere(enumPrefix.PC,enumTypeConcessionCotiere.Poissons_Pelagiques_Seg2, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcPPelSenneurs40A60 = new qTypeConcessionCotiere(enumPrefix.PC,enumTypeConcessionCotiere.Poissons_Pelagiques_Seg3, enumTypePechCotiere.NON_PONTEE);
     qTypeConcessionCotiere pcPAutreMol = new qTypeConcessionCotiere(enumPrefix.PC,enumTypeConcessionCotiere.Autres_Mollusques, enumTypePechCotiere.NON_PONTEE);

     qtypeconcessionpository.save(pcPCeph);
     qtypeconcessionpository.save(pcPCrust);
     qtypeconcessionpository.save(pcPDem);
     qtypeconcessionpository.save(pcPPelSenneursM26);
     qtypeconcessionpository.save(pcPPelSenneurs26A40);
     qtypeconcessionpository.save(pcPPelSenneurs40A60);
     qtypeconcessionpository.save(pcPAutreMol);


     qCategRessource qPCPCep = new qCategRessource(pcPCeph, enumSupport.Collectif, qEngins);
     qCategRessource qPCPCrust = new qCategRessource(pcPCrust, enumSupport.Collectif, qEngins);
     qCategRessource qPCPDem = new qCategRessource(pcPDem, enumSupport.Collectif, qEngins);
     qCategRessource qPCPPel1 = new qCategRessource(pcPPelSenneursM26, enumSupport.Collectif, qEngins);
     qCategRessource qPCPPel2 = new qCategRessource(pcPPelSenneurs26A40, enumSupport.Collectif, qEngins);
     qCategRessource qPCPPel3 = new qCategRessource(pcPPelSenneurs40A60, enumSupport.Collectif, qEngins);
     qCategRessource qPCPAlAut = new qCategRessource(pcPAutreMol, enumSupport.Collectif, qEngins);

     qcategressourcerepository.save(qPCPCep);
     qcategressourcerepository.save(qPCPCrust);
     qcategressourcerepository.save(qPCPDem);
     qcategressourcerepository.save(qPCPPel1);
     qcategressourcerepository.save(qPCPPel2);
     qcategressourcerepository.save(qPCPPel3);
     qcategressourcerepository.save(qPCPAlAut);

     // creer les categories de ressource 9 PH de 19 a  27
     qTypeConcessionHautiriere phNCeph = new qTypeConcessionHautiriere(enumPrefix.CEPH,enumTypeConcessionHautiriere.Cephalopode, enumTypePecheHautiriere.National);
     qTypeConcessionHautiriere phNAutres = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Autres_Mollusques, enumTypePecheHautiriere.National);
     qTypeConcessionHautiriere phNCrab = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Crabe_profond, enumTypePecheHautiriere.National);
     qTypeConcessionHautiriere phNCrv = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Crevettes, enumTypePecheHautiriere.National);
     qTypeConcessionHautiriere phNLangRose = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Langouste_rose, enumTypePecheHautiriere.National);
     qTypeConcessionHautiriere phNMerlu = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Merlus, enumTypePecheHautiriere.National);
     qTypeConcessionHautiriere phNPel = new qTypeConcessionHautiriere(enumPrefix.PE,enumTypeConcessionHautiriere.Pelagique, enumTypePecheHautiriere.National);
     qTypeConcessionHautiriere phNDemAQM = new qTypeConcessionHautiriere(enumPrefix.DEM,enumTypeConcessionHautiriere.Poissons_demersaux_autre_que_merlu, enumTypePecheHautiriere.National);
     qTypeConcessionHautiriere phNThon = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Thons, enumTypePecheHautiriere.National);

     qtypeconcessionpository.save(phNCeph);
     qtypeconcessionpository.save(phNAutres);
     qtypeconcessionpository.save(phNCrab);
     qtypeconcessionpository.save(phNCrv);
     qtypeconcessionpository.save(phNLangRose);
     qtypeconcessionpository.save(phNMerlu);
     qtypeconcessionpository.save(phNPel);
     qtypeconcessionpository.save(phNDemAQM);
     qtypeconcessionpository.save(phNThon);


     qCategRessource qRCphNCeph = new qCategRessource(phNCeph, enumSupport.Individuel, qEngins);
     qCategRessource qRCphNAutres = new qCategRessource(phNAutres, enumSupport.Individuel, qEngins);
     qCategRessource qRCphNCrab = new qCategRessource(phNCrab, enumSupport.Individuel, qEngins);
     qCategRessource qRCphNCrv = new qCategRessource(phNCrv, enumSupport.Individuel, qEngins);
     qCategRessource qRCphNLangRose = new qCategRessource(phNMerlu, enumSupport.Individuel, qEngins);
     qCategRessource qRCphNMerlu = new qCategRessource(phNPel, enumSupport.Individuel, qEngins);
     qCategRessource qRCphNPel = new qCategRessource(phNDemAQM, enumSupport.Individuel, qEngins);
     qCategRessource qRCphNDemAQM = new qCategRessource(phNThon, enumSupport.Individuel, qEngins);
     qcategressourcerepository.save(qRCphNCeph);
     qcategressourcerepository.save(qRCphNAutres);
     qcategressourcerepository.save(qRCphNCrab);
     qcategressourcerepository.save(qRCphNCrv);
     qcategressourcerepository.save(qRCphNLangRose);
     qcategressourcerepository.save(qRCphNMerlu);
     qcategressourcerepository.save(qRCphNPel);
     qcategressourcerepository.save(qRCphNDemAQM);


     qTypeConcessionHautiriere phACeph = new qTypeConcessionHautiriere(enumPrefix.CEPH,enumTypeConcessionHautiriere.Cephalopode, enumTypePecheHautiriere.Affraite);
     qTypeConcessionHautiriere phAAutres = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Autres_Mollusques, enumTypePecheHautiriere.Affraite);
     qTypeConcessionHautiriere phACrab = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Crabe_profond, enumTypePecheHautiriere.Affraite);
     qTypeConcessionHautiriere phACrv = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Crevettes, enumTypePecheHautiriere.Affraite);
     qTypeConcessionHautiriere phALangRose = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Langouste_rose, enumTypePecheHautiriere.Affraite);
     qTypeConcessionHautiriere phAMerlu = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Merlus, enumTypePecheHautiriere.Affraite);
     qTypeConcessionHautiriere phAPel = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Pelagique, enumTypePecheHautiriere.Affraite);
     qTypeConcessionHautiriere phADemAQM = new qTypeConcessionHautiriere(enumPrefix.DEM,enumTypeConcessionHautiriere.Poissons_demersaux_autre_que_merlu, enumTypePecheHautiriere.Affraite);
     qTypeConcessionHautiriere phAThon = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Thons, enumTypePecheHautiriere.Affraite);

     qtypeconcessionpository.save(phACeph);
     qtypeconcessionpository.save(phAAutres);
     qtypeconcessionpository.save(phACrab);
     qtypeconcessionpository.save(phACrv);
     qtypeconcessionpository.save(phALangRose);
     qtypeconcessionpository.save(phAMerlu);
     qtypeconcessionpository.save(phAPel);
     qtypeconcessionpository.save(phADemAQM);
     qtypeconcessionpository.save(phAThon);

     qCategRessource qRCphACeph = new qCategRessource(phACeph, enumSupport.Individuel, qEngins);
     qCategRessource qRCphAAutres = new qCategRessource(phAAutres, enumSupport.Individuel, qEngins);
     qCategRessource qRCphACrab = new qCategRessource(phACrab, enumSupport.Individuel, qEngins);
     qCategRessource qRCphACrv = new qCategRessource(phACrv, enumSupport.Individuel, qEngins);
     qCategRessource qphALangRose = new qCategRessource(phALangRose, enumSupport.Individuel, qEngins);
     qCategRessource qRCphAMerlu = new qCategRessource(phAMerlu, enumSupport.Individuel, qEngins);
     qCategRessource qRCphAPel = new qCategRessource(phAPel, enumSupport.Individuel, qEngins);
     qCategRessource qRCphADemAQM = new qCategRessource(phADemAQM, enumSupport.Individuel, qEngins);
     qCategRessource qRCphAThon = new qCategRessource(phAThon, enumSupport.Individuel, qEngins);


     qcategressourcerepository.save(qRCphACeph);
     qcategressourcerepository.save(qRCphAAutres);
     qcategressourcerepository.save(qRCphACrab);
     qcategressourcerepository.save(qRCphACrv);
     qcategressourcerepository.save(qphALangRose);
     qcategressourcerepository.save(qRCphAMerlu);
     qcategressourcerepository.save(qRCphAPel);
     qcategressourcerepository.save(qRCphADemAQM);
     qcategressourcerepository.save(qRCphAThon);

     qTypeConcessionHautiriere phLCeph = new qTypeConcessionHautiriere(enumPrefix.CEPH,enumTypeConcessionHautiriere.Cephalopode, enumTypePecheHautiriere.Licence);
     qTypeConcessionHautiriere phLAutres = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Autres_Mollusques, enumTypePecheHautiriere.Licence);
     qTypeConcessionHautiriere phLCrab = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Crabe_profond, enumTypePecheHautiriere.Licence);
     qTypeConcessionHautiriere phLCrv = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Crevettes, enumTypePecheHautiriere.Licence);
     qTypeConcessionHautiriere phLLangRose = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Langouste_rose, enumTypePecheHautiriere.Licence);
     qTypeConcessionHautiriere phLMerlu = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Merlus, enumTypePecheHautiriere.Licence);
     qTypeConcessionHautiriere phLPel = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Pelagique, enumTypePecheHautiriere.Licence);
     qTypeConcessionHautiriere phLDemAQM = new qTypeConcessionHautiriere(enumPrefix.DEM,enumTypeConcessionHautiriere.Poissons_demersaux_autre_que_merlu, enumTypePecheHautiriere.Licence);
     qTypeConcessionHautiriere phLThon = new qTypeConcessionHautiriere(enumPrefix.IND,enumTypeConcessionHautiriere.Thons, enumTypePecheHautiriere.Licence);

     qtypeconcessionpository.save(phLCeph);
     qtypeconcessionpository.save(phLAutres);
     qtypeconcessionpository.save(phLCrab);
     qtypeconcessionpository.save(phLCrv);
     qtypeconcessionpository.save(phLLangRose);
     qtypeconcessionpository.save(phLMerlu);
     qtypeconcessionpository.save(phLPel);
     qtypeconcessionpository.save(phLDemAQM);
     qtypeconcessionpository.save(phLThon);

     qCategRessource qRCphLCeph = new qCategRessource(phLCeph, enumSupport.Individuel, qEngins);
     qCategRessource qRCphLAutres = new qCategRessource(phLAutres, enumSupport.Individuel, qEngins);
     qCategRessource qRCphLCrab = new qCategRessource(phLCrab, enumSupport.Individuel, qEngins);
     qCategRessource qRCphLCrv = new qCategRessource(phLCrv, enumSupport.Individuel, qEngins);
     qCategRessource qphLLangRose = new qCategRessource(phLLangRose, enumSupport.Individuel, qEngins);
     qCategRessource qRCLphALangRose = new qCategRessource(phLMerlu, enumSupport.Individuel, qEngins);
     qCategRessource qRCLphAMerlu = new qCategRessource(phLPel, enumSupport.Individuel, qEngins);
     qCategRessource qRCLphAPel = new qCategRessource(phLDemAQM, enumSupport.Individuel, qEngins);
     qCategRessource qRCLphADemAQM = new qCategRessource(phLThon, enumSupport.Individuel, qEngins);
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
     qConsignataireRepository qConsignataireRepository = ctx.getBean(qConsignataireRepository.class);
     qConsignataire qconsignataire1 = new qConsignataire("Jelal eddine");
     qConsignataire qconsignataire2 = new qConsignataire("Jelal eddine 3");
     qConsignataire qconsignataire3 = new qConsignataire("Jelal eddine 2 ");
     qConsignataireRepository.save(qconsignataire1);
     qConsignataireRepository.save(qconsignataire2);
     qConsignataireRepository.save(qconsignataire3);
     //creer les engins de peches


     // creer la concession

     qConcessionRepository qconsignationrepository = ctx.getBean(qConcessionRepository.class);
     SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yy");
     //   SimpleDateFormat sdfmt2= new SimpleDateFormat("dd-MMM-yyyy");
     Date  dateLicence=null,datedebut=null, dateFin=null;

     try {
      dateLicence= sdfmt1.parse("12/12/2016");
      datedebut= sdfmt1.parse("12/12/2016");
      dateFin= sdfmt1.parse("12/12/2016");
     } catch (ParseException e) {
      e.printStackTrace();
     }
     // artisanal de 1 à 5
     // COTIER DE 6 à 19
     // HAUTIRIERE de 20 à 46

     List<qCategRessource> qcatList1=new ArrayList<qCategRessource>();
     qcatList1.add(qcategressourcerepository.findOne(13));
     qcatList1.add(qcategressourcerepository.findOne(14));
     qcatList1.add(qcategressourcerepository.findOne(15));
     qcatList1.add(qcategressourcerepository.findOne(16));
     qcatList1.add(qcategressourcerepository.findOne(17));
     qcatList1.add(qcategressourcerepository.findOne(18));
     qcatList1.add(qcategressourcerepository.findOne(19));


// c est une concession de type cotiere non pontee
    qConcession qConcessionPC=new qConcession("PC2016/0001",qconsignataire3,dateLicence,datedebut,dateFin,null,null);

     qconsignationrepository.save(qConcessionPC);
     // creer une licence bat last
        qLicenceRepository qlicencebatlastrepository= ctx.getBean(qLicenceRepository.class);
     qTypeLicRepository qtypelicrepo=ctx.getBean(qTypeLicRepository.class);
     qTypeLic qtyplic1=new qTypeLic('A','A',"Affreté artisanal");
     qTypeLic qtyplic2=new qTypeLic('A','C',"Affreté collecte pêche artisanal");
     qTypeLic qtyplic3=new qTypeLic('A','D',"Affreté demersal(poisson+ ceph)");
     qTypeLic qtyplic4=new qTypeLic('A','E',"Affreté demersal(crust+ ceph)");
     qTypeLic qtyplic5=new qTypeLic('A','L',"Affreté Langouste");
     qTypeLic qtyplic6=new qTypeLic('A','M',"Affreté Merlu");
     qTypeLic qtyplic7=new qTypeLic('A','P',"Affreté Pelagique");
     qTypeLic qtyplic8=new qTypeLic('A','T',"Affreté thon");
     qTypeLic qtyplic9=new qTypeLic('A','B',"Affreté crabes");
     qTypeLic qtyplic10=new qTypeLic('A','Q',"Affreté coquillage");
     qTypeLic qtyplic11=new qTypeLic('A','R',"Affreté recherche");
     qTypeLic qtyplic12=new qTypeLic('A','S',"Affreté requin");
     qTypeLic qtyplic13=new qTypeLic('A','V',"Affreté crevettes");
     qTypeLic qtyplic14=new qTypeLic('A','1',"Affreté crevettes+langoustes");
     qTypeLic qtyplic15=new qTypeLic('A','F',"Affreté demersal+lang verte");


     qTypeLic qtyplic20=new qTypeLic('L','A',"Licence artisanal");
     qTypeLic qtyplic21=new qTypeLic('L','C',"Licence collecte peche artisanal");
     qTypeLic qtyplic22=new qTypeLic('L','D',"Licence dem poss+ceph");
     qTypeLic qtyplic23=new qTypeLic('L','L',"Licence langouste");
     qTypeLic qtyplic24=new qTypeLic('L','M',"Licence merlu");
     qTypeLic qtyplic25=new qTypeLic('L','P',"Licence pelagique");
     qTypeLic qtyplic26=new qTypeLic('L','T',"Licence thon");
     qTypeLic qtyplic27=new qTypeLic('L','V',"Licence crust sauf langouste");
     qTypeLic qtyplic28=new qTypeLic('L','B',"Licence crabes");
     qTypeLic qtyplic29=new qTypeLic('L','H',"Licence esp dem profond");
     qTypeLic qtyplic30=new qTypeLic('L','Q',"Licence coquillage");
     qTypeLic qtyplic31=new qTypeLic('L','R',"Licence recherche");
     qTypeLic qtyplic32=new qTypeLic('L','S',"Licence requin");
     qTypeLic qtyplic33=new qTypeLic('L','1',"Licence crevettes +langouste");


     qTypeLic qtyplic35=new qTypeLic('N','A',"National artisanal");
     qTypeLic qtyplic36=new qTypeLic('N','C',"National collecte peche artisanal");
     qTypeLic qtyplic37=new qTypeLic('N','D',"National dem poiss+ ceph");
     qTypeLic qtyplic38=new qTypeLic('N','E',"National dem cep+ crust");
     qTypeLic qtyplic39=new qTypeLic('N','L',"National langouste");
     qTypeLic qtyplic40=new qTypeLic('N','M',"National merlu");
     qTypeLic qtyplic41=new qTypeLic('N','P',"National pelagique");
     qTypeLic qtyplic42=new qTypeLic('N','T',"National thon");
     qTypeLic qtyplic43=new qTypeLic('N','B',"National crabes");
     qTypeLic qtyplic44=new qTypeLic('N','Q',"National coquillage");
     qTypeLic qtyplic45=new qTypeLic('N','R',"National recherche");
     qTypeLic qtyplic46=new qTypeLic('N','S',"National requin");
     qTypeLic qtyplic47=new qTypeLic('N','V',"National crevettes");
     qTypeLic qtyplic48=new qTypeLic('N','1',"National crevettes+langouste");

     qTypeLic qtyplic49=new qTypeLic('L','G',"Licence Esp dem autre que merlu");
     qTypeLic qtyplic50=new qTypeLic('L','E',"Licence Esp dem");
     qTypeLic qtyplic51=new qTypeLic('A','V',"AU");
     qTypeLic qtyplic52=new qTypeLic('L','I',"Licence Thons+Espadons");
     qTypeLic qtyplic53=new qTypeLic('N','N',"NN");
     qTypeLic qtyplic54=new qTypeLic('N','I',"NI");
     qTypeLic qtyplic55=new qTypeLic('N','U',"NU");
     qTypeLic qtyplic56=new qTypeLic('L','T',"LT");
     qTypeLic qtyplic57=new qTypeLic('X','P',"XP");
     qTypeLic qtyplic58=new qTypeLic('Z','Z',"INDET");
     qTypeLic qtyplic59=new qTypeLic('L','0',"Licence INDET");
     qTypeLic qtyplic60=new qTypeLic('N','0',"National");
     qTypeLic qtyplic61=new qTypeLic('A','0',"Affrete INDET");
     qTypeLic qtyplic62=new qTypeLic('A','H',"National ravitalleur pel");

     qtypelicrepo.save(qtyplic1);
     qtypelicrepo.save(qtyplic2);
     qtypelicrepo.save(qtyplic3);
     qtypelicrepo.save(qtyplic4);
     qtypelicrepo.save(qtyplic5);
     qtypelicrepo.save(qtyplic6);
     qtypelicrepo.save(qtyplic7);
     qtypelicrepo.save(qtyplic8);
     qtypelicrepo.save(qtyplic9);
     qtypelicrepo.save(qtyplic10);
     qtypelicrepo.save(qtyplic11);
     qtypelicrepo.save(qtyplic12);
     qtypelicrepo.save(qtyplic13);
     qtypelicrepo.save(qtyplic14);
     qtypelicrepo.save(qtyplic15);
     qtypelicrepo.save(qtyplic20);
     qtypelicrepo.save(qtyplic21);
     qtypelicrepo.save(qtyplic23);
     qtypelicrepo.save(qtyplic24);
     qtypelicrepo.save(qtyplic25);
     qtypelicrepo.save(qtyplic26);
     qtypelicrepo.save(qtyplic27);
     qtypelicrepo.save(qtyplic28);
     qtypelicrepo.save(qtyplic29);
     qtypelicrepo.save(qtyplic30);
     qtypelicrepo.save(qtyplic31);
     qtypelicrepo.save(qtyplic32);
     qtypelicrepo.save(qtyplic33);
qZoneRepository qzonerepo=ctx.getBean(qZoneRepository.class);
qZone qZone1=new qZone(1,"CEE 96 Crustacé sauf Langoust");
     qZone qZone2=new qZone(1,"CEE 96 Crustacé sauf Langoust");
     qZone qZone3=new qZone(2,"CEE 96 Pelagique (Chalut)");
     qZone qZone4=new qZone(3,"CEE 96 Langoust (Casier)");
     qZone qZone5=new qZone(4,"CEE 96 Thon (palangre,canne)");
     qZone qZone6=new qZone(5,"CEE 96 Thon (senne)");
     qZone qZone7=new qZone(6,"CEE 96 Thon(canne ) + appait(sne)");
     qZone qZone8=new qZone(7," CEE 96 Cephalopode (chalut)");
     qZone qZone9=new qZone(8," CEE 96 Thon(canne ) + appait(sne)");
     qZone qZone10=new qZone(9,"CEE 96 Cephalopode (chalut)");
     qZone qZone11=new qZone(10,"CEE 96 Espéce demersal - Merlu (Chalut)");
     qZone qZone12=new qZone(11,"CEE 96 Espéce demersal - Merlu (Autres)");
     qZone qZone13=new qZone(12,"RIM - Japon Thon + Espadon)");
     qZone qZone14=new qZone(13,"ZAP 1 (Petit Pelagique)");
     qZone qZone15=new qZone(14,"ZAP 2 (Thon)");
     qZone qZone16=new qZone(15,"ZAP 3 (RIM)");
     qZone qZone17=new qZone(16," ZAP 3 (CEE)");
     qZone qZone18=new qZone(17,"ZAP 4(Demersaux autre que le merlu)");
     qZone qZone19=new qZone(18,"ZAP 5 (Crabe prophond)");
     qZone qZone20=new qZone(19,"ZAP6 (Maerlu)");
     qZone qZone21=new qZone(20,"ZAP7 (Crevette Gamba)");
     qZone qZone22=new qZone(21,"ZAP8 (Demersaux)");
     qZone qZone23=new qZone(22,"ZAP9 (Langoust Rose)");
     qZone qZone24=new qZone(23,"ZAP 10 (Langostinos)");
     qZone qZone25=new qZone(24,"Artisanal");
     qzonerepo.save(qZone7);
     qNationRepository qnationrep=ctx.getBean(qNationRepository.class);
     qNation qnation1=new qNation("Mauritanie");
     qNation qnation2=new qNation("France");
     qNation qnation3=new qNation("Espagne");
     qNation qnation4=new qNation("Hollande");
     qnationrep.save(qnation1);
     qnationrep.save(qnation2);
     qnationrep.save(qnation3);
     qnationrep.save(qnation4);

     qRegistreNavireRepository qregnavire=ctx.getBean(qRegistreNavireRepository.class);
     qRegistreNavire qreg1=new qRegistreNavire(null,"RT56");

     qregnavire.save(qreg1);
     //   SimpleDateFormat sdfmt2= new SimpleDateFormat("dd-MMM-yyyy");
     Date  dateLicenceDebut=null, dateLicenceFin=null;

     try {

      dateLicenceDebut= sdfmt1.parse("12/12/2016");
      dateLicenceFin= sdfmt1.parse("10/05/2017");
     } catch (ParseException e) {
      e.printStackTrace();
     }

     qLicence qlicencebatlast1=new qLicence(qtyplic20,qTypeEnc.MRT,qZone7,qnation3,qcatList1,qEngins,enumSupport.Individuel,qreg1,qconsignataire2,qConcessionPC,
             enumTypeBat.Congélateur_RTMA, dateLicenceDebut,dateLicenceFin,1983,23,"5G","23","12",12,123,23,"12","12.5M","34","AHMED VALL","ghgh","BATEAU N 1","TR2016","Z23","Nouadhibou","ER345","3ER",23);
     qlicencebatlastrepository.save(qlicencebatlast1);
        // creer les carnet et les pages automatiques
        qCarnetRepository qcarnetrepository= ctx.getBean(qCarnetRepository.class);
        qPageCarnetRepository qPageCarnetRepository=ctx.getBean(qPageCarnetRepository.class);
         // creation d'une fiche de debarquement
     qCarnet qcarnet=new qCarnet(enumTypeDoc.Fiche_Debarquement,enumPrefix.PC,1L,50);
     qDistributeur distributeur=new qDistributeur();

   //  qcarnet.distribuer();
   // qcarnetrepository.save(qcarnet);
   //  qCarnetPK pk1=new qCarnetPK(enumPrefix.PC,1L);
   //  qCarnet qc1=qcarnetrepository.findOne(pk1);

    // System.out.println(" hj "+qc1.getNumeroDebutPage().toString()+qc1.getPrefixNumerotation());
// distribuer le carnet dans le systeme
   qcarnetrepository.save(distributeur.distribuer(qcarnet));
     // distribuer le arnet pour un bâteau
     qcarnetrepository.save(distributeur.distribuer(qcarnet,qConcessionPC,qreg1,null));

  //   System.out.println(" hj "+qcarnet.getCarnetPK().toString());

  //   qcarnetrepository.save(qcarnet);
  //   qcarnetrepository.save(qcarnet);


        qPageCarnet qpagecarnet=new qPageCarnet();

        // distribuer les carnets et
       // qCarnet qcarnet=new qCarnet();

     // crer les modeles et les associer avec  les especes
     qModelJPRepository  qmodeljprepository=ctx.getBean(qModelJPRepository.class);
     qModelJP qmodelPA=new qModelJP(enumPrefix.PA,null);
     qModelJP qmodelPC=new qModelJP(enumPrefix.PC,null);
     qModelJP qmodelJPCEPH=new qModelJP(enumPrefix.CEPH,null);
     qModelJP qmodelJPPE=new qModelJP(enumPrefix.PE,null);
     qModelJP qmodelJPCRUS=new qModelJP(enumPrefix.CRUS,null);
     qModelJP qmodelJPDEM=new qModelJP(enumPrefix.DEM,null);


        // creer les especes
        qEspeceRepository qEspeceRepository=ctx.getBean(qEspeceRepository.class);
        qEspece qespeceArts1=new qEspece("الفئة الاولى","premiere espece",1);
        qEspece qespeceArts2=new qEspece("الفئة الاولى","deuxieme espece",2);
        qEspece qespeceArts3=new qEspece("الفئة الاولى","troisieme espece",3);

     qEspeceRepository.save(qespeceArts1);
     qEspeceRepository.save(qespeceArts2);
     qEspeceRepository.save(qespeceArts3);

     qmodeljprepository.save(qmodelPC);

// les especes typees
     qEspeceTypeeRepository qEspecetypeerepository=ctx.getBean(qEspeceTypeeRepository.class);
     List<qEspeceTypee> esptypessCollection=new ArrayList<qEspeceTypee>();

     qEspeceTypee qespecetypee1=new qEspeceTypee(enumEspType.CIBLEE,qespeceArts1,qmodelPC);
     qEspeceTypee qespecetypee2=new qEspeceTypee(enumEspType.CIBLEE,qespeceArts2,qmodelPC);
     qEspeceTypee qespecetypee3=new qEspeceTypee(enumEspType.CIBLEE,qespeceArts3,qmodelPC);
     esptypessCollection.add(qespecetypee1);
     esptypessCollection.add(qespecetypee2);
     esptypessCollection.add(qespecetypee3);
     qmodelPC.setEspecestypees(esptypessCollection);
     qmodeljprepository.save(qmodelPC);


     // simulation de saisie d'un fiche de debarquement PC Non Pontee n de page = pc7

     // detecter le type de doc associé a la page
String numeroDebut="PC7";
        Long partNumber=Long.valueOf(numeroDebut.substring(2));
     qPageCarnetRepository qpcrnrepo=ctx.getBean(qPageCarnetRepository.class);
     qPageCarnet qpageCurrent=qpcrnrepo.findOne(numeroDebut);
        Integer nbrPgs=3;
        Date  dateDepart=null, dateRetour=null;

        try {

            dateDepart= sdfmt1.parse("03/05/2016");
            dateRetour= sdfmt1.parse("04/06/2016");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        qDocRepository qdocrepo=ctx.getBean(qDocRepository.class);
     if(qpageCurrent.getQcarnet().getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {
         List<qEnginPecheDeb>  qEnginsDeb=new ArrayList<qEnginPecheDeb>();
//for(int i=numeroDebut.substring(1))

         qPageDebarquement qpdeb=new qPageDebarquement();
        if(qpageCurrent.getQcarnet().getPrefixNumerotation().equals(enumPrefix.PC)) {
            //recuperer le ref de concession et le bqteau concernee
           qRegistreNavire qnav= qpageCurrent.getQcarnet().getQnavire();
           qConcession qconcess=qpageCurrent.getQcarnet().getQconcession();
            qEnginsDeb.add(new qEnginPecheDeb(enumEnginDeb.Pots,false,0));               //creer un document de debarquement le PC NON PONTEE
            qEnginsDeb.add(new qEnginPecheDeb(enumEnginDeb.Casier,false,0));
            qEnginsDeb.add(new qEnginPecheDeb(enumEnginDeb.Filet_tremail,false,0));
            qEnginsDeb.add(new qEnginPecheDeb(enumEnginDeb.Filet_maillant,false,0));
            qEnginsDeb.add(new qEnginPecheDeb(enumEnginDeb.Turlutte,false,0));
            qEnginsDeb.add(new qEnginPecheDeb(enumEnginDeb.Ligne,false,0));
            qEnginsDeb.add(new qEnginPecheDeb(enumEnginDeb.Palangre,false,0));
            qEnginsDeb.add(new qEnginPecheDeb(enumEnginDeb.Filet_encerclant_senne_tourn,false,0));

            System.out.println("part number is : "+partNumber+"nbr des categ :");
            qDoc qDebPC=new qDebarquement(enumTypeDoc.Fiche_Debarquement,dateDepart,dateRetour,3,qnav,
                qconcess,qEnginsDeb,qconcess.getCategoriesRessources(),enumTypeDebarquement.Cotier,null);

            qdocrepo.save(qDebPC);

            }
      if(qpageCurrent.getQcarnet().getPrefixNumerotation().equals(enumPrefix.PA)) {
       // le PC ARTISANAL
      }
      else {
       // le prefix de pqge est erronee
      }
     }
     if(qpageCurrent.getQcarnet().getTypeDoc().equals(enumTypeDoc.Fiche_Traitement)) {

     }
     if(qpageCurrent.getQcarnet().getTypeDoc().equals(enumTypeDoc.Journal_Peche)) {

     }

        //creer les jours et leurs captures et les associer avec eux
        qCapturesRepository  qCapturesRepository=ctx.getBean(qCapturesRepository.class);
        qCapture qcapture=new qCapture();

        // creer les marees et les associer avec les pages du maree encvours
         qMareeRepository MareeRepository=ctx.getBean(qMareeRepository.class);
     //   qMarree qmarree=new qMarree(enumTypeDoc.Journal_Peche,null);

        // accocier les jours avex les pages et les jours avec les captures
        qJourMereRepository qjourrepository=ctx.getBean(qJourMereRepository.class);


        // visualuser une maree avec les contenues.

    }
}