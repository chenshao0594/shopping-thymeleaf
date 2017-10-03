package com.shoppay.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppay.common.domain.BusinessDomainInterface;
import com.shoppay.common.service.AbstractDomainService;

public abstract class AbstractDomainServiceImpl<E extends BusinessDomainInterface, K extends Serializable & Comparable<K>>
		implements AbstractDomainService<E, K> {

	private JpaRepository<E, K> repository;


	public AbstractDomainServiceImpl(JpaRepository<E, K> repository) {
		this.repository = repository;
	}

	@Override
	public E save(E entity) throws ServiceException {
		repository.save(entity);
		// searchRepository.save(entity);
		return entity;
	}

	@Override
	public void update(E entity) throws ServiceException {
		save(entity);
	}

	@Override
	public void delete(E entity) throws ServiceException {
		repository.delete(entity);
	}

	@Override
	public void delete(K id) throws ServiceException {
		repository.delete(id);
	}

	@Override
	public void flush() {
		repository.flush();
	}

	@Override
	public List<E> list() {
		return repository.findAll();
	}

	@Override
	public Long count() {
		return repository.count();
	}

	@Override
	public E findOne(K id) {
		return repository.findOne(id);
	}

	@Override
	public Page<E> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
