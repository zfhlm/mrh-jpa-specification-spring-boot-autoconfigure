package org.lushen.mrh.boot.data.jpa.specification.condition.between;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;

/**
 * [singular] between [xSingular] and [ySingular]
 * 
 * @author hlm
 * @param <E>
 */
public class JpaBetweenCondition<E> implements JpaCondition<E> {

	private String singular;

	private String xSingular;

	private String ySingular;

	public JpaBetweenCondition(String singular, String xSingular, String ySingular) {
		super();
		this.singular = singular;
		this.xSingular = xSingular;
		this.ySingular = ySingular;
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.between(root.get(this.singular), root.get(this.xSingular), root.get(this.ySingular));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(singular);
		builder.append(" between ");
		builder.append(xSingular);
		builder.append(" and ");
		builder.append(ySingular);
		return builder.toString();
	}

}
