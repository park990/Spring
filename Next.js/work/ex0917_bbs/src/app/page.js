"use client"
import styles from "./page.module.css";

import { Divider } from "@mui/material";

export default function Page() {
  return (
    <div className={styles.page}>
            <div style={{width: '90%', margin:'auto',padding:'20px'}}>
              <h2>SIST연습 페이지</h2>
              <Divider/>
            </div>
    </div>
  );
}
