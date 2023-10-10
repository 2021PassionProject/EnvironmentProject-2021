<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Clean Blog - Start Bootstrap Theme</title>

    <jsp:include page="../main/index-head.jsp" />

</head>

<body>

<!-- Navigation -->
<%@ include file="../main/index-nav.jsp"%>

<!-- Page Header -->
<header class="masthead" style="background-image: url('../img/contact-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1>Unregister</h1>
                    <span class="subheading">회원 탈퇴</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <form action="./delete.do" method="post" class="needs-validation" novalidate="">
                <div class="mb-3">
                    <label for="email">Email <span class="text-muted"></span></label>
                    <input type="email" class="form-control" name="email" value="${sessionScope.login.email}" id="email" readOnly>
                </div>
                <div class="mb-3">
                    <label for="pw">Password</label>
                    <input type="password" class="form-control" name="pw" id="pw" placeholder="passwd" required="">
                </div>
                <hr class="mb-4">
                <div class="text-center">
                    <button class="btn btn-primary" type="submit">Unregister</button>
                    <button class="btn btn-primary" type="reset">Reset</button>
                </div>
            </form>
        </div>
    </div>
</div>

<hr>

<!-- Footer -->
<%@ include file="../main/index-footer.jsp" %>

</body>

</html>

