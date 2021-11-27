package org.lushen.mrh.boot.data.jpa.specification.predicate;

/**
 * jpa and 查询条件
 * 
 * @author hlm
 * @param <E>
 */
public interface JpaAndPredicates<E> extends JpaComposedPredicates<E, JpaAndPredicates<E>>, JpaNestedPredicates<JpaOrPredicates<E>, JpaAndPredicates<E>> {}
