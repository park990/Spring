package com.sist.ex0828;

import mybatis.dao.MemoDAO;
import mybatis.vo.MemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemoControl {

    @Autowired
    MemoDAO MemoDAO;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/memo_list")
    public ModelAndView memo(){
        ModelAndView mv = new ModelAndView();
        MemoVO[] ar = MemoDAO.getTotal();
        mv.addObject("ar",ar);

        mv.setViewName("memo/memoList");

//       mv.setViewName("memo/list");
        // WEB-INF/jsp/memo/list.jsp
        return mv;
    }



    @RequestMapping(value = "/memo/add",method = RequestMethod.POST)
    public ModelAndView add(MemoVO vo){
        vo.setIp(request.getRemoteAddr());

        MemoDAO.add(vo);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/memo_list");
        return mv;
    }
}
