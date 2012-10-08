<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : Oct 4, 2012, 12:09:34 PM
    Author     : xtecuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantto Tabla de Alumnos</title>
    </head>
    <body>
        <h1>Mantto. Tabla de Alumnos</h1>

        <c:if test="${not empty outmsg}">

            <h6 style="color: blue"><c:out value="${outmsg}"/></h6>

            <c:remove scope="session" var="outmsg"/>

        </c:if>


        <c:if test="${not empty errormsg}">

            <h6 style="color: red"><c:out value="${errormsg}"/></h6>

            <c:remove scope="session" var="errormsg"/>

        </c:if>


        <form name="forma" action="${pageContext.servletContext.contextPath}/procesar.jsp" method="POST">

            <c:if test="${empty param.action}" var="fff">

                <input type="hidden" name="action" value="add"/>

            </c:if>

            <c:if test="${not fff}" var="fff1">

                <input type="hidden" name="action" value="${param.action}"/>

            </c:if>


            <table border="0" width="800" cellspacing="1" cellpadding="1">

                <tbody>

                    <tr>
                        <td>Carnet</td>
                        <td><input type="text" name="carnet" value="" /></td>
                    </tr>
                    <tr>
                        <td>Nombres</td>
                        <td><input type="text" name="nombres" value="" /></td>
                    </tr>
                    <tr>
                        <td>Apellidos</td>
                        <td><input type="text" name="apellidos" value="" /></td>
                    </tr>
                    <tr>
                        <td>Correo</td>
                        <td><input type="text" name="correo" value="" /></td>
                    </tr>
                    <tr>
                        <td>Fecha de Nacimiento</td>
                        <td>Dia: <select name="dia">
                                <option value="0">Seleccione</option>
                                <c:forEach var="i"  begin="1" end="31" step="1">

                                    <option value="${i}">${i}</option>

                                </c:forEach>
                            </select>
                            <jsp:useBean id="helper" scope="page" class="org.xtecuan.samples.beans.DateHelper" />
                            Mes: <select name="mes">
                                <option value="0">Seleccione</option>
                                <c:forEach var="item"  items="${helper.months}" varStatus="i">

                                    <option value="${i.index+1}">${item}</option>
                                </c:forEach>
                            </select>
                            Año:<select name="annio">
                                <option value="0">Seleccione</option>

                                <c:forEach var="i"  begin="1900" end="${helper.currentYear}" step="1">

                                    <option value="${i}">${i}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Enviar" />
                        </td>
                        <td>
                            <input type="reset" value="Limpiar" />
                        </td>
                    </tr>
                </tbody>
            </table>


        </form>


        <c:if test="${not empty alumno}">

            <c:remove scope="session" var="alumno"/>

        </c:if>                       
    </body>
</html>
