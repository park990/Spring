package com.sist.ex0908_Bbs_vs.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sist.ex0908_Bbs_vs.service.BbsSerivice;
import com.sist.ex0908_Bbs_vs.util.FileRenameUtil;
import com.sist.ex0908_Bbs_vs.util.Paging;
import com.sist.ex0908_Bbs_vs.vo.BbsVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class BbsControl {
    @Autowired
    private BbsSerivice bbsSerivice;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ServletContext application;

    @Value("${server.editor.path}")
    private String editor_img;

    private int numPerPage = 7;
    private int pagePerBlock = 5;

    @GetMapping("/list")
    public ModelAndView getBbslist(@RequestParam String bname, String cPage) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("bbs/list");
        int nowPage = 1;
        if (cPage != null && !cPage.equals("")) {
            nowPage = Integer.parseInt(cPage);
        }
        if (bname == null || bname.equals("")) {
            bname = "BBS";
        }

        int totalCount = bbsSerivice.getTotalCount(bname); // 전체 게시물의 수

        Paging page= new Paging(nowPage, totalCount, numPerPage, pagePerBlock, bname);

        BbsVO[] ar = bbsSerivice.getList(bname, page.getBegin(), page.getEnd());
        mv.addObject("totalCount", totalCount);
        mv.addObject("bname", bname);
        mv.addObject("ar", ar);
        mv.addObject("page", page);
        mv.addObject("PageResult", page.getPagingHTML());
        return mv;
    }

    @GetMapping("/write")
    public ModelAndView write(String bname, String cPage){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("bbs/write");

        return mv;
    }

    @PostMapping("/write")
    public ModelAndView write(BbsVO bvo,String cPage,String bname) {
        ModelAndView mv = new ModelAndView();
        bvo.setFile_name(bvo.getFile().getOriginalFilename());
        bbsSerivice.add(bvo);
        mv.setViewName("redirect:/list?bname="+bname+"&cPage="+cPage);
        System.out.println("bnamebnamebname"+bvo.getBname());
        return mv;
    }
    

    @PostMapping("/saveImg")
    public Map<String,Object> saveImg(MultipartFile upload){
        Map<String, Object> m = new HashMap<>();
        
        if(upload.getSize()>0){
            // 파일 저장할 위치(editor_img)를 절대경로화 시키자
            String realPath= application.getRealPath(editor_img);

            // 파일명을 얻어내자
            String oName= upload.getOriginalFilename();

            // 동일한 파일명이 있을 때만 파일명을 변경하자
            String fname=FileRenameUtil.checkSameFileName(oName, realPath);

            try{
                upload.transferTo(new File(realPath,fname));
            }catch(Exception e){
                e.printStackTrace();
            }

            // 업로드 된 파일의 전체경로를 map에 담자
            String url_path=request.getContextPath()+editor_img+fname;

            m.put("img_url", url_path);

        }
        
        return m;
    }
   
    
    

}
