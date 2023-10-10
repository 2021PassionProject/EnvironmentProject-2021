<%--
  Created by IntelliJ IDEA.
  User: egyou@office.induk.ac.kr
  Date: 2020-10-05
  Time: 오전 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
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
                    <h1>500</h1>
                    <span class="subheading">check message</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<h2 class="text-center">오류 : 서버 문제로 잠시 연결이 되지 않고 있습니다.</h2>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <div class="text-center">
                <a href="../main/index.jsp" class="btn btn-link"><h3>홈으로</h3></a>
            </div>
        </div>
    </div>
</div>
<hr>

<!-- Footer -->
<%@ include file="../main/index-footer.jsp" %>

</body>

</html>
