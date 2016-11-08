package com.gardecote.business.service;

import com.gardecote.entities.qCapture;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qTypeNav;

import java.util.List;

/**
 * Created by Dell on 08/11/2016.
 */
public interface qTypeNavService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qTypeNav findById(String idact) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qTypeNav> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qTypeNav save(qTypeNav entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qTypeNav update(qTypeNav entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qTypeNav create(qTypeNav entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(String idCapture);
}
