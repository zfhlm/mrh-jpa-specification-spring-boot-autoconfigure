package org.lushen.mrh.boot.data.jpa.specification.condition.isnull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;

/**
 * [singular] is null
 * 
 * @author hlm
 * @param <E>
 */
public class JpaIsNullCondition<E> implements JpaCondition<E> {

	private String singular;

	public JpaIsNullCondition(String singular) {
		super();
		this.singular = singular;
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder) {
		Path<?> path = root.get(singular);
		return criteriaBuilder.isNull(path);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(singular);
		builder.append(" is null");
		return builder.toString();
	}

}
