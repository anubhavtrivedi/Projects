package Service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codester.beans.Question;
import com.codster.dao.CodesterDAO;


public class Evaluate extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Evaluate() {
        super();      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op;
		PrintWriter out=response.getWriter();
		Question[] ques=CodesterDAO.questionCheck;
		int[][] ans=new int[11][5];
		
		for(int i=1;i<11;i++){
			for(int j=1;j<5;j++){
				op=i+"op"+j;
				if("on".equals(request.getParameter(op))){
				ans[i][j]=1;
			
				}
				else
					ans[i][j]=0;
			}
		}
		Question q;
		int score=0;
		String[] status=new String[10];
		for(int i=0;i<10;i++) {
			q=ques[i];
			if(ans[i+1][q.getCorrectAns()]==1)
			{score++;
				status[i]="CORRECT";
			}
			else {
				status[i]="INCORRECT";
		}
		}
	
		
		String question="";
		String userAns="";
		String correctAns="";
		
		out.println("<html>");
		out.println("<head>");
		out.println("    <link href=\"boot3/css/bootstrap.min.css\" rel=\"stylesheet\" />");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
		out.println("<title>Insert title here</title>");
		out.println("<style>");
		out.println("		table {");
		out.println("	    font-family: arial, sans-serif;");
		out.println("  border-collapse: collapse;");
		out.println(" width: 100%;");
		out.println("}");
		out.println("td, th {");
		out.println("border: 1px solid #dddddd;");
		out.println("   text-align: left;");
		out.println("padding: 8px;");
		out.println("}");
		out.println("		tr:nth-child(even) {");
		out.println("  background-color: #dddddd;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<br>");
		
		out.println( "YOUR SCORE : "+score+"/10");
		out.println("<br>");
		out.println("<br>");
		out.println("<table>");
		out.println(" <tr>");
		out.println("<th>Question</th>");
		out.println("<th>User Ans</th>");
		out.println("<th>Correct Ans</th>");
		out.println("  <th>Status</th>");
		out.println(" </tr>");
		out.println("  <tr>");
		int ansInt,userInt=0;
	for(int i=0;i<10;i++) {
		userInt=0;
		question="Ques "+(i+1)+". "+ques[i].getQuestion();
		ansInt=ques[i].getCorrectAns();
		switch(ansInt) {
		case 1:

			correctAns=ques[i].getOption1();
			break;
		case 2:correctAns=ques[i].getOption2();
		break;
		case 3:correctAns=ques[i].getOption3();
		break;
		case 4:correctAns=ques[i].getOption4();
		break;
		}
		if(ans[i+1][1]==1) 
			userInt=1;
		if(ans[i+1][2]==1) 
			userInt=2;
		if(ans[i+1][3]==1) 
			userInt=3;
		if(ans[i+1][4]==1) 
			userInt=4;
		
		if(userInt==0)
		{userAns="NOT ATTEMPTED";}
		else {
		switch(userInt) {
		case 1:
			userAns=ques[i].getOption1();
			break;
		case 2:userAns=ques[i].getOption2();
		break;
		case 3:userAns=ques[i].getOption3();
		break;
		case 4:userAns=ques[i].getOption4();
		break;
		}
	}
		out.println("  <td>"+question+"</td>");
		out.println(" <td>"+userAns+"</td>");
		out.println("<td>"+correctAns+"</td>");
		out.println("<td>"+status[i]+"</td>");
		out.println("</tr>");
	}
		out.println(" </table>");
		out.println("<a href='Home.jsp'><button class='btn btn-success'>Home</button></a>");
		out.println("<a href='logout'><button class='btn btn-success'>Logout</button></a>");
		out.println("<a href='MailIndex.jsp'><button class='btn btn-success'>Mail Report</button></a>");
		out.println("</body>");
		out.println("</html>");
		
		
	}

}
