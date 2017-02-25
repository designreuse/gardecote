package com.gardecote.springbatch;

import com.gardecote.entities.qLic;
import com.gardecote.models.qLicenceModel;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Dell on 07/02/2017.
 */
public class LicItemProcessor implements ItemProcessor<qLicenceModel, qLicenceModel> {
    @Override
    public qLicenceModel process(qLicenceModel qLic) throws Exception {
        System.out.println("Processing result :"+qLic);
        if(qLic.getTypencad() == 55) {
            return null;
        }
        return qLic;

    }
}
