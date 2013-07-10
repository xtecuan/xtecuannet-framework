/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.model.securityservices.impl;

import com.googlecode.xtecuannet.framework.model.constants.FrameworkConstants;
import com.googlecode.xtecuannet.framework.model.securityservices.PersistenceServiceNoAud;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.eclipse.persistence.example.jpa.nativesql.constructor.JavaBeanResult;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xtecuan
 */
@Repository
@Transactional
//@PersistenceContext(name = ConstJSicom.ENTITY_MANAGER_JSICOM_REF, unitName = ConstJSicom.DEFAULT_PU)
public class PersistenceServiceNoAudImpl implements PersistenceServiceNoAud {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(PersistenceServiceNoAudImpl.class);
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    //= this.lookupPersistenceBeanRemote().getEm();
    private DataSource dataSource;

    public PersistenceServiceNoAudImpl() {
//        this.iniEm();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    //@PersistenceContext(type = PersistenceContextType.EXTENDED)
//    @PersistenceContext(name = ConstJSicom.ENTITY_MANAGER_JSICOM_REF,unitName = ConstJSicom.DEFAULT_PU)
    //unitName = ConstJSicom.DEFAULT_PU, name = ConstJSicom.ENTITY_MANAGER_JSICOM_REF)
//    private void iniEm() {
//
//        this.setEntityManager((EntityManager) JSicomnbLocator.obtenerReferenciaEjb("java:comp/env/"+ConstJSicom.ENTITY_MANAGER_JSICOM_REF));
//    }
    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 
     * @param entidad
     * @return 
     */
    public Object guardarEntidad(Object entidad) {
        try {

            logger.debug("Iniciando el método: guardarEntidad(Object=" + entidad + ")");

            em.persist(entidad);

            logger.debug("Terminando el método: guardarEntidad(Object=" + entidad + ")");

        } catch (Exception e) {

            logger.error("Se produjo una excepción al ejecutar: guardarEntidad(Object=" + entidad + ") excepcion: " + e);
        }
        return entidad;
    }

    /**
     * 
     * @param entidad
     * @return 
     */
    public Object actualizarEntidad(Object entidad) {
        try {

            logger.debug("Iniciando del método: actualizarEntidad(Object=" + entidad + ")");

            em.merge(entidad);

            logger.debug("Finalizando del método: actualizarEntidad(Object=" + entidad + ")");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Se produjo una excepción al ejecutar: actualizarEntidad(Object=" + entidad + ") excepcion: " + e);
        }
        return entidad;
    }

    /**
     * 
     * @param <T>
     * @param tipoEntity
     * @param pkEntity
     * @return 
     */
    public <T> Object hacerBorradoEntidad(Class<T> tipoEntity,
            Object pkEntity) {

        try {

            logger.debug("Iniciando operación "
                    + "hacerBorradoEntidad(Class<T> tipoEntity, = "
                    + tipoEntity + ")");

            getEm().remove(getEm().find(tipoEntity,
                    pkEntity));

            logger.debug("Terminando operación "
                    + "hacerBorradoEntidad(Class<T> tipoEntity, = "
                    + tipoEntity + ")");


        } catch (Exception e) {

            logger.error("Exception en la operación "
                    + "hacerBorradoEntidad(Class<T> tipoEntity, = "
                    + tipoEntity + ")" + e);
        }

        return "";
    }

    /**
     * 
     * @param query
     * @return 
     */
    public List hacerNamedQueryVariosRegistros(String query) {

        List<?> respuesta = null;
        try {

            logger.debug("Iniciando operación "
                    + "hacerNamedQueryVariosRegistros(String query = )"
                    + query);

            Query querySQL = getEm().createNamedQuery(query);
//            verificarTamanio(ConstJSicom.DEFAULT_PAG,
//                    querySQL);


            respuesta = querySQL.getResultList();

            logger.debug("Terminando operación "
                    + "hacerNamedQueryVariosRegistros(String query = )"
                    + query);

        } catch (Exception e) {

            logger.error("Exception en la operación "
                    + "hacerNamedQueryVariosRegistros(String query = )"
                    + query + e);


        }
        return respuesta;
    }

    /**
     * 
     * @param query
     * @param paramMap
     * @return 
     */
    public List hacerNamedQueryVariosRegistros(String query,
            Map paramMap) {
        List<?> respuesta = null;

        try {

            logger.debug("Iniciando operación "
                    + "hacerNamedQueryVariosRegistros(String query, = \n"
                    + query + " " + "Map paramMap, = \n" + paramMap + ")");

            Query querySQL = getEm().createNamedQuery(query);

            if (!paramMap.isEmpty()) {
                for (Iterator<String> iterator = paramMap.keySet().iterator();
                        iterator.hasNext();) {
                    String name = iterator.next();

                    querySQL.setParameter(name, paramMap.get(name));


                }
            }


//            verificarTamanio(ConstJSicom.DEFAULT_PAG,
//                    querySQL);

            respuesta = querySQL.getResultList();


            logger.debug("Terminando operación "
                    + "hacerNamedQueryVariosRegistros(String query, = \n"
                    + query + " " + "Map paramMap, = \n" + paramMap + ")");

        } catch (Exception e) {

            logger.error("Exception en la operación "
                    + "hacerNamedQueryVariosRegistros(String query, = \n"
                    + query + " " + "Map paramMap, = \n" + paramMap + ")"
                    + e);
        }
        return respuesta;
    }

    /**
     * 
     * @param tamanio
     * @param query 
     */
    private void verificarTamanio(Integer tamanio,
            Query query) {
        if (tamanio <= FrameworkConstants.DEFAULT_PAG) {
            query.setMaxResults(tamanio);

        } else {
            query.setMaxResults(FrameworkConstants.DEFAULT_PAG);

        }
    }

    /**
     * 
     * @param sql
     * @param params 
     */
    public void executeProcedure(String sql,
            Object[] params) {
        Connection conn;
        CallableStatement cstmt;
        try {
            conn = dataSource.getConnection();
            cstmt = conn.prepareCall(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    Object param = params[i];
                    cstmt.setObject(i + 1, param, resolveType(param));
                }
            }
            cstmt.execute();
            cstmt.close();
            conn.close();
        } catch (Exception e) {
            logger.error("Error al ejecutar el procedimiento almacenado: ", e);
        }
    }

    /**
     * 
     * @param param
     * @return 
     */
    private int resolveType(Object param) {
        int sqlType = Types.VARCHAR;
        if (param == null) {
            sqlType = Types.NULL;
        } else {
            Class paramClass = param.getClass();
            if (param instanceof String) {
                sqlType = Types.VARCHAR;
            } else if (paramClass.equals(double.class)
                    || param instanceof Double) {
                sqlType = Types.DOUBLE;
            } else if (param instanceof BigDecimal) {
                sqlType = Types.NUMERIC;
            } else if (param instanceof Calendar
                    || param instanceof java.sql.Date) {
                sqlType = Types.DATE;
            } else if (paramClass.equals(int.class)
                    || param instanceof Integer) {
                sqlType = Types.INTEGER;
            } else if (paramClass.equals(long.class)
                    || param instanceof Long) {
                sqlType = Types.BIGINT;
            } else if (paramClass.equals(float.class)
                    || param instanceof Float) {
                sqlType = Types.REAL;

            }
        }
        return sqlType;
    }

    /**
     * 
     * @param query
     * @param paramMap
     * @return 
     */
    public Object hacerNamedQueryUnRegistro(String query, Map paramMap) {
        Object respuesta = null;

        try {

            logger.debug("Iniciando operación "
                    + "hacerNamedQueryUnRegistro(String query, = \n"
                    + query + " " + "Map paramMap, = \n" + paramMap + ")");

            Query querySQL = getEm().createNamedQuery(query);

            if (!paramMap.isEmpty()) {
                for (Iterator<String> iterator = paramMap.keySet().iterator();
                        iterator.hasNext();) {
                    String name = iterator.next();

                    querySQL.setParameter(name, paramMap.get(name));


                }
            }

            respuesta = querySQL.getSingleResult();


            logger.debug("Terminando operación "
                    + "hacerNamedQueryUnRegistro(String query, = \n"
                    + query + " " + "Map paramMap, = \n" + paramMap + ")");

        } catch (Exception e) {

            logger.error("Exception en la operación "
                    + "hacerNamedQueryUnRegistro(String query, = \n"
                    + query + " " + "Map paramMap, = \n" + paramMap + ")"
                    + e);
        }
        return respuesta;
    }

    /**
     * 
     * @param <T>
     * @param query
     * @param params
     * @param entity
     * @return 
     */
    public <T> List hacerQueryNativo(String query, Map<String, Object> params, Class<T> entity) {
        List<?> respuesta = null;

        try {

            logger.info("hacerQueryNativo: query:" + query + " params:" + params + " class:" + entity);

            Query querySQL =
                    getEm().createNativeQuery(query, entity);

            if (!params.isEmpty()) {

                for (String key : params.keySet()) {

                    querySQL.setParameter(key, params.get(key));
                }


            }


            verificarTamanio(FrameworkConstants.DEFAULT_PAG,
                    querySQL);

            respuesta = querySQL.getResultList();


        } catch (Exception e) {

            logger.error("Error al ejecutar query nativo: " + query + " Los parametros: " + params, e);

        }
        return respuesta;
    }

    /**
     * 
     * @param <T>
     * @param query
     * @param entity
     * @return 
     */
    @Override
    public <T> List hacerQueryNativo(String query, Class<T> entity) {
        Map map = new HashMap();

        return this.hacerQueryNativo(query, map, entity);
    }

    /**
     * 
     * @param <T>
     * @param query
     * @param params
     * @return 
     */
    @Override
    public <T> List hacerQueryNativo(String query, Map<String, Object> params) {

        List<?> respuesta = null;

        try {

            logger.info("hacerQueryNativo: query:" + query + " params:" + params);

            Query querySQL = this.getEm().createNativeQuery(query);

            if (!params.isEmpty()) {

                for (String key : params.keySet()) {

                    querySQL.setParameter(key, params.get(key));
                }


            }

            respuesta = querySQL.getResultList();

        } catch (Exception ex) {
        }

        return respuesta;
    }

    /**
     * 
     * @param <T>
     * @param query
     * @param params
     * @param javabean
     * @return 
     */
    public <T> List doNativeQueryWithPojo(String query, Map<String, Object> params, Class<T> javabean) {

        List<?> respuesta = null;

        try {

            logger.info("doNativeQueryWithPojo: query:" + query + " params:" + params + "clazz: " + javabean);

            Query querySQL = this.getEm().createNativeQuery(query);

            JavaBeanResult.setQueryResultClass(querySQL, javabean);

            if (!params.isEmpty()) {

                for (String key : params.keySet()) {

                    querySQL.setParameter(key, params.get(key));
                }


            }

            respuesta = querySQL.getResultList();

        } catch (Exception ex) {

            this.logger.error("Exception", ex);
        }

        return respuesta;
    }

    /**
     * 
     * @param sql
     * @param params
     * @return 
     */
    public Object executeFunction(String sql, Object[] params) {
        Object outPut = null;
        try {
            Connection conn;
            CallableStatement cstmt;

            conn = dataSource.getConnection();


            cstmt = conn.prepareCall(sql);

            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    Object param = params[i];
                    if (i == 0) {
                        cstmt.registerOutParameter(1, resolveType(param));
                    } else {
                        logger.debug("parametro -->" + param + " tipo-->"
                                + resolveType(param));
                        cstmt.setObject(i + 1, param, resolveType(param));
                    }
                }
            }
            logger.debug("begin executing ...>" + sql + "<");
            cstmt.execute();
            logger.debug("end executing ...>" + sql + "<");
            outPut = cstmt.getObject(1);
            cstmt.close();
            conn.close();

        } catch (Exception ex) {
            logger.error("Error al ejecutar la función: ",
                    ex);
        }
        return outPut;

    }

    /**
     * 
     * @param <T>
     * @param sql
     * @param params
     * @param pojo
     * @return 
     */
    @Override
    public <T> List executeProcedureWithResults(String sql, Object[] params, Class<T> pojo) {
        List<Object> respuesta = new ArrayList<Object>(0);

        Connection conn = null;
        CallableStatement cstmt = null;

        try {


            conn = dataSource.getConnection();
            conn.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);

            cstmt = conn.prepareCall(sql);


            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    Object param = params[i];
                    cstmt.setObject(i + 1, param, resolveType(param));
                }
            }
            cstmt.execute();


            ResultSet rset = cstmt.getResultSet();



            while (rset.next()) {


                Object current = pojo.newInstance();

                Field[] fields = pojo.getDeclaredFields();

                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];

                    if (rset.getObject(field.getName()) != null) {

                        BeanUtils.copyProperty(current, field.getName(), rset.getObject(field.getName()));
                    }

                }

                respuesta.add(current);

            }

            //rset.close();
            cstmt.close();
            cstmt = null;
            conn.close();
            conn = null;


        } catch (Exception ex) {
            logger.error("Error al ejecutar el procedimiento almacenado con resultado: ", ex);
        } finally {
            if (cstmt != null) {

                try {
                    cstmt.close();
                } catch (SQLException e) {
                    logger.error("Error al cerrar el callable statement ", e);
                }

                cstmt = null;
            }

            if (conn != null) {

                try {
                    conn.close();
                } catch (SQLException e) {

                    logger.error("Error al cerrar la conexión ", e);
                }
                conn = null;
            }
        }

        return respuesta;
    }

    /**
     * 
     * @param <T>
     * @param query
     * @param paramMap
     * @param dto
     * @return 
     */
    @Override
    public <T> List hacerNamedQueryVariosRegistrosDTO(String query, Map paramMap, Class<T> dto) {
        List<?> respuesta = null;

        try {

            logger.debug("Iniciando operación "
                    + "hacerNamedQueryVariosRegistros(String query, = \n"
                    + query + " " + "Map paramMap, = \n" + paramMap + ")");

            Query querySQL = getEm().createNamedQuery(query);

            JavaBeanResult.setQueryResultClass(querySQL, dto);

            if (!paramMap.isEmpty()) {
                for (Iterator<String> iterator = paramMap.keySet().iterator();
                        iterator.hasNext();) {
                    String name = iterator.next();

                    querySQL.setParameter(name, paramMap.get(name));


                }
            }



            respuesta = querySQL.getResultList();


            logger.debug("Terminando operación "
                    + "hacerNamedQueryVariosRegistrosDTO(String query, = \n"
                    + query + " " + "Map paramMap, = \n" + paramMap + ")");

        } catch (Exception e) {

            logger.error("Exception en la operación "
                    + "hacerNamedQueryVariosRegistrosDTO(String query, = \n"
                    + query + " " + "Map paramMap, = \n" + paramMap + ")"
                    + e);
        }
        return respuesta;
    }

    /**
     * 
     * @param <T>
     * @param query
     * @param params
     * @param clase
     * @return 
     */
    public <T> Object hacerQueryNativoSingleResult(String query, Map<String, Object> params, Class<T> clase) {
        Object salida = null;

        List<T> lista = (List<T>) this.hacerQueryNativo(query, params);

        salida = lista.get(0);

        return salida;
    }

    /**
     * 
     * @param query
     * @param params
     * @return 
     */
    public int executeSQLUpdateQuery(String query, Map<String, Object> params) {

        int result = -1;

        try {

            Query q = this.getEm().createNativeQuery(query);

            if (!params.isEmpty()) {

                for (String key : params.keySet()) {
                    q.setParameter(key, params.get(key));
                }

            }

            result = q.executeUpdate();


        } catch (Exception e) {

            logger.error("Error al ejecutar el update con query: " + query + " params: " + params, e);
        }

        return result;
    }
}
