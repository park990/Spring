export default function page(p){
    return(
        <div>
            <h1 className="title">상세보기</h1>
            <hr/>
            <div>
                <h2>{p.params.num}</h2>
            </div>
        </div>
    );
};