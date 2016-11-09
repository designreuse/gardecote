/*
 * Created on 26 sept. 2016 ( Time 10:18:28 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service;
import com.gardecote.entities.qCategRessource;
import java.util.List;
import com.gardecote.entities.qTypeConcession;
/**
 * Business Service Interface for entity AutorisEntity.
 */
public interface qTypeConcessionService {
	/**
	 * Loads an entity from the database using its Primary Key
	 * @return entity
	 */
	qTypeConcession findById(Integer qPK) ;
	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<qTypeConcession> findAll();
	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	qTypeConcession save(qTypeConcession entity);
	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	qTypeConcession update(qTypeConcession entity);
	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	qTypeConcession create(qTypeConcession entity);
	/**
	 * Deletes an entity using its Primary Key
	 */
	void delete(Integer qPK);
}