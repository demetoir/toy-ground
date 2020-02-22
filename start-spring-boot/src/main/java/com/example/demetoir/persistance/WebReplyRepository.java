package com.example.demetoir.persistance;

import com.example.demetoir.domain.WebReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebReplyRepository extends JpaRepository<WebReply, Long> {
}
