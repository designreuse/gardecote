package com.gardecote.business.service.impl;

import com.gardecote.business.service.qRegistreNavireService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qRegistreNavireRepository;
import com.gardecote.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public qNavire findById(String idcarnet) {
        qNavire authprovEntity =qRegistreNavireRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qNavire> findAll() {
        Iterable<qNavire> entities = qRegistreNavireRepository.findAll();
        List<qNavire> beans = new ArrayList<qNavire>();
        for(qNavire authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qNavire save(qNavire authprov) {
        return update(authprov) ;
    }

    @Override
    public qNavire create(qNavire authprov) {
        qNavire         authprovEntitySaved = qRegistreNavireRepository.save(authprov);

        return authprovEntitySaved;
    }

    @Override
    public qNavire update(qNavire authprov) {
   //     qRegistreNavire authprovEntity = qRegistreNavireRepository.findOne(authprov.getNumimm());

        qNavire authprovEntitySaved = qRegistreNavireRepository.save(authprov);
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

    @Override
    public List<qLic> retActLicences(qNavire navire) {
      Date currentDate = new Date();


        return qRegistreNavireRepository.retActLicences(navire,currentDate);


    }

    public Page<qNavire> findAll(int p, int size,String terme) {

        //	Iterable<qLic> entities = codauthJpaRepository.findAll(new PageRequest(p, size));
        Page<qNavire> entities = qRegistreNavireRepository.findAll(new PageRequest(p, size),terme);
        //	List<qLic> beans = new ArrayList<qLic>();
//		for(qLic codauthEntity : entities) {
        //		beans.add(codauthEntity);
//		}
        //	return beans;
        return entities;
    }
    @Override
    public Page<qNavire> getSuggNavire(String searchpage) {
        return qRegistreNavireRepository.returnSuggNomNav1(new PageRequest(0, 10),searchpage);
    }
}
