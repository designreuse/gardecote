package com.gardecote.business.service.impl;

import com.gardecote.business.service.qSeqService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qSeqRepository;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qCarnetPK;
import com.gardecote.entities.qSeq;
import com.gardecote.entities.qSeqPK;
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
public class qSeqServiceImpl implements qSeqService {
    @Autowired
    private qSeqRepository qSeqRepository;


    @Override
    public qSeq findById(qSeqPK idcarnet) {
        qSeq authprovEntity =qSeqRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qSeq> findAll() {
        Iterable<qSeq> entities = qSeqRepository.findAll();
        List<qSeq> beans = new ArrayList<qSeq>();
        for(qSeq authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qSeq save(qSeq authprov) {
        return update(authprov) ;
    }

    @Override
    public qSeq create(qSeq authprov) {
        qSeq authprovEntity = qSeqRepository.findOne(authprov.getSeqPK());
        if( authprovEntity != null ) {
            throw new IllegalStateException("already.exists");
        }
        authprovEntity = new qSeq();

        qSeq authprovEntitySaved = qSeqRepository.save(authprovEntity);
        return authprovEntitySaved;
    }

    @Override
    public qSeq update(qSeq authprov) {
        qSeq authprovEntity = qSeqRepository.findOne(authprov.getSeqPK());

        qSeq authprovEntitySaved = qSeqRepository.save(authprovEntity);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(qSeqPK  idauthpr) {
        qSeqRepository.delete(idauthpr);
    }

    public qSeqRepository getAuthprovJpaRepository() {
        return qSeqRepository;
    }

    public void setAuthprovJpaRepository(qSeqRepository authprovJpaRepository) {
        this.qSeqRepository = authprovJpaRepository;
    }


}
