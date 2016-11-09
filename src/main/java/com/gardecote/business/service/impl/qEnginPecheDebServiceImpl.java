/*
 * Created on 26 sept. 2016 ( Time 10:51:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service.impl;

import com.gardecote.business.service.qEnginPecheDebService;
import com.gardecote.business.service.qEnginPecheService;
import com.gardecote.data.repository.jpa.qEnginPecheDebRepository;
import com.gardecote.data.repository.jpa.qEnginPecheRepository;
import com.gardecote.entities.enumEngin;
import com.gardecote.entities.enumEnginDeb;
import com.gardecote.entities.qEnginPeche;
import com.gardecote.entities.qEnginPecheDeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Captures2Service
 */
@Service
@Transactional
public class qEnginPecheDebServiceImpl implements qEnginPecheDebService {

	@Autowired
	private qEnginPecheDebRepository captures2JpaRepository;

	
	
	@Override
	public qEnginPecheDeb findById(enumEnginDeb idCapture) {
		qEnginPecheDeb captures2Entity = captures2JpaRepository.findOne(idCapture);
		return captures2Entity ;
	}

	@Override
	public List<qEnginPecheDeb> findAll() {
		Iterable<qEnginPecheDeb> entities = captures2JpaRepository.findAll();
		List<qEnginPecheDeb> beans = new ArrayList<qEnginPecheDeb>();
		for(qEnginPecheDeb captures2Entity : entities) {
			beans.add(captures2Entity);
		}
		return beans;
	}

	@Override
	public qEnginPecheDeb save(qEnginPecheDeb captures2) {
		return update(captures2) ;
	}

	@Override
	public qEnginPecheDeb create(qEnginPecheDeb captures2) {


		qEnginPecheDeb captures2EntitySaved = captures2JpaRepository.save(captures2);
		return captures2EntitySaved;
	}

	@Override
	public qEnginPecheDeb update(qEnginPecheDeb captures2) {
		qEnginPecheDeb captures2Entity = captures2JpaRepository.findOne(captures2.getEngin());
		qEnginPecheDeb captures2EntitySaved = captures2JpaRepository.save(captures2Entity);
		return captures2EntitySaved;
	}

	@Override
	public void delete(enumEnginDeb idCapture) {
		captures2JpaRepository.delete(idCapture);
	}

	public qEnginPecheDebRepository getCaptures2JpaRepository() {
		return captures2JpaRepository;
	}

	public void setCaptures2JpaRepository(qEnginPecheDebRepository captures2JpaRepository) {
		this.captures2JpaRepository = captures2JpaRepository;
	}

	

}
