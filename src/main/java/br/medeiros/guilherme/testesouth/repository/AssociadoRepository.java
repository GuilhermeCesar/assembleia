package br.medeiros.guilherme.testesouth.repository;

import br.medeiros.guilherme.testesouth.entity.Associado;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AssociadoRepository extends PagingAndSortingRepository<Associado, Long> , JpaSpecificationExecutor<Associado> {



}
