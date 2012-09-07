/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.viewcontroller.beans.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.component.toolbar.Toolbar;
import org.primefaces.component.toolbar.ToolbarGroup;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.xtecuan.model.dto.OptionsDTO;
import org.xtecuan.model.dto.UsersDTO;

/**
 *
 * @author xtecuan
 */
public class SystemInfoBean implements Serializable {

    private UsersDTO user;
    private MenuModel model;
    private Toolbar toolbar;
    private ToolbarGroup toolbarGroup;

    /**
     * Creates a new instance of SystemInfoBean
     */
    public SystemInfoBean() {
        populateMenuModel();
        populateUser();
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    
    private void populateUser(){
        
        user = new UsersDTO();
        
        user.setUserId(1);
        user.setName("Julian");
        user.setLastname("Rivera Pineda");
        user.setLogin("xtecuan");
        
    }

    private void populateMenuModel() {

        model = new DefaultMenuModel();

        List<OptionsDTO> options = new ArrayList<OptionsDTO>(0);

        OptionsDTO inicio = new OptionsDTO();
        inicio.setOptId(1);
        inicio.setOptTitle("Inicio");
        inicio.setOptUrl("/app/inicio.jsf");
        OptionsDTO inicio1 = new OptionsDTO();
        inicio1.setOptId(2);
        inicio1.setOptTitle("Ir Inicio");
        inicio1.setOptUrl("/app/inicio.jsf");
        inicio1.setOptParent(inicio);

        List<OptionsDTO> listaInicio = new ArrayList<OptionsDTO>(0);
        listaInicio.add(inicio1);
        inicio.setOptionsList(listaInicio);


        OptionsDTO cat = new OptionsDTO();
        cat.setOptId(3);
        cat.setOptTitle("Catalogos");
        cat.setOptUrl("/app/usuarios.jsf");
        OptionsDTO cat1 = new OptionsDTO();
        cat1.setOptId(4);
        cat1.setOptTitle("Usuarios");
        cat1.setOptUrl("/app/usuarios.jsf");
        cat1.setOptParent(cat);
        OptionsDTO cat2 = new OptionsDTO();
        cat2.setOptId(5);
        cat2.setOptTitle("Roles");
        cat2.setOptUrl("/app/roles.jsf");
        cat2.setOptParent(cat);

        options.add(inicio);
        options.add(cat);


        List<OptionsDTO> listaCat = new ArrayList<OptionsDTO>(0);
        listaCat.add(cat1);
        listaCat.add(cat2);
        cat.setOptionsList(listaCat);


        for (OptionsDTO option : options) {

            Submenu submenu = new Submenu();
            submenu.setLabel(option.getOptTitle());

            List<OptionsDTO> items = option.getOptionsList();

            for (OptionsDTO item : items) {

                List<OptionsDTO> subtipo2 = item.getOptionsList();

                if (subtipo2 == null || subtipo2.isEmpty()) {

                    MenuItem menuItem = new MenuItem();
                    menuItem.setValue(item.getOptTitle());
                    menuItem.setUrl(item.getOptUrl());
                    submenu.getChildren().add(menuItem);
                } else {
                    Submenu tipo2 = new Submenu();
                    tipo2.setLabel(item.getOptTitle());
                    for (OptionsDTO itemtipo3 : subtipo2) {
                        MenuItem menuItem1 = new MenuItem();
                        menuItem1.setValue(itemtipo3.getOptTitle());
                        menuItem1.setUrl(itemtipo3.getOptUrl());
                        tipo2.getChildren().add(menuItem1);
                    }
                    submenu.getChildren().add(tipo2);
                }
            }

            model.addSubmenu(submenu);

        }



    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public ToolbarGroup getToolbarGroup() {
        return toolbarGroup;
    }

    public void setToolbarGroup(ToolbarGroup toolbarGroup) {
        this.toolbarGroup = toolbarGroup;
    }
    
    
}
