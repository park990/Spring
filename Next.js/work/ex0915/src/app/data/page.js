function popular(){
    let title="자료실"
    let ar1=['자바바이블 예제','스프링 용어집','AI활용'];
    let ar2=['2025-09-02','2025-09-03','2025-09-04']

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
                            <p><img src={`/images/book${i+1}.png`} className="book" alt={data}></img></p>
                            </header>
                        </div>
                    );
            })

            }




        </div>
    );
}
export default page;