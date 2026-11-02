package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    private Workbook workbook;
    private Sheet sheet;
    private int rowNum;

    //constructor
    public ExcelReader(String filePath, String sheetName, int rowNum){
        try{
            FileInputStream fis=new FileInputStream(filePath);
            workbook=WorkbookFactory.create(fis); // opens the Excel sheet whether its .xls or .xlsx
            sheet=workbook.getSheet(sheetName);
            this.rowNum=rowNum;
            if(sheet==null){
                throw new RuntimeException("Sheet not found - "+sheetName);
            }
            fis.close();
        }catch (Exception e){
            throw new RuntimeException("Unable to load excel file!", e);
        }
    }

    // To find the columnName in sheet
    private int getColumnIndex(String columnName){
        Row headerRow=sheet.getRow(0);
        DataFormatter formatter=new DataFormatter();

        for(Cell cell:headerRow){
            String header=formatter.formatCellValue(cell);
            if(header.trim().equalsIgnoreCase(columnName)){
                return cell.getColumnIndex();
            }
        }
        throw new RuntimeException("Column not found! - "+columnName);
    }

    // to get the cell data
    public String getCellData(String columnName){
        int colNum=getColumnIndex(columnName);
        Row row=sheet.getRow(rowNum);

        if(row==null || row.getCell(colNum)==null){
            return "";
        }
        return new DataFormatter().formatCellValue(row.getCell(colNum));
    }


    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }} catch(IOException e){
                throw new RuntimeException(e);
            }
        }


}
