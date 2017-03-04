package com.gardecote.reports;

import com.gardecote.entities.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 28/01/2017.
 */
public class licencesExcelView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//VARIABLES REQUIRED IN MODEL
        String sheetName = (String)map.get("sheetname");
        List<qLic> licences= (List<qLic>)map.get("licences");
        //BUILD DOC
        Sheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth((short) 12);
        int currentRow = 0;
        short currentColumn = 0;
        //CREATE STYLE FOR HEADER
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);
        //POPULATE HEADER COLUMNS
        Row headerRow = sheet.createRow(currentRow);


        currentColumn = 0;

        List<String> headers = new ArrayList<String>();
        headers.add("type de licence");
        headers.add("concession");
        headers.add("concessionnaire");
        headers.add("type d'accord international");
        headers.add("id qtypnav");
        headers.add("type nav");
         headers.add("id zone");
        headers.add("zone");
         headers.add("id nation");
        headers.add("designation nation");
        headers.add("qcatressources");
        // this.qcatressources = qcatressources;
        headers.add("qnavire");
        headers.add("typb");
        headers.add("dateDebutAuth");
        headers.add("dateFinAuth");
         headers.add("anneeconstr");
          headers.add("balise");
         headers.add("calpoids");
       headers.add("count");
          headers.add("eff");
         headers.add("gt");
         headers.add("imo");
          headers.add("kw");
         headers.add("larg");
        headers.add("longg");
        headers.add("nbrhomm");
        headers.add("nomar");
        headers.add("numimm ");
        headers.add("numlic");
         headers.add("port");
        headers.add("puimot");
        headers.add("radio");
        headers.add("tjb");



        for( String header : headers ){
            RichTextString textFieldHeader = new XSSFRichTextString(header);
            Cell cell = headerRow.createCell(currentColumn);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(textFieldHeader);
            currentColumn++;
        }

        //POPULATE VALUE ROWS/COLUMNS
        currentRow++;//exclude header

        for(qLic result: licences) {
            System.out.println(currentRow);

            Row row = sheet.createRow(currentRow);
            List<String> strings = new ArrayList<String>();
            if (result instanceof qLicenceNational)
            {  strings.add("National");
        if(((qLicenceNational) result).getQconcession()!=null)
                headers.add(((qLicenceNational) result).getQconcession().getRefConcession());
                else headers.add("");
                if(((qLicenceNational) result).getQconcession()!=null)
                    headers.add(((qLicenceNational) result).getQconcession().getQconsignataire().getNomconsignataire());
                else headers.add("");
                headers.add("");

            }
            if (result instanceof qLicenceLibre)
            {
                strings.add("Etrangere");
                headers.add("");
                headers.add("");
                headers.add(((qLicenceLibre) result).getAccord().getAbbrevAccor().toString());
            }

            strings.add(result.getNumimm().toString());
            strings.add(result.getQtypnav().getIdTypeLic().toString());
            strings.add(result.getQtypnav().getDescr().toString());
            strings.add(result.getZone().getIdZone().toString());
            strings.add(result.getZone().getNom().toString());
            strings.add(result.getNation().getIdNation().toString());
            strings.add(result.getNation().getDesignation().toString());
            String strCat="";
            Integer i=1;
            for(qCategRessource catRess:result.getQcatressources()) {
                i++;
                strCat=strCat+catRess.getIdtypeConcession();
              if(i<result.getQcatressources().size())  strCat=strCat+";";
            }
            strings.add(result.getQcatressources().toString());
            // this.qcatressources = qcatressources;
              strings.add(result.getQnavire().getNumimm().toString());
            strings.add(result.getQnavire().getNomnav().toString());
              strings.add(result.getTypb().toString());
              strings.add(result.getDateDebutAuth().toString());
              strings.add(result.getDateFinAuth().toString());
              strings.add(result.getAnneeconstr().toString());
              strings.add(result.getBalise().toString());
              strings.add(result.getCalpoids().toString());
               strings.add(result.getCount().toString());
               strings.add(result.getEff().toString());
               strings.add(Float.toString(result.getGt()));
               strings.add(result.getImo().toString());
               strings.add(Float.toString(result.getKw()));
               strings.add(result.getLarg().toString());
              strings.add(result.getLongg().toString());
              strings.add(result.getNbrhomm().toString());
              strings.add(result.getNomar().toString());
              strings.add(result.getNumimm().toString());
              strings.add(result.getNumlic().toString());
              strings.add(result.getPort().toString());
              strings.add(result.getPuimot().toString());
              strings.add(result.getRadio().toString());
              strings.add(Float.toString(result.getTjb()));

            currentColumn = 0;
            for(String rowcell : strings){
                RichTextString textFieldValue = new XSSFRichTextString(rowcell);
                Cell cell = row.createCell(currentColumn);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(textFieldValue);
                currentColumn++;
            }


            currentRow++;
        }

        }

}
