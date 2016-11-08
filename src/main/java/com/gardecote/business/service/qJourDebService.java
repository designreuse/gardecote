package com.gardecote.business.service;

import com.gardecote.entities.qCapture;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qJourDeb;
import com.gardecote.entities.qJourPK;

import java.util.List;

/**
 * Created by Dell on 08/11/2016.
 */
public interface qJourDebService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qJourDeb findById(qJourPK idact) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qJourDeb> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qJourDeb save(qJourDeb entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qJourDeb update(qJourDeb entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qJourDeb create(qJourDeb entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(qJourPK idCapture);
}
