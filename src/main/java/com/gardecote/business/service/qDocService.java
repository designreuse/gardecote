package com.gardecote.business.service;

import com.gardecote.entities.qCapture;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qDocPK;

import java.util.List;

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
    List<qDoc> findAll();

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
}
