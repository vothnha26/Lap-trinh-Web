package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // --- KHAI BÁO CÁC THÔNG TIN KẾT NỐI ---
    // Đặt tất cả thông tin ở đây để dễ dàng thay đổi khi cần
    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=LapTrinhWeb;user=sa;password=nhacc123@;encrypt=true;trustServerCertificate=true;";
    private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /**
     * Constructor private để ngăn việc tạo đối tượng từ bên ngoài.
     * Đây là một lớp tiện ích, chỉ nên dùng phương thức tĩnh.
     */
    private DBConnection() {}

    /**
     * Phương thức tĩnh để lấy về một kết nối đến cơ sở dữ liệu.
     * @return một đối tượng Connection hoặc null nếu có lỗi.
     */
    public static Connection getConnection() {
        try {
            // Bước 1: Nạp driver
            Class.forName(DRIVER_CLASS);
            
            // Bước 2: Trả về kết nối
            return DriverManager.getConnection(CONNECTION_URL);
            
        } catch (ClassNotFoundException | SQLException e) {
            // Trong ứng dụng thực tế, nên sử dụng một logger chuyên nghiệp
            e.printStackTrace();
            return null; // Trả về null nếu kết nối thất bại
        }
    }
}