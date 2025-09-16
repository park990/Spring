import { Card, CardContent } from "@mui/material";
import Link from "next/link";

function OneMemo({memoData}){

return(
        <Card style={{width:'500px',margin:'20px auto'}}>
            <CardContent>
                        <div className="list-item">
                        <h4 className="item-list-h4">{memoData.idx}번 메모</h4>
                        <h5><stronger>내용:</stronger>{memoData.content}:</h5>
                        <p className="item-list-p">{memoData.writer}/{memoData.reg_date}</p>
                        </div>
            </CardContent>
        </Card>
    )
}
export default OneMemo;