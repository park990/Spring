<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
</head>
<header><h1>카카오지도 연습</h1></header>
<body>
<article>
    <div id="map" style="width: 500px; height: 400px"></div>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b4ac8856a604bf44f9398b2f25ca738f"></script>
<script>
    var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    var options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.499334, 127.033162), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
    };

    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리

    var markerPosition  = new kakao.maps.LatLng(37.499334, 127.033162);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });
    marker.setMap(map);
</script>
</article>
</body>
</html>
