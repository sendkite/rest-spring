package com.example.restspring.persistence;

import com.example.restspring.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    @Query("select * from Todo t where t.userId = ?1")  // ?1 은 매개변수 위치 순서
    List<TodoEntity> findByUserId(String userId);
}
