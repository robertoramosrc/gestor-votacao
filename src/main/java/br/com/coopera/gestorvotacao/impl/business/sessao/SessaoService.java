package br.com.coopera.gestorvotacao.impl.business.sessao;

import br.com.coopera.gestorvotacao.impl.business.pauta.PautaService;
import br.com.coopera.gestorvotacao.impl.exceptions.NegocioException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaService pautaService;

    public SessaoService(SessaoRepository sessaoRepository, PautaService pautaService) {
        this.sessaoRepository = sessaoRepository;
        this.pautaService = pautaService;
    }

    public Sessao abrirParaIniciarVotacao(Sessao sessao){
        validarSessaoParaAberturaDeVotacao(sessao);

        if(sessao.getDuracao() == null){
            sessao.setDuracao(DuracaoSessaoEnum.UM_MINUTO);
        }
        sessao.setSituacao(SituacaoSessaoEnum.ABERTA);
        sessao.setDataAbertura(LocalDateTime.now());

        return sessaoRepository.salvar(sessao);
    }

    public Sessao encerrarSessaoDeVotacao(Long pautaId){
        Sessao sessao = buscarSessaoPorPauta(pautaId);

        validarSessaoParaEncerraMentoDeVotacao(sessao);

        sessao.setSituacao(SituacaoSessaoEnum.ENCERRADA);
        sessao.setDataEncerramento(LocalDateTime.now());

        return sessaoRepository.salvar(sessao);
    }

    private Sessao buscarSessaoPorPauta(Long pautaId) {
        return sessaoRepository.buscarPorPauta(pautaId)
                .orElseThrow(() -> new NegocioException("Não foi encontrada uma sessão aberta para a pauta."));
    }

    private void validarSessaoParaAberturaDeVotacao(Sessao sessao){
        if(!pautaService.isPautaCadastrada(sessao.getPautaId())){
            throw new NegocioException("Pauta não existente para a abertura da votação.");
        }

        if( sessaoRepository.buscarPorPauta(sessao.getPautaId()).isPresent() ){
            throw new NegocioException("Sessão já aberta anteriormente.");
        };
    }

    private void validarSessaoParaEncerraMentoDeVotacao(Sessao sessao){
        if(!SituacaoSessaoEnum.ABERTA.equals(sessao.getSituacao())){
            throw new NegocioException("Não é possível encerrar uma sessão de votação que ão esteja aberta.");
        }
    }
}
