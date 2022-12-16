<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<c:forEach var="arrayList" items="${arrayList}">
<c:if test="${arrayList.umail eq param.umail}">
<script type="text/javascript">
alert("입력하신 ${param.umail}이 존재합니다.");
location.href="./MemberInsertView.me";
</script>
</c:if>
</c:forEach>
</head>
<body>
<script type="text/javascript">
alert("${uname} 님 반갑습니다.")
location.href="./MemberSelect.me"
</script>
</body>
</html>