"use client"
import { Box, Grid } from "@mui/material";
import Link from "next/link";

export default function Nav(){
    const navItem =[{title:"Products", path:"/products"},
                    {title:"Color cosmetics", path:"/color"},
                    {title:"Skin cosmetics", path:"/skin"},
                    {title:"Board", path:"/board"},
                    {title:"About", path:"/about"}];

    return(
        <nav id ="header">
            <Grid container my={2}>
                {navItem.map(function(item,i){
                    return(
                        <Grid item key={i} size={{xs:12, md:2}}>
                        <Link key={i} href={`${item.path}`}>
                        <Box bgcolor="primary.light" p={2}>{item.title}</Box>
                    </Link>
                    </Grid>
                    )
                })
                }
            </Grid>
        </nav>
    );
}

