package com.zhuwei.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JsonServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//利用javabean来模拟数据库
		Province p1 = new Province(1, "吉林省");
		Province p2 = new Province(2, "辽宁省");
		Province p3 = new Province(3, "山东省");
		
		List<Province> list = new ArrayList<Province>();
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		Gson gson = new Gson();
		String json = gson.toJson(list, List.class);
		//System.out.println(json);
		out.println(json);
		
		//JSONArray json = JSONArray.fromObject(list);
		
		//String json = "[{'province':'吉林省'},{'province':'辽宁省'},{'province':'山东省'}]";
		
		//out.println(json.toString());		//响应始终以字符串形式
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
