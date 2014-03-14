<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="CKE" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Test CKEditor</title>
    <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
    <script type="text/javascript">  
	    window.onload = function(){  
	        CKEDITOR.replace('editor1');  
	    };  
	</script>
  </head>
  
  <body>
		<form action="" method="get">
			<p>
				<label for="editor1">Editor 1:</label>
				<textarea cols="80" id="editor1" name="editor1" rows="10"></textarea>
			</p>
			<p>
				<input type="submit" value="Submit" />
			</p>
		</form>
  </body>
</html>
