package com.gardecote.business.service;


import com.gardecote.entities.qEnginAuthorisee;
import com.gardecote.entities.qEnginAuthoriseePK;

import java.util.List;

/**
 * Created by Dell on 04/03/2017.
 */
public interface qEnginAuthoriseeService {
    /**
     * Business Service Interface for entity BateaucompletEntity.
     */
      /**
         * Loads an entity from the database using its Primary Key
         * @param numimm
         * @return entity
         */
        qEnginAuthorisee findById(qEnginAuthoriseePK numimm) ;

        /**
         * Loads all entities.
         * @return all entities
         */
        List<qEnginAuthorisee> findAll();

        /**
         * Saves the given entity in the database (create or update)
         * @param entity
         * @return entity
         */
        qEnginAuthorisee save(qEnginAuthorisee entity);

        /**
         * Updates the given entity in the database
         * @param entity
         * @return
         */
        qEnginAuthorisee update(qEnginAuthorisee entity);

        /**
         * Creates the given entity in the database
         * @param entity
         * @return
         */
        qEnginAuthorisee create(qEnginAuthorisee entity);

        /**
         * Deletes an entity using its Primary Key
         * @param numimm
         */
        void delete(qEnginAuthorisee numimm);
}
