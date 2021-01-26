<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- jQuery -->

<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

 -->

 <%-- <script src="${pageContext.request.contextPath}/resources/common/js/jquery-3.3.1.min.js" ></script> --%>

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">



<!-- Bootstrap theme -->

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css"> --%>



<!-- common CSS -->

<%-- <link rel="stylesheet" href="<c:url value='/resources/common/css/common.css'/>" > --%>


<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
function searchFun() {


		$.ajax({
		url : '/board/search',
		type : 'get',
		data :{ search: $('#search').val() },
		success : function (data){
			$('#area').html(data);
			console.log("검색내용 : " + search.value);
			alert("이건 아마 안쓸듯 ㅋㅋ 검색내용 : " + search.value);
		}, error: function (e) {
			console.log("오류발생!!!!!!!");
			alert("ajax 오류발생");
		}
		
		});
		
}
</script>



<!--메뉴바 추가 부분-->

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">

  <a class="navbar-brand" href="/">홈페이지</a>
  

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">

    <span class="navbar-toggler-icon"></span>

  </button>



  <div class="collapse navbar-collapse" id="navbarsExample03">

    <ul class="navbar-nav mr-auto">

      <li class="nav-item active">

        <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>

      </li>

      <li class="nav-item">

        <a class="nav-link" href="/board">Board</a>

      </li>

		
		
      <li class="nav-item">

        <a class="nav-link disabled" href="#">Disabled</a>

      </li>

      <li class="nav-item dropdown">

        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>

        <div class="dropdown-menu" aria-labelledby="dropdown03">

          <a class="dropdown-item" href="#">Action</a>

          <a class="dropdown-item" href="#">Another action</a>

          <a class="dropdown-item" href="#">Something else here</a>


        </div>

      </li>
<c:if test="${loginid ne null}"> 
          
     	 <li class="nav-item">
				 <a class="nav-link">${loginid} 님 반갑습니다</a> 
  		</li> 
  		
  			 <li class="nav-item">
				 <a class="nav-link">유저등급 : ${logintest.auth} </a> 
  		</li> 
</c:if>


      
      <c:if test="${loginid eq null}"> 
        <li class="nav-item">

        <a class="nav-link" href="/login">Login</a>

      </li>
      </c:if>
      
       <c:if test="${loginid ne null}"> 
        <li class="nav-item">

        <a class="nav-link" href="/logout">Logout</a>
	
  	    </li>
		</c:if>
		    </ul>

 <!--   <form   class="form-inline my-2 my-md-0" onsubmit="searchFun();" > 

      <input class="form-control" type="text" placeholder="Search" name="search" id="search">

  </form>  -->
  
  

  </div>

</nav>


