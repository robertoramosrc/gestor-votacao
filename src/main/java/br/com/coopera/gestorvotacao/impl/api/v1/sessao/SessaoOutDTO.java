package br.com.coopera.gestorvotacao.impl.api.v1.sessao;

import br.com.coopera.gestorvotacao.impl.business.sessao.DuracaoSessaoEnum;
import br.com.coopera.gestorvotacao.impl.business.sessao.SituacaoSessaoEnum;

public class SessaoOutDTO {
    private Long id;
    private Long pautaId;
    private DuracaoSessaoEnum duracao;
    private SituacaoSessaoEnum situacao;

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

    public DuracaoSessaoEnum getDuracao() {
        return duracao;
    }

    public void setDuracao(DuracaoSessaoEnum duracao) {
        this.duracao = duracao;
    }

    public SituacaoSessaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoSessaoEnum situacao) {
        this.situacao = situacao;
    }
}
