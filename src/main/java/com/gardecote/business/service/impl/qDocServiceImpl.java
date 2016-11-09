package com.gardecote.business.service.impl;

import com.gardecote.business.service.qDocService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qDocRepository;
import com.gardecote.data.repository.jpa.qModelJPRepository;
import com.gardecote.data.repository.jpa.qSeqRepository;
import com.gardecote.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private qDocRepository qdocRepository;

    @Autowired
    private qSeqRepository qseqRepository;
    @Autowired
    private qCarnetRepository qcarnetRepository;
    @Autowired
    private qModelJPRepository qmodelRepository;

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
        return update(authprov) ;
    }

    @Override
    public qDoc create(qDoc authprov) {


        qDoc authprovEntitySaved = qdocRepository.save(authprov);
        return authprovEntitySaved;
    }

    @Override
    public qDoc update(qDoc authprov) {
        qDoc authprovEntity = qdocRepository.findOne(authprov.getqDocPK());

        qDoc authprovEntitySaved = qdocRepository.save(authprovEntity);
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
    public Map<String,Set<Object>> detecterDestructionDonnees(qSeqPK seqActive) {
        Set<Object> lstDoc=new HashSet<Object>();
        Set<Object> lstTraitements=new HashSet<Object>();
        Map<String,Set<Object>> mpResult=new HashMap<String,Set<Object>>();
        boolean flagLiberte = false, flagIdentiteCarnet = false, flg = true;
        Integer fi = 0, fl = 0;
        qDoc qdoc=null;
        Pattern pattern=null;
        Matcher matcherDebut, matcherFin;

        String numeroDebut = seqActive.getDebut(), numeroFin = seqActive.getFin(), debutPrefix = null, finPrefix = null;
        String debutNumber = "", finNumber = "";
        Integer indexDebut = -1, indexFin = -1;
        if (checkSaisie(seqActive)) {
            matcherDebut = pattern.matcher(numeroDebut);
            matcherFin = pattern.matcher(numeroFin);
            while (matcherDebut.find()) {
                debutPrefix = matcherDebut.group();
                indexDebut = matcherDebut.end();
                debutNumber = String.valueOf(numeroDebut.substring(indexDebut));
            }
            while (matcherFin.find()) {
                finPrefix = matcherFin.group();
                indexFin = matcherFin.end();
                finNumber = String.valueOf(numeroFin.substring(indexFin));
            }

            Long finNumberL = Long.valueOf(finNumber), debutNumberL = Long.valueOf(debutNumber);
            qCarnet carnetDebut = qcarnetRepository.retCarnet(numeroDebut);
            qCarnet carnetFin = qcarnetRepository.retCarnet(numeroFin);

            List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
              for (qPageCarnet qp : pagesConcernees) {
                  if(qp instanceof qPageDebarquement)
                      lstDoc.add(((qPageDebarquement)qp).getQdebarquement());

                  if(qp instanceof qPageMarree) lstDoc.add(((qPageMarree)qp).getQmarree());
                  if(qp instanceof qPageTraitement) lstTraitements.add(((qPageTraitement)qp).getQtraitement());
                }
            mpResult.put("lstDoc",lstDoc);
            mpResult.put("lstTraitements",lstTraitements);


        }
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

        pattern = Pattern.compile("\\D{2,4}");
        matcherDebut = pattern.matcher(numeroDebut);
        matcherFin = pattern.matcher(numeroFin);
        while (matcherDebut.find()) {
            debutPrefix = matcherDebut.group();
            indexDebut = matcherDebut.end();
            debutNumber = String.valueOf(numeroDebut.substring(indexDebut));
        }
        while (matcherFin.find()) {
            finPrefix = matcherFin.group();
            indexFin = matcherFin.end();
            finNumber = String.valueOf(numeroFin.substring(indexFin));
        }
        boolean isnumericDebut = debutNumber.matches("-?\\d+(\\.\\d+)?"), isnumericFin = finNumber.matches("-?\\d+(\\.\\d+)?");

        qCarnet carnetDebut = qcarnetRepository.retCarnet(numeroDebut);
        qCarnet carnetFin = qcarnetRepository.retCarnet(numeroFin);

        if (debutPrefix.equals(finPrefix) && indexDebut == indexFin && isnumericDebut == true && isnumericFin == true && this.contains(debutPrefix) &&
                carnetDebut.equals(carnetFin) && Long.valueOf(debutNumber)<=Long.valueOf(finNumber))
        return false;
        else return true;
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
    public qDoc creerDoc(Date dateDepart, Date dateRetour, qSeq seqActive, List<qEnginPecheDeb> engisDeb, List<qEnginPeche> engisMar) {
        String numeroDebut = seqActive.getDebut(),numeroFin = seqActive.getFin(), debutPrefix = null,finPrefix = null;
        qDoc documentCree=null;

        Integer indexDebut = 0,indexFin = 0;
        Long debutNumberL=0L,finNumberL =0L;
       String debutNumber=null,finNumber =null;

        Pattern pattern;
        Matcher matcherDebut=null,matcherFin=null;

        pattern = Pattern.compile("\\D{2,4}");
        matcherDebut = pattern.matcher(numeroDebut);

        while (matcherDebut.find()) {
            debutPrefix = matcherDebut.group();
            indexDebut = matcherDebut.end();
            debutNumber = String.valueOf(numeroDebut.substring(indexDebut));
        }
        while (matcherFin.find()) {
            finPrefix = matcherFin.group();
            indexFin = matcherFin.end();
            finNumber = String.valueOf(numeroFin.substring(indexFin));
        }
        finNumberL = Long.valueOf(finNumber);
        debutNumberL = Long.valueOf(debutNumber);

        qCarnet carnetDebut = qcarnetRepository.retCarnet(numeroDebut);
        qRegistreNavire qnav = carnetDebut.getQnavire();
        qConcession qconcess = carnetDebut.getQconcession();
        qModelJP currentModel=qmodelRepository.findOne(enumPrefix.valueOf(debutPrefix));

        if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {
            if (carnetDebut.getPrefixNumerotation().equals(enumPrefix.PC)) {
                // la peche cotiere
                documentCree = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,
                        qconcess, engisDeb, qconcess.getCategoriesRessources(), enumTypeDebarquement.Cotier, null);
            }

            if (carnetDebut.getPrefixNumerotation().equals(enumPrefix.PA)) {
                // la peche artisanal

                documentCree = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,
                        qconcess, engisDeb, qconcess.getCategoriesRessources(), enumTypeDebarquement.Artisanal, null);


            }
                List<qPageDebarquement> lstPgsDeb=new ArrayList<qPageDebarquement>();
                // preparer les pages
            List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
                for(qPageCarnet  qpc:pagesConcernees) {
                    lstPgsDeb.add((qPageDebarquement)qpc);
                }
                Iterator<qPageDebarquement> qdebpages=lstPgsDeb.iterator();
                Date dateJourCourant=  documentCree.getDepart();
                List<qCapture> capturesLine=new ArrayList<qCapture>();

                List<qJourDeb>  joursDeb=new ArrayList<qJourDeb>();
                while(qdebpages.hasNext())
                {
                    qPageDebarquement currp=qdebpages.next();
                    currp.setEtatPage(enumEtatPage.DRAFT);
                    currp.setQdebarquement((qDebarquement) documentCree);

                    // pqrcourir les ligne de page
                    for(int k=0;k<currp.getNbrLigne();k++) {

                        qJourDeb jourDeb=new qJourDeb(dateJourCourant,qnav,null,currp);
                        // traitement des captures

                        for(qEspeceTypee esptypee:currentModel.getEspecestypees())
                        {
                            qCapture qcapture=new qCapture( documentCree,esptypee,0,null,jourDeb);
                            qcapture.setQdoc( documentCree);
                            qcapture.setJourDeb(jourDeb);
                            qcapture.setDatedepart( documentCree.getDepart());
                            qcapture.setNummimm( documentCree.getNumImm());
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
                        cal.add( Calendar.DATE, 1 );
                        dateJourCourant=cal.getTime();
                        capturesLine.clear();
                    }

                    currp.setListJours(joursDeb);
                    joursDeb.clear();
                }
            documentCree.setQseq(seqActive);
                ((qDebarquement) documentCree).setPages(lstPgsDeb);
                //   ((qDebarquement)qdoc).setCaptures(capturesTotal);



        }
        if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Journal_Peche)) {
            Integer flagcotiere=0,flaghautiriere=0;
            for(qCategRessource qcat:qconcess.getCategoriesRessources() )
            {
                if(qcat.getTypeconcessionConcernee() instanceof qTypeConcessionCotiere) flagcotiere++;
                if(qcat.getTypeconcessionConcernee() instanceof qTypeConcessionHautiriere) flaghautiriere++;
            }
            // pour la journal de peche
            if(flagcotiere>0)    documentCree = new qMarree(enumTypeDoc.Journal_Peche, dateDepart, dateRetour, seqActive, null,
                    qconcess, qconcess.getCategoriesRessources(), enumJP.Cotier, null, null);
            if(flaghautiriere>0) documentCree = new qMarree(enumTypeDoc.Journal_Peche, dateDepart, dateRetour, seqActive, null,
                    qconcess, qconcess.getCategoriesRessources(), enumJP.Hautirere, null, null);

            List<qPageMarree> lstPgsMar=new ArrayList<qPageMarree>();
            // preparer les pages
            List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
            for(qPageCarnet  qpc:pagesConcernees) {
                lstPgsMar.add((qPageMarree) qpc);
            }
            Iterator<qPageMarree> qmarpages=lstPgsMar.iterator();
            Date dateJourCourant=  documentCree.getDepart();
            List<qCapture> capturesLine=new ArrayList<qCapture>();

            List<qJourMere>  joursMar=new ArrayList<qJourMere>();
            while(qmarpages.hasNext())
            {
                qPageMarree currp=qmarpages.next();
                currp.setEtatPage(enumEtatPage.DRAFT);
                currp.setQmarree((qMarree) documentCree);

                // pqrcourir les ligne de page
                for(int k=0;k<currp.getNbrLigne();k++) {

                    qJourMere jourMar=new qJourMere(dateJourCourant,qnav,null,0,0,0,currp);
                    // traitement des captures

                    for(qEspeceTypee esptypee:currentModel.getEspecestypees())
                    {
                        qCapture qcapture=new qCapture( documentCree,esptypee,0,jourMar,null);
                        qcapture.setQdoc( documentCree);
                        qcapture.setJourMere(jourMar);
                        qcapture.setDatedepart( documentCree.getDepart());
                        qcapture.setNummimm( documentCree.getNumImm());
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
                    cal.add( Calendar.DATE, 1 );
                    dateJourCourant=cal.getTime();
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
        return documentCree;
    }
}

