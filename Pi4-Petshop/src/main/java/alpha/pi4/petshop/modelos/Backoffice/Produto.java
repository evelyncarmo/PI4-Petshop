/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.pi4.petshop.modelos.Backoffice;

import java.math.BigDecimal;

/**
 *
 * @author giovanebarreira
 */
public class Produto {
    
    private int idProduto;
    private String nome;
    private BigDecimal preco;
    private String fabricante;
    private int quantidade;
    private String modelo;
    private String codBarras;
   
    public Produto(){
        
    }
    
     public Produto(String nome, BigDecimal preco, String fabricante, int quantidade, String modelo, String codBarras) {
        this.nome = nome;
        this.preco = preco;
        this.fabricante = fabricante;
        this.quantidade = quantidade;
        this.modelo = modelo;
        this.codBarras = codBarras;
       
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }
     
     
    
}
