package br.com.coopera.gestorvotacao.impl.api.v1.voto;

import br.com.coopera.gestorvotacao.impl.api.v1.sessao.SessaoOutDTO;
import br.com.coopera.gestorvotacao.impl.business.voto.Voto;
import br.com.coopera.gestorvotacao.impl.business.voto.VotoService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/votos")
public class VotoRest {

    private final VotoService votoService;
    private final ModelMapper mapper;

    public VotoRest(VotoService votoService, ModelMapper mapper) {
        this.votoService = votoService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation("Registrar o voto em uma sessão")
    public ResponseEntity<VotoOutDto> registrarVoto(
            @Valid @RequestBody VotoInDTO votoInDto) {

        Voto voto = this.votoService.registrarVoto(
                mapper.map(votoInDto, Voto.class));

        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(voto, VotoOutDto.class));
    }
}
