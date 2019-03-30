<%-- 
    Document   : menu
    Created on : Nov 29, 2018, 6:05:08 PM
    Author     : senacpi3
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="sidebar">
    <div class="sidebar-header">
        <img src="assets/images/logo.png">
        <strong></strong>
    </div>

    <ul class="list-unstyled components">
        <p style="text-align: center; color: white">${sessionScope.usuario.nome}</p>

        <li>
            <a href="ProdutoServlet">
                <i class="glyphicon glyphicon-heart"></i>
                Produtos
            </a>
        </li>

        <li>
            <a href="UsuarioBackofficeServlet">
                <i class="glyphicon glyphicon-user"></i>
                Gestão de Usuários 
            </a>
        </li>

        <li>
            <a href="RelatoriosServlet">
                <i class="glyphicon glyphicon-stats"></i>
                Relatórios
            </a>
        </li>
        
        <li>
            <a href="Logout">
                <i class="glyphicon glyphicon-off"></i>
                Sair
            </a>
        </li>

    </ul>
</nav>
