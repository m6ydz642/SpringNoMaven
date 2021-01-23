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
	// alert("함수 호출 글번호 : " + board_id);

}


function searchBoard() {


	$.ajax({
	url : "/search",
	type : "get",
	// dataType : "json", // list로 하려면 json이거 빼야됨 ㅅㅂ 계속 오류남 ㅋㅋㅋㅋㅋ

	data : {search : $("#search").val() },
	 async: true,
	success : function (data, textStatus){

		
		 var obj = JSON.parse(data);
		 var tag = ""; // 초기값 안주면 계속 undefined앞에 같이 나옴
		 var nothing = "";
		console.log("데이터 길이 : " + obj.searchlist.length); // 길이

		
		if (obj.searchlist.length > 0) { // 데이터가 1개 이상이면
	
			 $('.table td').remove(); // 와 시발 이거땜에 존나 삽질함 ㅋㅋㅋㅋㅋ
		
		for (var i = 0; i <obj.searchlist.length; i++) {  
		console.log("board_id : " + obj.searchlist[i]["board_id"]); // 글번호
		console.log("userid : " + obj.searchlist[i]["userid"]); // 아이디
		console.log("subject : " + obj.searchlist[i]["subject"]); // 제목
		console.log("content : " + obj.searchlist[i]["content"]); // 내용

		 //   tag += '<table class= "table' + '">'
		 	tag += '<tr id="board' + '" +  onclick ="fn_contentView('+ obj.searchlist[i]["board_id"] + ')">' 
			tag += '<td class="board_id' + '">' + obj.searchlist[i]["board_id"] + '</td>' 
			tag += '<td>' + obj.searchlist[i]["subject"]  + '</td>'
			tag +=  '<td>' + obj.searchlist[i]["userid"] + '</td>'
			tag +=  '<td>' + obj.searchlist[i]["write_date"] + '</td>'
			tag += '<td>' +  obj.searchlist[i]["view_count"] + '</td>'
		 	tag += '</tr>'
		// 	tag += '</table>'
		/*  	var tag = "<tr id='board'>" + 
          		"<td class='board_id'>" + obj.searchlist[i]["board_id"] + "</td>" + 
          		"<td class='board_subject'>" + obj.searchlist[i]["subject"] + "</td>" + 
          		"<td class='board_userid'>" + obj.searchlist[i]["userid"] + "</td>" + 
          		"<td class='write_date'>" + obj.searchlist[i]["write_date"] + "</td>" + 
          		"<td class='view_count'>" + obj.searchlist[i]["view_count"] + "</td>" + 
      	  "</tr>"    */
      	

		$(".table").append(tag);
      	  console.log("태그 내용 : " + tag);
		console.log("태그 추가 성공");
}
		var test = "";
		 test += "검색명 : " + search.value ;	// 개소름
		// $("#testdiv").replaceWith(test);
			  

       
		 alert("ajax 검색내용 : " + search.value);
		}else if (search.value == ""){
			alert("검색할 내용을 입력하세요 ^^;;;;;;");
		}else{
			 $('.table td').remove();
			console.log("검색결과 null");
			 alert(search.value + " 에 대한 검색결과가 없습니다! ");
			
			 console.log("데이터 길이 : " + obj.searchlist.length);

			 	nothing += '<tr id="board">' 
				nothing += '<td>' + search.value + ' 에 대한 검색결과가 없어용' + '</td>'
				nothing += '</tr>'

				
				 $(".table").append(nothing);
			
	
		}
		
	}, error: function (e) {
		console.log("오류발생!!!!!!! : " + e.value);
		alert("ajax 오류발생 ");
	}
	
	});
	
}
/*************************************************************************/
 // 더보기 버튼
function morelist() {

	var morelist="";
	$.ajax({
	url : "/morelist",
	type : "get",
	// dataType : "json", // list로 하려면 json이거 빼야됨 ㅅㅂ 계속 오류남 ㅋㅋㅋㅋㅋ

	data : {morelist : morelist },
	 async: true,
	success : function (data, textStatus){

		alert('공사중 입니다 아직 페이징 카운트 안되어 있음');
		console.log('data내용 ' + data);
		 var obj = JSON.parse(data);
		 var tag = ""; // 초기값 안주면 계속 undefined앞에 같이 나옴
		 var nothing = "";
		console.log("더보기 데이터 길이 : " + obj.morelist.length); // 길이

	
		if (obj.morelist.length > 0) { // 데이터가 1개 이상이면
	
			// $('.table td').remove(); // 와 시발 이거땜에 존나 삽질함 ㅋㅋㅋㅋㅋ
		
		for (var i = 0; i <obj.morelist.length; i++) {  
		console.log("board_id : " + obj.morelist[i]["board_id"]); // 글번호
		console.log("userid : " + obj.morelist[i]["userid"]); // 아이디
		console.log("subject : " + obj.morelist[i]["subject"]); // 제목
		console.log("content : " + obj.morelist[i]["content"]); // 내용

		 //   tag += '<table class= "table' + '">'
		 	tag += '<tr id="board' + '" +  onclick ="fn_contentView('+ obj.morelist[i]["board_id"] + ')">' 
			tag += '<td class="board_id' + '">' + obj.morelist[i]["board_id"] + '</td>' 
			tag += '<td>' + obj.morelist[i]["subject"]  + '</td>'
			tag +=  '<td>' + obj.morelist[i]["userid"] + '</td>'
			tag +=  '<td>' + obj.morelist[i]["write_date"] + '</td>'
			tag += '<td>' +  obj.morelist[i]["view_count"] + '</td>'
		 	tag += '</tr>'

		$(".table").append(tag);
      	  console.log("ajax 태그 내용 : " + tag);
		console.log("ajax 태그 추가 성공");
}
	
		} 
		
	}, error: function (e) {
		console.log("더보기 오류발생!!!!!!! : " + e.value);
		alert("더보기 ajax 오류발생 ");
	}
	
	});
	
}
	/*************************************************************************/
	 function Enter_Check(){
	        // 엔터키의 코드는 13입니다.
	    if(event.keyCode == 13){
	    	searchBoard();  // 실행할 이벤트
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
			</div>
				</tr>
			
			</c:forEach>

<!-- ---------------------------------------------------------------- -->
			<c:if test="${contentlist eq '[]'}">
				<tr>
					<td style="text-align:center">게시물이 없습니다 !</td>
				</tr>

			</c:if>
<!-- ---------------------------------------------------------------- -->			
			
		</tbody>
           </table>
           <hr>
           <!-- 나중에 게시글 몇개 이상일때 나오는걸로 바꿔야 됨 -->
                <center>
        <!-- 글 10개? 정도 이상 되야 더보기 버튼 나타나는 걸로 if문 처리하기 (검색결과도 동일하게 limit를 걸던지 -->
			<input class="btn btn-secondary btn-sm" type="submit" value="더보기" onclick="morelist();"/> <br>
           </center>
<!-- ---------------------------------------------------------------- -->		
           
          <c:if test="${logininfo.userid ne null}">
          <!-- logininfo = 로그인 할때 들어간 세션값임  -->
                 <a class="btn btn-success" href="/boardwrite">글쓰기</a>
          </c:if>
          
          <div id="testdiv">
          <br>
          
          <font color="blue" size="3">인터넷 익스플로러로 접속시 디자인에서 오류가 발생합니다</font>
          <br><br>
          <font color="red" size="4"> 회원가입은 없습니다 <br> </font>
          ID : 1234 <br>
          PW : 1234 <br>
          
          <br>
          
          or <br><br>
          
          ID : user <br>
          PW : 1234 <br>
          
          <br>
        현재 게시판 select 결과 -> insert 전환 백업쿼리 생성 <br>
         <a href="http://60.100.123.75/backup">http://60.100.123.75/backup</a>
          
          
 
          </div>
          

        <!--   <form class="navbar-form navbar-right" onsubmit="searchBoard();">   -->
        <!-- 나중에 이거 고쳐야 됨  -->
        
			<div class="navbar-form navbar-right">
			 <div class="form-group" >
			  <input onsubmit="searchBoard();" type="text" id="search" 
			  name="search" class="form-control" placeholder="검색할 글제목을 입력해주세요"  onkeydown="Enter_Check();">
			  
			 <input class="btn btn-primary btn-sm" value="검색" type="submit" onclick="searchBoard();" >
		
			  <!-- <button   type="submit" class="btn btn-default">검색</button> -->
	   		</div>
	   		</div>
	   
	    <!-- </form>  -->

	   
	   
	   
</div>
        
        
   </div>        
    </body>
    
</html>
