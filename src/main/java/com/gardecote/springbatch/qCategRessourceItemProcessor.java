package com.gardecote.springbatch;

import com.gardecote.models.qCategRessourceModelInput;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Dell on 16/02/2017.
 */
public class qCategRessourceItemProcessor implements ItemProcessor<qCategRessourceModelInput, qCategRessourceModelInput> {
    @Override
    public qCategRessourceModelInput process(qCategRessourceModelInput qCategRessourceModel) throws Exception {
        return qCategRessourceModel;
    }
}
