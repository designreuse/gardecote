package com.gardecote.business.service;

import com.gardecote.data.repository.jpa.qRegistreNavireRepository;
import com.gardecote.entities.*;
import org.springframework.data.domain.Page;

import java.util.Date;
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
    public qNavire findById(String idact) ;
    public Page<qNavire> getSuggNavire(String searchpage);

    /**
     * Loads all entities.
     * @return all entities
     */
   public Page<qNavire> findAll(int p, int size,String term);
   public  List<qNavire> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qNavire save(qNavire entity);

    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qNavire update(qNavire entity);

    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qNavire create(qNavire entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(String idCapture);

    List<qLic> retActLicences(qNavire navire);

}
