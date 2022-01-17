package com.example.restspring.service;

import com.example.restspring.model.TodoEntity;
import com.example.restspring.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {

        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        repository.save(entity);
        TodoEntity savedEntiry = repository.findById(entity.getId()).get(); // .get()의 경우 결과값이 null일 경우 NoSuchElementException 발생! orElse로 예외 넣어줘야함
        return savedEntiry.getTitle();

    }

    public List<TodoEntity> create(final TodoEntity entity){
        // 검증, save, return 세 단계로 구성
        // 1. validation
        if (entity == null) {
            log.warn("Entity can not be null");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unkown user");
        }

        // 2. save
        log.info("Entity Id : {} is saved", entity.getUserId());

        // 3. return
        return repository.findByUserId(entity.getUserId());
    }
}
