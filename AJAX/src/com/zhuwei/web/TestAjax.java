package com.zhuwei.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		System.out.println("xxx");
		System.out.println(request.getMethod());
		
		out.print("Connection is ok");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		System.out.println("connection server success!");
		
		System.out.println("a = "+request.getParameter("a"));
		System.out.println("b = "+request.getParameter("b"));
		System.out.println("c = "+request.getParameter("c"));
		
		out.println("post connection server success");
	}

}
