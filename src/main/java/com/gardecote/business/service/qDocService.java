package com.gardecote.business.service;

import com.gardecote.entities.*;
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
    List<qDoc> findAll(Pageable pageable);

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

    Map<String,Set<Object>> detecterDestructionDonnees(qSeqPK sequencepk,Date dateDepartt);

    boolean checkSaisie(qSeqPK sequencepk);

    qDoc creerDoc(Date dateDepart, Date dateRetour, qSeq seqActive);

    }
