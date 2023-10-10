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
                    <h1>Sing Up</h1>
                    <span class="subheading">Input Your Information</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <form action="./signup.do" method="post" class="needs-validation" novalidate="">
                <div class="mb-3">
                    <label for="email">Email <span class="text-muted"></span></label>
                    <input type="email" class="form-control" name="email" id="email" placeholder="you@example.com">
                </div>
                <div class="mb-3">
                    <label for="pw">Password</label>
                    <input type="password" class="form-control" name="pw" id="pw" placeholder="passwd" required="">
                </div>
                <div class="mb-3">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="name" required="">
                </div>
                <div class="mb-3">
                    <label for="phone">Phone</label>
                    <input type="text" class="form-control" name="phone" id="phone" placeholder="phone">
                </div>
                <div class="mb-3">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" name="address" id="address" placeholder="address">
                </div>
                <hr class="mb-4">
                <div class="text-center">
                    <button class="btn btn-primary" type="submit">Sign Up</button>
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

