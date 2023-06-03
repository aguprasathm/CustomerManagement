package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDao 
{
	private static final String url="jdbc:mysql://localhost:3306/agu";
	private static final String username="root";
	private static final String password="admin";
	 
	private static Connection con=null;
	private static PreparedStatement ps=null;
	private static Statement s=null;
	private static ResultSet rs=null;
	
	private static final String ValidateQuery="select * from store_admin where username=? and password=?";
	
	public static boolean validateAdmin(String user,String pass)
	{
		boolean b = false;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, username, password);
			
			ps=con.prepareStatement(ValidateQuery);
			
			rs=ps.executeQuery();
			
			b=rs.next();
			
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}
}
