package br.com.coopera.gestorvotacao.impl.business.pauta;

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

    public Pauta listarPorId(Long id) {
        return pautaRepository.listarPorId(id);
    }
}
