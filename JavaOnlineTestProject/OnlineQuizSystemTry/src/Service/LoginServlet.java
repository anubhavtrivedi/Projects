package Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codester.beans.Question;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	java.sql.Connection con=null;
	   PreparedStatement stmt=null;
	   ResultSet rs=null;
	  
	   
	   
	   
	   @Override
		public void init() throws ServletException {

			try {
				
				con=(Connection) getServletContext().getAttribute("connection");

			   stmt = con.prepareStatement("select * from signup where uname=? and pass=?");
			 
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
			
		}
		
	   public void destroy() {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultSet rs = null;		
		try {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");	
			stmt.clearParameters();
			stmt.setString(1,username);
			stmt.setString(2,password);
		
			rs = stmt.executeQuery();
		
			
if(rs.next())
{
	HttpSession session = request.getSession();
	session.setAttribute("questions", new Vector<Question>());
	session.setAttribute("username", username);
	session.setAttribute("password", password);
	response.sendRedirect(response.encodeRedirectURL("ChoseLang.jsp"));
	}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			request.setAttribute("loginMessage", "Invalid username/password");
			rd.forward(request,response);			
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}
