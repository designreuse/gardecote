/*
 * Created on 26 sept. 2016 ( Time 10:51:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.gardecote.data.repository.jpa.qRegistreNavireRepository;
import com.gardecote.entities.qLic;
import com.gardecote.entities.qNavire;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gardecote.business.service.qLicenceService;
import com.gardecote.data.repository.jpa.qLicenceRepository;
/**
 * Implementation of CodauthService
 */
@Service
@Transactional
public class qLicenceServiceImpl implements qLicenceService {

	@Autowired
	private qLicenceRepository codauthJpaRepository;
	@Autowired
	private qRegistreNavireRepository navRepository;


	@Override
	public qLic findById(String codeauth) {
		qLic codauthEntity = codauthJpaRepository.findOne(codeauth);
		return codauthEntity;
	}

	//@Override
	public Page<qLic> findAll(int p,int size) {

	//	Iterable<qLic> entities = codauthJpaRepository.findAll(new PageRequest(p, size));
		Page<qLic> entities = codauthJpaRepository.findAll(new PageRequest(p, size));
	//	List<qLic> beans = new ArrayList<qLic>();
//		for(qLic codauthEntity : entities) {
	//		beans.add(codauthEntity);
//		}
	//	return beans;
		return entities;
	}

	@Override
	public qLic save(qLic codauth) {
		return update(codauth) ;
	}

	@Override
	public qLic create(qLic codauth) {
    	qLic codauthEntitySaved = codauthJpaRepository.save(codauth);
		return codauthEntitySaved ;
	}

	@Override
	public qLic update(qLic codauth) {
    	qLic codauthEntitySaved = codauthJpaRepository.save(codauth);
		return codauthEntitySaved;
	}

	@Override
	public void delete(String codeauth) {
		codauthJpaRepository.delete(codeauth);
	}

	public qLicenceRepository getCodauthJpaRepository() {
		return codauthJpaRepository;
	}

	public void setCodauthJpaRepository(qLicenceRepository codauthJpaRepository) {
		this.codauthJpaRepository = codauthJpaRepository;
	}

	public Page<qLic> returnSuggNomNav(String searchNomnav){
		return codauthJpaRepository.returnSuggNomNav1(new PageRequest(0, 10),searchNomnav);
	}

	@Override
	public boolean validatenav(qLic lic) {
		qNavire  qnv=navRepository.findOne(lic.getNumimm());
		if(qnv==null)  return false;
		else           return true;

}
	@Override
	public boolean validatenumlic(qLic lic) {
		qLic  qlc=codauthJpaRepository.findOne(lic.getNumlic());
		if(qlc==null)  return true;
		else           return false;

	}
}
