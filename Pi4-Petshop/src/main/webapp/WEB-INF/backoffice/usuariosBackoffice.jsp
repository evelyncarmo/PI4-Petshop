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
                    <div style="font-size: 20px; text-align: center;">Gerenciar Usuários</div>
                </nav>



                <div style="margin-bottom: 15px;">
                    <button type="button" class="btn btn-secondary btn-lg" id="liberacadastro">Cadastro</button>
                    <button type="button" class="btn btn-secondary btn-lg" id="liberapesquisa">Listar</button>
                </div>

                <div id="cadastro">


                    <form action="
                          <c:choose>
                              <c:when test="${usuario != null}">
                                  ${pageContext.request.contextPath}/AlterarUsuarioServlet?id=${usuario.id}
                              </c:when>
                              <c:otherwise>
                                  ${pageContext.request.contextPath}/UsuarioBackofficeServlet
                              </c:otherwise>
                          </c:choose>
                          " method="post">
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label for="servico">Nome</label>
                                <input type="text" class="form-control" id="servico" name="nome" value="${usuario.nome}" >
                            </div>

                            <div class="form-group col-md-4">
                                <label for="calendario">Data de Nascimento</label>
                                <input type="text" class="form-control" id="calendario" name="dtnascimento" value="${usuario.dtNascimentoStr}" >
                            </div> 
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="cpf">CPF</label>
                                <input type="text" class="form-control" id="cpf" name="cpf" value="${usuario.cpf}" >
                            </div>

                            <div class="form-group col-md-6">
                                <label for="inputState">Sexo</label>
                                <select id="inputState" class="form-control" name="sexo" value="${usuario.sexo}">
                                    <option>Selecione...</option>
                                    <option value="M"
                                            <c:if test="${usuario.sexo == 'M'.charAt(0)}">selected</c:if>
                                            >Masculino</option>
                                    <option value="F" <c:if test="${usuario.sexo == 'F'.charAt(0)}">selected</c:if>>Feminino</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-row">

                            <div class="form-group col-md-6">
                                <label for="rg">RG</label>
                                <input type="text" class="form-control" id="rg" name="rg" value="${usuario.rg}">
                            </div>

                            <div class="form-group col-md-6">
                                <label for="inputState">Tipo Acesso</label>
                                <select id="inputState" class="form-control" name="tipoacesso" value="${usuario.tipoAcesso}">
                                    <option selected>Selecione...</option>
                                    <option value="1" <c:if test="${usuario.tipoAcesso == 1}">selected</c:if>>Cliente</option>
                                    <option value="2" <c:if test="${usuario.tipoAcesso == 2}">selected</c:if>>Funcionario filial</option>
                                    <option value="3" <c:if test="${usuario.tipoAcesso == 3}">selected</c:if>>Gerente filial</option>
                                    <option value="4" <c:if test="${usuario.tipoAcesso == 4}">selected</c:if>>Gerente geral</option>
                                    <option value="5" <c:if test="${usuario.tipoAcesso == 5}">selected</c:if>>TI</option>
                                    <option value="6" <c:if test="${usuario.tipoAcesso == 6}">selected</c:if>>Backoffice</option>
                                    <option value="7" <c:if test="${usuario.tipoAcesso == 7}">selected</c:if>>RH</option>
                                </select>
                            </div>

                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="telefone">Telefone</label>
                                <input type="text" class="form-control" id="telefone" name="telefone" value="${usuario.telefone}">
                            </div>
                            
                            <div class="form-group col-md-3">
                            </div>

                            <div class="form-group col-md-6">
                                <label for="endereco">Endereço</label>
                                <input type="text" class="form-control" id="endereco" name="endereco" value="${usuario.endereco}">
                            </div>

                            

                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="email">Email</label>
                                <input type="text" class="form-control" id="email" name="email" value="${usuario.email}">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="senha">Senha</label>
                                <input type="password" class="form-control" id="senha" name="senha" value="${usuario.senha}">
                            </div>

                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-4">
                            </div>
                            <div class="form-group col-md-4 text-center">
                                <button type="submit" class="btn btn-primary btn-lg btn-block">Cadastrar</button>
                            </div>
                            <div class="form-group col-md-4">
                            </div>
                        </div>

                    </form>

                </div>

                <div id="pesquisa" style="display: none">

                    <table class="table" id="tabelaservicos">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">RG</th>
                                <th scope="col">Data Nascimento</th>
                                <th scope="col">Telefone</th>
                                <th scope="col">Email</th>
                                <th scope="col">Endereço</th>
                                <th scope="col">Editar</th>
                                <th scope="col">Excluir</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${usuarios}" var="u">
                                <tr>
                                    <td scope="row"><c:out value="${u.id}" /></td>
                                    <td><c:out value="${u.nome}" /></td>
                                    <td><c:out value="${u.cpf}" /></td>
                                    <td><c:out value="${u.rg}" /></td>
                                    <td><c:out value="${u.dtNascimento}" /></td>
                                    <td><c:out value="${u.telefone}" /></td>
                                    <td><c:out value="${u.email}" /></td>
                                    <td><c:out value="${u.endereco}" /></td>

                                    <td><a href="${pageContext.request.contextPath}/AlterarUsuarioServlet?id=${u.id}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
                                    <td><a href="${pageContext.request.contextPath}/ExcluirUsuarioServlet?id=${u.id}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>


                </div>
            </div>
        </div>

        <script src="assets/js/jquery-1.12.0.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="assets/css/jquery.dataTables.css">
        <script type="text/javascript" charset="utf8" src="assets/js/jquery.dataTables.js"></script>
        <script src="assets/js/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="assets/css/jquery-ui.css" />


        <script type="text/javascript">
            $(document).ready(function () {
                //$("#calendario").datepicker();
                $( "#calendario" ).datepicker( "option", "dateFormat", "dd/mm/yy");
                $('#tabelaservicos').DataTable({
                    "language": {
                    "lengthMenu": "Mostrando _MENU_ resultados por página",
                    "zeroRecords": "Nada encontrado - desculpe.",
                    "infoEmpty": "Não há dados para mostrar",
                    "infoFiltered": "(filtrado no total de _MAX_ resultados)",
                    "search": "Procurar: ",
                    "info": "Mostrando página _PAGE_ de _PAGES_",
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