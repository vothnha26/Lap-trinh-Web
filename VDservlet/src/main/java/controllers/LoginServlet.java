package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	response.getWriter().append("Mật khẩu không chính xác");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {
	resp.setContentType("text/html");
	//lấy dữ liệu từ tham số của form
	String user = req.getParameter("username");
	String pass = req.getParameter("password");
	
	if ("kikaho".equals(user) && "kikaho".equals(pass)) {
	    Cookie c = new Cookie("username", user);
	    c.setMaxAge(30);
	    resp.addCookie(c);
	    resp.sendRedirect(req.getContextPath() + "/hello");
	} else {
	    resp.sendRedirect(req.getContextPath() + "/login");
	}
	}
	
}
