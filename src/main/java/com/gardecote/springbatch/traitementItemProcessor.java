package com.gardecote.springbatch;

import com.gardecote.models.qZoneModel;
import com.gardecote.web.qModelDoc;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Dell on 23/04/2017.
 */
public class traitementItemProcessor implements ItemProcessor<qModelDoc, qModelDoc> {
    @Override
    public qModelDoc process(qModelDoc qModelDoc) throws Exception {
        return qModelDoc;
    }

}
