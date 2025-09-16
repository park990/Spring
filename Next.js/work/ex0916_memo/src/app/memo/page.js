"use client"
import Memo from "@/components/MemoList";
import { Card, CardContent, Divider } from "@mui/material";
import axios from "axios";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function Page(){
    const api_url="/memo/all";
    const[list,setList]=useState([])

    function callApi(){
        axios.get(api_url).then(function(res){
            setList(res.data.m_list)
        });
    }

    useEffect(()=>{     //>>> useEffect(function(){ })
        callApi();
    },[]) // [] : 현재 페이지가 열릴 때 한 번 수행한다.
        
    return(
        <div className="list-bg">
            <h2>메모장</h2>
            <Divider/>
            <Memo mList={list}/>
        </div>
    );
}