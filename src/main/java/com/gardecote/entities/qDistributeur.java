package com.gardecote.entities;

import com.gardecote.data.repository.jpa.*;
import org.springframework.context.ApplicationContext;

import javax.persistence.Id;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dell on 31/10/2016.
 */
public class qDistributeur implements Serializable {

    public qDistributeur() {
    }

    public qCarnet distribuer(qCarnet qcarnet) {
       List<qPageCarnet>  pgs=new ArrayList<qPageCarnet>();
        if (qcarnet.getTypeDoc().equals(enumTypeDoc.Journal_Peche)){
            qcarnet.setNbrLigneParPage(10);

            for(int i=0;i<qcarnet.getNbrPages();i++) {
                qPageCarnet  qp= new qPageMarree(qcarnet.getPrefixNumerotation().toString()+Long.toString(qcarnet.getNumeroDebutPage()+i),
                        qcarnet.getNumeroDebutPage()+i,enumEtatPage.LIBRE,qcarnet,null,null );
                //   qp.setQcarnet(this);

                pgs.add(qp);
            }
        }
        if (qcarnet.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {
            qcarnet.setNbrLigneParPage(9);

            for(int i=0;i<qcarnet.getNbrPages();i++) {
                qPageCarnet  qp= new qPageDebarquement(qcarnet.getPrefixNumerotation().toString()+Long.toString(qcarnet.getNumeroDebutPage()+i),
                        qcarnet.getNumeroDebutPage()+i,enumEtatPage.LIBRE,qcarnet,null,null);
                pgs.add(qp);
            }
        }
        if(qcarnet.getTypeDoc().equals(enumTypeDoc.Fiche_Traitement)) {
             qcarnet.setNbrLigneParPage(8);
            for(int i=0;i<qcarnet.getNbrPages();i++) {
                qPageCarnet  qp= new qPageTraitement(qcarnet.getPrefixNumerotation().toString()+Long.toString(qcarnet.getNumeroDebutPage()+i),qcarnet.getNumeroDebutPage()+i,enumEtatPage.LIBRE,qcarnet,null,null,null);


                pgs.add(qp);
            }
        }
        qcarnet.setPages(pgs);
return qcarnet;
}
   public qCarnet distribuer(qCarnet qcarnet,qConcession qconcession,qRegistreNavire qnavire, qUsine qusine){
        qcarnet.setQnavire(qnavire);
        qcarnet.setQusine(qusine);
       qcarnet.setQconcession(qconcession);
       return qcarnet;
    }
    public qDoc creerDoc(Date dateDepart,Date dateRetour,qSeq seqActive,List<qEnginPecheDeb> engisDeb, ApplicationContext ctx) {

        qCarnetRepository qcrnrepo = ctx.getBean(qCarnetRepository.class);
        qDebarquementRepository qdebrepo = ctx.getBean(qDebarquementRepository.class);
        qDocRepository qdocrepo = ctx.getBean(qDocRepository.class);
        qModelJPRepository qmodelrepo=ctx.getBean(qModelJPRepository.class);
        qJourDebRepository qjourrepository=ctx.getBean(qJourDebRepository.class);
        qCapturesRepository qcapturepository=ctx.getBean(qCapturesRepository.class);


        List<qCapture> capturesTotal=new ArrayList<qCapture>();
        boolean flagLiberte = false, flagIdentiteCarnet = false, flg = true;
        Integer fi = 0, fl = 0;
        qDoc qdoc=null;

        String numeroDebut = seqActive.getDebut(), numeroFin = seqActive.getFin(), debutPrefix = null, finPrefix = null;
        String debutNumber = "", finNumber = "";
        Integer indexDebut = 0, indexFin = -1;
        Integer indexlastpos;
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



        // List<qPageCarnet> pgs = qcrnrepo.retPagesDemandees(carnetFin,debutNumber ,finNumber );
        //  qCarnet carnetFin = qcrnrepo.retCarnet(numeroFin);
        qCarnet carnetDebut = qcrnrepo.retCarnet(numeroDebut);
        qCarnet carnetFin = qcrnrepo.retCarnet(numeroFin);

        if (debutPrefix.equals(finPrefix) && indexDebut == indexFin && isnumericDebut == true && isnumericFin == true) {
            Long finNumberL = Long.valueOf(finNumber), debutNumberL = Long.valueOf(debutNumber);


            List<qPageCarnet> pagesConcernees = qcrnrepo.retPages(carnetDebut, debutNumberL, finNumberL);
            if (carnetDebut.equals(carnetFin)) {
                for (qPageCarnet qp : pagesConcernees) {
                    if (!qp.getQcarnet().equals(carnetDebut)) {
                        fi++;
                    }
                    if (qp.getEtatPage().equals(enumEtatPage.VALIDEE)) fl++;
                }
            }

        if (fi > 0 || fl > 0) flg = false;
        else flg = true;
        if (flg == true) // is the same carnet
        {


            qSeqPK qseqpk = new qSeqPK(numeroDebut, numeroFin);
            qSeq qseq = new qSeq(numeroDebut, numeroFin, null);
            //  recuperer la navire et la concession concernee
            qRegistreNavire qnav = carnetDebut.getQnavire();
            qConcession qconcess = carnetDebut.getQconcession();
            // rechecher si le document est encours de saisie et le retourner
            qdoc = qseq.getQdoc();
            if (qdoc == null) {

                // lire le model associé à prefix

                qModelJP currentModel=qmodelrepo.findOne(enumPrefix.valueOf(debutPrefix));

                // creer un nouvel
            if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {
                    if (carnetDebut.getPrefixNumerotation().equals(enumPrefix.PC)) {
                        // la peche cotiere


                        qdoc = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,
                                qconcess, engisDeb, qconcess.getCategoriesRessources(), enumTypeDebarquement.Cotier, null);


                        List<qPageDebarquement> lstPgsDeb=new ArrayList<qPageDebarquement>();
                        // preparer les pages

                        for(qPageCarnet  qpc:pagesConcernees) {
                           lstPgsDeb.add((qPageDebarquement)qpc);
                             }
                        Iterator<qPageDebarquement> qdebpages=lstPgsDeb.iterator();
                        Date dateJourCourant= qdoc.getDepart();
                        List<qCapture> capturesLine=new ArrayList<qCapture>();

                        List<qJourDeb>  joursDeb=new ArrayList<qJourDeb>();
                        while(qdebpages.hasNext())
                        {
                            qPageDebarquement currp=qdebpages.next();
                            currp.setEtatPage(enumEtatPage.DRAFT);
                            currp.setQdebarquement((qDebarquement) qdoc);

                            // pqrcourir les ligne de page
                            for(int k=0;k<currp.getNbrLigne();k++) {

                                qJourDeb jourDeb=new qJourDeb(dateJourCourant,qnav,null,currp);
                                // traitement des captures

                                for(qEspeceTypee esptypee:currentModel.getEspecestypees())
                                {
                                    qCapture qcapture=new qCapture(qdoc,esptypee,0,null,jourDeb);
                                    qcapture.setQdoc(qdoc);
                                    qcapture.setJourDeb(jourDeb);
                                    qcapture.setDatedepart(qdoc.getDepart());
                                    qcapture.setNummimm(qdoc.getNumImm());
                                    qcapture.setDateJour(dateJourCourant);
                                    qcapture.setIdespece(esptypee.getQespeceId());
                                    qcapture.setEsptype(esptypee.getEnumesptype());
                                    capturesLine.add(qcapture);
                                    capturesTotal.add(qcapture);

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
                        qdoc.setQseq(seqActive);
                        ((qDebarquement)qdoc).setPages(lstPgsDeb);
                     //   ((qDebarquement)qdoc).setCaptures(capturesTotal);
                    }
                    if (carnetDebut.getPrefixNumerotation().equals(enumPrefix.PA)) {
                        // la peche artisanal
                        qdoc = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,
                                qconcess, engisDeb, qconcess.getCategoriesRessources(), enumTypeDebarquement.Artisanal, null);


                    }

                }
                if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Journal_Peche)) {
                    // pour la journal de peche
                    qdoc = new qMarree(enumTypeDoc.Journal_Peche, dateDepart, dateRetour, seqActive, null,
                            qconcess, qconcess.getCategoriesRessources(), enumJP.Hautirere, null, null);

                }
                if (carnetDebut.getTypeDoc().equals(enumTypeDoc.Fiche_Traitement)) {

                }
                // fin creation de nouveaux documents
            }



        }

    }

        qdocrepo.save(qdoc);
        qcapturepository.save(capturesTotal);
        return qdoc;
    }
}


