import  NoticeTR from "@/components/noticeTR";

export default function notice(){
    let subject=['가을이 코앞에','리엑트 좋네', '테스트입니다']
    let date=['2025-09-01','2025-09-02','2025-09-03']
    return(
        <div>
            <h1 className="title">게시판</h1>
            <hr/>
            <div>
                <table className="t1">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>등록일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                    {/* JSX주석-- 다음은 컴포넌트를 활용한 예 */}
                    <NoticeTR subject={subject[0]} writer="임명희" date={date[0]}/>
                    <NoticeTR subject={subject[1]} writer="한상구" date={date[1]}/>
                    <NoticeTR subject={subject[2]} writer="김철기" date={date[2]}/>
                </tbody>
                </table>
            </div>
            </div>
    );
}