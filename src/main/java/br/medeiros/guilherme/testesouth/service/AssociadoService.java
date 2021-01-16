package br.medeiros.guilherme.testesouth.service;

import br.medeiros.guilherme.testesouth.dto.CadastrarAssociadoDTO;
import br.medeiros.guilherme.testesouth.dto.StatusCPFEnum;
import br.medeiros.guilherme.testesouth.exception.AssociadoException;
import br.medeiros.guilherme.testesouth.integration.CpfIntegration;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AssociadoService {

    private final CpfIntegration cpfIntegration;

    public void cadastrarAssociado(CadastrarAssociadoDTO cadastrarAssociadoDTO) {
        validarCPF(cadastrarAssociadoDTO);
    }

    private void validarCPF(CadastrarAssociadoDTO cadastrarAssociadoDTO) {
        try{
            var cpfValidationDTO = this.cpfIntegration.buscarCpfElegivel(cadastrarAssociadoDTO.getCpf());
            if(cpfValidationDTO.getStatus().equals(StatusCPFEnum.UNABLE_TO_VOTE)){
                throw new AssociadoException(HttpStatus.NOT_FOUND, "Cpf inválido");
            }
            log.info(cpfValidationDTO.getStatus().toString());
        }catch (FeignException.FeignClientException ex){
            throw new AssociadoException(HttpStatus.NOT_FOUND, "Cpf inválido");
        }
    }
}
