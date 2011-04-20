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
                <h1 class="title ui-widget-header ui-corner-all" style="width:700px">${r"#{"}bundle.${entityName}_jsf_header${"}"}</h1>

            </div>
        </ui:define>

        <ui:define name="form">

            <div align="left" class="entry">

                <h:form prependId="false">

                    <p:outputPanel id="campnou">


                        <h3 style="color:#4eb305"> <h:outputText rendered="${r"#{"}not empty outMessage${r"}"}" value="${r"#{"}outMessage${r"}"}" /></h3>

                    </p:outputPanel>

                    <p:panel id="paneldata">

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

                        <p:dataTable id="table${entityName}" var="${entityName}Var" value="${r"#{"}${entityName}Bean.list${entityName}${r"}"}" paginator="true" rows="10"
                                     selection="${r"#{"}${entityName}Bean.current${entityName}${r"}"}">

                            <f:facet name="header">
                                ${r"#{"}bundle.${entityName}_jsf_header${r"}"}
                            </f:facet>

                            <p:column selectionMode="single" />

                            

                            <#list columnNames as i>


                                <#if i.name!="serialVersionUID" && i.type.name !="java.util.List">

                                    <p:column>
                                        <f:facet name="header">
                                            <h:outputText value="${r"#{"}bundle.${entityName}_jsf_${i.name}${r"}"}" />
                                        </f:facet>

                                        

                                        <#if i.type.name=='java.util.Date'>
                                            <h:outputText value="${r"#{"}${entityName}Var.${i.name}${r"}"}">
                                                <f:convertDateTime pattern="dd/MM/yyyy"/>
                                            </h:outputText>
                                        <#else>
                                            <h:outputText value="${r"#{"}${entityName}Var.${i.name}${r"}"}"/>
                                        </#if>

                                    </p:column>

                                </#if>


                            </#list>                 
                      
                            <f:facet name="footer">

                                <h:panelGrid columns="3" cellspacing="4" cellpadding="2">
                                    <p:commandButton action="${r"#{"}${entityName}Bean.doCreateNew${entityName}${r"}"}" value="${r"#{"}bundle.${entityName}_jsf_btn_new${r"}"}" image="ui-icon ui-icon-search" />
                                    <p:commandButton action="${r"#{"}${entityName}Bean.doEdit${entityName}${r"}"}" value="${r"#{"}bundle.${entityName}_jsf_btn_edit${r"}"}" image="ui-icon ui-icon-pencil" />
                                    <p:commandButton update="paneldata" actionListener="${r"#{"}${entityName}Bean.delete${entityName}${r"}"}" value="${r"#{"}bundle.${entityName}_jsf_btn_delete${r"}"}" image="ui-icon ui-icon-close" />

                                </h:panelGrid>


                            </f:facet>

                        </p:dataTable>
                    </p:panel>

                    <h:panelGrid columns="3">
                        <p:panel header="Export All Data">
                            <h:commandLink>
                                <p:graphicImage value="/images/excel.png" />
                                <p:dataExporter type="xls" target="table${entityName}" fileName="${entityName}" excludeColumns="0"/>
                            </h:commandLink>

                            <h:commandLink>
                                <p:graphicImage value="/images/pdf.png" />
                                <p:dataExporter type="pdf" target="table${entityName}" fileName="${entityName}" excludeColumns="0"/>
                            </h:commandLink>

                            <h:commandLink>
                                <p:graphicImage value="/images/csv.png" />
                                <p:dataExporter type="csv" target="table${entityName}" fileName="${entityName}" excludeColumns="0" />
                            </h:commandLink>

                            <h:commandLink>
                                <p:graphicImage value="/images/xml.png" />
                                <p:dataExporter type="xml" target="table${entityName}" fileName="${entityName}" excludeColumns="0" />
                            </h:commandLink>
                        </p:panel>

                        <p:panel header="Export Page Data">
                            <h:commandLink>
                                <p:graphicImage value="/images/excel.png" />
                                <p:dataExporter type="xls" target="table${entityName}" fileName="${entityName}" excludeColumns="0" pageOnly="true"/>
                            </h:commandLink>

                            <h:commandLink>
                                <p:graphicImage value="/images/pdf.png" />
                                <p:dataExporter type="pdf" target="table${entityName}" fileName="${entityName}" excludeColumns="0" pageOnly="true"/>
                            </h:commandLink>

                            <h:commandLink>
                                <p:graphicImage value="/images/csv.png" />
                                <p:dataExporter type="csv" target="table${entityName}" fileName="${entityName}" excludeColumns="0" pageOnly="true"/>
                            </h:commandLink>

                            <h:commandLink>
                                <p:graphicImage value="/images/xml.png" />
                                <p:dataExporter type="xml" target="table${entityName}" fileName="${entityName}" excludeColumns="0" pageOnly="true"/>
                            </h:commandLink>
                        </p:panel>

                    </h:panelGrid>



                </h:form>


            </div>
        </ui:define>

        <ui:insert name="table">

            <div align="left" class="entry">



            </div>

        </ui:insert>

    </ui:composition>
</html>

