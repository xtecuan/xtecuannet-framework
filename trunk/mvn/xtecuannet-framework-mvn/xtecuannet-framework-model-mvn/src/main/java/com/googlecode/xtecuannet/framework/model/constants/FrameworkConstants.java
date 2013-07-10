/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.model.constants;

/**
 *
 * @author xtecuan
 */
public final class FrameworkConstants {

    /**
     * Nombre del Persistence Unit
     */
    public static final String DEFAULT_PU = "xtecuannet-framework-pu";
    /**
     * Nombres para JNDI para los EJB
     */
    public static final String EJB_SLASH = "ejb/";
    public static final String PERSISTENCE_SLASH = "persistence/";
    public static final String ENTITY_MANAGER_JSICOM_REF = PERSISTENCE_SLASH + DEFAULT_PU;
    public static final String DEFAULT_PERS_BEAN_NAME = "PersistenceBean";
    public static final String DEFAULT_PERS_BEAN_MAPPED_NAME = EJB_SLASH + DEFAULT_PERS_BEAN_NAME;
    public static final String DEFAULT_INVENTARIO_BEAN_NAME = "InventarioBean";
    public static final String DEFAULT_INVENTARIO_BEAN_MAPPED_NAME = EJB_SLASH + DEFAULT_INVENTARIO_BEAN_NAME;
    /**
     * Nombre JNDI del DataSource Transaccional
     */
    public static final String DEFAULT_DS = "jdbc/xtecuannet_framework_ds";
    /**
     * Tamaño por default de la paginación de los Queries
     */
    public static final int DEFAULT_PAG = 500;
    /**
     * Nombres de los NamedQueries a utilizar en los EJB
     */
    public static final String QUERY_FIND_ALL_ScMaestroInv = "ScMaestroInv.findAll";
    public static final String QUERY_FIND_ALL_ScProveedor = "ScProveedor.findAll";
    public static final String QUERY_FIND_ALL_ScUnidades = "ScUnidades.findAll";
    public static final String QUERY_FIND_ALL_ScLinea = "ScLinea.findAll";
    public static final String QUERY_FIND_BY_CArticulo_ScMaestroInv = "ScMaestroInv.findByCArticulo";
    public static final String QUERY_FIND_BY_CProveedor_ScProveedor = "ScProveedor.findByCProveedor";
    public static final String QUERY_FIND_BY_CUnidad_ScUnidades = "ScUnidades.findByCUnidad";
    public static final String QUERY_FIND_BY_CLinea_ScLinea = "ScLinea.findByCLinea";
    public static final String QUERY_FIND_BY_LoginAndPass = "SecUsers.findByLoginAndPass";
    public static final String QUERY_FIND_BY_CTercero = "ScTercero.findByCTercero";
    public static final String QUERY_FIND_ALL_ScTercero = "ScTercero.findAll";
    public static final String QUERY_FIND_BY_UserId = "SecUsers.findByUserId";
    public static final String QUERY_FIND_BY_UserLevelId = "SecUserLevel.findByUserLevelId";
    public static final String QUERY_FIND_BY_MenuLevelId = "SecMenuLevel.findByMenuLevelId";
    public static final String QUERY_FIND_BY_MenuId = "SecMenu.findByMenuId";
    public static final String QUERY_FIND_BY_LevelId = "SecLevels.findByLevelId";
    public static final String QUERY_FIND_ALL_SecUsers = "SecUsers.findAll";
    public static final String QUERY_FIND_ALL_SecUserLevel = "SecUserLevel.findAll";
    public static final String QUERY_FIND_ALL_SecMenuLevel = "SecMenuLevel.findAll";
    public static final String QUERY_FIND_ALL_SecMenu = "SecMenu.findAll";
    public static final String QUERY_FIND_ALL_SecLevels = "SecLevels.findAll";
    //public static final String QUERY_AUTORIZAR_OPCIONES_MENU="SELECT m.menu_id menuId, m.menu_name menuName, m.menu_url menuUrl FROM sec_menu m, sec_menu_level l, sec_users u WHERE m.menu_id = l.menu_id and l.level_id = u.level_id and u.login = ?1";
    public static final String QUERY_AUTORIZAR_OPCIONES_MENU = "SELECT m.* FROM sec_menu m, sec_menu_level l, sec_users u WHERE m.menu_id = l.menu_id and l.level_id = u.level_id and u.login = ?1 and m.menu_status=?2 and m.menu_level=?3";
    public static final String QUERY_AUTENTICAR_MARLEN = "Usuario.findByUsuarioNombreAndUsuarioContrasenna";
    public static final String QUERY_SEC_SESSION_AUDIT_BY_SESSION_ID = "SecSessionAudit.findByEventSessionId";
    public static final String QUERY_SUCURSALES_TERMINAL_BY_TERM_NOMBRE = "SucursalesTerminal.findByTermNombre";
    public static final String QUERY_SUCURSALES_BY_SUCURSAL_CODIGO = "Sucursales.findBySucursalCodigo";
    public static final String SUCURSAL_CENTRAL = "01";
    public static final String QUERY_DOCUMENTOS_PARA_FACTURAS = "Documentos.findByDocumentoCodigoFacCreFis";
    public static final String QUERY_TERRITORIOS_X_SUCURSAL_CODIGO = "Territorios.findBySucursalCodigo";
    
}
