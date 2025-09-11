package sist.ex0910_jwt.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sist.ex0910_jwt.mapper.MemberMapper;
import sist.ex0910_jwt.vo.MemVO;

@Service
public class MemberService {
    
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원가입
    public int regMember(MemVO vo){
        String pw=passwordEncoder.encode(vo.getM_pw());
        vo.setM_pw(pw);
        return memberMapper.reg(vo);
    }

    // 로그인
    public MemVO login(MemVO vo){
        MemVO mvo = memberMapper.login(vo.getM_id());
        if(mvo!=null){
            if(passwordEncoder.matches(vo.getM_pw(), mvo.getM_pw()))
            return mvo;
        }
            return null;
    }

    
}
