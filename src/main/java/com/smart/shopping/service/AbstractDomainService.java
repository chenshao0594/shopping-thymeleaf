package com.smart.shopping.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smart.shopping.domain.common.BusinessDomain;

public interface AbstractDomainService<E extends BusinessDomain, K extends Serializable & Comparable<K>> {

	E save(E entity);

	void update(E entity);

	void delete(K id);

	void delete(E entity);

	List<E> list();

	Page<E> findAll(Pageable pageable);

	Long count();

	void flush();

	Page<E> search(String query, Pageable pageable);

	E getById(K id);

	E findOne(K id);
}
