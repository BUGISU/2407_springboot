//src/main/java/com/example/ex4/repository/GuestbookRepository.java
package com.example.ex4.repository;

import com.example.ex4.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>,
    QuerydslPredicateExecutor<Guestbook> {

}
