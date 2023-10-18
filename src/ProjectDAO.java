// Data Access Object for Projects

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    // Add a new project to the database
    public void addProject(Project project) throws SQLException {
        String sql = "INSERT INTO project (project_name, building_type, address, erf_number, total_fee, amount_paid, deadline_date, completion_date, project_status, customer_id, architect_id, contractor_id, pm_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getBuildingType());
            statement.setString(3, project.getPhysicalAddress());
            statement.setString(4, project.getErfNumber());
            statement.setDouble(5, project.getTotalFee());
            statement.setDouble(6, project.getAmountPaid());
            statement.setString(7, project.getDeadlineDate());
            statement.setString(8, project.getCompletionDate());
            statement.setString(9, project.getProjectStatus());
            statement.setInt(10, project.getCustomerId());
            statement.setInt(11, project.getArchitectId());
            statement.setInt(12, project.getContractorId());
            statement.setInt(13, project.getProjectManagerId());
            statement.executeUpdate();
        }
    }

    // Retrieve all projects
    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int projectId = resultSet.getInt("project_id");
                String projectName = resultSet.getString("project_name");
                String buildingType = resultSet.getString("building_type");
                String physicalAddress = resultSet.getString("address");
                String erfNumber = resultSet.getString("erf_number");
                double totalFee = resultSet.getDouble("total_fee");
                double amountPaid = resultSet.getDouble("amount_paid");
                String deadlineDate = resultSet.getString("deadline_date");
                String completionDate = resultSet.getString("completion_date");
                String projectStatus = resultSet.getString("project_status");
                int customerId = resultSet.getInt("customer_id");
                int architectId = resultSet.getInt("architect_id");
                int contractorId = resultSet.getInt("contractor_id");
                int projectManagerId = resultSet.getInt("pm_id");
                Project project = new Project(projectName, buildingType, physicalAddress,
                        erfNumber, totalFee, amountPaid, deadlineDate, completionDate, projectStatus, customerId,
                        architectId, contractorId, projectManagerId);
                project.setId(projectId);
                projects.add(project);
            }
        }
        return projects;
    }

    // Retrieve a project by name or ID
    public Project getProjectByNameOrId(String nameOrID) throws SQLException {
        String sql = "SELECT * FROM project WHERE project_name = ? OR project_id = ?";
        Project project = null;
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nameOrID);
            statement.setString(2, nameOrID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int projectId = resultSet.getInt("project_id");
                String projectName = resultSet.getString("project_name");
                String buildingType = resultSet.getString("building_type");
                String physicalAddress = resultSet.getString("address");
                String erfNumber = resultSet.getString("erf_number");
                double totalFee = resultSet.getDouble("total_fee");
                double amountPaid = resultSet.getDouble("amount_paid");
                String deadlineDate = resultSet.getString("deadline_date");
                String completionDate = resultSet.getString("completion_date");
                String projectStatus = resultSet.getString("project_status");
                int customerId = resultSet.getInt("customer_id");
                int architectId = resultSet.getInt("architect_id");
                int contractorId = resultSet.getInt("contractor_id");
                int projectManagerId = resultSet.getInt("pm_id");
                project = new Project(projectName, buildingType, physicalAddress,
                        erfNumber, totalFee, amountPaid, deadlineDate, completionDate, projectStatus, customerId,
                        architectId, contractorId, projectManagerId);
                project.setId(projectId);
            }
        }

        return project;
    }

    // Retrieve a project by ID
    public Project getProjectById(int id) throws SQLException {
        String sql = "SELECT * FROM project WHERE project_id = ?";
        Project project = null;
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int projectId = resultSet.getInt("project_id");
                String projectName = resultSet.getString("project_name");
                String buildingType = resultSet.getString("building_type");
                String physicalAddress = resultSet.getString("address");
                String erfNumber = resultSet.getString("erf_number");
                double totalFee = resultSet.getDouble("total_fee");
                double amountPaid = resultSet.getDouble("amount_paid");
                String deadlineDate = resultSet.getString("deadline_date");
                String completionDate = resultSet.getString("completion_date");
                String projectStatus = resultSet.getString("project_status");
                int customerId = resultSet.getInt("customer_id");
                int architectId = resultSet.getInt("architect_id");
                int contractorId = resultSet.getInt("contractor_id");
                int projectManagerId = resultSet.getInt("pm_id");
                project = new Project(projectName, buildingType, physicalAddress,
                        erfNumber, totalFee, amountPaid, deadlineDate, completionDate, projectStatus, customerId,
                        architectId, contractorId, projectManagerId);
                project.setId(projectId);
            }
        }

        return project;
    }

    // Update a project
    public void updateProject(Project project) throws SQLException {
        String sql = "UPDATE project SET project_name = ?, building_type = ?, address = ?, erf_number = ?, total_fee = ?, amount_paid = ?, deadline_date = ?, completion_date = ?, project_status = ?, customer_id = ?, architect_id = ?, contractor_id = ?, pm_id = ? WHERE project_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getBuildingType());
            statement.setString(3, project.getPhysicalAddress());
            statement.setString(4, project.getErfNumber());
            statement.setDouble(5, project.getTotalFee());
            statement.setDouble(6, project.getAmountPaid());
            statement.setString(7, project.getDeadlineDate());
            statement.setString(8, project.getCompletionDate());
            statement.setString(9, project.getProjectStatus());
            statement.setInt(10, project.getCustomerId());
            statement.setInt(11, project.getArchitectId());
            statement.setInt(12, project.getContractorId());
            statement.setInt(13, project.getProjectManagerId());
            statement.setInt(14, project.getProjectId());
            statement.executeUpdate();
            System.out.println("Project updated successfully");
        }
    }

    // retrieve all projects that are overdue
    public List<Project> getOverdueProjects() throws SQLException {
        String sql = "SELECT * FROM Project WHERE STR_TO_DATE(deadline_date, '%Y-%m-%d') < CURDATE()";

        List<Project> overdueProjects = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Populate the Project object with data from the database
                Project project = new Project(
                        resultSet.getString("project_name"),
                        resultSet.getString("building_type"),
                        resultSet.getString("address"),
                        resultSet.getString("erf_number"),
                        resultSet.getDouble("total_fee"),
                        resultSet.getDouble("amount_paid"),
                        resultSet.getString("deadline_date"),
                        resultSet.getString("project_status"),
                        resultSet.getString("completion_date"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("architect_id"),
                        resultSet.getInt("contractor_id"),
                        resultSet.getInt("pm_id"));
                overdueProjects.add(project);
            }
        }

        return overdueProjects;
    }

    // Retrieve all projects that are not yet complete
    public List<Project> getNotYetComplete() throws SQLException {
        String sql = "SELECT * FROM Project WHERE project_status = 'In Progress'";

        List<Project> overdueProjects = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Populate the Project object with data from the database
                Project project = new Project(
                        resultSet.getString("project_name"),
                        resultSet.getString("building_type"),
                        resultSet.getString("address"),
                        resultSet.getString("erf_number"),
                        resultSet.getDouble("total_fee"),
                        resultSet.getDouble("amount_paid"),
                        resultSet.getString("deadline_date"),
                        resultSet.getString("project_status"),
                        resultSet.getString("completion_date"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("architect_id"),
                        resultSet.getInt("contractor_id"),
                        resultSet.getInt("pm_id"));
                project.setId(resultSet.getInt("project_Id"));
                overdueProjects.add(project);
            }
        }

        return overdueProjects;
    }

    // Delete a project
    public void deleteProject(Project projectIdToDelete) {
        String sql = "DELETE FROM project WHERE project_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, projectIdToDelete.getProjectId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
