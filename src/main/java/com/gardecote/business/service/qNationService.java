package com.gardecote.business.service;

import com.gardecote.entities.qCapture;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qNation;

import java.util.List;

/**
 * Created by Dell on 08/11/2016.
 */
public interface qNationService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qNation findById(Integer idact) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qNation> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qNation save(qNation entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qNation update(qNation entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qNation create(qNation entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(Integer idCapture);
}
