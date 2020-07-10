package br.com.coopera.gestorvotacao.infra.client;

public enum SituacaoCPFParaVotacaoEnum {
    ABLE_TO_VOTE,
    UNABLE_TO_VOTE;
    public static SituacaoCPFParaVotacaoEnum convert(Enum<?> e) {
        return null == e ? null : valueOf(e.name());
    }
}
