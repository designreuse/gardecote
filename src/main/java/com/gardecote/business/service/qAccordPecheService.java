package com.gardecote.business.service;

import com.gardecote.entities.qAccordPeche;

import java.util.List;

/**
 * Created by Dell on 04/03/2017.
 */
public interface qAccordPecheService  {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qAccordPeche findById(Integer idact) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qAccordPeche> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qAccordPeche save(qAccordPeche entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qAccordPeche update(qAccordPeche entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qAccordPeche create(qAccordPeche entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(qAccordPeche idCapture);
}
