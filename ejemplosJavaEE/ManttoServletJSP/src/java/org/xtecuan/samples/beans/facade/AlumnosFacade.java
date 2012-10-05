/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.samples.beans.facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.xtecuan.samples.beans.facade.exceptions.ManttoAlumnosException;
import org.xtecuan.samples.dto.Alumnos;

/**
 *
 * @author xtecuan
 */
public final class AlumnosFacade {

    private static final Logger logger = Logger.getLogger(AlumnosFacade.class);
    private DataSource ds;
    private Alumnos alumno;

    public AlumnosFacade() {
    }

    public AlumnosFacade(DataSource ds, Alumnos alumno) {
        this.ds = ds;
        this.alumno = alumno;
    }

    public AlumnosFacade(DataSource ds) {
        this.ds = ds;
    }

    private Connection obtenerConexion() {

        Connection conn = null;


        try {
            conn = this.ds.getConnection();
            logger.info("Se obtuvo una conexion!!!!");
        } catch (Exception e) {

            logger.error("Error al obtener la conexion hacia la base de datos ", e);
        }

        return conn;

    }

    public Alumnos guardarAlumno() throws Exception {
        Connection conn = null;

        PreparedStatement psta = null;

        ResultSet rset = null;

//        try {


        if (this.getAlumno() != null) {

            conn = this.obtenerConexion();

            boolean carnetExp = getAlumno().getCarnet() != null && getAlumno().getCarnet().length() > 0;
            boolean apellidosExp = getAlumno().getApellidos() != null && getAlumno().getApellidos().length() > 0;
            boolean nombresExp = getAlumno().getNombres() != null && getAlumno().getNombres().length() > 0;
            List<Object> params = new ArrayList<Object>(0);
            StringBuilder sbNames = new StringBuilder();
            StringBuilder sbMarks = new StringBuilder();
            if (carnetExp && nombresExp && apellidosExp) {

                params.add(this.getAlumno().getCarnet());
                params.add(this.getAlumno().getNombres());
                params.add(this.getAlumno().getApellidos());

                if (getAlumno().getCorreo() != null && getAlumno().getCorreo().length() > 0) {

                    sbNames.append("correo");
                    sbMarks.append("?");
                    params.add(getAlumno().getCorreo());
                }
                if (getAlumno().getFechanac() != null) {
                    if (params.size() > 3) {
                        sbNames.append(",");
                        sbMarks.append(",");
                    }
                    sbNames.append("fechanac");
                    sbMarks.append("?");
                    params.add(getAlumno().getFechanac());
                }


                String insert = Alumnos.generateInsert(sbNames, sbMarks);

                logger.info("Query/INSERT a ejecutar: " + insert);

                psta = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

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

                    if (object instanceof Date) {


                        Date date = (Date) object;

                        psta.setDate(w, new java.sql.Date(date.getTime()));

                    }

                    w++;

                }

                int result = psta.executeUpdate();
                if (result == 1) {

                    logger.info("Se inserto una entrada de alumnos d");

                    rset = psta.getGeneratedKeys();

                    while (rset.next()) {

                        ResultSetMetaData meta = rset.getMetaData();
                        int columnCount = meta.getColumnCount();

                        for (int i = 1; i <= columnCount; i++) {
                            Integer id = rset.getInt(1);
                            if (id != null) {
                                logger.info("El id del alumno es: " + id);
                                alumno.setId(id);
                            }
                        }
                    }

                    rset.close();
                } else {
                    logger.info("Problema al insertar un registro en alumnos !!!");
                    throw  new ManttoAlumnosException("Error de persistencia del sistema!!!");
                }

                psta.close();
            } else {
                logger.error("El Carnet, los nombres y los apellidos son requeridos!!!");
                throw new ManttoAlumnosException("El Carnet, los nombres y los apellidos son requeridos!!!");
            }


        } else {
            logger.error("Error al intentar guardar un objeto Alumnos no se fijo el alumno a guardar ");
            throw new ManttoAlumnosException("Error al intentar guardar un objeto Alumnos no se fijo el alumno a guardar ");
        }




//        } catch (Exception e) {

//            logger.error("Error al intentar guardar un objeto de tipo Alumnos", e);
//        } finally {

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            logger.error("Error al intentar cerrar la conexion INSERT", e);
        }
//        }


        return alumno;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }
}
