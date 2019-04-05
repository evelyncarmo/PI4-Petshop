/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.Servlets;

import alpha.pi4.petshop.BLL.ProdutoBLL;
import alpha.pi4.petshop.modelos.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
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
 * @author giovanebarreira
 */
@WebServlet(name = "ProdutoServlet", urlPatterns = {"/ProdutoServlet"})
public class ProdutoServlet extends HttpServlet {

      // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Produto> produtos = null;
        
        try{
            produtos = ProdutoBLL.listar("");
        }catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
        
        request.setAttribute("produtos",produtos);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Produtos.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Produto p = new Produto();
        List<Produto> produtos = null;
        
        p.setNome(request.getParameter("nome"));
        p.setPreco(BigDecimal.valueOf(Double.parseDouble(request.getParameter("preco"))));
        p.setFabricante(request.getParameter("fabricante"));
        p.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        p.setModelo(request.getParameter("modelo"));
        p.setCodBarras(request.getParameter("codbarras"));
        
//        Filial f = new Filial();
//        try {
//            f = FilialDAO.obterFilial(Integer.parseInt(request.getParameter("filial")));
//        } catch (SQLException ex) {
//            Logger.getLogger(ServicoServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
//        p.setFilial(f);
        
        
        try{
            ProdutoBLL.inserir(p);
            produtos = ProdutoBLL.listar("");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath() + "/ProdutoServlet");
    }

}


