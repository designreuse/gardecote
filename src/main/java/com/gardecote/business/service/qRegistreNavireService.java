package com.gardecote.business.service;

import com.gardecote.entities.qCapture;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qRegistreNavire;

import java.util.List;

/**
 * Created by Dell on 08/11/2016.
 */
public interface qRegistreNavireService {
    /**
     * Loads an entity from the database using its Primary Key
     * @param idact
     * @return entity
     */
    qRegistreNavire findById(String idact) ;

    /**
     * Loads all entities.
     * @return all entities
     */
    List<qRegistreNavire> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qRegistreNavire save(qRegistreNavire entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qRegistreNavire update(qRegistreNavire entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qRegistreNavire create(qRegistreNavire entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(String idCapture);
}
