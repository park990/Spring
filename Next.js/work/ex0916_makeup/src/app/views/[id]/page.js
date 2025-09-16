"use client"

import Item from "@/components/Item";
import axios from "axios";
import {use, useEffect, useState } from "react";

export default function Page({params}){
    // const id =params.id;
    // const id ='{params.id}';
    const {id}= use(params)

    const api_url=`/api/v1/products/${id}.json`;
    const [vo, setVO]=useState({    });

    function getData(){
        axios.get(api_url).then(function(res){
            setVO(res.data)
        });
    }

    // 현재문서에서 id변수의 값이 변경될 때마다 getData함수 호출
    useEffect(function(){if(id && id>0) getData();},[id]);


    return(
        <div style={{width:'80%',margin:'auto',padding:'20px'}}>
            <Item item={vo}/>
        </div>
    );
};