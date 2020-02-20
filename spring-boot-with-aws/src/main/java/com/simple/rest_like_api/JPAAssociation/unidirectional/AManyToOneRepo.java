package com.simple.rest_like_api.JPAAssociation.unidirectional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AManyToOneRepo extends JpaRepository<AManyToOne, Long> {}
