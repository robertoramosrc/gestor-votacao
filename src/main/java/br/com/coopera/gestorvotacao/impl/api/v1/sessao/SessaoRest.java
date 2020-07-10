package br.com.coopera.gestorvotacao.impl.api.v1.sessao;

import br.com.coopera.gestorvotacao.impl.business.sessao.Sessao;
import br.com.coopera.gestorvotacao.impl.business.sessao.SessaoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sessoes")
public class SessaoRest {

    private final SessaoService sessaoService;
    private final ModelMapper mapper;

    public SessaoRest(SessaoService sessaoService, ModelMapper mapper) {
        this.sessaoService = sessaoService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation("Abrir uma seção de votação")
    public ResponseEntity<SessaoOutDTO> abrirParaIniciarVotacao(@Valid @RequestBody SessaoInDto sessaoInDto) {

        Sessao sessao = this.sessaoService.abrirParaIniciarVotacao(mapper.map(sessaoInDto, Sessao.class));

        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(sessao, SessaoOutDTO.class));
    }

    @GetMapping
    @ApiOperation("Consultar as seções de votações")
    public ResponseEntity<List<SessaoOutDTO>> consultarSessaoDeVotacao() {

        List<SessaoOutDTO> dtos = this.sessaoService.listarSessoes()
                .stream()
                .map(sessao -> mapper.map(sessao, SessaoOutDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/pautas/{pautaId}/dados")
    @ApiOperation("Consultar a seção de votação de uma Pauta")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pautaId", required = true, dataType = "String",
                    paramType = "path", value = "Número da Pauta")})

    public ResponseEntity<SessaoOutDTO> consultarSessaoDeVotacaoPorPauta(
            @PathVariable Long pautaId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.map(this.sessaoService.buscarSessaoPorPauta(pautaId),
                        SessaoOutDTO.class));
    }
}
