<%-- 
    Document   : login
    Created on : 28/03/2019, 10:33:18
    Author     : Dud Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${msgErro != null}">
            <p style="background-color: plum">
                <c:out value="${msgErro}" />
            </p>
        </c:if>
            
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <div>
                <label>Email</label>
                <input type="text" name="email"/>
            </div>
            <div>
                <label>Senha</label>
                <input type="password" name="senha"/>
            </div>
            <button type="submit">Entrar</button>
        </form>
    </body>
</html>
