<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:p="http://primefaces.prime.com.tr/ui"
      xmlns:c="http://java.sun.com/jsp/jstl/core"
      xmlns:fn="http://java.sun.com/jsp/jstl/functions">

    <ui:composition template="${basicTemplate}">

        <ui:define name="title">
            <h:outputText value="${r"#{"}bundle.${entityName}_jsf_title${r"}"}"></h:outputText>
        </ui:define>

        <ui:define name="header">
            <div align="left" class="entry">
                <h1 class="title ui-widget-header ui-corner-all" style="width:700px">${r"#{"}bundle.${entityName}_jsf_header${r"}"}</h1>

            </div>
        </ui:define>

        <ui:define name="form">

            <div align="left" class="entry">

                <h:form>

                    
                    <p:panel id="forma">

                    <p:ajaxStatus style="width:16px;height:16px;">
                        <f:facet name="start">
                            <h:graphicImage value="/design/ajaxloading.gif" />
                        </f:facet>

                        <f:facet name="complete">
                            <h:outputText value="" />
                        </f:facet>
                    </p:ajaxStatus>

                    <p:growl id="growl" showDetail="true"/>
                    <p:messages/>

                        <h:panelGrid id="display" columns="2" cellpadding="4">
                             <#list columnNames as i>
                                <#if i.name!="serialVersionUID" && i.type.name !="java.util.List">                                    

                                    <#if ClassUtils.isSimplePKField(i)>

                                        <h:outputLabel value="${r"#{"}bundle.${entityName}_jsf_${i.name}${r"}"}" for="f_${i.name}"/>
                                        <h:inputText id="f_${i.name}" value="${"#{"}current${entityName}.${i.name}${"}"}" title="${"#{"}bundle.${entityName}_jsf_${i.name}${"}"}" disabled ="${"#{"}not insert${"}"}"/>

                                    <#else>                                        
                                          <#if ClassUtils.isManyToOneField(i)>
                                            <#list i.type.declaredFields as j>
                                                <#if ClassUtils.isSimplePKField(j)>
                                                    <#assign combopk="${j.name}">
                                                <#elseif ClassUtils.isEmbeddedIdField(j)>
                                                    <#assign combopk="${j.name}">
                                                </#if>
                                            </#list>
                                            <h:outputLabel value="${r"#{"}bundle.${entityName}_jsf_${i.name}${r"}"}" for="f_${i.name}"/>
                                            <h:selectOneMenu id="f_${i.name}" title="${r"#{"}bundle.${entityName}_jsf_${i.name}${r"}"}" value="${r"#{"}current${entityName}.${i.name}.${combopk}${r"}"}">
                                                <f:selectItem itemLabel="${r"#{"}bundle.${entityName}_jsf_combo_select${r"}"}" itemValue=""/>
                                                <f:selectItems value="${"#{"}${entityName}Bean.items${i.type.simpleName}${"}"}"/>
                                            </h:selectOneMenu>
                                          <#else>
                                                <#if i.type.name=="java.util.Date">
                                                    <h:outputLabel value="${r"#{"}bundle.${entityName}_jsf_${i.name}${r"}"}" for="f_${i.name}"/>
                                                    <p:calendar id="f_${i.name}" value="${"#{"}current${entityName}.${i.name}${"}"}" pattern="dd/MM/yyyy" title="${"#{"}bundle.${entityName}_jsf_${i.name}${"}"}" locale="es"/>
                                                <#else>
                                                    <h:outputLabel value="${r"#{"}bundle.${entityName}_jsf_${i.name}${r"}"}" for="f_${i.name}"/>
                                                    <h:inputText id="f_${i.name}" value="${"#{"}current${entityName}.${i.name}${"}"}" title="${"#{"}bundle.${entityName}_jsf_${i.name}${"}"}"/>
                                                </#if>
                                          </#if>
                                    </#if>                
                                </#if>
                            </#list> 
                            <f:facet name="footer">
                                <h:panelGrid id="display1" columns="3" cellpadding="4">
                                <p:commandButton update="forma" value="${r"#{"}bundle.${entityName}_jsf_btn_save${r"}"}"
                                                 action="${r"#{"}${entityName}Bean.save${entityName}${r"}"}" rendered="${r"#{"}insert${r"}"}"/>

                                <p:commandButton value="${r"#{"}bundle.${entityName}_jsf_btn_edit${r"}"}"
                                                 action="${r"#{"}${entityName}Bean.edit${entityName}${r"}"}"

                                                 rendered="${r"#{"}not insert${r"}"}"/>
                                <p:commandButton value="${"#{"}bundle.${entityName}_jsf_btn_cancel${"}"}" ajax="false" immediate="true" action="${entityName}?faces-redirect=true"/>
                                </h:panelGrid>
                            </f:facet>

                        </h:panelGrid>
                    </p:panel>

                </h:form>


            </div>
        </ui:define>

        <ui:insert name="table">

            <div align="left" class="entry">



            </div>

        </ui:insert>

    </ui:composition>
</html>

