package org.lushen.mrh.boot.data.jpa.specification.predicate;

import java.util.function.Function;

/**
 * jpa where between 方法定义接口
 * 
 * @author hlm
 * @param <E>
 * @param <X>
 */
@SuppressWarnings("unchecked")
public interface JpaBetweenPredicates<E, X extends JpaBetweenPredicates<E, X>> {

	/**
	 * between ... and ...
	 * 
	 * @param function
	 * @param begin
	 * @param end
	 * @return
	 */
	<T extends Comparable<T>> X between(Function<E, T> function, T begin, T end);

	/**
	 * between ... and ... 当 begin 或 end 等于 null 不生效
	 * 
	 * @param function
	 * @param begin
	 * @param end
	 * @return
	 */
	default <T extends Comparable<T>> X betweenIgnoreNull(Function<E, T> function, T begin, T end) {
		if(begin != null && end != null) {
			between(function, begin, end);
		}
		return (X) this;
	}

}
