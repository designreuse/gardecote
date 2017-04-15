package com.gardecote.reports;

import com.gardecote.entities.enumTypeDoc;
import com.gardecote.web.printedDocument;
import com.gardecote.web.resultatsCapturesByConcession;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.*;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.*;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.field;

/**
 * Created by Dell on 13/04/2017.
 */

public class reportDocument {
    private static final int cellWidth = 18;

    private static final int cellHeight = 18;
    private float factoryScal=0;
    private  int fontSize = 0;
    private List<printedDocument> printedDocs = new ArrayList<printedDocument>();
    private  String  fileType ;
    private HttpServletRequest request;
    private HttpServletResponse response;

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

    public reportDocument(List<printedDocument> printedDocs, String fileType, HttpServletRequest request, HttpServletResponse response,String paramPaper) throws IOException, ServletException, Exception {
        this.printedDocs = printedDocs;
        this.fileType = fileType;
        this.request = request;
        this.response = response;
        System.out.println("second text : "+ printedDocs.size());
        if(printedDocs.size()!=0) {
            if(printedDocs.get(0).getCurrentDoc().getEnumtypedoc().equals(enumTypeDoc.Journal_Peche))
                       build(printedDocs, fileType, request, response, out,paramPaper);
            else
                ;
        }
    }
    private void build(List<printedDocument> printedDocs, String  fileType, HttpServletRequest request, HttpServletResponse response, OutputStream out,String paperType) throws IOException, ServletException, Exception {
       SubreportBuilder subreport = cmp.subreport(createSubreport())
          .setDataSource(exp.subDatasourceBeanCollection("pagesDocCaptures"));
        JasperReportBuilder report = report();
        report.setDataSource(new JRBeanCollectionDataSource(printedDocs));
        if(paperType.equals("A3")){ factoryScal=(float)1;report.setPageFormat(PageType.A3, PageOrientation.LANDSCAPE);}
        if(paperType.equals("A4")){ factoryScal=(float)595/842;report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);System.out.println("hh"+factoryScal);}

        int pageWidth = PageType.A4.getWidth() - 20;//page width - left margin - right margin
        int charactersPerLine = 24;

        FontRenderContext context = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).createGraphics().getFontRenderContext();
         fontSize = 0;
        for (int i = 20; i > 0; i--) {
            Font font = new Font("Courier New", Font.PLAIN, i);
           // double height = font.getStringBounds("m", context).getHeight() * charactersPerLine;
            double width = font.getStringBounds("Nom de concessionnaire : ", context).getWidth();

            if (width <= (factoryScal*170) ) {
                fontSize = i;
                break;
            }
        }
        System.out.println("fontSize :"+fontSize);
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);
        System.out.println( "A3"+ PageType.A3.getWidth());
        System.out.println( "A4"+ PageType.A4.getWidth());
        StyleBuilder leftStyle = stl.style(textStyle).setFontSize(fontSize).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        StyleBuilder centeredStyle =stl.style(textStyle).setFontSize(fontSize).setHorizontalImageAlignment(HorizontalImageAlignment.CENTER).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        StyleBuilder rightStyle = stl.style(textStyle).setFontSize(fontSize).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        StyleBuilder cellStyle = stl.style(textStyle).setFontSize(fontSize).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);


        // .showColumnHeaderAndFooter();
        try {
            report
                    .setTemplate(Templates.reportTemplate)
                    .fields(
                            field("numeroPage", String.class))

                    .pageHeader(cmp.horizontalList(cmp.text("RUBRIQUE N 1").setStyle(cellStyle).setFixedWidth((int)(factoryScal*10)),cmp.horizontalGap((int)(factoryScal*5)),cmp.verticalList(cmp.horizontalList(addEntetDoc()).setStyle(cellStyle).newRow() ,
                            cmp.verticalList(cmp.horizontalList(cmp.horizontalGap((int)(factoryScal*10)),addNumPage()).newRow(),cmp.verticalGap((int)(factoryScal*10))),
                            cmp.verticalList(cmp.horizontalList(cmp.horizontalGap((int)(factoryScal*20)), addTypePeche(),cmp.horizontalGap((int)(factoryScal*300)),addReferencements()).newRow(),cmp.verticalGap((int)(factoryScal*10))),
                            cmp.verticalList(cmp.horizontalList(addInfoConcession(),cmp.horizontalGap((int)(factoryScal*80)),addRefLicencePeche(),cmp.horizontalGap((int)(factoryScal*270)),addDates().setStyle(leftStyle)).newRow(),cmp.verticalGap((int)(factoryScal*10))),
                            cmp.verticalList(cmp.horizontalList(addInfoBateau(),cmp.horizontalGap((int)(factoryScal*20)),addEngins(),cmp.horizontalGap((int)(factoryScal*100)),cmp.text("Signature capitaine de navire").setStyle(leftStyle)))))
                            )


                    .detail(cmp.horizontalList(subreport), Components.pageBreak().setPrintWhenExpression(new PageBreakExpression()))
                    .pageFooter(Templates.footerComponent)
                    .detailFooter()
                    .setDataSource(new JRBeanCollectionDataSource(printedDocs));
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


    ComponentBuilder<?, ?> addEngins() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);
        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);

        Date datedep=null,dateret=null;

        VerticalListBuilder listv = cmp.verticalList();

        HorizontalListBuilder list0 = cmp.horizontalList()
                .add(cmp.text("Engins de peche").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))

                .add(cmp.text("Chaluts").setStyle(cellStyle).setFixedWidth((int)(factoryScal*70)).setFixedHeight(cellHeight))
                .add(cmp.text("Casier").setStyle(cellStyle).setFixedWidth((int)(factoryScal*70)).setFixedHeight(cellHeight))
                .add(cmp.text("Nasses").setStyle(cellStyle).setFixedWidth((int)(factoryScal*70)).setFixedHeight(cellHeight))
                .add(cmp.text("Filet Tremail").setStyle(cellStyle).setFixedWidth((int)(factoryScal*70)).setFixedHeight(cellHeight))
                .add(cmp.text("Turlutte").setStyle(cellStyle).setFixedWidth((int)(factoryScal*70)).setFixedHeight(cellHeight));

        HorizontalListBuilder list1 = cmp.horizontalList()

                .add(cmp.text("Maillage").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))
                .add(cmp.text("X").setStyle(cellStyle).setFixedWidth((int)(factoryScal*70)).setFixedHeight(cellHeight))
                .add(cmp.text(" ").setStyle(cellStyle).setFixedWidth((int)(factoryScal*70)).setFixedHeight(cellHeight))
                .add(cmp.text("X").setStyle(cellStyle).setFixedWidth((int)(factoryScal*70)).setFixedHeight(cellHeight))
                .add(cmp.text("X").setStyle(cellStyle).setFixedWidth((int)(factoryScal*70)).setFixedHeight(cellHeight))
                .add(cmp.text("X").setStyle(cellStyle).setFixedWidth((int)(factoryScal*70)).setFixedHeight(cellHeight));

        listv.add(list0,list1);
        return listv;
    }

    ComponentBuilder<?, ?> addInfoBateau() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);
        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);

        Date datedep=null,dateret=null;

        VerticalListBuilder listv = cmp.verticalList();

        HorizontalListBuilder list0 = cmp.horizontalList()
                .add(cmp.text("Nom de Navire").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))

                .add(cmp.text("......").setStyle(cellStyle).setFixedWidth((int)(factoryScal*200)).setFixedHeight(cellHeight));


        HorizontalListBuilder list1 = cmp.horizontalList()

                .add(cmp.text("IMO").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))

                .add(cmp.text(".....").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))

                .add(cmp.text("GT").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(".....").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight));

        HorizontalListBuilder list2 = cmp.horizontalList()
                .add(cmp.text("Nom de capitaine").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))
                .add(cmp.text(".....").setStyle(cellStyle).setFixedWidth((int)(factoryScal*200)).setFixedHeight(cellHeight));
        listv.add(list0,list1,list2);
        return listv;
    }

    ComponentBuilder<?, ?> addDates() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);
        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);

        Date datedep=new Date(),dateret=new Date();
        String date1 = new SimpleDateFormat("MM/dd/yyyy").format(datedep);
        String date2 = new SimpleDateFormat("MM/dd/yyyy").format(datedep);
        String cellTextJ1 = StringUtils.rightPad(StringUtils.substringBefore(date1, "/"), 2);
        String cellTextM1 = StringUtils.rightPad(StringUtils.substringBetween(date1, "/"), 2);
        String cellTextY1 = StringUtils.rightPad(StringUtils.substringAfterLast(date1, "/"), 4);
        String cellTextJ2 = StringUtils.rightPad(StringUtils.substringBefore(date2, "/"), 2);
        String cellTextM2 = StringUtils.rightPad(StringUtils.substringBetween(date2, "/"), 2);
        String cellTextY2 = StringUtils.rightPad(StringUtils.substringAfterLast(date2, "/"), 4);
        VerticalListBuilder listv = cmp.verticalList();

        HorizontalListBuilder list0 = cmp.horizontalList()
                .add(cmp.text("Lieu").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text("Jours").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text("Mois").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text("Annee").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight));

        HorizontalListBuilder list1 = cmp.horizontalList()

                .add(cmp.text("NDB").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(cellTextJ1).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(cellTextM1).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(cellTextY1).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight));

        HorizontalListBuilder list2 = cmp.horizontalList()
                .add(cmp.text("NDB").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(cellTextJ2).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(cellTextM2).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(cellTextY2).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight));
        listv.add(list0,list1,list2);
        return listv;

    }
    ComponentBuilder<?, ?> addRefLicencePeche() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);


        HorizontalListBuilder listTypesPecheH1 = cmp.horizontalList();

        listTypesPecheH1.add(cmp.text("Reference de licence de pêche").setStyle(textStyle).setFixedDimension((int)(factoryScal*180), cellHeight),cmp.text("......").setStyle(cellStyle).setFixedDimension((int)(factoryScal*50), cellHeight));
        return listTypesPecheH1;
    }
    ComponentBuilder<?, ?> addInfoConcession() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);

        VerticalListBuilder listTypesPecheV = cmp.verticalList();
        HorizontalListBuilder listTypesPecheH1 = cmp.horizontalList();
        HorizontalListBuilder listTypesPecheH2 = cmp.horizontalList();
        listTypesPecheH1.add(cmp.text("Support").setStyle(cellStyle).setFixedDimension((int)(factoryScal*60), 2*cellHeight),cmp.text("Quota").setStyle(cellStyle).setFixedDimension((int)(factoryScal*60), 2*cellHeight),cmp.text("Duree de concession").setStyle(cellStyle).setFixedDimension((int)(factoryScal*80), 2*cellHeight),cmp.text("Date Expiration").setStyle(cellStyle).setFixedDimension((int)(factoryScal*80), 2*cellHeight));
        listTypesPecheH2.add(cmp.text(".....").setStyle(cellStyle).setFixedDimension((int)(factoryScal*60), cellHeight),cmp.text("......").setStyle(cellStyle).setFixedDimension((int)(factoryScal*60), cellHeight),cmp.text(".......").setStyle(cellStyle).setFixedDimension((int)(factoryScal*80), cellHeight),cmp.text("......").setStyle(cellStyle).setFixedDimension((int)(factoryScal*80), cellHeight));
        listTypesPecheV.add(listTypesPecheH1);
        listTypesPecheV.add(listTypesPecheH2);
        return listTypesPecheV;
    }
        ComponentBuilder<?, ?> addTypePeche() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);

        VerticalListBuilder listTypesPecheV = cmp.verticalList();
        HorizontalListBuilder listTypesPecheH1 = cmp.horizontalList();
        HorizontalListBuilder listTypesPecheH2 = cmp.horizontalList();
        listTypesPecheH1.add(cmp.text("Hautriere").setStyle(cellStyle).setFixedDimension((int)(factoryScal*50), cellHeight),cmp.text("Cotiere").setStyle(cellStyle).setFixedDimension((int)(factoryScal*50), cellHeight));
        listTypesPecheH2.add(cmp.text("X").setStyle(cellStyle).setFixedDimension((int)(factoryScal*50), cellHeight),cmp.text(" ").setStyle(cellStyle).setFixedDimension((int)(factoryScal*50), cellHeight));
        listTypesPecheV.add(listTypesPecheH1);
        listTypesPecheV.add(listTypesPecheH2);
        return listTypesPecheV;

    }
    ComponentBuilder<?, ?>  addReferencements() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);

        VerticalListBuilder listTypesPecheV = cmp.verticalList();
        HorizontalListBuilder listTypesPecheH1 = cmp.horizontalList();
        HorizontalListBuilder listTypesPecheH2 = cmp.horizontalList();
        listTypesPecheH1.add(cmp.text("Nom de concessionnaire : ").setStyle(textStyle).setFixedDimension((int)(factoryScal*170), cellHeight),cmp.text(".....").setStyle(cellStyle).setFixedDimension((int)(factoryScal*30), cellHeight));
        listTypesPecheH2.add(cmp.text("Ref de contrat de concession : ").setStyle(cellStyle).setFixedDimension((int)(factoryScal*170), cellHeight),cmp.text(".....").setStyle(cellStyle).setFixedDimension((int)(factoryScal*30), cellHeight));
        listTypesPecheV.add(listTypesPecheH1);
        listTypesPecheV.add(listTypesPecheH2);
        return listTypesPecheV;
    }

    ComponentBuilder<?, ?> addNumPage() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder leftStyle = stl.style(textStyle).setFontSize(fontSize).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        StyleBuilder centeredStyle =stl.style(textStyle).setFontSize(fontSize).setHorizontalImageAlignment(HorizontalImageAlignment.CENTER).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        StyleBuilder rightStyle = stl.style(textStyle).setFontSize(fontSize).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        HorizontalListBuilder list = cmp.horizontalList();
        list.add(
            cmp.horizontalList(cmp.text("Page N° ").setFixedWidth((int)(factoryScal*60)),
                    cmp.text(new RetNumPage() ).setFixedWidth((int)(factoryScal*150)).setStyle(cellStyle))  );


        return list;
}

    private class RetNumPage extends AbstractSimpleExpression<String> {

        private static final long serialVersionUID = 1L;

        @Override

        public String evaluate(ReportParameters reportParameters) {

            return  reportParameters.getValue("numeroPage") ;

        }

    }


    ComponentBuilder<?, ?> addEntetDoc() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder leftStyle = stl.style(textStyle).setFontSize(fontSize).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        StyleBuilder centeredStyle =stl.style(textStyle).setFontSize(fontSize).setHorizontalImageAlignment(HorizontalImageAlignment.CENTER).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        StyleBuilder rightStyle = stl.style(textStyle).setFontSize(fontSize).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        HorizontalListBuilder list = cmp.horizontalList()
                .setGap(0);


        list.add(
               cmp.text("Republique Islamique de la Mauritanie").setStyle(leftStyle));
        list.add(cmp.horizontalGap((int)(factoryScal*100)));

        list.add(
               cmp.text("Journal de Peche à bord").setStyle(centeredStyle));
        list.add(cmp.horizontalGap((int)(factoryScal*100)));

        list.add(cmp.text("Ministere des Pêches et de l'Economie Nationale").setStyle(rightStyle));

   return list.newRow();
}
    private JasperReportBuilder createSubreport() {

        StyleBuilder columnStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());
        StyleBuilder boldStyle = stl.style(Templates.boldStyle)
                .setBorder(stl.pen2Point());
        JasperReportBuilder report = report();

      //  report.setPageFormat(PageType.A3, net.sf.dynamicreports.report.constant.PageOrientation.LANDSCAPE);
        report()
                .setTemplate(Templates.reportTemplate)
                .title(cmp.text("Details de captures de").setStyle(Templates.boldCenteredStyle))
                ;

        TextColumnBuilder<Integer> rowNumberColumn = col.reportRowNumberColumn().setFixedColumns(2);
        report.addColumn(rowNumberColumn);
        for (int i = 0; i < printedDocs.get(0).getResultatsNamesCol().size(); i++) {
            report.addColumn(col.column(printedDocs.get(0).getResultatsEntetesCol().get(i), printedDocs.get(0).getResultatsNamesCol().get(i), type.floatType())).setTextStyle(columnStyle);

        }
        report.addColumn(col.column("totalCapturs", "totalCapturs", type.floatType())).setTextStyle(Templates.boldCenteredStyle);
        report.addColumn(col.column("totalCong", "totalCong", type.floatType())).setTextStyle(boldStyle);
        report.addColumn(col.column("nbrCaisse", "nbrCaisse", type.integerType())).setTextStyle(boldStyle);



        report.pageFooter(cmp.pageXofY(),cmp.pageBreak());

        return report;
    }
    private class PageBreakExpression extends AbstractSimpleExpression<Boolean> {
        public Boolean evaluate(ReportParameters reportParameters) {
            //Only having a page break after the first subreport
            if (reportParameters.getReportRowNumber() == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

}
