package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;
import dto.Task;

@WebServlet("/updatetask")
public class UpdateTask extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MyUser user=(MyUser)req.getSession().getAttribute("user");
		if(user==null) {
			//invalid session
			res.getWriter().print("<h1>invalid session, login again</h1>");
			req.getRequestDispatcher("login.html").include(req, res);
		}
		else {
			//valid session
			//receiving data from front-end form(name attribute value should be written)
			int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String description=req.getParameter("description");
		int days=Integer.parseInt(req.getParameter("days"));
		
		//setting the data inside task object
		Task task=new Task();
		task.setId(id);
		task.setName(name);
		task.setDescription(description);
		task.setTaskdate(LocalDate.now());
		task.setCompletionDate(LocalDate.now().plusDays(days));
		
		UserDao dao=new UserDao();
		dao.update(task);
		
		//Logic to Update Session
		MyUser user2 =dao.findByEmail(user.getEmail());
		req.getSession().setAttribute("user", user2);
		res.getWriter().print("<h1>Updated Successfully</h1>");
		req.setAttribute("list", user2.getTasks());
		req.getRequestDispatcher("todo_home.jsp").include(req, res);
		
		
		
		
	}
	}
}
