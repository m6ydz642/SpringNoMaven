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
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
function fn_contentView(board_id){

	var url = "${pageContext.request.contextPath}/boardcontent";

	url = url + "?board_id="+board_id;
	
	location.href = url;
	alert("함수 호출 글번호 : " + board_id);

}


function searchBoard() {


	$.ajax({
	url : "/search",
	type : "get",
	 dataType : "json",

	data : {search : $("#search").val() },
	
	success : function (data, textStatus){
		 var obj = JSON.parse(data);
		console.log("오브젝트 내용 : " + obj);
		// $('#area').html(data);
		console.log("검색내용 : " + search.value);
	/* 	$('#board .board_id').replaceWith('<td>1</td>');
		$('#board .board_subject').replaceWith('</td>');
		$('#board .board_userid').replaceWith('<td>사람</td>');
		$('#board .board_write_date').replaceWith('<td>2012-12-12</td>');
		$('#board .board_view_count').replaceWith('<td>123</td>'); */
		
		 test += " ajax 바뀔곳 : " + search.value + " : ";	// 개소름
		 $("#testdiv").replaceWith(test);
			  
		/* 	var changeurl = "${pageContext.request.contextPath}/board";
			changeurl = changeurl + "?search="+search.value;
			location.href = changeurl; */
       
		 alert("ajax 검색내용 : " + search.value);
			console.log("사이즈 : " + data);
		
	}, error: function (e) {
		console.log("오류발생!!!!!!! : " + e.value);
		alert("ajax 오류발생 ");
	}
	
	});
	
}

function press(f){ 
	if(f.keyCode == 13)
	{ //javascript에서는 13이 enter키를 의미함 
		formname.submit(); //formname에 사용자가 지정한 form의 name입력 
	
	} 
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
			
	<tr id="board" style="cursor:pointer;" onclick="fn_contentView(<c:out value="${item.board_id}"/>)"> 
					<td class="board_id"><c:out value="${item.board_id}" /></td>
	     			<td class="board_subject" ><c:out value="${item.subject}" /></td>
					<td class="board_userid" ><c:out value="${item.userid}" /></td>
					<td class="board_write_date" ><c:out value="${item.write_date[0]}" /></td>
					<!--  split으로 잘라서 온거라 배열 형태로 들어가있음 ㅋㅋ 
					그래서 0번부터가 날짜고 1번은 시간 2번은 모르겠다 한공백당 한종류씩 떨어짐 -->

					<%--  <fmt:formatDate value="${item.write_date}" pattern="yyyy.MM.dd" />  --%>
					<td class="board_view_count">${item.view_count}</td>
		
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
          
          <div id="testdiv">
          여기에 객체 리스트 나올거 
          <c:forEach var="selist" items="${searchlistadd}">
          글번호 : ${selist.board_id} <br>
          제목 : ${selist.subject} <br>
          조회수 : ${selist.view_count} <br>
          
          </c:forEach>
          </div>
          
     <!--       
        <div class="text-center">
           <ul class="pagination">
     
    
           </ul>
        </div> -->
        
        <!--   <form class="navbar-form navbar-right" onsubmit="searchBoard();">   -->
        <!-- 나중에 이거 고쳐야 됨  -->
        
			<div class="navbar-form navbar-right">
			 <div class="form-group" >
			  <input onsubmit="searchBoard();" type="text" id="search" 
			  name="search" class="form-control" placeholder="검색어를 입력하세요.">
			  
			 <input type="submit" onclick="searchBoard();" >
		
			  <!-- <button   type="submit" class="btn btn-default">검색</button> -->
	   		</div>
	   		</div>
	   
	    <!-- </form>  -->

	   
	   
	   
</div>
        
        
   </div>        
    </body>
    
</html>
