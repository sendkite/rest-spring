package com.example.restspring.dto;

import com.example.restspring.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
    private String id;
    private String title;
    private boolean done;

    public TodoDTO(final TodoEntity entity) { // 매개변수 재할당 방지용도로 final
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone(); // 불리언은 자동으로 getDone이 아니라 isDone으로 바뀜.
    }

    public static TodoEntity toEntity(final TodoDTO dto) {
        return TodoEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }
}
