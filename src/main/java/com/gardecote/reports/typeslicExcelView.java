package com.gardecote.reports;

import com.gardecote.entities.qZone;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 28/01/2017.
 */
public class typeslicExcelView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//VARIABLES REQUIRED IN MODEL
        String sheetName = (String)map.get("sheetname");
        List<qZone> zones = (List<qZone>)map.get("zones");
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
        RichTextString text = new XSSFRichTextString("Id zone");
        RichTextString text1 = new XSSFRichTextString("NOM");
        Cell cell = headerRow.createCell(0);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(text);
        Cell cell1 = headerRow.createCell(1);
        cell1.setCellStyle(headerStyle);
        cell1.setCellValue(text1);

        //POPULATE VALUE ROWS/COLUMNS
        currentRow++;//exclude header

        for(qZone result: zones) {
            System.out.println(currentRow);
            currentColumn = 0;
            Row row = sheet.createRow(currentRow);
       //  cell.setCellType(Cell.CELL_TYPE_NUMERIC);
       //  cell.setCellValue(result.getIdZone());
            Cell cellX0 = row.createCell(currentColumn);
            RichTextString textg = new XSSFRichTextString(result.getIdZone().toString());
            cellX0.setCellValue(textg);
            currentColumn++;
            Cell cellX1 = row.createCell(currentColumn);
            RichTextString textb = new XSSFRichTextString(result.getNom().toString());
            cellX1.setCellValue(textb);
            currentColumn++;
            currentRow++;
            }

        }

}
