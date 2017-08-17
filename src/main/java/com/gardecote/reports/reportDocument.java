package com.gardecote.reports;

import com.gardecote.entities.enumJP;
import com.gardecote.entities.enumTypeDoc;
import com.gardecote.entities.qDoc;
import com.gardecote.web.printedDocument;
import com.gardecote.web.resultatsCapturesByConcession;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.*;
import net.sf.dynamicreports.report.builder.grid.ColumnGridComponentBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.*;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import scala.Array;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
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


    public reportDocument(List<printedDocument> printedDocs, String fileType, HttpServletRequest request, HttpServletResponse response, String paramPaper) throws IOException, ServletException, Exception {
        this.printedDocs = printedDocs;
        this.fileType = fileType;
        this.request = request;
        this.response = response;
        System.out.println("second text : "+ printedDocs.size());
        if(printedDocs.size()!=0) {
            if(printedDocs.get(0).getCurrentDoc().getEnumtypedoc().equals(enumTypeDoc.Journal_Peche))
                build(printedDocs, fileType, request, response, out,paramPaper);
            if(printedDocs.get(0).getCurrentDoc().getEnumtypedoc().equals(enumTypeDoc.Fiche_Debarquement))
                buildDebarquement(printedDocs, fileType, request, response, out,paramPaper);
        }
    }

    private void build(List<printedDocument> printedDocs, String  fileType, HttpServletRequest request, HttpServletResponse response, OutputStream out,String paperType) throws IOException, ServletException, Exception {
        JasperReportBuilder report = report();
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

        SubreportBuilder subreport = cmp.subreport(createSubreport(fontSize))
                .setDataSource(exp.subDatasourceBeanCollection("pagesDocCaptures"));

        report.setDataSource(new JRBeanCollectionDataSource(printedDocs));
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
                            field("numeroPage", String.class),field("currentDoc.typePeche",String.class),
                            field("maillages",List.class),field("refConcession",String.class),field("nomConcessionnaire",String.class)
                            ,field("support",String.class),field("quota",String.class)
                            ,field("dureeCobcession",String.class),field("dateExpiration",String.class)
                            ,field("licencePeche",String.class),field("nomNavire",String.class)
                            ,field("imo",String.class),field("gt",String.class),field("nomCapitaine",String.class)
                            ,field("choixEngins",List.class),field("currentDoc.depart",Date.class),field("currentDoc.retour",Date.class))


                    .pageHeader(cmp.horizontalList(cmp.text("RUBRIQUE N 1").setStyle(cellStyle).setFixedWidth((int)(factoryScal*10)),cmp.horizontalGap((int)(factoryScal*5)),cmp.verticalList(cmp.horizontalList(addEntetDoc()).setStyle(cellStyle).newRow() ,
                            cmp.verticalList(cmp.horizontalList(cmp.horizontalGap((int)(factoryScal*10)),addNumPage()).newRow(),cmp.verticalGap((int)(factoryScal*10))),
                            cmp.verticalList(cmp.horizontalList(cmp.horizontalGap((int)(factoryScal*20)), addTypePeche(),cmp.horizontalGap((int)(factoryScal*300)),addReferencements()).newRow(),cmp.verticalGap((int)(factoryScal*10))),
                            cmp.verticalList(cmp.horizontalList(addInfoConcession(),cmp.horizontalGap((int)(factoryScal*80)),addRefLicencePeche(),cmp.horizontalGap((int)(factoryScal*190)),addDates().setStyle(leftStyle)).newRow(),cmp.verticalGap((int)(factoryScal*10))),
                            cmp.verticalList(cmp.horizontalList(addInfoBateau(),cmp.horizontalGap((int)(factoryScal*20)),addEngins(),cmp.horizontalGap((int)(factoryScal*100)),cmp.text("Signature capitaine de navire").setStyle(leftStyle)))))
                    )


                    .detail(cmp.verticalGap((int)(factoryScal*20)),cmp.horizontalList(subreport), Components.pageBreak().setPrintWhenExpression(new PageBreakExpression()))
                    .pageFooter(Templates.footerComponent)
                    .detailFooter()
                    //  .setDataSource(new JRBeanCollectionDataSource(printedDocs));
                    .setDataSource(new MyBeanCollectionDataSource(printedDocs));
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
    private void buildDebarquement(List<printedDocument> printedDocs, String  fileType, HttpServletRequest request, HttpServletResponse response, OutputStream out,String paperType) throws IOException, ServletException, Exception {
        JasperReportBuilder report = report();
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

        SubreportBuilder subreportDeb = cmp.subreport(createSubreportDeb(fontSize))
          .setDataSource(exp.subDatasourceBeanCollection("pagesDocCaptures"));

        report.setDataSource(new JRBeanCollectionDataSource(printedDocs));
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
                            field("numeroPage", String.class),field("currentDoc.typePeche",String.class),
                            field("maillages",List.class),field("refConcession",String.class),field("nomConcessionnaire",String.class)
                            ,field("support",String.class),field("quota",String.class)
                            ,field("dureeCobcession",String.class),field("dateExpiration",String.class)
                            ,field("licencePeche",String.class),field("nomNavire",String.class)
                           ,field("nomCapitaine",String.class)
                                    ,field("choixEngins",List.class),field("currentDoc.depart",Date.class),field("currentDoc.retour",Date.class),field("zonePeche",Date.class),field("choisTypesConcessions",List.class),field("segPeche",String.class))


                    .pageHeader(cmp.horizontalList(cmp.text("RUBRIQUE N 1").setStyle(cellStyle).setFixedWidth((int)(factoryScal*10)),cmp.horizontalGap((int)(factoryScal*5)),cmp.verticalList(cmp.horizontalList(addEntetDocDeb()).setStyle(cellStyle).newRow() ,
                            cmp.verticalList(cmp.horizontalList(cmp.horizontalGap((int)(factoryScal*10)),addNumPage()).newRow(),cmp.verticalGap((int)(factoryScal*10))),
                            cmp.verticalList(cmp.horizontalList(cmp.horizontalGap((int)(factoryScal*20)), addSegPecheDeb(),addConcessionDeb(),cmp.horizontalGap((int)(factoryScal*150)),addReferencements()).newRow(),cmp.verticalGap((int)(factoryScal*10))),
                            cmp.verticalList(cmp.horizontalList(addInfoConcession(),cmp.horizontalGap((int)(factoryScal*80)),addRefLicencePeche(),cmp.horizontalGap((int)(factoryScal*190)),addDates().setStyle(leftStyle)).newRow(),cmp.verticalGap((int)(factoryScal*10))),
                            cmp.verticalList(cmp.horizontalList(addInfoBateauDeb(),cmp.horizontalGap((int)(factoryScal*20)),addEnginsDeb(),cmp.horizontalGap((int)(factoryScal*100)),cmp.text("Signature capitaine de navire").setStyle(leftStyle)))))
                            )
                    .detail(cmp.verticalGap((int)(factoryScal*20)),cmp.horizontalList(subreportDeb), Components.pageBreak().setPrintWhenExpression(new PageBreakExpression()))
                  .pageFooter(Templates.footerComponent)
                    .detailFooter()
                  //  .setDataSource(new JRBeanCollectionDataSource(printedDocs));
                    .setDataSource(new MyBeanCollectionDataSource(printedDocs));
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

    ComponentBuilder<?, ?> addConcessionDeb() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
//equals("Hauteriere")?"X":""

        VerticalListBuilder listTypesPecheV = cmp.verticalList();
        HorizontalListBuilder listTypesPecheH1 = cmp.horizontalList();
        HorizontalListBuilder listTypesPecheH2 = cmp.horizontalList();
        listTypesPecheH1.add(cmp.text("Céphalopodes").setStyle(cellStyle).setFixedDimension((int)(factoryScal*70), cellHeight),cmp.text("Crustacés").setStyle(cellStyle).setFixedDimension((int)(factoryScal*70), cellHeight),cmp.text("P.Demersaux").setStyle(cellStyle).setFixedDimension((int)(factoryScal*70), cellHeight),
                cmp.text("P.Pelagique").setStyle(cellStyle).setFixedDimension((int)(factoryScal*70), cellHeight) ,cmp.text("P.Mollusques").setStyle(cellStyle).setFixedDimension((int)(factoryScal*70), cellHeight));


        listTypesPecheH2.add(cmp.text(new RetConcession(0)).setStyle(cellStyle).setFixedDimension((int)(factoryScal*70), cellHeight),cmp.text(new RetConcession(1)).setStyle(cellStyle).setFixedDimension((int)(factoryScal*70), cellHeight),
                cmp.text(new RetConcession(2)).setStyle(cellStyle).setFixedDimension((int)(factoryScal*70), cellHeight),cmp.text(new RetConcession(3)).setStyle(cellStyle).setFixedDimension((int)(factoryScal*70), cellHeight),cmp.text(new RetConcession(4)).setStyle(cellStyle).setFixedDimension((int)(factoryScal*70), cellHeight));
        listTypesPecheV.add(listTypesPecheH1);
        listTypesPecheV.add(listTypesPecheH2);
        return listTypesPecheV;
    }
    ComponentBuilder<?, ?> addSegPecheDeb() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        //equals("Hauteriere")?"X":""


        HorizontalListBuilder listTypesPecheH1 = cmp.horizontalList();

        listTypesPecheH1.add(cmp.text("Segment de Pêche : ").setFixedDimension((int)(factoryScal*110), cellHeight),cmp.text(new SegPecheExpression()).setFixedDimension((int)(factoryScal*40), cellHeight));


               return listTypesPecheH1;
    }

    ComponentBuilder<?, ?> addEnginsDeb() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);
        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        Date datedep=null,dateret=null;
        VerticalListBuilder listv = cmp.verticalList();
        VerticalListBuilder listvsub = cmp.verticalList();
        HorizontalListBuilder listhp1 = cmp.horizontalList();
        HorizontalListBuilder listH1 = cmp.horizontalList()


                .add(cmp.text("Pots").setStyle(cellStyle).setFixedWidth((int)(factoryScal*30)).setFixedHeight(cellHeight))
                .add(cmp.text("Casiers").setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text("Filet Tremail").setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text("Filet Maillant").setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text("Turlutte").setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text("Ligne").setStyle(cellStyle).setFixedWidth((int)(factoryScal*30)).setFixedHeight(cellHeight))
                .add(cmp.text("Palangre").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))
                .add(cmp.text("Filet Encerclant").setStyle(cellStyle).setFixedWidth((int)(factoryScal*80)).setFixedHeight(cellHeight));

        HorizontalListBuilder listH2 = cmp.horizontalList()
                .add(cmp.text(new ChoisExpression(0)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*30)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(1)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(2)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(3)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(4)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(5)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*30)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(6)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(7)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*80)).setFixedHeight(cellHeight));
         listvsub.add(listH1);
        listvsub.add(listH2);

        listhp1.add(cmp.text("Engins de peche").setStyle(cellStyle).setFixedWidth((int)(factoryScal*80)).setFixedHeight(2*cellHeight),listvsub);
        HorizontalListBuilder list1 = cmp.horizontalList()

                .add(cmp.text("Maillage").setStyle(cellStyle).setFixedWidth((int)(factoryScal*80)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(0))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*30)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(1))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(2))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(3))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(4))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                 .add(cmp.text((new EnginExpression(5))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*30)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(6))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(7))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*80)).setFixedHeight(cellHeight));

        listv.add(listhp1,list1);
        return listv;
    }
    ComponentBuilder<?, ?> addInfoBateauDeb() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);
        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);

        Date datedep=null,dateret=null;

        VerticalListBuilder listv = cmp.verticalList();


        HorizontalListBuilder list0 = cmp.horizontalList()
                .add(cmp.text("Nom de Navire").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))

                .add(cmp.text(new NomNavireExpression()).setStyle(cellStyle).setFixedWidth((int)(factoryScal*200)).setFixedHeight(cellHeight));


        HorizontalListBuilder list1 = cmp.horizontalList()

                .add(cmp.text("").setStyle(cellStyle).setFixedWidth((int)(factoryScal*300)).setFixedHeight(cellHeight));

        HorizontalListBuilder list2 = cmp.horizontalList()
                .add(cmp.text("Nom de capitaine").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))
                .add(cmp.text(new NomCapitaineExpression()).setStyle(cellStyle).setFixedWidth((int)(factoryScal*200)).setFixedHeight(cellHeight));
        listv.add(list0,list1,list2);
        return listv;
    }
    ComponentBuilder<?, ?> addEntetDocDeb() {
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
                cmp.text("Fiche Debarquement de Pêche").setStyle(centeredStyle));
        list.add(cmp.horizontalGap((int)(factoryScal*100)));

        list.add(cmp.text("Ministere des Pêches et de l'Economie Nationale").setStyle(rightStyle));

        return list.newRow();
    }

    ComponentBuilder<?, ?> addEngins() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);
        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);



        Date datedep=null,dateret=null;

        VerticalListBuilder listv = cmp.verticalList();
        VerticalListBuilder listvsub = cmp.verticalList();
        HorizontalListBuilder listhp1 = cmp.horizontalList();
        HorizontalListBuilder listH1 = cmp.horizontalList()


                .add(cmp.text("Chaluts").setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text("Casier").setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text("Nasses").setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text("Filet Tremail").setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text("Turlutte").setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight));

        HorizontalListBuilder listH2 = cmp.horizontalList()
                .add(cmp.text(new ChoisExpression(0)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(1)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(2)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(3)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text(new ChoisExpression(4)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight));
        listvsub.add(listH1);
        listvsub.add(listH2);

        listhp1.add(cmp.text("Engins de peche").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(2*cellHeight),listvsub);
        HorizontalListBuilder list1 = cmp.horizontalList()
                .add(cmp.text("Maillage").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(0))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(1))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(2))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(3))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*60)).setFixedHeight(cellHeight))
                .add(cmp.text((new EnginExpression(4))).setStyle(cellStyle).setFixedWidth((int)(factoryScal*40)).setFixedHeight(cellHeight));

        listv.add(listhp1,list1);
        return listv;
    }

    ComponentBuilder<?, ?> addInfoBateau() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);
        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);

        Date datedep=null,dateret=null;

        VerticalListBuilder listv = cmp.verticalList();


        HorizontalListBuilder list0 = cmp.horizontalList()
                .add(cmp.text("Nom de Navire").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))

                .add(cmp.text(new NomNavireExpression()).setStyle(cellStyle).setFixedWidth((int)(factoryScal*200)).setFixedHeight(cellHeight));


        HorizontalListBuilder list1 = cmp.horizontalList()

                .add(cmp.text("IMO").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))

                .add(cmp.text(new IMOExpression()).setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))

                .add(cmp.text("GT").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(new GTExpression()).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight));

        HorizontalListBuilder list2 = cmp.horizontalList()
                .add(cmp.text("Nom de capitaine").setStyle(cellStyle).setFixedWidth((int)(factoryScal*100)).setFixedHeight(cellHeight))
                .add(cmp.text(new NomCapitaineExpression()).setStyle(cellStyle).setFixedWidth((int)(factoryScal*200)).setFixedHeight(cellHeight));
        listv.add(list0,list1,list2);
        return listv;
    }

    ComponentBuilder<?, ?> addDates() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);
        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
               VerticalListBuilder listv = cmp.verticalList();

        HorizontalListBuilder list0 = cmp.horizontalList()
                .add(cmp.text("Lieu").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text("Jours").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text("Mois").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text("Annee").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight));

        HorizontalListBuilder list1 = cmp.horizontalList()

                .add(cmp.text("NDB").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(new DepartExpression(0)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(new DepartExpression(1)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(new DepartExpression(2)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight));

        HorizontalListBuilder list2 = cmp.horizontalList()
                .add(cmp.text("NDB").setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(new RetourExpression(0)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(new RetourExpression(1)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight))

                .add(cmp.text(new RetourExpression(2)).setStyle(cellStyle).setFixedWidth((int)(factoryScal*50)).setFixedHeight(cellHeight));
        listv.add(list0,list1,list2);
        return listv;

    }
    ComponentBuilder<?, ?> addRefLicencePeche() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);


        HorizontalListBuilder listTypesPecheH1 = cmp.horizontalList();

        listTypesPecheH1.add(cmp.text("Reference de licence de pêche").setStyle(textStyle).setFixedDimension((int)(factoryScal*150), cellHeight),cmp.text(new LicencePecheExpression()).setStyle(cellStyle).setFixedDimension((int)(factoryScal*180), cellHeight));
        return listTypesPecheH1;
    }
    ComponentBuilder<?, ?> addInfoConcession() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);


        VerticalListBuilder listTypesPecheV = cmp.verticalList();
        HorizontalListBuilder listTypesPecheH1 = cmp.horizontalList();
        HorizontalListBuilder listTypesPecheH2 = cmp.horizontalList();
        listTypesPecheH1.add(cmp.text("Support").setStyle(cellStyle).setFixedDimension((int)(factoryScal*60), 2*cellHeight),cmp.text("Quota").setStyle(cellStyle).setFixedDimension((int)(factoryScal*60), 2*cellHeight),cmp.text("Duree de concession").setStyle(cellStyle).setFixedDimension((int)(factoryScal*80), 2*cellHeight),cmp.text("Date Expiration").setStyle(cellStyle).setFixedDimension((int)(factoryScal*80), 2*cellHeight));
        listTypesPecheH2.add(cmp.text(new SupportExpression()).setStyle(cellStyle).setFixedDimension((int)(factoryScal*60), cellHeight),cmp.text(new QuotaExpression()).setStyle(cellStyle).setFixedDimension((int)(factoryScal*60), cellHeight),cmp.text(new DureeExpression()).setStyle(cellStyle).setFixedDimension((int)(factoryScal*80), cellHeight),cmp.text(new DateExpirationExpression()).setStyle(cellStyle).setFixedDimension((int)(factoryScal*80), cellHeight));
        listTypesPecheV.add(listTypesPecheH1);
        listTypesPecheV.add(listTypesPecheH2);
        return listTypesPecheV;
    }
        ComponentBuilder<?, ?> addTypePeche() {
        StyleBuilder textStyle = stl.style().setFontSize(fontSize);

        StyleBuilder cellStyle = stl.style(textStyle).setBorder(stl.pen1Point()).setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT).setVerticalTextAlignment(VerticalTextAlignment.TOP);
        //equals("Hauteriere")?"X":""
        VerticalListBuilder listTypesPecheV = cmp.verticalList();
        HorizontalListBuilder listTypesPecheH1 = cmp.horizontalList();
        HorizontalListBuilder listTypesPecheH2 = cmp.horizontalList();
        listTypesPecheH1.add(cmp.text("Hautriere").setStyle(cellStyle).setFixedDimension((int)(factoryScal*50), cellHeight),cmp.text("Cotiere").setStyle(cellStyle).setFixedDimension((int)(factoryScal*50), cellHeight));
        listTypesPecheH2.add(cmp.text(new RetTypePH()).setStyle(cellStyle).setFixedDimension((int)(factoryScal*50), cellHeight),cmp.text(new RetTypePC()).setStyle(cellStyle).setFixedDimension((int)(factoryScal*50), cellHeight));
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
        listTypesPecheH1.add(cmp.text("Concessionnaire ").setStyle(textStyle).setFixedDimension((int)(factoryScal*170), cellHeight),cmp.text(new RetNomConcess()).setStyle(cellStyle).setFixedDimension((int)(factoryScal*100), cellHeight));
        listTypesPecheH2.add(cmp.text("Ref de contrat de concession : ").setStyle(cellStyle).setFixedDimension((int)(factoryScal*170), cellHeight),cmp.text(new RefConcessionExpression()).setStyle(cellStyle).setFixedDimension((int)(factoryScal*100), cellHeight));
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


    private JasperReportBuilder createSubreportDeb(int fontsize) {

        StyleBuilder columnStyle = stl.style(Templates.columnStyle).setFontSize(fontsize)
                .setBorder(stl.pen1Point());
        StyleBuilder boldStyle = stl.style(Templates.boldStyle).setFontSize(fontsize)
                .setBorder(stl.pen2Point());
        JasperReportBuilder report = report();

        //  report.setPageFormat(PageType.A3, net.sf.dynamicreports.report.constant.PageOrientation.LANDSCAPE);
        report()
                .setTemplate(Templates.reportTemplate)
                .title(cmp.text("Details de captures de").setStyle(Templates.boldCenteredStyle));

        TextColumnBuilder<Date> dateColumn =col.column("Date", "date", type.dateType()).setTitleStyle(boldStyle).setStyle(boldStyle);
         TextColumnBuilder<Integer> rowNumberColumn = col.reportRowNumberColumn().setFixedColumns(2).setTitleStyle(boldStyle).setStyle(boldStyle);
        TextColumnBuilder<Float> totCap = col.column("totalCapturs", "totalCapturs", type.floatType()).setTitleStyle(boldStyle).setStyle(boldStyle);
       // TextColumnBuilder<Float> totalCong = col.column("totalCong", "totalCong", type.floatType()).setTitleStyle(boldStyle).setStyle(boldStyle);
      //  TextColumnBuilder<Integer> nbrCaisse = col.column("nbrCaisse", "nbrCaisse", type.integerType()).setTitleStyle(boldStyle).setStyle(boldStyle);
        TextColumnBuilder<Float> dyn=null;
        report.addColumn(dateColumn).setTitleStyle(boldStyle);
        ColumnTitleGroupBuilder rub0Groupe = grid.titleGroup("Débarquement",dateColumn.setTitleStyle(boldStyle));
        ColumnTitleGroupBuilder rubQGroupe = grid.titleGroup("Espéces  Poids(en Kg)").setTitleFixedHeight(cellHeight);

        //   HorizontalColumnGridListBuilder cpbl=grid.hColumnGridListCell(listGr);
        for (int i = 0; i < printedDocs.get(0).getResultatsNamesCol().size(); i++) {
            dyn = col.column(printedDocs.get(0).getResultatsEntetesCol().get(i), printedDocs.get(0).getResultatsNamesCol().get(i), type.floatType()).setStyle(boldStyle);
            report.addColumn(dyn).setTextStyle(boldStyle);
            rubQGroupe.add(dyn);
        }
   //   ColumnTitleGroupBuilder rub0Groupe = grid.titleGroup("Rubrique N 2",dateColumn.setTitleStyle(boldStyle),secteurColumn.setTitleStyle(boldStyle)).setTitleStyle(Templates.boldCenteredStyle);
   //   ColumnTitleGroupBuilder rub4 = grid.titleGroup("Especes",rubQGroupe.setTitleStyle(boldStyle)).setTitleStyle(Templates.boldCenteredStyle );


        report.columnGrid(dateColumn.setTitleStyle(boldStyle).setTitle("Débarquement"),rubQGroupe.setTitleStyle(boldStyle),totCap.setTitle("Total")).setTitleStyle(Templates.boldCenteredStyle).setTitleStyle(boldStyle);
        //  report.addColumn(rowNumberColumn);
    //    report.columnGrid(rub0Groupe.setTitleStyle(boldStyle),rub1Groupe.setTitleStyle(boldStyle),rub4.setTitleStyle(boldStyle)).setTitleStyle(boldStyle);
        //  report.addColumn(rowNumberColumn);

        report.addColumn(totCap.setStyle(boldStyle));
        //
        report.subtotalsAtSummary(sbt.sum(totCap));
        report.pageFooter(cmp.pageBreak());

        return report;
    }

    private JasperReportBuilder createSubreport(int fontsize) {

        StyleBuilder columnStyle = stl.style(Templates.columnStyle).setFontSize(fontsize)
                .setBorder(stl.pen1Point());
        StyleBuilder boldStyle = stl.style(Templates.boldStyle).setFontSize(fontsize)
                .setBorder(stl.pen2Point());
        JasperReportBuilder report = report();

      //  report.setPageFormat(PageType.A3, net.sf.dynamicreports.report.constant.PageOrientation.LANDSCAPE);
        report()
                .setTemplate(Templates.reportTemplate)
                .title(cmp.text("Details de captures de").setStyle(Templates.boldCenteredStyle));

        TextColumnBuilder<Date> dateColumn =col.column("Date", "date", type.dateType()).setTitleStyle(boldStyle).setStyle(boldStyle);
        TextColumnBuilder<String> secteurColumn =col.column("Secteur", "secteur", type.stringType()).setTitleStyle(boldStyle).setStyle(boldStyle);
        TextColumnBuilder<Integer> rowNumberColumn = col.reportRowNumberColumn().setFixedColumns(2).setTitleStyle(boldStyle).setStyle(boldStyle);
        TextColumnBuilder<Float> totCap = col.column("totalCapturs", "totalCapturs", type.floatType()).setTitleStyle(boldStyle).setStyle(boldStyle);
        TextColumnBuilder<Float> totalCong = col.column("totalCong", "totalCong", type.floatType()).setTitleStyle(boldStyle).setStyle(boldStyle);
        TextColumnBuilder<Integer> nbrCaisse = col.column("nbrCaisse", "nbrCaisse", type.integerType()).setTitleStyle(boldStyle).setStyle(boldStyle);
        TextColumnBuilder<Float> dyn=null;
        report.addColumn(dateColumn,secteurColumn).setTitleStyle(boldStyle);

        ColumnTitleGroupBuilder rubQGroupe = grid.titleGroup("Estimation").setTitleStyle(Templates.boldCenteredStyle);

     //   HorizontalColumnGridListBuilder cpbl=grid.hColumnGridListCell(listGr);
        for (int i = 0; i < printedDocs.get(0).getResultatsNamesCol().size(); i++) {
            dyn = col.column(printedDocs.get(0).getResultatsEntetesCol().get(i), printedDocs.get(0).getResultatsNamesCol().get(i), type.floatType()).setStyle(boldStyle);
            report.addColumn(dyn).setTextStyle(boldStyle);
            rubQGroupe.add(dyn).setTitleStyle(boldStyle);
        }
        ColumnTitleGroupBuilder rub1Groupe = grid.titleGroup("Rubrique N 3",rubQGroupe.setTitleStyle(boldStyle)).setTitleStyle(Templates.boldCenteredStyle);

        ColumnTitleGroupBuilder rub0Groupe = grid.titleGroup("Rubrique N 2",dateColumn.setTitleStyle(boldStyle),secteurColumn.setTitleStyle(boldStyle)).setTitleStyle(Templates.boldCenteredStyle);

        ColumnTitleGroupBuilder rub4 = grid.titleGroup("Rubrique N 4",grid.verticalColumnGridList(grid.titleGroup("Kg"),grid.titleGroup("Poids Total",totCap,grid.verticalColumnGridList(totalCong,nbrCaisse)))).setTitleStyle(Templates.boldCenteredStyle);
        report.columnGrid(rub0Groupe.setTitleStyle(boldStyle),rub1Groupe.setTitleStyle(boldStyle),rub4.setTitleStyle(boldStyle)).setTitleStyle(boldStyle);
      //  report.addColumn(rowNumberColumn);

        report.addColumn(totCap.setStyle(boldStyle),totalCong.setStyle(boldStyle),nbrCaisse.setStyle(boldStyle));
        //
        report.pageFooter(cmp.pageBreak());

        return report;
    }

    private class PageBreakExpression extends AbstractSimpleExpression<Boolean> {
        public Boolean evaluate(ReportParameters reportParameters) {
            //Only having a page break after the first subreport
            //if (reportParameters.getReportRowNumber() == 1) {
              //  return true;
            //} else {
          //      return false;
        //    }
            return true;
        }
    }
    private class RetTypePH extends AbstractSimpleExpression<String> {

        private static final long serialVersionUID = 2L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            //currentDoc.typePecheJJ
            System.out.println("sss"+reportParameters.getFieldValue("currentDoc.typePeche"));
            return  reportParameters.getValue("currentDoc.typePeche").toString().equals("Hautirere")?"X":"";
        }

    }
    private class RetTypePC extends AbstractSimpleExpression<String> {

        private static final long serialVersionUID = 2L;

        @Override
        public String evaluate(ReportParameters reportParameters) {
            //currentDoc.typePecheJJ
            System.out.println("sss"+reportParameters.getFieldValue("currentDoc.typePeche"));
            return  reportParameters.getValue("currentDoc.typePeche").toString().equals("Cotier")?"X":"";
        }

    }
    private class RetNumPage extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {
            System.out.println("kkk"+reportParameters.getFieldValue("numeroPage"));
            return  reportParameters.getValue("numeroPage") ;

        }

    }
    private class RetNomConcess extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;

        @Override
        public String evaluate(ReportParameters reportParameters) {

            System.out.println("kkk"+reportParameters.getFieldValue("refConcession"));
            return  reportParameters.getValue("refConcession") ;

        }

    }

    private class EnginExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
private Integer index;
        public EnginExpression(Integer i) {
            index=i;
        }

        @Override
        public String evaluate(ReportParameters reportParameters) {

            System.out.println("kkk"+reportParameters.getFieldValue("maillages"));
            List<String> mm=reportParameters.getValue("maillages");


            return mm.get(index);

        }

    }
    private class ChoisExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        private Integer index;
        public ChoisExpression(Integer i) {
            index=i;
        }

        @Override
        public String evaluate(ReportParameters reportParameters) {

            System.out.println("kkk"+reportParameters.getFieldValue("choixEngins"));
            List<Boolean> mm=reportParameters.getValue("choixEngins");

      if(mm.get(index).equals(true)) return "X";
            else return "";


        }

    }
    private class DepartExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        private Integer index;


        public DepartExpression(Integer index) {
            this.index = index;
        }

        @Override
        public String evaluate(ReportParameters reportParameters) {
            SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yyyy");
            //	if((!splitarray[13].equals("VIDE")) || (!splitarray[14].equals("VIDE"))) {
            String ret=null,dep=null;


            dep = new SimpleDateFormat("MM/dd/yyyy").format(reportParameters.getValue("currentDoc.depart"));
           // ret = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

            String cellTextJ1 = StringUtils.rightPad(StringUtils.substringBefore(dep, "/"), 2);
            String cellTextM1 = StringUtils.rightPad(StringUtils.substringBetween(dep, "/"), 2);
            String cellTextY1 = StringUtils.rightPad(StringUtils.substringAfterLast(dep, "/"), 4);
           // String cellTextJ2 = StringUtils.rightPad(StringUtils.substringBefore(ret, "/"), 2);
            //String cellTextM2 = StringUtils.rightPad(StringUtils.substringBetween(ret, "/"), 2);
            //String cellTextY2 = StringUtils.rightPad(StringUtils.substringAfterLast(ret, "/"), 4);

            System.out.println("kkk"+reportParameters.getFieldValue("currentDoc.depart"));

            if(index==0)    return cellTextJ1;
            if(index==1)    return cellTextM1;
            if(index==2)    return cellTextY1;
            else return null;
        }

           }
    private class RetourExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;

        private Integer index;

        public RetourExpression(Integer retour) {
            this.index = retour;
        }

        @Override
        public String evaluate(ReportParameters reportParameters) {

            SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yyyy");
            //	if((!splitarray[13].equals("VIDE")) || (!splitarray[14].equals("VIDE"))) {
            String ret=null,dep=null;


            dep = new SimpleDateFormat("MM/dd/yyyy").format(reportParameters.getValue("currentDoc.retour"));
            // ret = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

            String cellTextJ2 = StringUtils.rightPad(StringUtils.substringBefore(dep, "/"), 2);
            String cellTextM2 = StringUtils.rightPad(StringUtils.substringBetween(dep, "/"), 2);
            String cellTextY2 = StringUtils.rightPad(StringUtils.substringAfterLast(dep, "/"), 4);
            // String cellTextJ2 = StringUtils.rightPad(StringUtils.substringBefore(ret, "/"), 2);
            //String cellTextM2 = StringUtils.rightPad(StringUtils.substringBetween(ret, "/"), 2);
            //String cellTextY2 = StringUtils.rightPad(StringUtils.substringAfterLast(ret, "/"), 4);

            System.out.println("kkk"+reportParameters.getFieldValue("currentDoc.depart"));

            if(index==0)    return cellTextJ2;
            if(index==1)    return cellTextM2;
            if(index==2)    return cellTextY2;
            else return null;

        }
  }
   private class NomCapitaineExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("nomCapitaine");

        }
    }
    private class GTExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("gt");

        }
    }
    private class IMOExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("imo");

        }
    }
    private class NomNavireExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("nomNavire");

        }
    }

    private class LicencePecheExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("licencePeche");

        }
    }
    private class DateExpirationExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("dateExpiration");

        }
    }

    private class SegPecheExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("segPeche");

        }
    }

    private class QuotaExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("quota");

        }
    }
    private class DureeExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("dureeCobcession");

        }
    }
    private class SupportExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("support");

        }
    }
    private class RefConcessionExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
     @Override
        public String evaluate(ReportParameters reportParameters) {

       return reportParameters.getValue("refConcession");

        }
    }
    private class NomConcessionnaireExpression extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        @Override
        public String evaluate(ReportParameters reportParameters) {

            return reportParameters.getValue("nomConcessionnaire");

        }
    }
    // pour les debarquements
    private class RetConcession extends AbstractSimpleExpression<String> {
        private static final long serialVersionUID = 1L;
        private Integer index;
        public RetConcession(Integer in) {
            index=in;
        }

        @Override
        public String evaluate(ReportParameters reportParameters) {
            String ret=null;

       List<String>  chs=reportParameters.getValue("choisTypesConcessions");
            System.out.println("bbbbb"+chs);
          if(index==0) {
             if(chs.contains("1") || chs.contains("13")) ret="X"; else ret="";
                }
          if(index==1) {
                if(chs.contains("2") || chs.contains("13")) ret="X"; else ret="";
             }
            if(index==2) {
                if(chs.contains("3") || chs.contains("14")) ret="X"; else ret="";
                 }
            if(index==3) {
                if(chs.contains("4") || chs.contains("15")) ret="X"; else ret="";
            }
            if(index==4) {
                if(chs.contains("5") || chs.contains("19")) ret="X"; else ret="";
            }

return ret;

     }
    }

}
