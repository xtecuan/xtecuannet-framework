<%-- 
    Document   : procesar
    Created on : Oct 4, 2012, 1:53:00 PM
    Author     : xtecuan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="alumno" scope="session" class="org.xtecuan.samples.dto.Alumnos" />

<jsp:setProperty name="alumno" property="*" />

<jsp:useBean id="urlHelper" scope="page" class="org.xtecuan.samples.beans.UrlHelper" />


<c:if var="fecha" test="${param.dia >0  and  param.mes >0  and   param.annio >0}">

    <jsp:useBean id="dateHelper" scope="page" class="org.xtecuan.samples.beans.DateHelper" />

    <c:set target="${dateHelper}" property="dateStr" value="${param.dia}_${param.mes}_${param.annio}"/>

    <c:set target="${alumno}" property="fechanac" value="${dateHelper.dateFromStr}"/>

</c:if>

<c:redirect  url="${urlHelper.url}">
    <c:param name="action" value="${param.action}"/>
</c:redirect>

