package com.sist.ex0901_bbs;

import bbs.util.Paging;
//import jdk.internal.loader.Resource;
import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BbsControl {

    @Autowired
    private BbsDAO BbsDAO;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

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

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public ModelAndView send(BbsVO bvo) {
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
                String c_path = request.getContextPath() + "/resources/editor_img/" + fname;
                System.out.println("cpath is: " + c_path);

                m.put("img_url", c_path);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return m;
    }

    @GetMapping("/view")
    public ModelAndView view(String bname, String b_idx, String cPage) {
        ModelAndView mv = new ModelAndView();

        // 세션에 read_list라는 이름으로 저장된객체를 얻어낸다
        Object obj = session.getAttribute("read_list");
        ArrayList<BbsVO> list = null;

        // obj가 null이 아니면 obj를 형변환 해서 list에 저장하자.
        if (obj != null) {
            list = (ArrayList<BbsVO>) obj;
        } else {
            list = new ArrayList<>();
            session.setAttribute("read_list", list);
        }



        BbsVO bvo = BbsDAO.getBbs(b_idx);
        if (bvo != null) {
            // 이미 읽었던 게시물인지 아닌지를 알아보자.
            boolean chk = false;
            for (BbsVO lvo : list) {
                if (lvo.getB_idx().equals(b_idx)) ;
                {
                    chk = true; // 이전에 이미 한번 읽었던 게시물인 경우
                    break;
                }
            }// for의 끝
            //chk가 false를 유지하고 있다면 한번도 읽지 않은 게시물이고
            // true로 변경되었다면 읽었던 게시물
            if (!chk) {
                int hit = Integer.parseInt(bvo.getHit()); // 기존 히트수
                bvo.setHit(String.valueOf(hit + 1));
                BbsDAO.hit(b_idx);
                list.add(bvo);
            }
        mv.addObject("vo", bvo);
        }
        mv.addObject("bname", bname);
        mv.setViewName("view");

        return mv;
    }

    @PostMapping("/download")
    public ResponseEntity<Resource> download(String f_name) {
        // 파일들이 저장되어 있는 (bbs_upload)를 절대경로화 시키자
        String real_path = application.getRealPath(upload_path + f_name);
        File f = new File(real_path);

        if (f.exists()) {
            byte[] buf = new byte[4096];
            int size = -1;

            // 파일을 다운로드에 필요한 스트림준비
            FileInputStream fis = null;
            BufferedInputStream bis = null;

            BufferedOutputStream bos = null;
            ServletOutputStream sos = null;
            // response로 응답을 얻어내는 것이 ServletoutputStream
            // sos를 선언했다.

            try {
                // 접속자 화면에 다운로드 창 보여주기
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + new String(f_name.getBytes(), "8859_1"));

                fis = new FileInputStream(f);
                bis = new BufferedInputStream(fis);

                // response를 통해 다운로드할 스트림을 얻어낸다.
                sos = response.getOutputStream();
                bos = new BufferedOutputStream(sos);

                while ((size = bis.read(buf)) != -1) {
                    bos.write(buf,0,size);
                }// while의 끝
                    bos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null)
                        fis.close();
                    if (bis != null)
                        bis.close();
                    if (sos != null)
                        sos.close();
                    if (bos != null)
                        bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }

}
