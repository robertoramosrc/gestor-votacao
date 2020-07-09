package br.com.coopera.gestorvotacao.infra.repository.pauta;

import br.com.coopera.gestorvotacao.impl.business.pauta.Pauta;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PAUTA")
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PAUTA")
    private Long id;

    @Column(name = "NM_PAUTA")
    private String nome;

    @Column(name = "DES_PAUTA")
    private String descricao;

    @CreationTimestamp
    @Column(name = "DATA_INCLUSAO", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataInclusao;

    public static Pauta toBO(PautaEntity entity){
        Pauta pauta = new Pauta();
        pauta.setId(entity.getId());
        pauta.setNome(entity.getNome());
        pauta.setDescricao(entity.getDescricao());
        pauta.setDataInclusao(entity.getDataInclusao());

        return pauta;
    }

    public static PautaEntity fromBO(Pauta pauta){
        PautaEntity pautaEntity = new PautaEntity();
        pautaEntity.setId(pauta.getId());
        pautaEntity.setNome(pauta.getNome());
        pautaEntity.setDescricao(pauta.getDescricao());
        pautaEntity.setDataInclusao(pauta.getDataInclusao());

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

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
