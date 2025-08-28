<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
  <c:forEach var="vo" items="${ar}">
 <li>${vo.writer}/${vo.content}/${vo.reg_date}</li>
 </c:forEach>
</ul>
</body>
</html>
