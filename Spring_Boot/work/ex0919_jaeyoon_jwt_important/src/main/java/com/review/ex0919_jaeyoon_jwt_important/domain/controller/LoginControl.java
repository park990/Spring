package com.review.ex0919_jaeyoon_jwt_important.domain.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.review.ex0919_jaeyoon_jwt_important.domain.member.dto.AccessTokenDTO;
import com.review.ex0919_jaeyoon_jwt_important.domain.member.entity.MemEntity;
import com.review.ex0919_jaeyoon_jwt_important.domain.member.service.MemService;
import com.review.ex0919_jaeyoon_jwt_important.global.result.ResultData;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginControl {
    private final MemService mService;
    
    @PostMapping("/login")
    public ResultData<AccessTokenDTO> hi(@RequestBody MemEntity insertVO){  
        AccessTokenDTO dto= mService.authAndGenToken(insertVO);

        // 프론트에서 입력한 mvo를 가지고 먼저 아이디에 해당하는 user정보가 있는지 확인한다.
        // 있다면 입력한 비밀번호와 db에 저장되어있는 비밀번호를 비교해서 만약 같다면 토큰을 생성하는
        // 로직을 서비스에서 전부 만든다음에 아래처럼 dto에 넣어서 전달하면 될듯. 
        
        // 토큰을 생성하는데 성공했으면 dto에다가 accesstoken과 refreshtoken그리고 mem.getname을 넣어주자

        
        return ResultData.JsonReturn(1, "SUCCESS", dto);
    }
    
}
