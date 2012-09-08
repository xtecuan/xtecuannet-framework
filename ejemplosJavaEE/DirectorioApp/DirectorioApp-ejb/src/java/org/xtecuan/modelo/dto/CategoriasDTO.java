/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.modelo.dto;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xtecuan
 */
@XmlRootElement(name="categorias")
public class CategoriasDTO implements Serializable{
    
    public static final String SELECT_ALL="select * from categorias";
    private Long idcat;
    private String descat;
    private Date fhingreso;

    public CategoriasDTO() {
    }

    public Long getIdcat() {
        return idcat;
    }

    public void setIdcat(Long idcat) {
        this.idcat = idcat;
    }

    public String getDescat() {
        return descat;
    }

    public void setDescat(String descat) {
        this.descat = descat;
    }

    public Date getFhingreso() {
        return fhingreso;
    }

    public void setFhingreso(Date fhingreso) {
        this.fhingreso = fhingreso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.idcat != null ? this.idcat.hashCode() : 0);
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
        final CategoriasDTO other = (CategoriasDTO) obj;
        if (this.idcat != other.idcat && (this.idcat == null || !this.idcat.equals(other.idcat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategoriasDTO{" + "idcat=" + idcat + ", descat=" + descat + ", fhingreso=" + fhingreso + '}';
    }
    
    
}
