package controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.User;
import service.UserDao;
import service.impl.UserDaoImpl;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public RegisterController() {
        super();
        this.userDao = new UserDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        
        String alertMessage = null;

        // Kiểm tra mật khẩu
        if (!password.equals(confirmPassword)) {
            alertMessage = "Mật khẩu xác nhận không khớp.";
        } 
        // Kiểm tra tên đăng nhập đã tồn tại chưa
        else if (userDao.checkExistUsername(username)) {
            alertMessage = "Tên đăng nhập đã tồn tại.";
        } 
        // Kiểm tra email đã tồn tại chưa
        else if (userDao.checkExistEmail(email)) {
            alertMessage = "Email đã được sử dụng.";
        } 
        // Kiểm tra số điện thoại đã tồn tại chưa
        else if (userDao.checkExistPhone(phone)) {
            alertMessage = "Số điện thoại đã được sử dụng.";
        }
        
        if (alertMessage != null) {
            request.setAttribute("alert", alertMessage);
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else {
            // Tạo đối tượng User
            User newUser = new User();
            newUser.setFullName(fullName);
            newUser.setUserName(username);
            newUser.setEmail(email);
            newUser.setPhone(phone);
            newUser.setPassWord(password);
            
            newUser.setAvatar(null);
            newUser.setRoleid(2);
            newUser.setCreatedDate(Date.valueOf(LocalDate.now()));

            userDao.insert(newUser);
            
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}