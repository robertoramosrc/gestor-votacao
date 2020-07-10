package br.com.coopera.gestorvotacao.impl.api.v1.sessao;

import br.com.coopera.gestorvotacao.impl.business.sessao.DuracaoSessaoEnum;

public class SessaoInDto {
    private Long pautaId;
    private DuracaoSessaoEnum duracao;

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public DuracaoSessaoEnum getDuracao() {
        return duracao;
    }

    public void setDuracao(DuracaoSessaoEnum duracao) {
        this.duracao = duracao;
    }
}
