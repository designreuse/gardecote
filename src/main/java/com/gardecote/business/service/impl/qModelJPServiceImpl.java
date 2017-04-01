/*
 * Created on 26 sept. 2016 ( Time 10:51:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.gardecote.business.service.qModelJPService;

import com.gardecote.data.repository.jpa.qEspeceTypeeRepository;
import com.gardecote.data.repository.jpa.qModelJPRepository;
import com.gardecote.entities.*;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of CodesAmendeService
 */
@Service
@Transactional
public class qModelJPServiceImpl implements qModelJPService {

	@Autowired
	private qModelJPRepository codesAmendeJpaRepository;
	@Autowired
	private qEspeceTypeeRepository qesptypeeRepository;

	@Override
	public qModelJP findById(qPrefixPK id) {
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

		qModelJP codesAmendeEntitySaved = codesAmendeJpaRepository.save(codesAmende);
		return codesAmendeEntitySaved;
	}

	@Override
	public qModelJP update(qModelJP codesAmende) {
//		qModelJP codesAmendeEntity = codesAmendeJpaRepository.findOne(codesAmende.getPrefixModel());
		qModelJP codesAmendeEntitySaved = codesAmendeJpaRepository.save(codesAmende);


		return codesAmendeEntitySaved;
	}

	@Override
	public void delete(qPrefixPK  id) {
		codesAmendeJpaRepository.delete(id);
	}

	public qModelJPRepository getCodesAmendeJpaRepository() {
		return codesAmendeJpaRepository;
	}

	public void setCodesAmendeJpaRepository(qModelJPRepository codesAmendeJpaRepository) {
		this.codesAmendeJpaRepository = codesAmendeJpaRepository;
	}

	@Override
	public List<qEspeceTypee> findEspTypees(String pr) {
		return codesAmendeJpaRepository.findEspTypees(pr);
	}

	@Override
	public List<qEspeceDynamic> getEspecestypeesDyn(String numeroPage, enumTypeDoc typeDoc, qModelJP md) {
		List<qEspeceDynamic> result=new ArrayList<qEspeceDynamic>();

		for(qEspeceTypee esp:md.getEspecestypees()) {
			if(esp.getTypeesptypee().equals(enumTypeEspTypee.DYNAMIC)) {
				result.add(new qEspeceDynamic(numeroPage,typeDoc,esp.getNumOrdre(),esp,esp.getQespece()));
			}
		}
		return result;
	}
}
