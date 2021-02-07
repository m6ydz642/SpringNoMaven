<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${param.virutal_name} 게시판 글 작성</title>
</head>
 <jsp:include page="../menu/menubar2.jsp" />
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<body>
 
<!--<h2> 게시글 작성 페이지 </h2>-->
 
<div class="container">
    <form action="/virutalboardwritecomplete" method="post" accept-charset="utf-8">
      <div class="form-group">
        <label for="subject">게시판명 : ${param.virutal_name}</label> <br><br>
        <input name="virutal_name" value="${param.virutal_name }" hidden="" >
        
        <label for="subject">제목</label> <br>
      
        <input type="text" class="form-control" id="subject" name="subject" placeholder="제목을 입력하세요.">
      </div>
      <div class="form-group">
        <!-- <label for="writer">작성자</label> -->
      <!--   <input type="text" class="form-control" id="writer" name="writer" placeholder="내용을 입력하세요."> -->
       <p ><b>작성자 : <c:out value="${logininfo.userid}"/></b></p>
       <input name ="writer" value=" <c:out value="${logininfo.userid}"/>" readonly="readonly" hidden="">
       	<!-- 작성자 name값으로 파라메터로 전달하려고 hidden으로 숨김 -->
      </div>
      <div class="form-group">
        <label for="content">내용</label>
       <textarea class="form-control" id="content" name="content" rows="3" style="margin: 0px -3px 0px 0px; height: 248px; width: 567px;" placeholder="내용을 입력하세요"></textarea>
          
      </div>
      <button type="submit" class="btn btn-primary">작성</button>
    </form>
</div>

</body>
</html>


