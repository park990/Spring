package com.example.ex0906_bbs_review.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BbsVO {
    private String b_idx, subject, writer, content, file_name, ori_name, pwd, write_date, ip, hit, bname, status;
    private List<CommVO> comlist;
}
