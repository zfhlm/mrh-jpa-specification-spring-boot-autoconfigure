package org.lushen.mrh.boot.data.jpa.specification.predicate.achieve;

import java.util.List;
import java.util.function.Function;

import org.lushen.mrh.boot.data.jpa.specification.condition.JpaCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.between.JpaBetweenValueCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.equal.JpaEqualsCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.equal.JpaEqualsValueCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.equal.JpaNotEqualsCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.equal.JpaNotEqualsValueCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.in.JpaInCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.in.JpaNotInCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.isnull.JpaIsNotNullCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.isnull.JpaIsNullCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.like.JpaFullLikeCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.like.JpaLeftLikeCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.like.JpaNotFullLikeCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.like.JpaNotLeftLikeCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.like.JpaNotRightLikeCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.like.JpaRightLikeCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.than.JpaLessThanOrEqualsValueCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.than.JpaLessThanValueCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.than.JpaMoreThanOrEqualsValueCondition;
import org.lushen.mrh.boot.data.jpa.specification.condition.than.JpaMoreThanValueCondition;
import org.lushen.mrh.boot.data.jpa.specification.predicate.JpaComposedPredicates;
import org.lushen.mrh.boot.data.jpa.specification.supports.ClassFieldReader;
import org.lushen.mrh.boot.data.jpa.specification.supports.ProxyInvocation;
import org.lushen.mrh.boot.data.jpa.specification.supports.ProxyInvoker;
import org.lushen.mrh.boot.data.jpa.specification.supports.reader.CacheFieldReader;

/**
 * jpa 组合查询条件实现类
 * 
 * @author hlm
 * @param <E>
 * @param <F>
 */
@SuppressWarnings("unchecked")
public abstract class JpaComposedPredicatesImpl<E, F extends JpaComposedPredicates<E, F>> implements JpaComposedPredicates<E, F> {

	protected static final ClassFieldReader FIELD_READER = new CacheFieldReader();

	protected ProxyInvoker<E> proxyInvoker;

	protected final List<JpaCondition<E>> conditions;

	public JpaComposedPredicatesImpl(ProxyInvoker<E> proxyInvoker, List<JpaCondition<E>> conditions) {
		super();
		this.proxyInvoker = proxyInvoker;
		this.conditions = conditions;
	}

	protected String nameForGetter(Function<E, ?> function) {
		ProxyInvocation<E> invocation = proxyInvoker.invoke(function);
		return FIELD_READER.nameForGetter(invocation);
	}

	@Override
	public <T extends Comparable<T>> F moreThan(Function<E, T> function, T value) {
		conditions.add(new JpaMoreThanValueCondition<E, T>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public <T extends Comparable<T>> F moreThanOrEqual(Function<E, T> function, T value) {
		conditions.add(new JpaMoreThanOrEqualsValueCondition<E, T>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public <T extends Comparable<T>> F lessThan(Function<E, T> function, T value) {
		conditions.add(new JpaLessThanValueCondition<E, T>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public <T extends Comparable<T>> F lessThanOrEqual(Function<E, T> function, T value) {
		conditions.add(new JpaLessThanOrEqualsValueCondition<E, T>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public F leftLike(Function<E, ?> function, String value) {
		conditions.add(new JpaLeftLikeCondition<E>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public F rightLike(Function<E, ?> function, String value) {
		conditions.add(new JpaRightLikeCondition<E>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public F fullLike(Function<E, ?> function, String value) {
		conditions.add(new JpaFullLikeCondition<E>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public F notLeftLike(Function<E, ?> function, String value) {
		conditions.add(new JpaNotLeftLikeCondition<E>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public F notRightLike(Function<E, ?> function, String value) {
		conditions.add(new JpaNotRightLikeCondition<E>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public F notFullLike(Function<E, ?> function, String value) {
		conditions.add(new JpaNotFullLikeCondition<E>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public <T> F isNull(Function<E, T> function) {
		conditions.add(new JpaIsNullCondition<E>(nameForGetter(function)));
		return (F)this;
	}

	@Override
	public <T> F isNotNull(Function<E, T> function) {
		conditions.add(new JpaIsNotNullCondition<E>(nameForGetter(function)));
		return (F)this;
	}

	@Override
	public <T> F in(Function<E, T> function, List<T> values) {
		conditions.add(new JpaInCondition<E, T>(nameForGetter(function), values));
		return (F)this;
	}

	@Override
	public <T> F notIn(Function<E, T> function, List<T> values) {
		conditions.add(new JpaNotInCondition<E, T>(nameForGetter(function), values));
		return (F)this;
	}

	@Override
	public <T> F equal(Function<E, T> function, T value) {
		conditions.add(new JpaEqualsValueCondition<E>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public <T> F notEqual(Function<E, T> function, T value) {
		conditions.add(new JpaNotEqualsValueCondition<E>(nameForGetter(function), value));
		return (F)this;
	}

	@Override
	public <T> F equal(Function<E, T> function, Function<E, T> otherFunction) {
		conditions.add(new JpaEqualsCondition<E>(nameForGetter(function), nameForGetter(otherFunction)));
		return (F)this;
	}

	@Override
	public <T> F notEqual(Function<E, T> function, Function<E, T> otherFunction) {
		conditions.add(new JpaNotEqualsCondition<E>(nameForGetter(function), nameForGetter(otherFunction)));
		return (F)this;
	}

	@Override
	public <T extends Comparable<T>> F between(Function<E, T> function, T begin, T end) {
		conditions.add(new JpaBetweenValueCondition<E, T>(nameForGetter(function), begin, end));
		return (F)this;
	}

}
