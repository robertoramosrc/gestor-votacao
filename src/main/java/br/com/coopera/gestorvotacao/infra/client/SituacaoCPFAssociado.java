package br.com.coopera.gestorvotacao.infra.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SituacaoCPFAssociado {
    private static final Logger LOGGER = LoggerFactory.getLogger(SituacaoCPFAssociado.class);

    private final RestTemplate restTemplate;
    private final String apisCPFUrl;

    public SituacaoCPFAssociado(RestTemplate restTemplate,
                                @Value("${apis.CPFUrl}") String apisCPFUrl) {
        this.restTemplate = restTemplate;
        this.apisCPFUrl = apisCPFUrl;
    }

    public boolean isCPFHabilitadoParaVotacao(String cpf) {

        try {
            LOGGER.debug("[VOTACAO] -> CONSULTA CPF.");

            return validarCPFHabilitadoParaVotacao(cpf);

        } catch (Exception e) {
            LOGGER.error("[VOTACAO] -> ERRO CONSULTA CPF: ", e);
        }

        return false;
    }

    public Boolean validarCPFHabilitadoParaVotacao(String cpf) {
        ResponseEntity<SituacaoCPFDto> response =
                restTemplate.getForEntity(
                        apisCPFUrl,
                        SituacaoCPFDto.class, cpf.trim());

        if (response.getStatusCode() == HttpStatus.OK) {
            SituacaoCPFDto situacaoCPF = response.getBody();
            if (situacaoCPF != null) {
                return situacaoCPF.isHabilitado();
            }
        }

        return false;
    }
}
