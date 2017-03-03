package com.gardecote.springbatch;

import com.gardecote.business.service.qCategRessourceService;
import com.gardecote.business.service.qEnginsLicenceService;
import com.gardecote.entities.qEnginsLicence;
import com.gardecote.models.qCategRessourceModelInput;
import com.gardecote.models.qCategRessourceModelOutput;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dell on 16/02/2017.
 */
public class categoriesRessourcesItemProcessor implements ItemProcessor<qCategRessourceModelInput,qCategRessourceModelOutput> {
    @Autowired
    private qCategRessourceService categService;
    @Autowired
    private qEnginsLicenceService engLService;

    @Override
    public qCategRessourceModelOutput process(qCategRessourceModelInput qCategRessourceModel) throws Exception {
        qCategRessourceModelOutput outp=new qCategRessourceModelOutput();
                     String engString=null;Integer i=0;
                for(qEnginsLicence cat:categService.getEngL(qCategRessourceModel.getTypeconcessionConcernee_qtypeconcessionpk())) {
                    if(i==0) engString=engString+cat.getEnginDeb().toString()+","+cat.getEnginMar();
                    else {
                        engString=engString+";"+ cat.getEnginDeb().toString()+","+cat.getEnginMar();
                    }
                            i++;
                }
        outp.setIdtypeConcession(qCategRessourceModel.getIdcateg());
        outp.setTypeSupport(qCategRessourceModel.getTypeSupport());
        outp.setEngins(engString);
        outp.setTypeconcessionConcernee(qCategRessourceModel.getTypeconcessionConcernee_qtypeconcessionpk());

        return outp;
    }
}
