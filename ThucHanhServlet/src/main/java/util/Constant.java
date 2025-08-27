package util;

public final class Constant {
    
    // Private constructor để ngăn việc tạo đối tượng từ lớp tiện ích này
    private Constant() {}

    public static final class Path {
        private Path() {}

        // Đã bỏ /views/ theo yêu cầu
        public static final String REGISTER = "/register.jsp";
        public static final String LOGIN = "/login"; // Đường dẫn tới trang login
        public static final String ADMIN_HOME = "/admin"; // Đường dẫn tới trang admin
    }
}