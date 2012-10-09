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
        <script src="${pageContext.servletContext.contextPath}/js/functions.js"></script>
        <link href="${pageContext.servletContext.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
    </head>
    <body onload="populateGrid();">
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


            <div align="center">
                <table  border="0" width="600" cellspacing="1" cellpadding="1">

                    <tbody>

                    <input type="hidden" name="id" value="${alumno.id}"/> 

                    <tr>
                        <td>Carnet</td>
                        <td><input type="text" name="carnet" value="${alumno.carnet}" /></td>
                    </tr>
                    <tr>
                        <td>Nombres</td>
                        <td><input type="text" name="nombres" value="${alumno.nombres}" /></td>
                    </tr>
                    <tr>
                        <td>Apellidos</td>
                        <td><input type="text" name="apellidos" value="${alumno.apellidos}" /></td>
                    </tr>
                    <tr>
                        <td>Correo</td>
                        <td><input type="text" name="correo" value="${alumno.correo}" /></td>
                    </tr>
                    <jsp:useBean id="helper" scope="page" class="org.xtecuan.samples.beans.DateHelper" />

                    <c:if test="${not empty alumno.fechanac}" var="fechabool">
                        <c:set target="${helper}" property="dateObj" value="${alumno.fechanac}"/>
                    </c:if>
                    <tr>
                        <td>Fecha de Nacimiento</td>
                        <td>Dia: <select name="dia">
                                <option value="0">Seleccione</option>
                                <c:forEach var="i"  begin="1" end="31" step="1" varStatus="g">
                                    <option value="${i}">${i}</option>
                                    <c:if test="${fechabool and i eq helper.day}" var="dayBool">
                                        <c:set var="dayIndex" scope="page" value="${g.index}"/>
                                    </c:if>

                                </c:forEach>
                            </select>
                            <script>
                                <c:if test="${empty dayIndex}">
                                    <c:set var="dayIndex" scope="page" value="0"/>
                                </c:if>
                                    setIndexInCombo('forma','dia',${dayIndex});
                            </script>
                            Mes: <select name="mes">
                                <option value="0">Seleccione</option>
                                <c:forEach var="item"  items="${helper.months}" varStatus="i">
                                    <option value="${i.index+1}">${item}</option>
                                    <c:if test="${fechabool and (i.index+1) eq helper.month}" var="monthBool">
                                        <c:set var="monthIndex" scope="page" value="${i.index+1}"/>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <script>
                                <c:if test="${empty monthIndex}">
                                    <c:set var="monthIndex" scope="page" value="0"/>
                                </c:if>
                                    setIndexInCombo('forma','mes',${monthIndex});
                            </script>
                            Año:<select name="annio">
                                <option value="0">Seleccione</option>
                                <c:forEach var="i"  begin="1900" end="${helper.currentYear}" step="1">
                                    <option value="${i}">${i}</option>
                                    <c:if test="${fechabool and i eq helper.year}" var="yearBool">
                                        <c:set var="yearIndex" scope="page" value="${helper.year-1900+1}"/>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <script>
                                <c:if test="${empty yearIndex}">
                                    <c:set var="yearIndex" scope="page" value="0"/>
                                </c:if>
                                    setIndexInCombo('forma','annio',${yearIndex});
                            </script>
                        </td>
                    </tr>
                    <c:if test="${not empty alumno.fechanac}" var="fechaboolXX">
                        <c:remove scope="page" var="helper"/>
                    </c:if>
                    <tr class="botones">
                        <td>
                            <input type="submit" value="Enviar" />
                        </td>
                        <td>
                            <input type="reset" value="Limpiar" />
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </form>


        <c:if test="${not empty alumno}">

            <c:remove scope="session" var="alumno"/>

        </c:if>        

        <div id="grid">
            <c:forEach items="${alumnos}" var="current" varStatus="g">
                ${current}
            </c:forEach>
        </div>
    </body>
</html>
