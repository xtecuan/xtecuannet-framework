/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.services;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author xtecuan
 */
public interface ExcelService {

    public static final String EXCEL_PATH_KEY = "excel_path";
    public static final String TMP_DIR_XTECUANNET = ".xtecuannet_framework_temp";
    public static final String TMP_DIR = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + TMP_DIR_XTECUANNET;

    /**
     *
     * @param myWorkBook el libro al cual queremos aplicarle el estilo a las celdas
     * @return HSSFCellStyle un estilo para celdas de una hoja de calculo
     */
    public HSSFCellStyle createCellStyle(HSSFWorkbook myWorkBook);

    /**
     *
     * @param templateName Nombre del template de excel a procesar: ejemplos: INFORME_DE_MES_CSMS.xls
     * @param datos esta lista de listas contiene la data que sera vaciada en el template
     * @param fromRow un arreglo conteniendo las filas desde las cuales será vaciada la data este
     * tiene length = datos.size = numero de hojas dentro del libro del template
     * @return HSSFWorkbook el libro lleno de datos
     */
    public HSSFWorkbook generateWorkbook(String templateName, List<List<Object[]>> datos, int[] fromRow);

    /**
     * 
     * @param excelFile Archivo de Excel
     * @param fromRow arreglo de enteros que marca las posiciones desde las cuales se leera la data
     * @param pojo clase de la cual se generarán instancias para devolver los datos
     * @return 
     */
    public List<?> getExcelData(File excelFile, int[] fromRow, Class pojo);

    /**
     * 
     * @param excelStream
     * @param fromRow
     * @param pojo
     * @return 
     */
    public List<?> getExcelData(InputStream excelStream, int[] fromRow, Class pojo);
}
