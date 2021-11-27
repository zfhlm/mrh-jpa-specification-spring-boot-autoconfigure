package org.lushen.mrh.boot.data.jpa.specification.condition.isnull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;

/**
 * [singular] is not null
 * 
 * @author hlm
 * @param <E>
 */
public class JpaIsNotNullCondition<E> implements JpaCondition<E> {

	private String singular;

	public JpaIsNotNullCondition(String singular) {
		super();
		this.singular = singular;
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder) {
		Path<?> path = root.get(singular);
		return criteriaBuilder.isNotNull(path);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(singular);
		builder.append(" is not null");
		return builder.toString();
	}

}
