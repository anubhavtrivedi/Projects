package Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codester.beans.*;
import com.codster.dao.CodesterDAO;;


public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 PreparedStatement stmt = null;
	  Connection con = null;
	
	  
	  @Override
		public void init() throws ServletException {

			
			try {
				con=(Connection) getServletContext().getAttribute("connection");	
				stmt = con.prepareStatement("Select * from java");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	  }
			   		
	  
	  @Override
	public void destroy() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				Question[] ques= CodesterDAO.getQuestionsCpp();
		   		Vector<Question> listofquestions = new Vector<Question>();
		   		for(Question q:ques) {
					System.out.println(q.getOption1());
					listofquestions.add(q);
				}
				
		   		request.setAttribute("listofquestions", listofquestions);
		   		request.getRequestDispatcher("Test1.jsp").forward(request, response);
		   		
		   		
		
}
}