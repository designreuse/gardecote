package com.gardecote.business.service.impl;

import com.gardecote.business.service.qEnginsLicenceService;
import com.gardecote.data.repository.jpa.qEnginLicenceRepository;

import com.gardecote.entities.qEnginsLicence;
import com.gardecote.entities.qEnginsLicencePK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 17/11/2016.
 */
@Service
@Transactional
public class qEnginsLicenceServiceImpl implements qEnginsLicenceService {
    @Autowired
    private qEnginLicenceRepository enginsLicenceRepository;

    @Override
    public qEnginsLicence findById(qEnginsLicencePK numimm) {
        qEnginsLicence captures2Entity = enginsLicenceRepository.findOne(numimm);
        return captures2Entity ;

    }

    @Override
    public List<qEnginsLicence> findAll() {
        Iterable<qEnginsLicence> entities = enginsLicenceRepository.findAll();
        List<qEnginsLicence> beans = new ArrayList<qEnginsLicence>();
        for(qEnginsLicence captures2Entity : entities) {
            beans.add(captures2Entity);
        }
        return beans;

    }

    @Override
    public qEnginsLicence save(qEnginsLicence entity) {
        return update(entity) ;
    }

    @Override
    public qEnginsLicence update(qEnginsLicence entity) {
        qEnginsLicence captures2EntitySaved = enginsLicenceRepository.save(entity);
        return captures2EntitySaved;
    }

    @Override
    public qEnginsLicence create(qEnginsLicence entity) {
        qEnginsLicence captures2Entity = enginsLicenceRepository.findOne(entity.getLicencePK());
        //	if( captures2Entity != null ) {
        //		throw new Exception("already exists");
        //	}
        //captures2Entity = new qEnginPeche();

        qEnginsLicence captures2EntitySaved = enginsLicenceRepository.save(entity );
        return captures2EntitySaved;
    }

    @Override
    public void delete(qEnginsLicencePK numimm) {
        enginsLicenceRepository.delete(numimm);
    }

    public qEnginLicenceRepository getEnginsLicenceRepository() {
        return enginsLicenceRepository;
    }

    public void setEnginsLicenceRepository(qEnginLicenceRepository enginsLicenceRepository) {
        this.enginsLicenceRepository = enginsLicenceRepository;
    }
}
