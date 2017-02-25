package com.gardecote.business.service.impl;

import com.gardecote.business.service.qMareeAnnexeService;
import com.gardecote.data.repository.jpa.*;
import com.gardecote.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dell on 25/12/2016.
 */
@Service
@Transactional
public class qMareeAnnexeServiceImpl implements qMareeAnnexeService {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private qMarreeAnnexeRepository qmareeannexeRepository;
    @Autowired
    private qPageCarnetAnnexRepository qpagecarnetAnnexRepository;
    @Autowired
    private qJourMereAnnexeRepository qjourmereAnnexeRepository;


    @Override
    public qMarreeAnnexe findById(qDocPK idcarnet) {
        qMarreeAnnexe authprovEntity = qmareeannexeRepository.findOne(idcarnet);
        return authprovEntity;
    }

    @Override
    public List<qMarreeAnnexe> findAll() {
        Iterable<qMarreeAnnexe>  lst= qmareeannexeRepository.findAll();
        List<qMarreeAnnexe> annexes = new ArrayList<qMarreeAnnexe>();
        for(qMarreeAnnexe annexesc : lst) {
            annexes.add(annexesc);
        }
        return annexes;
    }

    @Override
    public qMarreeAnnexe save(qMarreeAnnexe authprov) {

        qMarreeAnnexe tt=entityManager.find(qMarreeAnnexe.class,authprov.getqDocPK());
        //      System.out.println(tt.toString());
        if (tt!=null){
            System.out.println(tt.toString());
            entityManager.merge(authprov);
        }else{
            entityManager.persist(authprov);
        }

        qMarreeAnnexe authprovEntity = qmareeannexeRepository.findOne(authprov.getqDocPK());
        return qmareeannexeRepository.save(authprov);

    }

    @Override
    public qMarreeAnnexe create(qMarreeAnnexe authprov) {



        qMarreeAnnexe authprovEntitySaved = qmareeannexeRepository.save(authprov);
        return authprovEntitySaved;
    }

    @Override
    public qMarreeAnnexe update(qMarreeAnnexe authprov) {
        // qDoc authprovEntity = qdocRepository.findOne(authprov.getqDocPK());

        qMarreeAnnexe authprovEntitySaved = qmareeannexeRepository.save(authprov);
        return authprovEntitySaved ;
    }

    @Override
    public void delete(qDocPK  idauthpr) {
        qMarreeAnnexe ff= qmareeannexeRepository.findOne(idauthpr);
        List<qJourMereAnnexe> jms=new ArrayList<qJourMereAnnexe>();
        if(ff instanceof qMarreeAnnexe) {

            for (Iterator<qPageAnnexe> iter = ((qMarreeAnnexe) ff).getPages().iterator(); iter.hasNext(); ) {
                qPageAnnexe currentp = iter.next();

                for (Iterator<qJourMereAnnexe> iter1 = currentp.getListJours().iterator(); iter1.hasNext(); ) {
                    qJourMereAnnexe currj = iter1.next();
                    currj.setPageMarree(null);
                    jms.add(currj);
                }
                currentp.setListJours(null);
                currentp.setQmarreeAnexe(null);

                qpagecarnetAnnexRepository.save(currentp);
            }

            ((qMarreeAnnexe) ff).setMarreePrincipal(null);
           qmareeannexeRepository.save(ff);
            for(qJourMereAnnexe jk:jms) {
                jk.setPageMarree(null);
                qjourmereAnnexeRepository.delete(jk);
            }
            qmareeannexeRepository.delete(ff.getqDocPK());
        }

    }

    public qMarreeAnnexeRepository getQmareeannexeRepository() {
        return qmareeannexeRepository;
    }

    public void setQmareeannexeRepository(qMarreeAnnexeRepository qmareeannexeRepository) {
        this.qmareeannexeRepository = qmareeannexeRepository;
    }

    public qPageCarnetAnnexRepository getQpagecarnetAnnexRepository() {
        return qpagecarnetAnnexRepository;
    }

    public void setQpagecarnetAnnexRepository(qPageCarnetAnnexRepository qpagecarnetAnnexRepository) {
        this.qpagecarnetAnnexRepository = qpagecarnetAnnexRepository;
    }

    public qJourMereAnnexeRepository getQjourmereAnnexeRepository() {
        return qjourmereAnnexeRepository;
    }

    public void setQjourmereAnnexeRepository(qJourMereAnnexeRepository qjourmereAnnexeRepository) {
        this.qjourmereAnnexeRepository = qjourmereAnnexeRepository;
    }
}
