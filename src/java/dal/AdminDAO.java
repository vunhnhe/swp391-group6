/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;

public class AdminDAO {
    private Connection connection;

    // Định nghĩa hằng số cho tên cột trong database
    private static final String COLUMN_ADMIN_ID = "AdminID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_PASSWORD = "Password";

    // Constructor để nhận Connection từ bên ngoài (cách tốt hơn để tránh hardcode)
    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * 
     * @return List<Admin>
     */
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM Admin";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt(COLUMN_ADMIN_ID),
                        rs.getString(COLUMN_NAME),
                        rs.getString(COLUMN_EMAIL),
                        rs.getString(COLUMN_PASSWORD)
                );
                admins.add(admin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admins;
    }

    /**
     * Lấy thông tin Admin theo ID
     * @param id ID của Admin
     * @return Admin object hoặc null nếu không tìm thấy
     */
    public Admin getAdminByID(int id) {
        String sql = "SELECT * FROM Admin WHERE " + COLUMN_ADMIN_ID + " = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                            rs.getInt(COLUMN_ADMIN_ID),
                            rs.getString(COLUMN_NAME),
                            rs.getString(COLUMN_EMAIL),
                            rs.getString(COLUMN_PASSWORD)
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Lấy thông tin Admin theo Email
     * @param email Email của Admin
     * @return Admin object hoặc null nếu không tìm thấy
     */
    public Admin getAdminByEmail(String email) {
        String sql = "SELECT * FROM Admin WHERE " + COLUMN_EMAIL + " = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                            rs.getInt(COLUMN_ADMIN_ID),
                            rs.getString(COLUMN_NAME),
                            rs.getString(COLUMN_EMAIL),
                            rs.getString(COLUMN_PASSWORD)
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Cập nhật thông tin Admin
     * @param admin Đối tượng Admin đã chỉnh sửa
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean updateAdmin(Admin admin) {
        String sql = "UPDATE Admin SET " + COLUMN_NAME + " = ?, " + COLUMN_EMAIL + " = ?, " + COLUMN_PASSWORD + " = ? WHERE " + COLUMN_ADMIN_ID + " = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());
            ps.setInt(4, admin.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Xóa Admin theo ID
     * @param id ID của Admin cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     */
    public boolean deleteAdmin(int id) {
        String sql = "DELETE FROM Admin WHERE " + COLUMN_ADMIN_ID + " = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Kiểm tra hoạt động
    public static void main(String[] args) {
        try {
            // Kết nối với database (có thể truyền connection từ nơi khác vào)
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=MovieTicketBooking;TrustServerCertificate=true;";
            String user = "your_username";  // Đổi lại tên đăng nhập
            String pass = "your_password";  // Đổi lại mật khẩu
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            
            // Tạo DAO và gọi thử phương thức
            AdminDAO dao = new AdminDAO(conn);
            System.out.println(dao.getAdminByID(1));

            // Đóng kết nối sau khi sử dụng
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
