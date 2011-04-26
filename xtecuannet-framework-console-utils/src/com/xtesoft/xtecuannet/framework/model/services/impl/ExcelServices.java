/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.services.impl;

import com.xtesoft.xtecuannet.framework.console.utils.ClassUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import sv.edu.ues.siaarafia.modelo.dto.NotasExcelDto;

/**
 *
 * @author xtecuan
 */
public class ExcelServices {

    private static Logger logger = Logger.getLogger(ExcelServices.class);

    public static List<?> getExcelData(File excelFile, int[] fromRow, Class pojo) {

        List<Object> salida = new ArrayList<Object>(0);

        HSSFWorkbook myWorkBook = null;

        try {
            InputStream is = new FileInputStream(excelFile);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(is);
            myWorkBook = new HSSFWorkbook(myFileSystem);

            int sheets = myWorkBook.getNumberOfSheets();

            for (int i = 0; i < sheets; i++) {

                HSSFSheet mySheet = myWorkBook.getSheetAt(i);

                int firstRowIndex = mySheet.getFirstRowNum();

//                logger.info(firstRowIndex);

                HSSFRow firstRow = mySheet.getRow(firstRowIndex);

                short lastCell = firstRow.getLastCellNum();

                List<String> colNames = new ArrayList<String>(0);

                for (int j = 0; j < lastCell; j++) {
                    int currentCell = j;

//                    logger.info(firstRow.getCell(currentCell));
                    colNames.add(firstRow.getCell(currentCell).toString());
                }

                int lastRowNumIndex = mySheet.getLastRowNum();

//                logger.info(lastRowNumIndex);

                logger.info(fromRow[i] + "----" + lastRowNumIndex);
                for (int k = fromRow[i]; k <= lastRowNumIndex; k++) {

                    HSSFRow currentRow = mySheet.getRow(k);

                    Object instance = pojo.newInstance();

                    for (int x = 0; x < colNames.size(); x++) {
                        String columnName = colNames.get(x);
                        HSSFCell cell = currentRow.getCell(x);

                        //logger.info(cell);

                        int cellType = cell.getCellType();

                        if (cellType == Cell.CELL_TYPE_STRING) {

                            if (cell.getStringCellValue() != null) {

                                ClassUtils.setPropertyToInstance(instance, columnName, cell.getStringCellValue());
                            }
                        } else if (cellType == Cell.CELL_TYPE_NUMERIC) {
                            if (cell.toString() != null) {
                                ClassUtils.setPropertyToInstance(instance, columnName, new BigDecimal(cell.getNumericCellValue()));
                            }
                        } else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
                            if (cell.toString() != null) {
                                ClassUtils.setPropertyToInstance(instance, columnName, Boolean.valueOf(cell.getBooleanCellValue()));
                            }
                        }



                    }

                    salida.add(instance);
                }



            }




        } catch (Exception e) {

            logger.info("Error reading Excel File: ", e);
        }

        return salida;
    }

    public static void main(String[] args) {

        File excel = new File("/home/xtecuan/Documents/xtecuannet-framework/xtecuannet-framework-console-utils/excel/formatoNotas.xls");
        List<NotasExcelDto> dtos = (List<NotasExcelDto>) getExcelData(excel, new int[]{3}, NotasExcelDto.class);

        logger.info("Size=" + dtos.size());

        for (NotasExcelDto current : dtos) {
            logger.info(current);
        }
    }
}
