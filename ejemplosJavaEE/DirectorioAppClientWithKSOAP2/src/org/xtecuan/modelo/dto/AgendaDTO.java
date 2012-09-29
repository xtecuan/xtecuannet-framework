/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.modelo.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xtecuan
 */
@XmlRootElement(name = "agenda")
public class AgendaDTO implements Serializable {

    public static final String SELECT_ALL = "select * from agenda ";
    public static final String SELECT_X_ID = SELECT_ALL + " where id=?";
    public static final String INSERT = "INSERT INTO agenda (institucion, telefono, correo, estado) \n"
            + "	VALUES (?, ?, ?, ?)";
    public static final String DELETE = "DELETE FROM agenda WHERE id = ?";
    public static final String UPDATE_BASE="UPDATE agenda SET ${0} WHERE id = ?";
    public static final String SELECT_BY_CLAVE=SELECT_ALL+ " where clave like ?";
    private Long id;
    private String institucion;
    private String telefono;
    private String correo;
    private Integer estado;
    private String clave;
    private Integer idcat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public AgendaDTO() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final AgendaDTO other = (AgendaDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AgendaDTO{" + "id=" + id + ", institucion=" + institucion + ", telefono=" + telefono + ", correo=" + correo + ", estado=" + estado + '}';
    }
    
    public static String generateUpdate(StringBuilder sb){
        
        return UPDATE_BASE.replace("${0}", sb.toString());
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getIdcat() {
        return idcat;
    }

    public void setIdcat(Integer idcat) {
        this.idcat = idcat;
    }
    
    
}
