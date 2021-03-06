package org.lushen.mrh.boot.data.jpa.specification.condition;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * jpa 查询条件对象
 * 
 * @author hlm
 * @param <E>
 */
public interface JpaCondition<E> {

	/**
	 * 生成 jpa Predicate
	 * 
	 * @param root
	 * @param criteriaBuilder
	 */
	Predicate toPredicate(Root<E> root, CriteriaBuilder criteriaBuilder);

}
