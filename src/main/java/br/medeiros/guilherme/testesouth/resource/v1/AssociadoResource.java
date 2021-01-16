package br.medeiros.guilherme.testesouth.resource.v1;

import br.medeiros.guilherme.testesouth.config.SwaggerConfig;
import br.medeiros.guilherme.testesouth.dto.CadastrarAssociadoDTO;
import br.medeiros.guilherme.testesouth.dto.CadastrarSessaoDTO;
import br.medeiros.guilherme.testesouth.dto.ErrorMessage;
import br.medeiros.guilherme.testesouth.dto.SessaoDTO;
import br.medeiros.guilherme.testesouth.service.AssociadoService;
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
@RequestMapping(path = "/v1/associado", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = SwaggerConfig.SwaggerTags.ASSOCIADO)
public class AssociadoResource {

    private final AssociadoService associadoService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Api de criação de associado")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sucesso", response = SessaoDTO.class),
            @ApiResponse(code = 500, message = "Falha ao inserir associado", response = ErrorMessage.class)
    })
    @PostMapping
    public void create(@Valid @RequestBody CadastrarAssociadoDTO cadastrarAssociadoDTO) {
        this.associadoService.cadastrarAssociado(cadastrarAssociadoDTO);
    }
}
