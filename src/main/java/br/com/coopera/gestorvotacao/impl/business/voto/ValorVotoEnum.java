package br.com.coopera.gestorvotacao.impl.business.voto;

import java.util.stream.Stream;

public enum ValorVotoEnum {
    SIM("S"),
    NAO("N");

    private final String valor;

    private ValorVotoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static ValorVotoEnum convert(Enum<?> e) {
        return null == e ? null : valueOf(e.name());
    }

    public static ValorVotoEnum getByValor(String valor){
        return Stream.of(values())
                .filter(voto -> valor.trim().equals(voto.valor))
                .findAny()
                .orElse(null);
    }
}
