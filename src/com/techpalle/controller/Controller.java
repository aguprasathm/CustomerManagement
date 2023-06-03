package com.techpalle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.Customerdao;
import com.techpalle.model.Customer;

@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String path=request.getServletPath();
		
		switch(path)
		{
		case"/delete":
			deleteCustomer(request,response);
			break;
		case"/edit":
			editCustomer(request,response);
			break;
		case"/editForm":
			getEditForm(request,response);
			break;
		case"/insertForm":
			getInsertForm(request,response);
			break;
		case"/add":
			addCustomer(request,response);
			break;
		default:
			getStartUpPage(request,response);
			break;
		}
	}

	
	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		int i=Integer.parseInt(request.getParameter("id"));
		
		Customerdao.deleteCustomer(i);
		
		try
		{
			
			//redirect user to customer list
			
			response.sendRedirect("list");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	private void editCustomer(HttpServletRequest request, HttpServletResponse response)
	{
		int i=Integer.parseInt(request.getParameter("tbId"));
		String n=request.getParameter("tbName");
		String e=request.getParameter("tbemail");
		long m=Long.parseLong(request.getParameter("tbmobil"));
		
		Customer c=new Customer(i,n,e,m);
		
		Customerdao.editCustomer(c);
		
		getStartUpPage(request,response);
		
	}


	private void getEditForm(HttpServletRequest request, HttpServletResponse response) 
	{
		//Fetch the id from url
		int i=Integer.parseInt(request.getParameter("id"));
		
		Customer c=Customerdao.getOneCustomer(i);
		
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_form.jsp");
			request.setAttribute("customer", c);
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}


	private void getInsertForm(HttpServletRequest request, HttpServletResponse response) 
	{
		
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_form.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	private void addCustomer(HttpServletRequest request, HttpServletResponse response)
	{
		
		//Reading data from customer-form Page
		String n=request.getParameter("tbName");
		String e=request.getParameter("tbemail");
		long m=Long.parseLong(request.getParameter("tbmobil"));
		
		//store the admin given data into model/object
		Customer c=new Customer(n,e,m);
		
		//Insert customer data to DB
		Customerdao.addCustomer(c);
		
		//Redirect Admin to homepage(Customer-list page)
		getStartUpPage(request,response);
		
	}


	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try
		{
			ArrayList<Customer> alCustomer=Customerdao.getAllCustomer();
			
			RequestDispatcher rd=request.getRequestDispatcher("customer_list.jsp");
            request.setAttribute("al", alCustomer);
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
