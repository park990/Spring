<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editor연습</title>
    <link href="resources/css/summernote-lite.css" rel="stylesheet"/>
</head>
<body>
<h1>Editor 연습</h1>
<div>
    <form action="write" method="post" enctype="multipart/form-data">
        <table>
            <caption>글쓰기</caption>
            <tbody>
            <tr>
                <td>제목</td>
                <td><input type="text" id="title" name="title"/></td>
            </tr>
            <tr>
                <td>내용</td>
                <td><textarea id="content" name="content" cols="50" rows="8"></textarea></td>
            </tr>
            <tr>
                <td>첨부파일 :</td>
                <td><input type="file" id="sss" name="sss"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button" id="btn1">저장</button>
                    <button type="button" id="btn2">취소</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="resources/js/summernote-lite.js"></script>
<script src="resources/js/lang/summernote-ko-KR.js"></script>
<script>
    $(function () {
        $("#content").summernote({
            lang: "ko-KR",
            callbacks: {
                onImageUpload: function (files, editor) {
                    for (let i = 0; i < files.length; i++)
                        saveImage(files[i], editor);

                }
            } // callback마지막
        });

        $("#btn1").on("click",function (){
               document.forms[0].submit();
        });

    });//function의 끝

    function saveImage(file, editor) {
        // 서버로 비동기식 통신을 통해 파일을 보내기 위해 폼객체 준비
        let frm = new FormData();

        // 서버로 보내기 전에 폼객체에 파일을 미리 파라미터로 저장하자
        frm.append("upload", file)

        $.ajax({
            url: "saveImg",
            data: frm,
            type: "POST",
            contentType: false,
            processData: false, // 첨부파일을 보내는 것이고 일반전송이 아님을 알리는 것.
            dataType: "json"
        }).done(function (res) {
            $("#content").summernote("editor.insertImage", res.url + res.fname);
        });// 성공 여기까지
    }

</script>
</body>
</html>
