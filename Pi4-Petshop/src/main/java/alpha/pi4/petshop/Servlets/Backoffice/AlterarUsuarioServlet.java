/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.Servlets.Backoffice;

import alpha.pi4.petshop.BLL.UsuarioBLL;
import alpha.pi4.petshop.modelos.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dud Felipe
 */
@WebServlet(name = "AlterarUsuarioServlet", urlPatterns = {"/AlterarUsuarioServlet"})
public class AlterarUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //No momento que o usuário pressionar em "Alterar" na hora da listagem dos clientes,
        //o processamento será enviado para esse método.
        
        //Ao clicar em "Alterar", o valor do ID do cliente está sendo enviado como um parâmetro via URL.
        //Sendo assim, é possível utilizar o método request.getParameter() para armazenar esse valor para
        //futuramente realizar a buscar do cliente correto e realizar a alteração no cadastro
        int id = Integer.parseInt(request.getParameter("id"));
        
        boolean manutencao = false; //Como não deve ser exibida a listagem dos dados, passamos a flag para falso
        
        Usuario u = null;
        List<Usuario> usuarios = null;
        
        try{
            u = UsuarioBLL.obterUsuario(id); //O cliente é obtido através do método obterCliente(), passando o ID resgatado por parâmetro
            usuarios = UsuarioBLL.listar();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        //Com os dados do cliente em mãos, podemos enviá-lo como atributo para a página cliente.jsp para que seja feita a alteração do seu cadastro
        request.setAttribute("usuario", u);
        request.setAttribute("manutencao", manutencao);
        request.setAttribute("usuarios", usuarios);
        
        //Caso não ocorra nenhum erro, a página cliente.jsp deve ser exibida com o formulário preenchido com os dados atuais do cliente
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/backoffice/usuariosBackoffice.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        //Com a alteração do cadastro realizada, o processamento será enviado para esse método.
        
        //A primeira coisa que deve ser feita é armazenar novamente o ID do cliente que foi alterado
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario u = null;
        List<Usuario> usuarios = null;
        
        try{
            u = UsuarioBLL.obterUsuario(id); //Com isso, obtemos novamente o cliente para que seja realizado de fato a alteração de seu cadastro
            
            //É feito o armazenamento dos dados que foram preenchidos no formulário, da mesma forma que foi feito no servlet UsuarioServlet
            u.setNome(request.getParameter("nome"));
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            u.setDtNascimento(sdf.parse(request.getParameter("dtnascimento")));
            
            u.setCpf(request.getParameter("cpf"));
            u.setSexo(request.getParameter("sexo").charAt(0));
            u.setRg(request.getParameter("rg"));
            u.setTelefone(request.getParameter("telefone"));
            u.setEmail(request.getParameter("email"));
            u.setEndereco(request.getParameter("endereco"));
            u.setSenha(request.getParameter("senha"));
            u.setTipoAcesso(Integer.parseInt(request.getParameter("tipoacesso")));
            
            //Depois de armazenar os novos dados do cliente, é chamado o método de validação alterar().
            //Esse método, por sua vez, chama o método responsável por realizar a alteração no banco de dados (caso todos os dados estejam válidos)
            UsuarioBLL.alterar(u);
            
          
            
            //Por fim, a página cliente.jsp é apresentada novamente ao usuário.
            response.sendRedirect(request.getContextPath() + "/UsuarioBackofficeServlet");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
