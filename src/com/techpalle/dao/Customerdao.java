package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.techpalle.model.Customer;

public class Customerdao
{
	private static final String url="jdbc:mysql://localhost:3306/agu";
	private static final String username="root";
	private static final String password="admin";
	 
	private static Connection con=null;
	private static PreparedStatement ps=null;
	private static Statement s=null;
	private static ResultSet rs=null;
	
	private static final String customerListQuery="select * from customers";
	private static final String customerinsert="insert into customers(name,email,mobil) values(?,?,?)";
	private static final String customereditQuery="select * from customers where id=?";
	private static final String customerupdate="update customers set name=?,email=?,mobil=? where id=?";
	private static final String customerdelete="delete from customers where id=?";
	
	public static void deleteCustomer(int id)
	{
		con=getConnectionDef();
		try
		{
			ps=con.prepareStatement(customerdelete);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			if(ps!=null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void editCustomer(Customer c)
	{
		
		try
		{
			con=getConnectionDef();
			ps=con.prepareStatement(customerupdate);
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setLong(3, c.getMobile());
			ps.setInt(4, c.getId());
			
			ps.executeUpdate();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			if(ps!=null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Customer getOneCustomer(int id)
	{
		Customer c=null;
		try 
		{
			con=getConnectionDef();

			ps=con.prepareStatement(customereditQuery);
			ps.setInt(1,id);
			
			rs=ps.executeQuery();
			rs.next();
			
			int i=rs.getInt("id");
			String n=rs.getString("name");
			String e=rs.getString("email");
			long m=rs.getLong("mobil");
			
			 c=new Customer(i,n,e,m);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		finally
		{
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
				if(ps!=null)
				{
					try {
						ps.close();
					} catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
					if(con!=null)
					{
						try {
							con.close();
						} 
						catch (SQLException e)
                        {
							e.printStackTrace();
						}
			}
		}
		return c;
	}
	
	public static void addCustomer(Customer customer)
	{
		try 
		{
			con=getConnectionDef();

			ps=con.prepareStatement(customerinsert);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setLong(3, customer.getMobile());
			
			ps.executeUpdate();
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		finally
		{
			if(ps!=null)
			{
				try 
				{
					ps.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try 
				{
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static Connection getConnectionDef()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static ArrayList<Customer> getAllCustomer()
	{
		
		ArrayList<Customer> al=new ArrayList<Customer>();
		try 
		{
			con=getConnectionDef();
			s=con.createStatement();
			rs=s.executeQuery(customerListQuery);
			
			while(rs.next()==true)
			{
				int i=rs.getInt("id");
				String n=rs.getString("name");
				String e=rs.getString("email");
				long m=rs.getLong("mobil");
				
				Customer c=new Customer(i,n,e,m);
				al.add(c);
			}		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		finally
		{
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
				if(s!=null)
				{
					try {
						s.close();
					} catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
					if(con!=null)
					{
						try {
							con.close();
						} 
						catch (SQLException e)
                        {
							e.printStackTrace();
						}
			}
		}
		
		return al;
	}
		}
	
