package org.lushen.mrh.boot.data.jpa.specification.predicate;

import java.util.function.Function;

/**
 * jpa where is-null/is-not-null 方法定义接口
 * 
 * @author hlm
 * @param <E>
 * @param <X>
 */
public interface JpaIsNullOrNotPredicates<E, X extends JpaIsNullOrNotPredicates<E, X>> {

	/**
	 * is null 条件
	 * 
	 * @param function
	 * @return
	 */
	<T> X isNull(Function<E, T> function);

	/**
	 * is not null 条件
	 * 
	 * @param function
	 * @return
	 */
	<T> X isNotNull(Function<E, T> function);

}
