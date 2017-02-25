package com.gardecote.springbatch.jobs;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;


import com.gardecote.business.service.qLicenceService;
import com.gardecote.business.service.qRegistreNavireService;
import com.gardecote.entities.qLic;
import com.gardecote.entities.qNavire;
import com.gardecote.models.qLicenceModel;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Created by Dell on 09/02/2017.
 */
public class Writer implements ItemWriter<qLic>{
    @Autowired
    private qLicenceService licenceService;
    @Autowired
    private qRegistreNavireService regNavireService;
    @Override
    public void write(List<? extends qLic> items) throws Exception {
           //first insert the data in the database
          // qLic lic = suggestedLic.getPodcast();
        for(qLic yy:items) {
            qNavire nav=regNavireService.findById(yy.getNumimm());
            if(nav==null) {
              //qNavire navire=new qNavire(yy.getNumimm(),yy.getNomnav(),yy.getNomar(),yy.getLongg(),yy.getPuimot(),yy.getNation(),yy.getLarg(),yy.getCount(),yy.getNbrhomm(),
              // yy.getEff(),yy.getAnneeconstr(),yy.getCalpoids(),yy.getGt(),yy.getKw(),yy.getTjb(),yy.getImo(),yy.getPort(),yy.getRadio(),yy.getBalise(),yy.getDateDebutAuth());
                regNavireService.create(yy.getQnavire());
                System.out.println("bat writer with "+yy.getQnavire().getNomnav());
            }
            else
            regNavireService.save(yy.getQnavire());
            licenceService.save(yy);
        }
     //      entityManager.persist(suggestedLic);
     //      entityManager.flush();
    }



}