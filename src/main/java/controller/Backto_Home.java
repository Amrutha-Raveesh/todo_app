package controller;

import java.io.IOException;
																											
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MyUser;
//this is to accept url
@WebServlet("/backtohome")
//this is to make normal class as servlet class
public class Backto_Home extends HttpServlet{
@Override
//when button is there and <a> is there then doGet
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//session validation logic
	MyUser user=(MyUser)req.getSession().getAttribute("user");
	if(user==null) {
		res.getWriter().print("<h1>session expired, login again</h1>");
		req.getRequestDispatcher("login.html").include(req, res);
	}
	else {
		//carrying tasks to todo_home.jsp to display
		req.setAttribute("list", user.getTasks());
		req.getRequestDispatcher("todo_home.jsp").include(req, res);
	}
	}
}

