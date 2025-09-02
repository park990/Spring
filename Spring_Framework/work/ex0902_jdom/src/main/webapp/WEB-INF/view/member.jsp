<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <style>
        #t1 {
            border-collapse: collapse;
            width: 400px;
        }

        #t1 caption {
            text-indent: -9999px;
            height: 0;
        }

        #t1 th {
            padding: 6px;
            background-color: #dedede;
            border: 1px solid black;
        }

        #t1 td {
            padding: 4px;
            border: 1px solid black;
        }

        #t1 .no-border {
            border: none;
        }

        #type, #value, #btn1 {
            padding: 5px;
        }

        .w150 {
            width: 150px;
        }
    </style>
</head>
<body>
<h1>회원목록</h1>
<hr/>
<table id="t1">
    <caption>회원목록테이블</caption>
    <thead>
    <tr>
        <td colspan="3" class="no-border">
            <form action="search" method="post">
                <select id="type">
                    <option value="0">이름</option>
                    <option value="1">이메일</option>
                    <option value="2">연락처</option>
                </select>
                <input type="text" id="value" class="w150"/>
                <button type="button" id="btn1">검색</button>
            </form>
        </td>
    </tr>
    <tr>
        <th>회원명</th>
        <th>이메일</th>
        <th>연락처</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ar}" var="vo">
        <tr>
            <td>${vo.name}</td>
            <td>${vo.email}</td>
            <td>${vo.phone}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script
        src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>
<script>
    $(function () {
        $("#btn1").on("click", function () {
            let dtype = $("#type").val();
            let dval = $("#value").val();
            console.log("클릭")
            $.ajax({
                url: "search",
                type: "POST",
                dataType: "JSON",
                data: {
                    "type": dtype,
                    "val": dval
                }
            }).done(function (res) {
                let str = "";
                if (res.ar != null && res.length > 0) {
                    for (let i = 0; i < res.length; i++) {
                        if(res.ar[i]!=null){
                            str+="<tr onclick='viewData(this)'><td>";
                            str+=res.ar[i].name;
                            str+="</td><td>"
                            str+=res.ar[i].email;
                            str+="</td><td>"
                            str+=res.ar[i].phone;
                            str+="</td></tr>"
                        }
                    }// for 끝
                }
                $("#t1 tbody").html(str);
            });
        });
    });
</script>
</body>
</html>
