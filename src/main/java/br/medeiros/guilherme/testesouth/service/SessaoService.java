package br.medeiros.guilherme.testesouth.service;

import br.medeiros.guilherme.testesouth.dto.CadastrarSessaoDTO;
import br.medeiros.guilherme.testesouth.dto.SessaoDTO;
import br.medeiros.guilherme.testesouth.entity.Sessao;
import br.medeiros.guilherme.testesouth.exception.SessaoException;
import br.medeiros.guilherme.testesouth.helper.MessageHelper;
import br.medeiros.guilherme.testesouth.repository.SessaoRepository;
import br.medeiros.guilherme.testesouth.repository.SessaoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final MessageHelper messageHelper;

    public SessaoDTO criarSessao(final CadastrarSessaoDTO cadastrarSessaoDTO) {
        try {
            final var sessao = Sessao
                    .builder()
                    .dataInicio(cadastrarSessaoDTO.getInicio())
                    .duracao(cadastrarSessaoDTO.getDuracao())
                    .pauta(cadastrarSessaoDTO.getPauta())
                    .build();

            this.sessaoRepository.save(sessao);

            return getSessaoDTO(sessao);
        } catch (Exception ex) {
            throw new SessaoException(INTERNAL_SERVER_ERROR, this.messageHelper.get(MessageHelper.ErrorCode.SESSAO_ERROR));
        }
    }

    private SessaoDTO getSessaoDTO(Sessao sessao) {
        return SessaoDTO
                .builder()
                .idSessao(sessao.getId())
                .inicio(sessao.getDataInicio())
                .duracao(sessao.getDuracao())
                .pauta(sessao.getPauta())
                .build();
    }


    public SessaoDTO obterSessao(Long idSessao) {
        final var sessao = this.sessaoRepository.findById(idSessao)
                .orElseThrow(() -> new SessaoException(NOT_FOUND, this.messageHelper.get(MessageHelper.ErrorCode.SESSAO_NAO_ENCONTRADA)));

        return getSessaoDTO(sessao);
    }

    public Page<SessaoDTO> buscarSessao(String pauta, Pageable pageable) {
        final var sessaoSpecification = SessaoSpecification
                .builder()
                .pauta(Optional.ofNullable(pauta))
                .build();

        final var sessaoPage = this.sessaoRepository.findAll(sessaoSpecification, pageable);

        return sessaoPage.map(sessaoMap -> SessaoDTO
                .builder()
                .pauta(sessaoMap.getPauta())
                .inicio(sessaoMap.getDataInicio())
                .duracao(sessaoMap.getDuracao())
                .idSessao(sessaoMap.getId())
                .build());
    }
}
