package org.lushen.mrh.boot.data.jpa.specification.condition.nested;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;

/**
 * [condition 1] and [condition 2] and ...
 * 
 * @author hlm
 * @param <E>
 */
public class JpaAndCondition<E> implements JpaCondition<E> {

	private List<JpaCondition<E>> conditions;

	public JpaAndCondition(List<JpaCondition<E>> conditions) {
		super();
		this.conditions = conditions;
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder) {
		if(conditions != null && ! conditions.isEmpty()) {
			List<Predicate> predicates = conditions.stream().map(e -> e.toPredicate(root, criteriaBuilder)).filter(e -> e != null).collect(Collectors.toList());
			if(! predicates.isEmpty()) {
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("( ");
		builder.append(conditions.stream().map(Object::toString).collect(Collectors.joining(" and ")));
		builder.append(" )");
		return builder.toString();
	}

}
