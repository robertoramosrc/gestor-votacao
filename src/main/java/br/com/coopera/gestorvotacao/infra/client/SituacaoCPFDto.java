package br.com.coopera.gestorvotacao.infra.client;

public class SituacaoCPFDto {
    private SituacaoCPFParaVotacaoEnum status;

    public SituacaoCPFParaVotacaoEnum getStatus() {
        return status;
    }

    public void setStatus(SituacaoCPFParaVotacaoEnum status) {
        this.status = status;
    }

    public boolean isHabilitado(){
        return status.equals(SituacaoCPFParaVotacaoEnum.ABLE_TO_VOTE);
    }
}
