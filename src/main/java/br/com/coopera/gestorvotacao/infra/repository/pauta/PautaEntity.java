package br.com.coopera.gestorvotacao.infra.repository.pauta;

import br.com.coopera.gestorvotacao.impl.business.pauta.Pauta;
import br.com.coopera.gestorvotacao.infra.repository.utils.DateUtils;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "pauta")
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPauta")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dataInclusao")
    private Date dataInclusao;

    public static Pauta toBO(PautaEntity entity){
        Pauta pauta = new Pauta();
        pauta.setId(entity.getId());
        pauta.setNome(entity.getNome());
        pauta.setDescricao(entity.getDescricao());
        pauta.setDataInclusao(DateUtils.toLocalDate(entity.getDataInclusao()));

        return pauta;
    }

    public static PautaEntity fromBO(Pauta pauta){
        PautaEntity pautaEntity = new PautaEntity();
        pautaEntity.setId(pauta.getId());
        pautaEntity.setNome(pauta.getNome());
        pautaEntity.setDescricao(pauta.getDescricao());
        pautaEntity.setDataInclusao(DateUtils.toSQLDate(pauta.getDataInclusao()));

        return pautaEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
