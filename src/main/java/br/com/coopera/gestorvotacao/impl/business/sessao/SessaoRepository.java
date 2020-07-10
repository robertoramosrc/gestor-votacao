package br.com.coopera.gestorvotacao.impl.business.sessao;

import br.com.coopera.gestorvotacao.impl.business.pauta.Pauta;
import br.com.coopera.gestorvotacao.impl.exceptions.RegistroNaoExisteException;
import br.com.coopera.gestorvotacao.infra.repository.sessao.SessaoDao;
import br.com.coopera.gestorvotacao.infra.repository.sessao.SessaoEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
}
