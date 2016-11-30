package com.gardecote.business.service.impl;

import com.gardecote.business.service.qDocService;
import com.gardecote.data.repository.jpa.*;
import com.gardecote.entities.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public qDoc findById(qDocPK idcarnet) {
        qDoc authprovEntity = qdocRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qDoc> findAll(Pageable pageable) {
        Iterable<qDoc> entities = qdocRepository.findAll(pageable);
        List<qDoc> beans = new ArrayList<qDoc>();
        for(qDoc authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qDoc save(qDoc authprov) {

        Session session = em.unwrap(Session.class);
        qDoc authprovEntity = qdocRepository.findOne(authprov.getqDocPK());
        if (authprovEntity == null) {
            em.persist(authprov);
            return authprov;
        } else {

            //   session.evict(authprovEntity);
            return em.merge(authprov);
        }
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
        qdocRepository.delete(idauthpr);
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
    public Map<String,Set<Object>> detecterDestructionDonnees(qSeqPK seqActive,Date dateDepartt) {
        Set<Object> lstDoc=new HashSet<Object>();
        Set<Object> lstTraitements=new HashSet<Object>();
        Set<Object> lstExact =new HashSet<Object>();

        Map<String,Set<Object>> mpResult=new HashMap<String,Set<Object>>();

        Integer fi = 0, fl = 0;
        qDoc qdoc=null;
        String numeroDebut = seqActive.getDebut(), numeroFin = seqActive.getFin(), debutPrefix = null, finPrefix = null;
        qPageCarnet debutp=qpagecarnetRepository.findOne(numeroDebut);
        qPageCarnet        finp=qpagecarnetRepository.findOne(numeroFin);
        Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();

            qCarnet carnetDebut = debutp.getQcarnet();
            qCarnet carnetFin = finp.getQcarnet();
        qNavire nv=carnetDebut.getQnavire();
        qDocPK qpk=new qDocPK(nv.getNumimm(),dateDepartt);

        qDoc qdocc=qdocRepository.findOne(qpk);
        if(qdocc!=null) {
        if(qdocc.getQseq().getSeqPK().equals(seqActive))   lstExact.add(qdocc);
         else lstDoc.add(qdocc);

        }

            List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
            for (qPageCarnet qp : pagesConcernees) {
                if (qp instanceof qPageDebarquement)
                    lstDoc.add(((qPageDebarquement) qp).getQdebarquement());

                if (qp instanceof qPageMarree) lstDoc.add(((qPageMarree) qp).getQmarree());
                if (qp instanceof qPageTraitement) lstTraitements.add(((qPageTraitement) qp).getQtraitement());
            }




        mpResult.put("lstDoc", lstDoc); // documents empechants la creation
        mpResult.put("lstTraitements", lstTraitements); // documents des usines empechant la creation
        mpResult.put("lstExact", lstExact); // document exacte  deja creer pour ouvrir

        return mpResult;
    }
    @Override
    public boolean checkSaisie(qSeqPK seqActive) {
        boolean flagLiberte = false, flagIdentiteCarnet = false, flg = true;
        Integer fi = 0, fl = 0;
        qDoc qdoc=null;

        String numeroDebut = seqActive.getDebut(), numeroFin = seqActive.getFin(), debutPrefix = null, finPrefix = null;
        String debutNumber = "", finNumber = "";
        Integer indexDebut = -1, indexFin = -1;

        Pattern pattern;
        Matcher matcherDebut, matcherFin;

        pattern = Pattern.compile("(^\\D{2,4})(\\d{1,}$)");
        matcherDebut = pattern.matcher(numeroDebut);
        matcherFin = pattern.matcher(numeroFin);
        boolean b = matcherDebut.matches();
        int z=0,r=0;
        while (b) {
            if(matcherDebut.groupCount()==3) {
                debutPrefix = matcherDebut.group(1); // groupe capturee
                indexDebut = matcherDebut.end();
                debutNumber = String.valueOf(matcherDebut.group(2));
            }
            else {
                debutPrefix = null;  // groupe capturee
                indexDebut = 0;
                debutNumber = null;
            }
        }
        boolean h =  matcherFin.matches();
        while (h) {
          if(matcherDebut.groupCount()==3) {
                finPrefix = matcherFin.group(1);
                indexFin = matcherFin.end();
                finNumber = String.valueOf(matcherFin.group(2));
            }
            else {
                finPrefix = null;
                indexFin = 0;
                finNumber = null;
                 }
        }


        boolean isnumericDebut = debutNumber.matches("-?\\d+(\\.\\d+)?"), isnumericFin = finNumber.matches("-?\\d+(\\.\\d+)?");

        qCarnet carnetDebut = qcarnetRepository.retCarnet(numeroDebut);
        qCarnet carnetFin = qcarnetRepository.retCarnet(numeroFin);

        if (debutPrefix.equals(finPrefix) && indexDebut == indexFin && isnumericDebut == true && isnumericFin == true && this.contains(debutPrefix) &&
                carnetDebut.equals(carnetFin) && Long.valueOf(debutNumber)<=Long.valueOf(finNumber))
        return true;
        else return false;
    }
    public static boolean contains(String test) {

        for (enumPrefix c : enumPrefix.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public qDoc creerDoc(Date dateDepart, Date dateRetour, qSeq seqActive) {
        String numeroDebut = seqActive.getDebut(),numeroFin = seqActive.getFin(), debutPrefix = null,finPrefix = null;
        qDoc documentCree=null;


        qPageCarnet        debutp=qpagecarnetRepository.findOne(numeroDebut);
        qPageCarnet        finp=qpagecarnetRepository.findOne(numeroFin);

        Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();

        qCarnet carnetDebut = debutp.getQcarnet();
        qCarnet carnetFin = finp.getQcarnet();
        debutPrefix=carnetDebut.getPrefixNumerotation().toString();

        qNavire qnav = carnetDebut.getQnavire();
        qConcession qconcess =carnetDebut.getQconcession();//qnav.getQlicencebatlastdernier().getQconcession(); // concession du dernier lic
        //carnetDebut.getQconcession();
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


        List<qEnginPecheMar> choixEnginsMar=null;


        qModelJP currentModel=qmodelRepository.findOne(enumPrefix.valueOf(debutPrefix));
       List<qCategRessource>  ours=new ArrayList<qCategRessource>();
        carnetDebut.getQconcession().getCategoriesRessources();
        ours.addAll(carnetDebut.getQconcession().getCategoriesRessources());        // cat du dernier licence
        if(seqActive.getQdoc()==null) {
            if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {
                if (carnetDebut.getPrefixNumerotation().equals(enumPrefix.PC)) {
                    // la peche cotiere
                    documentCree = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,
                             choixEnginsDeb,  enumTypeDebarquement.Cotier, qconcess,categsCot, null);
                }

                if (carnetDebut.getPrefixNumerotation().equals(enumPrefix.PA)) {
                    // la peche artisanal

                    documentCree = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,
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

                    currp.setListJours(joursDeb);
                  //  joursDeb.clear();
                }
                documentCree.setQseq(seqActive);
                ((qDebarquement) documentCree).setPages(lstPgsDeb);
                //   ((qDebarquement)qdoc).setCaptures(capturesTotal);


            }
            if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Journal_Peche)) {
                Integer flagcotiere = 0, flaghautiriere = 0;
                for (qCategRessource qcat : qconcess.getCategoriesRessources()) {
                    if (qcat.getTypeconcessionConcernee() instanceof qTypeConcessionCotiere) flagcotiere++;
                    if (qcat.getTypeconcessionConcernee() instanceof qTypeConcessionHautiriere) flaghautiriere++;
                }
                // pour la journal de peche
                if (flagcotiere > 0)
                    documentCree = new qMarree(enumTypeDoc.Journal_Peche, dateDepart, dateRetour, seqActive, qnav,
                            qconcess, enumJP.Cotier,  choixEnginsMar, null);
                if (flaghautiriere > 0)
                    documentCree = new qMarree(enumTypeDoc.Journal_Peche, dateDepart, dateRetour, seqActive, qnav,
                            qconcess,  enumJP.Hautirere, choixEnginsMar, null);

                List<qPageMarree> lstPgsMar = new ArrayList<qPageMarree>();
                // preparer les pages
                List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
                for (qPageCarnet qpc : pagesConcernees) {
                    lstPgsMar.add((qPageMarree) qpc);
                }
                Iterator<qPageMarree> qmarpages = lstPgsMar.iterator();
                Date dateJourCourant = documentCree.getDepart();
                List<qCapture> capturesLine = new ArrayList<qCapture>();

                List<qJourMere> joursMar = new ArrayList<qJourMere>();
                while (qmarpages.hasNext()) {
                    qPageMarree currp = qmarpages.next();
                    currp.setEtatPage(enumEtatPage.DRAFT);
                    currp.setQmarree((qMarree) documentCree);

                    // pqrcourir les ligne de page
                    for (int k = 0; k < currp.getNbrLigne(); k++) {

                        qJourMere jourMar = new qJourMere(dateJourCourant, qnav, null, 0, 0, 0, currp);
                        // traitement des captures

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
                        jourMar.setCapturesDuMarree(capturesLine);

                        jourMar.setPageMarree(currp);
                        joursMar.add(jourMar);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dateJourCourant);
                        cal.add(Calendar.DATE, 1);
                        dateJourCourant = cal.getTime();
                        capturesLine.clear();
                    }

                    currp.setListJours(joursMar);
                    joursMar.clear();
                }
                documentCree.setQseq(seqActive);
                ((qMarree) documentCree).setPages(lstPgsMar);
                //   ((qDebarquement)qdoc).setCaptures(capturesTotal);


            }

            if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Fiche_Traitement)) {

            }
        }
        else {
            documentCree=seqActive.getQdoc();
        }
        return documentCree;
    }
}

