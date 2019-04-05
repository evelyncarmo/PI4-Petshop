/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.DAO.Backoffice;

import alpha.pi4.petshop.modelos.Backoffice.Produto;
import alpha.pi4.petshop.Utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author giovanebarreira
 */
public class ProdutoDAO {
        public static void inserir(Produto p) throws SQLException, Exception{
        String sql = "INSERT INTO produto (nome, preco, fabricante, estoque, modelo, codigodebarras) " +
                     " VALUES (?, ?, ?, ?, ?, ?);";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection(); //Realiza a conexão com o banco de dados
            pst = conn.prepareStatement(sql); //Cria o PreparedStatement para que seja possível definir os parâmetros do INSERT
            
            //Preparando todos os parâmetros do método INSERT na ordem em que foram definidos na query
            pst.setString(1, p.getNome());
            pst.setBigDecimal(2, p.getPreco());
            pst.setString(3, p.getFabricante());
            pst.setInt(4, p.getQuantidade());
            pst.setString(5, p.getModelo());
            pst.setString(6, p.getCodBarras());
           
            
            pst.execute(); //Executando a query e realizando a inserção no banco de dados.
        }
        finally{
            if(pst != null && !pst.isClosed())
                pst.close(); //Caso o preparedStatement não esteja nullo e nem fechado, estamos fechando agora no final.
            
            if(conn != null && !conn.isClosed())
                conn.close(); //Mesma coisa para a conexão
        }
    }
        
         public static void alterar(Produto p) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE produto SET nome = ?, preco = ?, fabricante = ?, estoque = ?, modelo = ?, codigodebarras = ? WHERE idProduto = ?";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection(); //Abrindo conexão com o banco de dados
            pst = conn.prepareStatement(sql); //Cria o PreparedStatement para que seja possível definir os parâmetros do UPDATE
            
            //Preparando todos os parâmetros do comando UPDATE definido anteriormente
            //Preparando todos os parâmetros do método INSERT na ordem em que foram definidos na query
            pst.setString(1, p.getNome());
            pst.setBigDecimal(2, p.getPreco());
            pst.setString(3, p.getFabricante());
            pst.setInt(4, p.getQuantidade());
            pst.setString(5, p.getModelo());
            pst.setString(6, p.getCodBarras());
            pst.setInt(7, p.getIdProduto());
            
            pst.execute(); //Executando a instrução SQL e realizando a alteração dos dados
        }
        finally{
            if(pst != null && !pst.isClosed())
                pst.close(); //Caso o preparedStatement não esteja nullo e nem fechado, estamos fechando agora no final.
            
            if(conn != null && !conn.isClosed())
                conn.close(); //Mesma coisa para a conexão
        }
    }
    
    public static void excluir(int id) throws SQLException, ClassNotFoundException{
        String sql = "DELETE FROM produto WHERE idProduto = ?"; 
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection(); //Abrindo conexão com o banco de dados
            pst = conn.prepareStatement(sql); //Cria o PreparedStatement para que seja possível definir o parâmetro do DELETE
            
            //Preparando todos os parâmetros do comando DELETE definido anteriormente
            pst.setInt(1, id);
            
            pst.execute(); //Executando a instrução SQL e realizando a alteração dos dados
        }
        finally{
            if(pst != null && !pst.isClosed())
                pst.close(); //Caso o preparedStatement não esteja nullo e nem fechado, estamos fechando agora no final.
            
            if(conn != null && !conn.isClosed())
                conn.close(); //Mesma coisa para a conexão
        }
    }
    
    public static List<Produto> listar(String filtro) throws SQLException{
        
        String sql = "SELECT * FROM produto ";
                if(filtro.length() > 0)
                {sql = sql +"WHERE " + filtro;}
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        List<Produto> listaProduto = new LinkedList<Produto>(); //Lista de cliente que será retornada ao final do método
        
        try{
            conn = ConnectionUtils.getConnection();
            pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery(); //Executando o comando SELECT e armazenando os dados em um ResultSet
            
            while(rs.next()){ 
               Produto p = new Produto();
                
                p.setIdProduto(rs.getInt("idProduto"));
                p.setCodBarras(rs.getString("codigodebarras"));
                p.setFabricante(rs.getString("fabricante"));
                p.setModelo(rs.getString("modelo"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getBigDecimal("preco"));
                p.setQuantidade(rs.getInt("estoque"));
                
//                Produto p = new Produto();
//                f = FilialDAO.obterFilial(rs.getInt("idFilial"));
//                p.setFilial(f);
                
                listaProduto.add(p); //Com todos os dados do cliente armazenado, adiciona o cliente na lista.
            }
            
            return listaProduto; //Depois que todos os clientes estiverem na lista, retorna a lista
        }
        catch(Exception ex){
            return null;
        }
        finally{
            if(pst != null && !pst.isClosed())
                pst.close();
            
            if(conn != null && !conn.isClosed())
                conn.close();
        }
    }
    
    public static Produto obterProduto(int id) throws SQLException{
        String sql = "SELECT * FROM produto WHERE idProduto = ?";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection();
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                Produto p = new Produto();
                
                p.setIdProduto(rs.getInt("idProduto"));
                p.setCodBarras(rs.getString("codigodebarras"));
                p.setFabricante(rs.getString("fabricante"));
                p.setModelo(rs.getString("modelo"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getBigDecimal("preco"));
                p.setQuantidade(rs.getInt("estoque"));
                
                return p;
            }
        }
        catch(Exception ex){
            return null;
        }
        finally{
            if(pst != null && !pst.isClosed())
                pst.close();
            
            if(conn != null && !conn.isClosed())
                conn.close();
        }
        return null;
    }
}
