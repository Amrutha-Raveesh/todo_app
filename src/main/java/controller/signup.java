package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;
//this is to accept URL
@WebServlet("/sign-up")
//this is to make our class as servlet class
public class signup extends HttpServlet {
@Override
//when form is there and method is post then we will take doPost
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	//receive data from front-end
	//name attribute value we should write here !!it is case sensitive
String firstname=req.getParameter("fn");
String lastname=req.getParameter("ln");
String email=req.getParameter("email");
String dob=req.getParameter("birth");
String mob=req.getParameter("phno");
String password=req.getParameter("pwd");
String address=req.getParameter("add");
String gender=req.getParameter("gen");
String[] language=req.getParameterValues("language");


MyUser user=new MyUser();
user.setAddress(address);
user.setDob(LocalDate.parse(dob));
user.setEmail(email);
user.setFirstname(firstname);
user.setGender(gender);
user.setLastname(lastname);
user.setLanguage(language);
user.setMob(Long.parseLong(mob));
user.setPassword(password);
 
UserDao userd=new UserDao();

//to verify email should not be repeated
MyUser myUser=userd.findByEmail(email);
if(myUser==null) {
	//email doesnot exist
	userd.save(user);
	//after saving where to go
	res.getWriter().print("<h1 style='color:green'>Account created successfully</h1>");
	req.getRequestDispatcher("login.html").include(req, res);
}
else {
	//if email exists
	res.getWriter().print("<h1 style='color:green'>Email should not be repeated</h1>");
	req.getRequestDispatcher("signup.html").include(req, res);
}
}
}
