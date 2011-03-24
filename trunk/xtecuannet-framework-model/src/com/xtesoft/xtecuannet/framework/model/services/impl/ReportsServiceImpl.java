/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.services.impl;


import com.xtesoft.xtecuannet.framework.utils.Configurator;
import com.xtesoft.xtecuannet.framework.utils.ReportsUtil;
import com.xtesoft.xtecuannet.framework.model.services.ReportsService;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.ui.jasperreports.JasperReportsUtils;

/**
 *
 * @author xtecuan
 */
public class ReportsServiceImpl implements ReportsService {

    private static final Logger logger = Logger.getLogger(ReportsServiceImpl.class);
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     *
     * @param pNombreReporte
     * @param pParametrosReporte
     * @return
     */
    public byte[] generarReporteRTF(String pNombreReporte, Map pParametrosReporte) {

        byte[] salida = new byte[10000];
        Connection vSqlConnection = null;
        Connection real = null;
        try {

            if (dataSource != null) {
                logger.info("Hay datasource: " + dataSource.getClass());
            }

            vSqlConnection = dataSource.getConnection();

            if (vSqlConnection != null) {
                logger.info("Hay conexiÃ³n hacia la base: " + vSqlConnection.getCatalog());
            }

            //    real = ((oracle.jdbc.OracleConnection)vSqlConnection)._getPC();

            // Cargar el template del reporte
            InputStream vReportInputStream =
                    new FileInputStream(Configurator.obtenerEntradaConfig(REPORTS_PATH)
                    + pNombreReporte);

            salida = ReportsUtil.exportarReporteRTF(vReportInputStream,
                    pParametrosReporte, vSqlConnection);


        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);

        } finally {
            try {
                if (vSqlConnection != null) {
                    // Devolver la conexion al pool
                    vSqlConnection.close();
                }
            } catch (SQLException e) {
                // Esta excepcion no se lanza pues solo representa el fallo al
                // cerrar la conexion
                logger.error(e);
            }
            logger.info("salio serv com imp ");
        }

        return salida;

    }

    /**
     *
     * @param pNombreReporte
     * @param pParametrosReporte
     * @param data
     * @return
     */
    public byte[] generarReporteRTF(String pNombreReporte, Map pParametrosReporte, Collection data) {

        byte[] salida = new byte[10000];

        try {

            // Cargar el template del reporte
            InputStream vReportInputStream =
                    new FileInputStream(Configurator.obtenerEntradaConfig(REPORTS_PATH)
                    + pNombreReporte);

            salida = ReportsUtil.exportarReporteRTF(vReportInputStream,
                    pParametrosReporte, JasperReportsUtils.convertReportData(data));


        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);

        }

        return salida;

    }

    /**
     * 
     * @param pNombreReporte
     * @param pParametrosReporte
     * @return
     */
    public byte[] generarReportePDF(String pNombreReporte, Map pParametrosReporte) {

        byte[] salida = new byte[10000];
        Connection vSqlConnection = null;
        Connection real = null;
        try {

            if (dataSource != null) {
                logger.info("Hay datasource: " + dataSource.getClass());
            }

            vSqlConnection = dataSource.getConnection();

            if (vSqlConnection != null) {
                logger.info("Hay conexiÃ³n hacia la base: " + vSqlConnection.getCatalog());
            }

            // real = ((oracle.jdbc.OracleConnection)vSqlConnection)._getPC();

            // Cargar el template del reporte
            InputStream vReportInputStream =
                    new FileInputStream(Configurator.obtenerEntradaConfig(REPORTS_PATH)
                    + pNombreReporte);

            salida = ReportsUtil.exportarReportePDF(vReportInputStream,
                    pParametrosReporte, vSqlConnection);


        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);

        } finally {
            try {
                if (vSqlConnection != null) {
                    // Devolver la conexion al pool
                    vSqlConnection.close();
                }
            } catch (SQLException e) {
                // Esta excepcion no se lanza pues solo representa el fallo al
                // cerrar la conexion
                logger.error(e);
            }
            logger.info("salio serv com imp ");
        }

        return salida;

    }

    /**
     *
     * @param pNombreReporte
     * @param pParametrosReporte
     * @param data
     * @return
     */
    public byte[] generarReportePDF(String pNombreReporte, Map pParametrosReporte, Collection data) {

        byte[] salida = new byte[10000];

        try {

            // Cargar el template del reporte
            InputStream vReportInputStream =
                    new FileInputStream(Configurator.obtenerEntradaConfig(REPORTS_PATH)
                    + pNombreReporte);

            salida = ReportsUtil.exportarReportePDF(vReportInputStream,
                    pParametrosReporte, JasperReportsUtils.convertReportData(data));


        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);

        }

        return salida;

    }

    /**
     *
     * @param nombre
     * @param extension
     * @return
     */
    public byte[] generarReporteSinParametros(String nombre, String extension) {

        byte[] salida = null;
        Map mapa = new HashMap();

        mapa.put("SUBREPORT_DIR",
                Configurator.obtenerEntradaConfig(REPORTS_PATH));

        if (extension.toLowerCase().equals(DOC) || extension.toLowerCase().equals(RTF)) {

            salida = generarReporteRTF(nombre, mapa);
        }
        if (extension.toLowerCase().equals(PDF)) {
            salida = generarReportePDF(nombre, mapa);
        }

        return salida;
    }

    /**
     * 
     * @param nombre
     * @param extension
     * @param pParametrosReporte
     * @return
     */
    public byte[] generarReporteConParametros(String nombre, String extension, Map pParametrosReporte) {

        byte[] salida = null;

        pParametrosReporte.put(SUBREPORT_DIR,
                Configurator.obtenerEntradaConfig(REPORTS_PATH));

        if (extension.toLowerCase().equals(DOC) || extension.toLowerCase().equals(RTF)) {

            salida = generarReporteRTF(nombre, pParametrosReporte);
        }
        if (extension.toLowerCase().equals(PDF)) {
            salida = generarReportePDF(nombre, pParametrosReporte);
        }

        return salida;
    }

    @Override
    public byte[] generarReporteSinParametros(String nombre, String extension, Collection data) {
        byte[] salida = null;
        Map mapa = new HashMap();

        mapa.put("SUBREPORT_DIR",
                Configurator.obtenerEntradaConfig(REPORTS_PATH));

        if (extension.toLowerCase().equals(DOC) || extension.toLowerCase().equals(RTF)) {

            salida = generarReporteRTF(nombre, mapa, data);
        }
        if (extension.toLowerCase().equals(PDF)) {
            salida = generarReportePDF(nombre, mapa, data);
        }

        return salida;
    }

    @Override
    public byte[] generarReporteConParametros(String nombre, String extension, Map pParametrosReporte, Collection data) {
        byte[] salida = null;

        pParametrosReporte.put(SUBREPORT_DIR,
                Configurator.obtenerEntradaConfig(REPORTS_PATH));

        if (extension.toLowerCase().equals(DOC) || extension.toLowerCase().equals(RTF)) {

            salida = generarReporteRTF(nombre, pParametrosReporte, data);
        }
        if (extension.toLowerCase().equals(PDF)) {
            salida = generarReportePDF(nombre, pParametrosReporte, data);
        }

        return salida;
    }
}
