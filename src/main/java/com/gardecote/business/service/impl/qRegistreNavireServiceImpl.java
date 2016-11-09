package com.gardecote.business.service.impl;

import com.gardecote.business.service.qRegistreNavireService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qRegistreNavireRepository;
import com.gardecote.entities.qCarnet;
import com.gardecote.entities.qCarnetPK;
import com.gardecote.entities.qRegistreNavire;
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
public class qRegistreNavireServiceImpl implements qRegistreNavireService {
    @Autowired
    private qRegistreNavireRepository qRegistreNavireRepository;


    @Override
    public qRegistreNavire findById(String idcarnet) {
        qRegistreNavire authprovEntity =qRegistreNavireRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qRegistreNavire> findAll() {
        Iterable<qRegistreNavire> entities = qRegistreNavireRepository.findAll();
        List<qRegistreNavire> beans = new ArrayList<qRegistreNavire>();
        for(qRegistreNavire authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qRegistreNavire save(qRegistreNavire authprov) {
        return update(authprov) ;
    }

    @Override
    public qRegistreNavire create(qRegistreNavire authprov) {


        qRegistreNavire authprovEntitySaved = qRegistreNavireRepository.save(authprov);
        return authprovEntitySaved;
    }

    @Override
    public qRegistreNavire update(qRegistreNavire authprov) {
        qRegistreNavire authprovEntity = qRegistreNavireRepository.findOne(authprov.getNumimm());

        qRegistreNavire authprovEntitySaved = qRegistreNavireRepository.save(authprovEntity);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(String  idauthpr) {
        qRegistreNavireRepository.delete(idauthpr);
    }

    public qRegistreNavireRepository getAuthprovJpaRepository() {
        return qRegistreNavireRepository;
    }

    public void setAuthprovJpaRepository(qRegistreNavireRepository authprovJpaRepository) {
        this.qRegistreNavireRepository = authprovJpaRepository;
    }


}
