/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.Servlets;

import alpha.pi4.petshop.BLL.UsuarioBLL;
import alpha.pi4.petshop.modelos.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dud Felipe
 */
public class UsuarioServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/cadastroUsuario.jsp");
        dispatcher.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario u = new Usuario();
        
        u.setCpf(request.getParameter("cpf"));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            u.setDtNascimento(sdf.parse(request.getParameter("dtnascimento")));
        } catch (ParseException ex) {
            
        }
        
        u.setEmail(request.getParameter("email"));
        u.setEndereco(request.getParameter("endereco"));
        u.setNome(request.getParameter("nome"));
        u.setRg(request.getParameter("rg"));
        u.setSenha(request.getParameter("senha"));
        u.setSexo(request.getParameter("sexo").charAt(0));
        u.setTelefone(request.getParameter("telefone"));
        u.setTipoAcesso(1); //Acesso para cliente = 1
        
        try{
            UsuarioBLL.inserir(u);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/cadastroUsuario.jsp");
        dispatcher.forward(request, response);
    }
}
