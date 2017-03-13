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
    public qBateau findById(String idact) ;
    public qNavireLegale findLegalById(String idact) ;

    public Page<qBateau> getSuggNavire(String searchpage);
    public List<qNavireLegale> findAllLegal();
    /**
     * Loads all entities.
     * @return all entities
     */
   public Page<qBateau> findAll(int p, int size,String term);
   public  List<qBateau> findAll();

    /**
     * Saves the given entity in the database (create or update)
     * @param entity
     * @return entity
     */
    qBateau save(qBateau entity);
    public Page<qNavireLegale> findAllLegal(int p, int size,String terme);
    /**
     * Updates the given entity in the database
     * @param entity
     * @return
     */
    qBateau update(qBateau entity);
    List<qLic> findLicences(qNavireLegale vleg);
    /**
     * Creates the given entity in the database
     * @param entity
     * @return
     */
    qBateau create(qBateau entity);

    /**
     * Deletes an entity using its Primary Key
     * @param idCapture
     */
    void delete(String idCapture);

    List<qLic> retActLicences(qBateau navire);

}
