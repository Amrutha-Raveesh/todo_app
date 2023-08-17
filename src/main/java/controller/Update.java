package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

@WebServlet("/updatestatus")
public class Update extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	if(req.getSession().getAttribute("user")==null) {
		res.getWriter().print("<h1>invalid session, login again</h1>");
		req.getRequestDispatcher("login.html").include(req, res);
	}
	else {
		int id=Integer.parseInt(req.getParameter("id"));
		UserDao dao=new UserDao();
		req.setAttribute("task", dao.fetchTask(id));
        req.getRequestDispatcher("Update.jsp").forward(req, res);
	}
}
}
