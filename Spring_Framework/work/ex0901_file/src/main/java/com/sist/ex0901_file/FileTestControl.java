package com.sist.ex0901_file;

import file.input.paramVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class FileTestControl {

    // 파일첨부에 필요한 멤버변수들
    @Autowired
    private ServletContext application;

    @Autowired
    private HttpServletRequest request;

    // 파일이 저장될 위치 - ~webapp/resources/file_upload라는 폴더로 지정하자.
    // 위치지정에 앞서 dispatcher-servlet.xml에서 webapp/resources를 등록해야한다.

    private String upload_path = "/resources/file_upload";

    @RequestMapping("/write")
    public String write() {
        return "write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public ModelAndView write(paramVO pvo) {
        MultipartFile s_file = pvo.getS_file();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/write");

        // 파일이 저장될 경로를 만들자.
        System.out.println("upload_path=" + upload_path);
        String real_path = application.getRealPath(upload_path);
        System.out.println("real_path=" + real_path);

        String fname = null;
        if (s_file.getSize() > 0) {
            fname = s_file.getOriginalFilename();
            pvo.setFile_name(fname);
//            pvo.setIP(request.getRemoteAddr());
            try {
                // 파일 업로드
                s_file.transferTo(new File(real_path, fname));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }// if문의 마지막

//        paramDAO.add(pvo) DB 저장


        return mv;
    }
}
