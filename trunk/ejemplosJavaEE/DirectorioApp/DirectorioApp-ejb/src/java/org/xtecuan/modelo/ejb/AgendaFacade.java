/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.modelo.ejb;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;
import org.xtecuan.modelo.dto.AgendaDTO;
import org.xtecuan.modelo.dto.CategoriasDTO;
import org.xtecuan.modelo.enums.ResultEnum;

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

        AgendaDTO dto = null;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement psta = conn.prepareStatement(AgendaDTO.SELECT_X_ID);
            psta.setLong(1, id);

            ResultSet rset = psta.executeQuery();

            while (rset.next()) {

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
    private AgendaDTO crearAgendaProcess(AgendaDTO agenda) {

        Connection conn = null;
        try {
            conn = getConnection();
            logger.info("agenda entrada: " + agenda);
            PreparedStatement psta = conn.prepareStatement(AgendaDTO.INSERT, Statement.RETURN_GENERATED_KEYS);
            psta.setString(1, agenda.getInstitucion());
            psta.setString(2, agenda.getTelefono());
            psta.setString(3, agenda.getCorreo());
            psta.setInt(4, agenda.getEstado());

            int result = psta.executeUpdate();

            logger.info("Resultado: " + result);

            if (result == 1) {

                logger.info("Se inserto una entrada de agenda");

                ResultSet rset = psta.getGeneratedKeys();

                while (rset.next()) {

                    ResultSetMetaData meta = rset.getMetaData();
                    int columnCount = meta.getColumnCount();

                    for (int i = 1; i <= columnCount; i++) {
                        Long id = rset.getLong(1);
                        if (id != null) {
                            logger.info("El id de agenda es: " + id);
                            agenda.setId(id);
                        }
                    }
                }

                rset.close();
            } else {
                logger.info("Problema al insertar un registro en la agenda!!!");
            }
            psta.close();

        } catch (Exception e) {
            logger.error("Error al encontrar la entrada de agenda con id: " + agenda.getId(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                logger.error("Error al cerrar la conexion: ", e);
            }
        }
        return agenda;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "borrarAgenda")
    public Long borrarAgenda(@WebParam(name = "idAgenda") Long idAgenda) {

        Connection conn = null;
        Long result = ResultEnum.Fallo.getCod();

        try {

            conn = getConnection();
            PreparedStatement psta = conn.prepareStatement(AgendaDTO.DELETE);
            psta.setLong(1, idAgenda);

            int resultDel = psta.executeUpdate();

            if (resultDel == 1) {

                result = ResultEnum.Exito.getCod();
                logger.info("Se borro un registro con id: " + idAgenda);

            } else {
                logger.info("Error al intentar borrar un registro con id: " + idAgenda);
            }

            psta.close();


        } catch (Exception e) {

            logger.error("Error al intentar borrar la entrada de agenda con id: " + idAgenda, e);
        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                logger.error("Error al intentar cerrar la conexion!!!", e);
            }
        }

        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "actualizarAgenda")
    public Long actualizarAgenda(@WebParam(name = "idUp") Long idUp, @WebParam(name = "institucionUp") String institucionUp,
            @WebParam(name = "telefonoUp") String telefonoUp,
            @WebParam(name = "correoUp") String correoUp, @WebParam(name = "estadoUp") Integer estadoUp) {

        Long result = ResultEnum.Fallo.getCod();
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement psta = null;
            StringBuilder sb = new StringBuilder();
            int counterWhere = 0;
            List<Object> params = new ArrayList<Object>(0);
            if ((institucionUp != null && institucionUp.length() > 0
                    || telefonoUp != null && telefonoUp.length() > 0
                    || correoUp != null && correoUp.length() > 0
                    || estadoUp != null) && idUp != null) {

                if (institucionUp != null && institucionUp.length() > 0) {

                    sb.append(" institucion = ? ");
                    counterWhere++;

                    params.add(institucionUp);
                }

                if (telefonoUp != null && telefonoUp.length() > 0) {

                    if (counterWhere > 0) {

                        sb.append(" , ");
                    }

                    sb.append(" telefono = ? ");

                    counterWhere++;

                    params.add(telefonoUp);
                }

                if (correoUp != null && correoUp.length() > 0) {
                    if (counterWhere > 0) {

                        sb.append(" , ");
                    }

                    sb.append(" correo = ? ");

                    counterWhere++;

                    params.add(correoUp);

                }

                if (estadoUp != null) {

                    if (counterWhere > 0) {
                        sb.append(" , ");
                    }

                    sb.append(" estado = ? ");

                    params.add(estadoUp);
                }

                if (counterWhere > 0) {
                    params.add(idUp);

                    String update = AgendaDTO.generateUpdate(sb);

                    logger.info("Query/Update a ejecutar: " + update);

                    psta = conn.prepareStatement(update);

                    if (!params.isEmpty()) {

                        int w = 1;
                        for (Object object : params) {

                            if (object instanceof String) {

                                psta.setString(w, (String) object);
                            }
                            if (object instanceof Integer) {
                                psta.setInt(w, (Integer) object);
                            }

                            if (object instanceof Long) {
                                psta.setLong(w, (Long) object);
                            }
                            w++;
                        }

                    }

                    int r = psta.executeUpdate();

                    if (r == 1) {
                        logger.info("Se actualizo una entrada de agenda!!!, con id: " + idUp);
                        result = ResultEnum.Exito.getCod();
                    }

                    psta.close();
                }

            } else {
                logger.error("Nada por actualizar para esta llamada");
            }


        } catch (Exception e) {

            logger.error("Error al actualizar la entrada de agenda: ", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                logger.error("Error al cerrar la conexion: ", e);
            }
        }

        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "encontrarPorEjemplo")
    public List<AgendaDTO> encontrarPorEjemplo(@WebParam(name = "institucionLike") String institucionLike,
            @WebParam(name = "telefonoLike") String telefonoLike,
            @WebParam(name = "correoLike") String correoLike, @WebParam(name = "estadoLike") Integer estadoLike) {

        List<AgendaDTO> listado = new ArrayList<AgendaDTO>(0);

        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement psta = null;

            StringBuilder sb = new StringBuilder(AgendaDTO.SELECT_ALL);
            int counterWhere = 0;
            List<Object> params = new ArrayList<Object>(0);
            if (institucionLike != null && institucionLike.length() > 0
                    || telefonoLike != null && telefonoLike.length() > 0
                    || correoLike != null && correoLike.length() > 0
                    || estadoLike != null) {

                sb.append(" WHERE ");



                if (institucionLike != null && institucionLike.length() > 0) {

                    sb.append(" institucion like ? ");
                    counterWhere++;

                    params.add(institucionLike + "%");
                }

                if (telefonoLike != null && telefonoLike.length() > 0) {

                    if (counterWhere > 0) {

                        sb.append(" AND ");
                    }

                    sb.append(" telefono like ? ");

                    counterWhere++;

                    params.add(telefonoLike + "%");
                }

                if (correoLike != null && correoLike.length() > 0) {
                    if (counterWhere > 0) {

                        sb.append(" AND ");
                    }

                    sb.append(" correo like ? ");

                    counterWhere++;

                    params.add(correoLike + "%");

                }

                if (estadoLike != null) {

                    if (counterWhere > 0) {
                        sb.append(" AND ");
                    }

                    sb.append(" estado = ? ");

                    params.add(estadoLike);
                }



            }


            logger.info("Query a ejecutar: " + sb.toString());

            psta = conn.prepareStatement(sb.toString());

            if (!params.isEmpty()) {

                int w = 1;
                for (Object object : params) {

                    if (object instanceof String) {

                        psta.setString(w, (String) object);
                    }
                    if (object instanceof Integer) {
                        psta.setInt(w, (Integer) object);
                    }
                    w++;
                }

            }


            ResultSet rset = psta.executeQuery();

            while (rset.next()) {

                AgendaDTO dto = new AgendaDTO();
                dto.setId(rset.getLong("id"));
                dto.setEstado(rset.getInt("estado"));
                dto.setInstitucion(rset.getString("institucion"));
                dto.setCorreo(rset.getString("correo"));
                dto.setTelefono(rset.getString("telefono"));

                listado.add(dto);
            }

            rset.close();
            psta.close();

        } catch (Exception e) {
            logger.error("Error al encontrar las entrada de agenda por ejemplo: ", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                logger.error("Error al cerrar la conexion: ", e);
            }
        }

        return listado;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "crearAgenda")
    public AgendaDTO crearAgenda(@WebParam(name = "institucion") String institucion, @WebParam(name = "telefono") String telefono, @WebParam(name = "correo") String correo, @WebParam(name = "estado") Integer estado) {


        AgendaDTO in = new AgendaDTO();
        AgendaDTO dto = null;

        try {

            if (institucion != null && telefono != null && correo != null) {

                in.setInstitucion(institucion);
                in.setCorreo(correo);
                in.setTelefono(telefono);

                if (estado == null) {
                    in.setEstado(Integer.valueOf("0"));
                } else {
                    in.setEstado(estado);
                }

                dto = crearAgendaProcess(in);
            }

        } catch (Exception e) {

            logger.error("Error al crear la entrada de agenda con el siguiente detalle: " + in, e);
        }


        return dto;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "recuperarCategorias")
    public List<org.xtecuan.modelo.dto.CategoriasDTO> recuperarCategorias() {
        //TODO write your implementation code here:

        List<CategoriasDTO> dtos = new ArrayList<CategoriasDTO>(0);

        Connection conn = null;

        try {
            conn = getConnection();

            Statement sta = conn.createStatement();

            ResultSet rset = sta.executeQuery(CategoriasDTO.SELECT_ALL);

            while (rset.next()) {

                CategoriasDTO dto = new CategoriasDTO();

                dto.setIdcat(rset.getLong("idcat"));
                dto.setDescat(rset.getString("descat"));

                java.sql.Date date = rset.getDate("fhingreso");

                dto.setFhingreso(new java.util.Date(date.getTime()));

                dtos.add(dto);


            }

            rset.close();
            sta.close();


        } catch (Exception e) {
            logger.error("Error al llenar el listado de categorias: ", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {

                logger.error("Error al cerrar la conexion: ", e);
            }

        }

        return dtos;
    }
}
