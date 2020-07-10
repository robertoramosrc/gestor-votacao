package br.com.coopera.gestorvotacao.impl.business.voto;

import br.com.coopera.gestorvotacao.infra.repository.sessao.SessaoEntity;
import br.com.coopera.gestorvotacao.infra.repository.voto.VotoDao;
import br.com.coopera.gestorvotacao.infra.repository.voto.VotoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VotoRepository {

    private final VotoDao votoDao;

    public VotoRepository(VotoDao votoDao) {
        this.votoDao = votoDao;
    }

    public Voto salvar(Voto voto){
        return VotoEntity.toBO(votoDao.save(VotoEntity.from(voto)));
    }

    public Optional<Voto> buscarPorCPF(String cpf){
        return votoDao.findByCpf(cpf)
                .map(VotoEntity::toBO);
    }

    public List<Voto> listarVotosDeUmaSessao(Long sessaoId){
        return votoDao.findBySessaoId(sessaoId)
                .stream()
                .map(VotoEntity::toBO)
                .collect(Collectors.toList());
    }
}
