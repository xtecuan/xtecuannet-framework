/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.viewcontroller.beans;

import java.io.Serializable;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.toolbar.Toolbar;
import org.primefaces.component.toolbar.ToolbarGroup;
import org.xtecuan.model.dto.UsersDTO;
import org.xtecuan.viewcontroller.beans.enums.ImageEnum;
import org.xtecuan.viewcontroller.beans.generic.SystemInfoBean;
import org.xtecuan.viewcontroller.beans.utils.JSFUtil;

/**
 *
 * @author xtecuan
 */
public class Usuarios implements Serializable {

    private UsersDTO current;
    private Toolbar toolbar;
    private ToolbarGroup toolbarGroup;
    private HtmlForm form;

    /**
     * Creates a new instance of Usuarios
     */
    public Usuarios() {
        init();
    }

    private void init() {

//        this.form = (HtmlForm) JSFUtil.findComponentInRoot("");
        
        CommandLink saveLink = JSFUtil.createCommandLinkPlusImage(ImageEnum.Save22.getPath(),
                ImageEnum.Save22.getLabel());

        CommandLink editLink = JSFUtil.createCommandLinkPlusImage(ImageEnum.Edit22.getPath(),
                ImageEnum.Edit22.getLabel());

        CommandLink findLink = JSFUtil.createCommandLinkPlusImage(ImageEnum.Find22.getPath(),
                ImageEnum.Find22.getLabel());

        CommandLink deleteLink = JSFUtil.createCommandLinkPlusImage(ImageEnum.Delete22.getPath(),
                ImageEnum.Delete22.getLabel());

//        SystemInfoBean info = (SystemInfoBean) JSFUtil.getBean("systemInfoBean");

        getToolbarGroup().getChildren().add(saveLink);
        getToolbarGroup().getChildren().add(editLink);
        getToolbarGroup().getChildren().add(findLink);
        getToolbarGroup().getChildren().add(deleteLink);

        getToolbar().getChildren().add(getToolbarGroup());

        form.getChildren().add(getToolbar());

//        FacesContext.getCurrentInstance().renderResponse();


    }

    public UsersDTO getCurrent() {

        if (this.current == null) {

            this.current = new UsersDTO();

        }

        return current;
    }

    public void setCurrent(UsersDTO current) {
        this.current = current;
    }

    public Toolbar getToolbar() {

        if (toolbar == null) {

            toolbar = (Toolbar) JSFUtil.createComponent(Toolbar.COMPONENT_TYPE);

            //toolbar.getChildren().add(getToolbarGroup());
        }

        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public ToolbarGroup getToolbarGroup() {

        if (this.toolbarGroup == null) {

            this.toolbarGroup = (ToolbarGroup) JSFUtil.createComponent(ToolbarGroup.COMPONENT_TYPE);
        }

        return toolbarGroup;
    }

    public void setToolbarGroup(ToolbarGroup toolbarGroup) {
        this.toolbarGroup = toolbarGroup;
    }

    public HtmlForm getForm() {

        if (form == null) {

            form = (HtmlForm) JSFUtil.createComponent(HtmlForm.COMPONENT_TYPE);
        }

        return form;
    }

    public void setForm(HtmlForm form) {
        this.form = form;
    }
}
