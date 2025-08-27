package com.sist.ex1;

import ex1.vo.DataVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyAction3 {

    @RequestMapping("/ex6")
    public ModelAndView ex(){
        ModelAndView mv =new ModelAndView();

        DataVO[] ar=new DataVO[3];
        ar[0]=new DataVO();
        ar[0].setName("일지매");
        ar[0].setPhone("010-1212-2323");

        ar[1]=new DataVO();
        ar[1].setName("마루치");
        ar[1].setPhone("010-3322-6745");

        ar[2]=new DataVO();
        ar[2].setName("이도");
        ar[2].setPhone("010-2222-2222");

        mv.addObject("ar",ar);
        mv.setViewName("ex6");

    return mv;
    }
}
