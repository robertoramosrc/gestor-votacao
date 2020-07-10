package br.com.coopera.gestorvotacao.impl.api.v1.voto;

import br.com.coopera.gestorvotacao.impl.business.sessao.SituacaoSessaoEnum;
import br.com.coopera.gestorvotacao.impl.business.voto.ResultadoVotacaoEnum;
import br.com.coopera.gestorvotacao.impl.business.voto.Voto;

import java.util.List;

public class ResultadoVotacaoDTO {
    private String pauta;
    private Long totalAFavor;
    private Long totalContra;
    private Long totalAnulado;
    private ResultadoVotacaoEnum resultado;
    private SituacaoSessaoEnum situacaoSessao;

    public String getPauta() {
        return pauta;
    }

    public void setPauta(String pauta) {
        this.pauta = pauta;
    }

    public Long getTotalAFavor() {
        return totalAFavor;
    }

    public void setTotalAFavor(Long totalAFavor) {
        this.totalAFavor = totalAFavor;
    }

    public Long getTotalContra() {
        return totalContra;
    }

    public void setTotalContra(Long totalContra) {
        this.totalContra = totalContra;
    }

    public Long getTotalAnulado() {
        return totalAnulado;
    }

    public void setTotalAnulado(Long totalAnulado) {
        this.totalAnulado = totalAnulado;
    }

    public ResultadoVotacaoEnum getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoVotacaoEnum resultado) {
        this.resultado = resultado;
    }

    public SituacaoSessaoEnum getSituacaoSessao() {
        return situacaoSessao;
    }

    public void setSituacaoSessao(SituacaoSessaoEnum situacaoSessao) {
        this.situacaoSessao = situacaoSessao;
    }
}
