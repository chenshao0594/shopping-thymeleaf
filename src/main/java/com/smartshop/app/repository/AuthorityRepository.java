package com.smartshop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartshop.core.user.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
