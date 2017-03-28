package com.gardecote.business.service;

import com.gardecote.entities.qEspeceDynamic;
import com.gardecote.entities.qEspeceDynamicPK;

import java.util.List;

/**
 * Created by Dell on 28/03/2017.
 */
public interface  qEspeceDynamicService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param numimm
     * @return entity
     */

    qEspeceDynamic findById(qEspeceDynamicPK numimm) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qEspeceDynamic> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qEspeceDynamic save(qEspeceDynamic entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qEspeceDynamic update(qEspeceDynamic entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qEspeceDynamic create(qEspeceDynamic entity);

    /**
     * Deletes an entity using its Primary Key
     * @param numimm
     */
    void delete(qEspeceDynamicPK numimm);

}
