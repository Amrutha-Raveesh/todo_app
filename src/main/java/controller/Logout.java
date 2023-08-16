package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//to accept the url from logout button intodo_home.jsp
@WebServlet("/logout")
//to convert normalclass into servlet class
public class Logout extends HttpServlet {
@Override
//when button is there and anchor tag is there we will take doGet
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//log out logic it will end session
	req.getSession().invalidate();
	res.getWriter().print("<h1>logout success</h1>");
	req.getRequestDispatcher("login.html").include(req, res);
}
}
