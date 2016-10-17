/*
 * Created on 26 sept. 2016 ( Time 10:51:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service;
import com.gardecote.entities.qZone;
import java.util.List;

/**
 * Business Service Interface for entity CodesAmende.
 */
public interface qZoneService {

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	qZone findById(Integer id) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<qZone> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	qZone save(qZone entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	qZone update(qZone entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	qZone create(qZone entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete(Integer id);


}
