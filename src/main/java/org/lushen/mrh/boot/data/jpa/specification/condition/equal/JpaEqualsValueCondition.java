package org.lushen.mrh.boot.data.jpa.specification.condition.equal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;

/**
 * [singular] = [value]
 * 
 * @author hlm
 * @param <E>
 */
public class JpaEqualsValueCondition<E> implements JpaCondition<E> {

	private String singular;

	private Object value;

	public JpaEqualsValueCondition(String singular, Object value) {
		super();
		this.singular = singular;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.equal(root.get(singular), value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(singular);
		builder.append(" = ");
		builder.append(value);
		return builder.toString();
	}

}
