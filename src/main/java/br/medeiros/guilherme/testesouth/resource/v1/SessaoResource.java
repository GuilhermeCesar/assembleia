package br.medeiros.guilherme.testesouth.resource.v1;

import br.medeiros.guilherme.testesouth.config.SwaggerConfig;
import br.medeiros.guilherme.testesouth.dto.CadastrarSessaoDTO;
import br.medeiros.guilherme.testesouth.dto.ErrorMessage;
import br.medeiros.guilherme.testesouth.dto.SessaoDTO;
import br.medeiros.guilherme.testesouth.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/sessao", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = SwaggerConfig.SwaggerTags.SESSAO)
public class SessaoResource {

    private final SessaoService sessaoService;

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
    public SessaoDTO get(@PathVariable("idSessao") Long idSessao){
        return this.sessaoService.obterSessao(idSessao);
    }

}
