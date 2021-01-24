<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>패치 내용</title>
<script language="JavaScript">
	function setCookie(name, value, expiredays) {
		var date = new Date();
		date.setDate(date.getDate() + expiredays);
		document.cookie = escape(name) + "=" + escape(value) + "; expires="
				+ date.toUTCString();
	}

	function closePopup() {
		if (document.getElementById("check").value) {
			setCookie("popupYN", "N", 1);
			self.close();
		}
	}
</script>
</head>
<body>
	
	<br />
	<hr>
	<ul>
		--- 1월 25일 월요일 추가내용 입니다
		<li>ajax 게시글 검색 기능, 오류수정, 검색 후 엔터키 가능하게 수정</li>
		<li>게시글 더보기 기능 (페이징)</li>
		<li></li>
		
	</ul>
	<hr>
	<fontsize=3> <b>하루에 한번만 보기</b> </font>
	<input type="checkbox" id="check" onclick="closePopup();">
</body>
</html>


