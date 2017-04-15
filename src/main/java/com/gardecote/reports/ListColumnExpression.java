package com.gardecote.reports;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

import java.util.List;

/**
 * Created by Dell on 11/04/2017.
 */
class ListColumnExpression extends AbstractSimpleExpression<String> {
    private static final long serialVersionUID = 1L;

    private String field;

    public ListColumnExpression(String field) {
        this.field = field;
    }

    public String evaluate(ReportParameters reportParameters) {
        List<String> values = reportParameters.getValue("typesCons");
        StringBuffer result = new StringBuffer();
        for (String value : values) {
            if (result.length() > 0) {
                result.append(",");
            }
            result.append(value);
        }
      return result.toString();
    }
}