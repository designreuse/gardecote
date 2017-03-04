package com.gardecote.business.service.impl;

import com.gardecote.business.service.qEnginAuthoriseeService;

import com.gardecote.data.repository.jpa.qEnginsAuthoriseeRepository;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qEnginAuthorisee;
import com.gardecote.entities.qEnginAuthoriseePK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Dell on 04/03/2017.
 */

@Service
@Transactional
public class qEnginsAuthoriseeServiceImpl implements qEnginAuthoriseeService {

    @Autowired
    private qEnginsAuthoriseeRepository qenginsAuthoriseeRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public qEnginAuthorisee findById(qEnginAuthoriseePK numimm) {
        return null;
    }

    @Override
    public List<qEnginAuthorisee> findAll() {
        return null;
    }

    @Override
    public qEnginAuthorisee save(qEnginAuthorisee entity) {
        qEnginAuthorisee tt=em.find(qEnginAuthorisee.class,entity.getEnginAuthoriseePK());
        //      System.out.println(tt.toString());
        if (tt!=null){
            System.out.println(tt.toString());
            em.merge(entity);
        }else{
            em.persist(entity);
        }

        return qenginsAuthoriseeRepository.save(entity);
    }

    @Override
    public qEnginAuthorisee update(qEnginAuthorisee entity) {
        return null;
    }

    @Override
    public qEnginAuthorisee create(qEnginAuthorisee entity) {
        return null;
    }

    @Override
    public void delete(qEnginAuthorisee numimm) {
        qenginsAuthoriseeRepository.delete(numimm.getEnginAuthoriseePK());
    }
}
