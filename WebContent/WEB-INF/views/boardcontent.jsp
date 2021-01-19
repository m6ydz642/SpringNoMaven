<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
 <jsp:include page="menu/menubar2.jsp" />
 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${boardcontent.subject}" /></title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


</head>

<script type="text/javascript">
 function fn_contentView(board_id){

	var url = "${pageContext.request.contextPath}/boardcontent";

	url = url + "?board_id="+board_id;
	
	location.href = url;
	alert("댓글 함수 호출 글번호 : " + board_id);

}
 
 function fn_contentDelete(board_id){

		var url = "${pageContext.request.contextPath}/boarddelete"; // 글삭제로 이동

		url = url + "?board_id="+board_id;
		
		
		alert("글삭제 함수 호출 글번호 : " + board_id);
		
		var result = confirm("글을 레알 삭제하시겠어요?");
		// 삭제 확인 여부
		if(result){
		    alert("삭제되었습니다");
		    location.href = url;
		}else{
		    alert("취소되었습니다");
		return false;
		}
	}
 
 function fn_contentModify(board_id){

		var url = "${pageContext.request.contextPath}/boardmodify"; // 글 수정으로 이동

		url = url + "?board_id="+board_id;
		 location.href = url;
		
		alert("글수정 함수 호출 글번호 : " + board_id);
		
	}
 
 function ready() {
	alert("준비중입니다 언제할지는 몰라요 안할수도 있습니다.");
}

</script>
<body>
<<div class="container">
	<table class="table">
		<tbody>
		<thead>
			<tr>

				<th>제목 : <c:out value="${boardcontent.subject}" /></th>
				<th></th>
				<th></th>
				<th>글번호 : ${boardcontent.board_id}</th>
				<th></th>

			</tr>
		</thead>
		<tbody>
			<tr>
				<th>작성자 : <c:out value="${boardcontent.userid}" /></th>
				<!--양식 유지시키려고 남겨놈-->
				<td></td>
				<td></td>
				<td>조회수 : ${boardcontent.view_count}</td>
				<td></td>
			</tr>
			<tr>
				<td >날짜 : ${boardcontent.write_date[0]}</td>
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>

			</tr>


		</tbody>
		<td><c:out value="${boardcontent.content}" /></td>

	</table>

</div>	<!-- 부트스트랩 디자인 컨테이너 가운데 정렬 범위 -->	

	<c:set var="commentvalue" value="${comment}" />

<c:set var="boardcontent_num" value="${boardcontent}" />
	<!-- ---------------------------------------- -->

	<br>
		<!-- ---------------------------------------- -->
	<!-- <button>글쓰기</button>-->
<div class="container">
	 <c:if test="${logininfo.userid ne null}">
	<a class="btn btn-success" href="/boardwrite">글쓰기</a>
	<a class="btn btn-primary" onclick ="fn_contentModify(<c:out value="${boardcontent_num.board_id}"/>)">글수정</a>
	<a class="btn btn-danger" onclick="fn_contentDelete(<c:out value="${boardcontent_num.board_id}"/>)">글삭제</a>
	</c:if>
	<a class="btn btn-info" href="/board">글목록</a>
<br>
<br>

<hr size="3">

	<c:if test="${commentvalue eq '[]'}">
 		<font color="gray" size="5">댓글이 없습니다! 등록하지 마세요!</font> <br>
 		<hr size="3">
	</c:if>
	
</div>

	<c:forEach var="comment" items="${comment}">
<div class="container">	
	
  댓글번호: ${comment.comment_number } <br>
  작성자: ${comment.userid } <br>
  <c:if test="${comment.comment_content eq ''}" >
  <font color="red"><b>내용 : 사용자가 null을 입력하였습니다</b></font><br>
  </c:if>
  
   <c:if test="${comment.comment_content ne ''}">
  내용 : ${comment.comment_content } <br>
  </c:if>
  
<!-- <div style="text-align:right"> 글자 오른쪽 정렬-->

 작성날짜 : ${comment.write_date} </div><br>
<div class="container">	
		<hr size="3">
</div>

	</c:forEach>
</div>
	</div>
	<br>
	
	<form action="/commentwrite" method="post">
		<div class="form-group">
		
			<!--
    <label for="comment">작성자:</label>
 <textarea class="form-control" rows="1" id="comment" style="margin: 0px 388px 0px 0px; width: 111px; height: 31px;"></textarea>-->
			
		
<div class="container">
			<tr>
 <c:if test="${logininfo.userid ne null}">
				<td><b>작성자 : </b> <br>
				<b>&nbsp;${logininfo.userid}</b></td>
				<input name="comment_userid" value="<c:out value="${logininfo.userid}"/>" readonly="readonly" hidden="">
			</tr>

			<br>
			<br> <label for="comment">댓글 내용:</label>
			<textarea class="form-control" rows="5" id="comment" name="commentcontent"
				style="margin: 0px 835px 0px 0px; width: 660px; height: 121px;"></textarea>
	
		<input name ="board_id" value ="<c:out value="${boardcontent_num.board_id}"/>" readonly="readonly" hidden="">
		<!-- <br> ↑↑ 조만간 hidden으로 숨길꺼임  <br><br> -->
		<!-- name값에 글번호 옮겨서 board_id=?해서 값 쓸용도 -->
		
		<button type="submit" class="btn btn-primary" name="comment_board_id"
		onclick="fn_contentView(<c:out value="${boardcontent_num.board_id}"/>)">댓글 작성</button>
</c:if>
		
		<c:if test="${logininfo.userid eq null}">
		
				<font color = "blue">로그인 후 댓글을 작성하실수 있습니다. <br> 회원가입은 지원하지 않습니다 ^^</font>
		</c:if>
		
	<hr>
	</div>		<!-- 부트스트랩 디자인 컨테이너 가운데 정렬 범위 -->	
</div>	<!-- 부트스트랩 디자인 컨테이너 가운데 정렬 범위 -->	
	</form>





	<div class="text-center">
		<ul class="pagination">

		</ul>
	</div>

</body>

</html>
