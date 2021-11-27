package org.lushen.mrh.boot.data.jpa.specification.condition.like;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;

/**
 * [singular] not like '%[value]%'
 * 
 * @author hlm
 * @param <E>
 */
public class JpaNotFullLikeCondition<E> implements JpaCondition<E> {

	private String singular;

	private String value;

	public JpaNotFullLikeCondition(String singular, String value) {
		super();
		this.singular = singular;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder) {
		Path<String> path = root.get(singular);
		String like = new StringBuilder().append("%").append(value).append("%").toString();
		return criteriaBuilder.notLike(path, like);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(singular);
		builder.append(" not like '%");
		builder.append(value);
		builder.append("%'");
		return builder.toString();
	}

}
