package org.lushen.mrh.boot.data.jpa.specification.example.achieve;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;
import org.lushen.mrh.boot.data.jpa.specification.example.JpaExample;
import org.lushen.mrh.boot.data.jpa.specification.order.JpaOrder;
import org.lushen.mrh.boot.data.jpa.specification.order.achieve.JpaAscOrder;
import org.lushen.mrh.boot.data.jpa.specification.order.achieve.JpaDescOrder;
import org.lushen.mrh.boot.data.jpa.specification.predicate.JpaAndPredicates;
import org.lushen.mrh.boot.data.jpa.specification.predicate.JpaOrPredicates;
import org.lushen.mrh.boot.data.jpa.specification.predicate.achieve.JpaAndPredicatesImpl;
import org.lushen.mrh.boot.data.jpa.specification.supports.ClassFieldReader;
import org.lushen.mrh.boot.data.jpa.specification.supports.ProxyInvocation;
import org.lushen.mrh.boot.data.jpa.specification.supports.ProxyInvoker;
import org.lushen.mrh.boot.data.jpa.specification.supports.invoker.DefaultProxyInvoker;
import org.lushen.mrh.boot.data.jpa.specification.supports.reader.CacheFieldReader;

/**
 * jpa 动态条件查询对象实现类
 * 
 * @author hlm
 * @param <E>
 */
public final class JpaExampleImpl<E> implements JpaExample<E> {

	private static final ClassFieldReader FIELD_READER = new CacheFieldReader();

	protected final ProxyInvoker<E> proxyInvoker;

	private final Class<E> domainClass;

	private boolean distinct = false;

	private final List<JpaCondition<E>> conditions = new ArrayList<JpaCondition<E>>();

	private final List<JpaOrder<E>> orders = new ArrayList<JpaOrder<E>>();

	public JpaExampleImpl(Class<E> domainClass) {
		super();
		this.domainClass = domainClass;
		this.proxyInvoker = new DefaultProxyInvoker<E>(domainClass);
	}

	protected String nameForGetter(Function<E, ?> function) {
		ProxyInvocation<E> invocation = proxyInvoker.invoke(function);
		return FIELD_READER.nameForGetter(invocation);
	}

	@Override
	public JpaExample<E> distinct() {
		this.distinct = true;
		return this;
	}

	@Override
	public JpaAndPredicates<E> where() {
		return new JpaAndPredicatesImpl<E>(proxyInvoker, conditions);
	}

	@Override
	public JpaOrPredicates<E> whereOr() {
		return where().or();
	}

	@Override
	public <T> JpaExample<E> desc(Function<E, T> function) {
		orders.add(new JpaDescOrder<E>(nameForGetter(function)));
		return this;
	}

	@Override
	public <T> JpaExample<E> asc(Function<E, T> function) {
		orders.add(new JpaAscOrder<E>(nameForGetter(function)));
		return this;
	}

	@Override
	public CriteriaQuery<E> toQuery(CriteriaBuilder criteriaBuilder) {

		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(domainClass);
		Root<E> root = criteriaQuery.from(domainClass);

		//distinct
		if(distinct) {
			criteriaQuery.distinct(true);
		}

		//where
		if( ! conditions.isEmpty()) {
			List<Predicate> predicates = conditions.stream().map(e -> e.toPredicate(root, criteriaBuilder)).collect(Collectors.toList());
			criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		}

		//order by
		if( ! orders.isEmpty()) {
			criteriaQuery.orderBy(orders.stream().map(e -> e.toOrder(root, criteriaBuilder)).collect(Collectors.toList()));
		}

		return criteriaQuery;
	}

	@Override
	public CriteriaQuery<Long> toCountQuery(CriteriaBuilder criteriaBuilder) {

		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<E> root = criteriaQuery.from(domainClass);

		//distinct
		if (distinct) {
			criteriaQuery.select(criteriaBuilder.countDistinct(root));
		} else {
			criteriaQuery.select(criteriaBuilder.count(root));
		}

		//where
		if( ! conditions.isEmpty()) {
			List<Predicate> predicates = conditions.stream().map(e -> e.toPredicate(root, criteriaBuilder)).collect(Collectors.toList());
			criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		}

		return criteriaQuery;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(distinct) {
			builder.append("distinct ");
		}
		builder.append("from ");
		builder.append(domainClass.getSimpleName());
		if( ! conditions.isEmpty() ) {
			builder.append(" where ");
			builder.append(conditions.stream().map(Object::toString).collect(Collectors.joining(" and ")));
		}
		if( ! orders.isEmpty() ) {
			builder.append(" order by ");
			builder.append(orders.stream().map(Object::toString).collect(Collectors.joining(", ")));
		}
		return builder.toString();
	}

}
