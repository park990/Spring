import { Grid } from "@mui/material";
import Link from "next/link";
import styles from "./css/ItemList.module.css"

export default function ItemList({list}){
    return(
        <div>
            <Grid container>
            {
                list.map(function(item,i){
                    return(
                        <Grid item size={{xs:3}} key={item.id}>
                            <Link href="/views/[id]" as={'/views/'+item.id}>
                                <div className={styles.wrap}>
                                    <img src={item.image_link} alt={item.name} className={styles.img_item}/>
                                    <div className={styles.title_item}>
                                        <strong>{item.name}</strong>
                                    </div>
                                    <span className={styles.txt_info}>
                                        {item.category} &nbsp; {item.product_type}
                                    </span>
                                    <div className={styles.num_price}>
                                        <strong>${item.price}</strong>

                                    </div>
                                </div>
                            </Link>
                        </Grid>
                    )
                })
            }
            </Grid>
        </div>
    )
}