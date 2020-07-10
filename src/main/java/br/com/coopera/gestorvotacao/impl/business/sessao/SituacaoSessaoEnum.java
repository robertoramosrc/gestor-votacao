package br.com.coopera.gestorvotacao.impl.business.sessao;

import java.util.stream.Stream;

public enum SituacaoSessaoEnum {

    INATIVA("I","Inativa"),
    ABERTA("A","Aberta"),
    ENCERRADA("E","Encerrada");

    private final String valor;
    private final String descricao;

    private SituacaoSessaoEnum(String valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public static SituacaoSessaoEnum convert(Enum<?> e) {
        return null == e ? null : valueOf(e.name());
    }

    public static SituacaoSessaoEnum getByValor(String valor){
        return Stream.of(values())
                .filter(situacao -> valor.trim().equals(situacao.valor))
                .findAny()
                .orElse(null);
    }

}
