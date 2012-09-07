/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author xtecuan
 */
public class OptionsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer optId;
    private String optName;
    private String optUrl;
    private int optLevel;
    private String optIcon;
    private short optStatus;
    private String optTitle;
    private Integer optOrder;
    private List<OptionsDTO> optionsList;
    private OptionsDTO optParent;

    public OptionsDTO() {
    }

    public Integer getOptId() {
        return optId;
    }

    public void setOptId(Integer optId) {
        this.optId = optId;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public String getOptUrl() {
        return optUrl;
    }

    public void setOptUrl(String optUrl) {
        this.optUrl = optUrl;
    }

    public int getOptLevel() {
        return optLevel;
    }

    public void setOptLevel(int optLevel) {
        this.optLevel = optLevel;
    }

    public String getOptIcon() {
        return optIcon;
    }

    public void setOptIcon(String optIcon) {
        this.optIcon = optIcon;
    }

    public short getOptStatus() {
        return optStatus;
    }

    public void setOptStatus(short optStatus) {
        this.optStatus = optStatus;
    }

    public String getOptTitle() {
        return optTitle;
    }

    public void setOptTitle(String optTitle) {
        this.optTitle = optTitle;
    }

    public Integer getOptOrder() {
        return optOrder;
    }

    public void setOptOrder(Integer optOrder) {
        this.optOrder = optOrder;
    }

    public List<OptionsDTO> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<OptionsDTO> optionsList) {
        this.optionsList = optionsList;
    }

    public OptionsDTO getOptParent() {
        return optParent;
    }

    public void setOptParent(OptionsDTO optParent) {
        this.optParent = optParent;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.optId != null ? this.optId.hashCode() : 0);
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
        final OptionsDTO other = (OptionsDTO) obj;
        if (this.optId != other.optId && (this.optId == null || !this.optId.equals(other.optId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OptionsDTO{" + "optId=" + optId + ", optName=" + optName + ", optUrl=" + optUrl + ", optLevel=" + optLevel + ", optIcon=" + optIcon + ", optStatus=" + optStatus + ", optTitle=" + optTitle + ", optOrder=" + optOrder + ", optionsList=" + optionsList + ", optParent=" + optParent + '}';
    }
    
    
}
