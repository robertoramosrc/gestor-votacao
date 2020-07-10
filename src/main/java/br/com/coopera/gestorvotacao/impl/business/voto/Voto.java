package br.com.coopera.gestorvotacao.impl.business.voto;

public class Voto {
    private Long id;
    private String cpf;
    private ValorVotoEnum valor;
    private Long sessaoId;
    private Long pautaId;

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

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }
}
