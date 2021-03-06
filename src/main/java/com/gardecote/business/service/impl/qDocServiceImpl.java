package com.gardecote.business.service.impl;

import com.gardecote.business.service.*;
import com.gardecote.data.repository.jpa.*;
import com.gardecote.entities.*;

import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;

import com.gardecote.web.*;
import net.sf.cglib.beans.BeanGenerator;

import org.hibernate.Hibernate;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.util.IdentityArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Expression;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by Dell on 08/11/2016.
 */
@Service
@Transactional
public class qDocServiceImpl implements qDocService {
    @Autowired
    private qEspeceDynamicRepository especeDynamicRepo;
    @Autowired
    private qTaskBarService progressService;
    @Autowired
    private qRegistreNavireService navService;

    @Autowired
    private qPrefixRepository prefRepo;
    @Autowired
    private qEspeceDynamicService dynService;
    @Autowired
    private qMarreeAnnexeRepository qmarreeannexeRepository;
    @PersistenceContext
   // @PersistenceUnit(unitName="default")
    private EntityManager em;
    @Autowired
    private qDocRepository qdocRepository;
    @Autowired
    private qCategRessourceRepository qcqtRepository;
    @Autowired
    private qEspeceTypeeRepository esptypeeRepository;
    @Autowired
    private qSeqRepository qseqRepository;
    @Autowired
    private qCarnetService qcarnetService;
    @Autowired
    private qCarnetRepository qcarnetRepository;
    @Autowired
    private qModelJPRepository qmodelRepository;
    @Autowired
    private qModelJPService qmodelService;
    @Autowired
    private qPageCarnetRepository qpagecarnetRepository;
    @Autowired
    private qJourMereRepository qjourmereRepository;
    @Autowired
    private qJourMereAnnexeRepository qjourmereannexeRepository;
    @Autowired
    private qJourDebRepository qjourdebRepository;
    @Autowired
    private qRegistreNavireRepository navRepository;
    @Autowired
    private qOpTraitementRepository qopTraitementRepository;
    @Autowired
    private qNationRepository qnationRepository;
    @Autowired
    private qEspeceRepository espRepository;
    @Autowired
    private qTypeConcessionRepository typeconcessionRepository;
    @Autowired
    private qUsineRepository qusineRepository;
    @Autowired
    private attributionValidator attrValidator;
    @Autowired
    private qAccordPecheRepository accordRepository;
    @Autowired
    private qZoneRepository zoneRepo;
    @Autowired
    private qConcessionService concessionService;

    @Autowired
    private qConsignataireService consignataireService;
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

        qDoc tt = null;
        if (authprov != null) {
            tt = em.find(qDoc.class, authprov.getqDocPK());
            //      System.out.println(tt.toString());
            Integer i = 0;


            if (tt != null) {

                em.merge(authprov);
            } else {

                em.persist(authprov);
            }


            return qdocRepository.save(authprov);
        }
        else         return null;
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
       qDoc ff=qdocRepository.findOne(idauthpr);

        List<qJourMere> jms=new ArrayList<qJourMere>();
        List<qJourMereAnnexe> jmsAX=new ArrayList<qJourMereAnnexe>();
        List<qJourDeb> jds=new ArrayList<qJourDeb>();
        List<qOpTraitement> jts=new ArrayList<qOpTraitement>();
        List<qEspeceDynamic> espDynLiees=espDynLiees=new ArrayList<qEspeceDynamic>();
        if(ff instanceof  qMarree) {
            if(((qMarree) ff).getPages().size()!=0) {
                for (Iterator<qPageMarree> iter = ((qMarree) ff).getPages().iterator(); iter.hasNext(); ) {

                    qPageMarree currentp = iter.next();

                 //   espDynLiees.addAll(currentp.getEspecesDyn());
                    for (Iterator<qJourMere> iter1 = currentp.getListJours().iterator(); iter1.hasNext(); ) {
                        qJourMere currj = iter1.next();
                        currj.setPageMarree(null);
                        jms.add(currj);
                    }
                    for(Iterator<qEspeceDynamic> iter1 = currentp.getEspecesDyn().iterator(); iter1.hasNext();){
                        qEspeceDynamic currespd = iter1.next();
                        currespd.setPagemaree(null);
                        currespd.setMasterEspeceTypee(null);
                        currespd.setEspeceChoisie(null);
                        espDynLiees.add(currespd);
                    }

                    currentp.setListJours(null);
                    currentp.setQmarree(null);
                    currentp.setEspecesDyn(null);
                    qpagecarnetRepository.save(currentp);
                }
                ((qMarree) ff).setPages(null);
                qdocRepository.save(ff);
                for (qJourMere jk : jms) {
                    //qjourmereRepository.save(jk);
                    qjourmereRepository.delete(jk);
                }
                for (qEspeceDynamic espd : espDynLiees) {
                    //qjourmereRepository.save(jk);
                    especeDynamicRepo.delete(espd);
                }
                qMarreeAnnexe marrAnnexe = ((qMarree) ff).getMarreeAnnexe();
                if (marrAnnexe != null) {
                    ((qMarree) ff).setMarreeAnnexe(null);
                    marrAnnexe.setMarreePrincipal(null);
//                qdocRepository.save(ff);

                    qmarreeannexeRepository.save(marrAnnexe);
                    for (Iterator<qPageAnnexe> iter2 = marrAnnexe.getPages().iterator(); iter2.hasNext(); ) {
                        qPageAnnexe currPX = iter2.next();
                        for (Iterator<qJourMereAnnexe> iter1 = currPX.getListJours().iterator(); iter1.hasNext(); ) {
                            qJourMereAnnexe currjAX = iter1.next();
                            currjAX.setPageMarree(null);
                            jmsAX.add(currjAX);
                        }
                        currPX.setListJours(null);
                        currPX.setQmarreeAnexe(null);
                        qpagecarnetRepository.save(currPX);
                    }
                    marrAnnexe.setPages(null);
                    qmarreeannexeRepository.save(marrAnnexe);
                    for (qJourMereAnnexe jk : jmsAX) {
                        qjourmereannexeRepository.save(jk);
                        qjourmereannexeRepository.delete(jk);
                    }
                    //      qMarreeAnnexe ffx=qmarreeannexeRepository.findOne(marrAnnexe.getqDocPK());
                    //  qmarreeannexeRepository.save(marrAnnexe);
                    qmarreeannexeRepository.delete(marrAnnexe);

                }
                // qmarreeannexeRepository.delete(ff.getqDocPK());
                //   qDoc fff=qdocRepository.findOne(ff.getqDocPK());
                //   qdocRepository.save(fff);
//           qdocRepository.save(ff);
            }
            else qdocRepository.delete(ff);
            }

        if(ff instanceof  qDebarquement) {
            if(((qDebarquement) ff).getPages().size()!=0) {
                for (Iterator<qPageDebarquement> iter = ((qDebarquement) ff).getPages().iterator(); iter.hasNext(); ) {
                    qPageDebarquement currentp = iter.next();
                    for (Iterator<qJourDeb> iter1 = currentp.getListJours().iterator(); iter1.hasNext(); ) {
                        qJourDeb currj = iter1.next();
                        currj.setPagesDeb(null);
                        jds.add(currj);
                    }
                    currentp.setListJours(null);
                    currentp.setQdebarquement(null);
                    qpagecarnetRepository.save(currentp);
                }
                ((qDebarquement) ff).setPages(null);
                //       qdocRepository.save(ff);
                for (qJourDeb jk : jds) {
                    //qjourmereRepository.save(jk);
                    qjourdebRepository.delete(jk);
                }


                // qmarreeannexeRepository.delete(ff.getqDocPK());
                // qDoc fff=qdocRepository.findOne(ff.getqDocPK());
                //   qdocRepository.save(fff);
//          qdocRepository.delete(ff);
            }
            else  qdocRepository.delete(ff);
        }

        if(ff instanceof  qTraitement) {
            if (((qTraitement) ff).getPagesTraitement().size() != 0) {
                for (Iterator<qPageTraitement> iter = ((qTraitement) ff).getPagesTraitement().iterator(); iter.hasNext(); ) {
                    qPageTraitement currentp = iter.next();
                    for (Iterator<qOpTraitement> iter1 = currentp.getOpTraitements().iterator(); iter1.hasNext(); ) {
                        qOpTraitement currj = iter1.next();
                        currj.setPageTraitement(null);
                        jts.add(currj);
                    }
                    currentp.setOpTraitements(null);
                    currentp.setQtraitement(null);
                    qpagecarnetRepository.save(currentp);
                }
                ((qTraitement) ff).setPagesTraitement(null);
                //       qdocRepository.save(ff);
                for (qOpTraitement jk : jts) {
                    //qjourmereRepository.save(jk);
                    qopTraitementRepository.delete(jk);
                }


                // qmarreeannexeRepository.delete(ff.getqDocPK());
                qDoc fff = qdocRepository.findOne(ff.getqDocPK());
                //   qdocRepository.save(fff);
                qdocRepository.delete(ff.getqDocPK());

            }
            else qdocRepository.delete(ff.getqDocPK());
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
    public boolean checkPrefix(qPrefix deletedPrefix) {
        boolean result = true;
       List<qPageCarnet> pgs=qdocRepository.checkPrefix(deletedPrefix.getPrefix(),deletedPrefix.getTypeDoc().toString());
       if(pgs.size()>0)  return true;
           else          return false;
    }



    @Override
    public qDebarquement creerDebarquementByImport(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc,List<Boolean> enginsDebs,List<Boolean> categDebs,Map<String,Map<Integer,List<Float>>> quantities) {
        // qSeq seqActive=qseqRepository.findOne(seqActive1.getSeqPK());
        List<qEspeceDynamic> especesDyn=null;
        qSeq seqActive=seqActive1;
        String numeroDebut = seqActive.getDebut(),numeroFin = seqActive.getFin(), debutPrefix = null,finPrefix = null;
        qDebarquement      documentCree=null;
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroDebut,typeDoc));
        qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroFin,typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qCarnet carnetFin = finp.getQcarnet();
        debutPrefix=carnetDebut.getPrefixNumerotation().toString();

        qNavireLegale qnav = carnetDebut.getQnavire();
        qConcession qconcess =carnetDebut.getQconcession();//qnav.getQlicencebatlastdernier().getQconcession(); // concession du dernier lic
        //carnetDebut.getQconcession();
        Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();
        List<qEnginPecheDebar> choixEnginsDeb=new ArrayList<qEnginPecheDebar>();
        qEnginPecheDebar engdeb1=new qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Filet_maillant,0,enginsDebs.get(0));
        qEnginPecheDebar engdeb2=new  qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Filet_Enc_ST,0,enginsDebs.get(1));
        qEnginPecheDebar engdeb3=new  qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Palangre,0,enginsDebs.get(2));
        qEnginPecheDebar engdeb4=new  qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Filet_tremail,0,enginsDebs.get(3));

        qEnginPecheDebar engdeb6=new qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Casier,0,enginsDebs.get(4));
        qEnginPecheDebar engdeb7=new qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Ligne,0,enginsDebs.get(5));
        qEnginPecheDebar engdeb8=new qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Pots,0,enginsDebs.get(6));
        qEnginPecheDebar engdeb9=new qEnginPecheDebar(qnav.getNumimm(),dateDepart,enumEngin.Indefini,enumEnginDeb.Turlutte,0,enginsDebs.get(7));
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

        qCategDeb c1=new qCategDeb(qnav.getNumimm(),dateDepart,cr1,categDebs.get(0));
        qCategDeb c2=new qCategDeb(qnav.getNumimm(),dateDepart,cr2,categDebs.get(1));
        qCategDeb c3=new qCategDeb(qnav.getNumimm(),dateDepart,cr3,categDebs.get(2));
        qCategDeb c4=new qCategDeb(qnav.getNumimm(),dateDepart,cr4,categDebs.get(3));
        qCategDeb c5=new qCategDeb(qnav.getNumimm(),dateDepart,cr5,categDebs.get(4));
     //   c1.setFlag(categDebs.get(0));c1.setFlag(categDebs.get(1));c1.setFlag(categDebs.get(2));c1.setFlag(categDebs.get(3));
      //  c1.setFlag(categDebs.get(4));
     //   categsArt.add(c1);categsArt.add(c2);categsArt.add(c3);categsArt.add(c4);categsArt.add(c5);
        List<qCategDeb> categsCot=new ArrayList<qCategDeb>();
        qCategRessource cr11=qcqtRepository.findOne(6);
        qCategRessource cr21=qcqtRepository.findOne(7);
        qCategRessource cr31=qcqtRepository.findOne(8);
        qCategRessource cr41=qcqtRepository.findOne(9);
        qCategRessource cr51=qcqtRepository.findOne(10);
        qCategRessource cr61=qcqtRepository.findOne(11);
        qCategRessource cr71=qcqtRepository.findOne(12);

        qCategDeb c11=new qCategDeb(qnav.getNumimm(),dateDepart,cr11,categDebs.get(0));
        qCategDeb c21=new qCategDeb(qnav.getNumimm(),dateDepart,cr21,categDebs.get(1));
        qCategDeb c31=new qCategDeb(qnav.getNumimm(),dateDepart,cr31,categDebs.get(2));
        qCategDeb c41=new qCategDeb(qnav.getNumimm(),dateDepart,cr41,categDebs.get(3));

       // categsCot.add(c11);categsCot.add(c21);categsCot.add(c31);categsCot.add(c41);
        qCategDeb xx;
        //   categsArt.add(c1);categsArt.add(c2);categsArt.add(c3);categsArt.add(c4);categsArt.add(c5);
        //     typesConceArt.add(tc1);typesConceArt.add(tc2);typesConceArt.add(tc3);typesConceArt.add(tc4);typesConceArt.add(tc5);
        int l=0;
        List<qTypeConcession> typesConceArt=new ArrayList<qTypeConcession>();
        List<qTypeConcession> typesConceCot=new ArrayList<qTypeConcession>();
        for(qCategRessource cress:typeconcessionRepository.returnCategoriesArtisanale()) {
            xx=new qCategDeb(qnav.getNumimm(),dateDepart,cress,categDebs.get(l));
            categsArt.add(xx);
            if(categDebs.get(l).equals(true))
                typesConceArt.add(typeconcessionRepository.findOne(cress.getIdtypeConcession()));
            l++;
        }
        for(qCategRessource cress:typeconcessionRepository.returnCategoriesCotNonP()) {
            xx=new qCategDeb(qnav.getNumimm(),dateDepart,cress,categDebs.get(l));
            categsCot.add(xx);
            if(categDebs.get(l).equals(true))
                typesConceCot.add(typeconcessionRepository.findOne(cress.getIdtypeConcession()));
        }
        // typesConceArt.addAll(typeconcessionRepository.returnTypesConcessionArtisanale());

        qPrefixPK pref=new qPrefixPK(debutPrefix,typeDoc);

        qPrefix currprefixx=prefRepo.findOne(pref);
        qModelJP currentModel=qmodelRepository.findOne(pref);

        if (carnetDebut.getPrefixNumerotation().equals("PC")) {
            // la peche cotiere
            documentCree = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,null,
                    choixEnginsDeb,  enumTypeDebarquement.Cotier, qconcess,categsCot, null,0,qnav.getModePeche());
            documentCree.setSegPeche(debutPrefix);
            documentCree.setQprefix(currprefixx);
            documentCree.setTypesConcession(typesConceArt);
            documentCree.setNomNavire(qnav.getNomnav());

        }

        if (carnetDebut.getPrefixNumerotation().equals("PA")) {
            // la peche artisanal

            documentCree = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,null,
                    choixEnginsDeb, enumTypeDebarquement.Artisanal,qconcess, categsArt,null,0,qnav.getModePeche());

            documentCree.setQprefix(currprefixx);
            documentCree.setTypesConcession(typesConceArt);
            documentCree.setSegPeche(debutPrefix);
            documentCree.setNomNavire(qnav.getNomnav());


        }

        //  List<qPageDebarquement> lstPgsDeb =null;
        List<qPageDebarquement> lstPgsDeb = new ArrayList<qPageDebarquement>();
        // preparer les pages
        List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
        for (qPageCarnet qpc : pagesConcernees) {
            lstPgsDeb.add((qPageDebarquement) qpc);
        }
        Iterator<qPageDebarquement> qdebpages = lstPgsDeb.iterator();
        Date dateJourCourant = documentCree.getDepart();
        List<qJourDeb> joursDeb =null;
        Map<Integer,List<Float>> currentPageAQuanties=null;
        List<Float>   currentLineQuantities=null;
        Integer i=0;

        while (qdebpages.hasNext()) {
            qPageDebarquement currp = qdebpages.next();
            currentPageAQuanties=quantities.get(currp.getNumeroPage());
            currp.setEtatPage(enumEtatPage.DRAFT);
            currp.setQdebarquement((qDebarquement) documentCree);
            for(qEspeceDynamic dn:qmodelService.getEspecestypeesDyn(currp.getNumeroPage(),documentCree.getEnumtypedoc(),currentModel)) {
                dn.setPagedebarquement(currp);
            } //,qmodelService.getEspecestypeesDyn(qnav.getNumimm(),dateDepart,currentModel)
            joursDeb = new ArrayList<qJourDeb>();
            // pqrcourir les ligne de page
            for (int k = 0; k < currp.getNbrLigne(); k++) {
                if (dateJourCourant.before(documentCree.getRetour()) || dateJourCourant.equals(documentCree.getRetour())) {
                    currentLineQuantities= currentPageAQuanties.get(k);
                    qJourDeb jourDeb = new qJourDeb(k,currp.getNumeroPage(),dateJourCourant, qnav, null, currp,0);
                    List<qCapture> capturesLine = new ArrayList<qCapture>();
                    // traitement des captures
                    i=0;
                    for (qEspeceTypee esptypee : currentModel.getEspecestypees()) {
                        qCapture qcapture = new qCapture(k,currp.getNumeroPage(),documentCree, esptypee, currentLineQuantities.get(i),esptypee.getNumOrdre(), null, jourDeb);
                        qcapture.setQdoc(documentCree);
                        qcapture.setJourDeb(jourDeb);
                        qcapture.setDatedepart(documentCree.getDepart());
                        qcapture.setNummimm(documentCree.getNumImm());
                        qcapture.setDateJour(dateJourCourant);
                        qcapture.setIdespece(esptypee.getQespeceId());
                        qcapture.setEsptype(esptypee.getEnumesptype());
                        capturesLine.add(qcapture);
                        //  capturesTotal.add(qcapture);
                        i++;
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
        //  qdocRepository.save(documentCree);
        // ((qDebarquement)qdoc).setCaptures(capturesTotal);
        return documentCree;
    }

    @Override
    public qTraitement creerTraitementByImport(Date dateDepart, Date dateRetour, qSeq seqActive1, enumTypeDoc typeDoc, List<Boolean> enginsMarree, enumJP typeJP, Map<String, Map<Integer, List<Double>>> quantities) {
        return null;
    }

    @Override
    public void creerMarreesByImportDup(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc,List<Boolean> enginsMarree,enumJP selectedTypeJP,Map<String,Map<String,List<Float>>> quantities,String typeM,Set<String> nuemeros) {
        //   qSeq seqActive=qseqRepository.findOne(seqActive1.getSeqPK());
        qSeq seqActive = seqActive1;
        List<String>   lstStr=new ArrayList<String>();
        Set<String> Refused=new HashSet<String>();
        System.out.print("debut!fin:" + seqActive1.getDebut() + "" + seqActive1.getFin());
        String numeroDebut = seqActive.getDebut(), numeroFin = seqActive.getFin(), debutPrefix = null, finPrefix = null;
        qMarree documentCree = null;
        System.out.println("ndebut" + numeroDebut);
        System.out.println("nfin" + numeroFin);

        System.out.println("flag lehbib");
        qPageCarnet debutp = qpagecarnetRepository.findOne(new qPageCarnetPK(numeroDebut, typeDoc));
        qPageCarnet finp = qpagecarnetRepository.findOne(new qPageCarnetPK(numeroFin, typeDoc));
        Long finNumberL = finp.getNumeroOrdrePage(), debutNumberL = debutp.getNumeroOrdrePage();
        qCarnet carnetDebut = debutp.getQcarnet();
        qCarnet carnetFin = finp.getQcarnet();
        debutPrefix = carnetDebut.getPrefixNumerotation().toString();
        qPrefixPK pk = new qPrefixPK(debutPrefix, typeDoc);
        qPrefix currprefixx = prefRepo.findOne(pk);

        qNavireLegale qnav = carnetDebut.getQnavire();
        qConcession qconcess;
        //qnav.getQlicencebatlastdernier().getQconcession(); // concession du dernier lic
        //carnetDebut.getQconcession();
        List<qEnginPecheMar> choixEnginsMar = new ArrayList<qEnginPecheMar>();
        qEnginPecheMar engmar1 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Chalut, enumEnginDeb.Indefini, 0, enginsMarree.get(0));
        qEnginPecheMar engmar2 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Casier, enumEnginDeb.Indefini, 0, enginsMarree.get(1));
        qEnginPecheMar engmar3 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Nasses, enumEnginDeb.Indefini, 0, enginsMarree.get(2));
        qEnginPecheMar engmar4 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Pots, enumEnginDeb.Indefini, 0, enginsMarree.get(3));

        qEnginPecheMar engmar6 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Filet_tremail, enumEnginDeb.Indefini, 0, enginsMarree.get(4));
        qEnginPecheMar engmar7 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Turlutte, enumEnginDeb.Indefini, 0, enginsMarree.get(5));

        choixEnginsMar.add(engmar1);
        choixEnginsMar.add(engmar2);
        choixEnginsMar.add(engmar3);
        choixEnginsMar.add(engmar4);
        choixEnginsMar.add(engmar6);
        choixEnginsMar.add(engmar7);
        // qPrefixPK pref = new qPrefixPK(debutPrefix, typeDoc);
        // cat du dernier licence
        qModelJP currentModel = qmodelRepository.findOne(pk);
        //   qPrefix curpref = prefRepo.findOne(pref);
        qCategRessource h = null;
        if (typeM.equals("H"))
            h = qcqtRepository.findCategH(currprefixx);
        if (typeM.equals("C"))
            h = qcqtRepository.findCategC(currprefixx);
        System.out.println("type M : "+typeM);
        System.out.println("type concession : "+currprefixx.getPrefix());
        qTypeConcession typeConcessionActive = typeconcessionRepository.findOne(h.getIdtypeConcession());

        List<qCategRessource> ours = new ArrayList<qCategRessource>();
        Integer flagcotiere = 0, flaghautiriere = 0;
        if (carnetDebut.getQconcession() != null) {
            qconcess = carnetDebut.getQconcession();
            ours.addAll(carnetDebut.getQconcession().getCategoriesRessources());
        } else qconcess = null;
        System.out.println("numimm:" + qnav.getNumimm());
        System.out.println("type concession : "+h);

        System.out.println("depart:" + dateDepart);
        for(String str:nuemeros) {
            //  String pc=qpagecarnetRepository.findOne(new qPageCarnetPK(str,enumTypeDoc.Journal_Peche)).getNumeroPage(); // IL FAUT VRTIFIRT LE DOCUMENTS ET PAS LE CATNET
            //   qPageMarree  pm=qcarnetRepository.check(pc.getNumeroPage());
            if(qpagecarnetRepository.retDoc(str)>0 ) {lstStr.add(seqActive.getDebut());Refused.add(seqActive.getDebut());}
        }

        System.out.println("list str" + lstStr);
        if(lstStr.size()==0) {
            documentCree = (qMarree) qdocRepository.findOne(new qDocPK(qnav.getNumimm(), dateDepart));
            if (documentCree != null) {
                delete(new qDocPK(qnav.getNumimm(),dateDepart));
        }
            documentCree = new qMarree(enumTypeDoc.Journal_Peche, dateDepart, dateRetour, seqActive, qnav, null,
                    qconcess, selectedTypeJP, choixEnginsMar, null, 0, 0, 0, qnav.getModePeche(), typeConcessionActive);
            documentCree.setTypeJP(selectedTypeJP);
            documentCree.setSegPeche(debutPrefix);
            documentCree.setNomNavire(qnav.getNomnav());
            documentCree.setNumImm(qnav.getNumimm());
            documentCree.setQnavire(qnav);
            documentCree.setQprefix(currprefixx);
            documentCree.setTypePeche(selectedTypeJP);
            System.out.println(debutPrefix);
            // pour la journal de peche
            System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
            System.out.println(currentModel.getEspecestypees());
            System.out.println(currentModel.getEspecestypees().size());

            System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
            List<qPageMarree> lstPgsMar = new ArrayList<qPageMarree>();
            // preparer les pages
            System.out.println("debutc/debut/fin : " + carnetDebut.getNumeroDebutPage() + "/" + debutNumberL + "/" + finNumberL);
            //  Set<qPageCarnet> setPC=new HashSet<qPageCarnet>();
            System.out.println("dockpk : " + documentCree.getqDocPK());
            List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
            System.out.println("page concernee size : " + pagesConcernees.size());
            for (qPageCarnet qpc : pagesConcernees) {
                lstPgsMar.add((qPageMarree) qpc);
                System.out.println("numero de page : " + qpc.getNumeroPage());
            }
            Iterator<qPageMarree> qmarpages = lstPgsMar.iterator();
            Date dateJourCourant = documentCree.getDepart();
            List<qJourMere> joursMar = null;
            Map<String, List<Float>> currentPageAQuanties = null;
            List<Float> currentLineQuantities = null;
            List<Float> currentLineQuantitiesZero = new ArrayList<Float>();
            for (int i = 0; i < 16; i++) currentLineQuantitiesZero.add(0F);
            Integer i = 0;
            float ttotd = 0;
            while (qmarpages.hasNext()) {
                float ttotp = 0;
                List<qEspeceDynamic> dynEsps = new ArrayList<qEspeceDynamic>();
                qPageMarree currp = qmarpages.next();
                currp.setEtatPage(enumEtatPage.DRAFT);
                currp.setQmarree((qMarree) documentCree);

                System.out.println("quantities : from inside"+quantities);
                currentPageAQuanties = quantities.get(currp.getNumeroPage());
                System.out.println("quantities of page : from inside"+currentPageAQuanties);
                //dynEsps = qmodelService.getEspecestypeesDyn(currp.getNumeroPage(), documentCree.getEnumtypedoc(), currentModel);
               //  List<qEspeceDynamic> dynEsps=new ArrayList<qEspeceDynamic>();
                //,qmodelService.getEspecestypeesDyn(qnav.getNumimm(),dateDepart,currentModel)

                qEspeceTypee esptypee13=esptypeeRepository.findOne(new qEspeceTypeePK("-1",enumEspType.ACCESSOIR,13,"DEM",enumTypeEspTypee.DYNAMIC));
                esptypee13.setQespece(espRepository.findOne("Rampo"));
                    qEspeceDynamic h13=especeDynamicRepo.findOne(new qEspeceDynamicPK(currp.getNumeroPage(),enumTypeDoc.Journal_Peche,esptypee13.getNumOrdre()));
                    if(h13==null)  { h13=new qEspeceDynamic(currp.getNumeroPage(),enumTypeDoc.Journal_Peche,esptypee13.getNumOrdre(),esptypee13,espRepository.findOne("Rampo"));}
                    h13.setEspeceChoisie(espRepository.findOne("Rampo"));h13.setMasterEspeceTypee(esptypee13);
                    h13.setEspeceChoisie(espRepository.findOne("Rampo"));h13.setPagemaree(currp);
                    dynEsps.add(h13);

                qEspeceTypee esptypee14=esptypeeRepository.findOne(new qEspeceTypeePK("-1",enumEspType.ACCESSOIR,14,"DEM",enumTypeEspTypee.DYNAMIC));esptypee14.setQespece(espRepository.findOne("Huevo"));
                qEspeceDynamic  h14=especeDynamicRepo.findOne(new qEspeceDynamicPK(currp.getNumeroPage(),enumTypeDoc.Journal_Peche,esptypee14.getNumOrdre()));
                    if(h14==null)  { h14=new qEspeceDynamic(currp.getNumeroPage(),enumTypeDoc.Journal_Peche,esptypee14.getNumOrdre(),esptypee14,espRepository.findOne("Huevo"));}
                    h14.setEspeceChoisie(espRepository.findOne("Huevo"));h14.setMasterEspeceTypee(esptypee14);h14.setPagemaree(currp);
                    dynEsps.add(h14);
                    currp.setEspecesDyn(dynEsps);

                // parcourir les ligne de page
                joursMar = new ArrayList<qJourMere>();
                for (int k = 0; k < currp.getNbrLigne(); k++) {
                    float ttot = 0;
                    if (dateJourCourant.before(documentCree.getRetour()) || dateJourCourant.equals(documentCree.getRetour())) {
                        qJourMere jourMar = new qJourMere(k, currp.getNumeroPage(), dateJourCourant, qnav, null, 0, 0, 0, currp);
                        // traitement des captures
                        currentLineQuantities = currentPageAQuanties.get(Integer.toString(k));
                        System.out.println("quantities of line : "+k+ " : "+currentLineQuantities);
                        if (currentLineQuantities == null) {
                            currentLineQuantities = currentLineQuantitiesZero;
                        }
                        List<qCapture> capturesLine = new ArrayList<qCapture>();
                        i = 0;

                        for (qEspeceTypee esptypee : currentModel.getEspecestypees()) {
                            System.out.println("curr k :" + esptypee.getNumOrdre());
                            System.out.println("curr currp :" + currp.getNumeroPage());
                            System.out.println("documentCree :" + documentCree);
                            System.out.println(" esptypee :" + esptypee);
                            System.out.println("currentLineQuantities.get(i) :" + currentLineQuantities.get(i));
                            System.out.println("esptypee.getNumOrdre() :" + esptypee.getNumOrdre());
                            System.out.println("jourMar :" + jourMar);


                            System.out.println(esptypee);
                            qCapture qcapture = new qCapture(k, currp.getNumeroPage(), documentCree, esptypee, currentLineQuantities.get(esptypee.getNumOrdre()-1), esptypee.getNumOrdre(), jourMar, null);
                            qcapture.setQdoc(documentCree);
                            qcapture.setJourMere(jourMar);
                            qcapture.setDatedepart(documentCree.getDepart());
                            qcapture.setNummimm(documentCree.getNumImm());
                            qcapture.setDateJour(dateJourCourant);
                            qcapture.setIdespece(esptypee.getQespeceId());
                            qcapture.setEsptype(esptypee.getEnumesptype());
                            capturesLine.add(qcapture);
                            ttot = ttot + currentLineQuantities.get(esptypee.getNumOrdre()-1);
                            i++;
                            //  capturesTotal.add(qcapture);
                        }
                        jourMar.setTotalCapturs(ttot);
                        if (typeM.equals("H")) jourMar.setTotalCong(ttot); else jourMar.setTotalCong(0);
                        jourMar.setNbrCaisse(0);
                        jourMar.setCapturesDuMarree(capturesLine);
                        jourMar.setPageMarree(currp);
                        joursMar.add(jourMar);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dateJourCourant);
                        cal.add(Calendar.DATE, 1);
                        dateJourCourant = cal.getTime();
                        //capturesLine.clear();
                    }
                    ttotp=ttotp+ttot;
                }
                currp.setEspecesDyn(dynEsps);
                currp.setListJours(joursMar);
                currp.setTotalCapturs(ttotp);
                if (typeM.equals("H")) currp.setTotalCong(ttotp);else currp.setTotalCong(0);
                ttotd=ttotd+ ttotp;
                //   joursMar.clear();
            }

            qSeq se=qseqRepository.findOne(new qSeqPK(seqActive.getDebut(),seqActive.getFin()));
            if(se==null) se=new qSeq(seqActive.getDebut(),seqActive.getFin(),documentCree);
            documentCree.setQseq(se);
            documentCree.setPages(lstPgsMar);
            documentCree.setTotalCapturs(ttotd);
            //((qDebarquement)qdoc).setCaptures(capturesTotal);
            if (typeM.equals("H"))  documentCree.setTotalCong(ttotd); else  documentCree.setTotalCapturs(0);
            System.out.println("List des numeros refusee");


        }
          save(documentCree);

    }









    @Override
    public qMarree creerMarreesByImport(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc,List<Boolean> enginsMarree,enumJP selectedTypeJP,Map<String,Map<String,List<Float>>> quantities,String typeM,Set<String> nuemeros) {
        //   qSeq seqActive=qseqRepository.findOne(seqActive1.getSeqPK());
        qSeq seqActive = seqActive1;
       List<String>   lstStr=new ArrayList<String>();
        Set<String> Refused=new HashSet<String>();
        System.out.print("debut!fin:" + seqActive1.getDebut() + "" + seqActive1.getFin());
        String numeroDebut = seqActive.getDebut(), numeroFin = seqActive.getFin(), debutPrefix = null, finPrefix = null;
        qMarree documentCree = null;
        System.out.println("ndebut" + numeroDebut);
        System.out.println("nfin" + numeroFin);



            System.out.println("flag lehbib");
        qPageCarnet debutp = qpagecarnetRepository.findOne(new qPageCarnetPK(numeroDebut, typeDoc));
        qPageCarnet finp = qpagecarnetRepository.findOne(new qPageCarnetPK(numeroFin, typeDoc));
        Long finNumberL = finp.getNumeroOrdrePage(), debutNumberL = debutp.getNumeroOrdrePage();
        qCarnet carnetDebut = debutp.getQcarnet();
        qCarnet carnetFin = finp.getQcarnet();
        debutPrefix = carnetDebut.getPrefixNumerotation().toString();
        qPrefixPK pk = new qPrefixPK(debutPrefix, typeDoc);
        qPrefix currprefixx = prefRepo.findOne(pk);

        qNavireLegale qnav = carnetDebut.getQnavire();
        qConcession qconcess;
        //qnav.getQlicencebatlastdernier().getQconcession(); // concession du dernier lic
        //carnetDebut.getQconcession();
        List<qEnginPecheMar> choixEnginsMar = new ArrayList<qEnginPecheMar>();
        qEnginPecheMar engmar1 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Chalut, enumEnginDeb.Indefini, 0, enginsMarree.get(0));
        qEnginPecheMar engmar2 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Casier, enumEnginDeb.Indefini, 0, enginsMarree.get(1));
        qEnginPecheMar engmar3 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Nasses, enumEnginDeb.Indefini, 0, enginsMarree.get(2));
        qEnginPecheMar engmar4 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Pots, enumEnginDeb.Indefini, 0, enginsMarree.get(3));

        qEnginPecheMar engmar6 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Filet_tremail, enumEnginDeb.Indefini, 0, enginsMarree.get(4));
        qEnginPecheMar engmar7 = new qEnginPecheMar(qnav.getNumimm(), dateDepart, enumEngin.Turlutte, enumEnginDeb.Indefini, 0, enginsMarree.get(5));

        choixEnginsMar.add(engmar1);
        choixEnginsMar.add(engmar2);
        choixEnginsMar.add(engmar3);
        choixEnginsMar.add(engmar4);
        choixEnginsMar.add(engmar6);
        choixEnginsMar.add(engmar7);
       // qPrefixPK pref = new qPrefixPK(debutPrefix, typeDoc);
        // cat du dernier licence
        qModelJP currentModel = qmodelRepository.findOne(pk);
     //   qPrefix curpref = prefRepo.findOne(pref);
        qCategRessource h = null;
        if (typeM.equals("H"))
            h = qcqtRepository.findCategH(currprefixx);
        if (typeM.equals("C"))
            h = qcqtRepository.findCategC(currprefixx);
        System.out.println("type M : "+typeM);
        System.out.println("type concession : "+currprefixx.getPrefix());
        qTypeConcession typeConcessionActive = typeconcessionRepository.findOne(h.getIdtypeConcession());

        List<qCategRessource> ours = new ArrayList<qCategRessource>();
        Integer flagcotiere = 0, flaghautiriere = 0;
        if (carnetDebut.getQconcession() != null) {
            qconcess = carnetDebut.getQconcession();
            ours.addAll(carnetDebut.getQconcession().getCategoriesRessources());
        } else qconcess = null;
        System.out.println("numimm:" + qnav.getNumimm());
        System.out.println("type concession : "+h);

        System.out.println("depart:" + dateDepart);
        for(String str:nuemeros) {
            //  String pc=qpagecarnetRepository.findOne(new qPageCarnetPK(str,enumTypeDoc.Journal_Peche)).getNumeroPage(); // IL FAUT VRTIFIRT LE DOCUMENTS ET PAS LE CATNET
            //   qPageMarree  pm=qcarnetRepository.check(pc.getNumeroPage());
            if(qpagecarnetRepository.retDoc(str)==0) {lstStr.add(seqActive.getDebut());Refused.add(seqActive.getDebut());}
        }

        System.out.println("list str" + lstStr);
        if(lstStr.size()==0) {
            documentCree = new qMarree(enumTypeDoc.Journal_Peche, dateDepart, dateRetour, seqActive, qnav, null,
                    qconcess, selectedTypeJP, choixEnginsMar, null, 0, 0, 0, qnav.getModePeche(), typeConcessionActive);
            documentCree.setTypeJP(selectedTypeJP);
            documentCree.setSegPeche(debutPrefix);
            documentCree.setNomNavire(qnav.getNomnav());
            documentCree.setNumImm(qnav.getNumimm());
            documentCree.setQnavire(qnav);
            documentCree.setQprefix(currprefixx);

            System.out.println(debutPrefix);
            // pour la journal de peche
            System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
            System.out.println(currentModel.getEspecestypees());
            System.out.println(currentModel.getEspecestypees().size());

            System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
            List<qPageMarree> lstPgsMar = new ArrayList<qPageMarree>();
            // preparer les pages
            System.out.println("debutc/debut/fin : " + carnetDebut.getNumeroDebutPage() + "/" + debutNumberL + "/" + finNumberL);
            //  Set<qPageCarnet> setPC=new HashSet<qPageCarnet>();
            System.out.println("dockpk : " + documentCree.getqDocPK());
            List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
            System.out.println("page concernee size : " + pagesConcernees.size());
            for (qPageCarnet qpc : pagesConcernees) {
                lstPgsMar.add((qPageMarree) qpc);
                System.out.println("numero de page : " + qpc.getNumeroPage());
            }
            Iterator<qPageMarree> qmarpages = lstPgsMar.iterator();
            Date dateJourCourant = documentCree.getDepart();
            List<qJourMere> joursMar = null;
            Map<String, List<Float>> currentPageAQuanties = null;
            List<Float> currentLineQuantities = null;
            List<Float> currentLineQuantitiesZero = new ArrayList<Float>();
            for (int i = 0; i < 16; i++) currentLineQuantitiesZero.add(0F);
            Integer i = 0;
            while (qmarpages.hasNext()) {
                List<qEspeceDynamic> dynEsps = null;
                qPageMarree currp = qmarpages.next();
                currp.setEtatPage(enumEtatPage.DRAFT);
                currp.setQmarree((qMarree) documentCree);

                System.out.println("quantities : from inside"+quantities);
                currentPageAQuanties = quantities.get(currp.getNumeroPage());
                System.out.println("quantities of page : from inside"+currentPageAQuanties);
                dynEsps = qmodelService.getEspecestypeesDyn(currp.getNumeroPage(), documentCree.getEnumtypedoc(), currentModel);

                for (qEspeceDynamic dn : dynEsps) {
                    dn.setPagemaree(currp);
                } //,qmodelService.getEspecestypeesDyn(qnav.getNumimm(),dateDepart,currentModel)

                currp.setEspecesDyn(dynEsps);

                // parcourir les ligne de page
                joursMar = new ArrayList<qJourMere>();
                for (int k = 0; k < currp.getNbrLigne(); k++) {
                    if (dateJourCourant.before(documentCree.getRetour()) || dateJourCourant.equals(documentCree.getRetour())) {
                        qJourMere jourMar = new qJourMere(k, currp.getNumeroPage(), dateJourCourant, qnav, null, 0, 0, 0, currp);
                        // traitement des captures
                        currentLineQuantities = currentPageAQuanties.get(Integer.toString(k));
                        System.out.println("quantities of line : "+k+ " : "+currentLineQuantities);
                        if (currentLineQuantities == null) {
                            currentLineQuantities = currentLineQuantitiesZero;
                        }
                        List<qCapture> capturesLine = new ArrayList<qCapture>();
                        i = 0;
                        float ttot = 0;
                        for (qEspeceTypee esptypee : currentModel.getEspecestypees()) {
                            System.out.println("curr k :" + esptypee.getNumOrdre());
                            System.out.println("curr currp :" + currp.getNumeroPage());
                            System.out.println("documentCree :" + documentCree);
                            System.out.println(" esptypee :" + esptypee);
                            System.out.println("currentLineQuantities.get(i) :" + currentLineQuantities.get(i));
                            System.out.println("esptypee.getNumOrdre() :" + esptypee.getNumOrdre());
                            System.out.println("jourMar :" + jourMar);
                            if (esptypee.getTypeesptypee().equals(enumTypeEspTypee.DYNAMIC)) {
                                {
                                    if (i == 13) esptypee.setQespece(espRepository.findOne("Rampo"));
                                    if (i == 14) esptypee.setQespece(espRepository.findOne("Huevo"));
                                }
                            }
                            qCapture qcapture = new qCapture(k, currp.getNumeroPage(), documentCree, esptypee, currentLineQuantities.get(i), esptypee.getNumOrdre(), jourMar, null);
                            qcapture.setQdoc(documentCree);
                            qcapture.setJourMere(jourMar);
                            qcapture.setDatedepart(documentCree.getDepart());
                            qcapture.setNummimm(documentCree.getNumImm());
                            qcapture.setDateJour(dateJourCourant);
                            qcapture.setIdespece(esptypee.getQespeceId());
                            qcapture.setEsptype(esptypee.getEnumesptype());
                            capturesLine.add(qcapture);
                            ttot = ttot + currentLineQuantities.get(i);
                            i++;
                            //  capturesTotal.add(qcapture);
                        }
                        jourMar.setTotalCapturs(ttot);
                        jourMar.setTotalCong(ttot);
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

            //  ((qDebarquement)qdoc).setCaptures(capturesTotal);


            System.out.println("List des numeros refusee");


        }
     //   save(documentCree);
        return documentCree;
    }











    @Override
    public String importerDocuments(MultipartFile file, String fullpatchname) {

      String  TypeDoc,Depart,EngFilet_maillant,Filet_Enc_ST,Palangre,Filet_tremail,Casier,	Ligne,	Pots,Turlutte,categDebCeph,	CategDebDem,CategCrustDeb,CategPelDeb,CategAutresMollusques,qseqdebut,qseqfin,DateRetour,	DateJour,	NPage ,Esp1	,Esp2,	Esp3,	Esp4,	Esp5,	Esp6,	Esp7,	Esp8,	Esp9,	Esp10,	Esp11,	Esp12,	Esp13,	Esp14,	Esp15,treatedDocType="";
        Integer indexLigneDebArtisanal=1;
List<Boolean> choixEnginsPA=new ArrayList<Boolean>(),choixCategoriesPA=new ArrayList<Boolean>();
        Map<String,Map<Integer,List<Float>>>  quantities=new HashMap<String,Map<Integer,List<Float>>>();
        Map<Integer,List<Float>> currentPageCaps=null;//new HashMap<Integer,List<Double>>();
        List<Float> currentLineCaptures=new ArrayList<Float>();
        Date DDepart=null,DRetour=null; qPageCarnet currentp=null;qPageCarnet currentpnew=null;qPageCarnetPK pk=null;qPageCarnetPK pknew=null;
        qPrefixPK prefpk=null;
        qModelJP currentModel=null;
        qSeq seqActive=null;
        qPrefixPK prefpknew=null;
        qModelJP currentModelnew=null;
        qSeq seqActivenew=null;
        Integer k=0;
        try {
          file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
            FileReader reader = new FileReader(fullpatchname);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    System.out.println(k);

                    String splitarray[] = line.split("\t");

                            TypeDoc=splitarray[0];
                            Depart=splitarray[1];
                             DateRetour=	splitarray[2];
                            EngFilet_maillant=splitarray[3];
                            Filet_Enc_ST=splitarray[4];
                            Palangre=splitarray[5];
                            Filet_tremail=splitarray[6];
                            Casier=	splitarray[7];
                            Ligne=	splitarray[8];
                            Pots=splitarray[9];
                            Turlutte=splitarray[10];
                            categDebCeph=splitarray[11];
                            CategDebDem=splitarray[12];
                            CategCrustDeb=splitarray[13];
                            CategPelDeb=splitarray[14];
                            CategAutresMollusques=splitarray[15];
                            qseqdebut=  splitarray[16];
                            qseqfin=    splitarray[17];
                            DateJour=	splitarray[18];
                            NPage=  splitarray[19];
                            Esp1=   splitarray[20];
                            Esp2=   splitarray[21];
                            Esp3=   splitarray[22];
                            Esp4=	splitarray[23];
                            Esp5=	splitarray[24];
                            Esp6=   splitarray[25];
                            Esp7=	splitarray[26];
                            Esp8=	splitarray[27];
                            Esp9=	splitarray[28];
                            Esp10=	splitarray[29];
                            Esp11=  splitarray[30];
                            Esp12=	splitarray[31];
                            Esp13=	splitarray[32];
                            Esp14=	splitarray[33];
                            Esp15=  splitarray[34];

                    SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yyyy");
                    //	if((!splitarray[13].equals("VIDE")) || (!splitarray[14].equals("VIDE"))) {


                    // traitement des debarquement Artisanale

                    if(splitarray[0].equals("DEBARQUEMENTA"))
                    {
                        currentPageCaps=new HashMap<Integer,List<Float>>();
                        try {
                            DDepart=sdfmt1.parse(Depart);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        try {
                            DRetour=sdfmt1.parse(DateRetour);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        indexLigneDebArtisanal=0;

                        pk=new qPageCarnetPK(NPage,enumTypeDoc.Fiche_Debarquement);
                        currentp=qpagecarnetRepository.findOne(pk);
                        prefpk=new qPrefixPK("PA",enumTypeDoc.Fiche_Debarquement);

                       currentModel=qmodelRepository.findOne(prefpk);
                       seqActive=new qSeq(qseqdebut,qseqfin,null);
                        // former les choix pour les engins de PA
                        choixEnginsPA.add(Boolean.parseBoolean(EngFilet_maillant));
                        choixEnginsPA.add(Boolean.parseBoolean(Filet_Enc_ST));
                        choixEnginsPA.add(Boolean.parseBoolean(Palangre));
                        choixEnginsPA.add(Boolean.parseBoolean(Palangre));
                        choixEnginsPA.add(Boolean.parseBoolean(Casier));
                        choixEnginsPA.add(Boolean.parseBoolean(Ligne));
                        choixEnginsPA.add(Boolean.parseBoolean(Pots));
                        choixEnginsPA.add(Boolean.parseBoolean(Turlutte));
                        // former les chois pour les  categories de PA

                        choixCategoriesPA.add(Boolean.parseBoolean(categDebCeph));
                        choixCategoriesPA.add(Boolean.parseBoolean(CategDebDem));
                        choixCategoriesPA.add(Boolean.parseBoolean(CategCrustDeb));
                        choixCategoriesPA.add(Boolean.parseBoolean(CategPelDeb));
                        choixCategoriesPA.add(Boolean.parseBoolean(CategAutresMollusques));

                        // former la mape pour les captures par page et pare line ou jour*
                        List<String> qs=new ArrayList<String>();
                        qs.add(Esp1);
                        qs.add(Esp2);
                        qs.add(Esp3);
                        qs.add(Esp4);
                        qs.add(Esp5);
                        qs.add(Esp6);
                        qs.add(Esp7);
                        qs.add(Esp8);
                        qs.add(Esp9);
                        qs.add(Esp10);
                        qs.add(Esp11);
                        qs.add(Esp12);
                        qs.add(Esp13);
                        qs.add(Esp14);
                        qs.add(Esp15);

                       for(int i=0;i<currentModel.getEspecestypees().size();i++)
                        currentLineCaptures.add(Float.parseFloat(qs.get(i)));

                        currentPageCaps.put(indexLigneDebArtisanal,currentLineCaptures);
                         // create new debarquement
                    }
                 else {
                        if (splitarray[0].equals("V") ) {
                            currentLineCaptures.clear();

                            pknew = new qPageCarnetPK(NPage, enumTypeDoc.Fiche_Debarquement);
                            currentpnew = qpagecarnetRepository.findOne(pknew);
                            if (currentp.equals(currentpnew)) {
                                indexLigneDebArtisanal++;
                                //    prefpknew=new qPrefixPK("PA",enumTypeDoc.Fiche_Debarquement);

                                //     currentModel=qmodelRepository.findOne(prefpk);
                          //      seqActivenew = new qSeq(qseqdebut, qseqfin, null);
                                List<String> qs = new ArrayList<String>();
                                qs.add(Esp1);
                                qs.add(Esp2);
                                qs.add(Esp3);
                                qs.add(Esp4);
                                qs.add(Esp5);
                                qs.add(Esp6);
                                qs.add(Esp7);
                                qs.add(Esp8);
                                qs.add(Esp9);
                                qs.add(Esp10);
                                qs.add(Esp11);
                                qs.add(Esp12);

                                qs.add(Esp13);
                                qs.add(Esp14);
                                qs.add(Esp15);

                                for (int i = 0; i < currentModel.getEspecestypees().size(); i++)
                                    currentLineCaptures.add(Float.parseFloat(qs.get(i)));

                                currentPageCaps.put(indexLigneDebArtisanal, currentLineCaptures);
                            }
                            else  {
                                indexLigneDebArtisanal = 0;
                                quantities.put(currentp.getNumeroPage(), currentPageCaps);
                                currentPageCaps=new HashMap<Integer,List<Float>>();
                                currentLineCaptures.clear();
                                //    prefpknew=new qPrefixPK("PA",enumTypeDoc.Fiche_Debarquement);

                                //     currentModel=qmodelRepository.findOne(prefpk);
                           //     seqActivenew = new qSeq(qseqdebut, qseqfin, null);
                                List<String> qs = new ArrayList<String>();
                                qs.add(Esp1);
                                qs.add(Esp2);
                                qs.add(Esp3);
                                qs.add(Esp4);
                                qs.add(Esp5);
                                qs.add(Esp6);
                                qs.add(Esp7);
                                qs.add(Esp8);
                                qs.add(Esp9);
                                qs.add(Esp10);
                                qs.add(Esp11);
                                qs.add(Esp12);

                                qs.add(Esp13);
                                qs.add(Esp14);
                                qs.add(Esp15);

                                for (int i = 0; i < currentModel.getEspecestypees().size(); i++)
                                    currentLineCaptures.add(Float.parseFloat(qs.get(i)));

                                currentPageCaps.put(indexLigneDebArtisanal, currentLineCaptures);
                                currentp = currentpnew;
                            }


                        }
                        else {
                            if(splitarray[0].equals("Fin")) {
                                quantities.put(currentp.getNumeroPage(), currentPageCaps);
                                currentp = null;

                                qDebarquement newDoc=creerDebarquementByImport(DDepart,DRetour,seqActive,enumTypeDoc.Fiche_Debarquement,
                                        choixEnginsPA,choixCategoriesPA,quantities);
//                                qdocRepository.save(newDoc);

                            }
                            else System.out.println("Erreur. mot cle non reconnue");
                        }
                    }

                 }

                k++;
            }


            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return null;
    }




    @Override
    public String importerMarrees(MultipartFile file, String fullpatchname) {

        Iterable<qDoc> documents=qdocRepository.findAll();
         //   List<qDoc> beans = new ArrayList<qDoc>();
            for(qDoc authprovEntity1 : documents) {
                delete(authprovEntity1.getqDocPK());
              //  beans.add(authprovEntity1);
            }
        //     return beans;
        Set<String> Refused=new HashSet<String>();
        String typeM=null;
        String  TypeDoc,TypeJP,Depart,Chalut,Prefix,	Casier,	Nasse,	Pots,	Filet_tremail,	Turlutte,qseqdebut,qseqfin,DateRetour,	DateJour,	NPage ,Esp1	,Esp2,	Esp3,	Esp4,	Esp5,	Esp6,	Esp7,	Esp8,	Esp9,	Esp10,	Esp11,	Esp12,	Esp13,	Esp14,	Esp15,Esp16,treatedDocType="";
        Integer indexLigneMarree=1;
        List<Boolean> choixEnginsMaree=new ArrayList<Boolean>();
        Map<String,Map<String,List<Float>>>  quantities=null;
        Set<String> lstusedPages=new HashSet<String>();
        Map<String,List<Float>> currentPageCaps=null;//new HashMap<Integer,List<Double>>();
        List<Float> currentLineCaptures=null;
        Set<String> numeroPages=null;
        Date DDepart=null,DRetour=null; qPageCarnet currentp=null;qPageCarnet currentpnew=null;qPageCarnetPK pk=null;qPageCarnetPK pknew=null;
        qPrefixPK prefpk=null;
        qModelJP currentModel=null;
        qSeq seqActive=null;
        qPrefixPK prefpknew=null;
        qModelJP currentModelnew=null;
        qSeq seqActivenew=null;
        enumJP selectedTypeJP=null;
        Integer k=0;
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
            FileReader reader = new FileReader(fullpatchname);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    System.out.println(k);

                    String splitarray[] = line.split("\t");

                    TypeDoc=splitarray[0];
                    TypeJP=splitarray[1];
                    Depart=splitarray[2];
                    DateRetour=	splitarray[3];
                    Chalut=splitarray[4];
                      Casier=splitarray[5];
                      Nasse=splitarray[6];
                      Pots=splitarray[7];
                      Filet_tremail=splitarray[8];
                      Turlutte=splitarray[9];
                    qseqdebut=splitarray[10];
                    qseqfin=splitarray[11];
                    DateJour=	splitarray[12];
                    NPage=splitarray[13];
                    Esp1   =splitarray[14];
                    Esp2   =splitarray[15];
                    Esp3    =splitarray[16];
                    Esp4  =	splitarray[17];
                    Esp5  =	splitarray[18];
                    Esp6    =splitarray[19];
                    Esp7  =	splitarray[20];
                    Esp8  =	splitarray[21];
                    Esp9  =	splitarray[22];
                    Esp10  =splitarray[23];
                    Esp11 =  splitarray[24];
                    Esp12=	splitarray[25];
                    Esp13=	splitarray[26];
                    Esp14=	splitarray[27];
                    Esp15=  splitarray[28];
                    Esp16=  splitarray[29];
                    Prefix=  splitarray[30];

                    SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yyyy");
                    //	if((!splitarray[13].equals("VIDE")) || (!splitarray[14].equals("VIDE"))) {


                    // traitement des debarquement Artisanale

                    if(TypeDoc.equals("MARREE")) {
                        numeroPages = new HashSet<String>();

                        quantities = new HashMap<String, Map<String, List<Float>>>();
                        if (TypeJP.equals("HAUTIRIERE")) {
                            typeM = "H";
                            selectedTypeJP = enumJP.Hautirere;
                        } else {
                            typeM = "C";
                            selectedTypeJP = enumJP.Cotier;
                        }

                        currentPageCaps = new HashMap<String, List<Float>>();
                        try {
                            DDepart = sdfmt1.parse(Depart);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        try {
                            DRetour = sdfmt1.parse(DateRetour);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        indexLigneMarree = 1;

                        pk = new qPageCarnetPK(NPage, enumTypeDoc.Journal_Peche);
                        currentp = qpagecarnetRepository.findOne(pk);
                        System.out.println("currentp  :" + currentp);
                        numeroPages.add(currentp.getNumeroPage());
                      //  lstusedPages.add(currentp.getNumeroPage());
                        prefpk = new qPrefixPK(Prefix, enumTypeDoc.Journal_Peche);

                        currentModel = qmodelRepository.findOne(prefpk);
                        seqActive = new qSeq(qseqdebut, qseqfin, null);
                        // former les choix pour les engins de PA

                        choixEnginsMaree.add(Boolean.parseBoolean(Chalut));
                        choixEnginsMaree.add(Boolean.parseBoolean(Casier));
                        choixEnginsMaree.add(Boolean.parseBoolean(Nasse));
                        choixEnginsMaree.add(Boolean.parseBoolean(Pots));
                        choixEnginsMaree.add(Boolean.parseBoolean(Filet_tremail));
                        choixEnginsMaree.add(Boolean.parseBoolean(Turlutte));

                        // former la mape pour les captures par page et pare line ou jour*
                        List<String> qs = new ArrayList<String>();
                        qs.add(Esp1);
                        qs.add(Esp2);
                        qs.add(Esp3);
                        qs.add(Esp4);
                        qs.add(Esp5);
                        qs.add(Esp6);
                        qs.add(Esp7);
                        qs.add(Esp8);
                        qs.add(Esp9);
                        qs.add(Esp10);
                        qs.add(Esp11);
                        qs.add(Esp12);
                        qs.add(Esp13);
                        qs.add(Esp14);
                        qs.add(Esp15);
                        qs.add(Esp16);
                        System.out.println("size of current model : "+currentModel.getEspecestypees().size());
                        currentLineCaptures=new ArrayList<Float>();
                        for (qEspeceTypee espt:currentModel.getEspecestypees())//int i = 0; i < currentModel.getEspecestypees().size(); i++)
                        {

                            currentLineCaptures.add(Float.parseFloat(qs.get(espt.getNumOrdre()-1)));
                        }

                        currentPageCaps.put(Integer.toString(indexLigneMarree-1),currentLineCaptures);
                        // create new debarquement
                    }
                    else {
                        if (splitarray[0].equals("V") ) {
                            currentLineCaptures=new ArrayList<Float>();
                            System.out.println("currp:"+NPage);
                            pknew = new qPageCarnetPK(NPage, enumTypeDoc.Journal_Peche);
                            currentpnew = qpagecarnetRepository.findOne(pknew);
                            numeroPages.add(currentp.getNumeroPage());
                        //    lstusedPages.add(currentp.getNumeroPage());
                            System.out.println("currpn:"+NPage);
                            System.out.println("index ligne:"+indexLigneMarree);
                            if (currentp.equals(currentpnew)) {
                                indexLigneMarree++;
                                //    prefpknew=new qPrefixPK("PA",enumTypeDoc.Fiche_Debarquement);

                                //     currentModel=qmodelRepository.findOne(prefpk);
                                //      seqActivenew = new qSeq(qseqdebut, qseqfin, null);
                                List<String> qs = new ArrayList<String>();
                                qs.add(Esp1);
                                qs.add(Esp2);
                                qs.add(Esp3);
                                qs.add(Esp4);
                                qs.add(Esp5);
                                qs.add(Esp6);
                                qs.add(Esp7);
                                qs.add(Esp8);
                                qs.add(Esp9);
                                qs.add(Esp10);
                                qs.add(Esp11);
                                qs.add(Esp12);

                                qs.add(Esp13);
                                qs.add(Esp14);
                                qs.add(Esp15);
                                qs.add(Esp16);
                      //          for (int i = 0; i < currentModel.getEspecestypees().size(); i++)
                        //            currentLineCaptures.add(Float.parseFloat(qs.get(i)));
                                currentLineCaptures=new ArrayList<Float>();
                                for (qEspeceTypee espt:currentModel.getEspecestypees())//int i = 0; i < currentModel.getEspecestypees().size(); i++)
                                {

                                    currentLineCaptures.add(Float.parseFloat(qs.get(espt.getNumOrdre()-1)));
                                }

                                currentPageCaps.put(Integer.toString(indexLigneMarree-1), currentLineCaptures);

                            }
                            else  {
                                quantities.put(currentp.getNumeroPage(), currentPageCaps);
                                indexLigneMarree = 1;

                                currentp = currentpnew;
                                currentPageCaps=new HashMap<String,List<Float>>();
                                currentLineCaptures=new ArrayList<Float>();
                                //    prefpknew=new qPrefixPK("PA",enumTypeDoc.Fiche_Debarquement);

                                //     currentModel=qmodelRepository.findOne(prefpk);
                                //     seqActivenew = new qSeq(qseqdebut, qseqfin, null);
                                List<String> qs = new ArrayList<String>();
                                qs.add(Esp1);
                                qs.add(Esp2);
                                qs.add(Esp3);
                                qs.add(Esp4);
                                qs.add(Esp5);
                                qs.add(Esp6);
                                qs.add(Esp7);
                                qs.add(Esp8);
                                qs.add(Esp9);
                                qs.add(Esp10);
                                qs.add(Esp11);
                                qs.add(Esp12);

                                qs.add(Esp13);
                                qs.add(Esp14);
                                qs.add(Esp15);
                                qs.add(Esp16);
                                for (qEspeceTypee espt:currentModel.getEspecestypees())//int i = 0; i < currentModel.getEspecestypees().size(); i++)
                                {

                                    currentLineCaptures.add(Float.parseFloat(qs.get(espt.getNumOrdre()-1)));
                                }
                                currentPageCaps.put(Integer.toString(indexLigneMarree-1), currentLineCaptures);

                            }


                        }
                        else {
                            if(splitarray[0].equals("Fin")) {
                                quantities.put(currentp.getNumeroPage(), currentPageCaps);
                                //currentp = null;
                                System.out.println("chetchik "+splitarray[0]);
                                System.out.println("depart "+DDepart);
                                System.out.println("retour"+DRetour);
                                System.out.println("quantities"+quantities);
                                List<String> lstStr=new ArrayList<String>();
                                Integer counter=0;
                                        for(String str:numeroPages) {
                                            if(lstusedPages.contains(str))   { counter++;  Refused.add(str); }
                                            lstusedPages.add(str);
                                        }

                                if(counter==0)
                                    creerMarreesByImportDup(DDepart,DRetour,seqActive,enumTypeDoc.Journal_Peche,choixEnginsMaree,selectedTypeJP,quantities,typeM,numeroPages);
                                   // save1(newDoc);

                            }
                            else System.out.println("Erreur. mot cle non reconnue");
                        }
                    }

                }

                k++;
            }

            for(String dd:Refused) {
                System.out.println("refused page "+dd);
            }

            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return null;
    }
    @Override
    public Expression<Class> rendreTypeM(enumJP jp) {
        Expression<Class> cl=null;
        CriteriaBuilder     cb = em.getCriteriaBuilder();
        if(jp.equals(enumJP.Hautirere)) {

            cl=cb.literal(qTypeConcessionHautiriere.class)  ;
        }
        else  cl=cb.literal(qTypeConcessionCotiere.class);
        return cl;
    }

    @Override
    public String importerTraitements(MultipartFile file, String fullpatchname) {

        String  TypeDoc,Depart,EngFilet_maillant,Filet_Enc_ST,Palangre,Filet_tremail,Casier,	Ligne,	Pots,Turlutte,categDebCeph,	CategDebDem,CategCrustDeb,CategPelDeb,CategAutresMollusques,qseqdebut,qseqfin,DateRetour,	DateJour,	NPage ,Esp1	,Esp2,	Esp3,	Esp4,	Esp5,	Esp6,	Esp7,	Esp8,	Esp9,	Esp10,	Esp11,	Esp12,	Esp13,	Esp14,	Esp15,treatedDocType="";
        Integer indexLigneDebArtisanal=1;
        List<Boolean> choixEnginsPA=new ArrayList<Boolean>(),choixCategoriesPA=new ArrayList<Boolean>();
        Map<String,Map<Integer,List<Float>>>  quantities=new HashMap<String,Map<Integer,List<Float>>>();
        Map<Integer,List<Float>> currentPageCaps=null;//new HashMap<Integer,List<Double>>();
        List<Float> currentLineCaptures=null;
        Date DDepart=null,DRetour=null; qPageCarnet currentp=null;qPageCarnet currentpnew=null;qPageCarnetPK pk=null;qPageCarnetPK pknew=null;
        qPrefixPK prefpk=null;
        qModelJP currentModel=null;
        qSeq seqActive=null;
        qPrefixPK prefpknew=null;
        qModelJP currentModelnew=null;
        qSeq seqActivenew=null;
        Integer k=0;
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
            FileReader reader = new FileReader(fullpatchname);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    System.out.println(k);

                    String splitarray[] = line.split("\t");

                    TypeDoc=splitarray[0];
                    Depart=splitarray[1];
                    DateRetour=	splitarray[2];
                    EngFilet_maillant=splitarray[3];
                    Filet_Enc_ST=splitarray[4];
                    Palangre=splitarray[5];
                    Filet_tremail=splitarray[6];
                    Casier=	splitarray[7];
                    Ligne=	splitarray[8];
                    Pots=splitarray[9];
                    Turlutte=splitarray[10];
                    categDebCeph=splitarray[11];
                    CategDebDem=splitarray[12];
                    CategCrustDeb=splitarray[13];
                    CategPelDeb=splitarray[14];
                    CategAutresMollusques=splitarray[15];
                    qseqdebut=splitarray[16];
                    qseqfin=splitarray[17];
                    DateJour=	splitarray[18];
                    NPage=splitarray[19];
                    Esp1=splitarray[20];
                    Esp2=splitarray[21];
                    Esp3=splitarray[22];
                    Esp4=	splitarray[23];
                    Esp5=	splitarray[24];
                    Esp6=splitarray[25];
                    Esp7=	splitarray[26];
                    Esp8=	splitarray[27];
                    Esp9=	splitarray[28];
                    Esp10=	splitarray[29];
                    Esp11=  splitarray[30];
                    Esp12=	splitarray[31];
                    Esp13=	splitarray[32];
                    Esp14=	splitarray[33];
                    Esp15=  splitarray[34];

                    SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yyyy");
                    //	if((!splitarray[13].equals("VIDE")) || (!splitarray[14].equals("VIDE"))) {


                    // traitement des debarquement Artisanale

                    if(splitarray[0].equals("DEBARQUEMENTA"))
                    {
                        currentPageCaps=new HashMap<Integer,List<Float>>();

                        try {
                            DDepart=sdfmt1.parse(Depart);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        try {
                            DRetour=sdfmt1.parse(DateRetour);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        indexLigneDebArtisanal=0;

                        pk=new qPageCarnetPK(NPage,enumTypeDoc.Fiche_Debarquement);
                        currentp=qpagecarnetRepository.findOne(pk);
                        prefpk=new qPrefixPK("PA",enumTypeDoc.Fiche_Debarquement);

                        currentModel=qmodelRepository.findOne(prefpk);
                        seqActive=new qSeq(qseqdebut,qseqfin,null);
                        // former les choix pour les engins de PA
                        choixEnginsPA.add(Boolean.parseBoolean(EngFilet_maillant));
                        choixEnginsPA.add(Boolean.parseBoolean(Filet_Enc_ST));
                        choixEnginsPA.add(Boolean.parseBoolean(Palangre));
                        choixEnginsPA.add(Boolean.parseBoolean(Palangre));
                        choixEnginsPA.add(Boolean.parseBoolean(Casier));
                        choixEnginsPA.add(Boolean.parseBoolean(Ligne));
                        choixEnginsPA.add(Boolean.parseBoolean(Pots));
                        choixEnginsPA.add(Boolean.parseBoolean(Turlutte));
                        // former les chois pour les  categories de PA

                        choixCategoriesPA.add(Boolean.parseBoolean(categDebCeph));
                        choixCategoriesPA.add(Boolean.parseBoolean(CategDebDem));
                        choixCategoriesPA.add(Boolean.parseBoolean(CategCrustDeb));
                        choixCategoriesPA.add(Boolean.parseBoolean(CategPelDeb));
                        choixCategoriesPA.add(Boolean.parseBoolean(CategAutresMollusques));

                        // former la mape pour les captures par page et pare line ou jour*
                        List<String> qs=new ArrayList<String>();
                        qs.add(Esp1);
                        qs.add(Esp2);
                        qs.add(Esp3);
                        qs.add(Esp4);
                        qs.add(Esp5);
                        qs.add(Esp6);
                        qs.add(Esp7);
                        qs.add(Esp8);
                        qs.add(Esp9);
                        qs.add(Esp10);
                        qs.add(Esp11);
                        qs.add(Esp12);

                        qs.add(Esp13);
                        qs.add(Esp14);
                        qs.add(Esp15);
                        currentLineCaptures=new ArrayList<Float>();
                        for(int i=0;i<currentModel.getEspecestypees().size();i++)
                            currentLineCaptures.add(Float.parseFloat(qs.get(i)));

                        currentPageCaps.put(indexLigneDebArtisanal,currentLineCaptures);
                        // create new debarquement
                    }
                    else {
                        if (splitarray[0].equals("V") ) {
                            currentLineCaptures.clear();

                            pknew = new qPageCarnetPK(NPage, enumTypeDoc.Fiche_Debarquement);
                            currentpnew = qpagecarnetRepository.findOne(pknew);
                            if (currentp.equals(currentpnew)) {
                                indexLigneDebArtisanal++;
                                //    prefpknew=new qPrefixPK("PA",enumTypeDoc.Fiche_Debarquement);

                                //     currentModel=qmodelRepository.findOne(prefpk);
                                //      seqActivenew = new qSeq(qseqdebut, qseqfin, null);
                                List<String> qs = new ArrayList<String>();
                                qs.add(Esp1);
                                qs.add(Esp2);
                                qs.add(Esp3);
                                qs.add(Esp4);
                                qs.add(Esp5);
                                qs.add(Esp6);
                                qs.add(Esp7);
                                qs.add(Esp8);
                                qs.add(Esp9);
                                qs.add(Esp10);
                                qs.add(Esp11);
                                qs.add(Esp12);

                                qs.add(Esp13);
                                qs.add(Esp14);
                                qs.add(Esp15);

                                for (int i = 0; i < currentModel.getEspecestypees().size(); i++)
                                    currentLineCaptures.add(Float.parseFloat(qs.get(i)));

                                currentPageCaps.put(indexLigneDebArtisanal, currentLineCaptures);
                            }
                            else  {
                                indexLigneDebArtisanal = 0;
                                quantities.put(currentp.getNumeroPage(), currentPageCaps);
                                currentPageCaps=new HashMap<Integer,List<Float>>();
                                currentLineCaptures.clear();
                                //    prefpknew=new qPrefixPK("PA",enumTypeDoc.Fiche_Debarquement);
                                //     currentModel=qmodelRepository.findOne(prefpk);
                                //     seqActivenew = new qSeq(qseqdebut, qseqfin, null);
                                List<String> qs = new ArrayList<String>();
                                qs.add(Esp1);
                                qs.add(Esp2);
                                qs.add(Esp3);
                                qs.add(Esp4);
                                qs.add(Esp5);
                                qs.add(Esp6);
                                qs.add(Esp7);
                                qs.add(Esp8);
                                qs.add(Esp9);
                                qs.add(Esp10);
                                qs.add(Esp11);
                                qs.add(Esp12);

                                qs.add(Esp13);
                                qs.add(Esp14);
                                qs.add(Esp15);

                                for (int i = 0; i < currentModel.getEspecestypees().size(); i++)
                                    currentLineCaptures.add(Float.parseFloat(qs.get(i)));

                                currentPageCaps.put(indexLigneDebArtisanal, currentLineCaptures);
                                currentp = currentpnew;
                            }
                        }
                        else {
                            if(splitarray[0].equals("Fin")) {
                                quantities.put(currentp.getNumeroPage(), currentPageCaps);
                                currentp = null;

                                qDebarquement newDoc=creerDebarquementByImport(DDepart,DRetour,seqActive,enumTypeDoc.Fiche_Debarquement,
                                        choixEnginsPA,choixCategoriesPA,quantities);
//                                qdocRepository.save(newDoc);

                            }
                            else System.out.println("Erreur. mot cle non reconnue");
                        }
                    }

                }

                k++;
            }


            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return null;
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

    qNavireLegale qnav = carnetDebut.getQnavire();
    qConcession qconcess =carnetDebut.getQconcession();//qnav.getQlicencebatlastdernier().getQconcession(); // concession du dernier lic
    //carnetDebut.getQconcession();
    Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();
    List<qEnginPecheDebar> choixEnginsDeb=new ArrayList<qEnginPecheDebar>();
    List<qTypeConcession> typesConceArt=new ArrayList<qTypeConcession>();
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
        qTypeConcession tc1=typeconcessionRepository.findOne(1);
        qTypeConcession tc2=typeconcessionRepository.findOne(2);
        qTypeConcession tc3=typeconcessionRepository.findOne(3);
        qTypeConcession tc4=typeconcessionRepository.findOne(4);
        qTypeConcession tc5=typeconcessionRepository.findOne(5);

    qCategDeb c1=new qCategDeb(qnav.getNumimm(),dateDepart,cr1,false);
    qCategDeb c2=new qCategDeb(qnav.getNumimm(),dateDepart,cr2,false);
    qCategDeb c3=new qCategDeb(qnav.getNumimm(),dateDepart,cr3,false);
    qCategDeb c4=new qCategDeb(qnav.getNumimm(),dateDepart,cr4,false);
    qCategDeb c5=new qCategDeb(qnav.getNumimm(),dateDepart,cr5,false);
        qCategDeb xx;
 //   categsArt.add(c1);categsArt.add(c2);categsArt.add(c3);categsArt.add(c4);categsArt.add(c5);
        //     typesConceArt.add(tc1);typesConceArt.add(tc2);typesConceArt.add(tc3);typesConceArt.add(tc4);typesConceArt.add(tc5);
for(qCategRessource cress:typeconcessionRepository.returnCategoriesArtisanale()) {
     xx=new qCategDeb(qnav.getNumimm(),dateDepart,cress,false);
    categsArt.add(xx);
}
       // typesConceArt.addAll(typeconcessionRepository.returnTypesConcessionArtisanale());


    List<qCategDeb> categsCot=new ArrayList<qCategDeb>();
    List<qTypeConcession> typesConceCot=new ArrayList<qTypeConcession>();

    qCategRessource cr11=qcqtRepository.findOne(6);
    qCategRessource cr21=qcqtRepository.findOne(7);
    qCategRessource cr31=qcqtRepository.findOne(8);
    qCategRessource cr41=qcqtRepository.findOne(9);
    qCategRessource cr51=qcqtRepository.findOne(10);
    qCategRessource cr61=qcqtRepository.findOne(11);
    qCategRessource cr71=qcqtRepository.findOne(12);

        qTypeConcession tc11=typeconcessionRepository.findOne(6);
        qTypeConcession tc21=typeconcessionRepository.findOne(7);
        qTypeConcession tc31=typeconcessionRepository.findOne(8);
        qTypeConcession tc41=typeconcessionRepository.findOne(9);
        qTypeConcession tc51=typeconcessionRepository.findOne(10);
        qTypeConcession tc61=typeconcessionRepository.findOne(11);
        qTypeConcession tc71=typeconcessionRepository.findOne(12);


    qCategDeb c11=new qCategDeb(qnav.getNumimm(),dateDepart,cr11,false);
    qCategDeb c21=new qCategDeb(qnav.getNumimm(),dateDepart,cr21,false);
    qCategDeb c31=new qCategDeb(qnav.getNumimm(),dateDepart,cr31,false);
    qCategDeb c41=new qCategDeb(qnav.getNumimm(),dateDepart,cr41,false);

      //  categsCot.add(c11);categsCot.add(c21);categsCot.add(c31);categsCot.add(c41);
     //   typesConceCot.add(tc11);typesConceCot.add(tc21);typesConceCot.add(tc31);typesConceCot.add(tc41);
        for(qCategRessource cress:typeconcessionRepository.returnCategoriesCotNonP()) {
            xx=new qCategDeb(qnav.getNumimm(),dateDepart,cress,false);
            categsCot.add(xx);
        }
      //  typesConceCot.addAll(typeconcessionRepository.returnTypesConcessionCotNonP());


    qPrefixPK pref=new qPrefixPK(debutPrefix,typeDoc);
        qPrefix currprefixx=prefRepo.findOne(pref);
    qModelJP currentModel=qmodelRepository.findOne(pref);

        if (carnetDebut.getPrefixNumerotation().equals("PC")) {
            // la peche cotiere
            documentCree = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,null,
                    choixEnginsDeb,  enumTypeDebarquement.Cotier, qconcess,categsCot, null,0,qnav.getModePeche());
            documentCree.setSegPeche(debutPrefix);
            documentCree.setTypesConcession(typesConceCot);
            documentCree.setQprefix(currprefixx);
            documentCree.setNomNavire(qnav.getNomnav());
        }

        if (carnetDebut.getPrefixNumerotation().equals("PA")) {
            // la peche artisanal

            documentCree = new qDebarquement(enumTypeDoc.Fiche_Debarquement, dateDepart, dateRetour, seqActive, qnav,null,
                    choixEnginsDeb, enumTypeDebarquement.Artisanal,qconcess, categsArt,null,0,qnav.getModePeche());
            documentCree.setSegPeche(debutPrefix);
            documentCree.setQprefix(currprefixx);
            documentCree.setTypesConcession(typesConceArt);
            documentCree.setNomNavire(qnav.getNomnav());

        }

      //  List<qPageDebarquement> lstPgsDeb =null;
        List<qPageDebarquement> lstPgsDeb = new ArrayList<qPageDebarquement>();
        // preparer les pages
        List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
        for (qPageCarnet qpc : pagesConcernees) {
            lstPgsDeb.add((qPageDebarquement) qpc);
        }
        Iterator<qPageDebarquement> qdebpages = lstPgsDeb.iterator();
        Date dateJourCourant = documentCree.getDepart();
        List<qJourDeb> joursDeb =null;
        while (qdebpages.hasNext()) {
            qPageDebarquement currp = qdebpages.next();
            currp.setEtatPage(enumEtatPage.DRAFT);
            currp.setQdebarquement((qDebarquement) documentCree);
            for(qEspeceDynamic dn:qmodelService.getEspecestypeesDyn(currp.getNumeroPage(),documentCree.getEnumtypedoc(),currentModel)) {
                dn.setPagedebarquement(currp);
            } //,qmodelService.getEspecestypeesDyn(qnav.getNumimm(),dateDepart,currentModel)

            joursDeb = new ArrayList<qJourDeb>();
            // pqrcourir les ligne de page
            for (int k = 0; k < currp.getNbrLigne(); k++) {
                if (dateJourCourant.before(documentCree.getRetour()) || dateJourCourant.equals(documentCree.getRetour())) {

                    qJourDeb jourDeb = new qJourDeb(k,currp.getNumeroPage(),dateJourCourant, qnav, null, currp,0);
                    List<qCapture> capturesLine = new ArrayList<qCapture>();
                    // traitement des captures

                    for (qEspeceTypee esptypee : currentModel.getEspecestypees()) {
                        qCapture qcapture = new qCapture(k,currp.getNumeroPage(),documentCree, esptypee, (float)0,esptypee.getNumOrdre(), null, jourDeb);
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
      //  qdocRepository.save(documentCree);
     // ((qDebarquement)qdoc).setCaptures(capturesTotal);
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

        qNavireLegale qnav = carnetDebut.getQnavire();
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
                    qJourMereAnnexe jourMar = new qJourMereAnnexe(k,currp.getNumeroPage(),"",dateDepart, qnav, null, 0, null, currp);
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

        List<qEspeceDynamic> especesDyn=null;
        qSeq seqActive=seqActive1;
        String numeroDebut = seqActive.getDebut(),numeroFin = seqActive.getFin(), debutPrefix = null,finPrefix = null;
        qMarree documentCree=null;
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroDebut,typeDoc));
        qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(numeroFin,typeDoc));
        Long finNumberL=finp.getNumeroOrdrePage(),debutNumberL=debutp.getNumeroOrdrePage();
        qCarnet carnetDebut = debutp.getQcarnet();
        qCarnet carnetFin = finp.getQcarnet();
        debutPrefix=carnetDebut.getPrefixNumerotation().toString();
        qPrefixPK pk=new qPrefixPK(debutPrefix,typeDoc);
        qPrefix currprefixx=prefRepo.findOne(pk);

        qNavireLegale qnav = carnetDebut.getQnavire();
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
        qPrefixPK pref=new qPrefixPK(debutPrefix,typeDoc);
        // cat du dernier licence
        qModelJP currentModel=qmodelRepository.findOne(pref);
         qPrefix curpref=prefRepo.findOne(pref);
        qCategRessource h=qcqtRepository.findCategH(curpref);
        qTypeConcession typeConcessionActive=typeconcessionRepository.findOne(h.getIdtypeConcession());
        List<qCategRessource>  ours=new ArrayList<qCategRessource>();
        Integer flagcotiere = 0, flaghautiriere = 0;
        if(carnetDebut.getQconcession()!=null) {qconcess=carnetDebut.getQconcession();
        ours.addAll(carnetDebut.getQconcession().getCategoriesRessources());}
        else qconcess=null;

        documentCree = new qMarree(enumTypeDoc.Journal_Peche, dateDepart, dateRetour, seqActive, qnav,null,
        qconcess,enumJP.Hautirere ,  choixEnginsMar, null,0,0,0,qnav.getModePeche(),typeConcessionActive);
        documentCree.setSegPeche(debutPrefix);
        documentCree.setQprefix(currprefixx);
        documentCree.setTypePeche(enumJP.Hautirere);
       // typeConcessionActive.setMarrees();
        documentCree.setNomNavire(qnav.getNomnav());


        System.out.println(debutPrefix);
        // pour la journal de peche
        System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        System.out.println(currentModel.getEspecestypees());
        System.out.println(currentModel.getEspecestypees().size());

        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        List<qPageMarree> lstPgsMar = new ArrayList<qPageMarree>();
        // preparer les pages
        List<qPageCarnet> pagesConcernees = qcarnetRepository.retPages(carnetDebut, debutNumberL, finNumberL);
        for (qPageCarnet qpc : pagesConcernees) {
            lstPgsMar.add((qPageMarree) qpc);
        }
        Iterator<qPageMarree> qmarpages = lstPgsMar.iterator();

        Date dateJourCourant = documentCree.getDepart();
        List<qJourMere> joursMar = null;
        List<qEspeceDynamic> dynEsps=null;
        while (qmarpages.hasNext()) {
            qPageMarree currp = qmarpages.next();
            currp.setEtatPage(enumEtatPage.DRAFT);
            currp.setQmarree((qMarree) documentCree);
            dynEsps=qmodelService.getEspecestypeesDyn(currp.getNumeroPage(),documentCree.getEnumtypedoc(),currentModel);

            for(qEspeceDynamic dn:dynEsps) {
                dn.setPagemaree(currp);
            } //,qmodelService.getEspecestypeesDyn(qnav.getNumimm(),dateDepart,currentModel)

            currp.setEspecesDyn(dynEsps);
            // parcourir les ligne de page
            joursMar = new ArrayList<qJourMere>();
            for (int k = 0; k < currp.getNbrLigne(); k++) {
                if (dateJourCourant.before(documentCree.getRetour()) || dateJourCourant.equals(documentCree.getRetour())) {
                    qJourMere jourMar = new qJourMere(k,currp.getNumeroPage(),dateJourCourant, qnav, null, 0, 0, 0, currp);
                    // traitement des captures
                    List<qCapture> capturesLine = new ArrayList<qCapture>();
                    for (qEspeceTypee esptypee : currentModel.getEspecestypees()) {
                        qCapture qcapture = new qCapture(k,currp.getNumeroPage(),documentCree, esptypee, (float)0,esptypee.getNumOrdre(), jourMar, null);
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

       //  ((qDebarquement)qdoc).setCaptures(capturesTotal);
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
                null,qusine.getRefAgrement() , dateDepart,null,null,null,0L, null,0);

        List<qSegUsines> lstSegUsine=new ArrayList<qSegUsines>();
        qSegUsines seg1=new qSegUsines(documentCree.getNumImm(),documentCree.getDepart(),enumSegPeche.Peche_Artisanal,false,false,false,false,false,false);
        qSegUsines seg2=new qSegUsines(documentCree.getNumImm(),documentCree.getDepart(),enumSegPeche.Pêche_Cotier,false,false,false,false,false,false);
        qSegUsines seg3=new qSegUsines(documentCree.getNumImm(),documentCree.getDepart(),enumSegPeche.Pêche_Hautiriere,false,false,false,false,false,false);
        lstSegUsine.add(seg1);lstSegUsine.add(seg2);lstSegUsine.add(seg3);

        List<qQuantiteExportee> lstQteExp=new ArrayList<qQuantiteExportee>();
        qQuantiteExportee r1=new qQuantiteExportee(documentCree.getNumImm(),documentCree.getDepart(),enumZonOrientation.AFRIC,0);
        qQuantiteExportee r2=new qQuantiteExportee(documentCree.getNumImm(),documentCree.getDepart(),enumZonOrientation.ASIE,0);
        qQuantiteExportee r3=new qQuantiteExportee(documentCree.getNumImm(),documentCree.getDepart(),enumZonOrientation.EUROPE,0);
        qQuantiteExportee r4=new qQuantiteExportee(documentCree.getNumImm(),documentCree.getDepart(),enumZonOrientation.AUTRES,0);
        lstQteExp.add(r1);lstQteExp.add(r2);lstQteExp.add(r3);lstQteExp.add(r4);

        qQuantitesTraites qtesTraitees=new qQuantitesTraites(documentCree.getNumImm(),documentCree.getDepart(),0,0,0,0,0,0);


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
        List<qPageTraitement> lstPgsTaitM = new ArrayList<qPageTraitement>();
        Iterator<qPageTraitement> qmarpages = lstPgsTait.iterator();
        Date dateJourCourant = documentCree.getDepart();
        List<qOpTraitement> opSTraitement = null;
        while (qmarpages.hasNext()) {
            qPageTraitement currp = qmarpages.next();
            currp.setQtraitement(documentCree);

            currp.setEtatPage(enumEtatPage.DRAFT);
            opSTraitement = new ArrayList<qOpTraitement>();
            // parcourir les ligne de page
            for (int k = 0; k < currp.getNbrLigne(); k++) {

                    qOpTraitement uniteTr = new qOpTraitement(k,currp.getNumeroPage(),currp, null, 0L);

                  //  uniteTr.setPageTraitement(currp);
                    opSTraitement.add(uniteTr);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dateJourCourant);
                    cal.add(Calendar.DATE, 1);
                    dateJourCourant = cal.getTime();
                    //    capturesLine.clear();

            }

            currp.setOpTraitements( opSTraitement);
            lstPgsTaitM.add(currp);
            //   joursMar.clear();
        }
        //documentCree.setPagesTraitement(lstPgsTaitM);
        documentCree.setQseq(seqActive);
       documentCree.setPagesTraitement(lstPgsTait);
        //   ((qDebarquement)qdoc).setCaptures(capturesTotal);
        return documentCree;
    }
    @Override
    public qMarreeAnnexe creerNouvAnnexe(Date dateDepart,qMarree qCurrentMaree,qSeqPK spk,enumTypeDoc typeDoc) {
        // qSeq seqActive = qseqRepository.findOne(seqActive1.getSeqPK());
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
                //((qMarree) qCurrentMaree).setMarreeAnnexe(documentCree);
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
                    documentCree = creerTraitement(dateDepart, null, seqActive1,typeDoc);
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
        qNavireLegale qnav = carnetDebut.getQnavire();
        List<qLic> lics=qdocRepository.retLicences(qnav);
        return lics;
    }

    @Override
    public qDoc checkIfDupDocExist(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
        qNavireLegale qnav=null;
        qUsine  qusine=null;
        qDocPK docpk=null;
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getDebut(),typeDoc));
        System.out.println(seqActive1.getDebut());
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
        if(nbrLignes-nbrDays>=0 && nbrLignes-nbrDays<nbrLignCarnet) return true;
        else return false;
    }
    @Override
    public List<qDoc> checkIfDupPagesExist(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc) {
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getDebut(),typeDoc));
        qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getFin(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qNavireLegale qnav = carnetDebut.getQnavire();
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
        qPageCarnet    debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(seqActive1.getDebut(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qNavireLegale qnav = carnetDebut.getQnavire();
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
    public Page<qDoc> findAll(int p, int size,int validation) {
        boolean validationValue=false;
        if(validation==0)              validationValue=false;
        if(validation==1)              validationValue=true;
        Page<qDoc> entities = qdocRepository.findAll(new PageRequest(p, size),validationValue);
        return entities;
    }

    @Override
    public qMarreeAnnexe checkIfDupAnnexeExist(Date dateDepart, qSeqPK spk, enumTypeDoc typeDoc) {
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(spk.getFin(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qNavireLegale qnav = carnetDebut.getQnavire();
        qDocPK docpk=new qDocPK(((qBateau)qnav).getNumimm(),dateDepart);
        qMarreeAnnexe docdoublon=qmarreeannexeRepository.findOne(docpk);
        if(docdoublon==null) return null;
        else return docdoublon;
    }

    @Override

    public boolean checkIfNavAnnexIsSameAsNavPrincipal(Date dateDepart, qSeqPK spk, qMarree currentMaree,enumTypeDoc typeDoc) {
        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(spk.getFin(),typeDoc));
        System.out.println("---------");
        System.out.println(spk.getFin());
        System.out.println(typeDoc);
        System.out.println("---------");
        qCarnet carnetDebut = debutp.getQcarnet();
        System.out.println(carnetDebut);
        qNavireLegale qnavAnnex = carnetDebut.getQnavire();
        qNavireLegale qnavMarree = currentMaree.getQnavire();
        if(qnavAnnex.equals(qnavMarree)) return true;
        else return false;
    }

    @Override
    public List<qMarreeAnnexe> checkIfDupPagesAnnexeExist(Date dateDepart, qSeqPK spk, enumTypeDoc typeDoc) {

        qPageCarnet        debutp=qpagecarnetRepository.findOne(new qPageCarnetPK(spk.getFin(),typeDoc));
        qPageCarnet        finp=qpagecarnetRepository.findOne(new qPageCarnetPK(spk.getFin(),typeDoc));
        qCarnet carnetDebut = debutp.getQcarnet();
        qNavireLegale qnav = carnetDebut.getQnavire();

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

    @Override
    public Page<qDoc> findAllMatcheds(String numeroPage) {
          List<qDoc> searchedDocs=new ArrayList<qDoc>();
          qDoc currentDoc=null;
         Page<qPageCarnet> currentSearchedPages=qpagecarnetRepository.returnSearchedPage(new PageRequest(0, 20),numeroPage);

        for(qPageCarnet tt:currentSearchedPages){
           if(tt instanceof qPageMarree) {
            if(((qPageMarree) tt).getQmarree()!=null)   searchedDocs.add(((qPageMarree) tt).getQmarree());
           }
            if(tt instanceof qPageDebarquement) {
                if(((qPageDebarquement) tt).getQdebarquement()!=null)    searchedDocs.add(((qPageDebarquement) tt).getQdebarquement());
            }
            if(tt instanceof qPageTraitement) {
                if(((qPageTraitement) tt).getOpTraitements()!=null)     searchedDocs.add(((qPageTraitement) tt).getQtraitement());
            }
      //  currentDoc=qdocRepository.findSearchedDoc(tt.getNumeroOrdrePage(),tt.getTypeDoc());
        //    searchedDocs.add(currentDoc);
        }
    //   Page<qDoc> pgdocs= qdocRepository.findAllMatcheds(new PageRequest(0, 10),numeroPage);
        Page<qDoc> docs = new PageImpl<qDoc>(searchedDocs);

        return docs;
    }
    @Override
    public Page<qDoc> findAllMatchedDocs(Date searchDateCapture, String searchBat,Integer page) {
        String currentnumimm=null;
         qNavireLegale seletetedNavire=navRepository.findLegalByName(searchBat);
        if(seletetedNavire==null) currentnumimm=null;
        else currentnumimm=seletetedNavire.getNumimm();
        Page<qDoc> pgdocs= qdocRepository.findAllMatchedDocs(new PageRequest(page, 20),searchDateCapture, currentnumimm);
        return pgdocs;
    }

    @Override
    public Page<qDoc> findAllMatchedDocsBetweenDatesTr(Date searchDateCapture1, Date searchDateCapture2, String searchBat,Integer page) {
        String currentnumimm=null;
        qUsine seletetedUsine=qusineRepository.findOne(searchBat);
        if(seletetedUsine==null) currentnumimm=null;
        else currentnumimm=seletetedUsine.getRefAgrement();
        Page<qDoc> pgdocs= qdocRepository.findAllMatchedDocsBetweenDatesTr(new PageRequest(page, 20),searchDateCapture1,searchDateCapture2, currentnumimm);
        return pgdocs;

    }

    @Override
    public resultatsCapturesByConcession  findAllMatchedDocsBetweenDatesConcessionCap(Date searchDateCapture1, Date searchDateCapture2,String nom, List<choixTypeConcession> types,final @RequestParam(name="PARAMETER_TYPE") String PARAMETER_TYPE,qTaskProgressBar task) throws IOException, ServletException, Exception {
        String currentnumimm = null;
        Set<qDoc> a = null;
        qJour currentJour=null;
        String fileType = PARAMETER_TYPE;
        List<qJour> lstJours=new ArrayList<qJour>();
        Set<qJourMere> lstJoursMComplete=new HashSet<>();
        Set<qJourDeb> lstJoursDComplete=new HashSet<qJourDeb>();
        List<resultatCapturesCl> pgs=new ArrayList<resultatCapturesCl>();
        Set<String> colsPage=new HashSet<>();
        Set<String> colsHPage=new HashSet<>();
        Map<String,List<String>> colspagemap=new HashMap<String,List<String>>();
        Set<qEspece> listeCompleteEsp=new HashSet<qEspece>();
        Set<qEspece> listeCompleteEspDef=new HashSet<>();
        List<qTypeConcession> typesConcession=new ArrayList<qTypeConcession>();
        List<qDoc>  retDocs=new ArrayList<qDoc>();
        qTaskProgressBar taskcurrent=task;
        task.setProgress(0);
      //  Data[] list = new Data[1];
       // creation of beans
        List<qDoc> cleanedDocs=null;
        qEspece  currentEsp=null;
        Integer progress=0;
      try
        {
        for(choixTypeConcession ct:types)
        {
            if(ct.getChoix()==true)      typesConcession.add(typeconcessionRepository.findOne(ct.getTypeConcession().getQtypeconcessionpk()));
        }
        for(qTypeConcession tcon:typesConcession) {
            if(tcon.getTypeDoc().equals(enumTypeDoc.Journal_Peche)) {
                List<qJourMere>  joursMere=  qdocRepository.findAllMatchedJoursM(searchDateCapture1,searchDateCapture2,nom, tcon);
            //    retDocs.addAll(qdocRepository.findAllMatchedDocsBetweenDatesByConcessionM(searchDateCapture1,searchDateCapture2, tcon));
                lstJoursMComplete.addAll(joursMere);
            }
            if(tcon.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {
              List<qJourDeb>  joursDeb=qdocRepository.findAllMatchedJoursD(searchDateCapture1,searchDateCapture2,nom, tcon);
              //  retDocs.addAll(qdocRepository.findAllMatchedDocsBetweenDatesByConcessionD(searchDateCapture1,searchDateCapture2, tcon));
                lstJoursDComplete.addAll(joursDeb);
                System.out.println("nombre des jours : "+joursDeb.size());
            }
        }
            //update total
            taskcurrent.setTotal(lstJoursDComplete.size()+lstJoursMComplete.size()+40);
            taskcurrent.setNbrTaitee(0);
            taskcurrent.setProgress(100 * (taskcurrent.getNbrTaitee()) / taskcurrent.getTotal());

            progressService.save(taskcurrent);
        Integer i=0;
          for(qJourMere jm:lstJoursMComplete){

              currentJour=new qJour(jm.getDatejourMere(),jm.getIndexLigne(),jm.getNumPage(),jm.getNumImm(),jm.getSecteur(),jm.getNavire(),jm.getCapturesDuMarree(),
                      jm.getTotalCapturs(),jm.getTotalCong(),jm.getNbrCaisse(),jm.getPageMarree(),jm.getPageMarree().getQmarree(),jm.getNavire().getModePeche(),jm.getPageMarree().getQmarree().getTypePeche().toString());
             for(qCapture et:jm.getCapturesDuMarree()) {
            if(et.getEspeceTypee().getQespece()!=null)  {

               listeCompleteEsp.add(et.getEspeceTypee().getQespece());


            }
        }
              lstJours.add(currentJour);
      }
            taskcurrent.setNbrTaitee(lstJoursMComplete.size());
            taskcurrent.setProgress(100 * (taskcurrent.getNbrTaitee()) / taskcurrent.getTotal());
            progressService.save(taskcurrent);
        for(qJourDeb jd:lstJoursDComplete){

   currentJour=new qJour(jd.getDatejourDeb(),jd.getIndexLigne(),jd.getNumPage(),jd.getNumImm(),null,jd.getNavire(),jd.getDebarqDuJour(),jd.getTotalCapturs(),0,0,jd.getPagesDeb(),jd.getPagesDeb().getQdebarquement(),jd.getNavire().getModePeche(),null);
            List<qCapture> lstCa=jd.getDebarqDuJour();

            for(qCapture etc:lstCa) {
                // we must treat ESp+ getnumeroOrdre. to be used later
                if(etc.getEspeceTypee().getQespece()!=null)  {
                        listeCompleteEsp.add(etc.getEspeceTypee().getQespece());
                         }
                }
            lstJours.add(currentJour);
        }
            taskcurrent.setNbrTaitee(lstJoursMComplete.size()+lstJoursDComplete.size());
            taskcurrent.setProgress(100 * (taskcurrent.getNbrTaitee()) / taskcurrent.getTotal());
            progressService.save(taskcurrent);
            System.out.println("nombre des especes : "+listeCompleteEsp.size());

        BeanGenerator beanGenerator = new BeanGenerator();
        Object myBean=null;

      //  beanGenerator.addProperty("date", Date.class);
     //   beanGenerator.addProperty("navire", String.class);
        //myBean = beanGenerator.create();

        for(qEspece vv:listeCompleteEsp) {
            beanGenerator.addProperty(StringUtils.uncapitalize(vv.getNomFr().replaceAll("\\s+", "")),Float.class);
            colsPage.add(StringUtils.uncapitalize(vv.getNomFr().replaceAll("\\s+", "")));
            colsHPage.add(vv.getNomFr()); }

            //  Method setter = myBean.getClass().getMethod("set"+StringUtils.capitalize(vv.getEspeceTypee().getQespece().getNomFr().replaceAll("\\s+", "")), String.class);

//        }

        Method setter=null, getter;



            taskcurrent.setNbrTaitee(lstJoursMComplete.size()+lstJoursDComplete.size()+5);
            taskcurrent.setProgress(100 * (taskcurrent.getNbrTaitee()) / taskcurrent.getTotal());

            progressService.save(taskcurrent);
            resultatCapturesCl currentRes=null;
        for(qJour jour:lstJours) {
            currentRes=new resultatCapturesCl();
            myBean = beanGenerator.create();
           // setter =myBean.getClass().getMethod("setDate",Date.class);  // myBean.getClass().getMethod("setDate", Date.class);
         //   setter.invoke(myBean, jour.getDateJour());
currentRes.setDateCapture(jour.getDateJour());
        //    setter =myBean.getClass().getMethod("setNavire",String.class);  // myBean.getClass().getMethod("setDate", Date.class);
        //    setter.invoke(myBean, jour.getNavire().getNomnav());
            currentRes.setNavire(jour.getNavire().getNomnav());
         //   List<Object> lstStr=new ArrayList<Object>();
            for (qCapture vv : jour.getCapturesDuMarree()) {
                if (vv.getEspeceTypee().getQespece() != null) {
                    if(listeCompleteEsp.contains(vv.getEspeceTypee().getQespece())) {
                setter = myBean.getClass().getMethod("set" + StringUtils.capitalize(vv.getEspeceTypee().getQespece().getNomFr().replaceAll("\\s+", "")), Float.class);
                setter.invoke(myBean, vv.getQuantite());
                getter = myBean.getClass().getMethod("get" + StringUtils.capitalize(vv.getEspeceTypee().getQespece().getNomFr().replaceAll("\\s+", "")));
                    }
            }
               // lstStr.add(myBean);
            }
            currentRes.setResultatCapEsp(myBean);
            pgs.add(currentRes);
          }

       //     beanGenerator.addProperty(StringUtils.uncapitalize(("Esp"+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")), Float.class);
        //    colsPage.add(StringUtils.uncapitalize("Esp"+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", ""));
         //   colsHPage.add("Esp"+vv.getEspeceTypee().getNumOrdre());


            taskcurrent.setNbrTaitee(lstJoursMComplete.size()+lstJoursDComplete.size()+10);
            taskcurrent.setProgress(100 * (taskcurrent.getNbrTaitee() ) / taskcurrent.getTotal());

            progressService.save(taskcurrent);

            resultatsCapturesByConcession resultatsCapturesByConcession=new resultatsCapturesByConcession(searchDateCapture1,searchDateCapture2,null,new ArrayList<resultatCapturesCl>(pgs),new ArrayList<qTypeConcession>(typesConcession),new ArrayList<String>(colsHPage),new ArrayList<String>(colsPage));
            taskcurrent.setNbrTaitee(lstJoursMComplete.size()+lstJoursDComplete.size()+30);
            taskcurrent.setProgress(100 * (taskcurrent.getNbrTaitee() ) / taskcurrent.getTotal());

            progressService.save(taskcurrent);

            return resultatsCapturesByConcession;
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }




    }

    @Override
    public List<printedDocument> printDocument(qDoc currentDoc, String PARAMETER_TYPE, HttpServletRequest request, HttpServletResponse response, qTaskProgressBar task,String paperType) throws IOException, ServletException, Exception{
        String fileType = PARAMETER_TYPE;
        List<Object> pgs = new ArrayList<Object>();
        List<String> colsPage = new ArrayList<String>();
        List<String> colsHPage = new ArrayList<String>();
        Map<String, List<String>> colspagemap = new HashMap<String, List<String>>();
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> dataForReport = new HashMap<String, Object>();
        Date dateDepart = null;
        List<printedDocument> list = new ArrayList<printedDocument>();
        List<Object> capturesPage=null;
        List<Boolean> choixEngins=new ArrayList<Boolean>();
        task.setProgress(0);
         String segPeche=null;
         String zonePeche=null;
         List<String> choisTypesConcessions=new ArrayList<String>();
        //myBean = beanGenerator.create();

        // creation of beans
        printedDocument currentPrintedDoc=null;
        List<String> mrgs=new ArrayList<String>();
        BeanGenerator beanGenerator = new BeanGenerator();

        if(currentDoc instanceof qMarree) {

        Object myBean=null;
        beanGenerator.addProperty("date", Date.class);
        beanGenerator.addProperty("secteur", String.class);
            beanGenerator.addProperty("navire", String.class);
        for (qCapture vv : ((qMarree) currentDoc).getPages().get(0).getListJours().get(0).getCapturesDuMarree()) {
            if(vv.getEspeceTypee().getQespece()!=null) {
                beanGenerator.addProperty(StringUtils.uncapitalize((vv.getEspeceTypee().getQespece().getNomFr()+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")), Float.class);
                colsPage.add(StringUtils.uncapitalize((vv.getEspeceTypee().getQespece().getNomFr()+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")));
                colsHPage.add(vv.getEspeceTypee().getQespece().getNomFr());
            }
            else
            {
                beanGenerator.addProperty(StringUtils.uncapitalize(("Esp"+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")), Float.class);
                colsPage.add(StringUtils.uncapitalize("Esp"+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", ""));
                colsHPage.add("Esp"+vv.getEspeceTypee().getNumOrdre());
            }


        }
        beanGenerator.addProperty("totalCapturs", Float.class);
        beanGenerator.addProperty("totalCong", Float.class);
        beanGenerator.addProperty("nbrCaisse", Integer.class);

        Method setter, getter;


        for (qPageMarree pm : ((qMarree) currentDoc).getPages()) {
            currentPrintedDoc=new printedDocument();
            currentPrintedDoc.setCurrentDoc(currentDoc);
            currentPrintedDoc.setNumeroPage(pm.getNumeroPage());
            capturesPage=new ArrayList<Object>();

            for (qJourMere jm : pm.getListJours()) {

                myBean = beanGenerator.create();
                setter = myBean.getClass().getMethod("setDate", Date.class);
                setter.invoke(myBean, jm.getDateJour());
                setter = myBean.getClass().getMethod("setSecteur", String.class);
                setter.invoke(myBean, jm.getSecteur());
                setter = myBean.getClass().getMethod("setNavire", String.class);
                setter.invoke(myBean, jm.getNavire().getNomnav());

                setter = myBean.getClass().getMethod("setTotalCapturs", Float.class);
                setter.invoke(myBean, jm.getTotalCapturs());

                setter = myBean.getClass().getMethod("setTotalCong", Float.class);
                setter.invoke(myBean, jm.getTotalCong());

                setter = myBean.getClass().getMethod("setNbrCaisse", Integer.class);
                setter.invoke(myBean, jm.getNbrCaisse());


                for (qCapture vv : jm.getCapturesDuMarree()) {
                    if (vv.getEspeceTypee().getQespece() != null) {
                        setter = myBean.getClass().getMethod("set" + StringUtils.capitalize((vv.getEspeceTypee().getQespece().getNomFr()+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")), Float.class);
                        setter.invoke(myBean, vv.getQuantite());
                        getter = myBean.getClass().getMethod("get" + StringUtils.capitalize((vv.getEspeceTypee().getQespece().getNomFr()+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")));
                    }
                    else {
                        setter = myBean.getClass().getMethod("set" + StringUtils.capitalize(("Esp"+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")), Float.class);
                        setter.invoke(myBean, vv.getQuantite());
                        getter = myBean.getClass().getMethod("get" + StringUtils.capitalize("Esp"+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", ""));

                    }
                }
                capturesPage.add(myBean);

            }
            currentPrintedDoc.setPagesDocCaptures(capturesPage);
            currentPrintedDoc.setResultatsEntetesCol(colsHPage);
            currentPrintedDoc.setResultatsNamesCol(colsPage);

            if(currentDoc.getQconcession()!=null) {
            if(currentDoc.getQconcession().getRefConcession()!=null)   currentPrintedDoc.setRefConcession(currentDoc.getQconcession().getRefConcession());
                else currentPrintedDoc.setRefConcession("");
             if(currentDoc.getQconcession().getQconsignataire()!=null)   currentPrintedDoc.setNomConcessionnaire(currentDoc.getQconcession().getQconsignataire().getNomconsignataire());
             else currentPrintedDoc.setNomConcessionnaire("");
            }

            currentPrintedDoc.setSupport(currentDoc.getSupport().toString());
            if(currentDoc.getQuota()!=null) currentPrintedDoc.setQuota(currentDoc.getQuota().toString());
            else currentPrintedDoc.setQuota("");

            if(currentDoc.getDureeConcession()!=null) currentPrintedDoc.setDureeCobcession(currentDoc.getDureeConcession().toString());
            else currentPrintedDoc.setDureeCobcession("");
            if(currentDoc.getDateExpiration()!=null)
            currentPrintedDoc.setDateExpiration(currentDoc.getDateExpiration().toString());
            else
                currentPrintedDoc.setDateExpiration("");
            if(currentDoc.getLicenceImputation()!=null)
            currentPrintedDoc.setLicencePeche(currentDoc.getLicenceImputation().toString());
            else
                currentPrintedDoc.setLicencePeche("");
            if(currentDoc.getNomNavire()!=null)
            currentPrintedDoc.setNomNavire(currentDoc.getNomNavire());
            else  currentPrintedDoc.setNomNavire("");
           if(currentDoc.getImo()!=null)
            currentPrintedDoc.setImo(currentDoc.getImo());
            else currentPrintedDoc.setImo("");
            if(currentDoc.getGT()!=null)
            currentPrintedDoc.setGt(currentDoc.getGT());
            else currentPrintedDoc.setGt("");
            if(currentDoc.getNomCapitaine()!=null)
            currentPrintedDoc.setNomCapitaine(currentDoc.getNomCapitaine());
            else  currentPrintedDoc.setNomCapitaine("");

               for(qEnginPecheMar epm:((qMarree) currentDoc).getqEngins()) {
                  if(epm.getEnginMar().equals(enumEngin.Chalut)){
                      mrgs.add(((qMarree) currentDoc).getqEngins().get(0).getMaillage().toString());
                      choixEngins.add(((qMarree) currentDoc).getqEngins().get(0).isFlag());
                  }
                 if(epm.getEnginMar().equals(enumEngin.Casier)) {
                     mrgs.add(((qMarree) currentDoc).getqEngins().get(1).getMaillage().toString());
                     choixEngins.add(((qMarree) currentDoc).getqEngins().get(1).isFlag());
                 }
                 if(epm.getEnginMar().equals(enumEngin.Nasses)) {
                     mrgs.add(((qMarree) currentDoc).getqEngins().get(2).getMaillage().toString());
                     choixEngins.add(((qMarree) currentDoc).getqEngins().get(2).isFlag());
                 }
                 if(epm.getEnginMar().equals(enumEngin.Filet_tremail)) {
                     mrgs.add(((qMarree) currentDoc).getqEngins().get(3).getMaillage().toString());
                     choixEngins.add(((qMarree) currentDoc).getqEngins().get(3).isFlag());
                 }
                 if(epm.getEnginMar().equals(enumEngin.Turlutte)) {
                     mrgs.add(((qMarree) currentDoc).getqEngins().get(4).getMaillage().toString());
                     choixEngins.add(((qMarree) currentDoc).getqEngins().get(4).isFlag());
                 }

                }
            currentPrintedDoc.setMaillages(mrgs) ;//= new String[2][5];
            currentPrintedDoc.setChoixEngins(choixEngins); ;

            list.add(currentPrintedDoc);
            }

        }
        if(currentDoc instanceof qDebarquement) {
            Object myBean=null;
            beanGenerator.addProperty("date", Date.class);
            beanGenerator.addProperty("navire", String.class);
            for (qCapture vv : ((qDebarquement) currentDoc).getPages().get(0).getListJours().get(0).getDebarqDuJour()) {
                if(vv.getEspeceTypee().getQespece()!=null) {
                    beanGenerator.addProperty(StringUtils.uncapitalize((vv.getEspeceTypee().getQespece().getNomFr()+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")), Float.class);
                    colsPage.add(StringUtils.uncapitalize((vv.getEspeceTypee().getQespece().getNomFr()+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")));
                    colsHPage.add(vv.getEspeceTypee().getQespece().getNomFr());
                }
                else
                {
                    beanGenerator.addProperty(StringUtils.uncapitalize(("Esp"+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")), Float.class);
                    colsPage.add(StringUtils.uncapitalize("Esp"+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", ""));
                    colsHPage.add("Esp"+vv.getEspeceTypee().getNumOrdre());
                }


            }
            beanGenerator.addProperty("totalCapturs", Float.class);


            Method setter, getter;


            for (qPageDebarquement pdeb : ((qDebarquement) currentDoc).getPages()) {
                currentPrintedDoc=new printedDocument();
                currentPrintedDoc.setCurrentDoc(currentDoc);
                currentPrintedDoc.setNumeroPage(pdeb.getNumeroPage());
                capturesPage=new ArrayList<Object>();

                for (qJourDeb jdeb : pdeb.getListJours()) {

                    myBean = beanGenerator.create();
                    setter = myBean.getClass().getMethod("setDate", Date.class);
                    setter.invoke(myBean, jdeb.getDateJour());

                    setter = myBean.getClass().getMethod("setNavire", Date.class);
                    setter.invoke(myBean, jdeb.getNavire().getNomnav());

                    setter = myBean.getClass().getMethod("setTotalCapturs", Float.class);
                    setter.invoke(myBean, jdeb.getTotalCapturs());




                    for (qCapture vv : jdeb.getDebarqDuJour()) {
                        if (vv.getEspeceTypee().getQespece() != null) {
                            setter = myBean.getClass().getMethod("set" + StringUtils.capitalize((vv.getEspeceTypee().getQespece().getNomFr()+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")), Float.class);
                            setter.invoke(myBean, vv.getQuantite());
                            getter = myBean.getClass().getMethod("get" + StringUtils.capitalize((vv.getEspeceTypee().getQespece().getNomFr()+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")));
                        }
                        else {
                            setter = myBean.getClass().getMethod("set" + StringUtils.capitalize(("Esp"+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", "")), Float.class);
                            setter.invoke(myBean, vv.getQuantite());
                            getter = myBean.getClass().getMethod("get" + StringUtils.capitalize("Esp"+vv.getEspeceTypee().getNumOrdre()).replaceAll("\\s+", ""));

                        }
                    }
                    capturesPage.add(myBean);

                }
                currentPrintedDoc.setPagesDocCaptures(capturesPage);
                currentPrintedDoc.setResultatsEntetesCol(colsHPage);
                currentPrintedDoc.setResultatsNamesCol(colsPage);
                System.out.println("ent:"+currentPrintedDoc.getResultatsEntetesCol().size());
                if(currentDoc.getQconcession()!=null) {
                    if(currentDoc.getQconcession().getRefConcession()!=null)    currentPrintedDoc.setRefConcession(currentDoc.getQconcession().getRefConcession());
                    else currentPrintedDoc.setRefConcession("");
                    if(currentDoc.getQconcession().getQconsignataire()!=null)   currentPrintedDoc.setNomConcessionnaire(currentDoc.getQconcession().getQconsignataire().getNomconsignataire());
                    else currentPrintedDoc.setNomConcessionnaire("");
                }
if(((qDebarquement) currentDoc).getTypesConcession().size()!=0) {
//    String segPeche=null;
 //   String zonePeche=null;
    for(qTypeConcession tc:((qDebarquement) currentDoc).getTypesConcession()) {
        System.out.println("id :"+tc.getQtypeconcessionpk().toString());
       choisTypesConcessions.add(tc.getQtypeconcessionpk().toString());
    }
    currentPrintedDoc.setChoisTypesConcessions(choisTypesConcessions);
}
                currentPrintedDoc.setSupport(currentDoc.getSupport().toString());
                if(currentDoc.getQuota()!=null) currentPrintedDoc.setQuota(currentDoc.getQuota().toString());
                else currentPrintedDoc.setQuota("");

                if(currentDoc.getDureeConcession()!=null) currentPrintedDoc.setDureeCobcession(currentDoc.getDureeConcession().toString());
                else currentPrintedDoc.setDureeCobcession("");
                if(currentDoc.getDateExpiration()!=null)
                    currentPrintedDoc.setDateExpiration(currentDoc.getDateExpiration().toString());
                else
                    currentPrintedDoc.setDateExpiration("");
                if(currentDoc.getLicenceImputation()!=null)
                    currentPrintedDoc.setLicencePeche(currentDoc.getLicenceImputation().toString());
                else
                    currentPrintedDoc.setLicencePeche("");
                if(currentDoc.getNomNavire()!=null)
                    currentPrintedDoc.setNomNavire(currentDoc.getNomNavire());
                else  currentPrintedDoc.setNomNavire("");


                if(currentDoc.getNomCapitaine()!=null)
                    currentPrintedDoc.setNomCapitaine(currentDoc.getNomCapitaine());
                else  currentPrintedDoc.setNomCapitaine("");
                if(currentDoc.getLicenceImputation().getZone()!=null) {
                    currentPrintedDoc.setZonePeche(currentDoc.getLicenceImputation().getZone().getNom());
                          }
                else  currentPrintedDoc.setZonePeche("");
                    currentPrintedDoc.setSegPeche(currentDoc.getSegPeche());



                for(qEnginPecheDebar epd:((qDebarquement) currentDoc).getEngins()) {
                    if(epd.getEnginDeb().equals(enumEnginDeb.Pots)){
                        mrgs.add(((qDebarquement) currentDoc).getEngins().get(0).getMaillage().toString());
                        choixEngins.add(((qDebarquement) currentDoc).getEngins().get(0).isFlag());
                    }
                    if(epd.getEnginDeb().equals(enumEnginDeb.Casier)) {
                        mrgs.add(((qDebarquement) currentDoc).getEngins().get(1).getMaillage().toString());
                        choixEngins.add(((qDebarquement) currentDoc).getEngins().get(1).isFlag());
                    }
                    if(epd.getEnginDeb().equals(enumEnginDeb.Filet_tremail)) {
                        mrgs.add(((qDebarquement) currentDoc).getEngins().get(2).getMaillage().toString());
                        choixEngins.add(((qDebarquement) currentDoc).getEngins().get(2).isFlag());
                    }
                    if(epd.getEnginDeb().equals(enumEnginDeb.Filet_maillant)) {
                        mrgs.add(((qDebarquement) currentDoc).getEngins().get(3).getMaillage().toString());
                        choixEngins.add(((qDebarquement) currentDoc).getEngins().get(3).isFlag());
                    }
                    if(epd.getEnginDeb().equals(enumEnginDeb.Turlutte)) {
                        mrgs.add(((qDebarquement) currentDoc).getEngins().get(4).getMaillage().toString());
                        choixEngins.add(((qDebarquement) currentDoc).getEngins().get(4).isFlag());
                    }
                    if(epd.getEnginDeb().equals(enumEnginDeb.Ligne)) {
                        mrgs.add(((qDebarquement) currentDoc).getEngins().get(4).getMaillage().toString());
                        choixEngins.add(((qDebarquement) currentDoc).getEngins().get(4).isFlag());
                    }
                    if(epd.getEnginDeb().equals(enumEnginDeb.Palangre)) {
                        mrgs.add(((qDebarquement) currentDoc).getEngins().get(4).getMaillage().toString());
                        choixEngins.add(((qDebarquement) currentDoc).getEngins().get(4).isFlag());
                    }
                    if(epd.getEnginDeb().equals(enumEnginDeb.Filet_Enc_ST)) {
                        mrgs.add(((qDebarquement) currentDoc).getEngins().get(4).getMaillage().toString());
                        choixEngins.add(((qDebarquement) currentDoc).getEngins().get(4).isFlag());
                    }

                }
                currentPrintedDoc.setMaillages(mrgs) ;//= new String[2][5];
                currentPrintedDoc.setChoixEngins(choixEngins);

                list.add(currentPrintedDoc);
            }



        }
        if(currentDoc instanceof qTraitement) {

        }

return list;
    }

    @Override
    public Page<qDoc> findAllMatchedDocsBetweenDatesConcession(Date searchDateCapture1, Date searchDateCapture2,String nomNavie, List<choixTypeConcession> types,Integer page) {

        String currentnumimm=null;
        List<qTypeConcession> typesC=new ArrayList<qTypeConcession>();
        List<qDoc> retDocs=new ArrayList<>();
        List<qDoc> pgmarrees=null;
        List<qMarree> lstMarrees=new ArrayList<qMarree>();
        List<qDebarquement> lstDeb=new ArrayList<qDebarquement>();
        Set<qDoc> a = null;
     //   Set<qEspeceTypee> b = new TreeSet<qEspeceTypee>(oldEspTypees);

        List<qDoc> cleanedDocs=null;
        //  qNavireLegale seletetedNavire=navRepository.findLegalByName(searchBat);
    //    if(seletetedNavire==null) currentnumimm=null;
      // else currentnumimm=seletetedNavire.getNumimm();

        for(choixTypeConcession ct:types)
        {
         if(ct.getChoix()==true)           typesC.add(typeconcessionRepository.findOne(ct.getTypeConcession().getQtypeconcessionpk()));
        }

        for(qTypeConcession tcon:typesC) {

       if(tcon.getTypeDoc().equals(enumTypeDoc.Journal_Peche)) {
           retDocs.addAll(qdocRepository.findAllMatchedDocsBetweenDatesByConcessionM(searchDateCapture1,searchDateCapture2,nomNavie, tcon));
       }
            if(tcon.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {

                retDocs.addAll(qdocRepository.findAllMatchedDocsBetweenDatesByConcessionD(searchDateCapture1,searchDateCapture2,nomNavie, tcon));

            }

        }

//for(choixTypeConcession tc:types)
    //pgmarrees= qdocRepository.findAllMatchedDocsBetweenDatesByConcessionM(new PageRequest(0, 20),searchDateCapture1,searchDateCapture2, typesC);
    //   List<qDoc> pgmarrees= qdocRepository.findAllMatchedDocsBetweenDatesByConcessionM(searchDateCapture1,searchDateCapture2, typesC);
     //   List<qDoc> pgdebarq= qdocRepository.findAllMatchedDocsBetweenDatesByConcessionD(searchDateCapture1,searchDateCapture2, typesC);
     //   Page<qDoc> pgdocs= qdocRepository.findAllMatchedDocsBetweenDatesByConcession(new PageRequest(0, 20),searchDateCapture1,searchDateCapture2, typesC);
        a = new TreeSet<qDoc>(retDocs);
        cleanedDocs=new ArrayList<qDoc>(a);
        Page<qDoc> pgdocs= new PageImpl<qDoc>(cleanedDocs,new PageRequest(page,20),cleanedDocs.size());
        return pgdocs;
    }



    @Override
    public Page<qDoc> findAllMatchedDocsBetweenDates(Date searchDateCapture1, Date searchDateCapture2, String searchBat,Integer page) {
        String currentnumimm=null;
        qNavireLegale seletetedNavire=navRepository.findLegalByName(searchBat);
        if(seletetedNavire==null) currentnumimm=null;
        else currentnumimm=seletetedNavire.getNumimm();
        Page<qDoc> pgdocs= qdocRepository.findAllMatchedDocsBetweenDates(new PageRequest(page, 20),searchDateCapture1,searchDateCapture2, currentnumimm);
        return pgdocs;
    }

    @Override
    public String importerNationalites(MultipartFile file, String fullpatchname) {

        String  IdNation,codePays,descr,idEnc;
        qNation nationToBeCreated=new qNation();
        Integer k=0;
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
      //      FileReader reader = new FileReader(fullpatchname);
            InputStreamReader reader =   new InputStreamReader(new FileInputStream(fullpatchname), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    System.out.println(k);
                    String splitarray[] = line.split("\t");
                    IdNation=splitarray[0];

                    descr=splitarray[1];
                    idEnc=splitarray[2];
                    System.out.print(descr);
                    System.out.print("é");
                    qNation nation=qnationRepository.findOne(Integer.parseInt(IdNation));
                        nationToBeCreated.setIdNation(Integer.parseInt(IdNation));
                        nationToBeCreated.setDesignation(descr);
                        qAccordPeche rr=accordRepository.findOne(Integer.parseInt(idEnc));
                        nationToBeCreated.setAccordPeche(rr);
                        qnationRepository.save(nationToBeCreated);
                }

                k++;
            }

            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return null;
    }


    @Override
    public String importerTypesConcession(MultipartFile file, String fullpatchname) {


        String  TYPE_CONCESSION,	TypeConcessionPK,	designation,	prefixModel,	typeDoc,
        enumAncCategRessource,	TypePecheHautiriere,	TypePecheCotiere;
        Integer k=0;
        qTypeConcession currentConcession=null;
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
            FileReader reader = new FileReader(fullpatchname);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    System.out.println(k);

                    String splitarray[] = line.split("\t");
                    TYPE_CONCESSION=splitarray[0];
                    TypeConcessionPK=splitarray[1];
                    designation=splitarray[2];
                     prefixModel=splitarray[3];
                     typeDoc=splitarray[4];
                     enumAncCategRessource=splitarray[5];
                   TypePecheHautiriere=splitarray[6];
                    TypePecheCotiere=splitarray[7];
                   if(TYPE_CONCESSION.equals("TARTISANAL")) {
                       currentConcession=new qTypeConcessionArtisanal() ;
                       qPrefix prefcurrent=prefRepo.findOne(new qPrefixPK(prefixModel,enumTypeDoc.valueOf(typeDoc)));
                       currentConcession.setPrefixNum(prefixModel);
                       currentConcession.setDesignation(designation);
                       currentConcession.setQtypeconcessionpk(Integer.parseInt(TypeConcessionPK));
                       currentConcession.setTypeDoc(enumTypeDoc.valueOf(typeDoc));
                       currentConcession.setPrefix(prefcurrent);
                                }
                    if(TYPE_CONCESSION.equals("TCOTIER")) {
                        currentConcession=new qTypeConcessionCotiere() ;
                        qPrefix prefcurrent=prefRepo.findOne(new qPrefixPK(prefixModel,enumTypeDoc.valueOf(typeDoc)));
                        currentConcession.setPrefixNum(prefixModel);
                        currentConcession.setDesignation(designation);
                        currentConcession.setQtypeconcessionpk(Integer.parseInt(TypeConcessionPK));
                        currentConcession.setTypeDoc(enumTypeDoc.valueOf(typeDoc));
                        currentConcession.setPrefix(prefcurrent);
                        ((qTypeConcessionCotiere)currentConcession).setEnumTypePecheCotiere(enumTypePechCotiere.valueOf(TypePecheCotiere));

                                   }
                    if(TYPE_CONCESSION.equals("THAUTIRIERE")) {
                        currentConcession=new qTypeConcessionHautiriere();

                        qPrefix prefcurrent=prefRepo.findOne(new qPrefixPK(prefixModel,enumTypeDoc.valueOf(typeDoc)));
                        currentConcession.setPrefixNum(prefixModel);
                        currentConcession.setDesignation(designation);
                        currentConcession.setQtypeconcessionpk(Integer.parseInt(TypeConcessionPK));
                        currentConcession.setTypeDoc(enumTypeDoc.valueOf(typeDoc));
                        currentConcession.setPrefix(prefcurrent);
                        ((qTypeConcessionHautiriere)currentConcession).setEnumTypePecheHautiriere(enumTypePecheHautiriere.valueOf(TypePecheHautiriere));

                    }

                    typeconcessionRepository.save(currentConcession);

                }

                k++;
            }


            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return null;
    }
    @Override
    public String importerZones(MultipartFile file, String fullpatchname) {
        String  IdZone,nomZone;
        Integer k=0;
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
            FileReader reader = new FileReader(fullpatchname);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    System.out.println(k);

                    String splitarray[] = line.split("\t");

                    IdZone=splitarray[0];
                    nomZone=splitarray[1];
                    qZone currentZone=new qZone(Integer.parseInt(IdZone),nomZone);
                    zoneRepo.save(currentZone);

                }

                k++;
            }


            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return null;
    }

    @Override
    public String importerModels(MultipartFile file, String fullpatchname) {

        String prefixModel,typeDoc,	Especes,numOrdre,espTypeeType;
        qModelJP modelToBeCreated=new qModelJP();

        String codEsp,espDesign,espType;
        Integer k=0;
        List<qEspeceTypee> esptypess=null;
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
            FileReader reader = new FileReader(fullpatchname);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    System.out.println(k);
                    esptypess=new ArrayList<qEspeceTypee>();
                    String splitarray[] = line.split("\t");
                    prefixModel=splitarray[0];
                    typeDoc=splitarray[1];
                    Especes=splitarray[2];


//qModelJP modelCurrent=qmodelRepository.findOne(new qPrefixPK(prefixModel,enumTypeDoc.valueOf(typeDoc)));
                    qPrefix prefCurrent=prefRepo.findOne(new qPrefixPK(prefixModel,enumTypeDoc.valueOf(typeDoc)));
                    modelToBeCreated.setPrefix(prefixModel);
                    modelToBeCreated.setQprefix(prefCurrent);
                    modelToBeCreated.setTypeDoc(enumTypeDoc.valueOf(typeDoc));

                    String[] lineEspeces=Especes.split("/");
                    List<String> container =Arrays.asList(lineEspeces);
                    for(String esp:container) {
                        String[] espInfo=esp.split(",");
                        List<String> espFields =Arrays.asList(espInfo);
                        codEsp=espFields.get(0);
                        espDesign=espFields.get(1);
                        espType=espFields.get(2);
                        numOrdre=espFields.get(3);
                        espTypeeType=espFields.get(4);
                        qEspece currentEsp=espRepository.findOne(codEsp);

                        if(currentEsp==null) {currentEsp=new qEspece(codEsp,"",espDesign,Integer.parseInt(numOrdre));}
                        currentEsp.setNomFr(espDesign);
                    //    currentEsp.setNumOrdre(Integer.parseInt(numOrdre));
                        currentEsp=espRepository.save(currentEsp);


                      //  qEspeceTypee currentEspTypee=new qEspeceTypee(enumEspType.valueOf(espType),currentEsp,null);

                       qEspeceTypee currentEspTypee= esptypeeRepository.findOne(new qEspeceTypeePK(codEsp,enumEspType.valueOf(espType),Integer.valueOf(numOrdre),prefixModel,enumTypeEspTypee.valueOf(espTypeeType)));
                        if(currentEspTypee==null) {currentEspTypee=new qEspeceTypee(enumEspType.valueOf(espType),currentEsp,null,Integer.valueOf(numOrdre),prefixModel,enumTypeEspTypee.valueOf(espTypeeType));
                            }
                        esptypess.add(currentEspTypee);
                    }
                    modelToBeCreated.setEspecestypees(esptypess);
                    qmodelRepository.save(modelToBeCreated);
                    esptypess=new ArrayList<qEspeceTypee>();
                }

                k++;
            }


            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return null;
    }
    @Override
    public String importerCarnets(MultipartFile file, String fullpatchname,BindingResult bindingresult) {


        String  debutPage1,prefixNum,typeDoc,DJTA,NbrLignes,NbrPages,refConcession,NumimmNav,RefAgr;
        qCarnet currentCarnet=null;qNavireLegale currentNavLegale=null;qUsine currentUsine=null;
        Integer k=0;
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
            FileReader reader = new FileReader(fullpatchname);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    System.out.println(k);

                    String splitarray[] = line.split("\t");
                            debutPage1=splitarray[0];
                            prefixNum=splitarray[1];
                            typeDoc=splitarray[2];
                NbrPages=splitarray[3];

                            NumimmNav=splitarray[4];
                            RefAgr=splitarray[5];
                    qPrefix currentpref=prefRepo.findOne(new qPrefixPK(prefixNum,enumTypeDoc.valueOf(typeDoc)));

                   currentNavLegale=(qNavireLegale)navRepository.findOne(NumimmNav);
System.out.println(NumimmNav);
                        currentUsine=qusineRepository.findOne(RefAgr);
                            //new qPrefix(prefixNum,enumTypeDoc.valueOf(typeDoc),Integer.parseInt(NbrLignes),"");
                    currentCarnet=new qCarnet(currentpref,Long.parseLong(debutPage1),Integer.parseInt(NbrPages),currentNavLegale,currentUsine);

    //              attrValidator.validate(currentCarnet, bindingresult);
       //             if (!bindingresult.hasErrors()) {

                        qCarnet crn = qcarnetService.entrerDansLeSystem(currentCarnet);
                    System.out.println("worked");
                        qcarnetService.attribuerCarnetAuNavire(crn, currentNavLegale, null, currentUsine);

                //    } else {
              //          System.out.println("echec d'entrer le carnet");
              //      }
                }

                k++;
            }


            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return null;
    }
    @Override
    public String importerCategoriesPeche(MultipartFile file, String fullpatchname) {

        String  idcateg,typeSupport,typeconcessionLiee;
        Integer k=0;
        qCategRessource currentCategorie=null ;
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
            FileReader reader = new FileReader(fullpatchname);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    System.out.println(k);

                    String splitarray[] = line.split("\t");
                    idcateg=splitarray[0];
                    typeSupport=splitarray[1];
                    typeconcessionLiee=splitarray[2];
                    currentCategorie=new qCategRessource();
                    qTypeConcession currentType=typeconcessionRepository.findOne(Integer.parseInt(typeconcessionLiee));
                    currentCategorie.setIdtypeConcession(Integer.parseInt(idcateg));
                    currentCategorie.setTypeSupport(enumSupport.valueOf(typeSupport));
                    currentCategorie.setTypeconcessionConcernee(currentType);

                    qcqtRepository.save(currentCategorie);
                }

                k++;
            }


            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return null;
    }

    @Override
    public String importerPrefixes(MultipartFile file, String fullpatchname) {

        String  prefix,typedoc,information,nblifnesparcarnet;
        Integer k=0;
        qPrefix prefixToBeCreated=new qPrefix();
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
    //        FileReader reader = new FileReader(fullpatchname);
            InputStreamReader reader =   new InputStreamReader(new FileInputStream(fullpatchname), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    System.out.println(k);

                    String splitarray[] = line.split("\t");
                    prefix=splitarray[0];
                    typedoc=splitarray[1];
                    information=splitarray[2];
                    nblifnesparcarnet=splitarray[3];

                    qPrefix pref=prefRepo.findOne(new qPrefixPK(prefix,enumTypeDoc.valueOf(typedoc)));
                    prefixToBeCreated.setTypeDoc(enumTypeDoc.valueOf(typedoc));
                    prefixToBeCreated.setInformation(information);
                    prefixToBeCreated.setPrefix(prefix);
                    prefixToBeCreated.setNbrLigneCarnet(Integer.parseInt(nblifnesparcarnet));
                    prefRepo.save(prefixToBeCreated);
                           }

                k++;
            }


            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return null;
    }

    @Override
    public qDoc treatEspDynamic(qDoc currentDoc) {

        qEspece currentEsp=null;
        List<qEspeceDynamic> espds=null;
        String[] items=null;
        List<String> codes=null;
        if(currentDoc instanceof qMarree) // espdyn=((qMarree) currentDoc).getEspecesDyn();
        {
            for (qPageMarree pm : ((qMarree) currentDoc).getPages())
                if (pm.getEspecesDyn() != null) {
                    if (pm.getEspecesDyn().size() > 0) {
                        espds = pm.getEspecesDyn();
                        for (qEspeceDynamic espdy : espds) {
                            currentEsp = espRepository.findOne(espdy.getEspeceChoisie().getCodeEsp());
                            espdy.setEspeceChoisie(currentEsp);

                        }
                        pm.setEspecesDyn(espds);
                    }
        }
        }
        return currentDoc;
    }

    @Override
    public String[][] traiterConcessions(MultipartFile file, String fullpatchname) {
        String  prefix,typedoc,information,nblifnesparcarnet;
        Integer k=0;Long debut=null;
        Set<qModelConcessionExport> edd=new HashSet<qModelConcessionExport>();
        List<qConcession> concessions=new ArrayList<qConcession>();
        qConcession concess=null;qConsignataire consign=null;Long pagelong=null;
        String[][] value = null;
        String[][] npgs= null; qPrefixPK prefpk=null;
        Set<String> concSet=new HashSet<String>();
        Set<String> pgSet=new HashSet<String>();
        Set<qConcession> concessionsSet=new HashSet<qConcession>();
        Set<qCarnet> carnetsSet=new HashSet<qCarnet>();
        qPrefix prefixToBeCreated=new qPrefix();
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Read test file.
        BufferedReader bufferedReader = null;
        try {
            //  FileReader reader = new FileReader(fullpatchname);
            InputStreamReader reader =   new InputStreamReader(new FileInputStream(fullpatchname), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if(!first) {
                    k++;
                    System.out.println(k);
                    String splitarray[] = line.split("\t");
                    String numero=splitarray[0];
                    String seg=splitarray[1];
                    String typepeche=splitarray[2];
                    String nomnav=splitarray[3];
                    String Ref=splitarray[4];
                    String Nom=splitarray[5];
                    String Npage=splitarray[6];
                    String date	=splitarray[7];
                    String chinchard=splitarray[8];
                    String nomnadup=splitarray[9];
                    String esp1=splitarray[10];
                    String esp2=splitarray[11];
                    String esp3=splitarray[12];
                    String esp4=splitarray[13];
                    String esp5=splitarray[14];
                    String esp6=splitarray[15];
                    String esp7=splitarray[16];
                    String esp8=splitarray[17];
                    String esp9=splitarray[18];
                    String esp10=splitarray[19];
                    String esp11=splitarray[20];
                    String esp12=splitarray[21];
                    String esp13=splitarray[22];
                    qBateau currentBat=null;

                    pgSet.add(Npage);
                    if(!Npage.equals("")) {

                        System.out.println(nomnav);
                        currentBat=navService.getSuggNavire(nomnav).getContent().get(0);
                        System.out.println(Npage.substring(0, 2));
                        if (Npage.substring(0, 3).equals("PEL")) {
                            prefpk = new qPrefixPK("PE", enumTypeDoc.Journal_Peche);
                            pagelong = Long.parseLong(Npage.substring(3));
                        }
                        if (Npage.substring(0, 3).equals("DEM")) {
                            prefpk = new qPrefixPK("DEM", enumTypeDoc.Journal_Peche);
                            pagelong = Long.parseLong(Npage.substring(3));
                        }
                        if (Npage.substring(0, 4).equals("CRUS")) {
                            prefpk = new qPrefixPK("CRUST", enumTypeDoc.Journal_Peche);
                            pagelong = Long.parseLong(Npage.substring(4));
                        }
                        if (Npage.substring(0, 3).equals("CEP")) {
                            prefpk = new qPrefixPK("CEPH", enumTypeDoc.Journal_Peche);
                            pagelong = Long.parseLong(Npage.substring(3));
                        }
                        qPrefix currentPref = prefRepo.findOne(prefpk);
                        System.out.println("npage "+Npage);
                      //  pagelong = Long.parseLong(Npage.substring(4));
                        Long gg = pagelong;
                        Integer Count = 0;
                        Integer firstnumber=0;

                        String lastpart=null;String firstpart=null;
                        Integer lastnumber =null;
                        if(gg.toString().length()>2)
                        {
                            lastpart = gg.toString().substring(gg.toString().length() - 2);
                            firstpart = gg.toString().substring(0, gg.toString().length() - 2);
                            firstnumber = Integer.parseInt(firstpart);
                            System.out.println("firstpart "+firstpart);
                            lastnumber = Integer.parseInt(lastpart);

                            System.out.println("lastpart"+lastpart);
                            System.out.println("lastnumber"+lastnumber);
                            System.out.println("firstnumber"+firstnumber);
                            if(lastnumber==0) debut=Long.parseLong(firstpart.concat("51"));
                            else {
                                if(1<=lastnumber && lastnumber<=50) debut=Long.parseLong(firstpart.concat("01"));
                                else debut=Long.parseLong(firstpart.concat("51"));
                            }
                        }
                        else { firstpart=""; firstnumber = 0;lastpart=gg.toString();
                               lastnumber=Integer.parseInt(gg.toString());
                            if(1<=lastnumber && lastnumber<=50) debut=Long.parseLong(firstpart.concat("01"));
                            else debut=Long.parseLong(firstpart.concat("51"));
                                  }

                        System.out.println("debut :"+debut);

                        qCarnetPK currentCarnet = new qCarnetPK(currentPref.getPrefix(), debut, enumTypeDoc.Journal_Peche);
                        qCarnet currcarnett = new qCarnet(currentPref, debut, 50, (qNavireLegale) currentBat, null);
                        currcarnett.setNumeroDebutPage(debut);
                        currcarnett.setQnavire((qNavireLegale) currentBat);
                        currcarnett.setPrefixNumerotation(currentPref.getPrefix());
                        carnetsSet.add(currcarnett);
                    }
                    consign=new qConsignataire(Nom,null);
                    concess=new qConcession(Ref,consign,null,null,null,null,null);
                    if(!Nom.equals("") && !Ref.equals(""))   concessionsSet.add(concess);
               }

                k++;
            }
for(qConcession co:concessionsSet) {
    qConsignataire consignat=null; qConcession concessi=null;
   if(!co.getRefConcession().equals("")) {
       if(co.getQconsignataire().getNomConsignataire()!=null ) {
       //    qConsignataire ccc=new qConsignataire();
         //  ccc.setNomConsignataire(co.getRefConcession());
         //  System.out.println(ccc);
          //    consignat= consignataireService.create(ccc);
            //  co.setQconsignataire(consignat);
         // concessi= concessionService.create(co);
       }
   }


        concSet.add(co.getRefConcession());
   }
            npgs=new String[pgSet.size()][1];
            int i=0;
            for(String pg:pgSet) {
                npgs[i][0]=pg;
                i++;
            }
            int rownumber=0;
            value=new String[carnetsSet.size()][6];
            for(qCarnet cv:carnetsSet) {
            value[rownumber][0]=cv.getNumeroDebutPage().toString();
            value[rownumber][1]=cv.getPrefixNumerotation().toString();
            value[rownumber][2]=cv.getTypeDoc().toString();
                value[rownumber][3]=cv.getNbrPages().toString();
                value[rownumber][4]=cv.getQnavire().getNumimm().toString();
                value[rownumber][5]="NULL";
                rownumber=rownumber+1;
            }

            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return value; //concessions; //new ArrayList<qModelConcessionExport>(concessions);

    }


    @Override
    public String[][] genererDocumentsImport(MultipartFile file, String fullpatchname) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String  prefix,typedoc,information,nblifnesparcarnet;
        List<qDoc> documents= new ArrayList<qDoc>();
        List<qEspeceDynamic> especesDyn=null;
        qPageMarree currentp=null;
        List<qJourMere> currentJours= null;
        List<qPageMarree> currentPagesMaree= null;
        Integer compteurLignes=0;
        qModelJP currentModel=null;
        Long currentPNL=null;
        String currentPNS=null;
        Integer i=0;
        qJourMere currJM=new qJourMere();
        qDoc currentDocument=null;
        Integer k=0;Long debut=null;
        Integer lastnumber = null;
        String lastpart = null;
        String firstpart = null;
        Integer firstnumber = 0;
        Date currentDateJour=null;
        qPrefix currentPref=null;
        Set<qModelConcessionExport> edd=new HashSet<qModelConcessionExport>();
        List<qConcession> concessions=new ArrayList<qConcession>();
        qConcession concess=null;qConsignataire consign=null;Long pagelong=null;
        String[][] value = null;
        String[][] npgs= null; qPrefixPK prefpk=null;
        Set<String> concSet=new HashSet<String>();
        Set<String> pgSet=new HashSet<String>();
        Set<qConcession> concessionsSet=new HashSet<qConcession>();
        Set<qCarnet> carnetsSet=new HashSet<qCarnet>();
        qPrefix prefixToBeCreated=new qPrefix();
        try {
            file.transferTo(new File(fullpatchname));
        } catch (IllegalStateException e) {
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Boolean fl=true;
        SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yyyy");

        Date dateDepart = null;
        // Read test file.
        BufferedReader bufferedReader = null;
        try {
            //  FileReader reader = new FileReader(fullpatchname);
            InputStreamReader reader =   new InputStreamReader(new FileInputStream(fullpatchname), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(reader);
            String next, line = bufferedReader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = bufferedReader.readLine()) == null);
                if (!first) {
                    String splitarray[] = line.split("\t");
                    String numero = splitarray[0];
                    String seg = splitarray[1];
                    String typepeche = splitarray[2];
                    String nomnav = splitarray[3];
                    String Ref = splitarray[4];
                    String Nom = splitarray[5];
                    String Npage = splitarray[6];
                    String datedep = splitarray[7];
                    String deuxNomNav = splitarray[8];
                    String secteur = splitarray[9];

                    String esp1 = splitarray[10];
                    String esp2 = splitarray[11];
                    String esp3 = splitarray[12];
                    String esp4 = splitarray[13];
                    String esp5 = splitarray[14];
                    String esp6 = splitarray[15];
                    String esp7 = splitarray[16];
                    String esp8 = splitarray[17];
                    String esp9 = splitarray[18];
                    String esp10 = splitarray[19];
                    String esp11 = splitarray[20];
                    String esp12 = splitarray[21];
                    String esp13 = splitarray[22];
                    String esp14 = splitarray[23];
                    String esp15 = splitarray[24];
                    String esp16 = splitarray[25];
                    String esp17 = splitarray[26];
                    System.out.println(numero);

                    Float[] currentLineQuantities = new Float[17];
                    currentLineQuantities[0]=Float.parseFloat(esp1);
                    currentLineQuantities[1]=Float.parseFloat(esp2);
                    currentLineQuantities[2]=Float.parseFloat(esp3);
                    currentLineQuantities[3]=Float.parseFloat(esp4);
                    currentLineQuantities[4]=Float.parseFloat(esp5);
                    currentLineQuantities[5]=Float.parseFloat(esp6);
                    currentLineQuantities[6]=Float.parseFloat(esp7);
                    currentLineQuantities[7]=Float.parseFloat(esp8);
                    currentLineQuantities[8]=Float.parseFloat(esp9);
                    currentLineQuantities[9]=Float.parseFloat(esp10);
                    currentLineQuantities[10]=Float.parseFloat(esp11);
                    currentLineQuantities[11]=Float.parseFloat(esp12);
                    currentLineQuantities[12]=Float.parseFloat(esp13);
                    currentLineQuantities[13]=Float.parseFloat(esp14);
                    currentLineQuantities[14]=Float.parseFloat(esp15);
                    currentLineQuantities[15]=Float.parseFloat(esp16);
                    currentLineQuantities[16]=Float.parseFloat(esp17);
                    qBateau currentBat = null;

                    Integer Count = 0;
                    firstnumber = 0;
                    pgSet.add(Npage);
                    if (!Npage.equals("")) { // debut du maree
                        currentDocument = new qMarree();
                        currentPagesMaree=new ArrayList<qPageMarree>();
                        currentJours=new ArrayList<qJourMere>();
                        currentBat = navService.getSuggNavire(nomnav).getContent().get(0);
                        currentDocument.setEnumtypedoc(enumTypeDoc.Journal_Peche);
                        currentDocument.setQnavire((qNavireLegale) currentBat);
                        currentDocument.setTypePeche(enumJP.Hautirere);
                        System.out.println("first blok");
                        System.out.println((qNavireLegale) currentBat);
                        //currentDocument.setQnavire((qNavireLegale) currentBat);
                        compteurLignes=1;
                        System.out.println("debut de marree:"+compteurLignes);
                        i = 0;// first line.
                        System.out.println(nomnav);
                        if (Npage.substring(0, 3).equals("PEL")) {
                            prefpk = new qPrefixPK("PE", enumTypeDoc.Journal_Peche);
                            pagelong = Long.parseLong(Npage.substring(3));
                        }
                        if (Npage.substring(0, 3).equals("DEM")) {
                            prefpk = new qPrefixPK("DEM", enumTypeDoc.Journal_Peche);
                            pagelong = Long.parseLong(Npage.substring(3));
                        }
                        if (Npage.substring(0, 4).equals("CRUS")) {
                            prefpk = new qPrefixPK("CRUST", enumTypeDoc.Journal_Peche);
                            pagelong = Long.parseLong(Npage.substring(4));
                        }
                        if (Npage.substring(0, 3).equals("CEP")) {
                            prefpk = new qPrefixPK("CEPH", enumTypeDoc.Journal_Peche);
                            pagelong = Long.parseLong(Npage.substring(3));
                        }

                        currentPref = prefRepo.findOne(prefpk);
                        System.out.println("npage "+Npage);
                        //  pagelong = Long.parseLong(Npage.substring(4));
                        Long gg = pagelong;




                        if(gg.toString().length()>2)
                        {
                            lastpart = gg.toString().substring(gg.toString().length() - 2);
                            firstpart = gg.toString().substring(0, gg.toString().length() - 2);
                            firstnumber = Integer.parseInt(firstpart);
                            System.out.println("firstpart "+firstpart);
                            lastnumber = Integer.parseInt(lastpart);

                            System.out.println("lastpart"+lastpart);
                            System.out.println("lastnumber"+lastnumber);
                            System.out.println("firstnumber"+firstnumber);
                            if(lastnumber==0) debut=Long.parseLong(firstpart.concat("51"));
                            else {
                                if(1<=lastnumber && lastnumber<=50) debut=Long.parseLong(firstpart.concat("01"));
                                else debut=Long.parseLong(firstpart.concat("51"));
                            }
                        }
                        else { firstpart=""; firstnumber = 0;lastpart=gg.toString();
                            lastnumber=Integer.parseInt(gg.toString());
                            if(1<=lastnumber && lastnumber<=50) debut=Long.parseLong(firstpart.concat("01"));
                            else debut=Long.parseLong(firstpart.concat("51"));
                              }

                      gg = pagelong;

                        currentDocument.setQprefix(currentPref);
                        currentDocument.setPrefix(currentPref.getPrefix());
                        try {
                            dateDepart = sdfmt1.parse(datedep);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        currentDocument.setDepart(dateDepart);
                      if(firstnumber!=0) {
                          currentPNL = Long.parseLong(firstnumber.toString().concat(lastpart).toString());
                          currentPNS = currentPref.getPrefix().concat(firstnumber.toString().concat(lastpart).toString());
                      }
                        else {
                          currentPNL = Long.parseLong(firstpart.toString().concat(lastpart).toString());
                          currentPNS = currentPref.getPrefix().concat(firstpart.toString().concat(lastpart).toString());

                      }
                        qCarnetPK currentCarnet = new qCarnetPK(currentPref.getPrefix(), debut, enumTypeDoc.Journal_Peche);
                        qCarnet currcarnett = new qCarnet(currentPref, debut, 50, (qNavireLegale) currentBat, null);
                        currcarnett.setNumeroDebutPage(debut);
                        currcarnett.setQnavire((qNavireLegale) currentBat);
                        currcarnett.setPrefixNumerotation(currentPref.getPrefix());
                        carnetsSet.add(currcarnett);
                        currentModel = qmodelRepository.findOne(prefpk);

                        currJM=new qJourMere();

                        List<qCapture> captures = new ArrayList<qCapture>();
                        // Date dateJourCourant = documentCree.getDepart();
                        float tot = 0;
                        especesDyn = qmodelService.getEspecestypeesDyn(currentPNS, enumTypeDoc.Journal_Peche, currentModel);

                        for (qEspeceDynamic dn : especesDyn) {
                            dn.setPagemaree(currentp);
                        }
                        i=0;
                        for (qEspeceTypee currespt : currentModel.getEspecestypees()) {
                            qCapture qcapture=null;
                            if (currentPref.getPrefix().equals("DEM")) {

                                if(currespt.getNumOrdre()==15) {
                                    qcapture = new qCapture(compteurLignes, currentPNS, currentDocument, currespt, currentLineQuantities[14] +  currentLineQuantities[15] , currespt.getNumOrdre(), currJM, null);
                                }
                                if(currespt.getNumOrdre()==16) {
                                    qcapture = new qCapture(compteurLignes, currentPNS, currentDocument, currespt, currentLineQuantities[16], currespt.getNumOrdre(), currJM, null);
                                }
                               else {
                                    qcapture = new qCapture(compteurLignes, currentPNS, currentDocument, currespt, currentLineQuantities[currespt.getNumOrdre()-1], currespt.getNumOrdre(), currJM, null);
                                }
                             }
                            else {
                                qcapture = new qCapture(compteurLignes, currentPNS, currentDocument, currespt, currentLineQuantities[currespt.getNumOrdre()-1], currespt.getNumOrdre(), currJM, null);
                            }
                            System.out.println(qcapture);
                            tot = tot + currentLineQuantities[i];
                            qcapture.setDateJour(dateDepart);
                            qcapture.setEsptype(currespt.getEnumesptype());
                            captures.add(qcapture);
                        i++;
                        }
                        currentDateJour=dateDepart;

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(currentDateJour);
                        //  cal.add(Calendar.DATE, 1);
                        currentDateJour = cal.getTime();
                        currJM.setDatejourMere(currentDateJour);
                        currJM.setCapturesDuMarree(captures);
                        currJM.setTotalCapturs(tot);
                        currJM.setTotalCong(tot);
                        currJM.setNumPage(currentPNS);
                        currentJours.add(currJM);
                        currentp = new qPageMarree();
                        currentp.setNumeroPage(currentPNS);
                        currentp.setNumeroOrdrePage(currentPNL);
                    } else {
                     if(secteur.equals("") && deuxNomNav.equals("") && datedep.equals("")){
                         compteurLignes=0;
                        if (currentJours.size() > 0) {
                            currentp.setListJours(currentJours);
                            currentPagesMaree.add(currentp);
                                         }
                         ((qMarree) currentDocument).setPages(currentPagesMaree);
                         ((qMarree) currentDocument).setRetour(currentDateJour);
                        documents.add(currentDocument);
                        System.out.println("secteur deuxnomnv dep sont vides"+compteurLignes);
                    }
                        else {    // les jours du maree
                         float totte=0f;
                         for(float cl:currentLineQuantities) totte=totte+cl;
if(totte!=0){
                         // sequence du marree
                        i = 0;
                        //        if (!deuxNomNav.equals("") || !secteur.equals("") || !numero.equals("")) {
                         //compteurLignes++;
                         currJM = new qJourMere();
                         List<qCapture> captures = new ArrayList<qCapture>();
                         currJM.setSecteur(secteur);


                         currJM.setNumPage(currentPNS);
                         // Date dateJourCourant = documentCree.getDepart();

                         float tot = 0;
                         for (qEspeceTypee currespt : currentModel.getEspecestypees()) {
                             qCapture qcapture=null;

                             if (currentPref.getPrefix().equals("DEM")) {

                                 if(currespt.getNumOrdre()==15) {
                                         qcapture = new qCapture(compteurLignes, currentPNS, currentDocument, currespt, currentLineQuantities[14]+
                                         currentLineQuantities[15] , currespt.getNumOrdre(), currJM, null);
                                 }
                                 if(currespt.getNumOrdre()==16) {
                                     qcapture = new qCapture(compteurLignes, currentPNS, currentDocument, currespt, currentLineQuantities[16], currespt.getNumOrdre(), currJM, null);
                                 }
                                 else {
                                     qcapture = new qCapture(compteurLignes, currentPNS, currentDocument, currespt, currentLineQuantities[currespt.getNumOrdre()-1], currespt.getNumOrdre(), currJM, null);
                                     }
                             }
                             else {
                                     qcapture = new qCapture(compteurLignes, currentPNS, currentDocument, currespt, currentLineQuantities[currespt.getNumOrdre()-1], currespt.getNumOrdre(), currJM, null);

                                  }

                             tot = tot + currentLineQuantities[i];
                             qcapture.setDateJour(dateDepart);

                             qcapture.setEsptype(currespt.getEnumesptype());
                             captures.add(qcapture);
                             i = i++;
                         }


                         Calendar cal = Calendar.getInstance();
                         cal.setTime(currentDateJour);
                         cal.add(Calendar.DATE, 1);
                         currentDateJour = cal.getTime();
                         currJM.setDatejourMere(currentDateJour);
                         currJM.setCapturesDuMarree(captures);
                         currJM.setTotalCapturs(tot);
                         currJM.setTotalCong(tot);
                         currentJours.add(currJM);
                         compteurLignes++;

                        if (compteurLignes == 5) { // la page est pleine;enregister l actuelle et ouvrire la nouvelle
                            compteurLignes = 0;
                            currentp.setListJours(currentJours);
                            currentPagesMaree.add(currentp);

                            currentp = new qPageMarree();
                            currentJours = new ArrayList<qJourMere>();
                            currentPNL =currentPNL + 1L;
                            currentPNS = currentPref.getPrefix().toString().concat(Long.toString(currentPNL));

                            currentp.setNumeroPage(currentPNS);
                            currentp.setNumeroOrdrePage(currentPNL);

                        } //else currentPNS = currentPref.toString().concat(Long.toString(currentPNL));



                        consign = new qConsignataire(Nom, null);
                        concess = new qConcession(Ref, consign, null, null, null, null, null);
                        if (!Nom.equals("") && !Ref.equals("")) concessionsSet.add(concess);

                    }
                    }
                    }


                }
                for (qConcession co : concessionsSet) {
                    qConsignataire consignat = null;
                    qConcession concessi = null;
                    if (!co.getRefConcession().equals("")) {
                        if (co.getQconsignataire().getNomConsignataire() != null) {
                            //    qConsignataire ccc=new qConsignataire();
                            //  ccc.setNomConsignataire(co.getRefConcession());
                            //  System.out.println(ccc);
                            //    consignat= consignataireService.create(ccc);
                            //  co.setQconsignataire(consignat);
                            // concessi= concessionService.create(co);
                        }
                    }


                    concSet.add(co.getRefConcession());
                }
            }

            npgs=new String[pgSet.size()][1];
             i=0;
            for(String pg:pgSet) {
                npgs[i][0]=pg;
                i++;
            }
            qPrefix currpr=prefRepo.findOne(new qPrefixPK("CRUST",enumTypeDoc.Journal_Peche));
            System.out.println("document size is : "+documents.size());
            int rownumber=0;
            String typePec=null;
            Integer nbrjour=0;
            for(qDoc mr:documents) {
                for (qPageMarree pm : ((qMarree) mr).getPages()) {
                   nbrjour=nbrjour+pm.getListJours().size();
                }
            }
            System.out.print(nbrjour+1);
            value=new String[nbrjour+1+documents.size()][35];

            value[0][0]="TypeDoc";
            value[0][1]="TypeJP";
            value[0][2]="Depart";
            value[0][3]="DateRetour";
            value[0][4]="Chalut";
            value[0][5]="Casier";
            value[0][6]="Nasse";
            value[0][7]="Pots";
            value[0][8]="tremail tremail";
            value[0][9]="Turlutte";
            value[0][10]="qseqdebut";
            value[0][11]="qseqfin";
            value[0][12]="Date Jour";
            value[0][13]="NPage";
            value[0][14]="Esp1";
            value[0][15]="Esp2";
            value[0][16]="Esp3";
            value[0][17]="Esp4";
            value[0][18]="Esp5";
            value[0][19]="Esp6";
            value[0][20]="Esp7";
            value[0][21]="Esp8";
            value[0][22]="Esp9";
            value[0][23]="Esp10";
            value[0][24]="Esp11";
            value[0][25]="Esp12";
            value[0][26]="Esp13";
            value[0][27]="Esp14";
            value[0][28]="Esp15";
            value[0][29]="Esp16";
            value[0][30]="PREFIXE";

            int u=0;
            rownumber=1;
            Date dateCJ=null,dateR=null,date=null;
            for(qDoc mr:documents) {
                u=0;
                if(mr.getTypePeche().equals(enumJP.Hautirere)) typePec="HAUTIRIERE";
                if(mr.getTypePeche().equals(enumJP.Cotier)) typePec="COTIERE";

                System.out.println("size is : " + ((qMarree) mr).getPages().size());
                for (qPageMarree pm : ((qMarree) mr).getPages()) {
                    for (qJourMere currjm : pm.getListJours()) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(mr.getDepart());

                        date = cal.getTime();
                        cal.setTime(mr.getRetour());
                        dateR = cal.getTime();
                        cal.setTime(currjm.getDatejourMere());
                        dateCJ = cal.getTime();

                        System.out.println(rownumber);
                        if (u == 0) {
                            value[rownumber][0] = "MARREE";
                            value[rownumber][1] = typePec;
                            value[rownumber][2] = sdfmt1.format(date);
                            value[rownumber][3] = sdfmt1.format(dateR);
                            value[rownumber][4] = "false";

                            value[rownumber][5] = "false";
                            value[rownumber][6] = "false";
                            value[rownumber][7] = "false";
                            value[rownumber][8] = "false";
                            value[rownumber][9] = "false";
                            value[rownumber][10] = ((qMarree) mr).getPages().get(0).getNumeroPage();
                            value[rownumber][11] = ((qMarree) mr).getPages().get(((qMarree) mr).getPages().size() - 1).getNumeroPage();
                            value[rownumber][12] = sdfmt1.format(dateCJ);
                            value[rownumber][13] = currjm.getNumPage();
                            value[rownumber][14] = currjm.getCapturesDuMarree().get(0).getQuantite().toString();
                            value[rownumber][15] = currjm.getCapturesDuMarree().get(1).getQuantite().toString();
                            value[rownumber][16] = currjm.getCapturesDuMarree().get(2).getQuantite().toString();
                            value[rownumber][17] = currjm.getCapturesDuMarree().get(3).getQuantite().toString();
                            value[rownumber][18] = currjm.getCapturesDuMarree().get(4).getQuantite().toString();
                            value[rownumber][19] = currjm.getCapturesDuMarree().get(5).getQuantite().toString();
                            value[rownumber][20] = currjm.getCapturesDuMarree().get(6).getQuantite().toString();
                            value[rownumber][21] = currjm.getCapturesDuMarree().get(7).getQuantite().toString();
                            value[rownumber][22] = currjm.getCapturesDuMarree().get(8).getQuantite().toString();
                            value[rownumber][23] = currjm.getCapturesDuMarree().get(9).getQuantite().toString();
                            value[rownumber][24] = currjm.getCapturesDuMarree().get(10).getQuantite().toString();
if(mr.getPrefix().equals("CRUST")) {
    value[rownumber][25] = "0";
    value[rownumber][26] = "0";
    value[rownumber][28] = "0";
    value[rownumber][29] = "0";
}
                            else {
    value[rownumber][25] = currjm.getCapturesDuMarree().get(11).getQuantite().toString();
    value[rownumber][26] = currjm.getCapturesDuMarree().get(12).getQuantite().toString();
    value[rownumber][27] = currjm.getCapturesDuMarree().get(13).getQuantite().toString();
    value[rownumber][28] = currjm.getCapturesDuMarree().get(14).getQuantite().toString();
    value[rownumber][29] = currjm.getCapturesDuMarree().get(15).getQuantite().toString();
}

                            value[rownumber][30] = mr.getPrefix();
                            u= u + 1;
                        }
                        else {
                            value[rownumber][0] = "V";
                            value[rownumber][1] ="V";
                            value[rownumber][2] = "V";
                            value[rownumber][3] =  "V";
                            value[rownumber][4] = "V";

                            value[rownumber][5] = "V";
                            value[rownumber][6] ="V";
                            value[rownumber][7] ="V";
                            value[rownumber][8] = "V";
                            value[rownumber][9] = "V";
                            value[rownumber][10] = "V";
                            value[rownumber][11] = "V";
                            value[rownumber][12] =  sdfmt1.format(dateCJ);
                            value[rownumber][13] =currjm.getNumPage();
                            value[rownumber][14] = currjm.getCapturesDuMarree().get(0).getQuantite().toString();
                            value[rownumber][15] = currjm.getCapturesDuMarree().get(1).getQuantite().toString();
                            value[rownumber][16] = currjm.getCapturesDuMarree().get(2).getQuantite().toString();
                            value[rownumber][17] = currjm.getCapturesDuMarree().get(3).getQuantite().toString();
                            value[rownumber][18] = currjm.getCapturesDuMarree().get(4).getQuantite().toString();
                            value[rownumber][19] = currjm.getCapturesDuMarree().get(5).getQuantite().toString();
                            value[rownumber][20] = currjm.getCapturesDuMarree().get(6).getQuantite().toString();
                            value[rownumber][21] =currjm.getCapturesDuMarree().get(7).getQuantite().toString();
                            value[rownumber][22] =currjm.getCapturesDuMarree().get(8).getQuantite().toString();
                            value[rownumber][23] = currjm.getCapturesDuMarree().get(9).getQuantite().toString();
                            value[rownumber][24] = currjm.getCapturesDuMarree().get(10).getQuantite().toString();

                            if(mr.getPrefix().equals("CRUST")) {
                                value[rownumber][25] = "0";
                                value[rownumber][26] = "0";
                                value[rownumber][28] = "0";
                                value[rownumber][29] = "0";
                            }
                            else {
                                value[rownumber][25] = currjm.getCapturesDuMarree().get(11).getQuantite().toString();
                                value[rownumber][26] = currjm.getCapturesDuMarree().get(12).getQuantite().toString();
                                value[rownumber][27] = currjm.getCapturesDuMarree().get(13).getQuantite().toString();
                                value[rownumber][28] = currjm.getCapturesDuMarree().get(14).getQuantite().toString();
                                value[rownumber][29] = currjm.getCapturesDuMarree().get(15).getQuantite().toString();
                            }



                            value[rownumber][30] = mr.getPrefix();
                        }
                        rownumber=rownumber+1;
                    }
                }

                value[rownumber][0] = "Fin";
                value[rownumber][1] ="Fin";
                value[rownumber][2] = "Fin";
                value[rownumber][3] =  "Fin";
                value[rownumber][4] = "Fin";

                value[rownumber][5] = "Fin";
                value[rownumber][6] ="Fin";
                value[rownumber][7] ="Fin";
                value[rownumber][8] = "Fin";
                value[rownumber][9] = "Fin";
                value[rownumber][10] = "Fin";
                value[rownumber][11] = "Fin";
                value[rownumber][12] =  "Fin";
                value[rownumber][13] = "Fin";
                value[rownumber][14] = "Fin";
                value[rownumber][15] = "Fin";
                value[rownumber][16] = "Fin";
                value[rownumber][17] = "Fin";
                value[rownumber][18] = "Fin";
                value[rownumber][19] = "Fin";
                value[rownumber][20] = "Fin";
                value[rownumber][21] ="Fin";
                value[rownumber][22] ="Fin";

             value[rownumber][23] = "Fin";
            value[rownumber][24] = "Fin";
            value[rownumber][25] = "Fin";
           value[rownumber][26] = "Fin";
           value[rownumber][27] = "Fin";
            value[rownumber][28] = "Fin";
            value[rownumber][29] = "Fin";

             value[rownumber][30] = "Fin";
                rownumber=rownumber+1;
            }

            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader!= null) try { bufferedReader.close(); } catch (IOException logOrIgnore) {}
        }
        return value; //concessions; //new ArrayList<qModelConcessionExport>(concessions);

    }
}

