package org.lushen.mrh.boot.data.jpa.specification.example;

import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.lushen.mrh.boot.data.jpa.specification.example.achieve.JpaExampleImpl;
import org.lushen.mrh.boot.data.jpa.specification.predicate.JpaAndPredicates;
import org.lushen.mrh.boot.data.jpa.specification.predicate.JpaOrPredicates;

/**
 * jpa 动态条件查询对象
 * 
 * @author hlm
 * @param <E>
 */
public interface JpaExample<E> {

	/**
	 * 创建 example对象
	 * 
	 * @return
	 */
	public static <E> JpaExample<E> from(Class<E> domainClass) {
		return new JpaExampleImpl<E>(domainClass);
	}

	/**
	 * distinct
	 * 
	 * @return
	 */
	JpaExample<E> distinct();

	/**
	 * where and条件查询对象
	 * 
	 * @return
	 */
	JpaAndPredicates<E> where();

	/**
	 * where or条件查询对象
	 * 
	 * @return
	 */
	JpaOrPredicates<E> whereOr();

	/**
	 * desc 排序条件
	 * 
	 * @param function
	 * @return
	 */
	<T> JpaExample<E> desc(Function<E, T> function);

	/**
	 * asc 排序条件
	 * 
	 * @param function
	 * @return
	 */
	<T> JpaExample<E> asc(Function<E, T> function);

	/**
	 * 转换为记录查询对象
	 * 
	 * @param criteriaBuilder
	 * @return
	 */
	CriteriaQuery<E> toQuery(CriteriaBuilder criteriaBuilder);

	/**
	 * 转换为总数查询对象
	 * 
	 * @param criteriaBuilder
	 * @return
	 */
	CriteriaQuery<Long> toCountQuery(CriteriaBuilder criteriaBuilder);

}
