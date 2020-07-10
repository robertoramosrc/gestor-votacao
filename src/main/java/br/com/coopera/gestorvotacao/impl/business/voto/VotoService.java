package br.com.coopera.gestorvotacao.impl.business.voto;

import br.com.coopera.gestorvotacao.impl.exceptions.NegocioException;
import br.com.coopera.gestorvotacao.infra.client.SituacaoCPFAssociado;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final SituacaoCPFAssociado situacaoCPFAssociado;

    public VotoService(VotoRepository votoRepository, SituacaoCPFAssociado situacaoCPFAssociado) {
        this.votoRepository = votoRepository;
        this.situacaoCPFAssociado = situacaoCPFAssociado;
    }

    public Voto registrarVoto(Voto voto){
        validarCPF(voto);
        validarVoto(voto);
        return votoRepository.salvar(voto);
    }

    private void validarCPF(Voto voto) {
        if(voto.getCpf() == null){
            throw new NegocioException("CPF não informado na votação");
        }

        if(!situacaoCPFAssociado.isCPFHabilitadoParaVotacao(voto.getCpf())){
            throw new NegocioException("Não habilitado para votar. CPF: ".concat(voto.getCpf()));
        }
    }

    private void validarVoto(Voto voto) {
        if(votoRepository.buscarPorCPF(voto.getCpf()).isPresent()){
            throw new NegocioException("Voto já regsitrado pelo CPF: ".concat(voto.getCpf()));
        }
    }
}
