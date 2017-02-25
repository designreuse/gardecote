package com.gardecote.business.service.impl;

import com.gardecote.business.service.qSeqService;
import com.gardecote.business.service.qTraitementService;
import com.gardecote.data.repository.jpa.MyCustomEntityRepository;
import com.gardecote.data.repository.jpa.qTraitementRepository;
import com.gardecote.entities.qDoc;
import com.gardecote.entities.qDocPK;
import com.gardecote.entities.qTraitement;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Dell on 23/02/2017.
 */
@Service
@Transactional
public class qTraitementServiceImpl implements qTraitementService, MyCustomEntityRepository {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private qTraitementRepository qTraitementRepository;
    @Override
    public qTraitement findById(qDocPK idocpk) {
        return null;
    }

    @Override
    public void detach(Entity ent) {
        entityManager.detach(ent);
    }

    @Override
    public List<qTraitement> findAll() {
        return null;
    }

    @Override
    public qTraitement save(qTraitement entity) {
        return null;
    }

    @Override
    public qTraitement update(qTraitement entity) {
      //   entityManager.clear();
     //   entityManager.refresh(entity);
        System.out.println("First"+entity);

      qTraitement trEnCours=qTraitementRepository.findOne(entity.getqDocPK());
        System.out.println("saved before"+entity);
     //   entityManager.detach(trEnCours);
        //     Session session = em.unwrap(Session.class);
       // qTraitement authprovEntity = qTraitementRepository.findOne(authprov.getqDocPK());
      //  return qdocRepository.save(authprov);
     qTraitement tt=entityManager.find(qTraitement.class,entity.getqDocPK());
  //      System.out.println(tt.toString());
       if (tt!=null){
            System.out.println(tt.toString());
            entityManager.merge(entity);
        }else{
            entityManager.persist(entity);
        }

    //    qDoc currentD= entityManager.find(qDoc.class,entity.getqDocPK());
    /*    Session session = entityManager.unwrap(Session.class);
        session.clear();
               if (currentD== null) {
                   entityManager.persist(entity);
                 return entity;
             } else {
                   entityManager.flush();
           session.evict(currentD);

              return entityManager.merge(entity);
             } */
return entity;

    }

    @Override
    public qTraitement create(qTraitement entity) {
        return null;
    }

    @Override
    public void delete(qDocPK idCapture) {

    }
}
