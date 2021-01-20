<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
 <jsp:include page="menu/menubar2.jsp" />
<html>
<head>

<meta charset="UTF-8">
<title>백업</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<body>
<div class="container">
	<b>백업 구문 로그 생성 </b>  <br><br> 
	<font size="3" color="gray"> <b>테이블명 : board</b></font> <br><br>
	 
	<c:forEach var="backup" items="${boardbackup}">
<font color="red">insert into board (board_id, userid,subject,content, write_date ) values </font>

	<font color="orange">(${backup.board_id }, '${backup.userid }', '${backup.subject }'</font>, <font color="green"><c:out value="'${backup.content}'" escapeXml="true"/> </font>,
	<font color="blue"> now()</font> );
	<%-- <font color="blue"> ${backup.write_date[0]}</font> ); 
	sql상에는 now()로 타임스탬프라서 시간 백업 안됨띠--%>
	 <br><br>





</c:forEach>

</div>
</body>
</html>
