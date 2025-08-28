<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">

    <style>
        #list_table{
            border-collapse: collapse;
            width: 400px;
        }
        #list_table th, #list_table td{
            border: 1px solid #27a;
            padding: 3px;
        }
        #list_table thead th{
            background: #5ad;
            color: #fff;
        }
        #list_table caption{
            font-size: 30px;
            font-weight: bold;
            padding-bottom: 20px;
        }

        .btn{
            width: 70px;
            height: 20px;
            text-align: center;
            padding:0px;
        }

        .btn a{
            display: block;
            width: 100%;
            padding: 4px;
            height: 20px;
            line-height: 20px;
            background: #27a;
            color: #fff;
            border-radius: 3px;
            text-decoration: none;
            font-size: 12px;
            font-weight: bold;
        }
        .btn a:hover{
            background: #fff;
            color: #27a;
            border: 1px solid #27a;
        }

        .txt_center{
            text-align: center;
            height: 50px;
        }

        .show{
            display: block;
        }

        #list_table thead tr:first-child td{
            border: none;
        }

        #writer{
            background-color: #dedede;
            border: 1px solid #ababab;
        }
        #cmd_win{
            display: none;
        }
    </style>
</head>
<body>

<div id="wrap">
    <table id="list_table">
        <caption>메모 리스트</caption>
        <colgroup>
            <col width="50px">
            <col width="*">
            <col width="80px">
            <col width="90px">
        </colgroup>
        <thead>
        <tr>
            <td colspan="4" >
                <p class="btn">
                    <a href="javascript:writeMemo()">
                        글쓰기
                    </a>
                </p>
            </td>
        </tr>
        <tr>
            <th>번호</th>
            <th>내용</th>
            <th>글쓴이</th>
            <th>등록일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vo" items="${ar}">
            <tr>
                <td>${vo.idx}</td>
                <td>${vo.content}</td>
                <td>${vo.writer}</td>
                <td>${vo.reg_date}</td>
            </tr>
        </c:forEach>
        <c:if test="${ar eq null}">
            <tr>
                <td colspan="4" class="txt_center">
                    현재 등록된 데이터가 없습니다.
                </td>
            </tr>
        </c:if>

        </tbody>
    </table>
</div>


<div id="write_win" title="글쓰기">
    <form action="/memo/add" method="post" name="frm">
        <table>
            <tbody>

            <tr>
                <td><label for="writer">작성자:</label></td>
                <td>
                    <input type="text" id="writer"
                           name="writer"
                           value=""/>
                </td>
            </tr>
            <tr>
                <td><label for="content">내용:</label></td>
                <td>
         <textarea cols="40" rows="6"
                   id="content" name="content"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p class="btn">
                        <a href="javascript:exe()">
                            저장
                        </a>
                    </p>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>



<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>
<script>

    $(function (){

        let option = {
            modal: true,
            autoOpen: false,
            title: '메모 추가',
            width: 450,
            height: 280,
            resizable: false,
        };

        $("#write_win").dialog(option);

    });

    function writeMemo() {
        // 숨겨진 dialog를 보이도록 해야한다.
        $("#write_win").dialog("open");
    }

    function exe() {
        let writer = $("#writer").val().trim();
        let content = $("#content").val().trim();

        if(content.length == 0){
            alert("내용을 입력하세요");
            $("#content").val("");
            $("#content").focus();
            return;
        }

        if(writer.length == 0){
            alert("이름을 입력하세요");
            $("#writer").val("");
            $("#writer").focus();
            return;
        }

        document.frm.submit();
    }
</script>
</body>
</html>










