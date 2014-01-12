<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample Hello World Application</title>
    </head>
    <body>
        <h1>Sample Hello World Application</h1>

        <br>

        <c:if var="cond" test="${not empty person.firstname and not empty person.lastname}">

            <h2 style="color:blue">Welcome: ${person.completeName}</h2>

            <c:remove scope="session" var="person"/>

        </c:if>

        <c:if test="${not cond}">

            <h2 style="color:blue">Welcome: Anonymous Person</h2>
            <c:remove scope="session" var="person"/>
        </c:if>    

        <br>

        <form name="form1" action="process.jsp" method="POST">
            <table border="0" cellspacing="1" cellpadding="1">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Value</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Enter your first Name:</td>
                        <td><input type="text" name="firstname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Enter your Last Name:</td>
                        <td><input type="text" name="lastname" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Reset" /></td>
                        <td><input type="submit" value="Send" /></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
