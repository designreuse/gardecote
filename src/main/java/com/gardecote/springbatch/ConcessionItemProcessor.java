package com.gardecote.springbatch;

import com.gardecote.business.service.qCategRessourceService;
import com.gardecote.entities.qCategRessource;
import com.gardecote.models.qConcessionModel;
import com.gardecote.models.qLicenceModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dell on 16/02/2017.
 */
public class ConcessionItemProcessor implements ItemProcessor<qConcessionModel, qConcessionModel> {

    @Autowired
    private qCategRessourceService categService;
    @Override
    public qConcessionModel process(qConcessionModel qConcessionModel) throws Exception {
        String engString=null;Integer i=0;
        for(qCategRessource cat:categService.getCategories(qConcessionModel.getRefConcession())) {
            if(i==0) engString=engString+cat.getIdtypeConcession().toString();
            else {
                engString=engString+";"+ cat.getIdtypeConcession().toString();
            }
            i++;
        }
        qConcessionModel.setCategoriesRessources(engString);
        return null;
    }
}
