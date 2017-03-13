package com.gardecote.business.service.impl;

import com.gardecote.business.service.qRegistreNavireService;
import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qRegistreNavireLegalRepository;
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
    @Autowired
    private qRegistreNavireLegalRepository  qregistreNavireLegalRepository;


    @Override
    public qBateau findById(String idcarnet) {
        qBateau authprovEntity =qRegistreNavireRepository.findOne(idcarnet);
        return authprovEntity;
    }
    public qNavireLegale findLegalById(String idact) {
        qNavireLegale authprovEntity =qregistreNavireLegalRepository.findOne(idact);
        return authprovEntity;
    };

    @Override
    public List<qLic> findLicences(qNavireLegale vleg) {
        List<qLic> nvs=qregistreNavireLegalRepository.findLicences(vleg);
        return nvs;
    }

    @Override
    public List<qBateau> findAll() {
        Iterable<qBateau> entities = qRegistreNavireRepository.findAll();
        List<qBateau> beans = new ArrayList<qBateau>();
        for(qBateau authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qBateau save(qBateau authprov) {
        return update(authprov) ;
    }

    @Override
    public qBateau create(qBateau authprov) {
        qBateau         authprovEntitySaved = qRegistreNavireRepository.save(authprov);

        return authprovEntitySaved;
    }

    @Override
    public qBateau update(qBateau authprov) {
   //     qRegistreNavire authprovEntity = qRegistreNavireRepository.findOne(authprov.getNumimm());

        qBateau authprovEntitySaved = qRegistreNavireRepository.save(authprov);
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
    public List<qLic> retActLicences(qBateau navire) {
      Date currentDate = new Date();

        return qRegistreNavireRepository.retActLicences(navire,currentDate);


    }
    public Page<qNavireLegale> findAllLegal(int p, int size,String terme) {

        //	Iterable<qLic> entities = codauthJpaRepository.findAll(new PageRequest(p, size));
        Page<qNavireLegale> entities = qregistreNavireLegalRepository.findAll(new PageRequest(p, size),terme);
        //	List<qLic> beans = new ArrayList<qLic>();
//		for(qLic codauthEntity : entities) {
        //		beans.add(codauthEntity);
//		}
        //	return beans;
        return entities;
    }
    public List<qNavireLegale> findAllLegal() {

        //	Iterable<qLic> entities = codauthJpaRepository.findAll(new PageRequest(p, size));
        Iterable<qNavireLegale> entities = qregistreNavireLegalRepository.findAll();
        List<qNavireLegale> beans = new ArrayList<qNavireLegale>();
        for(qNavireLegale authprovEntity1 : entities) {
            beans.add(authprovEntity1);
        }

        return beans;
        //	List<qLic> beans = new ArrayList<qLic>();
//		for(qLic codauthEntity : entities) {
        //		beans.add(codauthEntity);
//		}
        //	return beans;

    }
    public Page<qBateau> findAll(int p, int size,String terme) {

        //	Iterable<qLic> entities = codauthJpaRepository.findAll(new PageRequest(p, size));
        Page<qBateau> entities = qRegistreNavireRepository.findAll(new PageRequest(p, size),terme);
        //	List<qLic> beans = new ArrayList<qLic>();
//		for(qLic codauthEntity : entities) {
        //		beans.add(codauthEntity);
//		}
        //	return beans;
        return entities;
    }
    @Override
    public Page<qBateau> getSuggNavire(String searchpage) {
        return qRegistreNavireRepository.returnSuggNomNav1(new PageRequest(0, 10),searchpage);
    }
}
