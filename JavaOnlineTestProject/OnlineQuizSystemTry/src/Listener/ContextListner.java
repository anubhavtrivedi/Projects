package Listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListner implements ServletContextListener{

	Connection con = null;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	
		try {
			if(con!=null)
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	
		
		ServletContext context = arg0.getServletContext();
		
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String username = context.getInitParameter("username");
		String password = context.getInitParameter("password");
		
		
		try {
			Class.forName(driver);
			con  = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Done");
			
			context.setAttribute("connection", con);
			System.out.println("contextInitalized");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
