package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;
import dto.Task;
//this is to accept url from addtask.html
@WebServlet("/addtask")
//this is to create a servlet class
public class AddTask extends HttpServlet{
@Override
//when form is there and method is post then we shoud take doPost
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
	String name=req.getParameter("name");
	String description=req.getParameter("description");
	int days=Integer.parseInt(req.getParameter("days"));
	
	//setting the data inside task object
	Task t=new Task();
	t.setName(name);
	t.setDescription(description);
	t.setTaskdate(LocalDate.now());
	t.setCompletionDate(LocalDate.now().plusDays(days));
	
	
	MyUser myuser=(MyUser)req.getSession().getAttribute("user");
	//for mapping task to user (it is one to many so list is required hence taking existing list)
	List<Task> list=myuser.getTasks();
	//to avoid null pointer exception
	if(list==null)
		list=new ArrayList<Task>();
	
	list.add(t);
	//mapping
	myuser.setTasks(list);
	
	//saving changes in database
	UserDao dao=new UserDao();
	dao.save(t);
	dao.update(myuser);
	 //saving changes in session
	MyUser myUser2=dao.findByEmail(myuser.getEmail());
	req.getSession().setAttribute("user", myUser2);
	res.getWriter().print("<h1 style='color:green'>Task added Successfully</h1>" );
	req.setAttribute("list", myuser.getTasks());
	req.getRequestDispatcher("todo_home.jsp").include(req, res);
	}
}
}
