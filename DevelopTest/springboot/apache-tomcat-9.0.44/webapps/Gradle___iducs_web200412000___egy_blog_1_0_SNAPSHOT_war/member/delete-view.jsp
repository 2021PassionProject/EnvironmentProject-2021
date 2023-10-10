<%--
  Created by IntelliJ IDEA.
  User: egyou@office.induk.ac.kr
  Date: 2020-10-05
  Time: 오전 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>error page</title>

    <jsp:include page="../main/index-head.jsp" />

</head>

<body>

<!-- Navigation -->
<jsp:include page="../main/index-nav.jsp" />

<!-- Page Header -->
<header class="masthead" style="background-image: url('../img/about-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1>회원 탈퇴 성공</h1>
                    <span class="subheading">confirm message</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto text-center">
            <h2 class="text-center">메시지 : ${message} </h2>
            <a href="../main/index.jsp" class="btn btn-link"><h4>홈으로</h4></a>
        </div>
    </div>
</div>
<hr>

<!-- Footer -->
<%@ include file="../main/index-footer.jsp" %>

</body>

</html>
