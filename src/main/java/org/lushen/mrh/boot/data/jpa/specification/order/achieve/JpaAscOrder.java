package org.lushen.mrh.boot.data.jpa.specification.order.achieve;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.lushen.mrh.boot.data.jpa.specification.order.JpaOrder;

/**
 * jpa asc 排序
 * 
 * @author hlm
 * @param <E>
 */
public class JpaAscOrder<E> implements JpaOrder<E> {

	private String singular;

	public JpaAscOrder(String singular) {
		super();
		this.singular = singular;
	}

	public Order toOrder(Root<E> root, CriteriaBuilder criteriaBuilder) {
		Path<?> path = root.get(singular);
		Order order = new OrderImpl(path, true);
		return order;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(singular);
		builder.append(" asc");
		return builder.toString();
	}

}
