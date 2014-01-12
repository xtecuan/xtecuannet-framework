<%-- 
    Document   : process
    Created on : 01-12-2014, 01:14:58 PM
    Author     : xtecuan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="person" scope="session" class="com.googlecode.xtecuannet.framework.web.samples.sampleapp.Person" />

<jsp:setProperty name="person" property="*"/>

<c:redirect url="index.jsp"/>