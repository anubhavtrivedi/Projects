<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@page import="com.codester.beans.*"%>
   <%@page import="com.codster.dao.*"%>
      <%@page import="Service.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="Home.css" rel="stylesheet" />
     
    <link href="boot3/css/bootstrap.min.css" rel="stylesheet" />
    
  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Home</title>
</head>
<body>

<div id="a1">
        <div class="navbar navbar-default  navbar-fixed-top">
        <button class="btn navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="glyphicon glyphicon-menu-hamburger"></span></button>
            <div class=" visible-xs ">
                             <li class="nav" >  <a id= "icon" href="#">BOOTSTRAP</a></li>
            </div>
           
            <div class="navbar-collapse collapse">
  



           
           <li class="nav"><h2 align="Center"><u>JAVA MCQ Test</u></h2></li>   
          
           <a href="logout" align="right">Logout</a>
          
          
             </div>
            </div>
<br><br><br>
<form name="test" method="post" action="Evaluate">
<div class='container'>



<%
Question[] ques= CodesterDAO.getQuestionsJava();
int i=1;
for(Question q:ques){
%>


<h3><%=i +". "+ q.getQuestion()%></h3>
<div class='row'>
<div class='col-md-12'>


 <input type="radio" name="<%=i+"op1"%>" >A. <%=q.getOption1() %><br>

 <input type="radio" name="<%=i+"op2"%>" >B. <%=q.getOption2() %><br>

 <input type="radio" name="<%=i+"op3"%>"  >C. <%=q.getOption3() %><br>
  
   <input type="radio" name="<%=i+"op4"%>"  >D. <%=q.getOption4() %>

  </div>
</div>
<%i++;} %>

<input type="submit" value="Submit"/>

</div>

</form>


</body>
</html>






















