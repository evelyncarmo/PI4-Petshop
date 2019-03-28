<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>PetShop TADES</title>

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="menu.jsp"></jsp:include>

            <div id="content">
                <nav class="navbar navbar-default">
                    <div style="font-size: 20px; text-align: center;">Gerenciar Produtos</div>
                </nav>


                <div style="margin-bottom: 15px;">
                    <button type="button" class="btn btn-success btn-lg" id="liberacadastro" >Cadastro</button>
                    <button type="button" class="btn btn-secondary btn-lg" id="liberapesquisa">Pesquisar</button>
                </div>

                <div id="cadastro">


                    <form action="
                          <c:choose>
                              <c:when test="${produto != null}">
                                  ${pageContext.request.contextPath}/AlterarProdutoServlet?id=${produto.idProduto}
                              </c:when>
                              <c:otherwise>
                                  ${pageContext.request.contextPath}/ProdutoServlet
                              </c:otherwise>
                          </c:choose>
                          " method="post" 
                          >
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="produtos">Nome</label>
                                <input type="text" class="form-control" id="produto" name="nome" value="${produto.nome}">
                            </div>

                            <div class="form-group col-md-6">
                                <label for="preco">Fabricante</label>
                                <input type="text" class="form-control" id="fabricante" name="fabricante" value="${produto.fabricante}">
                            </div>
                            
                            <div class="form-group col-md-6">
                                <label for="preco">Modelo</label>
                                <input type="text" class="form-control" id="modelo" name="modelo" value="${produto.modelo}">
                            </div>
                            
                            <div class="form-group col-md-6">
                                <label for="preco">Preço</label>
                                <input type="text" class="form-control" id="preco" name="preco" value="${produto.preco}">
                            </div>
                            
                            <div class="form-group col-md-6">
                                <label for="preco">Estoque</label>
                                <input type="text" class="form-control" id="quantidade" name="quantidade" value="${produto.quantidade}">
                            </div>
                            
                            <div class="form-group col-md-6">
                                <label for="preco">Cód. Barras</label>
                                <input type="text" class="form-control" id="quantidade" name="codbarras" value="${produto.codBarras}">
                            </div>
                            
                            <div class="form-group col-md-6">
                                <label for="preco">Filial</label>
                                <select id="inputState" class="form-control" name="filial">
                                    <option selected>Selecione...</option>
                                    <option value="1" <c:if test="${produto.getFilial().getId() == 1}">selected</c:if>>São Paulo</option>
                                    <option value="2" <c:if test="${produto.getFilial().getId() == 2}">selected</c:if>>Campina Grande</option>
                                    <option value="3" <c:if test="${produto.getFilial().getId() == 3}">selected</c:if>>Brasília</option>
                                    <option value="4" <c:if test="${produto.getFilial().getId() == 4}">selected</c:if>>Joinville</option>
                                </select>
                            </div>
                            
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                            </div>
                            <div class="form-group col-md-6 text-center">
                                <button type="submit" class="btn btn-primary btn-lg btn-block">Cadastrar</button>
                            </div>
                            <div class="form-group col-md-6">
                            </div>
                        </div>
                    </form>

                </div>

                <div id="pesquisa" style="display: none">

                    <table class="table" id="tabelaprodutos">
                        <thead>
                            <tr>
                                <th scope="col">Nome</th>
                                <th scope="col">Fabricante</th>
                                <th scope="col">Modelo</th>
                                <th scope="col">Preço</th>
                                <th scope="col">Estoque</th>
                                <th scope="col">Filial</th>
                                <th scope="col">Editar</th>
                                <th scope="col">Excluir</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${produtos}" var="prod">
                                <tr>
                                    <td><c:out value="${prod.nome}" /></td>
                                    <td><c:out value="${prod.fabricante}" /></td>
                                    <td><c:out value="${prod.modelo}" /></td>
                                    <td><c:out value="${prod.preco}" /></td>
                                    <td><c:out value="${prod.quantidade}" /></td>
                                    <td><c:out value="${prod.getFilial().getCidade()}" /></td>
                                    <td><a href="${pageContext.request.contextPath}/AlterarProdutoServlet?id=${prod.idProduto}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
                                    <td><a href="${pageContext.request.contextPath}/ExcluirProdutoServlet?id=${prod.idProduto}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>


        <script type="text/javascript">
            $(document).ready(function () {
                $('#tabelaservicos').DataTable({
                    "language": {
                        "lengthMenu": "Mostrando _MENU_ resultados por página",
                        "zeroRecords": "Nada encontrado - desculpe.",
                        "info": "Mostrando página _PAGE_ de _PAGES_",
                        "infoEmpty": "Não há dados para mostrar",
                        "infoFiltered": "(filtrado no total de _MAX_ resultados)",
                        "search": "Procurar: ",
                        "paginate": {
                            "first": "Primeiro",
                            "last": "Último",
                            "next": "Próximo",
                            "previous": "Anterior"
                        },
                    }
                });
            });

            $("#liberacadastro").on("click", function () {
                $("#cadastro").show();
                $("#pesquisa").hide();
            });

            $("#liberapesquisa").on("click", function () {
                $("#cadastro").hide();
                $("#pesquisa").show();
            });
        </script>
    </body>
</html>
