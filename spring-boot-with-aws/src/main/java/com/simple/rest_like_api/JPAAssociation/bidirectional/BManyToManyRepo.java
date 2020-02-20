package com.simple.rest_like_api.JPAAssociation.bidirectional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BManyToManyRepo  extends JpaRepository<BManyToMany, Long> {
}
