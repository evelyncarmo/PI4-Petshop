/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.Servlets.Backoffice;

import alpha.pi4.petshop.BLL.Backoffice.ProdutoBLL;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giovanebarreira
 */
@WebServlet(name = "ExcluirProdutoServlet", urlPatterns = {"/ExcluirProdutoServlet"})
public class ExcluirProdutoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        try{
            System.out.println("TESTEEEEE 1");
            //Com o ID correto armazenado, o método excluir() é chamado para que seja feita a exclusão desse cliente.
            ProdutoBLL.excluir(id);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println("TESTEEEEE 2");
        response.sendRedirect(request.getContextPath() + "/ProdutoServlet");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
