package com.gardecote.business.service;

import com.gardecote.entities.qCapture;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qEspeceTypee;
import com.gardecote.entities.qEspeceTypeePK;

import java.util.List;

/**
 * Created by Dell on 08/11/2016.
 */
public interface qEspeceTypeeService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qEspeceTypee findById(qEspeceTypeePK idact) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qEspeceTypee> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qEspeceTypee save(qEspeceTypee entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qEspeceTypee update(qEspeceTypee entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qEspeceTypee create(qEspeceTypee entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(qEspeceTypeePK idCapture);
}
