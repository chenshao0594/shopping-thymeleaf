package com.shoppay.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppay.common.user.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
