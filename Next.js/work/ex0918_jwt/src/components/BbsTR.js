import { TableCell, TableRow } from "@mui/material";
import Link from "next/link";

export default function BbsTR(item){
    return(
                        <TableRow>
                            <TableCell>{item.idx}</TableCell>

                            <TableCell>
                                <Link href={`bbs/${item.b_idx}`}>
                                {item.title}
                                </Link>
                            </TableCell>
                            <TableCell>{item.writer}</TableCell>
                            <TableCell>{item.write_date}</TableCell>
                            <TableCell>{item.hit}</TableCell>
                        </TableRow>
                            )
}