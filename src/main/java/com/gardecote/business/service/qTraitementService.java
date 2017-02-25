package com.gardecote.business.service;

import com.gardecote.data.repository.jpa.MyCustomEntityRepository;
import com.gardecote.entities.qDocPK;
import com.gardecote.entities.qSeq;
import com.gardecote.entities.qSeqPK;
import com.gardecote.entities.qTraitement;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Dell on 23/02/2017.
 */
public interface qTraitementService   {
    /**
     * Loads an entity from the database using its Primary Key
     * @param
     * @return entity
     */
    qTraitement findById(qDocPK idocpk) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qTraitement> findAll();



    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qTraitement save(qTraitement entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qTraitement update(qTraitement entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qTraitement create(qTraitement entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(qDocPK idCapture);
}
