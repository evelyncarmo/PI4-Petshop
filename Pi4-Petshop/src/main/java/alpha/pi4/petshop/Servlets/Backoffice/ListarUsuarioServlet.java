/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.Servlets.Backoffice;

import alpha.pi4.petshop.BLL.UsuarioBLL;
import alpha.pi4.petshop.modelos.Usuario;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "ListaUsuarioServlet", urlPatterns = {"/ListaUsuarioServlet"})
public class ListarUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Quando o usuário clicar no link "Listagem", o processamento será enviado para o método doGet da classe ListarUsuarioServlet (esta classe)
        //Sendo assim, a flag 'manutencao' é criada e atribuida o valor TRUE para que a página cliente.jsp saiba que deve ser montado a tabela de listagem
        //ao invés de montar o formulário para inserção de dados
        boolean manutencao = true;
        
        List<Usuario> usuarios = null; //É criado uma lista de clientes para que sejam exibidos na página cliente.jsp
        try{
            usuarios = UsuarioBLL.listar(); //É chamado o método listar que irá montar a lista com os clientes já existentes
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        //Para que a página cliente.jsp saiba o que deve ser montado e como deve ser montado,
        //estamos enviando como atributo a flag manutencao (irá fazer com que seja montado a listagem dos clientes)
        //e a lista de clientes que servirá para o forEach montar a tabela com todos os clientes cadastrados
        request.setAttribute("manutencao", manutencao);
        request.setAttribute("usuarios", usuarios);
        
        //Após isso, chamamos novamente a página cliente.jsp para que seja feita a apresentação da página correta ao usuário
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/backoffice/usuariosBackoffice.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }
}
