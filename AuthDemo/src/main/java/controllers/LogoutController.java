package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.Constant;

@WebServlet({"/logout"})
public class LogoutController extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		Cookie usernameCookie = new Cookie("username", null);
		usernameCookie.setMaxAge(0);
		
		response.addCookie(usernameCookie);
		response.sendRedirect(request.getContextPath() + Constant.Path.LOGIN_PAGE);
	}
}
