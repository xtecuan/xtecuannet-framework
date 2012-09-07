/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.viewcontroller.beans.utils;

import java.util.Iterator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.component.graphicimage.GraphicImage;

/**
 *
 * @author xtecuan
 */
public final class JSFUtil {

    protected static FacesContext facesContext;

    public static Object getBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getELContext().getELResolver().getValue(context.getELContext(), null, beanName);
    }

    public static UIComponent findComponentInRoot(String id) {
        UIComponent component = null;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            UIComponent root = facesContext.getViewRoot();
            component = findComponent(root, id);
        }

        return component;
    }

    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId())) {
            return base;
        }

        UIComponent kid = null;
        UIComponent result = null;
        Iterator kids = base.getFacetsAndChildren();
        while (kids.hasNext() && (result == null)) {
            kid = (UIComponent) kids.next();
            if (id.equals(kid.getId())) {
                result = kid;
                break;
            }
            result = findComponent(kid, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    public static UIComponent createComponent(String componentType) {
        return FacesContext.getCurrentInstance().getApplication().createComponent(componentType);
    }

    public static CommandLink createCommandLinkPlusImage(String imgPath, String tooltipMsg) {
        CommandLink link = (CommandLink) createComponent(CommandLink.COMPONENT_TYPE);
        GraphicImage image = (GraphicImage) createComponent(GraphicImage.COMPONENT_TYPE);
        image.setUrl(imgPath);
        image.setAlt(tooltipMsg);
        link.getChildren().add(image);
        return link;
    }
}
