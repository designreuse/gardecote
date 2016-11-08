package com.gardecote.business.service.impl;

import com.gardecote.business.service.qTypeLicService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qTypeLicRepository;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qCarnetPK;
import com.gardecote.entities.qTypeLic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 08/11/2016.
 */
@Service
@Transactional
public class qTypeLicServiceImpl implements qTypeLicService {
    @Autowired
    private qTypeLicRepository qTypeLicRepository;


    @Override
    public qTypeLic findById(Integer idcarnet) {
        qTypeLic authprovEntity = qTypeLicRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qTypeLic> findAll() {
        Iterable<qTypeLic> entities = qTypeLicRepository.findAll();
        List<qTypeLic> beans = new ArrayList<qTypeLic>();
        for(qTypeLic authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qTypeLic save(qTypeLic authprov) {
        return update(authprov) ;
    }

    @Override
    public qTypeLic create(qTypeLic authprov) {
        qTypeLic authprovEntity = qTypeLicRepository.findOne(authprov.getIdTypeLic());
        if( authprovEntity != null ) {
            throw new IllegalStateException("already.exists");
        }
        authprovEntity = new qTypeLic();

        qTypeLic authprovEntitySaved = qTypeLicRepository.save(authprovEntity);
        return authprovEntitySaved;
    }

    @Override
    public qTypeLic update(qTypeLic authprov) {
        qTypeLic authprovEntity = qTypeLicRepository.findOne(authprov.getIdTypeLic());

        qTypeLic authprovEntitySaved = qTypeLicRepository.save(authprovEntity);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(Integer  idauthpr) {
        qTypeLicRepository.delete(idauthpr);
    }

    public qTypeLicRepository getAuthprovJpaRepository() {
        return qTypeLicRepository;
    }

    public void setAuthprovJpaRepository(qTypeLicRepository authprovJpaRepository) {
        this.qTypeLicRepository = authprovJpaRepository;
    }


}
