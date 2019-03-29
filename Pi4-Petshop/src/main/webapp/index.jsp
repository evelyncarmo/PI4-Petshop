<%-- 
    Document   : index.jsp
    Created on : 28/03/2019, 10:30:50
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
        <c:choose>
            <c:when test="${usuario == null}">
                <a href="UsuarioServlet">Cadastrar</a> | <a href="LoginServlet">Entrar</a>
            </c:when>
                <c:otherwise>
                    <p>Olá <c:out value="${usuario.nome}"/></p> 
                    <a href="#">Sair</a>
                </c:otherwise>
        </c:choose>
        | <a href="ProdutoServlet">Produtos</a>
    </body>
</html>
