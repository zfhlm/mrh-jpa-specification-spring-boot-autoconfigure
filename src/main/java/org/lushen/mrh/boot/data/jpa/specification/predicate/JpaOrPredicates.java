package org.lushen.mrh.boot.data.jpa.specification.predicate;

/**
 * jpa or 查询条件
 * 
 * @author hlm
 * @param <E>
 */
public interface JpaOrPredicates<E> extends JpaComposedPredicates<E, JpaOrPredicates<E>>, JpaNestedPredicates<JpaOrPredicates<E>, JpaAndPredicates<E>> {}
