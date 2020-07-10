package br.com.coopera.gestorvotacao.service;

import br.com.coopera.gestorvotacao.impl.business.pauta.PautaService;
import br.com.coopera.gestorvotacao.impl.business.sessao.Sessao;
import br.com.coopera.gestorvotacao.impl.business.sessao.SessaoRepository;
import br.com.coopera.gestorvotacao.impl.business.sessao.SessaoService;
import br.com.coopera.gestorvotacao.impl.business.sessao.SituacaoSessaoEnum;
import br.com.coopera.gestorvotacao.impl.exceptions.NegocioException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessaoServiceTest {

    private SessaoService sessaoService;

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaService pautaService;

    @BeforeEach
    public void setup(){
        sessaoService = new SessaoService(sessaoRepository, pautaService);
    }

    @Test
    public void deveValidarEstadoDaSessaoParaEncerramentoDaVotacao(){
        Sessao sessao = new Sessao();
        sessao.setSituacao(SituacaoSessaoEnum.ENCERRADA);

        when(sessaoRepository.buscarPorPauta(any())).thenReturn(Optional.of(sessao));

        NegocioException e = assertThrows(NegocioException.class,
                () -> sessaoService.encerrarSessaoDeVotacao(null));

        Assertions.assertEquals("Não é possível encerrar uma sessão de votação que ão esteja aberta.",
                e.getMessage());

    }
}
