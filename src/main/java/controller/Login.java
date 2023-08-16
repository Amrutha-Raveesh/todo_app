package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;
import dto.Task;
//this is t0 accept url
@WebServlet("/login")
//this is to make our class as servlet class
public class Login extends HttpServlet {
@Override
//when the form is there and method is post then we will use doPost
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//receive the data from front-end
String email=req.getParameter("email");
String password=req.getParameter("password");

UserDao userdao=new UserDao();
//checking email is present
MyUser myuser=userdao.findByEmail(email);

if(myuser==null) {
	//email is not present i.e., wrong
	res.getWriter().print("<h1>Incorrect Email</h1>");
	req.getRequestDispatcher("login.html").include(req, res);
}
else {
	//email is present to check password
	if(myuser.getPassword().equals(password)) {
		//password is correct so login success
		//session tracking logic- adding into session
		//setting user inside session key and value(key is important)
		req.getSession().setAttribute("user", myuser);
		
		//this is for setting time for session
		req.getSession().setMaxInactiveInterval(30);
		res.getWriter().print("<h1>Login Success</h1>");
		
		//carrying data to todo_home.jsp to Display. set attribute helps to carry the data to todo_home.jsp
		req.setAttribute("list", myuser.getTasks());
		req.getRequestDispatcher("todo_home.jsp").include(req, res);
		
		//List<Task> list=userdao.fetchAllTask();
	}
	else {
		//email is correct but incorrect password
		res.getWriter().print("<h1>Incorrect Password</h1>");
		req.getRequestDispatcher("login.html").include(req, res);
	}
}
}
}
