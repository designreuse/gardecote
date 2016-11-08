package com.gardecote.business.service;

import com.gardecote.entities.qCapture;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qTypeLic;

import java.util.List;

/**
 * Created by Dell on 08/11/2016.
 */
public interface qTypeLicService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qTypeLic findById(Integer idact) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qTypeLic> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qTypeLic save(qTypeLic entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qTypeLic update(qTypeLic entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qTypeLic create(qTypeLic entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(Integer idCapture);
}
