package com.gardecote.business.service;

import com.gardecote.entities.qLic;
import com.gardecote.entities.qUsine;
import com.gardecote.entities.qZone;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dell on 27/12/2016.
 */

public interface qUsineService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param id
     * @return entity
     */
    qUsine findById(String id) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    Page<qUsine> findAll(int p, int size);

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qUsine save(qUsine entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qUsine update(qUsine entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qUsine create(qUsine entity);

    /**
     * Deletes an entity using its Primary Key
     * @param id
     */

    void delete(String id);

}
