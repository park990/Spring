package com.sist.alonemaster;

import bbs.util.Paging;
import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BbsControl {
    @Autowired
    BbsDAO BbsDAO;



    @RequestMapping("/bbs/list")
    public ModelAndView list(String bname, String cPage) {
        if(bname==null||bname.equals("")){
            bname="BBS";
        }

        // 페이지 설정
        Paging page = new Paging(5,5);

        //총 게시물 수를 알아내기
        int totalCount= BbsDAO.getTotal(bname);

        // 총 페이지 수 알아내기
        page.setTotalCount(totalCount);

        if(cPage==null){
            cPage="1";
        }

        // 현제 페이지 설정
        page.setNowPage(Integer.parseInt(cPage));



        ModelAndView mv = new ModelAndView();
        BbsVO[] vo = BbsDAO.list(bname, page.getBegin(),page.getEnd());
        mv.addObject("ar", vo);
        mv.addObject("page",page);
        mv.setViewName("bbs/list");
        return mv;
    }




}
