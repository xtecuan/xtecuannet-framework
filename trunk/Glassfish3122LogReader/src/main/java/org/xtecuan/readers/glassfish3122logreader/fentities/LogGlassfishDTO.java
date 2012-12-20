/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.readers.glassfish3122logreader.fentities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author xtecuan
 */
public class LogGlassfishDTO implements Serializable{
    
    private Long id;
    private String ldomain;
    private Date ldatetime;
    private String loglevel;
    private String lprodnameversion;
    private String loggername;
    private String lkeyvaluepairs;
    private String lmessage;

    public LogGlassfishDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLdomain() {
        return ldomain;
    }

    public void setLdomain(String ldomain) {
        this.ldomain = ldomain;
    }

    public Date getLdatetime() {
        return ldatetime;
    }

    public void setLdatetime(Date ldatetime) {
        this.ldatetime = ldatetime;
    }

    public String getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(String loglevel) {
        this.loglevel = loglevel;
    }

    public String getLprodnameversion() {
        return lprodnameversion;
    }

    public void setLprodnameversion(String lprodnameversion) {
        this.lprodnameversion = lprodnameversion;
    }

    public String getLoggername() {
        return loggername;
    }

    public void setLoggername(String loggername) {
        this.loggername = loggername;
    }

    public String getLkeyvaluepairs() {
        return lkeyvaluepairs;
    }

    public void setLkeyvaluepairs(String lkeyvaluepairs) {
        this.lkeyvaluepairs = lkeyvaluepairs;
    }

    public String getLmessage() {
        return lmessage;
    }

    public void setLmessage(String lmessage) {
        this.lmessage = lmessage;
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
        final LogGlassfishDTO other = (LogGlassfishDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LogGlassfishDTO{" + "id=" + id + ", ldomain=" + ldomain + ", ldatetime=" + ldatetime + ", loglevel=" + loglevel + ", lprodnameversion=" + lprodnameversion + ", loggername=" + loggername + ", lkeyvaluepairs=" + lkeyvaluepairs + ", lmessage=" + lmessage + '}';
    }
    
    
    
}
