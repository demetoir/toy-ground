package com.example.demetoir.persistance;

import com.example.demetoir.domain.QWebBoard;
import com.example.demetoir.domain.QWebReply;
import com.example.demetoir.domain.WebBoard;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;

@Log
public class CustomCrudRepositoryImpl extends QuerydslRepositorySupport implements CustomWebBoard {
  public CustomCrudRepositoryImpl() {
    super(WebBoard.class);
  }

  @Override
  public Page<Object[]> getCustomPage(String type, String keyword, Pageable pageable) {
    log.info("=================");
    log.info("type " + type);
    log.info("keyword " + keyword);
    log.info("page " + pageable);
    log.info("=================");

    QWebBoard b = QWebBoard.webBoard;
    QWebReply r = QWebReply.webReply;
    JPQLQuery<WebBoard> query = from(b);

    JPQLQuery<Tuple> tuple = query.select(b.bno, b.title, r.count(), b.writer, b.regDate);

    tuple.leftJoin(r);
    tuple.on(b.bno.eq(r.board.bno));
    tuple.where(b.bno.gt(0L));

    tuple.groupBy(b.bno);
    tuple.orderBy(b.bno.desc());

    if (type != null) {
      switch (type) {
        case "t":
          {
            tuple.where(b.title.like("%" + keyword + "%"));
            break;
          }
        case "c":
          {
            tuple.where(b.content.like("%" + keyword + "%"));
            break;
          }
        case "w":
          {
            tuple.where(b.writer.like("%" + keyword + "%"));
            break;
          }
      }
    }

    tuple.offset(pageable.getOffset());
    tuple.limit(pageable.getPageSize());

    List<Tuple> list = tuple.fetch();

    List<Object[]> resultList = new ArrayList<>();

    list.forEach(
        t -> {
          resultList.add(t.toArray());
        });

    long total = tuple.fetchCount();

    return new PageImpl<>(resultList, pageable, total);
  }
}
