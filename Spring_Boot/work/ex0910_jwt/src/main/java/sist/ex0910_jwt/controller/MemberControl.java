package sist.ex0910_jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import sist.ex0910_jwt.servise.MemberService;
import sist.ex0910_jwt.vo.MemVO;

@RestController
public class MemberControl {
    
    //DB 활용을 위해 
    @Autowired
    MemberService memberService;

    // 로그인 처리 위한 
    @Autowired
    HttpSession session;

    @RequestMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @RequestMapping("/reg")
    public ModelAndView reg(){
        ModelAndView mv = new ModelAndView("reg");
        return mv;
    }

    @PostMapping("/reg")
    public ModelAndView ref(MemVO vo){
        ModelAndView mv = new ModelAndView("index");
         memberService.regMember(vo);

         // 기본키를 저장할 때 바로 얻어내는 방법
         // mapper에 다음의 속성을 지정한다.
         // useGeneratedKeys="true" keyProperty="m_idx"
         System.out.println("멤버 기본키"+vo.getM_idx());
        return mv;
    }
    
    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @PostMapping("/reqLogin")
    public ModelAndView log(MemVO vo){
        ModelAndView mv = new ModelAndView("redirect:/");
        MemVO mvo= memberService.login(vo);
        if(mvo!=null){
            session.setAttribute("mvo", mvo);
        }else{
            mv.addObject("status","1");
        }
        return mv;
    }
    

}
