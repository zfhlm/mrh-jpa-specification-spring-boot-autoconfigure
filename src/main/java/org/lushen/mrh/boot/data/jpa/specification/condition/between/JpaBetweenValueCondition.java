package org.lushen.mrh.boot.data.jpa.specification.condition.between;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;

/**
 * [singular] between [begin] and [end]
 * 
 * @author hlm
 * @param <E>
 * @param <S>
 */
public class JpaBetweenValueCondition<E, S extends Comparable<S>> implements JpaCondition<E> {

	private String singular;

	private S begin;

	private S end;

	public JpaBetweenValueCondition(String singular, S begin, S end) {
		super();
		this.singular = singular;
		this.begin = begin;
		this.end = end;
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.between(root.get(this.singular), this.begin, this.end);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(singular);
		builder.append(" between ");
		builder.append(begin);
		builder.append(" and ");
		builder.append(end);
		return builder.toString();
	}

}
