package br.com.coopera.gestorvotacao.impl.api.v1.voto;

import br.com.coopera.gestorvotacao.impl.business.voto.Voto;
import br.com.coopera.gestorvotacao.impl.business.voto.VotoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("Registrar o voto em uma Pauta")
    public ResponseEntity<VotoOutDto> registrarVoto(
            @Valid @RequestBody VotoInDTO votoInDto) {

        Voto voto = this.votoService.registrarVoto(
                mapper.map(votoInDto, Voto.class));

        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(voto, VotoOutDto.class));
    }

    @GetMapping("/pautas/{pautaId}/votacao")
    @ApiOperation("Contabilizar a votação em uma Pauta")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pautaId", required = true, dataType = "Long",
                    paramType = "path", value = "Número da Pauta")})
    public ResponseEntity<ResultadoVotacaoDTO> contabilizarVotos(
            @PathVariable Long pautaId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper
                        .map(this.votoService.contabilizarVotacao(pautaId), ResultadoVotacaoDTO.class));
    }
}
