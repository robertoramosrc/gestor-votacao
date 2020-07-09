package br.com.coopera.gestorvotacao.impl.api.v1.pauta;

import javax.validation.constraints.NotNull;

public class PautaInDTO {
    @NotNull(message = "O Nome da pauta precisa ser informado")
    private String nome;

    @NotNull(message = "Uma Descrição da pauta deve ser informada")
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
