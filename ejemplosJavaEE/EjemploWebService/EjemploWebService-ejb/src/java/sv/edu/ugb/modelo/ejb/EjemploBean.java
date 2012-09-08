/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ugb.modelo.ejb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.sql.DataSource;
import sv.edu.ugb.modelo.dto.AgendaDTO;

/**
 *
 * @author xtecuan
 */
@WebService
@Stateless
@LocalBean
public class EjemploBean {

    @Resource(name = "miconexion")
    private DataSource miconexion;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Connection obtenerConexion() {

        Connection conn = null;

        try {

            conn = miconexion.getConnection();

        } catch (Exception e) {
        }

        return conn;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "encontrarlosTodos")
    public java.util.List<sv.edu.ugb.modelo.dto.AgendaDTO> encontrarlosTodos() {
        //TODO write your implementation code here:

        List<AgendaDTO> result = new ArrayList<AgendaDTO>(0);
        Connection conn = null;
        try {

            conn = obtenerConexion();

            Statement sta = conn.createStatement();

            ResultSet rset = sta.executeQuery(AgendaDTO.SELECT_ALL);

            while (rset.next()) {

                AgendaDTO dto = new AgendaDTO();

                dto.setId(rset.getInt("id"));
                dto.setInstitucion(rset.getString("institucion"));
                dto.setTelefono(rset.getString("telefono"));
                dto.setCorreo(rset.getString("correo"));
                dto.setEstado(rset.getInt("estado"));

                result.add(dto);

            }

            rset.close();
            sta.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
