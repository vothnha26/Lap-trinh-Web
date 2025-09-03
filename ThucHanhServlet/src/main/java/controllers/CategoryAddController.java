package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig; // THÊM DÒNG NÀY
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;
import util.Constant; // THÊM DÒNG NÀY

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

// BẮT BUỘC PHẢI CÓ ANNOTATION NÀY ĐỂ XỬ LÝ FILE UPLOAD
@MultipartConfig 
@WebServlet({"/admin/category/add"})
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService cateService = new CategoryServiceImpl();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/add-category.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Category category = new Category();

        try {
            String cateName = request.getParameter("name");
            
            // Kiểm tra validation cho tên danh mục trước
            if (cateName == null || cateName.trim().isEmpty()) {
                request.setAttribute("error", "Tên danh mục không được để trống!");
                doGet(request, response);
                return; // Dừng xử lý
            }
            category.setCatename(cateName.trim());

            // --- Xử lý file upload an toàn hơn ---
            Part filePart = request.getPart("icon"); // Lấy Part có name="icon"

            // Kiểm tra xem người dùng có thực sự chọn file không
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                
                // Lấy đường dẫn thực của webapp, sử dụng hằng số
                String realPath = request.getServletContext().getRealPath("/");
                String uploadPath = realPath + Constant.DIR;
                
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs(); // Dùng mkdirs() để tạo cả thư mục cha nếu cần
                }

                // Tạo tên file duy nhất để tránh ghi đè
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                String filePath = uploadPath + File.separator + uniqueFileName;
                
                // Ghi file lên server
                filePart.write(filePath);
                
                // Lưu đường dẫn TƯƠNG ĐỐI vào database để linh hoạt
                // Sử dụng "/" làm dấu phân cách cho đường dẫn web
                category.setIcon(Constant.DIR + "/" + uniqueFileName);
            }
            // Nếu không có file nào được upload, trường category.icon sẽ là null, hoàn toàn ổn.

            // --- Kết thúc xử lý file upload ---

            cateService.insert(category);
            
            // Chuyển hướng về trang danh sách sau khi thêm thành công
            response.sendRedirect(request.getContextPath() + "/admin/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã có lỗi xảy ra trong quá trình xử lý!");
            doGet(request, response);
        }
    }
}