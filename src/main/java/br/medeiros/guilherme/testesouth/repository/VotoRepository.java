package br.medeiros.guilherme.testesouth.repository;

import br.medeiros.guilherme.testesouth.dto.ContagemVotacaoDTO;
import br.medeiros.guilherme.testesouth.entity.Associado;
import br.medeiros.guilherme.testesouth.entity.Voto;
import br.medeiros.guilherme.testesouth.entity.VotoId;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VotoRepository extends PagingAndSortingRepository<Voto, VotoId>, JpaSpecificationExecutor<Associado> {

    Long countBySessaoIdAndAprovado(Long idSessao, Boolean aprovado);

    @Query("""
            select new br.medeiros.guilherme.testesouth.dto.ContagemVotacaoDTO(voto.aprovado, COUNT(voto.aprovado))
            FROM Voto as voto            
            WHERE voto.sessaoId = :idSessao
            GROUP BY voto.aprovado
            """)
    List<ContagemVotacaoDTO> contaVotacao(@Param("idSessao") Long idSessao);
}
