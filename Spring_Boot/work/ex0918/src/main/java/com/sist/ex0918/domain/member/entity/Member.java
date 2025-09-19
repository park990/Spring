package com.sist.ex0918.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sist.ex0918.global.jpa.BaseEntity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true) // BaseEntity의 함수를 쓸수 있다이
public class Member extends BaseEntity{
    private String mid, mname;

    // 비밀번호는 외부로 가는 것이 보안상 좋지 않기 때문에 JSON으로 반환을 하지 못하게 설정
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String mpwd;
    private String accessToken;
    private String refreshToken;
}
