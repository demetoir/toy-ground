package com.example.demetoir.persistance;

import com.example.demetoir.domain.WebBoard;
import com.example.demetoir.domain.WebReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WebReplyRepository extends JpaRepository<WebReply, Long> {

  // todo change to query dls
  @Query("select r from WebReply r where r.board = ?1 and r.rno > 0 order by r.rno ASC")
  List<WebReply> getWebRepliesOfBoard(WebBoard board);
}
