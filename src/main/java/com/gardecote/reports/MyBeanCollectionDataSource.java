package com.gardecote.reports;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Collection;

/**
 * Created by Dell on 18/04/2017.
 */
public class MyBeanCollectionDataSource extends JRBeanCollectionDataSource {
    static final String BEAN_MAPPING = "this";
    protected Object getFieldValue(Object bean, JRField field) throws JRException {
            String prop = getPropertyName(field);
            Object value;
        if (prop.equals(BEAN_MAPPING)) {
                value = bean;
        } else {
                value = super.getFieldValue(bean, field);
        }
        return value;
    }

    public MyBeanCollectionDataSource(Collection<?> beanCollection) {
        super(beanCollection);
    }

    public MyBeanCollectionDataSource(Collection<?> beanCollection, boolean isUseFieldDescription) {
        super(beanCollection, isUseFieldDescription);
    }
}