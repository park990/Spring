package com.sist.ex0917_bbs.util;


import java.io.File;

public class FileRenameUtil {
    public static String checkSameFileName(String filename, String path){
        // 인자인 filename에서 확장자를 뺀 파일명 가려내자.
        // 우선 "."의 위치를 알아내야 한다.
        int dotindex = filename.lastIndexOf(".");
        String f_name = filename.substring(0, dotindex); // 파일명
        String suffix = filename.substring(dotindex); // 확장자

        // 전체경로(절대경로+파일명)
        // String savefilename = path + "/" + filename;
        String saveFilePath=path+System.getProperty("file.separator")+filename;

        // 위 경로를 가지고 전체경로로 활용하아ㅕ 파일 객체 생성
        File f = new File (saveFilePath);

        // 파일이 이미 있다면 파일명 뒤에 숫자를 붙이기 위해 변수 준비하자
        int idx=1;

        // 동일한 파일명이 존재하는지 체크
        while(f.exists()){
            StringBuffer sb = new StringBuffer();
            sb.append(f_name);
            sb.append(idx++);
            sb.append(suffix);

            filename=sb.toString(); // test1.txt

            // 변경된 파일명으로 다시 경로 구성
            saveFilePath= path+System.getProperty("file.separator")+filename;

            // 변경된 파일명으로 파일객체 생성
            f= new File(saveFilePath);
        }

        return filename;
    }
}
