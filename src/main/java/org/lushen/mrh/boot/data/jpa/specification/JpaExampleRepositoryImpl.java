package org.lushen.mrh.boot.data.jpa.specification;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.lushen.mrh.boot.data.jpa.specification.example.JpaExample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * jpa 自定义查询接口实现
 * 
 * @author hlm
 * @param <T>
 * @param <ID>
 */
public class JpaExampleRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements JpaExampleRepository<T, ID> {

	private EntityManager entityManager;

	public JpaExampleRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	public JpaExampleRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public T findWithId(ID id) {
		return findById(id).orElse(null);
	}

	@Override
	public T findOne(JpaExample<T> example) {
		TypedQuery<T> query = createQuery(example, Pageable.unpaged());
		return query.getSingleResult();
	}

	@Override
	public T findFirst(JpaExample<T> example) {
		TypedQuery<T> query = createQuery(example, PageRequest.of(0, 1));
		List<T> resultList = query.getResultList();
		return resultList.isEmpty()? null : resultList.get(0);
	}

	@Override
	public List<T> findAll(JpaExample<T> example) {
		TypedQuery<T> query = createQuery(example, null);
		return query.getResultList();
	}

	@Override
	public Page<T> findPage(JpaExample<T> example, Pageable pageable) {
		TypedQuery<T> query = createQuery(example, pageable);
		return new PageImpl<T>(query.getResultList(), pageable, count(example));
	}

	@Override
	public long count(JpaExample<T> example) {
		TypedQuery<Long> query = createCountQuery(example);
		long result = query.getSingleResult();
		return result;
	}

	/**
	 * 生成查询对象
	 * 
	 * @param example
	 * @param pageable
	 * @return
	 */
	private TypedQuery<T> createQuery(JpaExample<T> example, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		TypedQuery<T> query =  this.entityManager.createQuery(example.toQuery(criteriaBuilder));
		if(pageable != null && pageable.isPaged()) {
			query.setFirstResult((int) pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
		return query;
	}

	/**
	 * 生成总数查询对象
	 * 
	 * @param example
	 * @return
	 */
	private TypedQuery<Long> createCountQuery(JpaExample<T> example) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = example.toCountQuery(criteriaBuilder);
		return this.entityManager.createQuery(criteriaQuery);
	}

}
