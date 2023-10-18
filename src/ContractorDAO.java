// Data Access Object for Contractor

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractorDAO {

    // Add an Contractor object to the database
    public void createContractor(Contractor contractor) throws SQLException {
        String sql = "INSERT INTO Contractor (contractor_name, contractor_email, contractor_phone, contractor_address) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, contractor.getName());
            statement.setString(2, contractor.getEmail());
            statement.setString(3, contractor.getPhone());
            statement.setString(4, contractor.getAddress());
            statement.executeUpdate();
            System.out.println("Contractor created successfully");
        }
    }

    // Retrieve all Contractors
    public List<Contractor> getAllContractors() throws SQLException {
        List<Contractor> contractors = new ArrayList<>();
        String sql = "SELECT * FROM Contractor";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int contractorId = resultSet.getInt("contractor_id");
                String contractorName = resultSet.getString("contractor_name");
                String contractorEmail = resultSet.getString("contractor_email");
                String contractorPhone = resultSet.getString("contractor_phone");
                String contractorAddress = resultSet.getString("contractor_address");

                Contractor contractor = new Contractor(contractorName, contractorEmail, contractorPhone,
                        contractorAddress);
                contractor.setId(contractorId);
                contractors.add(contractor);
            }
        }
        return contractors;
    }

    // Update a Contractor
    public void updateContractor(Contractor contractor) throws SQLException {
        String sql = "UPDATE Contractor SET  contractor_email = ?, contractor_phone = ?, contractor_address = ? WHERE contractor_name = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(4, contractor.getName());
            statement.setString(1, contractor.getEmail());
            statement.setString(2, contractor.getPhone());
            statement.setString(3, contractor.getAddress());
            statement.executeUpdate();
            System.out.println("Contractor updated successfully");
        }
    }

    // Get a Contractor by its Name
    public Contractor getContractorByName(String Name) throws SQLException {
        String sql = "SELECT * FROM Contractor WHERE contractor_name = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, Name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int contractorId = resultSet.getInt("contractor_id");
                String contractorName = resultSet.getString("contractor_name");
                String contractorEmail = resultSet.getString("contractor_email");
                String contractorPhone = resultSet.getString("contractor_phone");
                String contractorAddress = resultSet.getString("contractor_address");

                Contractor contractor = new Contractor(contractorName, contractorEmail, contractorPhone,
                        contractorAddress);
                contractor.setId(contractorId);
                return contractor;
            }
        }
        return null;
    }

    // Get a Contractor by its ID
    public Contractor getContractorById(int contractorId) throws SQLException {
        String sql = "SELECT * FROM Contractor WHERE contractor_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contractorId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String contractorName = resultSet.getString("contractor_name");
                String contractorEmail = resultSet.getString("contractor_email");
                String contractorPhone = resultSet.getString("contractor_phone");
                String contractorAddress = resultSet.getString("contractor_address");

                Contractor contractor = new Contractor(contractorName, contractorEmail, contractorPhone,
                        contractorAddress);
                contractor.setId(contractorId);
                return contractor;
            }
        }
        return null;
    }

    // Delete a Contractor
    public void deleteContractor(Contractor contractor) throws SQLException {
        String sql = "DELETE FROM Contractor WHERE contractor_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();
        }
    }

}
