package com.sist.ex0828_bbs;

import bbs.util.Paging;
import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BbsControl {
    @Autowired
    BbsDAO BbsDAO;

    @Autowired
    private HttpSession session;

    int numPerPage = 7; // 한 페이지에 보여줄 게시물 수
    int pagePerBlock = 5; // 한 블럭당 보여질 페이지 수

    @RequestMapping("/bbs_list")
    public ModelAndView list(String bname, String cPage) {
        if (bname == null || bname.equals("")) {
            bname = "BBS";
        }

        // 한 페이지의 게시물 수와 보여질 페이지 수 설정
        Paging page = new Paging(numPerPage,pagePerBlock);

        // 총 페이지 수를 구하기 위해 총 게시물 수를 알아내기
        int totalCount= BbsDAO.getTotalCount(bname);

        // 총 페이지 수 알아내기
        page.setTotalCount(totalCount);

        // 현재 페이지 설정
        if(cPage==null){
            cPage="1";
        }
        page.setNowPage(Integer.parseInt(cPage));

        ModelAndView mv = new ModelAndView();
        // 뷰페이지에 표현할 목록 받자
        BbsVO[] ar =BbsDAO.getList(bname, page.getBegin(),page.getEnd());

        mv.addObject("ar", ar);
        mv.addObject("page",page);
        mv.setViewName("/bbs/list");
        return mv;
    }

//    @RequestMapping(value="/bbs/view",method = RequestMethod.POST)
    @RequestMapping("/bbs/viewTest")
    public ModelAndView goview(String bname, String b_idx, String cPage){

//      (조회수 hit F5 중복 방지 설정)
        List<BbsVO> list=null;
        // 세션으로부터 이름이 r_list라는 이름으로 저장된 객체를 얻어낸다.
        Object obj = session.getAttribute("r_list");
        if(obj!=null){
            list= (List<BbsVO>) obj;
        }else{
            list=new ArrayList<BbsVO>();
            session.setAttribute("r_list",list);
        }

        // 이제 list에서 인자로 받은 b_idx값과 같은 값을 가진 BbsVO를 list에서 검색
        boolean check =false; //찾지 못한 경우
        for(BbsVO bvo: list){
            if(bvo.getB_idx().equals(b_idx)){
                check=true;
            }
        } // for의 끝

        // check변수의 값이 false이면 조회수 증가
        if(!check){
            BbsDAO.hit(b_idx);
        }


        BbsVO vo = BbsDAO.getBbs(b_idx);
        ModelAndView mv = new ModelAndView();
        if(vo!=null) {
            if (!check) {
                list.add(vo); // 다음에 같은 게시물을 클릭하면 조회수 증가를 하지 않는다.
            }
        mv.addObject("vo", vo);
        }

        mv.setViewName("bbs/view");
        return mv;
    }
}
