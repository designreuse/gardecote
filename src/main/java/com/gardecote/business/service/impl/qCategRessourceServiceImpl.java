package com.gardecote.business.service.impl;

import com.gardecote.business.service.qCategRessourceService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qCategRessourceRepository;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qCarnetPK;
import com.gardecote.entities.qCategPK;
import com.gardecote.entities.qCategRessource;
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
public class qCategRessourceServiceImpl implements qCategRessourceService {

    @Autowired
    private qCategRessourceRepository qCategRessourceRepository;


    @Override
    public qCategRessource findById(Integer idcarnet) {
        qCategRessource authprovEntity = qCategRessourceRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qCategRessource> findAll() {
        Iterable<qCategRessource> entities = qCategRessourceRepository.findAll();
        List<qCategRessource> beans = new ArrayList<qCategRessource>();
        for(qCategRessource authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qCategRessource save(qCategRessource authprov) {
        return update(authprov) ;
    }

    @Override
    public qCategRessource create(qCategRessource authprov) {
        qCategRessource authprovEntitySaved=null;
        qCategRessource authpro=qCategRessourceRepository.findOne(authprov.getIdtypeConcession());
        if(authpro==null)
        authprovEntitySaved = qCategRessourceRepository.save(authprov);
        return authprovEntitySaved;
    }

    @Override
    public qCategRessource update(qCategRessource authprov) {
      //  qCategRessource authprovEntity = qCategRessourceRepository.findOne(authprov.getIdtypeConcession());

        qCategRessource authprovEntitySaved = qCategRessourceRepository.save(authprov);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(Integer idauthpr) {
        qCategRessourceRepository.delete(idauthpr);
    }

    public qCategRessourceRepository getqCarnetRepository() {
        return qCategRessourceRepository;
    }

    public void setqCarnetRepository(qCategRessourceRepository qCarnetRepository) {
        this.qCategRessourceRepository = qCarnetRepository;
    }
}
