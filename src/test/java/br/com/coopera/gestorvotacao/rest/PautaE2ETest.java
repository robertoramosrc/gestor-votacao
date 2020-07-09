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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql(value = "classpath:sql/inclusao_dados_base.sql")
public class PautaE2ETest {

    private static final String PAUTA_VALIDA_PARA_CADASTRO =
            json("{'nome': 'Pauta 1', 'descricao': 'Pauta 1 para deliberação'," +
            " 'dataInclusao': '2020-07-08'}");

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveCadastrarUmaPauta() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.post("/pautas")
                .content(PAUTA_VALIDA_PARA_CADASTRO)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deveListarPautasCriadas() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/pautas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is2xxSuccessful());
    }

   @Test
   public void deveRetornarUmaPautaDadoUmId() throws Exception {
       mockMvc
               .perform(MockMvcRequestBuilders.get("/pautas/1"))
               .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
               .andExpect(jsonPath("nome").value(CoreMatchers.is("Pauta 1")))
               .andExpect(jsonPath("descricao").value(CoreMatchers.is("Primeira Pauta")));
   }

}
