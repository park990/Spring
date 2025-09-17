package com.sist.ex0917_bbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sist.ex0917_bbs.config.DbConfig;
import com.sist.ex0917_bbs.service.BbsService;
import com.sist.ex0917_bbs.service.CommService;
import com.sist.ex0917_bbs.util.Paging;
import com.sist.ex0917_bbs.vo.BbsVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BbsControl {

    private final DbConfig dbConfig;
    private final BbsService bService;
    private final CommService cService;

    private int numPerPage = 7;
    private int pagePerBlock = 5;
    


    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam("bname") String bname, String searchType, String searchValue,
            String cPage) {
        int nowPage = 1;

        if (cPage != null)
            nowPage = Integer.parseInt(cPage);

        if (bname.trim().length() == 0)
            bname = "BBS";

        // 페이징 기법을 위해 총 게시물의 수를 알아내자
        int totalCount = bService.getTotalCount(bname, searchType, searchValue);

        // 페이징 객체 생성
        Paging page = new Paging(numPerPage, pagePerBlock);

        // 총 게시물 수 지정
        page.setTotalCount(totalCount);
        page.setNowPage(nowPage);

        // 페이징 객체로부터 begin과 end 값을 알아내자
        int begin = page.getBegin();
        int end = page.getEnd();

        // 화면에 표현할 게시물 목록 받기
        List<BbsVO> list = bService.getList(bname, searchType, searchValue, begin, end);

        // 반환할 Map 구조 생성하자
        Map<String, Object> m = new HashMap<>();
        m.put("ar", list);
        m.put("bname", bname);
        m.put("nowPage", nowPage);
        m.put("totalCount", totalCount);
        m.put("totalPage", page.getTotalPage());
        m.put("length", list.size());

        return m;
    }

    @RequestMapping("getBbs")
    public Map<String,Object> getBbs(@RequestParam String b_idx){
        Map<String,Object> m = new HashMap<>();
        BbsVO vo = bService.get_bbs(b_idx);
        m.put("vo",vo);
        return m;
    }

    // 저장
    @RequestMapping("/add")
    public Map<String,Object> addBbs(@RequestBody BbsVO vo){
        Map<String,Object> m = new HashMap<>();

        // 파일 처리 해야한다
        //....
        //....
        //생략...

        int cnt = bService.addBbs(vo);
        m.put("cnt",cnt);
        return m;
    }
}
