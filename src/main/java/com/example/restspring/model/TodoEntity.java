package com.example.restspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder // 빌더 디자인 패턴을 만들어 주는 어노테이션
@Entity
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 생성자
@Data // Getter, Setter 생성 TODO: getter setter랑 차이가 뭐지? 찾아보기
@Table(name = "plan")
public class TodoEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid") // hibernate 기본 제너레이트가 아니라 uuid(네트워크 표준 식별자 규약)으로 만들겠다는 의미
    private String id;
    private String userId;
    private String title;
    private boolean done;
}
