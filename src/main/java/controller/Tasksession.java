package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//this is to accept url from todo_home.jsp from addtask button
@WebServlet("/tasksession")
//to convert normal class to servlet class
public class Tasksession extends HttpServlet {
@Override
//when button is there and <a> is there it is doGet
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//logic to verify session(here value shoud be same as key as we set in login)
	if(req.getSession().getAttribute("user")==null) {
		//not logged in already logged out
		res.getWriter().print("<h1>invalid session, login again</h1>");
		req.getRequestDispatcher("login.html").include(req, res);
	}
	else {
		//valid user - logged in
		req.getRequestDispatcher("Addtask.html").forward(req, res);
	}
}
}
