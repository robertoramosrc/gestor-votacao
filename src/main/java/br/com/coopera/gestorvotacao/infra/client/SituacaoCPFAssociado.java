package br.com.coopera.gestorvotacao.infra.client;

import br.com.coopera.gestorvotacao.impl.exceptions.NegocioException;
import br.com.coopera.gestorvotacao.impl.exceptions.RegistroNaoExisteException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SituacaoCPFAssociado {

    private final RestTemplate restTemplate;
    private final String apisCPFUrl;

    public SituacaoCPFAssociado(RestTemplate restTemplate,
                                @Value("${apis.CPFUrl}") String apisCPFUrl) {
        this.restTemplate = restTemplate;
        this.apisCPFUrl = apisCPFUrl;
    }

    public boolean isCPFHabilitadoParaVotacao(String cpf) {

        ResponseEntity<SituacaoCPFDto> response =
                restTemplate.getForEntity(
                        apisCPFUrl,
                        SituacaoCPFDto.class, cpf);

        if (response.getStatusCode() == HttpStatus.OK) {
            SituacaoCPFDto situacaoCPF = response.getBody();
            if(situacaoCPF != null){
                return situacaoCPF.isHabilitado();
            }

        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new NegocioException("CPF inválido");
        }

        throw new IllegalArgumentException("Erro ao acessar o serviço de validação de CPFs");
    }
}
