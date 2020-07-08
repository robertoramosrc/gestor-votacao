package br.com.coopera.gestorvotacao.impl.api.v1.pauta;

import br.com.coopera.gestorvotacao.impl.business.pauta.Pauta;
import br.com.coopera.gestorvotacao.impl.business.pauta.PautaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pautas")
@Validated
public class PautaRest {

    private final PautaService pautaService;
    private final ModelMapper mapper;


    public PautaRest(PautaService pautaService, ModelMapper mapper) {
        this.pautaService = pautaService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation("Criar uma pauta para votação em assembléias")
    public ResponseEntity<PautaDTO> cadastrar(
            @RequestBody PautaDTO pautaDTO) {

        Pauta pauta = this.pautaService.salvar(mapper.map(pautaDTO, Pauta.class));

        URI location = URI.create(
                new StringBuffer()
                        .append("/pautas/")
                        .append(pauta.getId())
                        .toString());

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ApiOperation("Consultar Pautas")
    public ResponseEntity<List<PautaDTO>> listar(){
        return pautaService.listar();
    }

    @GetMapping("/id")
    @ApiOperation("Consultar Pauta por ID")
    public ResponseEntity<PautaDTO> listarPorId(
            @ApiParam("Identificador da Pauta")
            @PathVariable Long id ){

        return pautaService.listarPorId(id);
    }
}
