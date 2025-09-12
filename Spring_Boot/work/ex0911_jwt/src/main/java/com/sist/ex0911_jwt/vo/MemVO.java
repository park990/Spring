package com.sist.ex0911_jwt.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "member_t")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemVO {

    @Id
    private Long mIdx;
    private String mName, mId, mPw;
    
}
