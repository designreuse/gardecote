package com.gardecote.business.service;

import com.gardecote.entities.qCapture;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qSeq;

import java.util.List;
import com.gardecote.entities.qSeqPK;

/**
 * Created by Dell on 08/11/2016.
 */
public interface  qSeqService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qSeq findById(qSeqPK idact) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qSeq> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qSeq save(qSeq entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qSeq update(qSeq entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qSeq create(qSeq entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(qSeqPK idCapture);
}
