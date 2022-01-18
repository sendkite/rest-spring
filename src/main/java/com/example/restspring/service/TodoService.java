package com.example.restspring.service;

import com.example.restspring.model.TodoEntity;
import com.example.restspring.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

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
    // 생성
    public List<TodoEntity> create(final TodoEntity entity){
        // 검증, save, return 세 단계로 구성
        // 1. validation
        validation(entity);

        // 2. save
        repository.save(entity);
        log.info("Entity Id : {} is saved", entity.getUserId());

        // 3. return
        return repository.findByUserId(entity.getUserId());
    }

    // 조회
    public List<TodoEntity> retrieve(final String userId) {
        log.info("UserId is {}", userId);
        return repository.findByUserId(userId);
    }

    // 수정
    public List<TodoEntity> update(final TodoEntity entity) {
        validation(entity);

        final Optional<TodoEntity> original = repository.findById(entity.getId());
        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone()); // 불리언은 is로 바뀜 옵셔널이 NUll이 아니면 이렇게
        });
        repository.save(entity);
        return retrieve(entity.getUserId());
    }

    // 삭제
    public List<TodoEntity> delete(final TodoEntity entity) {
        validation(entity);

        try {
            repository.delete(entity);
        } catch(Exception e) {
            log.error("error deleting entity " + entity.getId());
            throw new RuntimeException("error deleting entity " + entity.getId());
        }

        return retrieve(entity.getUserId());
    }


    // 벨리데이션 분리
    private void validation(TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity can not be null");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unkown user");
        }
    }
}
