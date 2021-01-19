<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
 <jsp:include page="menu/menubar2.jsp" />


    <meta charset="UTF-8"> 
    <title>게시판</title>
    
    <!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    
</head>
<script type="text/javascript">
function fn_contentView(board_id){

	var url = "${pageContext.request.contextPath}/boardcontent";

	url = url + "?board_id="+board_id;
	
	location.href = url;
	alert("함수 호출 글번호 : " + board_id);

}



</script>
       <body>
       <div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            
            <th style="padding-left: 6px; ">번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>날짜</th>
            <th style="padding-left: -1px; ">조회수</th>
                
        </tr>
        </thead>   
        <tbody>


 <!-- ---------------------------------------------------------------- -->
			<c:forEach var="item" items="${contentlist}">
		
			<%-- <a href="#" onClick="fn_contentView(<c:out value="${item.board_id}"/>)"> --%>
			
	<tr style="cursor:pointer;" onclick="fn_contentView(<c:out value="${item.board_id}"/>)"> 
					<td><c:out value="${item.board_id}" /></td>
	     			<td><c:out value="${item.subject}" /></td>
					<td><c:out value="${item.userid}" /></td>
					<td><c:out value="${item.write_date[0]}" /></td>
					<!--  split으로 잘라서 온거라 배열 형태로 들어가있음 ㅋㅋ 
					그래서 0번부터가 날짜고 1번은 시간 2번은 모르겠다 한공백당 한종류씩 떨어짐 -->

					<%--  <fmt:formatDate value="${item.write_date}" pattern="yyyy.MM.dd" />  --%>
					<td>${item.view_count}</td>
		
				</tr>
		
			</c:forEach>
<!-- ---------------------------------------------------------------- -->
			<c:if test="${contentlist eq '[]'}">
				<tr>
					<td style="text-align:center">게시물이 없습니다 등록하지 마세요 ㅎㅎ</td>
				</tr>
<!-- ---------------------------------------------------------------- -->


			</c:if>
		</tbody>
           </table>
           <hr>
           
          <c:if test="${logininfo.userid ne null}">
          <!-- logininfo = 로그인 할때 들어간 세션값임  -->
          
           <a class="btn btn-default" href="/boardwrite">글쓰기</a>
        
          </c:if>
           
        <div class="text-center">
           <ul class="pagination">
     
    
           </ul>
        </div>
   </div>        
    </body>
    
</html>
