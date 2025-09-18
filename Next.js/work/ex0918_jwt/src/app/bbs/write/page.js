"use client"
import { Button, Divider, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import axios from "axios";
import Link from "next/link";
import { useState } from "react";

export default function Page(){

    // 서버 URL
    const api_url="/das";

    //사용자가 입력한 값들을 하나의 객체로 저장할 곳
    const[bbs,setBbs] =useState({});  // 중괄호 대괄호 차이를 알자***********

    function handleChange(e){
        const {name,value} =e.target;
        setBbs({...bbs,[name]:value})
    }


    function sendBbs(){
        axios.get(api_url).then(function(res){
            // res.data.??
        })
    }

    return(
        <div style={{width:"90%",margin:"10px auto"}}>
            <h2>게시판</h2>
            <Divider/>

            <TableContainer component={Paper}>
                <Table className="t1">
                    <TableBody>
                        <TableRow>
                            <TableCell>제목</TableCell>
                            <TableCell colSpan={3}>
                                <input type="text" id="title" name="title" onChange={handleChange}/>
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>글쓴이</TableCell>
                            <TableCell colSpan={3}>
                                <input type="text" id="writer" name="writer" onChange={handleChange}/>
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>내용</TableCell>
                            <TableCell colSpan={3}>
                                <textarea cols={40} rows={5} id="content"
                                 name="content" onChange={handleChange}>
                                </textarea>
                            </TableCell>
                        </TableRow>
                    

                    <TableRow>
                        <TableCell colSpan={4}>
                            <Link href="/bbs">
                            <Button variant="contained" color="primary">뒤로</Button>
                            </Link> &nbsp;

                            <Link href="">
                            <Button variant="contained" color="primary" onClick={sendBbs}>저장</Button>
                            </Link>
                        </TableCell>
                    </TableRow>
                </TableBody>
                </Table>
            </TableContainer>
        </div>
    
    )
}