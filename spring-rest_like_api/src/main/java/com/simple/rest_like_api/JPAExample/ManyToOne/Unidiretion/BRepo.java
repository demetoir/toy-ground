package com.simple.rest_like_api.JPAExample.ManyToOne.Unidiretion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BRepo extends JpaRepository<B, Long> {}
