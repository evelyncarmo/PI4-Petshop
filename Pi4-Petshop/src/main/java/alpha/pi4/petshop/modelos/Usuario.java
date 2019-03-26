/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dud Felipe
 */
public class Usuario {
    private int id;
    private String nome;
    private Date dtNascimento;
    private String cpf;
    private String rg;
    private String telefone;
    private String email;
    private String endereco;
    private String senha;
    
    /**
     * tipoAcesso = 1 -> Acesso de Cliente
     * tipoAcesso = 2 -> Acesso de Funcionário de filial
     * tipoAcesso = 3 -> Acesso de Funcionário Gerente de filial
     * tipoAcesso = 4 -> Acesso de Funcionário Gerente Regional
     * tipoAcesso = 5 -> Acesso de Funcionário TI
     * tipoAcesso = 6 -> Acesso de Funcionário Backoffice
     * tipoAcesso = 7 -> Acesso de Funcionário RH
     */
    private int tipoAcesso;
    private char sexo;
    
    public Usuario() {
        
    }
    
    public Usuario(int id, String nome, Date dtNascimento, String cpf, String rg, String telefone, String email, String endereco, String senha, int tipoAcesso, char sexo) {
        this.id = id;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.senha = senha;
        this.tipoAcesso = tipoAcesso;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getDtNascimentoStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(this.dtNascimento);
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(int tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
