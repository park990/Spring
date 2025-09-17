import { Button, Pagination, Paper } from "@mui/material";
import { TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import { useRouter } from "next/navigation";

export default function BbsList({ar,tp,cp}){
    const router =useRouter();
    return(
        <TableContainer component={Paper}>
            <table sx={{minWidth:650}} width={1200}>
                    <TableHead>
                        <TableRow>
                            <TableCell>번호</TableCell>
                            <TableCell>제목</TableCell>
                            <TableCell>글쓴이</TableCell>
                            <TableCell>날짜</TableCell>
                            <TableCell>조회수</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                {ar.map((row,i) => (
                    <TableRow key={i} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                        <TableCell component="th" scope="row">{row.b_idx}</TableCell>
                        <TableCell align="right">{row.subject}</TableCell>
                        <TableCell align="right">{row.writer}</TableCell>
                        <TableCell align="right">{row.write_date}</TableCell>
                        <TableCell align="right">{row.hit}</TableCell>
                    </TableRow>
                ))}   
                
                        {/* 페이징 기법을 위한 행*/}
                        <TableRow>
                            <TableCell colSpan={4}>
                                <Pagination count={tp} color="primary" onChange={cp}/>
                            </TableCell>
                            <TableCell>
                                <Button variant="contained" color="primary"
                                    onClick={function(){
                                        router.push("/board/write")
                                    }}>글쓰기</Button>
                            </TableCell>
                        </TableRow>
                    </TableBody>
            </table>
        </TableContainer>
    );
}