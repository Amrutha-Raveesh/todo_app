package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;
import dto.Task;
//accept the url from todo_home.jsp
@WebServlet("/deletestatus")
//to convert normal class to servlet class
public class Delete extends HttpServlet{
@Override
//if button is there and <a> is there then we will use doGet
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	MyUser user=(MyUser)req.getSession().getAttribute("user");
	if(user==null) {
		//invalid session
		res.getWriter().print("<h1>invalid session, login again</h1>");
		req.getRequestDispatcher("login.html").include(req, res);
	}
	else {
		//valid session
		int id=Integer.parseInt(req.getParameter("id"));
		UserDao dao=new UserDao();
		Task task=dao.fetchTask(id);
		if(task==null) {
			res.getWriter().print("<h1>session expired,login again</h1>");
		req.getRequestDispatcher("login.html").include(req, res);
		}
		else {
		//logic to remove mapping
		user.getTasks().remove(task);
		dao.update(user);
		
		//logic to delete
		dao.remove(task);
		
		MyUser user2=dao.findByEmail(user.getEmail());
		req.getSession().setAttribute("user", user2);
		res.getWriter().print("<h1>data deleted successfully</h1>");
		req.setAttribute("list", user2.getTasks());
		req.getRequestDispatcher("todo_home.jsp").include(req, res);
}
}
}
}
