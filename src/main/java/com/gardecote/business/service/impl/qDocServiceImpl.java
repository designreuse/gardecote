package com.gardecote.business.service.impl;

import com.gardecote.business.service.qDocService;
import com.gardecote.data.repository.jpa.*;
import com.gardecote.entities.*;
import org.hibernate.Session;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dell on 08/11/2016.
 */
@Service
@Transactional
public class qDocServiceImpl implements qDocService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private qMarreeAnnexeRepository qmarreeannexeRepository;

    @Autowired
    private qDocRepository qdocRepository;
    @Autowired
    private qCategRessourceRepository qcqtRepository;

    @Autowired
    private qSeqRepository qseqRepository;
    @Autowired
    private qCarnetRepository qcarnetRepository;
    @Autowired
    private qModelJPRepository qmodelRepository;
    @Autowired
    private qPageCarnetRepository qpagecarnetRepository;
    @Autowired
    private qJourMereRepository qjourmereRepository;


    @Override
    public qDoc findById(qDocPK idcarnet) {
        qDoc authprovEntity = qdocRepository.findOne(idcarnet);
        return authprovEntity;
    }

  //  @Override
  //   public List<qDoc> findAll(Pageable pageable) {
  //       Iterable<qDoc> entities = qdocRepository.findAll(pageable);
    //     List<qDoc> beans = new ArrayList<qDoc>();
    //      for(qDoc authprovEntity1 : entities) {
    //         beans.add(authprovEntity1);
    //     }
    //     return beans;
    //  }

    @Override
    public qDoc save(qDoc authprov) {
 //     Session session = em.unwrap(Session.class);
        qDoc authprovEntity = qdocRepository.findOne(authprov.getqDocPK());
        return qdocRepository.save(authprov);
        //     Session session = em.unwrap(Session.class);
 //       if (authprovEntity == null) {
   //         em.persist(authprov);
   //         return authprov;
   //     } else {
        //   session.evict(authprovEntity);
      //      return em.merge(authprov);
    //     }
    }

    @Override
    public qDoc create(qDoc authprov) {

 //       qDoc qdoc = qdocRepository.findOne(authprov.getqDocPK());
  //      if( qdoc != null ) {
   //         throw new IllegalStateException("Document deja saisie");
    //    }
        qDoc authprovEntitySaved = qdocRepository.save(authprov);
        return authprovEntitySaved;
    }

    @Override
    public qDoc update(qDoc authprov) {
      // qDoc authprovEntity = qdocRepository.findOne(authprov.getqDocPK());

        qDoc authprovEntitySaved = qdocRepository.save(authprov);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(qDocPK  idauthpr) {
       qDoc ff= qdocRepository.findOne(idauthpr);
        List<qJourMere> jms=new ArrayList<qJourMere>();
        if(ff instanceof  qMarree) {
            for(Iterator<qPageMarree> iter=((qMarree) ff).getPages().iterator();iter.hasNext();) {
                qPageMarree currentp=iter.next();

                for (Iterator<qJourMere> iter1 = currentp.getListJours().iterator(); iter1.hasNext(); )
                {  qJourMere currj=iter1.next();
                    currj.setPageMarree(null);
                    jms.add(currj);
                }
                currentp.setListJours(null);
                currentp.setQmarree(null);
                  qpagecarnetRepository.save(currentp);
            }
            ((qMarree) ff).setPages(null);
            qdocRepository.save(ff);
            for(qJourMere jk:jms) {
                qjourmereRepository.delete(jk);
            }
            qdocRepository.delete(ff.getqDocPK());
        }

   }

    public qDocRepository getAuthprovJpaRepository() {
        return qdocRepository;
    }

    public void setAuthprovJpaRepository(qDocRepository authprovJpaRepository) {
        this.qdocRepository = authprovJpaRepository;
    }

    @Override
    public qDoc verifierAncienDoc(qSeqPK sequencepk) {
        qSeq currentSeq=qseqRepository.findOne(sequencepk);
        if(currentSeq!=null) return currentSeq.getQdoc(); // verifier EAGER
        else  return null;
    }

    @Override
    public List<qJourDeb> jourDebDejaUtilisee(String numimm,Date datedep,Date dateret) {
     System.out.println(qdocRepository.checkUsedJoursDeb(numimm,datedep,dateret));
     return qdocRepository.checkUsedJoursDeb(numimm,datedep,dateret);

    }
    @Override
    public List<qJourMere> jourMereDejaUtilisee(String numimm,Date datedep,Date dateret) {
        return qdocRepository.checkUsedJoursMere(numimm,datedep,dateret);

    }




    @Override
public qDebarquement creerDebarquement(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
//    qSeq seqActive=qseqRepository.findOne(seqActive1.getSeqPK());
    qSeq seqActive=seqActive1;
    String numeroDebut = seqActive.getDebut(),numeroFin = seqActive.getFin(), debutPrefix = null,finPrefix = null;
    qDebarquement      documentCree=null;
    qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroDebut,typeDoc));
    qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroFin,typeDoc));
    qCarnet carnetDebut = debutp.getQcarnet();
    qCarnet carnetFin = finp.getQcarnet();
    debutPrefix=carnetDebut.getPrefixNumerotation().toString();

    qNavire qnav = carnetDebut.getQnavire();
    qConcession qconcess =carnetDebut.getQconcession();//qnav.getQlicencebatlastdernier().getQconcession(); // concession du dernier lic
    //carnetDebut.getQconcession();
    Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();
    List<qEnginPecheDebar> choixEnginsDeb=new ArrayList<qEnginPecheDebar>();
    qEnginPecheDebar engdeb1=new qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Filet_maillant,0,false);
    qEnginPecheDebar engdeb2=new  qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Filet_Enc_ST,0,false);
    qEnginPecheDebar engdeb3=new  qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Palangre,0,false);
    qEnginPecheDebar engdeb4=new  qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Filet_tremail,0,false);

    qEnginPecheDebar engdeb6=new qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Casier,0,false);
    qEnginPecheDebar engdeb7=new qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Ligne,0,false);
    qEnginPecheDebar engdeb8=new qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Pots,0,false);
    qEnginPecheDebar engdeb9=new qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Turlutte,0,false);
    choixEnginsDeb.add(engdeb1);
    choixEnginsDeb.add(engdeb2);
    choixEnginsDeb.add(engdeb3);
    choixEnginsDeb.add(engdeb4);

    choixEnginsDeb.add(engdeb6);
    choixEnginsDeb.add(engdeb7);
    choixEnginsDeb.add(engdeb8);
    choixEnginsDeb.add(engdeb9);

    List<qCategDeb> categsArt=new ArrayList<qCategDeb>();
    qCategRessource cr1=qcqtRepository.findOne(1);
    qCategRessource cr2=qcqtRepository.findOne(2);
    qCategRessource cr3=qcqtRepository.findOne(3);
    qCategRessource cr4=qcqtRepository.findOne(4);
    qCategRessource cr5=qcqtRepository.findOne(5);

    qCategDeb c1=new qCategDeb(qnav.getNumimm(),dateDepart,cr1,false);
    qCategDeb c2=new qCategDeb(qnav.getNumimm(),dateDepart,cr2,false);
    qCategDeb c3=new qCategDeb(qnav.getNumimm(),dateDepart,cr3,false);
    qCategDeb c4=new qCategDeb(qnav.getNumimm(),dateDepart,cr4,false);
    qCategDeb c5=new qCategDeb(qnav.getNumimm(),dateDepart,cr5,false);

    categsArt.add(c1);categsArt.add(c2);categsArt.add(c3);categsArt.add(c4);categsArt.add(c5);

    List<qCategDeb> categsCot=new ArrayList<qCategDeb>();
    qCategRessource cr11=qcqtRepository.findOne(6);
    qCategRessource cr21=qcqtRepository.findOne(7);
    qCategRessource cr31=qcqtRepository.findOne(8);
    qCategRessource cr41=qcqtRepository.findOne(9);
    qCategRessource cr51=qcqtRepository.findOne(10);
    qCategRessource cr61=qcqtRepository.findOne(11);
    qCategRessource cr71=qcqtRepository.findOne(12);

    qCategDeb c11=new qCategDeb(qnav.getNumimm(),dateDepart,cr11,false);
    qCategDeb c21=new qCategDeb(qnav.getNumimm(),dateDepart,cr21,false);
    qCategDeb c31=new qCategDeb(qnav.getNumimm(),dateDepart,cr31,false);
    qCategDeb c41=new qCategDeb(qnav.getNumimm(),dateDepart,cr41,false);

    categsCot.add(c11);categsCot.add(c21);categsCot.add(c31);categsCot.add(c41);
 qPrefixPK pref=new qPrefixPK(debutPrefix,typeDoc);
    qModelJP currentModel=qmodelRepository.findOne(pref);


        if (carnetDebut.getPrefixNumerotation().equals("PC")) {
            // la peche cotiere
            documentCree = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,null,
                    choixEnginsDeb,  enumTypeDebarquement.Cotier, qconcess,categsCot, null);
        }

        if (carnetDebut.getPrefixNumerotation().equals("PA")) {
            // la peche artisanal

            documentCree = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,null,
                    choixEnginsDeb, enumTypeDebarquement.Artisanal,qconcess, categsArt,null);


        }
        List<qPageDebarquement> lstPgsDeb = new ArrayList<qPageDebarquement>();
        // preparer les pages
        List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
        for (qPageCarnet qpc : pagesConcernees) {
            lstPgsDeb.add((qPageDebarquement) qpc);
        }
        Iterator<qPageDebarquement> qdebpages = lstPgsDeb.iterator();
        Date dateJourCourant = documentCree.getDepart();

        while (qdebpages.hasNext()) {
            qPageDebarquement currp = qdebpages.next();
            currp.setEtatPage(enumEtatPage.DRAFT);
            currp.setQdebarquement((qDebarquement) documentCree);
            List<qJourDeb> joursDeb = new ArrayList<qJourDeb>();
            // pqrcourir les ligne de page
            for (int k = 0; k < currp.getNbrLigne(); k++) {
                if (dateJourCourant.before(documentCree.getRetour()) || dateJourCourant.equals(documentCree.getRetour())) {

                    qJourDeb jourDeb = new qJourDeb(dateJourCourant, qnav, null, currp);
                    List<qCapture> capturesLine = new ArrayList<qCapture>();
                    // traitement des captures

                    for (qEspeceTypee esptypee : currentModel.getEspecestypees()) {
                        qCapture qcapture = new qCapture(documentCree, esptypee, 0, null, jourDeb);
                        qcapture.setQdoc(documentCree);
                        qcapture.setJourDeb(jourDeb);
                        qcapture.setDatedepart(documentCree.getDepart());
                        qcapture.setNummimm(documentCree.getNumImm());
                        qcapture.setDateJour(dateJourCourant);
                        qcapture.setIdespece(esptypee.getQespeceId());
                        qcapture.setEsptype(esptypee.getEnumesptype());
                        capturesLine.add(qcapture);
                        //  capturesTotal.add(qcapture);

                    }
                    jourDeb.setDebarqDuJour(capturesLine);

                    jourDeb.setPagesDeb(currp);
                    joursDeb.add(jourDeb);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dateJourCourant);
                    cal.add(Calendar.DATE, 1);
                    dateJourCourant = cal.getTime();
                    //   capturesLine.clear();
                }
            }
            currp.setListJours(joursDeb);
            //  joursDeb.clear();
        }
        documentCree.setQseq(seqActive);
        documentCree.setPages(lstPgsDeb);
        //   ((qDebarquement)qdoc).setCaptures(capturesTotal);
    return documentCree;
    }

    @Override
    public qMarreeAnnexe creerAnnexe(Date dateDepart,qMarree qCurrentMaree,qSeqPK spk,enumTypeDoc typeDoc) {
        qMarreeAnnexe documentCree=null;
        List<qPageAnnexe> lstPgsAnx=new ArrayList<qPageAnnexe>();

        String numeroDebut = spk.getDebut(),numeroFin = spk.getFin(), debutPrefix = null,finPrefix = null;
        qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroFin,typeDoc));
        qCarnet carnetFin = finp.getQcarnet();
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(carnetFin.getPrefixNumerotation()+numeroDebut,typeDoc));

        Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();
        qCarnet carnetDebut = debutp.getQcarnet();

        debutPrefix=carnetDebut.getPrefixNumerotation().toString();

        qNavire qnav = carnetDebut.getQnavire();
        documentCree = new qMarreeAnnexe(qCurrentMaree.getDepart(),qCurrentMaree.getNumImm(),qCurrentMaree, null, null,null,null,null);

        List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
        for (qPageCarnet qpc : pagesConcernees) {
            lstPgsAnx.add((qPageAnnexe) qpc);
        }
        List<qPageAnnexe> lstPgsMar = new ArrayList<qPageAnnexe>();
        // preparer les pages

        Iterator<qPageAnnexe> qmarpages = lstPgsAnx.iterator();
        Date dateJourCourant = documentCree.getDepart();


        List<qJourMereAnnexe> joursMar = new ArrayList<qJourMereAnnexe>();
        while (qmarpages.hasNext()) {
            qPageAnnexe currp = qmarpages.next();
            currp.setEtatPage(enumEtatPage.DRAFT);
            currp.setQmarreeAnexe((qMarreeAnnexe) documentCree);

            // parcourir les ligne de page
            for (int k = 0; k < currp.getNbrLigne(); k++) {
                    qJourMereAnnexe jourMar = new qJourMereAnnexe(k,"",dateDepart, qnav, null, 0, null, currp);
                    jourMar.setPageMarree(currp);
                    joursMar.add(jourMar);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dateJourCourant);
                    cal.add(Calendar.DATE, 1);
                    dateJourCourant = cal.getTime();

            }
            currp.setListJours(joursMar);
            //   joursMar.clear();
            lstPgsMar.add(currp);
        }

        documentCree.setPages(lstPgsMar);
        documentCree.setMarreePrincipal(qCurrentMaree);


        System.out.println("saving document cree annex");
        //   ((qDebarquement)qdoc).setCaptures(capturesTotal);
    //    return  qmarreeannexeRepository.save(documentCree);
        return documentCree;

    }
    @Override
    public qMarree creerMarree(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
     //   qSeq seqActive=qseqRepository.findOne(seqActive1.getSeqPK());
        qSeq seqActive=seqActive1;
        String numeroDebut = seqActive.getDebut(),numeroFin = seqActive.getFin(), debutPrefix = null,finPrefix = null;
        qMarree documentCree=null;
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroDebut,typeDoc));
        qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroFin,typeDoc));
        Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();
        qCarnet carnetDebut = debutp.getQcarnet();
        qCarnet carnetFin = finp.getQcarnet();
        debutPrefix=carnetDebut.getPrefixNumerotation().toString();

        qNavire qnav = carnetDebut.getQnavire();
        qConcession qconcess;
        //qnav.getQlicencebatlastdernier().getQconcession(); // concession du dernier lic
        //carnetDebut.getQconcession();
        List<qEnginPecheMar> choixEnginsMar=new ArrayList<qEnginPecheMar>();
        qEnginPecheMar engmar1=new qEnginPecheMar(qnav.getNumimm(),dateDepart,enumEngin.Chalut,enumEnginDeb.Indefini,0,false);
        qEnginPecheMar engmar2=new qEnginPecheMar(qnav.getNumimm(),dateDepart,enumEngin.Casier,enumEnginDeb.Indefini,0,false);
        qEnginPecheMar engmar3=new  qEnginPecheMar(qnav.getNumimm(),dateDepart,enumEngin.Nasses,enumEnginDeb.Indefini,0,false);
        qEnginPecheMar engmar4=new  qEnginPecheMar(qnav.getNumimm(),dateDepart,enumEngin.Pots,enumEnginDeb.Indefini,0,false);

        qEnginPecheMar engmar6=new qEnginPecheMar(qnav.getNumimm(),dateDepart,enumEngin.Filet_tremail,enumEnginDeb.Indefini,0,false);
        qEnginPecheMar engmar7=new qEnginPecheMar(qnav.getNumimm(),dateDepart,enumEngin.Turlutte,enumEnginDeb.Indefini,0,false);

        choixEnginsMar.add(engmar1);
        choixEnginsMar.add(engmar2);
        choixEnginsMar.add(engmar3);
        choixEnginsMar.add(engmar4);

        choixEnginsMar.add(engmar6);
        choixEnginsMar.add(engmar7);


        List<qCategRessource>  ours=new ArrayList<qCategRessource>();
        Integer flagcotiere = 0, flaghautiriere = 0;
        if(carnetDebut.getQconcession()!=null) {qconcess=carnetDebut.getQconcession();
        ours.addAll(carnetDebut.getQconcession().getCategoriesRessources());}
        else qconcess=null;

        documentCree = new qMarree(enumTypeDoc.Journal_Peche, dateDepart, dateRetour, seqActive, qnav,null,
                qconcess,enumJP.Hautirere ,  choixEnginsMar, null);
        documentCree.setSegPeche(debutPrefix);
        documentCree.setNomNavire(qnav.getNomnav());

         qPrefixPK pref=new qPrefixPK(debutPrefix,typeDoc);
        // cat du dernier licence
        qModelJP currentModel=qmodelRepository.findOne(pref);
        System.out.println(debutPrefix);
        // pour la journal de peche
        System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        System.out.println(currentModel.getEspecestypees());
        System.out.println(currentModel.getEspecestypees().size());
        for (qEspeceTypee esptypee : currentModel.getEspecestypees()){
            System.out.println(esptypee.getQespece().getCodeEsp());
                    System.out.println(esptypee.getQespece().getNomFr());
        }
        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        List<qPageMarree> lstPgsMar = new ArrayList<qPageMarree>();
        // preparer les pages
        List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
        for (qPageCarnet qpc : pagesConcernees) {
            lstPgsMar.add((qPageMarree) qpc);
        }
        Iterator<qPageMarree> qmarpages = lstPgsMar.iterator();
        Date dateJourCourant = documentCree.getDepart();
        List<qJourMere> joursMar = new ArrayList<qJourMere>();
        while (qmarpages.hasNext()) {
            qPageMarree currp = qmarpages.next();
            currp.setEtatPage(enumEtatPage.DRAFT);
            currp.setQmarree((qMarree) documentCree);
            // parcourir les ligne de page
            for (int k = 0; k < currp.getNbrLigne(); k++) {
                if (dateJourCourant.before(documentCree.getRetour()) || dateJourCourant.equals(documentCree.getRetour())) {
                    qJourMere jourMar = new qJourMere(dateJourCourant, qnav, null, 0, 0, 0, currp);
                    // traitement des captures
                    List<qCapture> capturesLine = new ArrayList<qCapture>();
                    for (qEspeceTypee esptypee : currentModel.getEspecestypees()) {
                        qCapture qcapture = new qCapture(documentCree, esptypee, 0, jourMar, null);
                        qcapture.setQdoc(documentCree);
                        qcapture.setJourMere(jourMar);
                        qcapture.setDatedepart(documentCree.getDepart());
                        qcapture.setNummimm(documentCree.getNumImm());
                        qcapture.setDateJour(dateJourCourant);
                        qcapture.setIdespece(esptypee.getQespeceId());
                        qcapture.setEsptype(esptypee.getEnumesptype());
                        capturesLine.add(qcapture);

                        //  capturesTotal.add(qcapture);

                    }
                    jourMar.setTotalCapturs(0);
                    jourMar.setTotalCong(0);
                    jourMar.setNbrCaisse(0);
                    jourMar.setCapturesDuMarree(capturesLine);
                    jourMar.setPageMarree(currp);
                    joursMar.add(jourMar);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dateJourCourant);
                    cal.add(Calendar.DATE, 1);
                    dateJourCourant = cal.getTime();
                    //    capturesLine.clear();
                }
            }
            currp.setListJours(joursMar);
         //   joursMar.clear();
        }
        documentCree.setQseq(seqActive);
        documentCree.setPages(lstPgsMar);
        //   ((qDebarquement)qdoc).setCaptures(capturesTotal);
         return documentCree;
    }
    @Override
    public qTraitement creerTraitement(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
        //   qSeq seqActive=qseqRepository.findOne(seqActive1.getSeqPK());
        qSeq seqActive=seqActive1;
        String numeroDebut = seqActive.getDebut(),numeroFin = seqActive.getFin(), debutPrefix = null,finPrefix = null;
        qTraitement documentCree=null;
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroDebut,typeDoc));
        qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroFin,typeDoc));
        Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();
        qCarnet carnetDebut = debutp.getQcarnet();
        qCarnet carnetFin = finp.getQcarnet();
        debutPrefix=carnetDebut.getPrefixNumerotation().toString();

        qUsine qusine = carnetDebut.getQusine();
     //   qConcession qconcess;
        //qnav.getQlicencebatlastdernier().getQconcession(); // concession du dernier lic

        //carnetDebut.getQconcession();
        documentCree = new qTraitement(enumTypeDoc.Fiche_Traitement, dateDepart, dateRetour, seqActive, null,qusine,
                null,qusine.getRefAgrement() , dateDepart,null,null,null,0L, null);

        List<qSegUsines> lstSegUsine=new ArrayList<qSegUsines>();
        qSegUsines seg1=new qSegUsines(documentCree,enumSegPeche.Peche_Artisanal,false,false,false,false,false,false);
        qSegUsines seg2=new qSegUsines(documentCree,enumSegPeche.Pêche_Cotier,false,false,false,false,false,false);
        qSegUsines seg3=new qSegUsines(documentCree,enumSegPeche.Pêche_Hautiriere,false,false,false,false,false,false);
        lstSegUsine.add(seg1);lstSegUsine.add(seg2);lstSegUsine.add(seg3);

        List<qQuantiteExportee> lstQteExp=new ArrayList<qQuantiteExportee>();
        qQuantiteExportee r1=new qQuantiteExportee(documentCree,enumZonOrientation.AFRIC,0);
        qQuantiteExportee r2=new qQuantiteExportee(documentCree,enumZonOrientation.ASIE,0);
        qQuantiteExportee r3=new qQuantiteExportee(documentCree,enumZonOrientation.EUROPE,0);
        qQuantiteExportee r4=new qQuantiteExportee(documentCree,enumZonOrientation.AUTRES,0);
        lstQteExp.add(r1);lstQteExp.add(r2);lstQteExp.add(r3);lstQteExp.add(r4);

        qQuantitesTraites qtesTraitees=new qQuantitesTraites(qusine.getRefAgrement(),dateDepart,0,0,0,0,0,0);


        documentCree.setSegs(lstSegUsine);
        documentCree.setqQteExp(lstQteExp);
        documentCree.setqQteTraitees(qtesTraitees);
        documentCree.setSegPeche(debutPrefix);

       // documentCree.setNomNavire(qnav.getNomnav());

        qPrefixPK pref=new qPrefixPK(debutPrefix,typeDoc);

        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        List<qPageTraitement> lstPgsTait = new ArrayList<qPageTraitement>();
        // preparer les pages
        List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
        for (qPageCarnet qpc : pagesConcernees) {
            lstPgsTait.add((qPageTraitement) qpc);
        }
        Iterator<qPageTraitement> qmarpages = lstPgsTait.iterator();
        Date dateJourCourant = documentCree.getDepart();
        List<qOpTraitement> opSTraitement = new ArrayList<qOpTraitement>();
        while (qmarpages.hasNext()) {
            qPageTraitement currp = qmarpages.next();
            currp.setEtatPage(enumEtatPage.DRAFT);
            currp.setQtraitement((qTraitement) documentCree);
            // parcourir les ligne de page
            for (int k = 0; k < currp.getNbrLigne(); k++) {

                    qOpTraitement uniteTr = new qOpTraitement(currp, null, 0L);

                    uniteTr.setPageTraitement(currp);
                    opSTraitement.add(uniteTr);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dateJourCourant);
                    cal.add(Calendar.DATE, 1);
                    dateJourCourant = cal.getTime();
                    //    capturesLine.clear();

            }
            currp.setOpTraitements( opSTraitement);
            //   joursMar.clear();
        }
        documentCree.setQseq(seqActive);
        documentCree.setPagesTraitement(lstPgsTait);
        //   ((qDebarquement)qdoc).setCaptures(capturesTotal);
        return documentCree;
    }
    @Override
    public qMarreeAnnexe creerNouvAnnexe(Date dateDepart,qMarree qCurrentMaree,qSeqPK spk,enumTypeDoc typeDoc) {
        //    qSeq seqActive = qseqRepository.findOne(seqActive1.getSeqPK());
        qMarreeAnnexe documentCree = null;
        qMarreeAnnexe retObj = null;
        qTraitement traitement = null;
        String numeroDebut = spk.getFin();
        qPageCarnet debutp = qpagecarnetRepository.findOne(new qPageCarnetPK(numeroDebut, typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        if (typeDoc.equals(enumTypeDoc.Journal_Annexe)) {


            if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Journal_Annexe)) {
                documentCree = creerAnnexe(dateDepart,qCurrentMaree, spk, typeDoc);
                retObj = documentCree;
             //   ((qMarree) qCurrentMaree).setMarreeAnnexe(documentCree);
            }



        }
        return retObj;
    }
    @Override
    public qDoc creerNouvDoc(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
    //    qSeq seqActive = qseqRepository.findOne(seqActive1.getSeqPK());
        qDoc documentCree = null;
        qDoc retObj = null;
        qTraitement traitement = null;
        String numeroDebut = seqActive1.getDebut();
        qPageCarnet debutp = qpagecarnetRepository.findOne(new qPageCarnetPK(numeroDebut,typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        if (typeDoc.equals(enumTypeDoc.Fiche_Debarquement)||typeDoc.equals(enumTypeDoc.Journal_Peche) ||typeDoc.equals(enumTypeDoc.Fiche_Traitement)) {

            if (seqActive1.getQdoc() == null) {
                if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {
                    documentCree = creerDebarquement(dateDepart, dateRetour, seqActive1,typeDoc);
                    retObj = documentCree;
                }
                if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Journal_Peche)) {
                    documentCree = creerMarree(dateDepart, dateRetour, seqActive1,typeDoc);
                    retObj = documentCree;
                }
                if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Fiche_Traitement)) {
                    documentCree = creerTraitement(dateDepart, dateRetour, seqActive1,typeDoc);
                    retObj = documentCree;
                }
            }
        }

        return retObj;
    }
    @Override
    public List<qLic> retLicences(qSeq seqActive1,enumTypeDoc typeDoc) {
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getDebut(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qNavire qnav = carnetDebut.getQnavire();
        List<qLic> lics=qdocRepository.retLicences(qnav);
        return lics;
    }

    @Override
    public qDoc checkIfDupDocExist(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
        qNavire qnav=null;
        qUsine  qusine=null;
        qDocPK docpk=null;
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getDebut(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
         if(typeDoc.equals(enumTypeDoc.Fiche_Traitement))
         {
             qusine=carnetDebut.getQusine();
             docpk=new qDocPK(qusine.getRefAgrement(),dateDepart);}
         else
         {
         qnav = carnetDebut.getQnavire();
         docpk=new qDocPK(qnav.getNumimm(),dateDepart);
         }


        qDoc docdoublon=qdocRepository.findOne(docpk);


        if(docdoublon==null) return null;
        else return docdoublon;
    }

    @Override
    public boolean checkIfValidSeq(qSeq seqActive1) {
        qSeq seqActive = qseqRepository.findOne(seqActive1.getSeqPK());
        if(seqActive==null) return true;
        else return false;
    }
    @Override
    public boolean checkNombreJoursMoinsNombreLignes(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
        // calculer nombre des lignes
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getDebut(),typeDoc));
        qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getFin(),typeDoc));
        Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();
        qCarnet carnetDebut = debutp.getQcarnet();
        Integer nbrLignCarnet=carnetDebut.getNbrLigneParPage();
        Long t=finNumberL-debutNumberL+1L;
        Integer nbrLignes=(t.intValue()*carnetDebut.getNbrLigneParPage());

        // calculer nombre des jours)
        Integer nbrDays=Days.daysBetween(new LocalDate(dateDepart), new LocalDate(dateRetour)).getDays();
        // verifier
        if(nbrLignes-nbrDays-1>0 && nbrLignes-nbrDays-1<=nbrLignCarnet-1) return true;
        else return false;
    }
    @Override
    public List<qDoc> checkIfDupPagesExist(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getDebut(),typeDoc));
        qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getFin(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qNavire qnav = carnetDebut.getQnavire();
        List<qJourDeb>   joursdeb=new ArrayList<qJourDeb>();
        List<qJourMere>  joursmere=new ArrayList<qJourMere>();
        List<qDoc> lstDoc=new ArrayList<qDoc>();
        Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();
        List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
        if(carnetDebut.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {
            for (qPageCarnet qp : pagesConcernees) {
                if (qp instanceof qPageDebarquement && ((qPageDebarquement) qp).getQdebarquement()!=null)
                    lstDoc.add(((qPageDebarquement) qp).getQdebarquement());

            }

        }
        if(carnetDebut.getTypeDoc().equals(enumTypeDoc.Journal_Peche)) {
            for (qPageCarnet qp : pagesConcernees) {
               if (qp instanceof qPageMarree && ((qPageMarree) qp).getQmarree()!=null)
                   lstDoc.add(((qPageMarree) qp).getQmarree());

            }

        }

        return lstDoc;
    }

    @Override
    public List<qDoc> checkIfDupJoursExist(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getDebut(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qNavire qnav = carnetDebut.getQnavire();
        List<qJourDeb>   joursdeb=new ArrayList<qJourDeb>();
        List<qJourMere>  joursmere=new ArrayList<qJourMere>();
        List<qDoc> lstDoc=new ArrayList<qDoc>();
        if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement))
        {
            joursdeb=qdocRepository.checkUsedJoursDeb(qnav.getNumimm(),dateDepart,dateRetour);
          if(joursdeb!=null) {
              for(qJourDeb jdeb:joursdeb)
              {
                  qDebarquement  deb=jdeb.getPagesDeb().getQdebarquement();

                  if(deb!=null)    lstDoc.add(deb);
              }
          }

        }
        if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Journal_Peche))
        {
            joursmere=qdocRepository.checkUsedJoursMere(qnav.getNumimm(),dateDepart,dateRetour);
        if(joursmere!=null)   {
            for(qJourMere jmer:joursmere)
            {
                qMarree  maree=jmer.getPageMarree().getQmarree();
                lstDoc.add(maree);

            }
        }
        }

        return lstDoc;
    }

    @Override
    public qModelJP checkIfModelExist(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getDebut(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        String debutPrefix=carnetDebut.getPrefixNumerotation().toString();

        qPrefixPK prefpk=new qPrefixPK(debutPrefix,typeDoc);
        qModelJP currentModel=qmodelRepository.findOne(prefpk);
        return currentModel;
    }

    @Override
    public Page<qDoc> findAll(int p, int size) {
        Page<qDoc> entities = qdocRepository.findAll(new PageRequest(p, size));
        return entities;
    }

    @Override
    public qMarreeAnnexe checkIfDupAnnexeExist(Date dateDepart, qSeqPK spk, enumTypeDoc typeDoc) {
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(spk.getFin(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qNavire qnav = carnetDebut.getQnavire();
        qDocPK docpk=new qDocPK(qnav.getNumimm(),dateDepart);
        qMarreeAnnexe docdoublon=qmarreeannexeRepository.findOne(docpk);
        if(docdoublon==null) return null;
        else return docdoublon;
    }

    @Override

    public boolean checkIfNavAnnexIsSameAsNavPrincipal(Date dateDepart, qSeqPK spk, qMarree currentMaree,enumTypeDoc typeDoc) {
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(spk.getFin(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qNavire qnavAnnex = carnetDebut.getQnavire();
        qNavire qnavMarree = currentMaree.getQnavire();
        if(qnavAnnex.equals(qnavMarree)) return true;
        else return false;
    }

    @Override
    public List<qMarreeAnnexe> checkIfDupPagesAnnexeExist(Date dateDepart, qSeqPK spk, enumTypeDoc typeDoc) {

        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(spk.getFin(),typeDoc));
        qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(spk.getFin(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qNavire qnav = carnetDebut.getQnavire();

        List<qMarreeAnnexe> lstDoc=new ArrayList<qMarreeAnnexe>();

        Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();
        List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
        if(carnetDebut.getTypeDoc().equals(enumTypeDoc.Journal_Annexe)) {
            for (qPageCarnet qp : pagesConcernees) {
                if (qp instanceof qPageAnnexe && ((qPageAnnexe) qp).getQmarreeAnexe()!=null)
                    lstDoc.add(((qPageAnnexe) qp).getQmarreeAnexe());

            }

        }
       return lstDoc;
    }


}

