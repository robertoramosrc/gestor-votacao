package br.com.coopera.gestorvotacao.rest;

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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(value = "classpath:sql/inclusao_dados_base.sql")
public class VotoE2ETest {

    private static final String VOTO_VALIDO_PARA_CADASTRO =
            json("{'cpf': 37507080021, 'sessaoId':1,'voto': 'SIM'}");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotoRepository votoRepository;

    @Mock
    private SituacaoCPFAssociado situacaoCPFAssociado;

    @BeforeEach
    public void setup(){
        Mockito.when(situacaoCPFAssociado.isCPFHabilitadoParaVotacao(any())).thenReturn(true);
    }

    @Test
    public void deveRegistrarVotoComSucesso() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/votos")
                        .content(VOTO_VALIDO_PARA_CADASTRO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
}
