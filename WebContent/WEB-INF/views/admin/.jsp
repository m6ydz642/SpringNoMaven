<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
 <jsp:include page="../menu/menubar2.jsp" />
 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>어드민 글수정 페이지</title>
</head>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<body>
 
<!--<h2> 게시글 작성 페이지 </h2>-->
 
<div class="container">
    <form action="/boardmodifywritecomplete" method="post">
      <div class="form-group">
        <label for="subject">제목</label>
        <input type="text" class="form-control" id="subject" name="modifysubject" value="<c:out value="${boardcontent.subject}" />">
      </div>
      <div class="form-group">
        <!-- <label for="writer">작성자</label> -->
      <!--   <input type="text" class="form-control" id="writer" name="writer" placeholder="내용을 입력하세요."> -->
       <p><b>작성자 : ${boardcontent.userid}</b></p>
      </div>
      <div class="form-group">
        <label for="content">어드민 글수정 페이지 입니다</label>
       <textarea class="form-control" id="content" name="modifycontent" rows="3" style="margin: 0px -3px 0px 0px; height: 248px; width: 567px;" value=""><c:out value="${boardcontent.content}" /></textarea>
          
      </div>
      <input name ="board_id" value ="<c:out value="${boardcontent.board_id}"/>" readonly="readonly"  hidden="">
	<!-- 	<br> ↑↑ 조만간 hidden으로 숨길꺼임  <br><br> -->
		
      <button type="submit" class="btn btn-primary" onclick="alert('수정완료 되었습니다');">수정완료</button>
    </form>
</div>

</body>
</html>


