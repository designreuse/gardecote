package com.gardecote.business.service;

import com.gardecote.entities.*;
import com.gardecote.web.*;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Expression;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 08/11/2016.
 */
public interface qDocService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qDoc findById(qDocPK idact) ;
    boolean checkPrefix(qPrefix deletedPrefix);
   Page<qDoc> findAllMatcheds(String numeroPage);
   qDoc treatEspDynamic(qDoc currentDoc);
    /**
     * Loads all entities.
     * @return all entities
     */
    List<qLic> retLicences(qSeq seqActive1,enumTypeDoc typeDoc);
    Page<qDoc> findAll(int p, int size);
    String importerDocuments(MultipartFile file, String fullpatchname);
 String importerMarrees(MultipartFile file, String fullpatchname);
 String importerNationalites(MultipartFile file, String fullpatchname);
 String importerPrefixes(MultipartFile file, String fullpatchname);
 String importerTypesConcession(MultipartFile file, String fullpatchname);
 String importerZones(MultipartFile file, String fullpatchname);
 String importerModels(MultipartFile file, String fullpatchname);
 String importerCarnets(MultipartFile file, String fullpatchname,BindingResult bindingresult);
 String importerCategoriesPeche(MultipartFile file, String fullpatchname);

    String importerTraitements(MultipartFile file, String fullpatchname);
  //  List<qDoc> findAll(Pageable pageable);

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qDoc save(qDoc entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qDoc update(qDoc entity);


    public List<qJourDeb> jourDebDejaUtilisee(String numimm,Date datedep,Date dateret);
    public List<qJourMere> jourMereDejaUtilisee(String numimm,Date datedep,Date dateret);
    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qDoc create(qDoc entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(qDocPK idCapture);
    // cherecher cette sequence et si l on touve ,on retourne juste  l ancien document associee avec cette sequence.
    // s il y en a pas une sequence exacte , on recherche tous les documents associes au toutes les pages de cette sequence
    // si les docs==null on cree un nouveau document
    // si les docs n sont pas null, retourner la liste de ces documents avec leurs sequences et demander de l administrateur de supprimer un document si necessaire
    qDoc verifierAncienDoc(qSeqPK sequencepk);
    Page<qDoc> findAllMatchedDocs(Date searchDateCapture,String searchBat);
    Page<qDoc> findAllMatchedDocsBetweenDates(Date searchDateCapture1,Date searchDateCapture2,String searchBat);
    Page<qDoc> findAllMatchedDocsBetweenDatesTr(Date searchDateCapture1,Date searchDateCapture2,String searchBat);
    Page<qDoc> findAllMatchedDocsBetweenDatesConcession(Date searchDateCapture1, Date searchDateCapture2, List<choixTypeConcession> types);

    qDebarquement creerDebarquement(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc);
    qDebarquement creerDebarquementByImport(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc,List<Boolean> enginsDeb,List<Boolean> categDebs,Map<String,Map<Integer,List<Float>>> quantities);
    qMarree creerMarree(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc);
    qMarree creerMarreesByImport(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc,List<Boolean> enginsMarree,enumJP typeJP,Map<String,Map<Integer,List<Float>>> quantities,String typeM);
        //   qSeq seqActive=qseqRepository.findOne(seqActive1.getSeqPK());

    qTraitement creerTraitement(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc);
    qTraitement creerTraitementByImport(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc,List<Boolean> enginsMarree,enumJP typeJP,Map<String,Map<Integer,List<Double>>> quantities);
        //   qSeq seqActive=qseqRepository.findOne(seqActive1.getSeqPK());
        public Expression<Class> rendreTypeM(enumJP jp);
        qDoc creerNouvDoc(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc);
    qMarreeAnnexe  creerNouvAnnexe(Date dateDepart,qMarree qCurrentMaree,qSeqPK spk,enumTypeDoc typeDoc);
    qMarreeAnnexe creerAnnexe(Date dateDepart,qMarree qCurrentMaree,qSeqPK spk,enumTypeDoc typeDoc);
    boolean checkNombreJoursMoinsNombreLignes(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc);
    qDoc checkIfDupDocExist(Date dateDepart,Date dateRetour,qSeq seqActive1,enumTypeDoc typeDoc);
    qMarreeAnnexe checkIfDupAnnexeExist(Date dateDepart,qSeqPK spk, enumTypeDoc typeDoc);

    List<qMarreeAnnexe> checkIfDupPagesAnnexeExist(Date dateDepart,qSeqPK spk, enumTypeDoc typeDoc);
    List<qDoc> checkIfDupPagesExist(Date dateDepart,Date dateRetour,qSeq seqActive1,enumTypeDoc typeDoc);

    List<qDoc> checkIfDupJoursExist(Date dateDepart,Date dateRetour,qSeq seqActive1,enumTypeDoc typeDoc);
    qModelJP checkIfModelExist(Date dateDepart,Date dateRetour,qSeq seqActive1,enumTypeDoc typeDoc);
    boolean checkIfValidSeq(qSeq seqActive1);
    boolean checkIfNavAnnexIsSameAsNavPrincipal(Date dateDepart, qSeqPK spk, qMarree currentMaree,enumTypeDoc typeDoc);
    resultatsCapturesByConcession findAllMatchedDocsBetweenDatesConcessionCap(Date searchDateCapture1, Date searchDateCapture2, List<choixTypeConcession> types, final @RequestParam(name="PARAMETER_TYPE") String PARAMETER_TYPE,qTaskProgressBar task)  throws IOException, ServletException, Exception;
    List<printedDocument> printDocument(qDoc currentDoc, String PARAMETER_TYPE, HttpServletRequest request, HttpServletResponse response,qTaskProgressBar currentpro,String paperType) throws IOException, ServletException, Exception;
    public String[][] traiterConcessions(MultipartFile file, String fullpatchname);
    public String[][] genererDocumentsImport(MultipartFile file, String fullpatchname);
    }
