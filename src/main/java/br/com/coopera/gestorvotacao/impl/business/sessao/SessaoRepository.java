package br.com.coopera.gestorvotacao.impl.business.sessao;

import br.com.coopera.gestorvotacao.impl.exceptions.RegistroNaoExisteException;
import br.com.coopera.gestorvotacao.infra.repository.sessao.SessaoDao;
import br.com.coopera.gestorvotacao.infra.repository.sessao.SessaoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SessaoRepository {

    private final SessaoDao sessaoDao;

    public SessaoRepository(SessaoDao sessaoDao) {
        this.sessaoDao = sessaoDao;
    }

    public Sessao salvar(Sessao sessao) {
        return SessaoEntity.toBO(sessaoDao.save(SessaoEntity.from(sessao)));
    }

    public Sessao buscarPorId(Long id) {
        SessaoEntity entity = sessaoDao.findById(id)
                .orElseThrow(() -> new RegistroNaoExisteException("Sessao não encontrada"));

        return SessaoEntity.toBO(entity);
    }

    public Optional<Sessao> buscarPorPauta(Long pautaId) {
        return sessaoDao.findByPautaId(pautaId)
                .map(SessaoEntity::toBO);
    }

    public List<Sessao> listarSessoes() {
        return sessaoDao.findAll()
                .stream()
                .map(SessaoEntity::toBO)
                .collect(Collectors.toList());
    }
}
