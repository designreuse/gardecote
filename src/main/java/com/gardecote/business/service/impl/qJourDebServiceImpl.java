package com.gardecote.business.service.impl;

import com.gardecote.business.service.qJourDebService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qJourDebRepository;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qCarnetPK;
import com.gardecote.entities.qJourDeb;
import com.gardecote.entities.qJourPK;
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
public class qJourDebServiceImpl implements qJourDebService {
    @Autowired

    private qJourDebRepository qJourDebRepository;


    @Override
    public qJourDeb findById(qJourPK idcarnet) {
        qJourDeb authprovEntity = qJourDebRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qJourDeb> findAll() {
        Iterable<qJourDeb> entities = qJourDebRepository.findAll();
        List<qJourDeb> beans = new ArrayList<qJourDeb>();
        for(qJourDeb authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qJourDeb save(qJourDeb authprov) {
        return update(authprov) ;
    }

    @Override
    public qJourDeb create(qJourDeb authprov) {
        qJourDeb authprovEntity = qJourDebRepository.findOne(authprov.getJourPK());
        if( authprovEntity != null ) {
            throw new IllegalStateException("already.exists");
        }
        authprovEntity = new qJourDeb();

        qJourDeb authprovEntitySaved = qJourDebRepository.save(authprovEntity);
        return authprovEntitySaved;
    }

    @Override
    public qJourDeb update(qJourDeb authprov) {
        qJourDeb authprovEntity = qJourDebRepository.findOne(authprov.getJourPK());

        qJourDeb authprovEntitySaved = qJourDebRepository.save(authprovEntity);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(qJourPK idauthpr) {
        qJourDebRepository.delete(idauthpr);
    }

    public qJourDebRepository getAuthprovJpaRepository() {
        return qJourDebRepository;
    }

    public void setAuthprovJpaRepository(qJourDebRepository authprovJpaRepository) {
        this.qJourDebRepository = authprovJpaRepository;
    }


}
