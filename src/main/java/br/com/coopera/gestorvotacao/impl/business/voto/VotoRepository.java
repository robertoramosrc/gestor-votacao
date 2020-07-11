package br.com.coopera.gestorvotacao.impl.business.voto;

import br.com.coopera.gestorvotacao.impl.business.sessao.Sessao;
import br.com.coopera.gestorvotacao.impl.exceptions.RegistroNaoExisteException;
import br.com.coopera.gestorvotacao.infra.client.SituacaoCPFAssociadoClient;
import br.com.coopera.gestorvotacao.infra.client.SituacaoCPFDto;
import br.com.coopera.gestorvotacao.infra.repository.voto.VotoDao;
import br.com.coopera.gestorvotacao.infra.repository.voto.VotoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VotoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(VotoRepository.class);
    private final SituacaoCPFAssociadoClient situacaoCPFAssociadoClient;
    private final VotoDao votoDao;

    public VotoRepository(SituacaoCPFAssociadoClient situacaoCPFAssociadoClient, VotoDao votoDao) {
        this.situacaoCPFAssociadoClient = situacaoCPFAssociadoClient;
        this.votoDao = votoDao;
    }

    public Voto salvar(Voto voto) {
        return VotoEntity.toBO(votoDao.save(VotoEntity.from(voto)));
    }

    public Optional<Voto> buscarPorCPF(String cpf) {
        return votoDao.findByCpf(cpf)
                .map(VotoEntity::toBO);
    }

    public List<Voto> listarVotosDeUmaSessao(Sessao sessao) {
        return votoDao.findBySessaoId(sessao.getId())
                .stream()
                .map(VotoEntity::toBO)
                .collect(Collectors.toList());
    }

    public boolean isCpfAssociadoHabilitadoParaVotacao(String cpf) {
        try {
            LOGGER.debug("[VOTACAO] -> CONSULTA CPF.");

            return validarCPFHabilitadoParaVotacao(cpf);

        } catch (Exception e) {
            LOGGER.error("[VOTACAO] -> ERRO CONSULTA CPF: ", e);
            throw new RegistroNaoExisteException("CPF não encontrado");
        }

    }

    private boolean validarCPFHabilitadoParaVotacao(String cpf) {
        SituacaoCPFDto response =
                situacaoCPFAssociadoClient.validarSituacaoCpf(cpf);

        if (response != null) {
            return response.isHabilitado();
        }

        return false;
    }
}
