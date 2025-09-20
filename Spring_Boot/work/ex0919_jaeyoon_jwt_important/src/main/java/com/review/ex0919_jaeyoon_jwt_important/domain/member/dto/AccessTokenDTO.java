package com.review.ex0919_jaeyoon_jwt_important.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenDTO{
    private String accesstoken,refreshtoken,name;
}