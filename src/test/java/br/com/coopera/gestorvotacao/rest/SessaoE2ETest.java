package br.com.coopera.gestorvotacao.rest;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static br.com.coopera.gestorvotacao.utils.TestUtil.json;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(value = "classpath:sql/inclusao_dados_base.sql")
public class SessaoE2ETest {

    private static final String SESSAO_VALIDA_PARA_CADASTRO =
            json("{'pautaId': 1, 'duracao': 'UM_DIA'}");

    private static final String SESSAO_VALIDA_PARA_CADASTRO_SEM_TEMPO_DE_VOTACAO_ESPECIFICADO =
            json("{'pautaId': 1}");

    private static final String SESSAO_INVALIDA_PARA_CADASTRO_COM_PAUTA_INEXISTENTE =
            json("{'pautaId': 36}");

    private static final String SESSAO_INVALIDA_PARA_CADASTRO_JA_ABERTA_ANTERIORMENTE =
            json("{'pautaId': 2}");

    private static final String PAUTA_PARA_CONSULTA_DA_SESSAO_DE_VOTACAO = String.valueOf(4);

    private static final String SESSAO_VALIDA =
            json("{'id':12,'pautaId':4,'duracao':'UMA_HORA','situacao':'ABERTA'}");

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveAbrirUmaSessaoParaIniciarUmaVotacao() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/sessoes")
                        .content(SESSAO_VALIDA_PARA_CADASTRO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deveAbrirUmaSessaoComTempoPadraoDeUmMinutoParaVotacao() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/sessoes")
                        .content(SESSAO_VALIDA_PARA_CADASTRO_SEM_TEMPO_DE_VOTACAO_ESPECIFICADO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful())
                .andExpect(jsonPath("duracao").value(CoreMatchers.is("UM_MINUTO")));
    }

    @Test
    public void deveCriticarAberturaDeSessaoDeVotacaoParaPautaInexistente() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/sessoes")
                        .content(SESSAO_INVALIDA_PARA_CADASTRO_COM_PAUTA_INEXISTENTE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is4xxClientError())
                .andExpect(jsonPath("descricao")
                        .value(CoreMatchers.is("Pauta não existente para a abertura da votação.")));
    }

    @Test
    public void deveCriticarAberturaDeSessaoDeVotacaoParaPautaJaEmVotacao() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/sessoes")
                        .content(SESSAO_INVALIDA_PARA_CADASTRO_JA_ABERTA_ANTERIORMENTE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is4xxClientError())
                .andExpect(jsonPath("descricao")
                        .value(CoreMatchers.is("Sessão já aberta anteriormente.")));
    }

    @Test
    public void deveListarSesoesDeVotacoes() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/sessoes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful());
    }

    @Test
    public void deveRetornarUmaSessaoDeUmaPauta() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/sessoes/" +
                        "pautas/" + PAUTA_PARA_CONSULTA_DA_SESSAO_DE_VOTACAO +"/dados"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(content().json(SESSAO_VALIDA));
    }

}
