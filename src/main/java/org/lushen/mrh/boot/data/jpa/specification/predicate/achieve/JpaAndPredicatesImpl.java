package org.lushen.mrh.boot.data.jpa.specification.predicate.achieve;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.nested.JpaAndCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.nested.JpaOrCondition;
import org.lushen.mrh.boot.data.jpa.specification.predicate.JpaAndPredicates;
import org.lushen.mrh.boot.data.jpa.specification.predicate.JpaOrPredicates;
import org.lushen.mrh.boot.data.jpa.specification.supports.ProxyInvoker;

/**
 * jpa and 表达式实现类
 * 
 * @author hlm
 * @param <E>
 */
public class JpaAndPredicatesImpl<E> extends JpaComposedPredicatesImpl<E, JpaAndPredicates<E>> implements JpaAndPredicates<E> {

	public JpaAndPredicatesImpl(ProxyInvoker<E> proxyInvoker, List<JpaCondition<E>> conditions) {
		super(proxyInvoker, conditions);
	}

	@Override
	public JpaOrPredicates<E> or() {
		List<JpaCondition<E>> conditionsInOr = new ArrayList<JpaCondition<E>>();
		conditions.add(new JpaOrCondition<E>(conditionsInOr));
		return new JpaOrPredicatesImpl<E>(proxyInvoker, conditionsInOr);
	}

	@Override
	public JpaAndPredicates<E> and() {
		List<JpaCondition<E>> conditionsInAnd = new ArrayList<JpaCondition<E>>();
		conditions.add(new JpaAndCondition<E>(conditionsInAnd));
		return new JpaAndPredicatesImpl<E>(proxyInvoker, conditionsInAnd);
	}

	@Override
	public String toString() {
		return conditions.stream().map(Object::toString).collect(Collectors.joining(" and "));
	}

}
