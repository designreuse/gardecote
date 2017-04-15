package com.gardecote.business.service;

import com.gardecote.entities.qSeq;
import com.gardecote.entities.qSeqPK;
import com.gardecote.entities.qTaskProgressBar;

import java.util.List;

/**
 * Created by Dell on 13/04/2017.
 */
public interface qTaskBarService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qTaskProgressBar findById(String idact) ;

    /**
     * Loads all entities.
     * @return all entities
     */


    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qTaskProgressBar save(qTaskProgressBar entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qTaskProgressBar update(qTaskProgressBar entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qTaskProgressBar create( qTaskProgressBar entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(String idCapture);
}
