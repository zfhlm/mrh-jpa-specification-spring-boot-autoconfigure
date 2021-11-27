package org.lushen.mrh.boot.data.jpa.specification.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;
import org.lushen.mrh.boot.data.jpa.specification.predicate.achieve.JpaAndPredicatesImpl;
import org.lushen.mrh.boot.data.jpa.specification.service.HelloEntity;
import org.lushen.mrh.boot.data.jpa.specification.supports.ProxyInvoker;
import org.lushen.mrh.boot.data.jpa.specification.supports.invoker.DefaultProxyInvoker;

public class TestPredicate {

	public static void main(String[] args) {

		ProxyInvoker<HelloEntity> proxyInvoker = new DefaultProxyInvoker<HelloEntity>(HelloEntity.class);
		List<JpaCondition<HelloEntity>> conditions = new ArrayList<JpaCondition<HelloEntity>>();

		JpaAndPredicates<HelloEntity> andPredicates = new JpaAndPredicatesImpl<HelloEntity>(proxyInvoker, conditions);
		andPredicates.equal(HelloEntity::getId, "123");
		andPredicates.in(HelloEntity::getGender, Arrays.asList(1, 2, 3));
		andPredicates.or().equal(HelloEntity::getUsername, "张三").equal(HelloEntity::getUsername, "李四");

		System.out.println(andPredicates);

	}

}
