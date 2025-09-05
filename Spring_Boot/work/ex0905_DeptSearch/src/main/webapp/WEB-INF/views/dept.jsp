<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>부서 목록</title>
    <style>
        /* 전체 컨테이너 스타일 */
        #wrap {
            width: 800px;
            margin: 20px auto; /* 가운데 정렬 */
            font-family: 'Malgun Gothic', sans-serif;
        }

        /* 제목 스타일 */
        h1 {
            text-align: center;
            color: #333;
        }

        /* 테이블 스타일 */
        table {
            width: 100%;
            border-collapse: collapse; /* 테두리 겹침 방지 */
            margin-top: 20px;
        }

        /* 테이블 캡션(제목) 스타일 */
        caption {
            text-indent: -9999px;
        }

        /* 테이블 헤더(th) 및 셀(td) 스타일 */
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
        }

        /* 테이블 헤더 배경색 */
        thead th {
            background-color: #f2f2f2;
        }

        /* 검색 폼 요소 스타일 */
        select, input[type="text"], button {
            padding: 5px;
            margin: 0 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        button {
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div id="wrap">
    <header>
        <h1>부서목록</h1>
    </header>

    <article>
        <table id="d1">
            <caption>부서목록 테이블</caption>
            <thead>
            <tr>
                <td colspan="4">
                    <form action="search" method="post">
                        <select name="type" id="type">
                            <option value="0">부서코드</option>
                            <option value="1">부서명</option>
                            <option value="2">도시코드</option>
                        </select>
                        <input type="text" name="value" id="value" />
                        <button type="button" onclick="exe(this.form)">검색</button>
                    </form>
                </td>
            </tr>
            <tr>
                <th>부서코드</th>
                <th>부서명</th>
                <th>도시코드</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${ar ne null}">
                <c:forEach var="vo" items="${ar}">
                    <tr>
                        <td>${vo.deptno}</td>
                        <td>${vo.dname}</td>
                        <td>${vo.loc_code}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </article>
</div>
<script
        src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>
<script>
    function exe(frm) {
        let v= frm.value.value;
        if(v.trim().length<1){
            alert("검색어 입력하세요")
            frm.value.focus();
        }else{
            let type= $("#type").val();
            let value= $("#value").val();
            $.ajax({
                url:"search",
                dataType:"JSON",
                method:"POST",
                data:{"type":type,
                      "value":value}
            }).done(function (res){
                let str="";
                if(res.ar!=null&&res.length>0){
                    for(let i =0; i<res.length;i++){
                        if(res.ar[i]!=null){
                            str+="<tr><td>"
                            str+=res.ar[i].deptno;
                            str+="</td><td>"
                            str+=res.ar[i].dname;
                            str+="</td><td>"
                            str+=res.ar[i].loc_code;
                            str+="</td></tr>"
                        }
                    }// 반복 끝
                }
                $("#d1 tbody").html(str)
            });
        }

    }
</script>

</body>
</html>