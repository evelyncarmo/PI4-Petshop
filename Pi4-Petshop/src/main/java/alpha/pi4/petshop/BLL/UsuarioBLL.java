/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.BLL;

import alpha.pi4.petshop.DAO.UsuarioDAO;
import alpha.pi4.petshop.modelos.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Dud Felipe
 */
public class UsuarioBLL {
    //Método inserir, responsável por validar os dados de um novo cliente antes dos dados serem enviados para o banco de dados
    public static void inserir(Usuario u) throws Exception{
        
        if(u.getNome().trim().length() == 0){
            throw new Exception("O nome deve ser preenchido!");
        }
        if(u.getDtNascimento().toString().trim().length() == 0){
            throw new Exception("A data de nascimento deve ser preenchida!");
        }
        if(u.getCpf().trim().length() == 0){
            throw new Exception("O CPF deve ser preenchido!");
        }
        if(u.getRg().trim().length() == 0){
            throw new Exception("O RG deve ser preenchido!");
        }
        if(u.getTelefone().trim().length() == 0){
            throw new Exception("O telefone deve ser preenchido!");
        }
        if(u.getEmail().trim().length() == 0){
            throw new Exception("O email deve ser preenchido!");
        }
        if(u.getSexo() != 'M' && u.getSexo() != 'F'){
            throw new Exception("O sexo deve ser selecionado!");
        }
        
        //Caso todos os dados estejam corretos, o cliente é enviado para o método inserir da classe ClienteDAO.
        //Essa classe é responsável por realizar operações no banco de dados.
        UsuarioDAO.inserir(u);
    }
    
    //Método alterar.
    //Valida todos os dados antes de serem enviados para alteração no banco de dados.
    public static void alterar(Usuario u) throws Exception{
        if(u.getNome().trim().length() == 0){
            throw new Exception("O nome deve ser preenchido!");
        }
        if(u.getDtNascimento().toString().trim().length() == 0){
            throw new Exception("A data de nascimento deve ser preenchida!");
        }
        if(u.getCpf().trim().length() == 0){
            throw new Exception("O CPF deve ser preenchido!");
        }
        if(u.getRg().trim().length() == 0){
            throw new Exception("O RG deve ser preenchido!");
        }
        if(u.getTelefone().trim().length() == 0){
            throw new Exception("O telefone deve ser preenchido!");
        }
        if(u.getEmail().trim().length() == 0){
            throw new Exception("O email deve ser preenchido!");
        }
        if(u.getSexo() != 'M' && u.getSexo() != 'F'){
            throw new Exception("O sexo deve ser selecionado!");
        }
        
        //Caso todos os dados estejam corretos, o cliente é enviado para o método alterar() da classe ClienteDAO.
        //Nesse momento, a alteração é realizada no banco de dados.
        UsuarioDAO.alterar(u); 
    }
    
    //Método excluir.
    //Responsável por realizar a exclusão de algum cadastro no banco de dados
    public static void excluir(int id) throws SQLException, ClassNotFoundException{
        UsuarioDAO.excluir(id);
    }
    
    //Método listar, responsável por listar todos os clientes cadastrados
    public static List<Usuario> listar() throws SQLException, ClassNotFoundException{
        return UsuarioDAO.listar(false);
    }
    
    public static Usuario obterUsuario(String email) throws Exception{
        return UsuarioDAO.obterUsuario(email);
    }
    
    public static Usuario obterUsuario(int id) throws SQLException{
        return UsuarioDAO.obterUsuario(id);
    }
}
