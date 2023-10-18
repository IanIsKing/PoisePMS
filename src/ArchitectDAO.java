// Data Access Object for Architect

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArchitectDAO {

    // Add an Architect object to the database
    public void createArchitect(Architect architect) throws SQLException {
        String sql = "INSERT INTO Architect (architect_name, architect_email, architect_phone, architect_address) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, architect.getName());
            statement.setString(2, architect.getEmail());
            statement.setString(3, architect.getPhone());
            statement.setString(4, architect.getAddress());
            statement.executeUpdate();
            System.out.println("Architect created successfully");
        }
    }

    // Retrieve all Architects
    public List<Architect> getAllArchitects() throws SQLException {
        List<Architect> architects = new ArrayList<>();
        String sql = "SELECT * FROM Architect";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);

                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int architectId = resultSet.getInt("architect_id");
                String architectName = resultSet.getString("architect_name");
                String architectEmail = resultSet.getString("architect_email");
                String architectPhone = resultSet.getString("architect_phone");
                String architectAddress = resultSet.getString("architect_address");

                Architect architect = new Architect(architectName, architectEmail, architectPhone, architectAddress);
                architect.setId(architectId);
                architects.add(architect);
            }
        }
        return architects;
    }

    // Update a Architect
    public void updateArchitect(Architect architect) throws SQLException {
        String sql = "UPDATE Architect SET  architect_email = ?, architect_phone = ?, architect_address = ? WHERE architect_name = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(4, architect.getName());
            statement.setString(1, architect.getEmail());
            statement.setString(2, architect.getPhone());
            statement.setString(3, architect.getAddress());
            statement.executeUpdate();
            System.out.println("Architect updated successfully");
        }
    }

    // retrieve a Architect by name
    public Architect getArchitectByName(String name) throws SQLException {
        String sql = "SELECT * FROM Architect WHERE architect_name = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int architectId = resultSet.getInt("architect_id");
                    String architectName = resultSet.getString("architect_name");
                    String architectEmail = resultSet.getString("architect_email");
                    String architectPhone = resultSet.getString("architect_phone");
                    String architectAddress = resultSet.getString("architect_address");

                    Architect architect = new Architect(architectName, architectEmail, architectPhone,
                            architectAddress);
                    architect.setId(architectId);
                    return architect;
                }
            }
        }
        return null;
    }

    // retrieve a Architect by id
    public Architect getArchitectById(int architectId) {
        String sql = "SELECT * FROM Architect WHERE architect_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, architectId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("architect_id");
                    String architectName = resultSet.getString("architect_name");
                    String architectEmail = resultSet.getString("architect_email");
                    String architectPhone = resultSet.getString("architect_phone");
                    String architectAddress = resultSet.getString("architect_address");

                    Architect architect = new Architect(architectName, architectEmail, architectPhone,
                            architectAddress);
                    architect.setId(id);
                    return architect;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // delete a Architect by name
    public void deleteArchitectByName(String name) throws SQLException {
        String sql = "DELETE FROM Architect WHERE architect_name = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }

}
