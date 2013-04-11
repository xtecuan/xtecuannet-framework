<?xml version="1.0" encoding="UTF-8"?>
<#assign aDateTime = .now>
<#assign aTime = aDateTime?time>
${r"<!--"}${webXmlFile} generated by ${scriptName} version ${scriptVersion} ${r"-->"}
${r"<!--"}Created on ${aDateTime?iso_local}${r"-->"}
<web-app version="3.0" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">
    <!--##################IDENTIFICATION CONFIGURATION##############################-->
    <description>${webappDesc}</description>
    <display-name>${webappDisplayName}</display-name>
     <!--##################IDENTIFICATION CONFIGURATION END#########################-->
    
    <!--##################FACES CONFIGURATION##############################-->
    <context-param>
        <param-name>javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE</param-name>
        <param-value>true</param-value>
    </context-param>
    <context-param>
        <param-name>javax.faces.STATE_SAVING_METHOD</param-name>
        <param-value>server</param-value>
    </context-param>
    <context-param>
        <param-name>javax.faces.PROJECT_STAGE</param-name>
        <param-value>Production</param-value>
    </context-param>
    <context-param>
        <param-name>com.sun.faces.allowTextChildren</param-name>
        <param-value>true</param-value>
    </context-param>
    <servlet>
        <servlet-name>Faces Servlet</servlet-name>
        <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>${facesUrlPattern}</url-pattern>
    </servlet-mapping>
    <!--##################FACES CONFIGURATION END##############################-->
    <!--##################SESSION CONFIGURATION################################-->
    <session-config>
        <session-timeout>
            ${sessionTimeout}
        </session-timeout>
    </session-config>
    <!--##################SESSION CONFIGURATION END############################-->
    <!--##################WELCOME FILE LIST####################################-->
    <welcome-file-list>
        <welcome-file>${welcomePage}</welcome-file>
    </welcome-file-list>
    <!--##################WELCOME FILE LIST END################################-->
    <!--##################SPRINGFRAMEWORK CONFIGURATION########################-->
    <context-param>
        <description>contextConfigLocation de Spring-Framework</description>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:*/xtecuannet-framework-core-services.xml  ${springframeworkConfigFiles}</param-value>
    </context-param>
    <listener>
        <description>Listener Spring</description>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <#if exposeRemoteSpringServices>
    <servlet>
        <description>Spring Remoting Servlet</description>
        <servlet-name>remoting</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>remoting</servlet-name>
        <url-pattern>${remotingPattern}</url-pattern>
    </servlet-mapping>
    </#if>
    <!--##################SPRINGFRAMEWORK CONFIGURATION END####################-->
    <!--##################JPA JNDI CONFIGURATION###############################-->
    <persistence-unit-ref>
        <persistence-unit-ref-name>${jpaPuNameJndi}</persistence-unit-ref-name>
        <persistence-unit-name>${jpaPuName}</persistence-unit-name>
    </persistence-unit-ref>
    <!--##################JPA JNDI CONFIGURATION END###########################-->
    <!--##################PRIMEFACES CONFIGURATION#############################-->
    <context-param>
        <param-name>primefaces.PRIVATE_CAPTCHA_KEY</param-name>
        <param-value>6LfwZwoAAAAAAEhRyntKF1PBzysAJLzqp2v-GMRR</param-value>
    </context-param>
    <context-param>
        <param-name>primefaces.PUBLIC_CAPTCHA_KEY</param-name>
        <param-value>6LfwZwoAAAAAAI-oUHpdvRnkMfu9fXQHxc0P7IBu</param-value>
    </context-param>
    <context-param>
        <param-name>primefaces.THEME</param-name>
        <param-value>${r"#{guestPreferences.theme}"}</param-value>
    </context-param>
    <filter>
        <filter-name>PrimeFaces FileUpload Filter</filter-name>
        <filter-class>org.primefaces.webapp.filter.FileUploadFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>PrimeFaces FileUpload Filter</filter-name>
        <servlet-name>Faces Servlet</servlet-name>
    </filter-mapping>
    <filter>
        <filter-name>Character Encoding Filter</filter-name>
        <filter-class>org.primefaces.examples.filter.CharacterEncodingFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>Character Encoding Filter</filter-name>
        <servlet-name>Faces Servlet</servlet-name>
    </filter-mapping>
    <!--##################PRIMEFACES CONFIGURATION END#########################-->
</web-app>

