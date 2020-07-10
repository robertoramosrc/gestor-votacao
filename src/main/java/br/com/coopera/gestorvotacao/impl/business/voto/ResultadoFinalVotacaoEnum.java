package br.com.coopera.gestorvotacao.impl.business.voto;

public enum ResultadoFinalVotacaoEnum {
    PAUTA_APROVADA,
    PAUTA_REPROVADA;

    public static ResultadoFinalVotacaoEnum convert(Enum<?> e) {
        return null == e ? null : valueOf(e.name());
    }
}
