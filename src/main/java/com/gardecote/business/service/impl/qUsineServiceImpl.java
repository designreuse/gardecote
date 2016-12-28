package com.gardecote.business.service.impl;

import com.gardecote.business.service.qUsineService;
import com.gardecote.data.repository.jpa.qUsineRepository;
import com.gardecote.entities.qLic;
import com.gardecote.entities.qUsine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 27/12/2016.
 */
@Service
@Transactional
public class qUsineServiceImpl implements qUsineService {
    @Autowired
    private qUsineRepository qUsineRepository;
    @Override
    public qUsine findById(String id) {
        qUsine authprovEntity = qUsineRepository.findOne(id);
        return authprovEntity;
    }

    @Override
    public Page<qUsine> findAll(int p, int size) {
        //	Iterable<qLic> entities = codauthJpaRepository.findAll(new PageRequest(p, size));
        Page<qUsine> entities = qUsineRepository.findAll(new PageRequest(p, size));
        //	List<qLic> beans = new ArrayList<qLic>();
//		for(qLic codauthEntity : entities) {
        //		beans.add(codauthEntity);
//		}
        //	return beans;
        return entities;
    }

    @Override
    public qUsine save(qUsine entity) {
        return update(entity) ;
    }

    @Override
    public qUsine update(qUsine entity) {
        qUsine authprovEntity = qUsineRepository.findOne(entity.getRefAgrement());

        qUsine authprovEntitySaved = qUsineRepository.save(authprovEntity);
        return authprovEntitySaved ;
    }

    @Override
    public qUsine create(qUsine entity) {
        qUsine authprovEntitySaved = qUsineRepository.save(entity);
        return authprovEntitySaved;
    }

    @Override
    public void delete(String id) {
        qUsineRepository.delete(id);
    }

    public com.gardecote.data.repository.jpa.qUsineRepository getqUsineRepository() {
        return qUsineRepository;
    }

    public void setqUsineRepository(com.gardecote.data.repository.jpa.qUsineRepository qUsineRepository) {
        this.qUsineRepository = qUsineRepository;
    }
}
