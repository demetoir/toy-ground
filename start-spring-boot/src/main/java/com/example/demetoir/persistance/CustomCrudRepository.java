package com.example.demetoir.persistance;

import com.example.demetoir.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

public interface CustomCrudRepository extends CrudRepository<WebBoard, Long>, CustomWebBoard {}
