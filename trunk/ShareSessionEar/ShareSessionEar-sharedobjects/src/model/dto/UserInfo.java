/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.io.Serializable;

/**
 *
 * @author xtecuan
 */
public class UserInfo implements Serializable {

    public static final String DEFAULT_USER = "admin";
    public static final String DEFAULT_PASS = "welcome1";
    public static final String DEFAULT_NOMBRES = "Julian";
    public static final String DEFAULT_APELLIDOS = "Rivera Pineda";
    public static final String KEY_BRO_APP = "BRO_APP";
    public static final String KEY_BRO_APP_SERVLET = "BRO_APP_SERVLET";
    private String usuario;
    private String clave;
    private String nombres;
    private String apellidos;
    

    public UserInfo() {
        this.usuario = DEFAULT_USER;
        this.clave = DEFAULT_PASS;
        this.nombres = DEFAULT_NOMBRES;
        this.apellidos = DEFAULT_APELLIDOS;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserInfo other = (UserInfo) obj;
        if ((this.usuario == null) ? (other.usuario != null) : !this.usuario.equals(other.usuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "usuario=" + usuario + ", clave=" + clave + '}';
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
   
}
