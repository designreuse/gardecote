package com.gardecote.business.service.impl;

import com.gardecote.business.service.qPrefixService;
import com.gardecote.data.repository.jpa.qPrefixRepository;
import com.gardecote.entities.enumTypeDoc;
import com.gardecote.entities.qNavire;
import com.gardecote.entities.qPrefix;
import com.gardecote.entities.qPrefixPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 02/12/2016.
 */
@Service
@Transactional
public class qPrefixImpl implements qPrefixService {

    @Autowired
    private qPrefixRepository prefixRepo;

    @Override
    public qPrefix findById(qPrefixPK idpref) {
        qPrefix pref=prefixRepo.findOne(idpref);
        return pref;
    }

    @Override
    public List<qPrefix> findAll() {
        Iterable<qPrefix> prefixes=prefixRepo.findAll();
        List<qPrefix> beans = new ArrayList<qPrefix>();
        for(qPrefix authprovEntity1 : prefixes) {
            beans.add(authprovEntity1);
        }
        return beans;
    }

    @Override
    public qPrefix save(qPrefix entity) {
        return prefixRepo.save(entity);
    }

    @Override
    public void delete(qPrefixPK idPrefix) {
           prefixRepo.delete(idPrefix);
    }

    @Override
    public List<qPrefix> PrefixesByTypeDoc(enumTypeDoc typeDoc) {
        return prefixRepo.prefixesByTypeDoc(typeDoc);
    }
}
