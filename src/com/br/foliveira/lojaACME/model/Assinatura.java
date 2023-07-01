package com.br.foliveira.lojaACME.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class Assinatura {
    
    private BigDecimal mensalidade;
    private LocalDateTime inicio;
    private Optional<LocalDateTime> fim;
    private Cliente cliente;

    public Assinatura(BigDecimal mensalidade, LocalDateTime inicio, Cliente cliente){
        this.mensalidade = mensalidade;
        this.inicio = inicio;
        this.cliente = cliente;
        this.fim = Optional.empty();
    }

    public Assinatura(BigDecimal mensalidade, LocalDateTime inicio, Cliente cliente, LocalDateTime fim){
        this.mensalidade = mensalidade;
        this.inicio = inicio;
        this.cliente = cliente;
        this.fim = Optional.of(fim);
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }
    
    public void setMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public Optional<LocalDateTime> getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = Optional.of(fim);
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return cliente.getNome() + " - " + mensalidade + " - " + inicio + " - " + fim;
    }
}
