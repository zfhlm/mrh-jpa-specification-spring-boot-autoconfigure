package org.lushen.mrh.boot.data.jpa.specification.condition.than;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;

/**
 * [xSingular] >= [ySingular]
 * 
 * @author hlm
 * @param <E>
 */
public class JpaMoreThanOrEqualsCondition<E> implements JpaCondition<E> {

	private String xSingular;

	private String ySingular;

	public JpaMoreThanOrEqualsCondition(String xSingular, String ySingular) {
		super();
		this.xSingular = xSingular;
		this.ySingular = ySingular;
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.greaterThanOrEqualTo(root.get(xSingular), root.get(ySingular));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(xSingular);
		builder.append(" >= ");
		builder.append(ySingular);
		return builder.toString();
	}

}
