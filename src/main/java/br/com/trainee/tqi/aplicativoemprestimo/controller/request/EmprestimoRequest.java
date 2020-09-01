package br.com.trainee.tqi.aplicativoemprestimo.controller.request;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmprestimoRequest implements Serializable {

    private int usuarioId;
    private int id;
    private BigDecimal valor;

    public EmprestimoRequest(int usuarioId, BigDecimal valor, int id) {
        this.valor = valor;
        this.usuarioId = usuarioId;
        this.id = id;
    }

    public EmprestimoRequest() {
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
