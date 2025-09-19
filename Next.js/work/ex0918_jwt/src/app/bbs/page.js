"use client"
import { Button, Divider, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import Link from "next/link";
import styles from "../page.module.css"
import { useState, useEffect } from "react";
import axios from "axios";
import BbsTR from "@/components/BbsTR";

function Page(){
    // 서버 URL
    const api_url="/api/bbs";

    // 받을 배열
    const [list,setList]=useState([])


    function getBbsList(){
        axios.get(api_url).then(function(res){
            if(res.data.totalCount>0)
            setList(res.data.data)
            console.log(res)
        })
    }

    useEffect(()=>{
        getBbsList();
    },[])

    return(
        <div style={{width:"90%",margin:"10px auto"}}>
            <h2>게시판</h2>
            <Divider/>

            <TableContainer component={Paper}>
                <Table className="t1">
                    <TableHead>
                        <TableRow>
                            <TableCell colSpan={5} className="styles.no_border">
                                <Link href="bbs/write">
                                <Button variant="contained" color="primary">글쓰기</Button>
                                </Link>
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>기본키</TableCell>
                            <TableCell>제목</TableCell>
                            <TableCell>글쓴이</TableCell>
                            <TableCell>등록일</TableCell>
                            <TableCell>조회수</TableCell>
                        </TableRow>
                    </TableHead>
                    
                    <TableBody>
                        {list.map(function(item,i){
                            return(
                                <BbsTR key={i} idx={i+1} b_idx={item.b_idx} title={item.title}
                                writer={item.writer} write_date={item.write_date} hit={item.hit}></BbsTR>
                            )
                        }
                        )}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    )

}
export default Page;