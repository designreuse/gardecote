package com.gardecote.springbatch.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ItemProcessListener;

public class LogProcessorListener implements ItemProcessListener<Object, Object> {

    private static final Log log = LogFactory.getLog(LogProcessorListener.class);

    public void afterProcess(Object item, Object result) {
        if(item!=null) log.info("Input to Processor: " + item.toString());
        if(result!=null) log.info("Output of Processor: " + result.toString());
    }

    public void beforeProcess(Object item) {
    }

    public void onProcessError(Object item, Exception e) {
    }

}