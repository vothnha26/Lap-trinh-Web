package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

@WebServlet("/DownloadImageController")
public class DownloadImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DownloadImageController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tên file từ tham số URL, ví dụ: "uploads/category/12345.png"
        String fileName = request.getParameter("fname");

        if (fileName == null || fileName.isEmpty()) {
            // Nếu không có tên file, báo lỗi Bad Request
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File name parameter is missing.");
            return;
        }

        // ---- SỬA LẠI HOÀN TOÀN ĐOẠN NÀY ----
        // 1. Lấy đường dẫn thực của ứng dụng web trên server
        String appRealPath = request.getServletContext().getRealPath("/");

        // 2. Tạo đường dẫn tuyệt đối đến file ảnh
        File file = new File(appRealPath + fileName);

        if (file.exists()) {
            // 3. Để server tự nhận diện loại file (image/png, image/jpeg,...)
            String mimeType = getServletContext().getMimeType(file.getAbsolutePath());
            if (mimeType == null) {
                // Đặt một loại mặc định nếu không nhận diện được
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setContentLength((int) file.length());

            // 4. Đọc file và ghi vào response
            try (FileInputStream in = new FileInputStream(file);
                 OutputStream out = response.getOutputStream()) {
                
                IOUtils.copy(in, out); // Dùng thư viện IOUtils để sao chép hiệu quả
            }
        } else {
            // Nếu không tìm thấy file, trả về lỗi 404 Not Found
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found: " + fileName);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}