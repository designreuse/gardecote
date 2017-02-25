package com.gardecote.springbatch;

import com.gardecote.models.qTypeNavModel;
import com.gardecote.models.qZoneModel;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Dell on 16/02/2017.
 */
public class qZoneItemProcessor implements ItemProcessor<qZoneModel, qZoneModel> {
    @Override
    public qZoneModel process(qZoneModel qZoneModel) throws Exception {
        return qZoneModel;
    }
}
