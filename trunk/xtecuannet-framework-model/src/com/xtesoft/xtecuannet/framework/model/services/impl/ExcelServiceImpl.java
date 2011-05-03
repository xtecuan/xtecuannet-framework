/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.services.impl;

import com.xtesoft.xtecuannet.framework.model.services.ExcelService;
import com.xtesoft.xtecuannet.framework.utils.ClassUtils;
import com.xtesoft.xtecuannet.framework.utils.Configurator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author xtecuan
 */
public class ExcelServiceImpl implements ExcelService {

    private static final Logger logger = Logger.getLogger(ExcelServiceImpl.class);
    public static final File TMP_DIR_FILE = createTempDir();

    public static File createTempDir() {

        File dir = new File(TMP_DIR);

        if (!dir.exists()) {

            if (dir.mkdirs()) {

                logger.info("The file : " + dir.getPath() + " was created");
            }
        }

        return dir;

    }

    public HSSFCellStyle createCellStyle(HSSFWorkbook myWorkBook) {
        HSSFCellStyle style = myWorkBook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.BLACK.index);
        return style;
    }

    public HSSFWorkbook generateWorkbook(String templateName, List<List<Object[]>> datos, int[] fromRow) {
        HSSFWorkbook myWorkBook = null;
        try {
            InputStream is =
                    new FileInputStream(Configurator.obtenerEntradaConfig(EXCEL_PATH_KEY)
                    + templateName);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(is);
            myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFCellStyle style = createCellStyle(myWorkBook);

            int hoja = 0;
            for (List<Object[]> cdata : datos) {

                HSSFSheet mySheet = myWorkBook.getSheetAt(hoja);
                int iniRow = fromRow[hoja];
                for (Object[] dato : cdata) {
                    HSSFRow row = mySheet.createRow(iniRow);
                    for (int i = 0; i < dato.length; i++) {
                        HSSFCell cell = row.createCell(i);
                        cell.setCellStyle(style);
                        if (dato[i] instanceof String) {
                            cell.setCellValue(new HSSFRichTextString(dato[i].toString()));
                        } else if (dato[i] instanceof Long) {
                            cell.setCellValue((Long) (dato[i]));
                        } else if (dato[i] instanceof Integer) {
                            cell.setCellValue((Integer) (dato[i]));
                        } else if (dato[i] instanceof BigDecimal) {
                            cell.setCellValue(new Double((dato[i].toString())));
                        } else if (dato[i] instanceof Boolean) {
                            cell.setCellValue((Boolean) (dato[i]));
                        } else if (dato[i] instanceof java.util.Date) {
                            cell.setCellValue((new HSSFRichTextString(dato[i].toString())));
                        } else if (dato[i] instanceof java.sql.Date) {
                            cell.setCellValue((new HSSFRichTextString(dato[i].toString())));
                        } else if (dato[i] instanceof Double) {
                            cell.setCellValue((Double) (dato[i]));
                        } else if (dato[i] instanceof Float) {
                            cell.setCellValue((Float) (dato[i]));
                        } else if (dato[i] instanceof Short) {
                            cell.setCellValue((Short) (dato[i]));
                        }
                    }
                    iniRow++;
                }
                hoja++;
            }

        } catch (FileNotFoundException ex1) {
            logger.error(ex1);
        } catch (IOException ex) {
            logger.error(ex);
        }
        return myWorkBook;
    }

    public List<?> getExcelData(File excelFile, int[] fromRow, Class pojo) {
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

    public List<?> getExcelData(InputStream excelStream, int[] fromRow, Class pojo) {
        List<Object> salida = new ArrayList<Object>(0);

        HSSFWorkbook myWorkBook = null;

        try {
//            InputStream is = new FileInputStream(excelFile);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(excelStream);
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
}
