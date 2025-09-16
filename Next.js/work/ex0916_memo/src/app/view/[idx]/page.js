"use client"
import OneMemo from "@/components/OneMemo";
import axios from "axios";
import { use,useEffect, useState } from "react";

function Page({params}){

const {idx} = use(params);
console.log("idx:", idx);
const api_url=`/memo/${idx}`
const [vo, setVO]=useState({ });
////
axios.get(api_url).then(function(res){
  console.log("res 전체:", res.data);
  setVO(res.data.vo);
});
////
function getData(){
axios.get(api_url).then(function(res){
    console.log("abcdefghijk",res.data.vo)
setVO(res.data.vo)
})
}
useEffect(function() {getData()},[idx])

return(
    <OneMemo memoData={vo}/>
)
}

export default Page;