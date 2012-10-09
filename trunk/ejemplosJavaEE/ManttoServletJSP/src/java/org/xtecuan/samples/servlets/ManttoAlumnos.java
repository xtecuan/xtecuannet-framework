/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.samples.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.xtecuan.samples.beans.facade.AlumnosFacade;
import org.xtecuan.samples.dto.Alumnos;

/**
 *
 * @author xtecuan
 */
public class ManttoAlumnos extends HttpServlet {

    private static final String CMD_ADD = "add";
    private static final String CMD_EDIT = "edit";
    private static final String CMD_LIST = "list";
    private static final String OUTCOME = "index.jsp";
    private static final Logger logger = Logger.getLogger(ManttoAlumnos.class);
    @Resource(name = "jdbc/alumnos")
    private DataSource dataSource;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String salidaMsg = null;
        String errorMsg = null;

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if (action != null && action.equals(CMD_ADD)) {
            Alumnos alum = (Alumnos) session.getAttribute("alumno");
            logger.info("Alumno actual: " + alum);
            AlumnosFacade facade = new AlumnosFacade(dataSource, alum);
            try {
                Alumnos salida = facade.guardarAlumno();
                session.removeAttribute("alumno");
                session.setAttribute("alumno", salida);
                logger.info("Alumno con id: " + salida.getId());
                salidaMsg = "Se guardo el alumno con Id: " + salida.getId();
            } catch (Exception ex) {
                logger.error("Error al guardar el alumno:", ex);
                errorMsg = "Error al guardar el alumno: " + ex.getMessage();
            }
            if (salidaMsg != null) {
                session.setAttribute("outmsg", salidaMsg);
            }

            if (errorMsg != null) {
                session.setAttribute("errormsg", errorMsg);
            }
            response.sendRedirect(OUTCOME);

        } else if (action.equals(CMD_LIST)) {            
            AlumnosFacade facade = new AlumnosFacade(dataSource);
            List<Alumnos> alumnos = new ArrayList<Alumnos>(0);
            try {
                alumnos = facade.encontrarAlumnos();
            } catch (Exception e) {
                logger.error("Excepción encontrando los alumnos", e);
            }
            
            session.setAttribute("alumnos", alumnos);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para Mantto Tabla Alumnos";
    }// </editor-fold>
}
