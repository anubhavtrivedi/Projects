<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="choselang.css" rel="stylesheet" />
     
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
       <ul class="nav navbar-nav navbar-left">
             <li class="nav active" >  <a id= "icon" href="#"><img src="../boot3/logo_small.png" ></a></li>
           <li class="nav">   <a href='Home.jsp'>HOME</a></li>   
           
           
          
            </ul>
            <ul class="nav navbar-nav navbar-right">

          <li class="nav">   <a href='about.jsp'>ABOUT</a></li>
       <li class="nav"> <a href='Login.jsp'>CHOOSE LANGUAGE</a></li>
        
        
           <li class="nav"> <a href="logout">Logout</a></li>
         

            </ul>
             </div>
            </div>

<br><br><br><br>





 <div class='container'>
<div class='row'>
<div class='col-md-6'>
<img src='java.jpg' class='img-responsive'/>
 <%  session=request.getSession(false);  
        if(session!=null)
        {  
        String name=(String)session.getAttribute("username");  
      String password=(String)session.getAttribute("password");
       
        %>
<a href='JAVA.jsp'><button class='btn btn-success'>JAVA</button></a>
</div>
<div class='col-md-6'>
<img src='C.jpg' class='img-responsive'/>
<a href='CPP.jsp'><button class='btn btn-success'>C++</button></a>
</div>
</div>
<br>
<div class='row'>
<div class='col-md-6'>
<img src='sss.jpeg' class='img-responsive'/>
<a href='SQL.jsp'><button class='btn btn-success'>STRUCTURED QUERY LANGUAGE</button></a>
</div>
<div class='col-md-6'>
<img src='html.jpg' class='img-responsive'/>
<a href='HTML.jsp'><button class='btn btn-success'>HTML/CSS</button></a>
</div>
</div>
</div>

<br>
</div>
<%} 

        else
        {
        	 request.getRequestDispatcher("Login.jsp").forward(request,response);
		request.setAttribute("loginfirst", "Invalid username/password");
	
        }


%>

<div class="jumbotron1">
    <div class="row">
    <div class="col-md-8">

        
            <p><br>
All content copyright @ anshika,adtiya,samarpit,susheel</p>

                </div>



</body>
</html>