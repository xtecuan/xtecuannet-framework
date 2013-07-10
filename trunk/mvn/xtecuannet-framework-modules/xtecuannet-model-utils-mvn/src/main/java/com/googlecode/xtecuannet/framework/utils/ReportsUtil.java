package com.googlecode.xtecuannet.framework.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportsUtil {

    /**
     * Permite cargar la plantilla (.jrxml) como un objeto del tipo
     * {@link JasperDesign}
     *
     * @param pPlantilla
     *            El {@link InputStream} que representa el archivo de la
     *            plantilla, usualmete puede ser un objeto del tipo
     *            {@link FileInputStream}
     * @return Un objeto del tipo {@link JasperDesign}
     * @throws JRException
     */
    private static JasperDesign cargarPlantilla(InputStream pPlantilla)
            throws JRException {
        if (pPlantilla == null) {
            throw new IllegalArgumentException(
                    "El InputStream de la plantilla no puede ser nulo");
        }
        return JRXmlLoader.load(pPlantilla);
    }

    /**
     * Permite compilar un reporte dado un objeto del tipo {@link JasperDesign}
     *
     * @param pTemplate
     *            Una plantilla {@link JasperDesign} compilada con el metodo
     *            <code>cargarPlantilla(InputStream)</code>
     * @return un objeto del tipo {@link JasperDesign}
     * @throws JRException
     */
    private static JasperReport compilarReporte(JasperDesign pTemplate)
            throws JRException {
        if (pTemplate == null) {
            throw new IllegalArgumentException(
                    "El JasperDesign de la plantilla no puede ser nulo");
        }
        return JasperCompileManager.compileReport(pTemplate);
    }

    /**
     * Permite realizar la impresiÃ³n del reporte y lo representa a travez de un
     * objeto del tipo {@link JasperPrint}
     *
     * @param pReporteCompilado
     *            Un objeto del tipo {@link JasperReport}
     * @param pParametrosReporte
     *            Los parametros que se enviaran al reporte
     * @param pConexion
     *            Un objeto del tipo {@link Connection} que llenara el reporte
     *            via JDBC
     * @return un objeto del tipo {@link JasperPrint}
     * @throws JRException
     */
    public static JasperPrint imprimirReporte(JasperReport pReporteCompilado,
            Map pParametrosReporte, Connection pConexion)
            throws JRException {

        if (pReporteCompilado == null) {
            throw new IllegalArgumentException(
                    "El JasperReport de la plantilla no puede ser nulo");
        }

        if (pConexion == null) {
            throw new IllegalArgumentException(
                    "La conexiÃ³n JDBC no puede ser nulo");
        }

        return JasperFillManager.fillReport(pReporteCompilado,
                pParametrosReporte, pConexion);
    }

    /**
     * Permite realizar la impresiÃ³n del reporte y lo representa a travez de un
     * objeto del tipo {@link JasperPrint}
     *
     * @param pReporteCompilado
     *            Un objeto del tipo {@link JasperReport}
     * @param pParametrosReporte
     *            Los parametros que se enviaran al reporte
     * @param pJRDataSource
     * @return un objeto del tipo {@link JasperPrint}
     * @throws JRException
     */
    public static JasperPrint imprimirReporte(JasperReport pReporteCompilado,
            Map pParametrosReporte, JRDataSource pJRDataSource)
            throws JRException {

        if (pReporteCompilado == null) {
            throw new IllegalArgumentException(
                    "El JasperReport de la plantilla no puede ser nulo");
        }

        if (pJRDataSource == null) {
            throw new IllegalArgumentException(
                    "La conexiÃ³n JRDataSource no puede ser nulo");
        }

        return JasperFillManager.fillReport(pReporteCompilado,
                pParametrosReporte, pJRDataSource);
    }

    /**
     * Permite exportar un reporte en formato RTF a travez de una conexion JDBC
     *
     * @param pPlantilla
     *            El {@link InputStream} que representa el archivo de la
     *            plantilla, usualmete puede ser un objeto del tipo
     *            {@link FileInputStream}
     * @param pParametrosReporte
     *            Los parametros que se enviaran al reporte
     * @param pConexion
     *            Un objeto {@link Connection} que llenara el reporte via JDBC
     * @return
     * @throws JRException
     */
    public static byte[] exportarReporteRTF(InputStream pPlantilla,
            Map pParametrosReporte, Connection pConexion)
            throws JRException {

        JasperDesign vDisenhoReporte = cargarPlantilla(pPlantilla);
        JasperReport vcompilarReporte = compilarReporte(vDisenhoReporte);
        JasperPrint vJasperPrint = imprimirReporte(vcompilarReporte,
                pParametrosReporte, pConexion);
        ByteArrayOutputStream vByteOutputStream = new ByteArrayOutputStream();

        JRRtfExporter vRtfExporter = new JRRtfExporter();



        vRtfExporter.setParameter(JRTextExporterParameter.JASPER_PRINT,
                vJasperPrint);
        vRtfExporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM,
                vByteOutputStream);

        vRtfExporter.exportReport();

        return vByteOutputStream.toByteArray();
    }

    /**
     *
     * @param pPlantilla InputStream con el reporte
     * @param pParametrosReporte Map con los parametros del reporte
     * @param jrDataSource JRDataSource con los datos del reporte traidos a partir de los servicios de negocio
     * @return byte[] array con la impresion del reporte en RTF
     * @throws JRException
     */
    public static byte[] exportarReporteRTF(InputStream pPlantilla,
            Map pParametrosReporte, JRDataSource jrDataSource)
            throws JRException {

        JasperDesign vDisenhoReporte = cargarPlantilla(pPlantilla);
        JasperReport vcompilarReporte = compilarReporte(vDisenhoReporte);
        JasperPrint vJasperPrint = imprimirReporte(vcompilarReporte,
                pParametrosReporte, jrDataSource);
        ByteArrayOutputStream vByteOutputStream = new ByteArrayOutputStream();

        JRRtfExporter vRtfExporter = new JRRtfExporter();



        vRtfExporter.setParameter(JRTextExporterParameter.JASPER_PRINT,
                vJasperPrint);
        vRtfExporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM,
                vByteOutputStream);

        vRtfExporter.exportReport();

        return vByteOutputStream.toByteArray();
    }

    /**
     * Permite exportar un reporte en formato PDF a travez de una conexion JDBC
     *
     * @param pPlantilla
     *            El {@link InputStream} que representa el archivo de la
     *            plantilla, usualmete puede ser un objeto del tipo
     *            {@link FileInputStream}
     * @param pParametrosReporte
     *            Los parametros que se enviaran al reporte
     * @param pConexion
     *            Un objeto {@link Connection} que llenara el reporte via JDBC
     * @return
     * @throws JRException
     */
    public static byte[] exportarReportePDF(InputStream pPlantilla,
            Map pParametrosReporte, Connection pConexion)
            throws JRException {

        JasperDesign vDisenhoReporte = cargarPlantilla(pPlantilla);
        JasperReport vcompilarReporte = compilarReporte(vDisenhoReporte);
        JasperPrint vJasperPrint = imprimirReporte(vcompilarReporte,
                pParametrosReporte, pConexion);
        ByteArrayOutputStream vByteOutputStream = new ByteArrayOutputStream();

        //JRRtfExporter vRtfExporter = new JRRtfExporter();
        JRPdfExporter vRtfExporter = new JRPdfExporter();


        vRtfExporter.setParameter(JRTextExporterParameter.JASPER_PRINT,
                vJasperPrint);
        vRtfExporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM,
                vByteOutputStream);

        vRtfExporter.exportReport();


        return vByteOutputStream.toByteArray();
    }

    /**
     *
     * @param pPlantilla InputStream con el reporte
     * @param pParametrosReporte Map con los parametros del reporte
     * @param jrDataSource JRDataSource con los datos del reporte traidos a partir de los servicios de negocio
     * @return byte[] array con la impresion del reporte en PDF
     * @throws JRException
     */
    public static byte[] exportarReportePDF(InputStream pPlantilla,
            Map pParametrosReporte, JRDataSource jrDataSource)
            throws JRException {

        JasperDesign vDisenhoReporte = cargarPlantilla(pPlantilla);
        JasperReport vcompilarReporte = compilarReporte(vDisenhoReporte);
        JasperPrint vJasperPrint = imprimirReporte(vcompilarReporte,
                pParametrosReporte, jrDataSource);
        ByteArrayOutputStream vByteOutputStream = new ByteArrayOutputStream();

        //JRRtfExporter vRtfExporter = new JRRtfExporter();
        JRPdfExporter vRtfExporter = new JRPdfExporter();


        vRtfExporter.setParameter(JRTextExporterParameter.JASPER_PRINT,
                vJasperPrint);
        vRtfExporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM,
                vByteOutputStream);

        vRtfExporter.exportReport();


        return vByteOutputStream.toByteArray();
    }
}
