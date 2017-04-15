package com.gardecote.reports;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.*;
import java.awt.List;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import java.util.*;


import com.gardecote.entities.qTypeConcession;
import com.gardecote.web.resultatsCapturesByConcession;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;

import net.sf.dynamicreports.report.builder.FieldBuilder;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;

import net.sf.dynamicreports.report.definition.ReportParameters;

import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dell on 11/04/2017.
 */

public class reportDetailCapture {
    private FieldBuilder<qTypeConcession> typesCField;

    private resultatsCapturesByConcession[] captures = new resultatsCapturesByConcession[1];
    private  String  fileType ;
    private  HttpServletRequest request;
    private  HttpServletResponse response;

    private static final String FILE_FORMAT = "format";
    private static final String DATASOURCE = "datasource";
    private static final String PARAMETER_TYPE = "type";
    private static final String VALUE_TYPE_PDF = "pdf";
    private static final String VALUE_TYPE_XLS = "xls";

    private static final Map<String, String> FILE_TYPE_2_CONTENT_TYPE =
            new HashMap<String, String>();
    static {
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_PDF, "application/pdf");
        FILE_TYPE_2_CONTENT_TYPE.put(VALUE_TYPE_XLS, "application/vnd.ms-excel");
    }
    OutputStream out;
    public String getFileType() {
        return fileType;
    }

    public OutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = out;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public resultatsCapturesByConcession[] getCaptures() {
        return captures;
    }

    public void setCaptures(resultatsCapturesByConcession[] captures) {
        this.captures = captures;
    }

    public reportDetailCapture(resultatsCapturesByConcession[] captures1, String  fileType1, HttpServletRequest request1, HttpServletResponse response1) throws IOException, ServletException, Exception {

        this.captures=captures1;
        this.fileType=fileType1;
        this.request=request1;
        this.response=response1;

        build(captures1,fileType,request,response,out);
    }
    private void build(resultatsCapturesByConcession[] captures,String  fileType,HttpServletRequest request,HttpServletResponse response,OutputStream out) throws IOException, ServletException, Exception {
        SubreportBuilder subreport = cmp.subreport(createSubreport())
                  .setDataSource(exp.subDatasourceBeanCollection("resultatCaptures"));
        JasperReportBuilder report = report();

        try {
            report
                    .setTemplate(Templates.reportTemplate)
                    .setDataSource(new JRBeanArrayDataSource(captures))
                    .title(
                            Templates.createTitleComponent("Captures Détaillé des especes de : " + new Date()),
                            cmp.horizontalList().setStyle(stl.style(10)).setGap(50).add(
                                    cmp.hListCell(createCustomerComponentPeriode("Periode :")).heightFixedOnTop(),
                                    cmp.hListCell(createCustomerComponentTypes("Types de concessions :")).heightFixedOnTop()),
                            cmp.verticalGap(10))
                    .fields(
                    field("typesCons", List.class),
                            field("searchDateCapture1", Date.class),
                            field("searchDateCapture2", Date.class))


                    .detailFooter(cmp.horizontalList(subreport))
                    .pageFooter(Templates.footerComponent);

    //        response.setContentType(FILE_TYPE_2_CONTENT_TYPE.get(fileType));
          OutputStream out1=response.getOutputStream();
            if (VALUE_TYPE_PDF.equals(fileType)) {
                report.toPdf(out1);
            } else if (VALUE_TYPE_XLS.equals(fileType)) {
                report.toExcelApiXls(out1);
            }
   //        report.toPdf(out);
            out1.close();


        } catch (DRException e) {

            e.printStackTrace();

        }

//       response.getOutputStream().flush();

    }
    private ComponentBuilder<?, ?> createCustomerComponentPeriode(String label) {

        HorizontalListBuilder list = cmp.horizontalList().setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));

        addCustomerAttributeDate(list, "Date 1 : ", captures[0].getSearchDateCapture1());

        addCustomerAttributeDate(list, "Date 2 : ", captures[0].getSearchDateCapture1());


        return cmp.verticalList(

                cmp.text(label).setStyle(Templates.boldStyle),

                list);

    }

    private ComponentBuilder<?, ?> createCustomerComponentTypes(String label) {

        HorizontalListBuilder list = cmp.horizontalList().setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));





       addCustomerAttributeTypeConces(list, captures[0].getTypesCons());
        return cmp.verticalList( cmp.text(label).setStyle(Templates.boldStyle),list);//  return cmp.verticalList( cmp.text(label).setStyle(Templates.boldStyle),list);

    }

    private void addCustomerAttributeDate(HorizontalListBuilder list, String label, Date value) {

        if (value != null) {

            list.add(cmp.text(label + ":").setFixedColumns(8).setStyle(Templates.boldStyle), cmp.text(value)).newRow();

        }

    }
    private void addCustomerAttributeTypeConces(HorizontalListBuilder list, java.util.List<String> types) {
        StringBuffer result = new StringBuffer();
        if (types.size() != 0) {
         for (int i=0;i<types.size();i++) {
     //        list.add( cmp.text(types.get(i))).newRow();
             if (result.length() > 0) {
                 result.append(",");
             }
             result.append(types.get(i));
           }
            list.add( cmp.text(result.toString()));



        }

    }

    private JasperReportBuilder createSubreport() {

        StyleBuilder columnStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());
        JasperReportBuilder report = report();

        report.setPageFormat(PageType.A4, net.sf.dynamicreports.report.constant.PageOrientation.LANDSCAPE);
        report()
                .setTemplate(Templates.reportTemplate)
                .title(cmp.text("Details de captures de").setStyle(Templates.boldCenteredStyle))
                .pageFooter(cmp.pageXofY());
        TextColumnBuilder<Integer> rowNumberColumn = col.reportRowNumberColumn().setFixedColumns(2);
        report.addColumn(rowNumberColumn);
        for (int i = 0; i < captures[0].getResultatsNamesCol().size(); i++) {
            report.addColumn(col.column(captures[0].getResultatsEntetesCol().get(i), captures[0].getResultatsNamesCol().get(i), type.integerType())).setTextStyle(columnStyle);

        }
        report.addColumn(  col.column("Expression column", new ExpressionColumn()));

        return report;
    }

    private class ExpressionColumn extends AbstractSimpleExpression<BigDecimal>{

        private static final long serialVersionUID = 1L;

        @Override

        public BigDecimal evaluate(ReportParameters reportParameters) {

            return new BigDecimal(12) ;

        }

    }



        }
