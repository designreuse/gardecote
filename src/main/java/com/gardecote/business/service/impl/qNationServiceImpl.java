package com.gardecote.business.service.impl;

import com.gardecote.business.service.qNationService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qNationRepository;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qCarnetPK;
import com.gardecote.entities.qNation;
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
public class qNationServiceImpl implements qNationService {
    @Autowired
    private qNationRepository qNationRepository;


    @Override
    public qNation findById(Integer idcarnet) {
        qNation authprovEntity = qNationRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qNation> findAll() {
        Iterable<qNation> entities = qNationRepository.findAll();
        List<qNation> beans = new ArrayList<qNation>();
        for(qNation authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qNation save(qNation authprov) {
        return update(authprov) ;
    }

    @Override
    public qNation create(qNation authprov) {

        qNation authprovEntitySaved = qNationRepository.save(authprov);
        return authprovEntitySaved;
    }

    @Override
    public qNation update(qNation authprov) {
      //  qNation authprovEntity = qNationRepository.findOne(authprov.getIdNation());

        qNation authprovEntitySaved = qNationRepository.save(authprov);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(Integer  idauthpr) {
        qNationRepository.delete(idauthpr);
    }

    public qNationRepository getAuthprovJpaRepository() {
        return qNationRepository;
    }

    public void setAuthprovJpaRepository(qNationRepository authprovJpaRepository) {
        this.qNationRepository = authprovJpaRepository;
    }


}
