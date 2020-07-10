package br.com.coopera.gestorvotacao.infra.repository.sessao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface SessaoDao extends JpaRepository<SessaoEntity, Long> {

    Optional<SessaoEntity> findByPautaId(Long pautaId);
}
