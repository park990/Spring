import { Button, Divider, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import Link from "next/link";
import styles from "../page.module.css"

function Page(){
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
                        <TableRow>
                            <TableCell>1</TableCell>
                            <TableCell>연습입니다.</TableCell>
                            <TableCell>글쓴이</TableCell>
                            <TableCell>2025-09-11</TableCell>
                            <TableCell>0</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    )

}
export default Page;