/*
 * Created on 26 sept. 2016 ( Time 10:51:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service;

import com.gardecote.entities.enumEngin;
import com.gardecote.entities.qEnginPecheMar;
import com.gardecote.entities.qEnginsLicence;
import com.gardecote.entities.qEnginsLicencePK;

import java.util.List;

/**
 * Business Service Interface for entity BateaucompletEntity.
 */
public interface qEnginsLicenceService {
	/**
	 * Loads an entity from the database using its Primary Key
	 * @param numimm
	 * @return entity
	 */
	qEnginsLicence findById(qEnginsLicencePK numimm) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<qEnginsLicence> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	qEnginsLicence save(qEnginsLicence entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	qEnginsLicence update(qEnginsLicence entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	qEnginsLicence create(qEnginsLicence entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param numimm
	 */
	void delete(qEnginsLicencePK numimm);


}
