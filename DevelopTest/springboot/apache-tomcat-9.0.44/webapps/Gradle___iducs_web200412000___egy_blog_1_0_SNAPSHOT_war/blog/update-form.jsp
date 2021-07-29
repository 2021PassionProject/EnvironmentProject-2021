<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

  <%@ include file="../main/index-head.jsp" %>
  <title>Blog Create : Clean Blog - Start Bootstrap Theme</title>
  
</head>

<body>

  <!-- Navigation -->
  <%@ include file="../main/index-nav.jsp" %>
  
  <!-- Page Header -->
  <header class="masthead" style="background-image: url('../img/contact-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="page-heading">
            <h1>Update a Blog </h1>
            <span class="subheading">블로그에 글을 수정하십시요</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <p>Want to get in touch? Fill out the form below to send me a message and I will get back to you as soon as possible!</p>
        <!-- Contact Form - Enter your email address on line 19 of the mail/contact_me.php file to make this form work. -->
        <!-- WARNING: Some web hosts do not allow emails to be sent through forms to common mail hosts like Gmail or Yahoo. It's recommended that you use a private domain email address! -->
        <!-- To use the contact form, your site must be on a live web host with PHP! The form will not work locally! -->


        <form action="update.do" method="post" enctype="multipart/form-data"
        	name="sentMessage" id="contactForm" novalidate>
        	<input type="hidden" name="id" value="${blog.id }" />
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>Title</label>
              <input type="text" class="form-control" name="title" value="${blog.title }" placeholder="Title" id="name" required data-validation-required-message="Please enter your name.">
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>Content</label>
              <textarea rows="5" class="form-control" name="content" placeholder="Content" id="content" required data-validation-required-message="Please enter a message.">
              ${blog.content }
              </textarea>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>File</label>
              <input type="file" class="form-control" name="filepath" placeholder="File Path" id="filepath" required data-validation-required-message="Please enter your name.">
              <p class="help-block text-danger">${blog.filepath }</p>
            </div>
          </div>          
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>Blogger</label>
              <input type="text" class="form-control" name="blogger" value="${blog.blogger }" placeholder="Blogger" id="blogger" readonly>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <br>
          <div class="form-group">
            <div class="input-group date" id="example1">
				<input type="text" name="regDateTime" value="${blog.regDateTime }" class="form-control">
				<span class="input-group-addon">
				<span class="glyphicon glyphicon-calendar"></span>
				</span>
			  </div>
          </div>
          <div id="success"></div>

          <div class="form-group">
            <button type="submit" class="btn btn-primary" id="sendMessageButton">수정</button>
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
