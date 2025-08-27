<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>10번 부서</h1>
<hr/>
<ul>
<c:forEach var="vo" items="${ar}">

    <li>${vo.empno}/${vo.ename}/${vo.deptno}</li>

</c:forEach>
</ul>
</body>
</html>
