// Data Access Object for Customers

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    // Add an Customer object to the database
    public void createCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (customer_name, customer_email, customer_phone, customer_address) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getAddress());
            statement.executeUpdate();
            System.out.println("Customer created successfully");
        }
    }

    // Retrieve all Customers
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                String customerEmail = resultSet.getString("customer_email");
                String customerPhone = resultSet.getString("customer_phone");
                String customerAddress = resultSet.getString("customer_address");

                Customer customer = new Customer(customerName, customerEmail, customerPhone, customerAddress);
                customer.setId(customerId);
                customers.add(customer);
            }
        }
        return customers;
    }

    // Update a Customer
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET  customer_email = ?, customer_phone = ?, customer_address = ? WHERE customer_name = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(4, customer.getName());
            statement.setString(1, customer.getEmail());
            statement.setString(2, customer.getPhone());
            statement.setString(3, customer.getAddress());

            statement.executeUpdate();
            System.out.println("Customer updated successfully");
        }
    }

    // get a Customer by name
    public Customer getCustomerByName(String name) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE customer_name = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                String customerEmail = resultSet.getString("customer_email");
                String customerPhone = resultSet.getString("customer_phone");
                String customerAddress = resultSet.getString("customer_address");

                customer = new Customer(customerName, customerEmail, customerPhone, customerAddress);
                customer.setId(customerId);
            }
        }
        return customer;
    }

    // get a Customer by id
    public Customer getCustomerById(int customerId) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM Customer WHERE customer_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                String customerEmail = resultSet.getString("customer_email");
                String customerPhone = resultSet.getString("customer_phone");
                String customerAddress = resultSet.getString("customer_address");

                customer = new Customer(customerName, customerEmail, customerPhone, customerAddress);
                customer.setId(id);
            }
        }
        return customer;
    }

    // Delete a Customer
    public void deleteCustomer(Customer customer) throws SQLException {
        String sql = "DELETE FROM Customer WHERE customer_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();
        }
    }

}
