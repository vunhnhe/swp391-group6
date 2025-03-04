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
    private static final Logger LOGGER = Logger.getLogger(AdminDAO.class.getName());

    // Định nghĩa hằng số cho tên cột trong database
    private static final String COLUMN_ADMIN_ID = "AdminID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_PASSWORD = "Password";

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    public AdminDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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
            LOGGER.log(Level.SEVERE, "Error fetching all admins", ex);
        }
        return admins;
    }

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
            LOGGER.log(Level.SEVERE, "Error fetching admin by ID", ex);
        }
        return null;
    }

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
            LOGGER.log(Level.SEVERE, "Error fetching admin by email", ex);
        }
        return null;
    }

    public boolean updateAdmin(Admin admin) {
        String sql = "UPDATE Admin SET " + COLUMN_NAME + " = ?, " + COLUMN_EMAIL + " = ?, " + COLUMN_PASSWORD + " = ? WHERE " + COLUMN_ADMIN_ID + " = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());
            ps.setInt(4, admin.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating admin", ex);
        }
        return false;
    }

    public boolean deleteAdmin(int id) {
        String sql = "DELETE FROM Admin WHERE " + COLUMN_ADMIN_ID + " = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting admin", ex);
        }
        return false;
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=MovieTicketBooking;TrustServerCertificate=true;";
            String user = "your_username";
            String pass = "your_password";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, pass);
            
            AdminDAO dao = new AdminDAO(conn);
            System.out.println(dao.getAdminByID(1));
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "JDBC Driver not found", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database connection error: " + e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error", e);
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Failed to close connection", e);
            }
        }
    }

    public Admin getAdminByName(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
