"use client"

import Header from "@/components/Header";
import { Button, Divider, Paper, Table, TableBody, TableCell, TableContainer, TableFooter, TableHead, TableRow } from "@mui/material";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";


function Page(){
    let router= useRouter();
    api_url="/api/login"


    const[member, setMember]=useState({})

     function handleChange(e){
        let{name,value}=e.target;
        setMember({...member,[name]:value});
    }

    function signIn(){
        axios.get(api_url).then(function(res){
            const token= res.data.access_token;

            if(token!=null){
            router.push("/")
            }
        })
    }
    return(
        <div style={{width:"90%",margin:"10px auto"}}>
            <h2>로그인</h2>
            <Divider/>
            <div style={{width:"300px",margin:"10px auto"}}>
            <TableContainer component={Paper}>
                <Table className="t1">
                    <TableBody>
                        <TableRow>
                            <TableCell>사용자 ID</TableCell>
                            <TableCell>
                                <input type="text" id="u_id" name="mid" onChange={handleChange}/>
                            </TableCell>
                        </TableRow>

                        <TableRow>
                            <TableCell>사용자 PW</TableCell>
                            <TableCell>
                                <input type="password" id="u_pw" name="mpw" onChange={handleChange}/>
                            </TableCell>
                        </TableRow>
                    </TableBody>
                    <TableFooter>
                        <TableRow>
                            <TableCell colSpan={2} className="{styles.txtCenter">
                                <Button variant="contained" color="primary">로그인</Button>
                            </TableCell>
                        </TableRow>
                    </TableFooter>
                </Table>
            </TableContainer>
            </div>
        </div>
    )
}
export default Page;