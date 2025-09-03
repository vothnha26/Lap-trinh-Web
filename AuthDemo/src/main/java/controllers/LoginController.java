package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.*;
import models.*;
import services.impl.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private final UserServiceImpl userServiceImpl = new UserServiceImpl();
	private static final String COOKIE_REMEMBER = "username";
	private static final String SESSION_REMEMBER = "account";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute(SESSION_REMEMBER) != null) {
			response.sendRedirect(request.getContextPath() + Constant.Path.HOME);
			return;
		}
	
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals(COOKIE_REMEMBER)) {
					String username = cookie.getValue();
					User user = userServiceImpl.get(username);
					
					if (user != null) {
						session = request.getSession(true);
						session.setAttribute(SESSION_REMEMBER, user);
						response.sendRedirect(request.getContextPath() + Constant.Path.HOME);
						return;
					}
				}
			}
		}
		
		request.getRequestDispatcher(Constant.Path.LOGIN).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
		
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String remember = request.getParameter("remember-me");
	    
	    User user = userServiceImpl.Login(username, password);
	    boolean isRemember = "on".equals(remember); 
	    if(user != null) {
	    	HttpSession session = request.getSession();
	    	session.setAttribute(SESSION_REMEMBER, user);
	    	if(isRemember) {
	    		saveRemeberMe(request, response, username);
	    	}
	    	response.sendRedirect(request.getContextPath() + Constant.Path.HOME);
	    }
	}
	
	public void saveRemeberMe(HttpServletRequest request, HttpServletResponse response ,String username) {
		Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
		cookie.setMaxAge(7 * 24 * 60 * 60);
		response.addCookie(cookie);
	}
}
