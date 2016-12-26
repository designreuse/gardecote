package com.gardecote.business.service.impl;

import com.gardecote.business.service.qMareeAnnexeService;
import com.gardecote.data.repository.jpa.*;
import com.gardecote.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dell on 25/12/2016.
 */
@Service
@Transactional
public class qMareeAnnexeServiceImpl implements qMareeAnnexeService {

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
    public qMarreeAnnexe save(qMarreeAnnexe authprov) {

        //     Session session = em.unwrap(Session.class);
        qMarreeAnnexe authprovEntity = qmareeannexeRepository.findOne(authprov.getqDocPK());
        return qmareeannexeRepository.save(authprov);
        //       if (authprovEntity == null) {
        //         em.persist(authprov);
        //         return authprov;
        //     } else {
        //   session.evict(authprovEntity);
        //      return em.merge(authprov);
        //     }
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


            for(Iterator<qPageAnnexe> iter = ((qMarreeAnnexe) ff).getPages().iterator(); iter.hasNext();) {
                qPageAnnexe currentp=iter.next();

                for (Iterator<qJourMereAnnexe> iter1 = currentp.getListJours().iterator(); iter1.hasNext(); )
                {  qJourMereAnnexe currj=iter1.next();
                    currj.setPageMarree(null);
                    jms.add(currj);
                }
                currentp.setListJours(null);
                currentp.setQmarreeAnexe(null);
                qpagecarnetAnnexRepository.save(currentp);
            }
            ((qMarreeAnnexe) ff).setPages(null);
            qmareeannexeRepository.save(ff);
            for(qJourMereAnnexe jk:jms) {
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
