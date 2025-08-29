<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>JSON TEST</title>
  <meta charset="utf-8">
</head>
<style>
  #box{
    border:1px solid black;
    width: 300px;
    height: 300px;
      overflow:hidden;
  }
</style>
<body>

<h1>JSON연습이에요</h1>
<button type="button" id="btn">확인</button>
<br/>
<div id="box">
  ${m.name}<br/>
  ${m.email}
</div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script>
  $(function (){

    $("#btn").click(function (){
      $.ajax({
          url:"test3",
          date:"v1=100&v2=200",
          type:"POST",
          dataType:"JSON"
      }).done(function (res){
        // let str="";
        // str+="<tr><td>Name:</td><td>";
        // str+=res.name;
        // str+="</td></tr><tr><td>Email:</td><td>";
        // str+=res.email;
        // str+="</td></td>";

          $("#box").html("<h2>Name:"+res.name+"</h2>"+"</br><h2>Email:"+res.email+"</h2>");

      });
    });

  });//function의 끝
</script>

</body>
</html>
