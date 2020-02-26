package com.example.demetoir.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

// dummy entity for JdbcTokenRepositoryImpl
// JdbcTokenRepositoryImpl 를 이용하여  remember me 기능을 구현시 필요한 DB 테이블을 생성하기위한 JPA 엔테티
@Entity
@Table(name = "persistent_logins")
public class Persistancelogin {
  @Column(nullable = false, columnDefinition = "varchar(64)", name = "username")
  private String username;

  @Id
  @Column(columnDefinition = "varchar(64)", name = "series")
  private String series;

  @Column(nullable = false, columnDefinition = "varchar(64)", name="token")
  private String token;

  @Column(nullable = false, columnDefinition = "timestamp", name = "last_used")
  private Timestamp last_used;
}
