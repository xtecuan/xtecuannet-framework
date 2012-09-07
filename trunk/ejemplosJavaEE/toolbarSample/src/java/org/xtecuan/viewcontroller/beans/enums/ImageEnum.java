/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.viewcontroller.beans.enums;

import java.io.Serializable;

/**
 *
 * @author xtecuan
 */
public enum ImageEnum implements Serializable {

    Save22("Guardar", "/icons/22x22/document-save.png"),
    Save16("Guardar", "/icons/16x16/document-save.png"),
    Edit22("Actualizar", "/icons/22x22/edit-find-replace.png"),
    Edit16("Actualizar", "/icons/16x16/edit-find-replace.png"),
    Delete22("Borrar", "/icons/22x22/edit-delete.png"),
    Delete16("Borrar", "/icons/16x16/edit-delete.png"),
    Find22("Borrar", "/icons/22x22/edit-find.png"),
    Find16("Borrar", "/icons/16x16/edit-find.png");
    private String label;
    private String path;

    private ImageEnum(String label, String path) {
        this.label = label;
        this.path = path;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
