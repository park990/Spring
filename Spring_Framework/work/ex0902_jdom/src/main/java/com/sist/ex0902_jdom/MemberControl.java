package com.sist.ex0902_jdom;

import data.vo.MemberVO;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberControl {
    // 내부 xml문서를 접근 하여 마치 오픈api에서 결과를 받는 것 처럼 함.

    @Autowired
    ServletContext application;

    @RequestMapping("t1")
    public ModelAndView t1() throws Exception {
        ModelAndView mv = new ModelAndView();
        // 준비된 문서의 절대경로 지정하자.
        String realPath = application.getRealPath("/resources/pm/data/member.xml");// 원래는 api 주소

        //JDOM 파서를 이용해서 파싱
        SAXBuilder builder = new SAXBuilder();

        // 준비된 빌더를 통해 xml자원을 문서과(documnet) 시키자.
        Document doc = builder.build(realPath);

        // 메모리상에 존재하는 xml문서(document객체)의 루프를 얻어낸다.
        Element root = doc.getRootElement(); //members
//        System.out.println("ROOT:"+root);
        mv.setViewName("member");
        // 루트의 자식들 중 태그명이 member인 자식들 모두 가져온다.
        List<Element> list = root.getChildren("member");
//        System.out.println(list.size()); // 3

        // 위에서 얻은 lsit를 배열로 만들어보자
        MemberVO[] ar = null;
        if (list != null && list.size() > 0) {
            ar = new MemberVO[list.size()];
            int i = 0; //
            for (Element e : list) {
                String name = e.getChildText("name");
                String email = e.getChildText("email");
                String phone = e.getChildText("phone");

                MemberVO m = new MemberVO(name, email, phone);

                // 채워진 memberVO를 배열에 저장하자
                ar[i] = m;
                i++;
            }
            mv.addObject("ar", ar);
            mv.setViewName("member");
        }
        return mv;
    }

    @PostMapping("/search")
    @ResponseBody
    public Map<String, Object> search(String type, String val) throws Exception {
        Map<String, Object> m = new HashMap<>();
        ModelAndView mv = new ModelAndView();
        // 준비된 문서의 절대경로 지정하자.
        String realPath = application.getRealPath("/resources/pm/data/member.xml");// 원래는 api 주소

        //JDOM 파서를 이용해서 파싱
        SAXBuilder builder = new SAXBuilder();

        // 준비된 빌더를 통해 xml자원을 문서과(documnet) 시키자.
        Document doc = builder.build(realPath);

        // 메모리상에 존재하는 xml문서(document객체)의 루프를 얻어낸다.
        Element root = doc.getRootElement(); //members
//        System.out.println("ROOT:"+root);
        mv.setViewName("member");
        // 루트의 자식들 중 태그명이 member인 자식들 모두 가져온다.
        List<Element> list = root.getChildren("member");
        MemberVO[] ar = null;
        if (list != null && list.size() > 0) {
            ar = new MemberVO[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Element e = list.get(i);
                String name=e.getChildText("name");
                String phone=e.getChildText("phone");
                String email=e.getChildText("email");

                MemberVO mo= new MemberVO(name,email,phone);

                boolean chk=false;
                switch (type){
                    case "1":
                        if(email.contains(val))
                           chk=true;
                        break;

                    case "0":
                        if(name.contains(val))
                            chk=true;
                        break;
                    case "2":
                        if(phone.contains(val))
                            chk=true;
                        break;
                }// switch 마지막
                if(chk)
                    ar[i]=mo;
            }// 반복문의마지막
        }
        m.put("ar",ar);
        m.put("length",ar.length);
        return m;
    }
}
