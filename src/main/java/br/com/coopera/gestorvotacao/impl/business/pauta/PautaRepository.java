package br.com.coopera.gestorvotacao.impl.business.pauta;

import br.com.coopera.gestorvotacao.impl.exceptions.RegistroNaoExisteException;
import br.com.coopera.gestorvotacao.infra.repository.pauta.PautaDao;
import br.com.coopera.gestorvotacao.infra.repository.pauta.PautaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PautaRepository {
    private final PautaDao pautaDao;

    public PautaRepository(PautaDao pautaDao) {
        this.pautaDao = pautaDao;
    }

    public Pauta salvar(Pauta pauta){
        return PautaEntity.toBO(pautaDao.save(PautaEntity.fromBO(pauta)));
    }

    public List<Pauta> listar() {
        return pautaDao.findAll()
                .stream()
                .map(PautaEntity::toBO)
                .collect(Collectors.toList());
    }

    public Pauta listarPorId(Long id) {
        return PautaEntity.toBO(pautaDao.findById(id)
                .orElseThrow(() -> new RegistroNaoExisteException("Pauta não encontrada")));
    }
}
