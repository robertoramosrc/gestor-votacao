package br.com.coopera.gestorvotacao.impl.business.sessao;

import java.util.stream.Stream;

public enum DuracaoSessaoEnum {
    UM_MINUTO ("UM_MINUTO", "Um minuto de duração"),
    MEIA_HORA("MEIA_HORA","Meia hora de duração"),
    UMA_HORA("UMA_HORA","Uma hora de duração"),
    UM_DIA("UM_DIA","Um dia de duração"),
    CINCO_DIAS("CINCO_DIAS","Cinco dias de duração");

    private String valor;
    private String descricao;

    private DuracaoSessaoEnum(String valor, String descricao) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getValor() {
        return valor;
    }

    public static DuracaoSessaoEnum convert(Enum<?> e) {
        return null == e ? null : valueOf(e.name());
    }

    public static DuracaoSessaoEnum getByValor(String valor){
        return Stream.of(values())
                .filter(duracao -> valor.trim().equals(duracao.valor))
                .findAny()
                .orElse(null);
    }
}
