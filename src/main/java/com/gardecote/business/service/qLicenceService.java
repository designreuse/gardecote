/*
 * Created on 26 sept. 2016 ( Time 10:51:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service;

import com.gardecote.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Business Service Interface for entity Changmentact.
 */
public interface qLicenceService {

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param idchact
	 * @return entity
	 */
	qLic findById(String idchact) ;
	void updatechangements();
	void  importerLicence(MultipartFile file, String fullname);
	String importerLicenceNV(MultipartFile file, String fullpatchname);
	/**
	 * Loads all entities.
	 * @return all entities
	 */
	Page<qLic> findAll(int p, int size);
	List<qLic> findAll();
	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	qLic save(qLic entity);

	List<qLic> checkNation(qNation nationjp);
	boolean checkPrefix(qPrefix deletedPrefix);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	qLic update(qLic entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	qLic create(qLic entity);
	boolean validatenav(qLic lic);
	boolean validatenumlic(qLic lic);
	/**
	 * Deletes an entity using its Primary Key
	 * @param idchact
	 */
	void delete(String idchact);
	Page<qLic> returnSuggNomNav(String searchNomnav);
	boolean checkZones(qZone zone);

}
