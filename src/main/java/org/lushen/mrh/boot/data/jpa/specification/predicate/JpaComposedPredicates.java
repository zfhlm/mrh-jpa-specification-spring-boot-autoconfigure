package org.lushen.mrh.boot.data.jpa.specification.predicate;

/**
 * jpa 组合查询条件
 * 
 * @author hlm
 * @param <E>
 */
public interface JpaComposedPredicates<E, F extends JpaComposedPredicates<E, F>> extends 
			JpaBetweenPredicates<E, F>,
			JpaEqualOrNotPredicates<E, F>,
			JpaInOrNotPredicates<E, F>,
			JpaIsNullOrNotPredicates<E, F>,
			JpaLikeOrNotPredicates<E, F>,
			JpaThanPredicates<E, F> {

}
