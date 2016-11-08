package com.gardecote.business.service.impl;

import com.gardecote.business.service.qDocService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qDocRepository;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qCarnetPK;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qDocPK;
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
public class qDocServiceImpl implements qDocService {
    @Autowired
    private qDocRepository qDocRepository;


    @Override
    public qDoc findById(qDocPK idcarnet) {
        qDoc authprovEntity = qDocRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qDoc> findAll() {
        Iterable<qDoc> entities = qDocRepository.findAll();
        List<qDoc> beans = new ArrayList<qDoc>();
        for(qDoc authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qDoc save(qDoc authprov) {
        return update(authprov) ;
    }

    @Override
    public qDoc create(qDoc authprov) {
        qDoc authprovEntity = qDocRepository.findOne(authprov.getqDocPK());
        if( authprovEntity != null ) {
            throw new IllegalStateException("already.exists");
        }
        authprovEntity = new qDoc();

        qDoc authprovEntitySaved = qDocRepository.save(authprovEntity);
        return authprovEntitySaved;
    }

    @Override
    public qDoc update(qDoc authprov) {
        qDoc authprovEntity = qDocRepository.findOne(authprov.getqDocPK());

        qDoc authprovEntitySaved = qDocRepository.save(authprovEntity);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(qDocPK  idauthpr) {
        qDocRepository.delete(idauthpr);
    }

    public qDocRepository getAuthprovJpaRepository() {
        return qDocRepository;
    }

    public void setAuthprovJpaRepository(qDocRepository authprovJpaRepository) {
        this.qDocRepository = authprovJpaRepository;
    }


}
