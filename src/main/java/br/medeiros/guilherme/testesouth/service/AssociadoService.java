package br.medeiros.guilherme.testesouth.service;

import br.medeiros.guilherme.testesouth.dto.AssociadoDTO;
import br.medeiros.guilherme.testesouth.dto.CadastrarAssociadoDTO;
import br.medeiros.guilherme.testesouth.dto.StatusCPFEnum;
import br.medeiros.guilherme.testesouth.entity.Associado;
import br.medeiros.guilherme.testesouth.exception.AssociadoException;
import br.medeiros.guilherme.testesouth.integration.CpfIntegration;
import br.medeiros.guilherme.testesouth.repository.AssociadoRepository;
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
    private final AssociadoRepository associadoRepository;

    public AssociadoDTO cadastrarAssociado(CadastrarAssociadoDTO cadastrarAssociadoDTO) {
        validarCPF(cadastrarAssociadoDTO);

        final var associado = Associado.builder()
                .cpf(cadastrarAssociadoDTO.getCpf())
                .nome(cadastrarAssociadoDTO.getNome())
                .build();

        this.associadoRepository.save(associado);

        return AssociadoDTO
                .builder()
                .id(associado.getId())
                .cpf(associado.getCpf())
                .nome(associado.getNome())
                .build();
    }

    private void validarCPF(CadastrarAssociadoDTO cadastrarAssociadoDTO) {
        try {
            final var cpfValidationDTO = this.cpfIntegration.buscarCpfElegivel(cadastrarAssociadoDTO.getCpf());
            if (cpfValidationDTO.getStatus().equals(StatusCPFEnum.UNABLE_TO_VOTE)) {
                throw new AssociadoException(HttpStatus.NOT_FOUND, "Cpf inválido");
            }
        } catch (FeignException.FeignClientException ex) {
            throw new AssociadoException(HttpStatus.NOT_FOUND, "Cpf inválido");
        }
    }
}
