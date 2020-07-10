package br.com.coopera.gestorvotacao.infra.repository.voto;

import br.com.coopera.gestorvotacao.impl.business.voto.ValorVotoEnum;
import br.com.coopera.gestorvotacao.impl.business.voto.Voto;

import javax.persistence.*;

@Entity
@Table(name = "TB_VOTO")
public class VotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_VOTO")
    private Long id;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "VALOR")
    private String valor;

    @Column(name = "ID_SESSAO")
    private Long sessaoId;

    public static Voto toBO(VotoEntity entity){
        Voto voto = new Voto();
        voto.setId(entity.getId());
        voto.setCpf(entity.getCpf());
        voto.setValor(ValorVotoEnum.getByValor(entity.getValor()));
        voto.setSessaoId(entity.getSessaoId());

        return voto;
    }

    public static VotoEntity from(Voto bo){
        VotoEntity entity = new VotoEntity();
        entity.setId(bo.getId());
        entity.setCpf(bo.getCpf());
        entity.setValor(bo.getValor().getValor());
        entity.setSessaoId(bo.getSessaoId());

        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }
}
