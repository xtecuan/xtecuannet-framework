/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.readers.glassfish3122logreader.enums;

import java.io.Serializable;

/**
 *
 * @author xtecuan
 */
public enum ProcLogEnum implements Serializable {

    ProdDomain1(Integer.valueOf("1"), "sigju.csj.gob.sv", "10.9.45.143", "domain1", "/u01/Java/glassfish3"),
    ProdDomain2(Integer.valueOf("2"), "sigju.csj.gob.sv", "10.9.45.143", "domain2", "/u01/Java/glassfish3"),
    TestSigjur1(Integer.valueOf("3"), "comb.csj.oj", "10.9.45.249", "sigjur1", "/opt/gf3/Java/glassfish-3.1.2"),
    TestSigjur2(Integer.valueOf("4"), "comb.csj.oj", "10.9.45.249", "sigjur2", "/opt/gf3/Java/glassfish-3.1.2"),
    BlitzDomain1(Integer.valueOf("5"), "blitz5.local", "10.9.45.138", "domain1", "/Users/xtecuan/Documents/Work/CSJ/glassfish3"),
    BlitzLocalDomain1(Integer.valueOf("6"), "blitz4.csj.oj", "10.9.45.181", "domain1", "/home/xtecuan/Desktop/logs/");
    private Integer cod;
    private String name;
    private String ip;
    private String domain;
    private String gfhome;

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getGfhome() {
        return gfhome;
    }

    public void setGfhome(String gfhome) {
        this.gfhome = gfhome;
    }

    private ProcLogEnum(Integer cod, String name, String ip, String domain, String gfhome) {
        this.cod = cod;
        this.name = name;
        this.ip = ip;
        this.domain = domain;
        this.gfhome = gfhome;
    }

    public static ProcLogEnum getEnum(String name) {

        if (name.equals("ProdDomain1")) {
            return ProdDomain1;
        } else if (name.equals("TestSigjur2")) {
            return TestSigjur2;
        } else if (name.equals("TestSigjur1")) {
            return TestSigjur1;
        } else if (name.equals("ProdDomain2")) {
            return ProdDomain2;
        } else {
            return BlitzDomain1;
        }

    }
}
