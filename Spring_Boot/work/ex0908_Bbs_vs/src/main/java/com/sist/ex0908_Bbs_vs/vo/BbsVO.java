package com.sist.ex0908_Bbs_vs.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BbsVO {
    private String b_idx, subject, writer, content, file_name, 
                   ori_name, pwd, write_date, ip, hit, bname, status;

    private List<CommVO> c_list;           
    private MultipartFile file;    
    
}
