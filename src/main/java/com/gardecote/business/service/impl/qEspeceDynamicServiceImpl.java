package com.gardecote.business.service.impl;

import com.gardecote.business.service.qEspeceDynamicService;
import com.gardecote.data.repository.jpa.qEspeceDynamicRepository;
import com.gardecote.entities.qEspeceDynamic;
import com.gardecote.entities.qEspeceDynamicPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 28/03/2017.
 */
@Service
@Transactional
public class qEspeceDynamicServiceImpl implements qEspeceDynamicService {
    @Autowired
    private qEspeceDynamicRepository espDynRepository;

    @Override
    public qEspeceDynamic findById(qEspeceDynamicPK numimm) {
        return espDynRepository.findOne(numimm);
    }

    @Override
    public List<qEspeceDynamic> findAll() {
        Iterable<qEspeceDynamic> entities = espDynRepository.findAll();
        List<qEspeceDynamic> beans = new ArrayList<qEspeceDynamic>();
        for(qEspeceDynamic batobservEntity : entities) {
            beans.add(batobservEntity);
        }
        return beans;

    }

    @Override
    public qEspeceDynamic save(qEspeceDynamic entity) {
        return espDynRepository.save(entity);
    }

    @Override
    public qEspeceDynamic update(qEspeceDynamic entity) {
        return espDynRepository.save(entity);
    }

    @Override
    public qEspeceDynamic create(qEspeceDynamic entity) {
        return espDynRepository.save(entity);
    }

    @Override
    public void delete(qEspeceDynamicPK numimm) {
        espDynRepository.delete(numimm);
    }
}
