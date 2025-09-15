"use client"
import { useState } from "react";

function Page(){
    let title="자료실"
    let ar1=['자바바이블 예제','스프링 용어집','AI활용'];
    let ar2=['2025-09-02','2025-09-03','2025-09-04']
    // let [cnt, setCnt] = useState(0);
    let[cnt,setCnt]=useState([0,0,0]);
    return(
        <div>
            <h2 className="title">{title}</h2>
            <hr/>
            {

            ar1.map(function(data, i){
                    return(
                        <div className="box" key={i}>
                            <header>
                            <h4>{data}</h4>
                            <p>{ar2[i]}</p>
                            <div>
                            <img src={`/images/book${i+1}.png`} className="book"></img>
                            </div>
                            <div className="fr">
                                <span>{cnt[i]}</span>&nbsp;
                                <button onClick={function(){
                                    // 버튼을 클릭할 때마다 수행하는 곳
                                    //use State의 값을 증가 하자
                                    let cnt2=[...cnt]; //cnt의 내용 복사
                                    //cnt2는 cnt가 기억하고 있는 숫자 3개짜리 배열 그대로 복사
                                    ++cnt2[i]
                                    setCnt(cnt2);
                                }}>+</button>

                            </div>
                            </header>
                        </div>
                    );
            })

            }




        </div>
    );
}
export default Page;