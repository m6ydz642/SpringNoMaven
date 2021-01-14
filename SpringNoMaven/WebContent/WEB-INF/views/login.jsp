<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
</head>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<body>

  <!-- 로긴폼 -->

 <div class="container">

  <div class="col-lg-4"></div>

  <div class="col-lg-4">

  <!-- 점보트론 -->

   <div class="jumbotron" style="padding-top: 20px;">

   <!-- 로그인 정보를 숨기면서 전송post -->

   <form method="post" action="loginAction.jsp">

    <h3 style="text-align: center;"> 로그인화면 </h3>

    <div class="form-group">

     <input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20">

    </div>

      
    <div class="form-group">

     <input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">

    </div>

    <input type="submit" class="btn btn-primary form-control" value="로그인">

   </form>

  </div>

 </div>

</div>
 <!-- 애니매이션 담당 JQUERY -->

</body>
</html>


