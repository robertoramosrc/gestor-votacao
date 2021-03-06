package br.com.coopera.gestorvotacao.impl.business.voto;

import br.com.coopera.gestorvotacao.impl.business.sessao.SituacaoSessaoEnum;

import java.util.List;

public class ResultadoVotacao {
    private String pauta;
    private Long totalAFavor;
    private Long totalContra;
    private Long totalAnulado;
    private List<Voto> votos;
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

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
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
