package br.com.coopera.gestorvotacao.infra.repository.voto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface VotoDao extends JpaRepository<VotoEntity, Long> {

        List<VotoEntity> findBySessaoId(Long sessaoId);

        Optional<VotoEntity> findByCpf(String cpf);
}
