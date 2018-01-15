<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mail</title>
</head>
<body>
<img src="file:///C:/Final%20WorkSpace%20Last/OnlineQuizSystemTry/src/images.jpg">
<form action="SendMail">  
<table>
<tr><td><b>To</b></td><td><input type="text" name="to"/></td> </tr>
<tr><td><b>Subject:</b></td><td><input type="text" name="subject"></tr> 
<tr><td><b>WriteText:</b></td><td><textarea rows="10" cols="70" name="msg"></textarea></td> </tr>
<tr><td><input type="submit" value="send"/><td></tr>  
</table>
</form> 
</body>
</html>