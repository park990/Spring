"use client";

import {
  Button,
  Divider,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableFooter,
  TableHead,
  TableRow,
} from "@mui/material";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";
import tokenStore from "@/app/store/TokenStrore";
import axios from "axios";



function Page() {
  let router = useRouter();

  const [member, setMember] = useState({});
  const {accessToken, setToken} = tokenStore();
  const api_url = "/api/members/login";

  function handleChange(e) {
    let { name, value } = e.target;
    setMember({ ...member, [name]: value });
  }

  function signIn() {
    axios.post(api_url, JSON.stringify(member), {
        withCredentials:true,
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then(function (res) {
        if(res.status==200){
            setToken(res.data.data.accessToken);
            console.log(res.data);
            router.push("/")
        }
      });
  }

  return (
    <div style={{ width: "90%", margin: "10px auto" }}>
      <h2>로그인</h2>
      <Divider />
      <div style={{ width: "300px", margin: "10px auto" }}>
        <TableContainer component={Paper}>
          <Table className="t1">
            <TableBody>
              <TableRow>
                <TableCell>사용자 ID</TableCell>
                <TableCell>
                  <input
                    type="text"
                    id="u_id"
                    name="mid"
                    onChange={handleChange}
                  />
                </TableCell>
              </TableRow>

              <TableRow>
                <TableCell>사용자 PW</TableCell>
                <TableCell>
                  <input
                    type="password"
                    id="u_pw"
                    name="mpwd"
                    onChange={handleChange}
                  />
                </TableCell>
              </TableRow>
            </TableBody>
            <TableFooter>
              <TableRow>
                <TableCell colSpan={2} className="{styles.txtCenter">
                  <Button variant="contained" color="primary" onClick={signIn}>
                    로그인
                  </Button>
                </TableCell>
              </TableRow>
            </TableFooter>
          </Table>
        </TableContainer>
      </div>
    </div>
  );
}
export default Page;
