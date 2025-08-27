<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<c:forEach var="ar" items="${ar}">
  ${ar.empno} / ${ar.ename}
</c:forEach>
</body>
</html>
