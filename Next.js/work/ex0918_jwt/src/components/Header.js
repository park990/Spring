import Link from "next/link"

export default function Header(){
    const menuItem=[{title:"Home",path:"/home"},
                    {title:"Members",path:"/members"},
                    {title:"Board",path:"/bbs"}]

    const loginItem=[{title:"Login",path:"/members/login"},
                     {title:"Signup",path:"/members/signup"}]

    return(
        <div className="headWrap">
            <div className = "left_bar">
            {menuItem.map(function(item,i){
                return(
                <div key ={i} className = "left_item">
                    <Link href={`${item.path}`}>
                    {item.title}
                    </Link>
                </div>
                )
            })
            }
            </div>
                
            <div className = "right_bar"> 
            {loginItem.map(function(item,i){
                return(
                <div key={i} className = "right_item">
                    <Link href={`${item.path}`}>
                    {item.title}
                    </Link>
                </div>
                )
            })
            }
            
            </div>  
        </div>
    )
}