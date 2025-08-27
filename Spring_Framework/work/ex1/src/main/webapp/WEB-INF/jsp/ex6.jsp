<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>회원목록</h1>
<hr/>
<ul>
<c:forEach var="ar" items="${ar}">
    <li>${ar.name} / ${ar.phone}</li>
</c:forEach>

</ul>
</body>
</html>
