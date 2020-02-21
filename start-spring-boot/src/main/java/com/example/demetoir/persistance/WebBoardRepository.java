package com.example.demetoir.persistance;

import com.example.demetoir.domain.WebBoard;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.function.Predicate;

public interface WebBoardRepository
    extends JpaRepository<WebBoard, Long> {


//    public default Predicate makePredicate(String type, String keyword){
//        BooleanBuilder builder = new BooleanBuilder();
//
//        QWebBoard boar = QWebBoard.webBoard;
//    }
}
