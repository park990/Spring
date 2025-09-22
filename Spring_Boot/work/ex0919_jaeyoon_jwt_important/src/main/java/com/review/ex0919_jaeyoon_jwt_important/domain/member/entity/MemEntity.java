package com.review.ex0919_jaeyoon_jwt_important.domain.member.entity;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.review.ex0919_jaeyoon_jwt_important.global.jpa.SuperEntity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true) // 상속받은 테이블의 함수를 사용 가능하다
public class MemEntity extends SuperEntity{

    private String name, pid;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pPw;
    private String refreshToken;
    // accesstoken은 DB에 저장하지 않는다.

    
}
