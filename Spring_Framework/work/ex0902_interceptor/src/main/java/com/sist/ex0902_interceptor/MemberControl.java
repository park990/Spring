package com.sist.ex0902_interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MemberControl {

    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public String login(){

        return "login";
    }
    @GetMapping("/sub/bravo")
    public String subtab(){
        return "subTab";
    }
    @PostMapping("/login")
    public ModelAndView login(String m_id,String m_pw){
        ModelAndView mv= new ModelAndView();
        session.setAttribute("mvo",m_id);
        mv.setViewName("redirect:/");
        return mv;
    }

}
