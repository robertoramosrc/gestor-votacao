package br.com.coopera.gestorvotacao.impl.api.v1.pauta;

import br.com.coopera.gestorvotacao.impl.business.pauta.Pauta;
import br.com.coopera.gestorvotacao.impl.business.pauta.PautaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pautas")
public class PautaRest {

    private final PautaService pautaService;
    private final ModelMapper mapper;


    public PautaRest(PautaService pautaService, ModelMapper mapper) {
        this.pautaService = pautaService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation("Criar uma pauta para votação em assembléias")
    public ResponseEntity<PautaInDTO> cadastrar(
            @Valid @RequestBody PautaInDTO pautaInDTO) {

        Pauta pauta = this.pautaService.salvar(mapper.map(pautaInDTO, Pauta.class));

        URI location = URI.create(
                new StringBuffer()
                        .append("/pautas/")
                        .append(pauta.getId())
                        .toString());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ApiOperation("Consultar Pautas")
    public ResponseEntity<List<PautaOutDTO>> listar() {

        List<PautaOutDTO> dtos = pautaService.listar()
                .stream()
                .map(pauta -> mapper.map(pauta, PautaOutDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/{id}")
    @ApiOperation("Consultar Pauta por ID")
    public ResponseEntity<PautaOutDTO> listarPorId(
            @ApiParam("Identificador da Pauta")
            @PathVariable Long id) {

        return ResponseEntity.ok(
                mapper.map(pautaService.buscarPorId(id), PautaOutDTO.class));

    }
}
