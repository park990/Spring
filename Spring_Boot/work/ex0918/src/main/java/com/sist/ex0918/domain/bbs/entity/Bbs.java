package com.sist.ex0918.domain.bbs.entity;

import com.sist.ex0918.global.jpa.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// 현재클래스와 상위클래스의 필드값을 저장하기 위한 매서드들 포함
@SuperBuilder

// 부모가 가지는 함수를 사용 하겠다.(필드포함)
@ToString(callSuper = true)

public class Bbs extends BaseEntity {

    @Column(columnDefinition = "bigint default 0")
    private Long hit;

    @Column
    private Long state = 0L; // 위에 columnDefinition이랑 같은 의미의 선언

    @NonNull
    private String title, content, writer;

    // save함수로 데이터가 저장되기 전에 수행한다.
    @PrePersist
    public void initStatus() {
        if (state == null) {
            state = 0L;
            hit = 0L;
        }
    }

}
