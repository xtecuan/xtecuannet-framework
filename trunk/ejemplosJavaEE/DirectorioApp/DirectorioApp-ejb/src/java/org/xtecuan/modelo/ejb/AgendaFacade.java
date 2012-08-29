/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.modelo.ejb;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;
import org.xtecuan.modelo.dto.AgendaDTO;

/**
 *
 * @author xtecuan
 */
@WebService
@Stateless
@LocalBean
public class AgendaFacade {

    @Resource(name = "dataSource")
    private DataSource dataSource;
    private static Logger logger = Logger.getLogger(AgendaFacade.class);

    private Connection getConnection() {

        Connection conn = null;

        try {

            logger.info("Abriendo conexion....");
            conn = dataSource.getConnection();
            logger.info("Conexion abierta con exito");

        } catch (Exception e) {
            logger.error("Error al obtener la conexion: ", e);
        }
        return conn;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "encontrarPorId")
    public AgendaDTO encontrarPorId(@WebParam(name = "id") Long id) {
        //TODO write your implementation code here:
        AgendaDTO dto = null;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement psta = conn.prepareStatement(AgendaDTO.SELECT_X_ID);
            psta.setLong(1, id);
            
            ResultSet rset = psta.executeQuery();
            
            while(rset.next()){
                
                dto = new AgendaDTO();
                dto.setId(rset.getLong("id"));
                dto.setEstado(rset.getInt("estado"));
                dto.setInstitucion(rset.getString("institucion"));
                dto.setCorreo(rset.getString("correo"));
                dto.setTelefono(rset.getString("telefono"));
            }
            
            rset.close();
            psta.close();
            
        } catch (Exception e) {
            logger.error("Error al encontrar la entrada de agenda con id: " + id, e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                logger.error("Error al cerrar la conexion: ", e);
            }
        }
        return dto;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     * Web service operation
     */
    @WebMethod(operationName = "crearAgenda")
    public AgendaDTO crearAgenda(@WebParam(name = "agenda") AgendaDTO agenda) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "borrarAgenda")
    public Long borrarAgenda(@WebParam(name = "agenda") AgendaDTO agenda) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "actualizarAgenda")
    public Long actualizarAgenda(@WebParam(name = "agenda") AgendaDTO agenda) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "encontrarPorEjemplo")
    public List<AgendaDTO> encontrarPorEjemplo(@WebParam(name = "agenda") AgendaDTO agenda) {
        //TODO write your implementation code here:
        return null;
    }
}
