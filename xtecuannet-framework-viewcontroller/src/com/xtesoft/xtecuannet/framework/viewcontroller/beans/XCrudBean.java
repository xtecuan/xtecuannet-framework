/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.viewcontroller.beans;

import com.xtesoft.xtecuannet.framework.model.services.GenericService;
import com.xtesoft.xtecuannet.framework.utils.ClassUtils;
import com.xtesoft.xtecuannet.framework.viewcontroller.beans.enums.MessageKeyEntry;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author xtecuan
 */
public class XCrudBean<T> extends XBaseBean implements Serializable {

    private Class<T> entityClass;
    private GenericService<T> service;
    private T current;
    private T selected;
    private T search;
    private Boolean insert = Boolean.TRUE;
    private List<T> gridList;
    private Field pkField;
    private List<Field> manyToOneFields;
    private Map<String, Object> messageKeys;

    public XCrudBean() {
    }

    public XCrudBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public XCrudBean(GenericService<T> service) {
        this.service = service;
    }

    public XCrudBean(Class<T> entityClass, GenericService<T> service) {
        this.entityClass = entityClass;
        this.service = service;
    }

    public XCrudBean(Class<T> entityClass, GenericService<T> service, Map<String, Object> messageKeys) {
        this.entityClass = entityClass;
        this.service = service;
        this.messageKeys = messageKeys;
    }

    public T getCurrent() {
        if (current == null) {
            iniEntityWhich(current);
        }
        return current;
    }

    @PostConstruct
    public void init() {
        iniPkField();
        iniManyToOneFields();
        fillGridList();
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> getGridList() {
        return gridList;
    }

    public void setGridList(List<T> gridList) {
        this.gridList = gridList;
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }

    public T getSearch() {

        if (search == null) {
            iniEntityWhich(search);
        }
        return search;
    }

    public void setSearch(T search) {
        this.search = search;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    public GenericService<T> getService() {
        return service;
    }

    public void setService(GenericService<T> service) {
        this.service = service;
    }

    private void iniPkField() {
        pkField = ClassUtils.getEmbeddedIdField(this.getEntityClass());
        if (pkField == null) {
            pkField = ClassUtils.getSimpleIdField(this.getEntityClass());
        }

        if (pkField != null) {
            System.out.println("pkField: " + pkField.getName() + " " + pkField.getType());
        } else {
            System.out.println("Viene nulo!!!");
        }
    }

    private void fillGridList() {
        this.gridList = getService().findAll();
    }

    private void iniManyToOneFields() {
        this.manyToOneFields = ClassUtils.getManyToOneFields(this.getEntityClass());
    }

    private void iniEntityWhich(T which) {
        try {
            //        try {
            //            
            //            if (pkField == null) {
            //                iniPkField();
            //                System.out.println("Estaba nula y se creo el pkField");
            //            }
            //            which = getEntityClass().newInstance();
            //            System.out.println("which="+which);
            ////            if (ClassUtils.isEmbeddedIdField(pkField)) {
            //
            //            ClassUtils.setPropertyToInstance(which, pkField.getName(), pkField.getType().newInstance());
            ////            }
            //
            //            if (manyToOneFields == null) {
            //                
            //                iniManyToOneFields();
            //                
            //                for (Field field : getManyToOneFields()) {
            //                    ClassUtils.setPropertyToInstance(which, field.getName(), field.getType().newInstance());
            //                }
            //                
            //            }
            //            System.out.println("Final del método iniEntityWhich");
            //            
            //        } catch (Exception ex) {
            //            
            //            getLogger().error("Error when creating instance for current form value: class: " + entityClass.getName(), ex);
            //        }


            System.out.println("entityClass: " + getEntityClass().getName());

           which = (T) ClassUtils.createEmptyEntityInstance(getEntityClass());

            getLogger().error("which: " + which.toString());

        } catch (Exception ex) {
            getLogger().error("Error al instanciar la entidad de tipo: " + getEntityClass().getName(), ex);
        } 

    }

    public Field getPkField() {
        return pkField;
    }

    public void setPkField(Field pkField) {
        this.pkField = pkField;
    }

    public List<Field> getManyToOneFields() {
        return manyToOneFields;
    }

    public void setManyToOneFields(List<Field> manyToOneFields) {
        this.manyToOneFields = manyToOneFields;
    }

    private Object[] getPkValuesForOutMessage(T which) {

        Object pkVal = ClassUtils.getPropertyFromInstance(which, pkField.getName());

        List<Field> pkMetaData = ClassUtils.getOnlyFields(pkField.getType());

//        List<String> colNames = new ArrayList<String>(0);
//
//        if (ClassUtils.isSimplePKField(pkField)) {
//            colNames.add((String) messageKeys.get(MessageKeyEntry.PK_COLS_NAMES_ARRAY_KEYS.getKey()));
//        } else {
//            colNames = (List<String>) messageKeys.get(MessageKeyEntry.PK_COLS_NAMES_ARRAY_KEYS.getKey());
//        }



        Object[] colVals = new Object[pkMetaData.size()];

        for (int i = 0; i < pkMetaData.size(); i++) {
            Field col = pkMetaData.get(i);
            colVals[i] = ClassUtils.getPropertyFromInstance(pkVal, col.getName());

        }

        return colVals;
    }

    public void save(ActionEvent actionEvent) {
        System.out.println(getEntityClass().getName() + " " + ClassUtils.getPropertyFromInstance(current, pkField.getName()));
        try {
            T outEntity = getService().create(this.getCurrent());
            addMessage(this.getMessage((String) messageKeys.get(MessageKeyEntry.SUCCESS_KEY.getKey()), null, Locale.ENGLISH),
                    this.getMessage((String) messageKeys.get(MessageKeyEntry.CREATE_KEY.getKey()),
                    getPkValuesForOutMessage(current), Locale.ENGLISH));

            this.fillGridList();

            iniEntityWhich(current);
        } catch (Exception ex) {
            getLogger().error("Error trying to save entity of type: class: " + entityClass.getName(), ex);
        }
        FacesContext.getCurrentInstance().renderResponse();
    }

    public void delete(ActionEvent event) {
        if (this.getSelected() != null) {
            System.out.println("Selected: " + this.getSelected());
            this.getService().remove(this.getSelected());
            this.addMessage(this.getMessage((String) getMessageKeys().get(MessageKeyEntry.SUCCESS_KEY.getKey()), null, Locale.ENGLISH),
                    this.getMessage((String) getMessageKeys().get(MessageKeyEntry.DELETE_KEY.getKey()), getPkValuesForOutMessage(selected),
                    Locale.ENGLISH));
            this.fillGridList();
            iniEntityWhich(current);
            FacesContext.getCurrentInstance().renderResponse();
        }
    }

    public void prepareUpdate(ActionEvent event) {
        System.out.println("Selected: " + this.getSelected());

        this.current = this.getSelected();
        System.out.println(this.getCurrent().toString());
        this.insert = Boolean.FALSE;
        FacesContext.getCurrentInstance().renderResponse();
    }

    public void update(ActionEvent event) {
        this.getService().edit(this.getCurrent());
        this.addMessage(this.getMessage((String) getMessageKeys().get(MessageKeyEntry.SUCCESS_KEY.getKey()), null, Locale.ENGLISH),
                this.getMessage((String) MessageKeyEntry.UPDATE_KEY.getKey(),
                getPkValuesForOutMessage(current),
                Locale.ENGLISH));
        this.fillGridList();
        this.insert = Boolean.TRUE;
        iniEntityWhich(selected);
        iniEntityWhich(current);
        FacesContext.getCurrentInstance().renderResponse();
    }

    public String cancel() {

        return getEntityClass().getSimpleName() + "_form?faces-redirect=true";

    }

    public void find(ActionEvent event) {

        this.gridList = new ArrayList<T>(0);

        if (search != null) {

            this.gridList.add(getService().find(ClassUtils.getPropertyFromInstance(search, pkField.getName())));

            if (this.gridList.isEmpty()) {

                this.addMessage(
                        this.getMessage((String) getMessageKeys().get(MessageKeyEntry.FAILURE_KEY.getKey()), null, Locale.ENGLISH),
                        this.getMessage((String) getMessageKeys().get(MessageKeyEntry.SEARCH_NOT_FOUND_KEY.getKey()),
                        null, Locale.ENGLISH));
            } else {
                this.addMessage(
                        this.getMessage((String) getMessageKeys().get(MessageKeyEntry.FAILURE_KEY.getKey()), null, Locale.ENGLISH),
                        this.getMessage((String) getMessageKeys().get(MessageKeyEntry.FIND_KEY.getKey()), new Object[]{this.gridList.size()}, Locale.ENGLISH));

            }
        }
    }

    public Map<String, Object> getMessageKeys() {
        return messageKeys;
    }

    public void setMessageKeys(Map<String, Object> messageKeys) {
        this.messageKeys = messageKeys;
    }
}
