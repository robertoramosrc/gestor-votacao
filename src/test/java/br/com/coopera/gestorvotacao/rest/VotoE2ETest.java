package br.com.coopera.gestorvotacao.rest;

import br.com.coopera.gestorvotacao.impl.business.pauta.PautaService;
import br.com.coopera.gestorvotacao.impl.business.sessao.SessaoService;
import br.com.coopera.gestorvotacao.impl.business.voto.VotoRepository;
import br.com.coopera.gestorvotacao.infra.client.SituacaoCPFAssociado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static br.com.coopera.gestorvotacao.utils.TestUtil.json;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(value = "classpath:sql/inclusao_dados_base.sql")
public class VotoE2ETest {

    private static final String VOTO_VALIDO_PARA_CADASTRO =
            json("{'cpf': 37507080021, 'pautaId':8,'valor':'SIM'}");
    private static final String PAUTA_COM_SESSAO_ABERTA_E_VOTOS_REALIZADOS = "8";
    private static final String RESULTADO_VOTACAO_PAUTA_APROVADA =
            json("{'pauta':'Pauta 8','totalAFavor':6,'totalContra':3,'totalAnulado':1," +
                    "'resultado':'PAUTA_APROVADA','situacaoSessao':'ABERTA'}");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SituacaoCPFAssociado situacaoCPFAssociado;

    @Test
    public void deveRegistrarVotoComSucesso() throws Exception {

        when(situacaoCPFAssociado.isCPFHabilitadoParaVotacao(anyString())).thenReturn(true);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/votos")
                        .content(VOTO_VALIDO_PARA_CADASTRO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deveContabilizarAVotacaoDeUmaPautaComSucesso() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/votos" +
                        "/pautas/" + PAUTA_COM_SESSAO_ABERTA_E_VOTOS_REALIZADOS + "/votacao")
                        .content(VOTO_VALIDO_PARA_CADASTRO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful())
                .andExpect(content().json(RESULTADO_VOTACAO_PAUTA_APROVADA));
    }
}
