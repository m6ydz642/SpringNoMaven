<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="UTF-8">
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    <head>
     <jsp:include page="../menu/menubar2.jsp" />
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>마이페이지</title>
        <!-- Bootstrap -->
        <link href="../plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- font awesome -->
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
        <!-- Custom style -->
        <link rel="stylesheet" href="../plugin/bootstrap/css/style.css" media="screen" title="no title" charset="utf-8">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../plugin/bootstrap/js/bootstrap.min.js"></script>
        <script src="../config/js/join.js"></script>
    </head>
    <body>
        <article class="container">
            <div class="page-header">
                <div class="col-md-6 col-md-offset-3">
                <h3>마이페이지 Form</h3>
                </div>
            </div>
            <div class="col-sm-6 col-md-offset-3">
                <form role="form">
                    <div class="form-group">
                        <label for="inputName">성명</label>
                        <input type="text" class="form-control" id="inputName" value="${logininfoname }">
                    </div>
                    <div class="form-group">
                        <label for="InputEmail">등급</label>
                        <input type="email" class="form-control" id="InputEmail" value="${logintest.auth}">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">비밀번호</label>
                        <input type="password" class="form-control" id="inputPassword" placeholder="비밀번호를 입력해주세요">
                    </div>
                    <div class="form-group">
                        <label for="inputPasswordCheck">비밀번호 확인</label>
                        <input type="password" class="form-control" id="inputPasswordCheck" placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요">
                    </div>
                    <div class="form-group">
                        <label for="inputMobile">휴대폰 번호</label>
                        <input type="tel" class="form-control" id="inputMobile" placeholder="휴대폰번호를 입력해 주세요">
                    </div>
                    <div class="form-group">
                        <label for="inputtelNO">사무실 번호</label>
                        <input type="tel" class="form-control" id="inputtelNO" placeholder="사무실번호를 입력해 주세요">
                    </div>

                    <div class="form-group">
                    <label>약관 동의</label>
                    <div data-toggle="buttons">
                    <label class="btn btn-primary active">
                    <span class="fa fa-check"></span>
                    <input id="agree" type="checkbox" autocomplete="off" checked>
                    </label>
                    <a href="#">이용약관</a>에 동의합니다.
                    </div>
                    </div>

                    <div class="form-group text-center">
                        <button type="submit" id="join-submit" class="btn btn-primary">
                            회원가입<i class="fa fa-check spaceLeft"></i>
                        </button>
                        <button type="submit" class="btn btn-warning">
                            가입취소<i class="fa fa-times spaceLeft"></i>
                        </button>
                    </div>
                </form>
            </div>

        </article>
    </body>
</html>
