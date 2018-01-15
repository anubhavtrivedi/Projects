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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	java.sql.Connection con=null;
	   PreparedStatement ps=null;
	
	public void init() throws ServletException {

		   try {
			   
				con=(Connection) getServletContext().getAttribute("connection");

				   ps = con.prepareStatement("insert into signup values(?,?,?,?,?)");}catch (SQLException e) {
						e.printStackTrace();
					}
	}
	   @Override
	public void destroy() {
		try {
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		   ResultSet rs=null;
		   
				String name=request.getParameter("name");
				String username=request.getParameter("uname");
				String email=request.getParameter("eMail");
				String password=request.getParameter("pass1");
				String mob=request.getParameter("mobNo");
			
			
				
				try {
					ps.clearParameters();
					ps.setString(1, name);
					ps.setString(2, username);
					ps.setString(3, password);
					ps.setString(4, email);
					ps.setString(5, mob);
					
					
					int i=ps.executeUpdate() ;
					PrintWriter out=response.getWriter();
				
					if(i>0) {
					
						RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
						//out.println(");
						request.setAttribute("message123", "Your account successfully created");
						rd.include(request,response);
					}else
					{
						out.println("there are some technical problem while creating your account.\n Sorry for your inconvenience");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					try {
						if(rs!=null)
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

}
