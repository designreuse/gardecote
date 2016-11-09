/*
 * Created on 26 sept. 2016 ( Time 10:51:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.gardecote.business.service.qCapturesService;
import com.gardecote.entities.qCapture;
import com.gardecote.data.repository.jpa.qCapturesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gardecote.entities.qCapturePK;
/**
 * Implementation of ActivitebatService
 */
@Service
@Transactional
public class qCapturesServiceImpl implements qCapturesService {

	@Autowired
	private qCapturesRepository qCapturesJpaRepository;

	
	@Override
	public qCapture findById(qCapturePK idcapt) {
		qCapture qCaptures = qCapturesJpaRepository.findOne(idcapt);
		return qCaptures;
	}

	@Override
	public List<qCapture> findAll() {
		Iterable<qCapture> entities = qCapturesJpaRepository.findAll();
		List<qCapture> beans = new ArrayList<qCapture>();
		for(qCapture qCapture1 : entities) {
			beans.add(qCapture1);
		}
		return beans;
	}

	@Override
	public qCapture save(qCapture qcapture) {
		return update(qcapture) ;
	}

	@Override
	public qCapture create(qCapture qcapture) {

		qCapture qcaptureEntitySaved = qCapturesJpaRepository.save(qcapture);
		return qcaptureEntitySaved ;
	}

	@Override
	public qCapture update(qCapture qcapture) {
		qCapture qcaptureEntity = qCapturesJpaRepository.findOne(qcapture.getCapturePK());
		qCapture qcaptureEntitySaved = qCapturesJpaRepository.save(qcaptureEntity);
		return qcaptureEntitySaved;
	}

	@Override
	public void delete(qCapturePK  idcapt) {
		qCapturesJpaRepository.delete(idcapt);
	}


	public qCapturesRepository getqCapturesJpaRepository() {
		return qCapturesJpaRepository;
	}

	public void setqCapturesJpaRepository(qCapturesRepository qCapturesJpaRepository) {
		this.qCapturesJpaRepository = qCapturesJpaRepository;
	}
}
