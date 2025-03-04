/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Customer;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tovie
 */
public class CustomerDAO extends DBContext {

    public List<Customer> getAllCustomers() {
        try {
            List<Customer> customers = new ArrayList<>();
            String sql = "select * from customer";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Phone"),
                        rs.getString("CustomerName"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Address")
                );
                customers.add(customer);
            }
            return customers;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();}


    

    public Customer getCustomerByID(int id) {
        try {
            Customer customer = null;
            String sql = "select * from customer where CustomerID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Phone"),
                        rs.getString("CustomerName"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Address")
                );
            }
            return customer;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer getCustomerByEmail(String email) {
        try {
            Customer customer = null;
            String sql = "select * from customer where Email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Phone"),
                        rs.getString("CustomerName"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Address")
                );
            }
            return customer;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer getCustomerByName(String name) {
        try {
            Customer customer = null;
            String sql = "select * from customer where CustomerName=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Phone"),
                        rs.getString("CustomerName"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Address")
                );
            }
            return customer;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer getCustomerByPassword(String password) {
        try {
            Customer customer = null;
            String sql = "select * from customer where Password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Phone"),
                        rs.getString("CustomerName"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Address")
                );
            }
            return customer;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateCustomer(Customer customer) {
        try {
            String sql = "update customer set Phone=?, CustomerName=?, Password=?, Email=?, Address=? where CustomerID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getPhone());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getPassword());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getAddress());
            ps.setInt(6, customer.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteCustomer(int id) {
        try {
            String sql = "delete from customer where CustomerID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
        System.out.println(dao.getCustomerByID(2));
    }
}
