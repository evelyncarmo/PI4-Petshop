<%-- 
    Document   : cadastroUsuario
    Created on : 26/03/2019, 13:53:51
    Author     : Dud Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="UsuarioServlet">
            <div class="form-row">
                <div class="form-group col-md-8">
                    <label for="nome">Nome</label>
                    <input type="text" class="form-control" id="nome" name="nome">
                </div>

                <div class="form-group col-md-4">
                    <label for="dtnascimento">Data de Nascimento</label>
                    <input type="text" class="form-control" id="dtnascimento" name="dtnascimento">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="cpf">CPF</label>
                    <input type="text" class="form-control" id="cpf" name="cpf">
                </div>

                <div class="form-group col-md-6">
                    <label for="sexo">Sexo</label>
                    <select id="sexo" class="form-control" name="sexo">
                        <option selected>Selecione...</option>
                        <option value="M">Masculino</option>
                        <option value="F">Feminino</option>
                    </select>
                </div>
            </div>

            <div class="form-row">

                <div class="form-group col-md-6">
                    <label for="rg">RG</label>
                    <input type="text" class="form-control" id="rg" name="rg">
                </div>

                <div class="form-group col-md-6">
                </div>

            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="telefone">Telefone</label>
                    <input type="text" class="form-control" id="telefone" name="telefone">
                </div>

                <div class="form-group col-md-6">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" id="email" name="email">
                </div>

            </div>
            
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="endereco">Endereço</label>
                    <input type="text" class="form-control" id="endereco" name="endereco">
                </div>
                
                <div class="form-group col-md-6">
                    <label for="senha">Senha</label>
                    <input type="password" class="form-control" id="senha" name="senha">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                </div>
                <div class="form-group col-md-4 text-center">
                    <button type="submit" class="btn btn-secondary btn-lg btn-block">Cadastrar</button>
                </div>
                <div class="form-group col-md-4">
                </div>
            </div>
        </form>
    </body>
</html>
