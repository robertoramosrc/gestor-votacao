package br.com.coopera.gestorvotacao.impl.business.voto;

import br.com.coopera.gestorvotacao.impl.business.pauta.Pauta;
import br.com.coopera.gestorvotacao.impl.business.pauta.PautaService;
import br.com.coopera.gestorvotacao.impl.business.sessao.Sessao;
import br.com.coopera.gestorvotacao.impl.business.sessao.SessaoService;
import br.com.coopera.gestorvotacao.impl.business.sessao.SituacaoSessaoEnum;
import br.com.coopera.gestorvotacao.impl.exceptions.NegocioException;
import br.com.coopera.gestorvotacao.infra.client.SituacaoCPFAssociado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final SituacaoCPFAssociado situacaoCPFAssociado;
    private final SessaoService sessaoService;
    private final PautaService pautaService;

    public VotoService(VotoRepository votoRepository, SituacaoCPFAssociado situacaoCPFAssociado, SessaoService sessaoService, PautaService pautaService) {
        this.votoRepository = votoRepository;
        this.situacaoCPFAssociado = situacaoCPFAssociado;
        this.sessaoService = sessaoService;
        this.pautaService = pautaService;
    }

    public Voto registrarVoto(Voto voto){
        Sessao sessao = buscarSessaoDeVotacaoAberta(voto.getPautaId());

        validarVotoNaoDuplicado(voto);
        validarCPFHabilitadoParaVota(voto);

        voto.setSessaoId(sessao.getId());

        return votoRepository.salvar(voto);
    }

    private void validarSessaoEmAbertoParaAVotacaodaPauta(Sessao sessao){
        if(!SituacaoSessaoEnum.ABERTA.equals(sessao.getSituacao())){
            throw new NegocioException("Não existe sessão de votção aberta para a pauta");
        }
    }

    private void validarCPFHabilitadoParaVota(Voto voto) {
        if(voto.getCpf() == null){
            throw new NegocioException("CPF não informado na votação");
        }

        /*if(!situacaoCPFAssociado.isCPFHabilitadoParaVotacao(voto.getCpf())){
            throw new NegocioException("Não habilitado para votar. CPF: ".concat(voto.getCpf()));
        }*/
    }

    private void validarVotoNaoDuplicado(Voto voto) {
        if(votoRepository.buscarPorCPF(voto.getCpf()).isPresent()){
            throw new NegocioException("Voto já regsitrado pelo CPF: ".concat(voto.getCpf()));
        }
    }

    public ResultadoVotacao contabilizarVotacao(Long pautaId) {

        Sessao sessao = buscarSessaoDeVotacaoAberta(pautaId);
        List<Voto> votos = votoRepository.listarVotosDeUmaSessao(sessao);

        ResultadoVotacao resultado = new ResultadoVotacao();
        Pauta pauta = pautaService.buscarPorId(pautaId);

        resultado.setPauta(pauta.getNome());
        resultado.setSituacaoSessao(sessao.getSituacao());

        resultado.setTotalAFavor( votos.stream()
                .filter(voto -> ValorVotoEnum.SIM.equals(voto.getValor()))
                .count() );

        resultado.setTotalContra( votos.stream()
                .filter(voto -> ValorVotoEnum.NAO.equals(voto.getValor()))
                .count() );

        resultado.setTotalAnulado( votos.stream()
                .filter(voto -> ValorVotoEnum.NULO.equals(voto.getValor()))
                .count() );

        resultado.setResultado(
                resultado.getTotalAFavor() >= resultado.getTotalContra() ?
                        ResultadoVotacaoEnum.PAUTA_APROVADA : ResultadoVotacaoEnum.PAUTA_REPROVADA);

        return resultado;
    }

    private Sessao buscarSessaoDeVotacaoAberta(Long pautaId) {
        Sessao sessao = sessaoService.buscarSessaoPorPauta(pautaId);
        validarSessaoEmAbertoParaAVotacaodaPauta(sessao);

        return sessao;
    }
}
