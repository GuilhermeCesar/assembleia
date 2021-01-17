package br.medeiros.guilherme.testesouth.service;

import br.medeiros.guilherme.testesouth.dto.ContagemVotacaoDTO;
import br.medeiros.guilherme.testesouth.dto.VotoDTO;
import br.medeiros.guilherme.testesouth.dto.VotoFinalizadoDTO;
import br.medeiros.guilherme.testesouth.entity.Associado;
import br.medeiros.guilherme.testesouth.entity.Sessao;
import br.medeiros.guilherme.testesouth.entity.Voto;
import br.medeiros.guilherme.testesouth.entity.VotoId;
import br.medeiros.guilherme.testesouth.exception.VotoException;
import br.medeiros.guilherme.testesouth.helper.MessageHelper;
import br.medeiros.guilherme.testesouth.repository.AssociadoRepository;
import br.medeiros.guilherme.testesouth.repository.SessaoRepository;
import br.medeiros.guilherme.testesouth.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final SessaoRepository sessaoRepository;
    private final AssociadoRepository associadoRepository;
    private final MessageHelper messageHelper;

    public VotoFinalizadoDTO votar(Long idSessao, VotoDTO votoDTO) {
        final var sessao = this.sessaoRepository.findById(idSessao)
                .orElseThrow(() -> new VotoException(NOT_FOUND, this.messageHelper.get(MessageHelper.ErrorCode.ERROR_SESSAO_NAO_ENCONTRADA)));

        final var associado = this.associadoRepository.findById(votoDTO.getIdAssociado())
                .orElseThrow(() -> new VotoException(NOT_FOUND, this.messageHelper.get(MessageHelper.ErrorCode.ERROR_ASSOCIADO_NAO_ENCONTRADO)));

        validarSeVotacao(sessao, associado);

        final var voto = Voto.builder()
                .associadoId(associado.getId())
                .sessaoId(sessao.getId())
                .aprovado(votoDTO.getVoto())
                .build();
        this.votoRepository.save(voto);
        var votoFinalizadoDTO = contagemDeVotos(sessao);

        return votoFinalizadoDTO
                .withPauta(sessao.getPauta());
    }

    private void validarSeVotacao(Sessao sessao, Associado associado) {
        final var votoId = VotoId
                .builder()
                .associadoId(associado.getId())
                .sessaoId(sessao.getId())
                .build();

        if (LocalDateTime.now().isAfter(sessao.getFinalSessao())) {
            throw new VotoException(INTERNAL_SERVER_ERROR, this.messageHelper.get(MessageHelper.ErrorCode.ERROR_SESSAO_FINALIZADA));
        }

        this.votoRepository.findById(votoId)
                .ifPresent(votoSalvo -> {
                    throw new VotoException(CONFLICT, this.messageHelper.get(MessageHelper.ErrorCode.ERROR_ASSOCIADO_VOTOU_SESSAO));
                });
    }


    public VotoFinalizadoDTO contagemVotos(Long idSessao) {
        final var sessao = this.sessaoRepository.findById(idSessao)
                .orElseThrow(() -> new VotoException(NOT_FOUND, this.messageHelper.get(MessageHelper.ErrorCode.ERROR_SESSAO_NAO_ENCONTRADA)));

        var votoFinalizadoDTO = contagemDeVotos(sessao);

        return votoFinalizadoDTO
                .withPauta(sessao.getPauta());
    }

    private VotoFinalizadoDTO contagemDeVotos(Sessao sessao) {
        var votos = this.votoRepository.contaVotacao(sessao.getId());

        final var quantidadeSim = votos
                .stream()
                .filter(ContagemVotacaoDTO::getAprovado)
                .mapToLong(ContagemVotacaoDTO::getQuantidade)
                .findAny()
                .orElse(0);

        final var quantidadeNao = votos
                .stream()
                .filter(contaVotos -> !contaVotos.getAprovado())
                .mapToLong(ContagemVotacaoDTO::getQuantidade)
                .findAny()
                .orElse(0);

        return VotoFinalizadoDTO.builder()
                .contagemSim(quantidadeSim)
                .contagemNao(quantidadeNao)
                .build();
    }


}
