package com.gardecote.business.service;

import com.gardecote.entities.qLic;
import com.gardecote.entities.qNavire;
import com.gardecote.entities.qPrefix;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Dell on 02/12/2016.
 */
public interface qPrefixService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idpref
     * @return entity
     */
    public qPrefix findById(String idpref) ;


    /**
     * Loads all entities.
     * @return all entities
     */

    public List<qPrefix> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qPrefix save(qPrefix entity);


    void delete(String idPrefix);


}
