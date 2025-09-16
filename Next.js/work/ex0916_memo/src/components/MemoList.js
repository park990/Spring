import { Card, CardContent } from "@mui/material";
import Link from "next/link";

function Memo({mList}){
    return(
        <Card style={{width:'500px',margin:'20px auto'}}>
            <CardContent>
                {mList.map((item, i) => (
                        <Link key={i} href={`/view/${item.idx}`}>
                        <div className="list-item">
                        <h4 className="item-list-h4">{item.content}</h4>
                        <p className="item-list-p">{item.writer}/{item.reg_date}</p>
                        </div>
                    </Link>
                    )
                )}
            </CardContent>
        </Card>
    )
}

export default Memo;