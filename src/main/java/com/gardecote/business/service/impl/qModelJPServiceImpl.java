/*
 * Created on 26 sept. 2016 ( Time 10:51:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.gardecote.business.service.qModelJPService;

import com.gardecote.data.repository.jpa.qModelJPRepository;
import com.gardecote.entities.enumPrefix;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gardecote.entities.qModelJP;
import com.gardecote.entities.qTypeConcession;

/**
 * Implementation of CodesAmendeService
 */
@Service
@Transactional
public class qModelJPServiceImpl implements qModelJPService {

	@Resource
	private qModelJPRepository codesAmendeJpaRepository;

	
	
	@Override
	public qModelJP findById(enumPrefix id) {
		qModelJP codesAmendeEntity = codesAmendeJpaRepository.findOne(id);
		return codesAmendeEntity;
	}

	@Override
	public List<qModelJP> findAll() {
		Iterable<qModelJP> entities = codesAmendeJpaRepository.findAll();
		List<qModelJP> beans = new ArrayList<qModelJP>();
		for(qModelJP codesAmendeEntity : entities) {
			beans.add(codesAmendeEntity);
		}
		return beans;
	}

	@Override
	public qModelJP save(qModelJP codesAmende) {
		return update(codesAmende) ;
	}

	@Override
	public qModelJP create(qModelJP codesAmende) {
		qModelJP codesAmendeEntity = codesAmendeJpaRepository.findOne(codesAmende.getPrefixModel());
		if( codesAmendeEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		codesAmendeEntity = new qModelJP();
		qModelJP codesAmendeEntitySaved = codesAmendeJpaRepository.save(codesAmendeEntity);
		return codesAmendeEntitySaved;
	}

	@Override
	public qModelJP update(qModelJP codesAmende) {
		qModelJP codesAmendeEntity = codesAmendeJpaRepository.findOne(codesAmende.getPrefixModel());
		qModelJP codesAmendeEntitySaved = codesAmendeJpaRepository.save(codesAmendeEntity);
		return codesAmendeEntitySaved;
	}

	@Override
	public void delete(enumPrefix id) {
		codesAmendeJpaRepository.delete(id);
	}

	public qModelJPRepository getCodesAmendeJpaRepository() {
		return codesAmendeJpaRepository;
	}

	public void setCodesAmendeJpaRepository(qModelJPRepository codesAmendeJpaRepository) {
		this.codesAmendeJpaRepository = codesAmendeJpaRepository;
	}

	

}
