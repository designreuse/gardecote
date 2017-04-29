package com.gardecote.reports;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * Created by Dell on 18/04/2017.
 */
public class RetTypePecheH extends AbstractSimpleExpression<String> {

    private static final long serialVersionUID = 1L;

    @Override
    public String evaluate(ReportParameters reportParameters) {
        System.out.println("kkk"+reportParameters.getFieldValue("numeroPage"));
        return  reportParameters.getFieldValue("currentDoc.typePeche").toString();
    }

}