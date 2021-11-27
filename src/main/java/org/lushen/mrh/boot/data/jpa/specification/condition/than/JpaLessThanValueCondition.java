package org.lushen.mrh.boot.data.jpa.specification.condition.than;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;

/**
 * [singular] < [value]
 * 
 * @author hlm
 * @param <E>
 * @param <S>
 */
public class JpaLessThanValueCondition<E, S extends Comparable<S>> implements JpaCondition<E> {

	private String singular;

	private S value;

	public JpaLessThanValueCondition(String singular, S value) {
		super();
		this.singular = singular;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder) {
		Path<S> path = root.get(singular);
		return criteriaBuilder.lessThan(path, value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(singular);
		builder.append(" < ");
		builder.append(value);
		return builder.toString();
	}

}
