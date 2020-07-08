package br.com.coopera.gestorvotacao.infra.repository.pauta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PautaDao extends JpaRepository<PautaEntity, Long> {

}
