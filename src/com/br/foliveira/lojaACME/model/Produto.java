package com.br.foliveira.lojaACME.model;

import java.math.BigDecimal;
//import java.nio.file.Path;

public class Produto {
    
    private String nome;
    private String filePath;
    private BigDecimal preco;

    public Produto(String nome, String filePath, BigDecimal preco){
        this.nome = nome;
        this.filePath = filePath;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " - " + preco;
    }
}
