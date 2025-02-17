package dal;

import java.util.ArrayList;
import java.util.List;
import model.Customer;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Customer
 * Handles database operations related to Customer
 */
public class CustomerDAO extends DBContext {

    /**
     * Retrieves all customers from the database
     * @return List of Customer objects
     */
    public List<Customer> getAllCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
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
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    /**
     * Retrieves a customer by ID
     * @param id Customer ID
     * @return Customer object
     */
    public Customer getCustomerByID(int id) throws Exception {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE CustomerID=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(
                            rs.getInt("CustomerID"),
                            rs.getString("Phone"),
                            rs.getString("CustomerName"),
                            rs.getString("Password"),
                            rs.getString("Email"),
                            rs.getString("Address")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    /**
     * Retrieves a customer by email
     * @param email Customer email
     * @return Customer object
     */
    public Customer getCustomerByEmail(String email) throws Exception {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE Email=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(
                            rs.getInt("CustomerID"),
                            rs.getString("Phone"),
                            rs.getString("CustomerName"),
                            rs.getString("Password"),
                            rs.getString("Email"),
                            rs.getString("Address")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    /**
     * Retrieves a customer by name
     * @param name Customer name
     * @return Customer object
     */
    public Customer getCustomerByName(String name) throws Exception {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE CustomerName=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(
                            rs.getInt("CustomerID"),
                            rs.getString("Phone"),
                            rs.getString("CustomerName"),
                            rs.getString("Password"),
                            rs.getString("Email"),
                            rs.getString("Address")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    /**
     * Retrieves a customer by password
     * @param password Customer password
     * @return Customer object
     */
    public Customer getCustomerByPassword(String password) throws Exception {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE Password=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(
                            rs.getInt("CustomerID"),
                            rs.getString("Phone"),
                            rs.getString("CustomerName"),
                            rs.getString("Password"),
                            rs.getString("Email"),
                            rs.getString("Address")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    /**
     * Updates a customer in the database
     * @param customer Customer object
     * @return true if update was successful, false otherwise
     */
    public boolean updateCustomer(Customer customer) throws Exception {
        String sql = "UPDATE Customer SET Phone=?, CustomerName=?, Password=?, Email=?, Address=? WHERE CustomerID=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
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

    /**
     * Deletes a customer from the database
     * @param id Customer ID
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteCustomer(int id) throws Exception {
        String sql = "DELETE FROM Customer WHERE CustomerID=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
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
        try {
            System.out.println(dao.getCustomerByID(2));
        } catch (Exception ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
