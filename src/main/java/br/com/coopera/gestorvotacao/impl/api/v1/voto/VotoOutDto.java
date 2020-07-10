package br.com.coopera.gestorvotacao.impl.api.v1.voto;

import br.com.coopera.gestorvotacao.impl.business.voto.ValorVotoEnum;

public class VotoOutDto {
    private Long id;
    private String cpf;
    private ValorVotoEnum valor;
    private Long pautataId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ValorVotoEnum getValor() {
        return valor;
    }

    public void setValor(ValorVotoEnum valor) {
        this.valor = valor;
    }

    public Long getPautataId() {
        return pautataId;
    }

    public void setPautataId(Long pautataId) {
        this.pautataId = pautataId;
    }
}
