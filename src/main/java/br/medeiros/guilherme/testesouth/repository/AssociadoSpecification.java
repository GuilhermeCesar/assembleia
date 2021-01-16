package br.medeiros.guilherme.testesouth.repository;

import br.medeiros.guilherme.testesouth.entity.Associado;
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
public class AssociadoSpecification implements Specification<Associado> {

    @Builder.Default
    private transient Optional<Long> idAssociado = Optional.empty();
    @Builder.Default
    private transient Optional<String> nome = Optional.empty();
    @Builder.Default
    private transient Optional<String> cpf = Optional.empty();

    @Override
    public Predicate toPredicate(Root<Associado> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predcs = new ArrayList<>();

        this.idAssociado.ifPresent(idAssociado -> predcs.add(criteriaBuilder.equal(root.get("id"), idAssociado)));
        this.nome.ifPresent(nome -> predcs.add(criteriaBuilder.equal(root.get("nome"), nome)));
        this.cpf.ifPresent(cpf -> predcs.add(criteriaBuilder.equal(root.get("cpf"), cpf)));

        return criteriaBuilder.and(predcs.toArray(new Predicate[predcs.size()]));
    }
}
