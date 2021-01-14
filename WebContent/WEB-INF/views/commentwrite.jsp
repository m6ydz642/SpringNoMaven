<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>댓글 작성</title>
</head>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<body>
 <form action="/commentwrite" method="post">
<div class="form-group">
   <!--
    <label for="comment">작성자:</label>
 <textarea class="form-control" rows="1" id="comment" style="margin: 0px 388px 0px 0px; width: 111px; height: 31px;"></textarea>-->
    <tr>
                <td><b>작성자 : </b> 
                    <br><b>&nbsp;김준영</b></td>
    </tr>
    
    <br><br>
    <label for="comment">댓글 내용:</label>
  <textarea class="form-control" rows="5" id="comment" style="margin: 0px 835px 0px 0px; width: 660px; height: 121px;"></textarea>
</div>
     <button type="submit" class="btn btn-primary">댓글 작성</button>
    </form>
    

</body>
</html>


