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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dud Felipe
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        try{
            Usuario u = UsuarioBLL.obterUsuario(email);
            if(u != null){
                boolean senhaValida = u.validarSenha(senha);
                if(senhaValida){
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("usuario", u);
                    
                    response.sendRedirect("index.jsp");
                    return;
                }
            }
            
            request.setAttribute("msgErro", "Usuário ou senha inválidos!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
