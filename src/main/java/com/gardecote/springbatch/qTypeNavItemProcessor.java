package com.gardecote.springbatch;

import com.gardecote.models.qTypeNavModel;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Dell on 16/02/2017.
 */
public class qTypeNavItemProcessor implements ItemProcessor<qTypeNavModel, qTypeNavModel> {
    @Override
    public qTypeNavModel process(qTypeNavModel qTypeNavModel) throws Exception {
        return qTypeNavModel;
    }
}
