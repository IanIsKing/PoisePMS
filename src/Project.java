// Projects class to create project objects

import java.sql.SQLException;
import java.time.LocalDate;

public class Project {
    // Attributes
    private int projectId;
    private String projectName;
    private String buildingType;
    private String physicalAddress;
    private String erfNumber;
    private double totalFee;
    private double amountPaid;
    private String deadlineDate;
    private String completionDate;
    private String projectStatus;
    private int customerId;
    private int architectId;
    private int contractorId;
    private int projectManagerId;

    // Constructor
    public Project(String projectName, String buildingType, String physicalAddress,
            String erfNumber, double totalFee, double amountPaid, String deadlineDate, String completionDate,
            String projectStatus, int customerId, int architectId, int contractorId, int projectManagerId) {
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.physicalAddress = physicalAddress;
        this.erfNumber = erfNumber;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
        this.deadlineDate = deadlineDate;
        this.completionDate = completionDate;
        this.projectStatus = projectStatus;
        this.customerId = customerId;
        this.architectId = architectId;
        this.contractorId = contractorId;
        this.projectManagerId = projectManagerId;
    }

    // Getters and Setters
    public Project getProject() {
        return this;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public String getErfNumber() {
        return erfNumber;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getArchitectId() {
        return architectId;
    }

    public int getContractorId() {
        return contractorId;
    }

    public int getProjectManagerId() {
        return projectManagerId;
    }

    public void setId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setBuildingType(String newBuildingType) {
        this.buildingType = newBuildingType;
    }

    public void setPhysicalAddress(String newPhysicalAddress) {
        this.physicalAddress = newPhysicalAddress;
    }

    public void setErfNumber(String newErfNumber) {
        this.erfNumber = newErfNumber;
    }

    public void setTotalFee(double newTotalFee) {
        this.totalFee = newTotalFee;
    }

    public void setAmountPaid(double newAmountPaid) {
        this.amountPaid = newAmountPaid;
    }

    public void setDeadlineDate(String newDeadlineDate) {
        this.deadlineDate = newDeadlineDate;
    }

    public void setCompletionDate(String newCompletionDate) {
        this.completionDate = newCompletionDate;
    }

    public void setProjectStatus(String newProjectStatus) {
        this.projectStatus = newProjectStatus;
    }

    public void setCustomerId(int newCustomerId) {
        this.customerId = newCustomerId;
    }

    public void setArchitectId(int newArchitectId) {
        this.architectId = newArchitectId;
    }

    public void setContractorId(int newContractorId) {
        this.contractorId = newContractorId;
    }

    public void setProjectManagerId(int newProjectManagerId) {
        this.projectManagerId = newProjectManagerId;
    }

    // Methods
    // View Project details
    public void viewProjectDetail() throws SQLException {
        System.out.println("Project ID: " + this.getProjectId());
        System.out.println("1 - Project Name: " + this.getProjectName());
        System.out.println("2 - Building Type: " + this.getBuildingType());
        System.out.println("3 - Address: " + this.getPhysicalAddress());
        System.out.println("4 - ERF Number: " + this.getErfNumber());
        System.out.println("5 - Total Fee: " + this.getTotalFee());
        System.out.println("6 - Total Paid: " + this.getAmountPaid());
        System.out.println("7 - Deadline: " + this.getDeadlineDate());

        System.out.println("8 - Project Status: " + this.getProjectStatus());

        if (this.getCompletionDate() == null) {
            System.out.println("9 - Completion Date: Not yet completed");
        } else {
            System.out.println("9 - Completion Date: " + this.getCompletionDate());
        }

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomerById(this.getCustomerId());
        System.out.println("10 - Customer: " + customer.getName());

        ArchitectDAO architectDAO = new ArchitectDAO();
        Architect architect = architectDAO.getArchitectById(this.getArchitectId());
        System.out.println("11 - Architect: " + architect.getName());

        ContractorDAO contractorDAO = new ContractorDAO();
        Contractor contractor = contractorDAO.getContractorById(this.getContractorId());
        System.out.println("12 - Contractor: " + contractor.getName());

        ProjectManagerDAO projectManagerDAO = new ProjectManagerDAO();
        ProjectManager projectManager = projectManagerDAO.getProjectManagerById(this.getProjectManagerId());
        System.out.println("13 - Project Manager: " + projectManager.getName());

        System.out.println("-------------------");
    }

    // Finalise project
    public void finaliseProject() {
        this.setProjectStatus("Completed");
        // set the completeion date to current date
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.toString();
        this.setCompletionDate(formattedDate);
    }

}
