"use client"
import BbsList from '@/components/BbsList';
import axios from 'axios';
import * as React from 'react';
import { useState , useEffect} from 'react';

function Page(){
    const api_url="/board/list";
    const [bname, setBname]=useState("BBS");
    const [list,setList]=useState([]);
    const [cPage,setCpage]=useState(1);
    const [totalPage,setTotalPage]=useState(0);

    function callData(){
    axios.get(api_url,{params:{bname:bname, cPage:cPage}}).then(function(json){
        setList(json.data.ar);
        setTotalPage(json.data.totalPage);
    });
    }

    useEffect(function(){
        callData();
    }),[cPage]

    function changePage(e,p){
        setCpage(p)
    }

    return(
        <div style={{width:'90%', margin:'auto',padding:'20px'}}>
        <BbsList ar={list} tp={totalPage} cp={changePage}/>
        {/* cp라는 이름으로 changePage 함수를 전달한다. */}
        </div>
    )
}
export default Page;