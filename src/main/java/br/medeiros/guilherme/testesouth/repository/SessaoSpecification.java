package br.medeiros.guilherme.testesouth.repository;

import br.medeiros.guilherme.testesouth.entity.Sessao;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Value
@With
@Builder
public class SessaoSpecification implements Specification<Sessao> {

    @Builder.Default
    private transient Optional<String> pauta = Optional.empty();

    @Override
    public Predicate toPredicate(Root<Sessao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predcs = new ArrayList<>();

        this.pauta.ifPresent(p -> predcs.add(criteriaBuilder.equal(root.get("pauta"), p)));

        return criteriaBuilder.and(predcs.toArray(new Predicate[predcs.size()]));
    }
}
