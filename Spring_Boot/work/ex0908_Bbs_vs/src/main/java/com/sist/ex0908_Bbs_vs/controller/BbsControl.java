package com.sist.ex0908_Bbs_vs.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sist.ex0908_Bbs_vs.service.BbsSerivice;
import com.sist.ex0908_Bbs_vs.service.CommService;
import com.sist.ex0908_Bbs_vs.util.FileRenameUtil;
import com.sist.ex0908_Bbs_vs.util.Paging;
import com.sist.ex0908_Bbs_vs.vo.BbsVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class BbsControl {

    private final CommService commService;
    @Autowired
    private BbsSerivice bbsSerivice;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ServletContext application;

    @Value("${server.editor.path}")
    private String editor_img;

    @Value("${server.upload.path}")
    private String bbs_upload;

    private int numPerPage = 7;
    private int pagePerBlock = 5;

    BbsControl(CommService commService) {
        this.commService = commService;
    }

    @RequestMapping("/list")
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

        Paging page = new Paging(nowPage, totalCount, numPerPage, pagePerBlock, bname);

        BbsVO[] ar = bbsSerivice.getList(bname, page.getBegin(), page.getEnd());
        mv.addObject("totalCount", totalCount);
        mv.addObject("bname", bname);
        mv.addObject("ar", ar);
        mv.addObject("page", page);
        mv.addObject("PageResult", page.getPagingHTML());
        return mv;
    }

    @GetMapping("/write")
    public ModelAndView write(String bname, String cPage) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("bbs/write");

        return mv;
    }

    @PostMapping("/write")
    public ModelAndView write(BbsVO bvo, String cPage, String bname) {
        ModelAndView mv = new ModelAndView();
        MultipartFile f = bvo.getFile();

        if (f.getSize() > 0) {
            String realPath = application.getRealPath(bbs_upload);

            // 같은 파일명이 있다면 파일명 변경
            String fname = FileRenameUtil.checkSameFileName(f.getOriginalFilename(), realPath);

            try {
                f.transferTo(new File(realPath, fname));
                bvo.setFile_name(fname);
                bvo.setOri_name(f.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        bvo.setIp(request.getRemoteAddr());
        bbsSerivice.add(bvo);
        mv.setViewName("redirect:/list?bname=" + bname + "&cPage=" + cPage);
        System.out.println("bnamebnamebname" + bvo.getBname());
        return mv;

    }

    @PostMapping("/saveImg")
    public Map<String, Object> saveImg(MultipartFile upload) {
        Map<String, Object> m = new HashMap<>();

        if (upload.getSize() > 0) {
            // 파일 저장할 위치(editor_img)를 절대경로화 시키자
            String realPath = application.getRealPath(editor_img);

            // 파일명을 얻어내자
            String oName = upload.getOriginalFilename();

            // 동일한 파일명이 있을 때만 파일명을 변경하자
            String fname = FileRenameUtil.checkSameFileName(oName, realPath);

            try {
                upload.transferTo(new File(realPath, fname));
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 업로드 된 파일의 전체경로를 map에 담자
            String url_path = request.getContextPath() + editor_img + fname;

            m.put("img_url", url_path);

        }

        return m;
    }

    @GetMapping("/view")
    public ModelAndView view(String cPage, String bname, String b_idx) {
        ModelAndView mv = new ModelAndView();
        // 조회수 증가 처리, 읽은 게시물을 조회수 증가를 하지 않는다.
        // 조회수 증가 처리는 세션을 활용하여 처리한다.

        // read 라는 이름으로 저장된 세션이 있는지 확인
        Object obj = session.getAttribute("read");
        List<BbsVO> list = null;

        // 만약에 있다면 list에 저장해주자.
        if (obj != null) {
            list = (List<BbsVO>) obj;
        }
        // 없다면 리스트 만들어서 세션에 저장해 두자.
        else {
            list = new ArrayList<>();
            session.setAttribute("read", list);
        }
        BbsVO vo = bbsSerivice.getBbs(b_idx);

        // 읽었던 게시물인지?
        if (vo != null) {

            // 보지 않았음을 가정하자
            boolean chk = false;

            for (BbsVO bvo : list) {
                if (bvo.getB_idx().equals(b_idx)) {
                    chk = true;
                    break;
                }
            }

            // chk가 false를 유지한다? 한번도 읽지 않았음.
            if (!chk) {
                bbsSerivice.hit(b_idx);

                // db에는 반영이 됐지만 화면에 즉각적으로 조회수르 반영하기 위함.
                int hit = Integer.parseInt(vo.getHit());
                vo.setHit(String.valueOf(hit + 1));

                // 읽은 게시물로 등록하자
                list.add(vo);
            }
            mv.addObject("bname", bname);
            mv.addObject("cPage", cPage);
            mv.addObject("vo", vo);
            mv.setViewName("bbs/view");
        }

        return mv;
    }

    @PostMapping("/edit")
    public ModelAndView edit(BbsVO vo, String cPage) {
        // enctype 이 멀티팟으로 왔는지 확인
        ModelAndView mv = new ModelAndView();
        String type = request.getContentType();
        if (type != null && type.startsWith("multipart/form-data")) {
            // 업데이트
            MultipartFile file = vo.getFile();
            if (file.getSize() > 0) {
                String realPath = application.getRealPath(bbs_upload);
                String oname = file.getOriginalFilename();
                String fname = FileRenameUtil.checkSameFileName(oname, realPath);
                try {
                    file.transferTo(new File(realPath, fname));
                    vo.setFile_name(fname);
                    vo.setOri_name(oname);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            vo.setIp(request.getRemoteAddr());
            // db에서 수정처리
            bbsSerivice.udt(vo);
            mv.setViewName("redirect:/view?bname=" + vo.getBname() + "&b_idx=" + vo.getB_idx()
                    + "&cPage=" + cPage);

        } else {
            BbsVO v = bbsSerivice.getBbs(vo.getB_idx());
            mv.addObject("vo", v);
            mv.addObject("cPage", cPage);
            mv.addObject("bname", vo.getBname());
            mv.setViewName("bbs/edit");
        }

        return mv;
    }

    @PostMapping("/download")
    public ResponseEntity<String> download(String f_name) {
        String realPath = application.getRealPath(bbs_upload + f_name);
        File f = new File(realPath);

        if (f.exists()) {
            byte[] buf = new byte[4096];
            int size = -1;

            BufferedInputStream bis = null;
            FileInputStream fis = null;

            BufferedOutputStream bos = null;
            ServletOutputStream sos = null;
            try {
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition",
                 "attachment; filename="+new String(f_name.getBytes(),"8859_1"));
                fis=new FileInputStream(f);
                bis = new BufferedInputStream(fis);

                sos= response.getOutputStream();
                bos=new BufferedOutputStream(sos);

                while((size=bis.read(buf))!=-1){
                    bos.write(buf,0,size);
                    bos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bos != null)
                        bos.close();
                    if (sos != null)
                        sos.close();
                    if (bis != null)
                        bis.close();
                    if (fis != null)
                        fis.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }
}
