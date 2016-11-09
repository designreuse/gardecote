/*
 * Created on 26 sept. 2016 ( Time 10:51:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service;

import com.gardecote.entities.enumEngin;
import com.gardecote.entities.enumEnginDeb;
import com.gardecote.entities.qEnginPeche;
import com.gardecote.entities.qEnginPecheDeb;

import java.util.List;

/**
 * Business Service Interface for entity BateaucompletEntity.
 */
public interface qEnginPecheDebService {

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param numimm
	 * @return entity
	 */
	qEnginPecheDeb findById(enumEnginDeb numimm) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<qEnginPecheDeb> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	qEnginPecheDeb save(qEnginPecheDeb entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	qEnginPecheDeb update(qEnginPecheDeb entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	qEnginPecheDeb create(qEnginPecheDeb entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param numimm
	 */
	void delete(enumEnginDeb numimm);


}
