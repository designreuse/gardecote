package com.gardecote.springbatch;

import com.gardecote.web.qModelDoc;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

/**
 * Created by Dell on 23/04/2017.
 */
public class ReturnsListReader implements ItemReader<List<?>> {
    private List<qModelDoc> domains;
    @Override
    public List<?> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return domains;
    }
}
