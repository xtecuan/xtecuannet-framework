/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.readers.glassfish3122logreader.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xtecuan
 */
@Entity
@Table(name = "LOG_GLASSFISH", catalog = "", schema = "SIGJULOGGER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogGlassfish.findAll", query = "SELECT l FROM LogGlassfish l"),
    @NamedQuery(name = "LogGlassfish.findById", query = "SELECT l FROM LogGlassfish l WHERE l.id = :id"),
    @NamedQuery(name = "LogGlassfish.findByLdomain", query = "SELECT l FROM LogGlassfish l WHERE l.ldomain = :ldomain"),
    @NamedQuery(name = "LogGlassfish.findByLdatetime", query = "SELECT l FROM LogGlassfish l WHERE l.ldatetime = :ldatetime"),
    @NamedQuery(name = "LogGlassfish.findByLoglevel", query = "SELECT l FROM LogGlassfish l WHERE l.loglevel = :loglevel"),
    @NamedQuery(name = "LogGlassfish.findByLprodnameversion", query = "SELECT l FROM LogGlassfish l WHERE l.lprodnameversion = :lprodnameversion"),
    @NamedQuery(name = "LogGlassfish.findByLoggername", query = "SELECT l FROM LogGlassfish l WHERE l.loggername = :loggername"),
    @NamedQuery(name = "LogGlassfish.findByLkeyvaluepairs", query = "SELECT l FROM LogGlassfish l WHERE l.lkeyvaluepairs = :lkeyvaluepairs"),
    @NamedQuery(name = "LogGlassfish.findByLmessage", query = "SELECT l FROM LogGlassfish l WHERE l.lmessage = :lmessage"),
    @NamedQuery(name = "LogGlassfish.findByLserverip", query = "SELECT l FROM LogGlassfish l WHERE l.lserverip = :lserverip"),
    @NamedQuery(name = "LogGlassfish.findByLmessageNext", query = "SELECT l FROM LogGlassfish l WHERE l.lmessageNext = :lmessageNext"),
    @NamedQuery(name = "LogGlassfish.findByLlogfile", query = "SELECT l FROM LogGlassfish l WHERE l.llogfile = :llogfile")})
public class LogGlassfish implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPLSEQ")
    @SequenceGenerator(name = "SPLSEQ", sequenceName = "LOG_GLASSFISH_SEQ", allocationSize = 1, initialValue = 1)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "LDOMAIN", nullable = false, length = 20)
    private String ldomain;
    @Basic(optional = false)
    @Column(name = "LDATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ldatetime;
    @Column(name = "LOGLEVEL", length = 20)
    private String loglevel;
    @Column(name = "LPRODNAMEVERSION", length = 20)
    private String lprodnameversion;
    @Column(name = "LOGGERNAME", length = 150)
    private String loggername;
    @Column(name = "LKEYVALUEPAIRS", length = 150)
    private String lkeyvaluepairs;
    @Basic(optional = false)
    @Column(name = "LMESSAGE", nullable = false, length = 4000)
    private String lmessage;
    @Column(name = "LSERVERIP", length = 35)
    private String lserverip;
    @Column(name = "LMESSAGE_NEXT", length = 4000)
    private String lmessageNext;
    @Column(name = "LLOGFILE", length = 100)
    private String llogfile;

    public LogGlassfish() {
    }

    public LogGlassfish(Integer id) {
        this.id = id;
    }

    public LogGlassfish(Integer id, String ldomain, Date ldatetime, String lmessage) {
        this.id = id;
        this.ldomain = ldomain;
        this.ldatetime = ldatetime;
        this.lmessage = lmessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getLserverip() {
        return lserverip;
    }

    public void setLserverip(String lserverip) {
        this.lserverip = lserverip;
    }

    public String getLmessageNext() {
        return lmessageNext;
    }

    public void setLmessageNext(String lmessageNext) {
        this.lmessageNext = lmessageNext;
    }

    public String getLlogfile() {
        return llogfile;
    }

    public void setLlogfile(String llogfile) {
        this.llogfile = llogfile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogGlassfish)) {
            return false;
        }
        LogGlassfish other = (LogGlassfish) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.xtecuan.readers.glassfish3122logreader.entities.LogGlassfish[ id=" + id + " ]";
    }
}
