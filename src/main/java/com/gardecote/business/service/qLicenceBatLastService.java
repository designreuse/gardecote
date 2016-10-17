/*
 * Created on 26 sept. 2016 ( Time 10:51:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service;
import com.gardecote.entities.qLicenceBatLast;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Business Service Interface for entity Changmentact.
 */
public interface qLicenceBatLastService {

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param idchact
	 * @return entity
	 */
	qLicenceBatLast findById(Long idchact) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<qLicenceBatLast> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	qLicenceBatLast save(qLicenceBatLast entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	qLicenceBatLast update(qLicenceBatLast entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	qLicenceBatLast create(qLicenceBatLast entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param idchact
	 */
	void delete(Long idchact);
	Page<qLicenceBatLast> returnSuggNomNav(String searchNomnav);

}
