package br.medeiros.guilherme.testesouth.service;

import br.medeiros.guilherme.testesouth.dto.AssociadoDTO;
import br.medeiros.guilherme.testesouth.dto.CadastrarAssociadoDTO;
import br.medeiros.guilherme.testesouth.dto.StatusCPFEnum;
import br.medeiros.guilherme.testesouth.entity.Associado;
import br.medeiros.guilherme.testesouth.exception.AssociadoException;
import br.medeiros.guilherme.testesouth.helper.MessageHelper;
import br.medeiros.guilherme.testesouth.integration.CpfIntegration;
import br.medeiros.guilherme.testesouth.repository.AssociadoRepository;
import br.medeiros.guilherme.testesouth.repository.AssociadoSpecification;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AssociadoService {

    private final CpfIntegration cpfIntegration;
    private final AssociadoRepository associadoRepository;
    private final MessageHelper messageHelper;

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
                throw new AssociadoException(HttpStatus.NOT_FOUND, this.messageHelper.get(MessageHelper.ErrorCode.ERROR_CPF_INVALIDO));
            }
        } catch (FeignException.FeignClientException ex) {
            throw new AssociadoException(HttpStatus.NOT_FOUND, this.messageHelper.get(MessageHelper.ErrorCode.ERROR_CPF_INVALIDO));
        }
    }

    public Page<AssociadoDTO> findSessao(final String cpf, final Long idAssociado, final String nome, Pageable pageable) {
        final var asssociadoSpec = AssociadoSpecification
                .builder()
                .idAssociado(Optional.ofNullable(idAssociado))
                .cpf(Optional.ofNullable(cpf))
                .nome(Optional.ofNullable(nome))
                .build();

        final var associadoPage = this.associadoRepository.findAll(asssociadoSpec, pageable);

        return associadoPage.map(associadoMap -> AssociadoDTO
                .builder()
                .cpf(associadoMap.getCpf())
                .nome(associadoMap.getNome())
                .id(associadoMap.getId())
                .build());
    }
}
