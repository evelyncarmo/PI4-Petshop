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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "UsuarioBackofficeServlet", urlPatterns = {"/UsuarioBackofficeServlet"})
public class UsuarioBackofficeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Flag manutenção. Essa flag está sendo utilizada para que a página cliente.jsp saiba quando
        //montar o formulário para inserção dos dados ou montar a listagem dos clientes já cadastrados.
        //Sendo assim, quando manutencao == FALSE, deverá ser montado o formulário. Caso contrário, será montada a listagem.
        //Ele começa com FALSE para que seja montado o formulário por padrão toda vez que a página for executada pela primeira vez
        boolean manutencao = false; 
        
        //Essa flag é então enviada como um atributo para a página que será chamada, que no caso é a página cliente.jsp
        List<Usuario> usuarios = null; //É criado uma lista de clientes para que sejam exibidos na página cliente.jsp
        try{
            usuarios = UsuarioBLL.listar(); //É chamado o método listar que irá montar a lista com os clientes já existentes
        }
        catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
        
        request.setAttribute("manutencao", manutencao);
        request.setAttribute("usuarios", usuarios);
        
        
        //Enviando a requisição para a página cliente.jsp, nesse momento, a página será exibida no navegador do usuário,
        //mostrando o formulario para inserção dos dados de um novo cliente.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/backoffice/usuariosBackoffice.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Depois que o formulário for preenchido e o botão "Cadastrar" foi pressionado, 
        //(caso o usuário esteja inserindo um novo cliente e não alterando um já existente) 
        //o processamento dos dados será enviado para esse método
        
        Usuario u = new Usuario(); //Sendo assim, criaremos um novo cliente para ser cadastrado
        List<Usuario> usuarios = null;
        
        
        //Utilizando o método request.getParameter(), é possível resgatar o valor que foi digitado nos campos do
        //formulário lá na página cliente.jsp.
        //Para que seja pego o valor do campo correto, lá no formulário foi definido o atributo 'name' para todos os inputs.
        //É exatamente o valor desse atributo que tem que ser passado para o método getParameter();
        
        u.setNome(request.getParameter("nome")); //Armazenando o nome inserido no formulário
        
        //Criando um formatador de Data para que seja aceita somente data inserida no padrão utilizado por nós.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            //Para armazenar a data, foi necessário realizar um parse() pois tudo que é pego pelo método request.getParameter() é uma String.
            u.setDtNascimento(sdf.parse(request.getParameter("dtnascimento")));
        } catch (ParseException ex) {
            
        }
        
        //Armazenando os demais valores inseridos no formulário
        u.setCpf(request.getParameter("cpf"));
        u.setRg(request.getParameter("rg"));
        u.setTelefone(request.getParameter("telefone"));
        u.setEmail(request.getParameter("email"));
        u.setSexo(request.getParameter("sexo").charAt(0));
        u.setEndereco(request.getParameter("endereco"));
        u.setSenha(request.getParameter("senha"));
        u.setTipoAcesso(Integer.parseInt(request.getParameter("tipoacesso")));
        
        try{
            UsuarioBLL.inserir(u); //Com todos os dados armazenados, o cliente é enviado para a classe de validação, no método inserir().
            usuarios = UsuarioBLL.listar();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        request.setAttribute("usuarios", usuarios);
        
        //Depois de todo o processamento, caso não ocorra nenhum erro, a requisição é enviada novamente para a página cliente.jsp
        //para que o usuário possa realizar outra inserção se desejar.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/backoffice/usuariosBackoffice.jsp");
        dispatcher.forward(request, response);
    }
}
