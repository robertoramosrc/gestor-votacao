package br.com.coopera.gestorvotacao.infra.repository.sessao;

import br.com.coopera.gestorvotacao.impl.business.sessao.DuracaoSessaoEnum;
import br.com.coopera.gestorvotacao.impl.business.sessao.Sessao;
import br.com.coopera.gestorvotacao.impl.business.sessao.SituacaoSessaoEnum;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SESSAO")
public class SessaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_SESSAO")
    private Long id;

    @Column(name = "ID_PAUTA")
    private Long pautaId;

    @Column(name = "DES_SITUACAO")
    private String situacao;

    @Column(name = "DES_DURACAO")
    private String duracao;

    @CreationTimestamp
    @Column(name = "DATA_ABERTURA", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataAbertura;

    @CreationTimestamp
    @Column(name = "DATA_ENCERRAMENTO", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataEncerramento;

    public static Sessao toBO(SessaoEntity entity){
        Sessao sessao = new Sessao();
        sessao.setId(entity.getId());
        sessao.setPautaId(entity.getPautaId());
        sessao.setSituacao(SituacaoSessaoEnum.getByValor(entity.getSituacao()));
        sessao.setDuracao(DuracaoSessaoEnum.getByValor(entity.getDuracao()));
        sessao.setDataAbertura(entity.getDataAbertura());
        sessao.setDataEncerramento(entity.getDataEncerramento());

        return sessao;
    }

    public static SessaoEntity from (Sessao bo){
        SessaoEntity entity = new SessaoEntity();
        entity.setId(bo.getId());
        entity.setPautaId(bo.getPautaId());
        entity.setSituacao(bo.getSituacao().getValor());
        entity.setDuracao(bo.getDuracao().getValor());
        entity.setDataAbertura(bo.getDataAbertura());
        entity.setDataEncerramento(bo.getDataEncerramento());

        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }
}
