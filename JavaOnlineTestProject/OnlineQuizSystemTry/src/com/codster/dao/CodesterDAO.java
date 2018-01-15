package com.codster.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.codester.beans.Question;

public class CodesterDAO {
	static private String userName="sam";
	static private String pass="root";
	static private String url="jdbc:oracle:thin:@localhost:1521:XE";
	static private String driverClass="oracle.jdbc.driver.OracleDriver";
	static final int godNumberCPP=30;
	static final int godNumberHTML=30;
	static final int godNumberJAVA=30;
	static final int godNumberSQL=30;
	static public Question[] questionCheck;

	public static  Question[] getQuestionsCpp(){
		int i=0,x=0;
		int[] status=new int[50];
		try {
		Class.forName(driverClass);
		Connection con=DriverManager.getConnection(url,userName,pass);
		PreparedStatement pst= con.prepareStatement("Select * from cpp");
		
//	HttpSession session=request.getSession(false);
//		if(session == null)
//		{
//			response.sendRedirect("Login.jsp");
//			return;}
		
		
		
		ResultSet rs=pst.executeQuery();
		ArrayList<Question> quesList=new ArrayList<>();
		Question question;
		while(rs.next()) {
			question=new Question(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),Integer.parseInt(rs.getString(7)));
			quesList.add(question);				
		}
		Question[] questions=new Question[10];
		x=1;
		while(i<10) {
			double r=Math.random();
			r=r*100;
			 x=(int)r;
			 x=x%godNumberCPP;
			if(status[x]!=1) {
				status[x]=1;
				questions[i]=quesList.get(x);
				i++;
			}
		}
		questionCheck=questions;
			return questions;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static  Question[] getQuestionsHTML(){
		int i=0,x=0;
		int[] status=new int[50];
		try {
		Class.forName(driverClass);
		Connection con=DriverManager.getConnection(url,userName,pass);
		PreparedStatement pst= con.prepareStatement("Select * from html");
		ResultSet rs=pst.executeQuery();
		ArrayList<Question> quesList=new ArrayList<>();
		Question question;
		while(rs.next()) {
			question=new Question(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),Integer.parseInt(rs.getString(7)));
			quesList.add(question);				
		}
		Question[] questions=new Question[10];
		x=1;
		while(i<10) {
			double r=Math.random();
			r=r*100;
			 x=(int)r;
			 x=x%godNumberCPP;
			if(status[x]!=1) {
				status[x]=1;
				questions[i]=quesList.get(x);
				i++;
			}
		}
		questionCheck=questions;
			return questions;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static  Question[] getQuestionsJava(){
		int i=0,x=0;
		int[] status=new int[50];
		try {
		Class.forName(driverClass);
		Connection con=DriverManager.getConnection(url,userName,pass);
		PreparedStatement pst= con.prepareStatement("Select * from java");
		ResultSet rs=pst.executeQuery();
		ArrayList<Question> quesList=new ArrayList<>();
		Question question;
		while(rs.next()) {
			question=new Question(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),Integer.parseInt(rs.getString(7)));
			quesList.add(question);				
		}
		Question[] questions=new Question[10];
		x=1;
		while(i<10) {
			double r=Math.random();
			r=r*100;
			 x=(int)r;
			 x=x%godNumberCPP;
			if(status[x]!=1) {
				status[x]=1;
				questions[i]=quesList.get(x);
				i++;
			}
		}
		questionCheck=questions;
			return questions;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static  Question[] getQuestionsSql(){
		int i=0,x=0;
		int[] status=new int[50];
		try {
		Class.forName(driverClass);
		Connection con=DriverManager.getConnection(url,userName,pass);
		PreparedStatement pst= con.prepareStatement("Select * from sql");
		ResultSet rs=pst.executeQuery();
		ArrayList<Question> quesList=new ArrayList<>();
		Question question;
		while(rs.next()) {
			question=new Question(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),Integer.parseInt(rs.getString(7)));
			quesList.add(question);				
		}
		Question[] questions=new Question[10];
		x=1;
		while(i<10) {
			double r=Math.random();
			r=r*100;
			 x=(int)r;
			 x=x%godNumberCPP;
			if(status[x]!=1) {
				status[x]=1;
				questions[i]=quesList.get(x);
				i++;
			}
		}
		questionCheck=questions;
			return questions;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
