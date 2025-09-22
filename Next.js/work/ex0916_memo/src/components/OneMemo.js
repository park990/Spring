import { Button, Card, CardContent, Divider } from "@mui/material";
import Link from "next/link";

function OneMemo({memoData}){

return(
        <Card style={{width:'500px',margin:'20px auto'}}>
            <CardContent>
                        <div className="list-item">
<<<<<<< HEAD
                        <h4 className="item-list-h4">{memoData.idx}번 메모</h4>
                        <h5><stronger>내용:</stronger>{memoData.content}</h5>
                        <p className="item-list-p">{memoData.writer}/{memoData.reg_date}</p>
=======
                        <h4 className="item-list-h4">글쓴이: {memoData.writer}</h4>
                        <h5><stronger>내용:</stronger> {memoData.content}</h5>
                        <p className="item-list-p">{memoData.reg_date}</p>
>>>>>>> 0a82d90045e9107c8b461d09a532d35f506e854c
                        </div>
                        <Divider/>
                        <Button variant="contained" color="error">뒤로</Button> &nbsp;
                        <Button variant="contained" color="error">편집</Button> &nbsp;
                        <Button variant="contained" color="error">삭제</Button> &nbsp;
            </CardContent>
        </Card>
    )
}
export default OneMemo;