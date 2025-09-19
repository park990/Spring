package com.review.ex0919_jaeyoon_jwt_important.global.jpa;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter@Setter@NoArgsConstructor
@SuperBuilder
@MappedSuperclass // 지금 이 엔티티는 수퍼 엔티티라 전달만 할거니 생성 하지말아라
@EntityListeners(AuditingEntityListener.class) // 생성, 수정 시간을 기록하는 이벤트를 감지해서 자동으로 기록해줌 (@Created Data)
@ToString // 객체를 출력할 때 객체자체를 출력하기 위해서 표현함 그냥 출력하면 주소값만나옴.
public class SuperEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long p_idx;

    @CreatedDate
    private String signUpDate;
}
