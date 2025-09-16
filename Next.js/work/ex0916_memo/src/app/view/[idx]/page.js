"use client"
import OneMemo from "@/components/OneMemo";
import axios from "axios";
import { use, useEffect, useState } from "react";

function Page({params}){

const {idx} = use(params);{}
const api_url=`/memo/${idx}`
const [vo, setVO]=useState({ });

function getData(){
axios.get(api_url).then(function(res){
    console.log(res)
setVO(res.data)
})
}
useEffect(function() {getData()},[idx])

return(
    <OneMemo item={vo}/>
)
}

export default Page;