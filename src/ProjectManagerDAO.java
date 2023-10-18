// Data Access Object for ProjectManagers

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectManagerDAO {

    // Create a new ProjectManager
    public void createProjectManager(ProjectManager projectManager) throws SQLException {
        String sql = "INSERT INTO ProjectManager (pm_name, pm_email, pm_phone, pm_address) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, projectManager.getName());
            statement.setString(2, projectManager.getEmail());
            statement.setString(3, projectManager.getPhone());
            statement.setString(4, projectManager.getAddress());
            statement.executeUpdate();
            System.out.println("Project Manager created successfully");
        }
    }

    // Retrieve all ProjectManagers
    public List<ProjectManager> getAllProjectManagers() throws SQLException {
        List<ProjectManager> projectManagers = new ArrayList<>();
        String sql = "SELECT * FROM ProjectManager";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);

                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int pmId = resultSet.getInt("pm_id");
                String pmName = resultSet.getString("pm_name");
                String pmEmail = resultSet.getString("pm_email");
                String pmPhone = resultSet.getString("pm_phone");
                String pmAddress = resultSet.getString("pm_address");

                ProjectManager projectManager = new ProjectManager(pmName, pmEmail, pmPhone, pmAddress);
                projectManager.setId(pmId);
                projectManagers.add(projectManager);
            }
        }
        return projectManagers;
    }

    // Update a ProjectManager
    public void updateProjectManager(ProjectManager projectManager) throws SQLException {
        String sql = "UPDATE ProjectManager SET  pm_email = ?, pm_phone = ?, pm_address = ? WHERE pm_name = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(4, projectManager.getName());
            statement.setString(1, projectManager.getEmail());
            statement.setString(2, projectManager.getPhone());
            statement.setString(3, projectManager.getAddress());
            statement.executeUpdate();
            System.out.println("Project Manager updated successfully");
        }
    }

    // Retrieve a ProjectManager by their name or ID
    public ProjectManager getProjectManagerByName(String name) throws SQLException {
        String sql = "SELECT * FROM ProjectManager WHERE pm_name = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int pmId = resultSet.getInt("pm_id");
                    String pmName = resultSet.getString("pm_name");
                    String pmEmail = resultSet.getString("pm_email");
                    String pmPhone = resultSet.getString("pm_phone");
                    String pmAddress = resultSet.getString("pm_address");

                    ProjectManager projectManager = new ProjectManager(pmName, pmEmail, pmPhone, pmAddress);
                    projectManager.setId(pmId);
                    return projectManager;
                }
            }
        }
        return null;
    }

    // Retrieve a ProjectManager by their ID
    public ProjectManager getProjectManagerById(int Id) throws SQLException {
        String sql = "SELECT * FROM ProjectManager WHERE pm_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, Id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int pmId = resultSet.getInt("pm_id");
                    String pmName = resultSet.getString("pm_name");
                    String pmEmail = resultSet.getString("pm_email");
                    String pmPhone = resultSet.getString("pm_phone");
                    String pmAddress = resultSet.getString("pm_address");

                    ProjectManager projectManager = new ProjectManager(pmName, pmEmail, pmPhone, pmAddress);
                    projectManager.setId(pmId);
                    return projectManager;
                }
            }
        }
        return null;
    }

    // Delete a ProjectManager
    public void deleteProjectManager(int pmId) throws SQLException {
        String sql = "DELETE FROM ProjectManager WHERE pm_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pmId);

            statement.executeUpdate();
        }
    }
}
