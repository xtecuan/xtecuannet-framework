/*
 * =======${entityName}Bean.java=======
 * Xtecuannet-framework-console-utils v${version}
 * Managed Bean generator ${currentDatetime}
 */
package ${appWebPackage};

import ${appModelPackage}.${appModelPentities}.${entityName};
<#assign currentEntity="${appModelPackage}.${appModelPentities}.${entityName}">
<#list columnNames as i><#if ClassUtils.isManyToOneField(i)>
<#if currentEntity!="${appModelPackage}.${appModelPentities}.${i.type.simpleName}">
import ${appModelPackage}.${appModelPentities}.${i.type.simpleName};
</#if>
</#if></#list>
import ${appModelPackage}.${appModelPservices}.${entityName}Service;
<#assign currentService="${appModelPackage}.${appModelPservices}.${entityName}Service">
<#list columnNames as i><#if ClassUtils.isManyToOneField(i)>
<#if currentService!="${appModelPackage}.${appModelPservices}.${i.type.simpleName}Service">
import ${appModelPackage}.${appModelPservices}.${i.type.simpleName}Service;
</#if>
</#if></#list>
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
<#assign counter=0>
<#list columnNames as i><#if ClassUtils.isManyToOneField(i)>
<#if counter==0>
import javax.faces.model.SelectItem;
<#assign counter=1>
</#if>
</#if></#list>
import org.springframework.beans.factory.annotation.Autowired;
import ${appWebViewUtils};
import ${appWebBaseBean};


/**
 *
 * @author ${userName}
 */
public class ${entityName}Bean extends XBaseBean implements Serializable {

    @Autowired
    private ${entityName}Service facade${entityName};
    <#assign currentFacade="facade${entityName}">
    <#list columnNames as i><#if ClassUtils.isManyToOneField(i)>
    <#if currentService!="${appModelPackage}.${appModelPservices}.${i.type.simpleName}Service">
    ${r"@"}Autowired
    private ${i.type.simpleName}Service facade${i.type.simpleName};
    </#if>
    </#if>
    </#list>
    private ${entityName} current${entityName};
    private Boolean insert = Boolean.TRUE;
    private List<${entityName}> list${entityName} = new ArrayList<${entityName}>(0);
    <#list columnNames as i><#if ClassUtils.isManyToOneField(i)>
    private List<SelectItem> items${i.type.simpleName} = new ArrayList<SelectItem>(0);
    </#if></#list>
    


    public ${entityName}Bean(){

        this.removeDeleteMessage();
    }

    private void removeDeleteMessage() {
        if (this.getSession().getAttribute("outMessage") != null) {
            this.getSession().removeAttribute("outMessage");
        }
    }

    public ${entityName}Service getFacade${entityName}() {
        return facade${entityName};
    }

    public void setFacade${entityName}(${entityName}Service facade${entityName}) {
        this.facade${entityName} = facade${entityName};
    }

    public ${entityName} getCurrent${entityName}() {
        if (this.current${entityName} == null) {
            this.current${entityName} = new ${entityName}();
            <#list columnNames as i><#if ClassUtils.isManyToOneField(i)>
            this.current${entityName}.set${i.name?cap_first}(new ${i.type.simpleName?cap_first}());
            </#if></#list>
        }
        return current${entityName};
    }

    public void setCurrent${entityName}(${entityName} current${entityName}) {
        this.current${entityName} = current${entityName};
    }

    public Boolean getInsert() {
        return insert;
    }

    public void setInsert(Boolean insert) {
        this.insert = insert;
    }

    public List<${entityName}> getList${entityName}() {
        return list${entityName};
    }

    public void setList${entityName}(List<${entityName}> list${entityName}) {
        this.list${entityName} = list${entityName};
    }

    <#list columnNames as i><#if ClassUtils.isManyToOneField(i)>
    public List<SelectItem> getItems${i.type.simpleName}(){
        return items${i.type.simpleName};
    }

    public void setItems${i.type.simpleName}(List<SelectItem> items${i.type.simpleName}){
        this.items${i.type.simpleName}=items${i.type.simpleName};
    }
    </#if></#list>
    <#list columnNames as i><#if ClassUtils.isManyToOneField(i)>
    <#if currentService!="${appModelPackage}.${appModelPservices}.${i.type.simpleName}Service">

    public ${i.type.simpleName}Service getFacade${i.type.simpleName}() {
        return facade${i.type.simpleName};
    }

    public void setFacade${i.type.simpleName}(${i.type.simpleName}Service facade${i.type.simpleName}) {
        this.facade${i.type.simpleName} = facade${i.type.simpleName};
    }
    </#if>
    </#if>
    </#list>

    @PostConstruct
    private void init() {
        
        this.filloutList${entityName}();
        <#list columnNames as i><#if ClassUtils.isManyToOneField(i)>
        this.filloutItems${i.type.simpleName}();
        </#if>
        </#list>
        

    }

    private void filloutList${entityName}() {
        this.list${entityName} = this.getFacade${entityName}().findAll();
    }
    <#list columnNames as i>
    <#if ClassUtils.isSimplePKField(i)>
    <#assign pk="${i.name}">
    <#elseif ClassUtils.isEmbeddedIdField(i)>
    <#assign pk="${i.name}">
    </#if>
    </#list>
    <#list columnNames as i><#if ClassUtils.isManyToOneField(i)>
    <#list i.type.declaredFields as j>
        <#if ClassUtils.isSimplePKField(j)>
                <#assign pk1="${j.name}">
        </#if>
    </#list>
    private void filloutItems${i.type.simpleName}() {
        <#if currentService =="${appModelPackage}.${appModelPservices}.${i.type.simpleName}Service">
        this.items${i.type.simpleName} = ViewUtils.fromListToSelectItem(this.getFacade${i.type.simpleName}().findAll(), "${pk1}", "${pk1}");
        <#else>
        this.items${i.type.simpleName} = ViewUtils.fromListToSelectItem(this.getFacade${i.type.simpleName}().findAll(), "${pk1}", "${pk1}");
        </#if>
    }
    </#if></#list>

    public String doCreateNew${entityName}() {

        this.insert = Boolean.TRUE;
        this.getSession().setAttribute("insert", this.insert);
        this.getSession().setAttribute("current${entityName}", this.getCurrent${entityName}());
        return "${entityName}_form?faces-redirect=true";

    }

    public void delete${entityName}(ActionEvent event) {

        this.insert = Boolean.TRUE;
        if (this.getCurrent${entityName}() != null) {

            this.getFacade${entityName}().remove(this.getCurrent${entityName}());
            
            this.addMessage(this.obtenerMensajeBundle("${entityName}_jsf_msg_delete", new Object[]{this.getCurrent${entityName}().get${pk?cap_first}()}));
            this.filloutList${entityName}();
            FacesContext.getCurrentInstance().renderResponse();

        } else {

            this.getLogger().info("Select a row from table");
        }

    }

    public String doEdit${entityName}() {
        this.insert = Boolean.FALSE;
        this.getSession().setAttribute("insert", this.insert);
        this.getSession().setAttribute("current${entityName}", this.getCurrent${entityName}());
        return "${entityName}_form?faces-redirect=true";
    }

    public String save${entityName}() {
        this.current${entityName} = (${entityName}) this.getSession().getAttribute("current${entityName}");
        if (this.getCurrent${entityName}() != null) {


            ${entityName} salida = this.getFacade${entityName}().create(this.getCurrent${entityName}());
            this.addMessage(this.obtenerMensajeBundle("${entityName}_jsf_msg_save", new Object[]{this.getCurrent${entityName}().get${pk?cap_first}()}));
            this.getSession().setAttribute("outMessage", "${entityName} created with id: " + getCurrent${entityName}().get${pk?cap_first}());
            
        } else {
            this.getLogger().info("no entro por lo tanto no guardo");
        }
        this.getSession().removeAttribute("currentMenu");

        return "${entityName}?faces-redirect=true";


    }

    public String edit${entityName}() {
        this.current${entityName} = (${entityName}) this.getSession().getAttribute("current${entityName}");
        if (this.getCurrent${entityName}() != null) {

            ${entityName} salida = this.getFacade${entityName}().edit(this.getCurrent${entityName}());
            this.addMessage(this.obtenerMensajeBundle("${entityName}_jsf_msg_edit", new Object[]{this.getCurrent${entityName}().get${pk?cap_first}()}));
            this.getSession().setAttribute("outMessage", "${entityName} updated Id: " + this.getCurrent${entityName}().get${pk?cap_first}());

        }
        this.getSession().removeAttribute("current${entityName}");
        return "${entityName}?faces-redirect=true";



    }

   
}