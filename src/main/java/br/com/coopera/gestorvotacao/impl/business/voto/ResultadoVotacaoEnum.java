package br.com.coopera.gestorvotacao.impl.business.voto;

public enum ResultadoVotacaoEnum {
    PAUTA_APROVADA,
    PAUTA_REPROVADA;

    public static ResultadoVotacaoEnum convert(Enum<?> e) {
        return null == e ? null : valueOf(e.name());
    }
}
