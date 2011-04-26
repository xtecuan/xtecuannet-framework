/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.ues.siaarafia.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author xtecuan
 */
public class NotasExcelDto implements Serializable {

    private String carnet;
    private String nombres;
    private String apellidos;
    private String codasignatura;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;
    private BigDecimal nota4;
    private BigDecimal nota5;
    private BigDecimal nota6;
    private BigDecimal nota7;
    private BigDecimal nota8;
    private BigDecimal nota9;
    private BigDecimal nota10;

    public NotasExcelDto() {
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCodasignatura() {
        return codasignatura;
    }

    public void setCodasignatura(String codasignatura) {
        this.codasignatura = codasignatura;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }

    public BigDecimal getNota10() {
        return nota10;
    }

    public void setNota10(BigDecimal nota10) {
        this.nota10 = nota10;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }

    public BigDecimal getNota3() {
        return nota3;
    }

    public void setNota3(BigDecimal nota3) {
        this.nota3 = nota3;
    }

    public BigDecimal getNota4() {
        return nota4;
    }

    public void setNota4(BigDecimal nota4) {
        this.nota4 = nota4;
    }

    public BigDecimal getNota5() {
        return nota5;
    }

    public void setNota5(BigDecimal nota5) {
        this.nota5 = nota5;
    }

    public BigDecimal getNota6() {
        return nota6;
    }

    public void setNota6(BigDecimal nota6) {
        this.nota6 = nota6;
    }

    public BigDecimal getNota7() {
        return nota7;
    }

    public void setNota7(BigDecimal nota7) {
        this.nota7 = nota7;
    }

    public BigDecimal getNota8() {
        return nota8;
    }

    public void setNota8(BigDecimal nota8) {
        this.nota8 = nota8;
    }

    public BigDecimal getNota9() {
        return nota9;
    }

    public void setNota9(BigDecimal nota9) {
        this.nota9 = nota9;
    }

    @Override
    public String toString() {
        return "NotasExcelDto{" + "carnet=" + carnet + ", nombres=" + nombres + ", apellidos=" + apellidos + ", codasignatura=" + codasignatura + ", nota1=" + nota1 + ", nota2=" + nota2 + ", nota3=" + nota3 + ", nota4=" + nota4 + ", nota5=" + nota5 + ", nota6=" + nota6 + ", nota7=" + nota7 + ", nota8=" + nota8 + ", nota9=" + nota9 + ", nota10=" + nota10 + '}';
    }
    
    
}
