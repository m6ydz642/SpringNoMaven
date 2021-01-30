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
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

</head>

<script type="text/javascript">



 function fn_contentView(board_id){

	var url = "${pageContext.request.contextPath}/boardcontent";

	url = url + "?board_id="+board_id;
	
	location.href = url;
	// alert("댓글 함수 호출 글번호 : " + board_id);

}
 
 function fn_contentDelete(board_id){

		var url = "${pageContext.request.contextPath}/boarddelete"; // 글삭제로 이동

		url = url + "?board_id="+board_id;
		
		
		// alert("글삭제 함수 호출 글번호 : " + board_id);
		
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
		
		// alert("글수정 함수 호출 글번호 : " + board_id);
		
	}
 
 function ready() {
	alert("준비중입니다 언제할지는 몰라요 안할수도 있습니다.");
}
 
 function commentmodifytest (comment_number) { // 댓글 수정하기 완료 호출함수

	 console.log("ajax 댓글 수정 전달 글 번호 : " + $('#board_id').val()); // 필요한것도 아니고 안되서 안씀
	 console.log("ajax 요청 전달받은 댓글내용 : " + $('#testarea').val() );
 	console.log("ajax 전달받은 comment_number : " + comment_number);
 	var testarea = $('#testarea').val(); 
	  $.ajax({
	 		url : "/commentedit",
	 		
	 		type : "post", 
	 		// dataType : "json", // list로 하려면 json이거 빼야됨 ㅅㅂ 계속 오류남 ㅋㅋㅋㅋㅋ

	 		data : { 'comment_number' : comment_number , 'testarea' : testarea},
	 		 async: true,
	 		success : function (data, textStatus){
	 			alert("댓글을 수정하였습니다");
	 			console.log(data);
	 			$('.comment-edit-btn').focus();
	 			window.location.reload(); // 태그 다시 추가하기 귀찮아서 그냥 새로고침으로 ^^ ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
	 			document.location.reload(true);// 스크롤 유지
	 			
	 			}, error: function (e) {
			console.log("댓글 오류발생!!!!!!! : " + e.value);
			alert("댓글수정 ajax 오류발생 ");
		}
		
	});  
	
 }
 

 function commentmodify(comment_number, board_id) {
	
	console.log("댓글수정 번호 번호 : " + comment_number + "수정요청 글번호 : " + board_id);
	// alert("댓글수정 번호 번호 : " + comment_number + "수정요청 글번호 : " + board_id);
	

			var tag ="";

					
// $("#commentarea").remove(); // 기존에 작성되어 있는 댓글
$("#commentmodifybuttonarea").remove(); // 기존 댓글작성 버튼이랑 input태그를 삭제함
//  tag += '<textarea name=testarea id=testarea style="border-bottom-width: 0px; border-right-width: 0px; border-left-width: 0px; padding-left: 0px; width: 430.967px; margin-top: 0px; margin-bottom: 0px; height: 92px;">' + ' </textarea>'
tag += '<div class="container">'
tag += '<p id="commentmodifybuttonarea">'
 tag += '<textarea class="form-control"  rows="5"  name=testarea id=testarea  style="margin: 0px 835px 0px 0px; width: 660px; height: 121px  ">' + ' </textarea>'
 
// tag += '<button type="submit" class="btn btn-primary" onclick="commentmodifytest(' + comment_number + '); "> ' + '댓글수정하기' + '</button>'
tag += '<button type="submit" class="btn btn-success" onclick="commentmodifytest(' + comment_number + '); "> ' + '댓글수정하기' + '</button>'
tag += '</p>'
// $("#commentarea").append(tag);
tag += '</div>'
$("#newcommentarea").append(tag); // input부분에 버튼으로 대체
		
var content = $('#testarea').val();
			
 

	
// 	<!-- <textarea style="border-bottom-width: 0px; border-right-width: 0px; border-left-width: 0px; padding-left: 0px;  -->
// 		<!-- width: 430.967px; margin-top: 0px; margin-bottom: 0px; height: 92px;"> -->
// 		<!-- ㅇㅇ  -->
// 		<!-- 여기다 댓글 수정 칸 추가할거임 -->
// 		<!-- 	</textarea> -->

}
 
 
 
 
 /*********************************************************************/
 // 댓글삭제
 
 function commentdeletecomplete(comment_number, board_id) {
		
		console.log("댓글삭제요청 댓글번호 : " + comment_number);
		console.log("댓글삭제요청 글번호 : " + board_id);
		
		  $.ajax({
		 		url : "/commentdelete",
		 		
		 		type : "post", 
		 		// dataType : "json", // list로 하려면 json이거 빼야됨 ㅅㅂ 계속 오류남 ㅋㅋㅋㅋㅋ

		 		data : { comment_number: comment_number},
		 		 async: true,
		 		success : function (data, textStatus){
		 			
		 		alert("댓글이 삭제되었습니다");
		 			
		 		
		 			console.log(data);
		
		 			window.location.reload(); // 태그 다시 추가하기 귀찮아서 그냥 새로고침으로 ^^ ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
		 			document.location.reload(true);// 스크롤 유지
		 			
		 			}, error: function (e) {
				console.log("댓글삭제 오류발생!!!!!!! : " + e.value);
				alert("댓글삭제 ajax 오류발생 ");
			}
			
		});  
	var tag ="";

	}

 /*********************************************************************/
 function commentdelete(comment_number, board_id) {

		var result = confirm("댓글을 삭제하시겠어요?");
		// 삭제 확인 여부
		if(result){
		    alert("댓글이 삭제되었습니다");
		    commentdeletecomplete(comment_number, board_id);
		}else{
		    alert("댓글삭제가 취소되었습니다");
		return false;
		}
	}
 
</script>
<body>
<div class="container">
	<table class="table">
		<tbody>
		<thead>
			<tr>

				<th>제목 : <c:out value="${boardcontent.subject}" /></th>
				<th></th>
				<th></th>
				<th id="board_id">글번호 : ${boardcontent.board_id}</th>
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

	
	</table>
	${boardcontent.content}
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
	</c:if> <!-- 유저전용 -->
	
	<c:if test="${sessionScope.loginid eq boardcontent.userid }"> 
	<!-- 본인글만 삭제, 수정 나오게, 세션이랑 DB userid랑 비교 -->
	
	<a class="btn btn-primary" onclick ="fn_contentModify(<c:out value="${boardcontent_num.board_id}"/>)">글수정</a>
	<a class="btn btn-danger" onclick="fn_contentDelete(<c:out value="${boardcontent_num.board_id}"/>)">글삭제</a>
	
	
	</c:if>
	<a class="btn btn-info" href="javascript:window.history.back();">글목록</a>
<br>
<br>

<hr size="3">

	<c:if test="${commentvalue eq '[]'}">
 		<font color="gray" size="5">댓글이 없습니다!</font> <br>
 		<hr size="3">
	</c:if>
	
</div>
	<p id="commentmodifyarea">
<!-- <textarea style="border-bottom-width: 0px; border-right-width: 0px; border-left-width: 0px; padding-left: 0px;  -->
<!--  width: 430.967px; margin-top: 0px; margin-bottom: 0px; height: 92px;">  -->
<!-- ㅇㅇ  -->
<!-- 여기다 댓글 수정 칸 추가할거임 -->
<!-- 	</textarea> -->

	<c:forEach var="comment" items="${comment}">
	<div class="container" id="comment_test">	


	<!-- 프로필 사진 -->
		<img alt="Profile" src="../../image/profile.png" width="50px" height="50px" align="left">
		<p id=""> 댓글번호: ${comment.comment_number } <br></p>
		 작성자: <b>${comment.userid }</b> <font color="gray" size="1.5">(${comment.comment_date[0]})</font>
		<c:if test="${sessionScope.loginid eq comment.userid  && comment.delete eq 'N'}"> 
		<!-- 본인글만 댓글삭제, 댓글수정 나오게, 삭제된 댓글이 아닌 글만 -->
		 <a href="#testarea" class="comment-edit-btn" onclick="commentmodify(${comment.comment_number}, ${comment.board_id })">수정</a>
		 <a href="#" class="comment-edit-btn" onclick="commentdelete(${comment.comment_number}, ${comment.board_id })">삭제</a>	
		 <br>	
		 </c:if>
				<c:if test="${comment.comment_content eq ''}">
					<font color="red"><b>내용 : 사용자가 null을 입력하였습니다</b></font>
					<br>
				</c:if>

				 <c:if test="${comment.delete eq 'Y'}">
				<br>
					<font color="orange"><b>삭제된 댓글입니다</b></font>
					<br>
				</c:if> 


				<c:if test="${comment.comment_content ne '' && comment.delete eq 'N'}">
						<br>
					<p id="commentarea" style="text-indent:0em;">${comment.comment_content }
						
					</p>

				</c:if>


			</p>
	

  
<!-- <div style="text-align:right"> 글자 오른쪽 정렬 -->

</div>
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
	 <p id="commentmodifybuttonarea"> <!-- 자바스크립트 태그 교체 땜시 바꿈 -->
			<textarea class="form-control" rows="5" id="comment" name="commentcontent" id="commentcontent"
				style="margin: 0px 835px 0px 0px; width: 660px; height: 121px;"></textarea>
	
		<input name ="board_id" value ="<c:out value="${boardcontent_num.board_id}"/>" readonly="readonly" hidden="" id="boardid">
		<!-- <br> ↑↑ 조만간 hidden으로 숨길꺼임  <br><br> -->
		<!-- name값에 글번호 옮겨서 board_id=?해서 값 쓸용도 -->
			 
		
		<button type="submit" class="btn btn-primary" name="comment_board_id" id="commentwrite_button"
		onclick="fn_contentView(<c:out value="${boardcontent_num.board_id}"/>)">댓글 작성</button>
		
		</p>
		
		<p id="newcommentarea">
		
		</p>
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
