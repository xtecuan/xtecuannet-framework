/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.securityservices;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xtecuan
 */
public interface PersistenceServiceNoAud extends Serializable {

    public javax.persistence.EntityManager getEm();

    public javax.sql.DataSource getDataSource();

    public java.lang.Object guardarEntidad(java.lang.Object entidad);

    public java.lang.Object actualizarEntidad(java.lang.Object entidad);

    public <T extends java.lang.Object> java.lang.Object hacerBorradoEntidad(java.lang.Class<T> tipoEntity, java.lang.Object pkEntity);

    public List hacerNamedQueryVariosRegistros(String query);

    public List hacerNamedQueryVariosRegistros(String query, Map paramMap);

    public void executeProcedure(String sql, Object[] params);

    public Object hacerNamedQueryUnRegistro(String query, Map paramMap);

    public <T> List hacerQueryNativo(String query, Map<String, Object> params, Class<T> entity);

    public <T> List hacerQueryNativo(String query, Class<T> entity);

    public <T> List hacerQueryNativo(String query, Map<String, Object> params);

    public <T> List doNativeQueryWithPojo(String query, Map<String, Object> params, Class<T> javabean);

    public Object executeFunction(String sql, Object[] params);

    public <T> List executeProcedureWithResults(String sql, Object[] params, Class<T> pojo);

    public <T> List hacerNamedQueryVariosRegistrosDTO(String query, Map paramMap, Class<T> dto);

    public <T> Object hacerQueryNativoSingleResult(String query, Map<String, Object> params, Class<T> clase);

    public int executeSQLUpdateQuery(String query, Map<String, Object> params);
}
