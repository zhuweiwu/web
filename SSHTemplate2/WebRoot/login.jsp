<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>Login Page</title>
  </head>
  
  <body>
   	<s:form action="Login" method="post">
   		<s:textfield name="username" label="username"/>
   		<s:password name="password" label="password"/>
   		<s:submit label="submit" />
   	</s:form>
  </body>
</html>
