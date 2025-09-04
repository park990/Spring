<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <meta charset="utf-8">
</head>
<body>
<header>
  <h1>로그인</h1>
</header>
<article>
  <%--세션에 mvo라는 이름으로 값이 저장되어 있지 않을때 로그인 화면 보여줘야 함--%>
  <c:if test="${sessionScope.mvo eq null}">
    <form method="post" action="">
      ID:<input type="text" name="m_id"/><br/>
      PW:<input type="password" name="m_pw"/><br/>
      <button type="button">LOGIN</button>
    </form>
    <br/>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=045ad8ec819ec6a169e07d7aa6bedb78&redirect_uri=http://localhost:8080/login/kakao&response_type=code">
      <img src="resources/images/kakao.png"/>
    </a>
  </c:if>
</article>
</body>
</html>
