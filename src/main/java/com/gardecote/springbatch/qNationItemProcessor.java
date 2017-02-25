package com.gardecote.springbatch;

import com.gardecote.models.qNationModel;
import com.gardecote.models.qZoneModel;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Dell on 16/02/2017.
 */
public class qNationItemProcessor implements ItemProcessor<qNationModel, qNationModel> {
    @Override
    public qNationModel process(qNationModel qNationModel) throws Exception {
        return qNationModel;
    }
}
