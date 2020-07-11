package br.com.coopera.gestorvotacao.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users",
        url = "${apis.CPFUrl}",
        decode404 = true)
public interface SituacaoCPFAssociadoClient {

    @GetMapping("/users/{cpf}")
    SituacaoCPFDto validarSituacaoCpf(
            @PathVariable("cpf") String cpf);

}
