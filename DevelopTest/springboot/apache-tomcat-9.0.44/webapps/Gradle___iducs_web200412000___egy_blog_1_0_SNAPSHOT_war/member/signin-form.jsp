<%--
  Created by IntelliJ IDEA.
  User: egyou@office.induk.ac.kr
  Date: 2020-10-14
  Time: 오전 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <h1>Sign In</h1>
                    <span class="subheading">Input Your ID and Password</span>
                </div>
            </div>
        </div>
    </div>
</header>
<%
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie tempCookie : cookies){
            if(tempCookie.getName().equals("email")){
                request.setAttribute("cookie_id", tempCookie.getValue());
            }
        }
    }
%>
<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <form action="./signin.do" method="post" name="singinForm" id="singinForm" novalidate>
    <c:choose>
    <c:when test="${cookie == null}">
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>Email</label>
                        <input type="text" class="form-control" placeholder="Email" name="email" required data-validation-required-message="Please enter your id.">
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
    </c:when>
    <c:otherwise>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>ID</label>
                        <input type="text" class="form-control" placeholder="Email" name="email" value="${cookie_id}" required data-validation-required-message="Please enter your id.">
                        <p class="help-block text-danger"></p>
                    </div>
                </div>

    </c:otherwise>
    </c:choose>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                    <label>PW</label>
                    <input type="password" class="form-control" placeholder="PW" name="pw" required data-validation-required-message="Please enter your pw.">
                    <p class="help-block text-danger"></p>
                </div>
                </div>
                <div class="control-group">
                    <div class="form-group controls">
                    <label>Remember ID</label>
                    <input type="checkbox" name="checked" value="yes">
                    </div>
                </div>
                <br>
                <div id="success"></div>
                <button type="submit" class="btn btn-primary" id="sendMessageButton">Sign In</button>
            </form>
        </div>
    </div>
</div>
<hr>

<!-- Footer -->
<%@ include file="../main/index-footer.jsp" %>

</body>

</html>

