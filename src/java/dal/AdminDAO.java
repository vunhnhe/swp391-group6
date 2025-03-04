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

/**
 * Data Access Object for Admin
 * Handles database operations related to Admin
 */
public class AdminDAO extends DBContext {

    /**
     * Retrieves all admins from the database
     * @return List of Admin objects
     */
    public List<Admin> getAllAdmins() {
    List<Admin> admins = new ArrayList<>();
    String sql = "SELECT * FROM Admin";
    
    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Admin admin = new Admin(
                    rs.getInt("AdminID"),
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getString("Password")
            );
            admins.add(admin);
        }
    } catch (SQLException ex) {
        Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return admins;
}


    /**
     * Retrieves an admin by ID
     * @param id Admin ID
     * @return Admin object
     */
    public Admin getAdminByID(int id) {
        try {
            Admin admin = null;
            String sql = "SELECT * FROM Admin WHERE AdminID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Password")
                );
            }
            return admin;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Retrieves an admin by email
     * @param email Admin email
     * @return Admin object
     */
    public Admin getAdminByEmail(String email) {
        try {
            Admin admin = null;
            String sql = "SELECT * FROM Admin WHERE Email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Password")
                );
            }
            return admin;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Retrieves an admin by name
     * @param name Admin name
     * @return Admin object
     */
    public Admin getAdminByName(String name) {
        try {
            Admin admin = null;
            String sql = "SELECT * FROM Admin WHERE Name=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Password")
                );
            }
            return admin;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Retrieves an admin by password
     * @param password Admin password
     * @return Admin object
     */
    public Admin getAdminByPassword(String password) {
        try {
            Admin admin = null;
            String sql = "SELECT * FROM Admin WHERE Password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Password")
                );
            }
            return admin;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Updates an admin in the database
     * @param admin Admin object
     * @return true if update was successful, false otherwise
     */
    public boolean updateAdmin(Admin admin) {
        try {
            String sql = "UPDATE Admin SET Name=?, Email=?, Password=? WHERE AdminID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());
            ps.setInt(4, admin.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Deletes an admin from the database
     * @param id Admin ID
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteAdmin(int id) {
        try {
            String sql = "DELETE FROM Admin WHERE AdminID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
        System.out.println(dao.getAdminByID(1));
    }
}

