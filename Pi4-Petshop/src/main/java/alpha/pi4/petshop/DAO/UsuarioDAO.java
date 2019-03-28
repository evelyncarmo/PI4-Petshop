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
