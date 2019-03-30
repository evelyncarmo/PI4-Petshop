/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.DAO;

import alpha.pi4.petshop.Utils.ConnectionUtils;
import alpha.pi4.petshop.modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dud Felipe
 */
public class UsuarioDAO {
    public static void inserir(Usuario u) throws SQLException, Exception{
        //Comando de inserção no banco de dados, com alguns parâmetros a serem preparados
        String sql = "INSERT INTO usuario (cpf, nome, nascimento, telefone, email, sexo, rg, endereco, senha, tipoacesso) " +
                     " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection(); //Realiza a conexão com o banco de dados
            pst = conn.prepareStatement(sql); //Cria o PreparedStatement para que seja possível definir os parâmetros do INSERT
            
            //Preparando todos os parâmetros do método INSERT na ordem em que foram definidos na query
            pst.setString(1, u.getCpf());
            pst.setString(2, u.getNome());
            
            Timestamp t = new Timestamp(u.getDtNascimento().getTime());
            pst.setTimestamp(3, t);
            
            pst.setString(4, u.getTelefone());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getSexo() + "");
            pst.setString(7, u.getRg());
            pst.setString(8, u.getEndereco());
            pst.setString(9, u.getHashSenha());
            pst.setInt(10, u.getTipoAcesso());
            
            pst.execute(); //Executando a query e realizando a inserção no banco de dados.
        }
        finally{
            if(pst != null && !pst.isClosed())
                pst.close(); //Caso o preparedStatement não esteja nullo e nem fechado, estamos fechando agora no final.
            
            if(conn != null && !conn.isClosed())
                conn.close(); //Mesma coisa para a conexão
        }
    }
    
    public static void alterar(Usuario u) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE usuario SET cpf = ?, nome = ?, nascimento = ?, telefone = ?, email = ?, sexo = ?, rg = ?, endereco = ?, senha = ?, tipoacesso = ? WHERE idUsuario = ?"; 

        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection(); //Abrindo conexão com o banco de dados
            pst = conn.prepareStatement(sql); //Cria o PreparedStatement para que seja possível definir os parâmetros do UPDATE
            
            //Preparando todos os parâmetros do comando UPDATE definido anteriormente
            //Preparando todos os parâmetros do método INSERT na ordem em que foram definidos na query
            pst.setString(1, u.getCpf());
            pst.setString(2, u.getNome());
            
            Timestamp t = new Timestamp(u.getDtNascimento().getTime());
            pst.setTimestamp(3, t);
            
            pst.setString(4, u.getTelefone());
            pst.setString(5, u.getEmail());
            pst.setString(6, u.getSexo() + "");
            pst.setString(7, u.getRg());
            pst.setString(8, u.getEndereco());
            pst.setString(9, u.getSenha());
            pst.setInt(10, u.getTipoAcesso());
            pst.setInt(11, u.getId());
            
            pst.execute(); //Executando a instrução SQL e realizando a alteração dos dados
        }
        finally{
            if(pst != null && !pst.isClosed())
                pst.close(); //Caso o preparedStatement não esteja nullo e nem fechado, estamos fechando agora no final.
            
            if(conn != null && !conn.isClosed())
                conn.close(); //Mesma coisa para a conexão
        }
    }
    
    //Método excluir
    //Responsável por realizar a exclusão de um deterinado cliente
    public static void excluir(int id) throws SQLException, ClassNotFoundException{
        //Comando DELETE do banco de dados para realizar a exclusão de um determinado cliente utilizando o ID do mesmo
        String sql = "DELETE FROM usuario WHERE idUsuario = ?"; 
        
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
    
    //Método listar
    //Responsável por resgatar todos os clientes cadastrados e retornar uma lista com os mesmos
    public static List<Usuario> listar(boolean apenasCliente) throws SQLException, ClassNotFoundException{
        String filtro;
        //Comando SELECT para retornar todos os dados de todos os clientes
        if(apenasCliente)
            filtro = "tipoAcesso = 1";
        else
            filtro = "1=1";
        
        String sql = "SELECT * FROM usuario WHERE " + filtro;
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        List<Usuario> listaCliente = new LinkedList<Usuario>(); //Lista de cliente que será retornada ao final do método
        
        try{
            conn = ConnectionUtils.getConnection();
            pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery(); //Executando o comando SELECT e armazenando os dados em um ResultSet
            
            while(rs.next()){ //Enquanto houver registro no ResultSet, criar um cliente e adiciona-lo na lista
                Usuario c = new Usuario(); //Cria um novo cliente
                
                //Armazenando os dados do cliente contidos no ResultSet.
                //Para isso, estamos utilizando os métodos set..() da classe Cliente e os métodos get... do ResultSet passando o nome do campo no banco de dados
                c.setId(rs.getInt("idUsuario"));
                c.setNome(rs.getString("Nome"));
                c.setDtNascimento(rs.getDate("nascimento"));
                c.setCpf(rs.getString("CPF"));
                c.setRg(rs.getString("RG"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setSexo(rs.getString("sexo").charAt(0));
                c.setEndereco(rs.getString("endereco"));
                c.setSenha(rs.getString("senha"));
                c.setTipoAcesso(rs.getInt("tipoacesso"));
                
                listaCliente.add(c); //Com todos os dados do cliente armazenado, adiciona o cliente na lista.
            }
            
            return listaCliente; //Depois que todos os clientes estiverem na lista, retorna a lista
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
    
    
    public static Usuario obterUsuario(int id) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE idUsuario = ?";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection();
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                Usuario u = new Usuario();
                
                u.setId(rs.getInt("idUsuario"));
                u.setNome(rs.getString("Nome"));
                u.setDtNascimento(rs.getDate("nascimento"));
                u.setCpf(rs.getString("CPF"));
                u.setRg(rs.getString("RG"));
                u.setTelefone(rs.getString("telefone"));
                u.setEmail(rs.getString("email"));
                u.setSexo(rs.getString("sexo").charAt(0));
                u.setEndereco(rs.getString("endereco"));
                u.setSenha(rs.getString("senha"));
                u.setTipoAcesso(rs.getInt("tipoacesso"));   
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
    
    public static Usuario obterUsuario(String email) throws SQLException, Exception{
        String sql = "SELECT * FROM usuario WHERE email = ?";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection(); //Realiza a conexão com o banco de dados
            pst = conn.prepareStatement(sql); //Cria o PreparedStatement para que seja possível definir os parâmetros do INSERT
            
            //Preparando todos os parâmetros do método INSERT na ordem em que foram definidos na query
            pst.setString(1, email);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                Usuario u = new Usuario();
                
                u.setCpf(rs.getString("cpf"));
                u.setDtNascimento(rs.getDate("nascimento"));
                u.setEmail(rs.getString("email"));
                u.setEndereco(rs.getString("endereco"));
                u.setHashSenha(rs.getString("senha"));
                u.setId(rs.getInt("idUsuario"));
                u.setNome(rs.getString("nome"));
                u.setRg(rs.getString("rg"));
                u.setSexo(rs.getString("sexo").charAt(0));
                u.setTelefone(rs.getString("telefone"));
                u.setTipoAcesso(rs.getInt("tipoacesso"));
                
                return u;
            }
            
            return null;
        }
        finally{
            if(pst != null && !pst.isClosed())
                pst.close(); //Caso o preparedStatement não esteja nullo e nem fechado, estamos fechando agora no final.
            
            if(conn != null && !conn.isClosed())
                conn.close(); //Mesma coisa para a conexão
        }
    }
}
