package org.lushen.mrh.boot.data.jpa.specification.condition.in;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;

/**
 * [singular] in ([value 1], [value 2], ...)
 * 
 * @author hlm
 * @param <E>
 * @param <S>
 */
public class JpaInCondition<E, S> implements JpaCondition<E> {

	private String singular;

	private List<S> values;

	public JpaInCondition(String singular, List<S> values) {
		super();
		this.singular = singular;
		this.values = values;
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder) {
		Path<S> path = root.get(singular);
		In<S> in = criteriaBuilder.in(path);
		for(S value : values) {
			in.value(value);
		}
		return in;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(singular);
		builder.append(" in (");
		builder.append(values.stream().map(Object::toString).collect(Collectors.joining(", ")));
		builder.append(")");
		return builder.toString();
	}

}
