package com.shoppay.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppay.core.user.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
