package com.gardecote.reports;


import com.gardecote.entities.qNavireLegale;
import com.gardecote.entities.qZone;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.util.ReflectionUtils;
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
public class bateauExcelView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//VARIABLES REQUIRED IN MODEL
        String sheetName = (String)map.get("sheetname");
        List<qNavireLegale> navires = (List<qNavireLegale>)map.get("navires");
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

        Field[] fields = qNavireLegale.class.getDeclaredFields();
        currentColumn = 0;

        List<String> headers = new ArrayList<String>();
        headers.add("Numimm");

        headers.add("Nomnav");
        headers.add("Nomar");
        headers.add("Longg");
        headers.add("Puimot");
        headers.add("Nation");
         headers.add("Larg");
        headers.add("Count");
        headers.add("Nbrhomm");
        headers.add("Eff");
        headers.add("Anneeconstr");
        headers.add("Calpoids");
         headers.add("Gt");
         headers.add("Kw");
        headers.add("Tjb");
        headers.add("Imo");
         headers.add("Port");
        headers.add("Radio");
        headers.add("Balise");
        headers.add("UpdatedOn");


        for( String header : headers ){
            RichTextString textFieldHeader = new XSSFRichTextString(header);
            Cell cell = headerRow.createCell(currentColumn);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(textFieldHeader);
            currentColumn++;
        }

        //POPULATE VALUE ROWS/COLUMNS
        currentRow++;//exclude header

        for(qNavireLegale result: navires) {
            System.out.println(currentRow);

            Row row = sheet.createRow(currentRow);
            List<String> strings = new ArrayList<String>();

            strings.add(result.getNumimm().toString());
            strings.add(result.getNomnav().toString());
            strings.add(result.getNomar().toString());
            strings.add(result.getLongg().toString());
            strings.add(result.getPuimot().toString());
            strings.add(result.getNation().getIdNation().toString());
            strings.add(result.getLarg().toString());
            strings.add(result.getCount().toString());
            strings.add(result.getNbrhomm().toString());
            strings.add(result.getEff().toString());
            strings.add(result.getAnneeconstr().toString());
            strings.add(result.getCalpoids().toString());
            strings.add(Float.toString(result.getGt()));
            strings.add(Float.toString(result.getKw()));
            strings.add(Float.toString(result.getTjb()));
            strings.add(result.getImo().toString());
            strings.add(result.getPort().toString());
            strings.add(result.getRadio().toString());
            strings.add(result.getBalise().toString());
            strings.add(result.getUpdatedOn().toString());


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
