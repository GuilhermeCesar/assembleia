package br.medeiros.guilherme.testesouth.repository;

import br.medeiros.guilherme.testesouth.entity.Sessao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SessaoRepository extends PagingAndSortingRepository<Sessao, Long>, JpaSpecificationExecutor<Sessao> {
}
