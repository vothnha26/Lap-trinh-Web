package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;
import service.impl.UserServiceImpl;
import util.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Kiểm tra nếu người dùng đã đăng nhập trong session
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect(req.getContextPath() + Constant.Path.ADMIN_HOME);
            return;
        }

        // 2. Kiểm tra "remember me" trong cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true); // Tạo session mới nếu cần
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + Constant.Path.ADMIN_HOME);
                    return;
                }
            }
        }
        
        // 3. Nếu chưa đăng nhập, chuyển đến trang đăng ký
        req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Lấy thông tin từ form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        
        UserService service = new UserServiceImpl();
        String alertMsg = "";

        // Kiểm tra sự tồn tại của email và username
        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
            return;
        }
        
        if (service.checkExistUsername(username)) {
            alertMsg = "Tên đăng nhập đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
            return;
        }
        
        // Thực hiện đăng ký
        boolean isSuccess = service.register(username, password, email, fullname, phone);
        
        if (isSuccess) {
            // Dùng session để gửi thông báo thành công qua trang khác
            HttpSession session = req.getSession();
            session.setAttribute("successMsg", "Đăng ký thành công! Vui lòng đăng nhập.");
            resp.sendRedirect(req.getContextPath() + Constant.Path.LOGIN);
        } else {
            // Có lỗi hệ thống
            alertMsg = "Đã có lỗi xảy ra từ hệ thống, vui lòng thử lại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
        }
    }
}
