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
            <h1>Write a Blog </h1>
            <span class="subheading">블로그에 글을 남기십시요</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <form action="create.do" method="post" enctype="multipart/form-data"
        	name="sentMessage" id="contactForm" novalidate>
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>Title</label>
              <input type="text" class="form-control" name="title" placeholder="Title" id="title" required data-validation-required-message="Please enter your name.">
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>Content</label>
              <textarea rows="5" class="form-control" name="content" placeholder="Content" id="content" required data-validation-required-message="Please enter a message."></textarea>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>File</label>
              <input type="file" class="form-control" name="filepath" placeholder="File Path" id="filepath" required data-validation-required-message="Please enter your name.">
              <p class="help-block text-danger"></p>
            </div>
          </div>          
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>Blogger</label>
              <input type="text" class="form-control" name="blogger" value="${sessionScope.login.email}" placeholder="Blogger" id="blogger" readonly>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <br>
                    <div class="form-group">
            <div class="input-group date" id="example1">
				<input type="text" name="regDateTime" class="form-control">
				<span class="input-group-addon">
				<span class="glyphicon glyphicon-calendar"></span>
				</span>
			  </div>
          </div>
          <div id="success"></div>

          <div class="form-group">
            <button type="submit" class="btn btn-primary" id="sendMessageButton">Write</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <hr>

  <!-- Footer -->
  <%@ include file="../main/index-footer.jsp" %>
	
    <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Contact Form JavaScript -->
  <script src="js/jqBootstrapValidation.js"></script>
  <script src="js/contact_me.js"></script>

  <!-- Custom scripts for this template -->
  <script src="js/clean-blog.min.js"></script>

	<script src="https://code.jquery.com/jquery.min.js"></script>
	
	<script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment.min.js"></script>
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  
	
	<script type="text/javascript">
		$(function () {
	       $('#example1').datetimepicker( {
	    		   format: 'YYYY-MM-DD HH:mm:ss'
	       });
		});
	</script>

</body>

</html>
