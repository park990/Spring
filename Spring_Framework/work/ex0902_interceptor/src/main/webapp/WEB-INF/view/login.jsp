<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="nav.jsp" var="nav"/>
<c:import url="foot.jsp" var="foot"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <!-- 외부CSS 연결하기 -->
  <link rel="stylesheet" type="text/css" href="resources/css/common.css"/>
  <link rel="stylesheet" type="text/css" href="resources/css/login.css"/>
</head>
<body>
<div id="wrap">
  <!--========= 상단영역 =======-->
 ${nav}
  <!--======== 상단영역 끝 ======-->
  <!--======== 콘텐츠 영역 ======-->
  <div id="contents">
    <h1 class="sub_title tit02">회원 로그인</h1>
    <div class="login_area">
      <!-- 일반회원로그인 -->
      <div class="person_login">
        <h2 class="sub_title tit03">개인회원</h2>
        <div class="login">
          <form action="/login" method="post" name="frm1">
            <div class="input_area">
              <p>
                <label for="s_id">아이디</label>
                <input type="text" id="s_id" name="m_id"/>
              </p>
              <p>
                <label for="s_pw">비밀번호</label>
                <input type="password" id="s_pw" name="m_pw"/>
              </p>
            </div>
            <div class="btn_area">
              <span class="btn b_login">
                <a href="javascript:login()">로그인</a>
              </span>
            </div>
            <p class="login_search">
              <input type="checkbox" id="ch01" name="chk"/>
              <label for="ch01">아이디저장</label>
              <span class="btn b_search">
                <a href="">아이디/비밀번호찾기</a>
              </span>
            </p>
          </form>
        </div>
      </div>

      <!-- 단체회원로그인 -->
      <div class="group_login">
        <h2 class="sub_title tit04">단체회원</h2>
        <div class="login">
          <form action="" method="post">
            <div class="input_area">
              <p>
                <label for="s_id2">아이디</label>
                <input type="text" id="s_id2" name="m_id"/>
              </p>
              <p>
                <label for="s_pw2">비밀번호</label>
                <input type="password" id="s_pw2" name="m_pw"/>
              </p>
            </div>
            <div class="btn_area">
              <span class="btn b_login">
                <a href="">로그인</a>
              </span>
            </div>
            <p class="login_search">
              <input type="checkbox" id="ch02" name="chk"/>
              <label for="ch02">아이디저장</label>
              <span class="btn b_search">
                <a href="">아이디/비밀번호찾기</a>
              </span>
            </p>
          </form>
        </div>
      </div>
    </div>
  </div>
  <!--======= 콘텐츠영역 끝 =====-->

  <!--======= 하단영역 ======-->
 ${foot}
  <!--======= 하단영역 끝 =====-->
<script>
  function login(){
    document.frm1.submit();
  }
</script>
</div>
</body>
</html>