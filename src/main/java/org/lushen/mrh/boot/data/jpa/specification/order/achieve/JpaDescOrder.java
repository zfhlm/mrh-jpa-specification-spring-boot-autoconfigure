package org.lushen.mrh.boot.data.jpa.specification.order.achieve;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.lushen.mrh.boot.data.jpa.specification.order.JpaOrder;

/**
 * jpa desc 排序
 * 
 * @author hlm
 * @param <E>
 */
public class JpaDescOrder<E> implements JpaOrder<E> {

	private String singular;

	public JpaDescOrder(String singular) {
		super();
		this.singular = singular;
	}

	public Order toOrder(Root<E> root, CriteriaBuilder criteriaBuilder) {
		Path<?> path = root.get(singular);
		Order order = new OrderImpl(path, false);
		return order;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(singular);
		builder.append(" desc");
		return builder.toString();
	}

}
