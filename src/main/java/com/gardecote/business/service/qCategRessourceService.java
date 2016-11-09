package com.gardecote.business.service;

import com.gardecote.entities.qCapture;
import com.gardecote.entities.qCapturePK;
import com.gardecote.entities.qCategPK;
import com.gardecote.entities.qCategRessource;

import java.util.List;

/**
 * Created by Dell on 08/11/2016.
 */
public interface qCategRessourceService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qCategRessource findById(Integer idact) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qCategRessource> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qCategRessource save(qCategRessource entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qCategRessource update(qCategRessource entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qCategRessource create(qCategRessource entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(Integer idCapture);

}
