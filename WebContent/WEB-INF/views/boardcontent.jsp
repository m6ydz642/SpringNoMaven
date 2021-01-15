<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
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
	<table class="table">
		<tbody>
		<thead>
			<tr>

				<th><c:out value="${boardcontent.subject}" /></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>

			</tr>
		</thead>
		<tbody>
			<tr>
				<th><c:out value="${boardcontent.username}" /></th>
				<!--양식 유지시키려고 남겨놈-->
				<td></td>
				<td></td>
				<td>조회수 아직 안함</td>
				<td></td>
			</tr>
			<tr>
				<td>날짜 : 아직 안함</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>

			</tr>


		</tbody>
		<td><c:out value="${boardcontent.content}" /></td>

	</table>
	<hr>
	댓글창은 일단 여기에다가
	<br> 내용 ↓
	<br> ----------------------------------
	<br>
	<c:set var="commentvalue" value="${comment}" />

<c:set var="boardcontent_num" value="${boardcontent}" />
	<!-- ---------------------------------------- -->
	<c:if test="${commentvalue eq '[]'}">
 댓글이 없습니다! 등록하지 마세요! <br>
	</c:if>
	<br>
		<!-- ---------------------------------------- -->
	<!-- <button>글쓰기</button>-->
	<a class="btn btn-success" href="/boardwrite">글쓰기</a>
	<a class="btn btn-primary" onclick ="fn_contentModify(<c:out value="${boardcontent_num.board_id}"/>)">글수정</a>
	<a class="btn btn-danger" onclick="fn_contentDelete(<c:out value="${boardcontent_num.board_id}"/>)">글삭제</a>
	<a class="btn btn-info" href="/board">글목록</a>
<br>
<br>
	<c:forEach var="comment" items="${comment}">


  댓글번호: ${comment.comment_number } <br>
  작성자: ${comment.username } <br>
  내용 : ${comment.comment_content } <br>
  작성자: ${comment.username } <br>
  ---------------------------------- <br>


	</c:forEach>
	<br>
	<form action="/commentwrite" method="post">
		<div class="form-group">
		
			<!--
    <label for="comment">작성자:</label>
 <textarea class="form-control" rows="1" id="comment" style="margin: 0px 388px 0px 0px; width: 111px; height: 31px;"></textarea>-->
			
			<br>
			<hr>
			<tr>
				<td><b>작성자 : </b> <br>
				<b>&nbsp;DB이름으로 수정해야 함</b></td>
			</tr>

			<br>
			<br> <label for="comment">댓글 내용:</label>
			<textarea class="form-control" rows="5" id="comment" name="commentcontent"
				style="margin: 0px 835px 0px 0px; width: 660px; height: 121px;"></textarea>
		</div>
		<input name ="board_id" value ="<c:out value="${boardcontent_num.board_id}"/>" >
		<br> ↑↑ 조만간 hidden으로 숨길꺼임  <br><br>
		<!-- name값에 글번호 옮겨서 board_id=?해서 값 쓸용도 -->
		<button type="submit" class="btn btn-primary" name="comment_board_id"
		onclick="fn_contentView(<c:out value="${boardcontent_num.board_id}"/>)">댓글 작성</button>

	</form>



	<hr>


	<div class="text-center">
		<ul class="pagination">

		</ul>
	</div>

</body>

</html>
