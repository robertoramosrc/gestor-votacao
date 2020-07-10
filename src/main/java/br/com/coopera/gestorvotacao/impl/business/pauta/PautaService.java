package br.com.coopera.gestorvotacao.impl.business.pauta;

import br.com.coopera.gestorvotacao.impl.exceptions.RegistroNaoExisteException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta salvar(Pauta pauta) {
        return pautaRepository.salvar(pauta);

    }

    public List<Pauta> listar() {
        return pautaRepository.listar();
    }

    public Pauta buscarPorId(Long id) {
        return pautaRepository.buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoExisteException("Pauta não encontrada"));
    }
    public boolean isPautaCadastrada(Long id){
        return pautaRepository.buscarPorId(id).isPresent();
    }
}
