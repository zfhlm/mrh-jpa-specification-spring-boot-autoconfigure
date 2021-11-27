package org.lushen.mrh.boot.data.jpa.specification.predicate;

/**
 * where 嵌套 方法定义接口
 * 
 * @author hlm
 * @param <X>
 * @param <Y>
 */
public interface JpaNestedPredicates<X extends JpaNestedPredicates<X, Y>, Y extends JpaNestedPredicates<X, Y>> {

	/**
	 * or 条件
	 * 
	 * @return
	 */
	X or();

	/**
	 * and 条件
	 * 
	 * @return
	 */
	Y and();

}
