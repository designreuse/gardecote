package com.gardecote.business.service.impl;

import com.gardecote.business.service.qEspeceTypeeService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qEspeceTypeeRepository;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qCarnetPK;
import com.gardecote.entities.qEspeceTypee;
import com.gardecote.entities.qEspeceTypeePK;
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
public class qEspeceTypeeServiceImpl implements qEspeceTypeeService {
    @Autowired
    private qEspeceTypeeRepository qEspeceTypeeRepository;


    @Override
    public qEspeceTypee findById(qEspeceTypeePK idcarnet) {
        qEspeceTypee authprovEntity = qEspeceTypeeRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qEspeceTypee> findAll() {
        Iterable<qEspeceTypee> entities = qEspeceTypeeRepository.findAll();
        List<qEspeceTypee> beans = new ArrayList<qEspeceTypee>();
        for(qEspeceTypee authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qEspeceTypee save(qEspeceTypee authprov) {
        return update(authprov) ;
    }

    @Override
    public qEspeceTypee create(qEspeceTypee authprov) {
        qEspeceTypee authprovEntity = qEspeceTypeeRepository.findOne(authprov.getQEspeceTypeePK());
        if( authprovEntity != null ) {
            throw new IllegalStateException("already.exists");
        }
        authprovEntity = new qEspeceTypee();

        qEspeceTypee authprovEntitySaved = qEspeceTypeeRepository.save(authprovEntity);
        return authprovEntitySaved;
    }

    @Override
    public qEspeceTypee update(qEspeceTypee authprov) {
        qEspeceTypee authprovEntity = qEspeceTypeeRepository.findOne(authprov.getQEspeceTypeePK());

        qEspeceTypee authprovEntitySaved = qEspeceTypeeRepository.save(authprovEntity);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(qEspeceTypeePK  idauthpr) {
        qEspeceTypeeRepository.delete(idauthpr);
    }

    public com.gardecote.data.repository.jpa.qEspeceTypeeRepository getqEspeceTypeeRepository() {
        return qEspeceTypeeRepository;
    }

    public void setqEspeceTypeeRepository(com.gardecote.data.repository.jpa.qEspeceTypeeRepository qEspeceTypeeRepository) {
        this.qEspeceTypeeRepository = qEspeceTypeeRepository;
    }
}
