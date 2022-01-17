package com.example.restspring.persistence;

import com.example.restspring.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    List<TodoEntity> findByUserId(String userId);
}
