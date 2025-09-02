
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="foot.jsp" var="foot"/>
<c:import url="nav.jsp" var="nav"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
  <link rel="stylesheet" type="text/css" href="/resources/css/sub_tab.css">
</head>
<body>
<article id="wrap">
  <!--=========================================   상단영역   ===================================================-->
${nav}
  <!--========================================   상단영영 끝   =================================================-->


  <!--=======================================   <<콘텐츠 영역>>   ================================================-->
  <div id="contents">
    <p><img src="/resources/img/@img05.png" alt="이미지5"/></p>
    <div class="tab_type01">
      <ul>
        <li id="t1"><a href="javascript:ex1()">위드유</a></li>
        <li id="t2" class="selected"><a href="">위드유 영상</a></li>
        <li id="t3"><a href="">위드유 대화</a></li>
      </ul>
    </div>

    <div id="tab1" class="tab_count">
      위드유 내용입니다.!
    </div>
    <div id="tab2" class="tab_count show">

      (서울=연합뉴스) 조성미 기자 = SK텔레콤 해킹 사고에 대한 민관 합동 조사단의 최종 조사에서 해커의 공격이 2021년부터 이뤄졌으며 SKT가 2022년 자체 조사로 침해 사실을 발견하고도 제대로 조치하지 않으면서 사태를 키운 사실이 드러났다.
      통화 상대 및 시점 등 민감한 개인 정보가 노출될 수 있어 시선을 끌었던 통신기록(CDR) 유출은 파악된 바 없지만, 로그 기록이 남아있지 않은 2년 반 동안에는 유출 여부를 확인하는 것이 불가능해 미궁으로 남게 됐다.
      과학기술정보통신부를 주축으로 하는 민관 합동 조사단은 4일 정부서울청사에서 지난 4월 23일 조사단을 구성한 이래 진행한 SKT 서버 4만2천600대에 대한 전수 조사 결과를 최종 발표했다.
    </div>
    <div id="tab3" class="tab_count">
      세번째 Tab 내용입니다.!
    </div>

  </div>
  <!--======================================  <<콘텐츠 영역 끝>>   ===============================================-->


  <!--======================================  ***<<하단영역>>***   ===============================================-->
${foot}

  <!--======================================  ***<<하단영역 끝>>***   ===============================================-->

</article>
<!--스크립트-->
<script>
  function ex1(){
    // 첫번째 tab을 클릭했을 때 수행하는 곳

    //현재 문서에서 아이디가 t1인 요소를 검색한다.
    var t1= document.getElementById("t1")
    var t2= document.getElementById("t2")
    var t3= document.getElementById("t3")
    t1.className="selected"// li class="selected"
    t2.className=""
    t3.className=""
    //------탭처리------


    // 해당 탭에 표현한 내용처리
    var tab1= document.getElementById("tab1")
    var tab2= document.getElementById("tab2")
    var tab3= document.getElementById("tab3")
    tab1.className="tab_cont show"
    tab2.className="tab_cont"
    tab3.className="tab_cont"
  }
</script>

</body>
</html>
