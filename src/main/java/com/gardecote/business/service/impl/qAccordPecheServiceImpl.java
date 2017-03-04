package com.gardecote.business.service.impl;

import com.gardecote.business.service.qAccordPecheService;
import com.gardecote.data.repository.jpa.qAccordPecheRepository;
import com.gardecote.entities.qAccordPeche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 04/03/2017.
 */

@Service
@Transactional
public class qAccordPecheServiceImpl implements qAccordPecheService {
    @Autowired
    private qAccordPecheRepository qaccordPecheRepository;

    @Override
    public qAccordPeche findById(Integer idact) {
        return qaccordPecheRepository.findOne(idact);
    }

    @Override
    public List<qAccordPeche> findAll() {
        Iterable<qAccordPeche> entities = qaccordPecheRepository.findAll();
        List<qAccordPeche> beans = new ArrayList<qAccordPeche>();
        for(qAccordPeche qAccord : entities) {
            beans.add(qAccord);
        }
        return beans;

    }

    @Override
    public qAccordPeche save(qAccordPeche entity) {
        return qaccordPecheRepository.save(entity);
    }

    @Override
    public qAccordPeche update(qAccordPeche entity) {
        return qaccordPecheRepository.save(entity);
    }

    @Override
    public qAccordPeche create(qAccordPeche entity) {
        return qaccordPecheRepository.save(entity);
    }

    @Override
    public void delete(qAccordPeche idCapture) {
        qaccordPecheRepository.delete(idCapture.getIdentificateurAccord());
    }
}
