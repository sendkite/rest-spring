package com.example.restspring.service;

import com.example.restspring.model.TodoEntity;
import com.example.restspring.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {

        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        repository.save(entity);
        TodoEntity savedEntiry = repository.findById(entity.getId()).get();
        return savedEntiry.getTitle();

    }
}
