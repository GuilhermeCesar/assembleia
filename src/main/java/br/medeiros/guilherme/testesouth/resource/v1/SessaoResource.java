package br.medeiros.guilherme.testesouth.resource.v1;

import br.medeiros.guilherme.testesouth.config.SwaggerConfig;
import br.medeiros.guilherme.testesouth.dto.*;
import br.medeiros.guilherme.testesouth.service.SessaoService;
import br.medeiros.guilherme.testesouth.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/sessao", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = SwaggerConfig.SwaggerTags.SESSAO)
public class SessaoResource {

    private final SessaoService sessaoService;
    private final VotoService votoService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Api de criação de sessão")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sucesso", response = SessaoDTO.class),
            @ApiResponse(code = 500, message = "Falha ao inserir sessão", response = ErrorMessage.class)
    })
    @PostMapping
    public SessaoDTO create(@Valid @RequestBody CadastrarSessaoDTO sessaoDTO) {
        return this.sessaoService.criarSessao(sessaoDTO);
    }

    @GetMapping(path = "{idSessao}")
    public SessaoDTO get(@PathVariable("idSessao") Long idSessao) {
        return this.sessaoService.obterSessao(idSessao);
    }

    @GetMapping(path = "/busca")
    @ApiOperation(value = "Filtra associados", response = Page.class)
    @ResponseStatus(OK)
    public Page<SessaoDTO> findBy(@RequestParam(value = "pauta", required = false) String pauta,
                                  Pageable pageable) {
        return this.sessaoService.buscarSessao(pauta, pageable);
    }

    @ApiOperation(value = "Votar", response = VotoFinalizadoDTO.class)
    @PostMapping("/{idSessao}/voto")
    public VotoFinalizadoDTO votar(@PathVariable("idSessao") Long idSessao, @RequestBody VotoDTO votoDTO) {
        return this.votoService.votar(idSessao, votoDTO);
    }

    @ApiOperation(value = "Votar", response = VotoFinalizadoDTO.class)
    @GetMapping("/{idSessao}/voto/contagem")
    public VotoFinalizadoDTO votar(@PathVariable("idSessao") Long idSessao) {
        return this.votoService.contagemVotos(idSessao);
    }
}
