<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
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
    <table class="table table-hover">
        <thead>
        <tr>
            
            <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>날짜</th>
               <th>조회수</th>
                
        </tr>
        </thead>   
        <tbody>


			<c:forEach var="item" items="${contentlist}">
		
			<%-- <a href="#" onClick="fn_contentView(<c:out value="${item.board_id}"/>)"> --%>
			
			<tr style="cursor:pointer;" onclick="fn_contentView(<c:out value="${item.board_id}"/>)
			"> 
					<td><c:out value="${item.board_id}" /></td>
	     			<td><c:out value="${item.subject}" /></td>
					<td><c:out value="${item.username}" /></td>
					<td>날짜 아직안함</td>
					<td>조회수 아직안함</td>
				</tr>
		
			</c:forEach>



		</tbody>
           </table>
           <hr>
            <!-- <button>글쓰기</button>-->
           <a class="btn btn-default" href="/boardwrite">글쓰기</a>
           
        <div class="text-center">
           <ul class="pagination">
     
    
           </ul>
        </div>
           
    </body>
    
</html>
