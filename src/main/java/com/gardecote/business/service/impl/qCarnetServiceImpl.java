/*
 * Created on 26 sept. 2016 ( Time 10:51:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gardecote.business.service.impl;
import com.gardecote.business.service.qCarnetService;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;




import com.gardecote.data.repository.jpa.qCarnetRepository;
import com.gardecote.data.repository.jpa.qConcessionRepository;
import com.gardecote.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of AuthprovService
 */
@Service
@Transactional
public class qCarnetServiceImpl implements qCarnetService {

	@Autowired
	private qCarnetRepository qCarnetRepository;
	@Autowired
	private qConcessionRepository qconcessionRepository;



	@Override
	public qCarnet findById(qCarnetPK idcarnet) {
		qCarnet authprovEntity = qCarnetRepository.findOne(idcarnet);
		return authprovEntity;
	}

	@Override
	public List<qCarnet> findAll() {
		Iterable<qCarnet> entities = qCarnetRepository.findAll();
		List<qCarnet> beans = new ArrayList<qCarnet>();
		for(qCarnet authprovEntity1 : entities) {
			beans.add(authprovEntity1);
		}
		return beans;
	}

	@Override
	public qCarnet save(qCarnet authprov) {
		return update(authprov) ;
	}

	@Override
	public qCarnet create(qCarnet authprov) {

		qCarnet authprovEntitySaved = qCarnetRepository.save(authprov);
		return authprovEntitySaved;
	}

	@Override
	public qCarnet update(qCarnet authprov) {
		qCarnet authprovEntity = qCarnetRepository.findOne(authprov.getCarnetPK());

		qCarnet authprovEntitySaved = qCarnetRepository.save(authprovEntity);
		return authprovEntitySaved ;
	}

	@Override
	public void delete(qCarnetPK  idauthpr) {
		qCarnetRepository.delete(idauthpr);
	}

	public qCarnetRepository getAuthprovJpaRepository() {
		return qCarnetRepository;
	}

	public void setAuthprovJpaRepository(qCarnetRepository authprovJpaRepository) {
		this.qCarnetRepository = authprovJpaRepository;
	}

	@Override
	public qCarnet entrerDansLeSystem(qCarnet carnet) {
		List<qPageCarnet>  pgs=new ArrayList<qPageCarnet>();
		if (carnet.getTypeDoc().equals(enumTypeDoc.Journal_Peche)){
			carnet.setNbrLigneParPage(10);
     			for(int i=0;i<carnet.getNbrPages();i++) {
				qPageCarnet  qp= new qPageMarree(carnet.getPrefixNumerotation().toString()+Long.toString(carnet.getNumeroDebutPage()+i),
						carnet.getNumeroDebutPage()+i,enumEtatPage.LIBRE,carnet,null,null );
				//   qp.setQcarnet(this);

				pgs.add(qp);
			}
		}
		if (carnet.getTypeDoc().equals(enumTypeDoc.Fiche_Debarquement)) {
			carnet.setNbrLigneParPage(9);

			for(int i=0;i<carnet.getNbrPages();i++) {
				qPageCarnet  qp= new qPageDebarquement(carnet.getPrefixNumerotation().toString()+Long.toString(carnet.getNumeroDebutPage()+i),
						carnet.getNumeroDebutPage()+i,enumEtatPage.LIBRE,carnet,null,null);
				pgs.add(qp);
			}
		}
		if(carnet.getTypeDoc().equals(enumTypeDoc.Fiche_Traitement)) {
			carnet.setNbrLigneParPage(8);
			for(int i=0;i<carnet.getNbrPages();i++) {
				qPageCarnet  qp= new qPageTraitement(carnet.getPrefixNumerotation().toString()+Long.toString(carnet.getNumeroDebutPage()+i),carnet.getNumeroDebutPage()+i,enumEtatPage.LIBRE,carnet,null,null,null);


				pgs.add(qp);
			}
		}
		carnet.setPages(pgs);
		qCarnetRepository.save(carnet);
		return carnet;

	}

	@Override
	public qCarnet attribuerCarnetAuNavire(qCarnet carnet, qNavire navire, qLic licence, qUsine usine) {
		qConcession conHors= qconcessionRepository.findOne("Hors");

		carnet.setQnavire(navire);
		carnet.setQusine(usine);
        if(licence instanceof qLicenceLibre )  carnet.setQconcession(conHors);
        if(licence instanceof qLicenceNational )  carnet.setQconcession(((qLicenceNational) licence).getQconcession());

		qCarnetRepository.save(carnet);
		return carnet;

	}
}
