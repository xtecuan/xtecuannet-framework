/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.samples.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author xtecuan
 */
public class Alumnos implements Serializable {

    public static final String SELECT_ALL = "select * from alumnos ";
    public static final String SELECT_X_ID = SELECT_ALL + " where id=?";
    public static final String INSERT = "INSERT INTO alumnos (carnet,nombres,apellidos,${0}) \n"
            + "	VALUES (?, ?, ?,${1})";
    public static final String DELETE = "DELETE FROM alumnos WHERE id = ?";
    public static final String UPDATE_BASE = "UPDATE alumnos SET ${0} WHERE id = ?";
    private Integer id;
    private String carnet;
    private String nombres;
    private String apellidos;
    private String correo;
    private Date fechanac;

    public Alumnos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Alumnos other = (Alumnos) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Alumnos{" + "id=" + id + ", carnet=" + carnet + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correo=" + correo + ", fechanac=" + fechanac + '}';
    }

    public static String generateUpdate(StringBuilder sb) {


        return UPDATE_BASE.replace("${0}", sb.toString());
    }

    public static String generateInsert(StringBuilder cols, StringBuilder marks) {

        if (cols.length()==0 && marks.length()==0) {

            return INSERT.replace(",${0}", "").replace(",${1}", "");
        } else {

            return INSERT.replace("${0}", cols.toString()).replace("${1}", marks.toString());
        }

    }
    
    public static void main(String[] args) {
        
        
        System.out.println(generateInsert(null, null));
        
        
    }
}
