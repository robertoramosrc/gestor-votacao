package br.com.coopera.gestorvotacao.impl.api.v1.voto;

import br.com.coopera.gestorvotacao.impl.business.voto.ValorVotoEnum;

public class VotoInDTO {
    private String CPF;
    private ValorVotoEnum valor;
    private Long pautaId;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public ValorVotoEnum getValor() {
        return valor;
    }

    public void setValor(ValorVotoEnum valor) {
        this.valor = valor;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }
}
