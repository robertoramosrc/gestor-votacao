package br.com.coopera.gestorvotacao.impl.business.sessao;

import java.time.LocalDateTime;

public class Sessao {
    private Long id;
    private Long pautaId;
    private SituacaoSessaoEnum situacao;
    private DuracaoSessaoEnum duracao;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataEncerramento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public SituacaoSessaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoSessaoEnum situacao) {
        this.situacao = situacao;
    }

    public DuracaoSessaoEnum getDuracao() {
        return duracao;
    }

    public void setDuracao(DuracaoSessaoEnum duracao) {
        this.duracao = duracao;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }
}