package com.gardecote.business.service.impl;

import com.gardecote.business.service.qTypeNavService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qTypeNavRepository;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qCarnetPK;
import com.gardecote.entities.qTypeNav;
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
public class qTypeNavServiceImpl implements qTypeNavService {
    @Autowired
    private qTypeNavRepository qTypeNavRepository;


    @Override
    public qTypeNav findById(String idcarnet) {
        qTypeNav authprovEntity = qTypeNavRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qTypeNav> findAll() {
        Iterable<qTypeNav> entities = qTypeNavRepository.findAll();
        List<qTypeNav> beans = new ArrayList<qTypeNav>();
        for(qTypeNav authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qTypeNav save(qTypeNav authprov) {
        return update(authprov) ;
    }

    @Override
    public qTypeNav create(qTypeNav authprov) {
        qTypeNav authprovEntity = qTypeNavRepository.findOne(authprov.getIdqTypeNav());
        if( authprovEntity != null ) {
            throw new IllegalStateException("already.exists");
        }
        authprovEntity = new qTypeNav();

        qTypeNav authprovEntitySaved = qTypeNavRepository.save(authprovEntity);
        return authprovEntitySaved;
    }

    @Override
    public qTypeNav update(qTypeNav authprov) {
        qTypeNav authprovEntity = qTypeNavRepository.findOne(authprov.getIdqTypeNav());

        qTypeNav authprovEntitySaved = qTypeNavRepository.save(authprovEntity);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(String idauthpr) {
        qTypeNavRepository.delete(idauthpr);
    }

    public qTypeNavRepository getAuthprovJpaRepository() {
        return qTypeNavRepository;
    }

    public void setAuthprovJpaRepository(qTypeNavRepository authprovJpaRepository) {
        this.qTypeNavRepository = authprovJpaRepository;
    }


}
