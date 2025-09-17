"use client";

import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableRow } from "@mui/material";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function Write(){
    const router=useRouter();
    const api_url="/board/add";

    // Back서버로 보낼 파라미터 값들을 객체로 준비하자.
    const[vo,setVO]=useState({subject:"",writer:"",content:"",bname:"BBS"});

    function saveData(){
        //  비동기식 통신을 수행하자.
        axios.post(api_url,{bname:vo.bname,subject:vo.subject,writer:vo.writer,content:vo.content})
        .then(function(json){
            if(json.data.cnt==1){
                router.push("/") // int cnt가 반환 값이므로 insert 잘 됐으면 메인 페이지로 이동
            }
        })
    }

    function changeVO(e){
        // 목적은 useState의 vo객체를 변경하는 것.
        // 그렇다면 vo를 복사하자
        let bbs = {...vo};
        let value = e.target.value; // 값: 입력한거 값을 받는거임 value
        let name=e.target.name; // 입력한곳의 name예를들어서 parameterType받는거 nameSpace
        bbs[name] = value;
        setVO(bbs);// useSTate의 값을 최종적으로 변경하자.!*******중요
    }

    return(
        <div style={{width:'90%', margin:'auto',padding:'20px'}}>
                <TableContainer component={Paper}>
                    <Table sx={{minWidth:650}}>
                        <TableBody>
                            <TableRow>
                                <TableCell>제목</TableCell>
                                <TableCell>
                                    <input type="text" name="subject" onChange={changeVO}/>
                                </TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell>글쓴이</TableCell>
                                <TableCell>
                                    <input type="text" name="writer" onChange={changeVO}/>
                                </TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell>내용</TableCell>
                                <TableCell>
                                    <textarea name="content" rows={7} cols={50} onChange={changeVO}></textarea>
                                </TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell colSpan={2}>
                                    <Button variant="contained" color="primary"
                                    onClick={saveData}>
                                        저장
                                    </Button>
                                </TableCell>
                            </TableRow>
                        </TableBody>
                    </Table>
                </TableContainer>
        </div>
    );
}
