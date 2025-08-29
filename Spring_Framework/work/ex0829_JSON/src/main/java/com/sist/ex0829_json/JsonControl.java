package com.sist.ex0829_json;

import mybatis.dao.DataDAO;
import mybatis.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spring.input.imgVO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JsonControl {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ServletContext application;

    private String editor_img = "/resources/editor_img/";

    @Autowired
    DataDAO DataDAO;

    @RequestMapping("/test1")
    public ModelAndView test1() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ex1");

        return mv;
    }

    @RequestMapping("/test2")
    public String test2() {

        return "ex2";
    }

    @RequestMapping(value = "/test3", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> test3() {
        Map<String, String> m = new HashMap<>();
        m.put("name", "김진환");
        m.put("email", "Jinhwan@korea.com");

        return m;
    }

    @RequestMapping("/editor")
    public String editor() {

        return "editor";
    }

    @RequestMapping(value = "saveImg", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> saveImg(imgVO ivo) {
        // json으로 던질 반환형
        Map<String, String> map = new HashMap<>();

        //전달되어 오는 이미지 파일은 ivo라는 인자에 저장되어있다.(upload)로
        MultipartFile f = ivo.getUpload();

        if (f.getSize() > 0) {// 넘어온 파일이 있는 경우

            // 파일을 저장할 위치(editor.img)를 절대 경로로
            String realpath = application.getRealPath(editor_img);

            // 저장할 위치 절대경로로 만들었으니 파일을 준비하자
            try {
                f.transferTo(new File(realpath, f.getOriginalFilename()));// 파일 저장 완료
                map.put("fname", f.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 현재 파일이 저장된 서버경로(url)를 문자열로 만들자
            // 예) localhost:8080/resources/editor_img/...
            String c_path = request.getContextPath();
            map.put("url", c_path + editor_img);
        }// if의 마지막

        return map; //요청한 곳으로 보내는데 json으로 보내기 위해 현재
        //메서드 위에 ResponseBody를 지정해야한다.
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public ModelAndView write(DataVO dvo) {
        ModelAndView mv = new ModelAndView();

        // enctype이 multipart로 시작하는지?
        String c_type = request.getContentType();
        if (c_type.startsWith("multipart")) {
            // 파일첨부가 된 폼객체로 부터 요청이 된 경우라면
            MultipartFile f = dvo.getSss();
            String fname = null;
            if (f.getSize() > 0) {
                // 첨부된 파일을 원하는 위치에 저장!
                String realpath = application.getRealPath("resources/upload");

                // 파일명
                fname=f.getOriginalFilename();
                try {
                    f.transferTo(new File(realpath, fname)); // 파일 업로드

                    // DB에 저장 해야한다.
                    dvo.setFile_name(fname);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }//두번째 if
        dvo.setIp(request.getRemoteAddr());
        //DB에 저장
        DataDAO.add(dvo);
        mv.setViewName("redirect:/editor");
        }// 첫번째 if

        return mv;
    }


}
