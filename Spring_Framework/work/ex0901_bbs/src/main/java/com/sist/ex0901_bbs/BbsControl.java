package com.sist.ex0901_bbs;

import bbs.util.Paging;
import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BbsControl {

    @Autowired
    private BbsDAO BbsDAO;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ServletContext application;

    @Autowired
    private HttpSession session;

    int numPerPage = 7;
    int pagePerBlock = 5;

    // 첨부파일이 저장되는 곳
    private String upload_path = "/resources/bbs_upload/";

    //이미지 파일들이 저장 될 곳
    private String editor_path = "/resources/editor_img/";

    @RequestMapping("/list")
    public ModelAndView list(String bname, String cPage) {
        ModelAndView mv = new ModelAndView();
        // 페이징 기법--
        if (bname == null || bname.equals("")) {
            bname = "BBS";
        }

        Paging page = new Paging(numPerPage, pagePerBlock);

        int totalRecord = BbsDAO.getTotalCount(bname);
        page.setTotalCount(totalRecord);
        if (cPage == null)
            cPage = "1";
        page.setNowPage(Integer.parseInt(cPage));

        // 뷰페이지에서 표현할 목록 가져오기--
        BbsVO[] ar = BbsDAO.getList(bname, page.getBegin(), page.getEnd());
        mv.addObject("ar", ar);
        mv.addObject("page", page);
        mv.addObject("bname", bname);
        mv.setViewName("list");


        return mv;
    }

    @RequestMapping("/write")
    public String write() {
        return "write";
    }
    @RequestMapping(value ="/write",method = RequestMethod.POST)
    public ModelAndView send(BbsVO bvo){
        ModelAndView mv = new ModelAndView();
        bvo.setFile_name(bvo.getUpload().getOriginalFilename());
        int cnt = BbsDAO.add(bvo);

        mv.setViewName("redirect:/list");
        return mv;
    }


    @RequestMapping(value = "saveImg", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveImg(MultipartFile upload) {
        Map<String, Object> m = new HashMap<>();

        // 파라미터로 들어온 이미지 파일이 있는지 확인
        if (upload.getSize() > 0) {
            // 넘어온 이미지 파일이 있는 경우이므로 원하는 곳에 저장
            // 절대경로가 필요하다.
            String real_path = application.getRealPath(editor_path);
            String fname = upload.getOriginalFilename();// 이미지 파일명
            try {
                upload.transferTo(new File(real_path, fname));
                String c_path=request.getContextPath()+"/resources/editor_img/"+fname;
                System.out.println("cpath is: "+c_path);

                m.put("img_url", c_path);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return m;
    }
}
