"use client"
import { Box, Grid } from "@mui/material";

export default function Nav(){
    const navItem =["Products","Color cosmetics","Skin cosmetics","About"];

    return(
        <nav id ="header">
            <Grid container my={2}>
                {navItem.map(function(item,i){
                    return(
                    <Grid item key={i} size={{xs:12, md:3}}>
                        <Box bgcolor="primary.light" p={2}>{item}</Box>
                    </Grid>
                    )
                })
                }
            </Grid>
        </nav>
    );
}

