<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

  <%@ include file="../main/index-head.jsp" %>
  <title>Post : Clean Blog - Start Bootstrap Theme</title>

</head>

<body>

  <!-- Navigation -->
  <%@ include file="../main/index-nav.jsp" %>
  
  <!-- Page Header -->
  <header class="masthead" style="background-image: url('../img/post-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="site-heading">
            <h1>Blog Datail</h1>
            <span class="subheading">블로그 상세 보기</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Post Content -->
  <article>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <h2 class="section-heading">${blog.title }</h2>
		  <p>${blog.content } : Space, the final frontier. These are the voyages of the Starship Enterprise.</p>	
          <a href="#">
            <img class="img-fluid" src="../files/${blog.filepath}" alt="">
          </a>
          <span class="caption text-muted">To go places and do things that have never been done</span>
          <p class="post-meta">Posted by
          <a href="#">${blog.blogger }</a>
          on ${blog.regDateTime }</p>
          <div class="clearfix text-center">
            <c:if test="${sessionScope.login.email ==blog.blogger}">
	         <a class="btn btn-primary" href="./update-form.do?id=${blog.id }">수정</a> &nbsp; &nbsp;
	         <a class="btn btn-primary" href="./delete.do?id=${blog.id }">삭제</a>
            </c:if>
	      </div>
        </div>
      </div>
      
    </div>
  </article>
        
  <hr>

  <!-- Footer -->
  <%@ include file="../main/index-footer.jsp" %>
  
</body>

</html>
