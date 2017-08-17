package com.gardecote.reports;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.*;
import java.awt.List;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import java.net.URL;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import com.gardecote.entities.qTypeConcession;
import com.gardecote.web.resultatsCapturesByConcession;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import net.sf.dynamicreports.jasper.constant.JasperProperty;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;

import net.sf.dynamicreports.report.builder.FieldBuilder;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.datatype.FloatType;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.group.CustomGroupBuilder;
import net.sf.dynamicreports.report.builder.group.GroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import net.sf.dynamicreports.report.constant.*;
import net.sf.dynamicreports.report.datasource.DRDataSource;

import net.sf.dynamicreports.report.definition.ReportParameters;

import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.DefaultFormatFactory;
import net.sf.jasperreports.engine.util.FormatFactory;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
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

    public reportDetailCapture(resultatsCapturesByConcession[] captures1, String  fileType1, Boolean exludeDetails,HttpServletRequest request1, HttpServletResponse response1) throws IOException, ServletException, Exception {

        this.captures=captures1;
        this.fileType=fileType1;
        this.request=request1;
        this.response=response1;
   if(fileType.equals("pdf"))       build(captures1,fileType,exludeDetails,request,response,out);
   else                             buildXls(captures1,fileType,exludeDetails,request,response,out);
    }
    private void buildXls(resultatsCapturesByConcession[] captures,String  fileType, Boolean exludeDetails,HttpServletRequest request,HttpServletResponse response,OutputStream out) throws IOException, ServletException, Exception {

        ClassLoader classloader =           org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
        URL res = classloader.getResource("org/apache/poi/poifs/filesystem/POIFSFileSystem.class");
        String path = res.getPath();
        StyleBuilder style = stl.style().setRotation(Rotation.UPSIDE_DOWN);
        System.out.println("Core POI came from " + path);

        JasperXlsExporterBuilder xlsExporter = export.xlsExporter("c:/AA/report.xls")
                .setDetectCellType(true)
                .setIgnorePageMargins(true)
                .setWhitePageBackground(false)
                .setRemoveEmptySpaceBetweenColumns(true);

        SubreportBuilder subreport = cmp.subreport(createSubreport(exludeDetails))
                .setDataSource(exp.subDatasourceBeanCollection("resultatCaptures"));
     //   CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        JasperReportBuilder report = report();

        try {
            report
                    .setTemplate(Templates.reportTemplate)
                    .addProperty(JasperProperty.EXPORT_XLS_FREEZE_ROW, "2")
                    .ignorePageWidth()
                    .ignorePagination()
                    .background(cmp.text("Confidentiel ....")).setBackgroundStyle(style)
                    //.setTemplate(Templates.reportTemplate)
                    .setDataSource(new JRBeanArrayDataSource(captures))
                    .title(
                            Templates.createTitleComponent("Captures Détaillés "),
                            cmp.horizontalList().setStyle(stl.style(10)).setGap(50).add(
                                    cmp.hListCell(createCustomerComponentPeriode("Periode :")).heightFixedOnTop(),
                                    cmp.hListCell(createCustomerComponentTypes("Types de pêche :")).heightFixedOnTop()),
                            cmp.verticalGap(10))
                    .fields(
                            field("typesCons", List.class),
                            field("searchDateCapture1", Date.class),
                            field("searchDateCapture2", Date.class))
                    .detailFooter(cmp.horizontalList(subreport))
                    .pageFooter(Templates.footerComponent);
                  //  .toXls(xlsExporter);
         //            response.setContentType(FILE_TYPE_2_CONTENT_TYPE.get(fileType));
                     OutputStream out1=response.getOutputStream();

            if (VALUE_TYPE_PDF.equals(fileType)) {
                report.toPdf(out1);
            } else if (VALUE_TYPE_XLS.equals(fileType)) {
              //  report.toXls(xlsExporter); //xlsExporter report.toExcelApiXls(out1);
                report.toXls(out1);
            }
            //        report.toPdf(out);
            out1.close();


        } catch (DRException e) {

            e.printStackTrace();

        }

//       response.getOutputStream().flush();

    }
    private class FormatFactory extends DefaultFormatFactory {
        public NumberFormat createNumberFormat(String pattern, Locale locale) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
            symbols.setDecimalSeparator(',');
            symbols.setGroupingSeparator(' ');
            NumberFormat nf = super.createNumberFormat(pattern, locale);
            if (nf instanceof DecimalFormat) {
                ((DecimalFormat) nf).setDecimalFormatSymbols(symbols);
            }
            return nf;
        }
    }
    private void build(resultatsCapturesByConcession[] captures,String  fileType, Boolean exludeDetails,HttpServletRequest request,HttpServletResponse response,OutputStream out) throws IOException, ServletException, Exception {

        SubreportBuilder subreport = cmp.subreport(createSubreport(exludeDetails))
                 .setDataSource(exp.subDatasourceBeanCollection("resultatCaptures"));
       // JasperReportBuilder reportS = report();


        JasperReportBuilder report = report();

        report.setParameter(JRParameter.REPORT_FORMAT_FACTORY, new FormatFactory());

        try {
            report
                   .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                   .setTemplate(Templates.reportTemplate)
                   .setDataSource(new JRBeanArrayDataSource(captures))
                   .title(
                            Templates.createTitleComponent("Détails de captures "),
                            cmp.horizontalList().setStyle(stl.style(10)).setGap(50).add(
                                    cmp.hListCell(createCustomerComponentPeriode("Periode :")).heightFixedOnTop(),
                                    cmp.hListCell(createCustomerComponentTypes("Types de pêche :")).heightFixedOnTop()),
                                    cmp.verticalGap(3), cmp.horizontalList().setStyle(stl.style(10)).setGap(50).add(
                                    cmp.hListCell(createCustomerComponentLabel()).heightFixedOnTop()))
                    .fields(
                    field("typesCons", List.class),
                            field("searchDateCapture1", Date.class),
                            field("searchDateCapture2", Date.class))
                  .detail(subreport)
      //             .groupBy(colgr)
                   .pageFooter(Templates.footerComponent);
//            reportS.print();
    //       response.setContentType(FILE_TYPE_2_CONTENT_TYPE.get(fileType));
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
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        HorizontalListBuilder list = cmp.horizontalList().setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));
        if(!captures[0].equals(null)) {
            addCustomerAttributeDate(list, "De : ", sdf.format(captures[0].getSearchDateCapture1()));

            addCustomerAttributeDate(list, "A : ", sdf.format(captures[0].getSearchDateCapture2()));
        }
        return cmp.verticalList(
                cmp.text(label).setStyle(Templates.boldStyle),
                list);

    }
    private ComponentBuilder<?, ?> createCustomerComponentLabel() {
        String lineLabel="";

        for (int i = 0; i < captures[0].getResultatsEntetesCol().size(); i++) {
            lineLabel=lineLabel+i+"-"+captures[0].getResultatsEntetesCol().get(i);
          if(i!=captures[0].getResultatsEntetesCol().size())   lineLabel=lineLabel+",";

        }
                return cmp.text(lineLabel);

    }
    private ComponentBuilder<?, ?> createCustomerComponentTypes(String label) {

        HorizontalListBuilder list = cmp.horizontalList().setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));
        addCustomerAttributeTypeConces(list, captures[0].getTypesCons());
        return cmp.verticalList( cmp.text(label).setStyle(Templates.boldStyle),list);//  return cmp.verticalList( cmp.text(label).setStyle(Templates.boldStyle),list);

    }

    private void addCustomerAttributeDate(HorizontalListBuilder list, String label, String value) {

        if (value != null) {

            list.add(cmp.text(label + ":").setFixedColumns(8).setStyle(Templates.boldStyle), cmp.text(value)).newRow();

        }

    }
    private void addCustomerAttributeTypeConces(HorizontalListBuilder list, java.util.List<String> types) {
        StringBuffer result = new StringBuffer();
        if (types.size() != 0) {
         for (int i=0;i<types.size();i++) {
     //        list.111111111111add( cmp.text(types.get(i))).newRow();
             if (result.length() > 0) {
                 result.append(",");
             }
             result.append(types.get(i));
           }
            list.add( cmp.text(result.toString()));

        }

    }

    private JasperReportBuilder createSubreport(Boolean excludeDetails) {

        StyleBuilder columnStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());
        TextColumnBuilder<String> nav=col.column("navire","navire",type.stringType());
        // TextColumnBuilder<String>  colgr = col.column("navire", "navire", type.stringType());
        StyleBuilder groupStyle = stl.style().bold();
        ColumnGroupBuilder itemGroup = grp.group(nav)
                .setStyle(groupStyle)
                .setTitleStyle(groupStyle)
                .setTitleWidth(30);

        JasperReportBuilder report = report();
        report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);
        report()
                .setTemplate(Templates.reportTemplate)
                .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                .title(cmp.text("Details de captures de").setStyle(Templates.boldCenteredStyle))
                .title(Templates.createTitleComponent("FieldGroup"))
                .addField(field("navire", String.class))
                .addColumn(col.column("date","dateCapture",type.dateType())).setTextStyle(columnStyle)
                .addColumn().setTextStyle(columnStyle);

        //.title(Templates.creat
        report.sortBy(col.column("navire","navire",type.stringType()));

        report.groupBy(itemGroup);
        TextColumnBuilder<Float> current=null;
        TextColumnBuilder<Float> totalColumn=col.column("Total",  type.floatType());
        Float total=0f;
        for (int i = 0; i < captures[0].getResultatsNamesCol().size(); i++) {
            current=col.column(Integer.toString(i), "resultatCapEsp."+captures[0].getResultatsNamesCol().get(i), type.floatType()).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);
                    if(excludeDetails.equals(true)) {
                        current.setPrintWhenExpression(new PrintGroupHeaderColumnBreakExpression());
                    }


            //captures[0].getResultatsEntetesCol().get(i)
                  report.addColumn(current).setTextStyle(columnStyle);
            //   report.addField(field(captures[0].getResultatsNamesCol().get(i), float.class));
                  report.subtotalsAtGroupFooter(itemGroup, sbt.sum(current));
            report.subtotalsAtSummary(sbt.sum(current));

        };
        //        report.pageFooter(cmp.pageXofY());
        //         TextColumnBuilder<String> colgr= col.column("navire","navire" , type.stringType());
        //       TextColumnBuilder<Integer> rowNumberColumn = col.reportRowNumberColumn().setFixedColumns(2);
        //     reportS.addColumn(rowNumberColumn);
        //reportS.setDataSource(captures[0].getDataSource11());
        //report.subtotalsAtGroupFooter();

        if(excludeDetails.equals(true)) {     report.setDetailPrintWhenExpression(new PrintGroupHeaderColumnBreakExpression());}
        return report;
    }
    public class PrintGroupHeaderColumnBreakExpression extends AbstractSimpleExpression<Boolean> {

        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override

        public Boolean evaluate(ReportParameters reportParameters) {

            return false;

        }

    }

    private class ExpressionColumn extends AbstractSimpleExpression<BigDecimal>{

        private static final long serialVersionUID = 1L;

        @Override

        public BigDecimal evaluate(ReportParameters reportParameters) {

            return new BigDecimal(12) ;

        }

    }

}
