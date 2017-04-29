package com.gardecote.reports;

import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qLic;
import com.gardecote.entities.qLicenceLibre;
import com.gardecote.entities.qLicenceNational;
import com.gardecote.web.qModelConcessionExport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 22/04/2017.
 */
public class concessionExportExcelView extends AbstractXlsxView  {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//VARIABLES REQUIRED IN MODEL

        List<qModelConcessionExport> concessions= (List<qModelConcessionExport>)map.get("Concession");
        //BUILD DOC
        Sheet sheet = workbook.createSheet("Concessions");
        sheet.setDefaultColumnWidth((short) 12);
        int currentRow = 0;
        short currentColumn = 0;
        Cell cell1=null,cell2=null;
        RichTextString textFieldValueRef=null,textFieldValueNom=null;
        //CREATE STYLE FOR HEADER
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);
        //POPULATE HEADER COLUMNS
        Row headerRow = sheet.createRow(currentRow);
        currentColumn = 0;

        List<String> headers = new ArrayList<String>();
        headers.add("Ref");
        headers.add("Nom");


        for( String header : headers ){
            RichTextString textFieldHeader = new XSSFRichTextString(header);
            Cell cell = headerRow.createCell(currentColumn);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(textFieldHeader);
            currentColumn++;
        }

        //POPULATE VALUE ROWS/COLUMNS
        currentRow++;//exclude header

        for(qModelConcessionExport result: concessions) {
            System.out.println(currentRow);
            Row row = sheet.createRow(currentRow);

            currentColumn = 0;
            for(qModelConcessionExport rowcell : concessions){
               textFieldValueRef = new XSSFRichTextString(rowcell.getRefConcession());
              textFieldValueNom = new XSSFRichTextString(rowcell.getNomConcessionnaire());
            cell1 = row.createCell(currentColumn);
                cell1.setCellStyle(headerStyle);
                cell1.setCellValue(textFieldValueRef );
                 cell2 = row.createCell(currentColumn);
                cell2.setCellStyle(headerStyle);
                cell2.setCellValue(textFieldValueNom );
                currentColumn++;
            }


            currentRow++;
        }

    }
}
