package br.com.coopera.gestorvotacao.impl.business.voto;

import java.util.List;

public class ResultadoVotacao {
    private String pauta;
    private ResultadoFinalVotacaoEnum resultado;
    private Long totalAFavor;
    private Long totalContra;
    private List<Voto> votos;

    public String getPauta() {
        return pauta;
    }

    public void setPauta(String pauta) {
        this.pauta = pauta;
    }

    public ResultadoFinalVotacaoEnum getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoFinalVotacaoEnum resultado) {
        this.resultado = resultado;
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

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }
}
