/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.BLL;

import alpha.pi4.petshop.DAO.UsuarioDAO;
import alpha.pi4.petshop.modelos.Usuario;

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
    
    public static Usuario obterUsuario(String email) throws Exception{
        return UsuarioDAO.obterUsuario(email);
    }
}
