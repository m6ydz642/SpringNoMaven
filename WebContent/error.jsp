<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>저런 에러가 발생했군요 ㅠㅠ</title>
</head>
<body>
	<img src="/error/500error.jpg" width="700" height="700"/> <br>

	<font size="5">저런...<font color="black">500</font>에러가 생겼군요 <br> 
	DB나 DAO쪽을 확인해보세요
	
	</font>
	<br>
	
<font size="8" color="blue"> 에러타입 :<%= exception.getClass().getName() %> </font> <br>
<font size="8" color="red"> 에러메세지 : <b><%=exception.getMessage() %> </font></b>

</body>
</html>
