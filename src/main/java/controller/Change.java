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
//this is to accept url from todo_home.jsp
@WebServlet("/changestatus")
//to convert normal class to servlet class
public class Change extends HttpServlet {
@Override
//when button is there and <a> is there then we will use doGet
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	MyUser user=(MyUser)req.getSession().getAttribute("user");
	if(user==null) {
		//invalid session
		res.getWriter().print("<h1>invalid session, login again</h1>");
		req.getRequestDispatcher("login.html").include(req, res);
	}
	else {
		//valid session
		//logic to fetch task object
		int id=Integer.parseInt(req.getParameter("id"));
		UserDao dao=new UserDao();
		Task task=dao.fetchTask(id);
		//logic to change status
		if(task.isStatus())
			task.setStatus(false);
		else
			task.setStatus(true);
		//logic to update in database
		dao.update(task);
		//logic to update the session
		MyUser user2=dao.findByEmail(user.getEmail());
		req.getSession().setAttribute("user", user2);
		res.getWriter().print("<h1>Status change success</h1>");
		//carry data again to todo_home.jsp
		req.setAttribute("list", user2.getTasks());
		req.getRequestDispatcher("todo_home.jsp").include(req, res);
	}
}
}
