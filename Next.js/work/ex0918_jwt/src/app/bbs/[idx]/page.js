"use client"
import { Button, Divider, Paper, Table, TableBody, TableCell, TableContainer, TableFooter, TableHead, TableRow } from "@mui/material";
import axios from "axios";
import Link from "next/link";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

function Page(){
    const {idx}= useParams();
    const api_url=`/api/bbs/${idx}`
    const [bbs,setBbs]=useState({})

    function getBbs(){
        axios.get(api_url).then(function(res){
            if(res.data.totalCount>0)
            setBbs(res.data.data);
        })
    }

    useEffect(function(){
        getBbs();
    },[idx])
    

    return(
        <div style={{width:"90%",margin:"10px auto"}}>
            <h2>게시판 보기</h2>
            <Divider/>

            <TableContainer component={Paper}>
                <Table className="t1">
                    <TableBody>
                        <TableRow>
                            <TableCell>번호</TableCell>
                            <TableCell colSpan={3}>{idx}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>제목</TableCell>
                            <TableCell colSpan={3}>{bbs.title}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>글쓴이</TableCell>
                            <TableCell>{bbs.writer}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>등록일</TableCell>
                            <TableCell colSpan={3}>{bbs.write_date}</TableCell>
                        </TableRow>               
                        <TableRow>
                            <TableCell>내용</TableCell>
                            <TableCell colSpan={3}>{bbs.content}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>조회수</TableCell>
                            <TableCell colSpan={3}>{bbs.hit}</TableCell>
                        </TableRow>    
                    </TableBody>
                    <TableFooter>
                        <TableRow>
                            <TableCell colSpan={4}>
                                <Link href="/bbs">
                                <Button variant="contained" color="primary">뒤로</Button>
                                </Link>
                            </TableCell>
                        </TableRow>
                    </TableFooter>
                </Table>
            </TableContainer>
        </div>
    )

}
export default Page;