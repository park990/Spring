function list(){
    let title="방명록"
    let ar1=[{title:'자바바이블',date:'2025-09-14',content:'맞나?'},
            {title:'스프링 용어집',date:'2025-09-18',content:'스피링요어집이라고합니다'},
            {title:'AI활용',date:'2025-09-03',content:'ai를 배워야합니다.'}];

    return(
        <div className="listbox">
            <h2 className="title">{title}</h2>
            <hr/>
            {

            ar1.map(function(data, i){
                    return(
                        <div className="list" key={i}>
                            <header>
                            <h4>{data.title}</h4>
                            <p>{data.date}</p>
                            <p>{data.content}</p>
                            </header>
                        </div>
                    );
            })

            }




        </div>
    );
}
export default list;