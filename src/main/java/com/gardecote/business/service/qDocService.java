package com.gardecote.business.service;

import com.gardecote.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qLic> retLicences(qSeq seqActive1,enumTypeDoc typeDoc);
    Page<qDoc> findAll(int p, int size);
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



    qDebarquement creerDebarquement(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc);
    qMarree creerMarree(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc);
    qTraitement creerTraitement(Date dateDepart, Date dateRetour, qSeq seqActive1,enumTypeDoc typeDoc);
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

}
