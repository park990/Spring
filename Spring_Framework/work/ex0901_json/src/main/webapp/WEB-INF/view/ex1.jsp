<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
</head>
<style>
    #box {
        width: 300px;
        height: 300px;
        border: 2px solid black;
    }
</style>
<body>
<h1>비동기식 통신으로 json결과 받기</h1>
<hr/>
<button type="button" id="bt1">연습1</button>
<button type="button" id="bt2">사원2</button>
<div id="box"></div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
    $(function () {
        $("#bt1").on("click", function () {
            // 연습1 버튼을 클릭 할 때 수행
            $.ajax({
                url: "/callTest", // 컨트롤러에 RequestMapping을 의미
                type: "POST",
                dataType: "json"
            }).done(function (res) {
                // 비동기식 통신이 성공하였을 때
                console.log(res);
                let code = "<ul>";
                for (let i = 0; i < res.length; i++) {
                    code += "<li>";
                    code += res.ar[i].ename;
                    code += ",";
                    code += res.ar[i].email;
                    code += "</li>";
                }
                code += "</ul>";

                $("#box").html(code);
            })

        });


        $("#bt2").on("click", function () {
            $.ajax({
                url:"/emp",
                type: "POST",
                dataType: "json"
            }).done(function (res) {
                let code = `<ul>`;
                for (let i = 0; i < res.length; i++) {
                    code += "<li>";
                    code += res.ar[i].empno;
                    code += ",";
                    code += res.ar[i].ename;
                    code += ",";
                    code += res.ar[i].job;
                    code += ",";
                    code += res.ar[i].mgr;
                    code += ",";
                    code += res.ar[i].hiredate;
                    code += ",";
                    code += res.ar[i].sal;
                    code += '</li>';
                }
                code += "</ul>";
                $("#box").html(code);
            });

        })// 2번째 버튼
    });//function의 마지막
</script>
</body>

</html>
