import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class App {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		System.out.println("Welcome to the Project Manager Application\n");

		// User input options
		int option;
		int person;

		// Main menu
		do {
			// Display menu graphics
			printMainMenu();
			// Get user input
			option = getUserInput();

			// Process user input
			switch (option) {
				case 1:
					// Add a new project
					addProject();
					break;
				case 2:
					// Update information about existing projects
					viewAllProjects();
					System.out.println("Please enter project name or project ID: ");
					String projectNameOrIdToUpdate = scanner.nextLine();
					Project project = viewProjectDetail(projectNameOrIdToUpdate);
					int projectId = project.getProjectId();
					System.out.println("Please select the option you would like to update: ");
					int optionToUpdate = getUserInput();
					if (optionToUpdate > 0 && optionToUpdate < 14) {
						System.out.println("Option selected: " + optionToUpdate + " on project " + projectId);
						updateProject(project, optionToUpdate);
					} else {
						System.out.println("Error! Not a valid command.\n");
					}
					break;
				case 3:
					// Finalise existing projects.
					finaliseProject();

					break;
				case 4:
					// See a list of projects that still need to be completed (have not been
					// finalised).
					notYetComplete();
					break;
				case 5:
					// See a list of projects that are past the due date.
					listOverdueProjects();
					break;
				case 6:
					// Find and select a project by entering either the project number or project
					// name.
					System.out.println("Please enter project name or project ID: ");
					String projectNameOrId = scanner.nextLine();
					viewProjectDetail(projectNameOrId);
					break;
				case 7:
					// list all projects
					viewAllProjects();
					break;
				case 8:
					// Add/view/update project manager/architect/contractor/customer
					printPersonSelector();
					person = getUserInput();
					printAddViewUpdateMenu(person);
					break;
				case 9:
					// Delete data about projects and people associated with them.
					viewAllProjects();
					deleteProject();
					break;
				case 0:
					// Exit
					System.out.println("Bye!");
					break;
				default:
					System.out.println("Error! Not a valid command.\n");
					break;
			}
		} while (option != 0);

	}

	// Main menu
	public static void printMainMenu() {
		System.out.println("1 - Add a new project");
		System.out.println("2 - Update information about existing projects");
		System.out.println("3 - Finalise existing projects.");
		System.out.println("4 - See a list of projects that still need to be completed (have not been finalised).");
		System.out.println("5 - See a list of projects that are past the due date.");
		System.out.println("6 - Find a project by entering either the project number or project name.");
		System.out.println("7 - List all projects");
		System.out.println("8 - Add/view/update project manager/architect/contractor/customer");
		System.out.println("9 - Delete a project.");
		System.out.println("0 - Exit\n");
	}

	// person selector menu
	public static void printPersonSelector() {
		System.out.println("Please select catagory of peron to Add/View/Edit:");
		System.out.println("1 - Project manager");
		System.out.println("2 - Architect");
		System.out.println("3 - Contractor");
		System.out.println("4 - Customer");
		System.out.println("0 - Exit\n");
	}

	// Add/view/update menu
	public static void printAddViewUpdateMenu(int person) {
		switch (person) {
			case 1:
				System.out.println("1 - Add a project manager");
				System.out.println("2 - View a project manager");
				System.out.println("3 - Update a project manager");
				System.out.println("0 - Exit\n");
				CRUDPeople(person, getUserInput());
				break;
			case 2:
				System.out.println("1 - Add an architect");
				System.out.println("2 - View an architect");
				System.out.println("3 - Update an architect");
				System.out.println("0 - Exit\n");
				CRUDPeople(person, getUserInput());
				break;
			case 3:
				System.out.println("1 - Add a contractor");
				System.out.println("2 - View a contractor");
				System.out.println("3 - Update a contractor");
				System.out.println("0 - Exit\n");
				CRUDPeople(person, getUserInput());
				break;
			case 4:
				System.out.println("1 - Add a customer");
				System.out.println("2 - View a customer");
				System.out.println("3 - Update a customer");
				System.out.println("0 - Exit\n");
				CRUDPeople(person, getUserInput());
				break;
			default:
				System.out.println("Error! Not a valid command.\n");
				break;
		}
	}

	// CRUD people menu
	public static void CRUDPeople(int person, int option) {
		try {
			switch (option) {
				// Adding a person
				case 1:
					System.out.println("Please enter Name: ");
					String name = scanner.nextLine();
					System.out.println("Please enter Email: ");
					String email = scanner.nextLine();
					System.out.println("Please enter Phone: ");
					String phone = scanner.nextLine();
					System.out.println("Please enter Address: ");
					String address = scanner.nextLine();
					switch (person) {
						case 1:
							ProjectManager projectManager = new ProjectManager(name, email, phone, address);
							ProjectManagerDAO projectManagerDAO = new ProjectManagerDAO();
							projectManagerDAO.createProjectManager(projectManager);
							break;
						case 2:
							Architect architect = new Architect(name, email, phone, address);
							ArchitectDAO architectDAO = new ArchitectDAO();
							architectDAO.createArchitect(architect);
							break;
						case 3:
							Contractor contractor = new Contractor(name, email, phone, address);
							ContractorDAO contractorDAO = new ContractorDAO();
							contractorDAO.createContractor(contractor);
							break;
						case 4:
							Customer customer = new Customer(name, email, phone, address);
							CustomerDAO customerDAO = new CustomerDAO();
							customerDAO.createCustomer(customer);
							break;
					}
					break;
				// Viewing a person
				case 2:
					System.out.println("Please enter Name of person to view details: ");
					String nameToView = scanner.nextLine();
					switch (person) {
						case 1:
							ProjectManagerDAO projectManagerDAO = new ProjectManagerDAO();
							try {
								ProjectManager projectManager = projectManagerDAO.getProjectManagerByName(nameToView);
								System.out.println(projectManager.getName());
								System.out.println(projectManager.getEmail());
								System.out.println(projectManager.getPhone());
								System.out.println(projectManager.getAddress());
								break;
							} catch (Exception e) {
								System.out.println("Sorry but there are no results for that name.");
								listAllProjectManagers();
								break;
							}
						case 2:
							ArchitectDAO architectDAO = new ArchitectDAO();
							try {
								Architect architect = architectDAO.getArchitectByName(nameToView);
								System.out.println(architect.getName());
								System.out.println(architect.getEmail());
								System.out.println(architect.getPhone());
								System.out.println(architect.getAddress());
								break;
							} catch (Exception e) {
								System.out.println("Sorry but there are no results for that name.");
								listAllArchitects();
								break;
							}
						case 3:
							ContractorDAO contractorDAO = new ContractorDAO();
							try {
								Contractor contractor = contractorDAO.getContractorByName(nameToView);
								System.out.println(contractor.getName());
								System.out.println(contractor.getEmail());
								System.out.println(contractor.getPhone());
								System.out.println(contractor.getAddress());
								break;
							} catch (Exception e) {
								System.out.println("Sorry but there are no results for that name.");
								listAllContractors();
								break;
							}
						case 4:
							CustomerDAO customerDAO = new CustomerDAO();
							try {
								Customer customer = customerDAO.getCustomerByName(nameToView);
								System.out.println(customer.getName());
								System.out.println(customer.getEmail());
								System.out.println(customer.getPhone());
								System.out.println(customer.getAddress());
								break;
							} catch (Exception e) {
								System.out.println("Sorry but there are no results for that name.");
								listAllCustomers();
								break;
							}
						default:
							System.out.println("Error! Not a valid command.\n");
							break;
					}
					break;
				// Updating a person
				case 3:
					System.out.println("Please enter Name of person to update details: ");
					String nameToUpdate = scanner.nextLine();
					switch (person) {
						case 1:
							ProjectManagerDAO projectManagerDAO = new ProjectManagerDAO();
							try {
								ProjectManager projectManager = projectManagerDAO.getProjectManagerByName(nameToUpdate);
								System.out.println("Please enter new Email: ");
								String newEmail = scanner.nextLine();
								System.out.println("Please enter new Phone: ");
								String newPhone = scanner.nextLine();
								System.out.println("Please enter new Address: ");
								String newAddress = scanner.nextLine();
								projectManager.setName(nameToUpdate);
								projectManager.setEmail(newEmail);
								projectManager.setPhone(newPhone);
								projectManager.setAddress(newAddress);
								projectManagerDAO.updateProjectManager(projectManager);
								break;
							} catch (Exception e) {
								System.out.println("Sorry but there are no results for that name.");
								listAllProjectManagers();
								break;
							}
						case 2:
							ArchitectDAO architectDAO = new ArchitectDAO();
							try {
								Architect architect = architectDAO.getArchitectByName(nameToUpdate);
								System.out.println("Please enter new Email: ");
								String newEmail = scanner.nextLine();
								System.out.println("Please enter new Phone: ");
								String newPhone = scanner.nextLine();
								System.out.println("Please enter new Address: ");
								String newAddress = scanner.nextLine();
								architect.setName(nameToUpdate);
								architect.setEmail(newEmail);
								architect.setPhone(newPhone);
								architect.setAddress(newAddress);
								architectDAO.updateArchitect(architect);
								break;
							} catch (Exception e) {
								System.out.println("Sorry but there are no results for that name.");
								listAllArchitects();
								break;
							}
						case 3:
							ContractorDAO contractorDAO = new ContractorDAO();
							try {
								Contractor contractor = contractorDAO.getContractorByName(nameToUpdate);
								System.out.println("Please enter new Email: ");
								String newEmail = scanner.nextLine();
								System.out.println("Please enter new Phone: ");
								String newPhone = scanner.nextLine();
								System.out.println("Please enter new Address: ");
								String newAddress = scanner.nextLine();
								contractor.setName(nameToUpdate);
								contractor.setEmail(newEmail);
								contractor.setPhone(newPhone);
								contractor.setAddress(newAddress);
								contractorDAO.updateContractor(contractor);
								break;
							} catch (Exception e) {
								System.out.println("Sorry but there are no results for that name.");
								listAllContractors();
								break;
							}
						case 4:
							CustomerDAO customerDAO = new CustomerDAO();
							try {
								Customer customer = customerDAO.getCustomerByName(nameToUpdate);
								System.out.println("Please enter new Email: ");
								String newEmail = scanner.nextLine();
								System.out.println("Please enter new Phone: ");
								String newPhone = scanner.nextLine();
								System.out.println("Please enter new Address: ");
								String newAddress = scanner.nextLine();
								customer.setName(nameToUpdate);
								customer.setEmail(newEmail);
								customer.setPhone(newPhone);
								customer.setAddress(newAddress);
								customerDAO.updateCustomer(customer);
								break;
							} catch (Exception e) {
								System.out.println("Sorry but there are no results for that name.");
								listAllCustomers();
								break;
							}
						default:
							System.out.println("Error! Not a valid command.\n");
							break;

					}
					break;
				default:
					System.out.println("Invalid option!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Get user input
	public static int getUserInput() {
		try {
			System.out.print("Enter a command: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			return option;
		} catch (Exception e) {
			System.err.println("An error occurred while getting user input: " + e.getMessage());
			return 0;
		}
	}

	// list all projects
	public static void listAllProjects() {
		System.out.println("Listing projects...");
		try {
			// Create an instance of ProjectDAO
			ProjectDAO projectDAO = new ProjectDAO();

			// Use the instance to get all projects
			List<Project> projects = projectDAO.getAllProjects();
			for (Project project : projects) {
				System.out.println(project.getProjectId() + " - " + project.getProjectName());
			}
			System.out.println("-------------------");
		} catch (SQLException e) {
			System.err.println("An error occurred while fetching projects: " + e.getMessage());
		}
	}

	// list all project managers
	public static void listAllProjectManagers() {
		System.out.println("Listing project managers...");
		try {
			// Create an instance of ProjectManagerDAO
			ProjectManagerDAO projectManagerDAO = new ProjectManagerDAO();

			// Use the instance to get all project managers
			List<ProjectManager> projectManagers = projectManagerDAO.getAllProjectManagers();
			for (ProjectManager projectManager : projectManagers) {
				System.out.println(projectManager.getId() + " - " + projectManager.getName());
			}
		} catch (SQLException e) {
			System.err.println("An error occurred while fetching project managers: " + e.getMessage());
		}
	}

	// list all architects
	public static void listAllArchitects() {
		System.out.println("Listing architects...");
		try {
			// Create an instance of ArchitectDAO
			ArchitectDAO architectDAO = new ArchitectDAO();

			// Use the instance to get all architects
			List<Architect> architects = architectDAO.getAllArchitects();
			for (Architect architect : architects) {
				System.out.println(architect.getId() + " - " + architect.getName());
			}
		} catch (SQLException e) {
			System.err.println("An error occurred while fetching architects: " + e.getMessage());
		}
	}

	// list all contractors
	public static void listAllContractors() {
		System.out.println("Listing contractors...");
		try {
			// Create an instance of ContractorDAO
			ContractorDAO contractorDAO = new ContractorDAO();

			// Use the instance to get all contractors
			List<Contractor> contractors = contractorDAO.getAllContractors();
			for (Contractor contractor : contractors) {
				System.out.println(contractor.getId() + " - " + contractor.getName());
			}
		} catch (SQLException e) {
			System.err.println("An error occurred while fetching contractors: " + e.getMessage());
		}
	}

	// list all customers
	public static void listAllCustomers() {
		System.out.println("Listing customers...");
		try {
			// Create an instance of CustomerDAO
			CustomerDAO customerDAO = new CustomerDAO();

			// Use the instance to get all customers
			List<Customer> customers = customerDAO.getAllCustomers();
			for (Customer customer : customers) {
				System.out.println(customer.getId() + " - " + customer.getName());
			}
		} catch (SQLException e) {
			System.err.println("An error occurred while fetching customers: " + e.getMessage());
		}
	}

	// Add a project
	private static void addProject() {
		try {
			// Get project details from user
			System.out.println("Please enter project name: ");
			String projectName = scanner.nextLine();
			System.out.println("Please enter building type: ");
			String buildingType = scanner.nextLine();
			System.out.println("Please enter physical address: ");
			String physicalAddress = scanner.nextLine();
			System.out.println("Please enter ERF number: ");
			String erfNumber = scanner.nextLine();
			System.out.println("Please enter total fee: ");
			double totalFee = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Please enter amount paid: ");
			double amountPaid = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Please enter deadline date (YYYY-MM-DD): ");
			String deadlineDate = scanner.nextLine();
			String completionDate = null;
			String projectStatus = "In Progress";
			System.out.println("Please enter customer ID: ");
			listAllCustomers();
			int customerId = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Please enter architect ID: ");
			listAllArchitects();
			int architectId = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Please enter contractor ID: ");
			listAllContractors();
			int contractorId = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Please enter project manager ID: ");
			listAllProjectManagers();
			int projectManagerId = scanner.nextInt();
			scanner.nextLine();

			// Create an instance of Project
			Project project = new Project(projectName, buildingType, physicalAddress, erfNumber,
					totalFee, amountPaid, deadlineDate, completionDate, projectStatus, customerId, architectId,
					contractorId, projectManagerId);

			// Create an instance of ProjectDAO
			ProjectDAO projectDAO = new ProjectDAO();

			// Use the instance of DAO to add the project to the db
			projectDAO.addProject(project);

			System.out.println("Project added successfully");
		} catch (SQLException e) {
			System.err.println("An error occurred while adding project: " + e.getMessage());
		}
	}

	// View all the projects
	private static void viewAllProjects() {
		System.out.println("Listing projects...");
		try {
			// Create an instance of ProjectDAO
			ProjectDAO projectDAO = new ProjectDAO();

			// Use the instance to get all projects and show the user
			List<Project> projects = projectDAO.getAllProjects();
			for (Project project : projects) {
				System.out.println(project.getProjectId() + " - " + project.getProjectName());
			}
			System.out.println("-------------------");
		} catch (SQLException e) {
			System.err.println("An error occurred while fetching projects: " + e.getMessage());
		}
	}

	// View a projects details
	public static Project viewProjectDetail(String projectNameOrId) {
		try {
			// Create an instance of ProjectDAO
			ProjectDAO projectDAO = new ProjectDAO();

			// Use the instance to get the project by name or ID
			Project project = projectDAO.getProjectByNameOrId(projectNameOrId);
			if (project == null) {
				System.out.println("Sorry but there are no results for that name or ID.");
				listAllProjects();
				return null;
			}
			project.viewProjectDetail();
			return project;

		} catch (SQLException e) {
			System.err.println("An error occurred while fetching project details: " + e.getMessage());
		}
		return null;
	}

	// Update a project
	public static void updateProject(Project project, int optionToUpdate) throws SQLException {
		ProjectDAO projectDAO = new ProjectDAO();
		switch (optionToUpdate) {
			case 1:
				System.out.println("Please enter new project name: ");
				String newProjectName = scanner.nextLine();
				project.setProjectName(newProjectName);
				projectDAO.updateProject(project);
				break;
			case 2:
				System.out.println("Please enter new building type: ");
				String newBuildingType = scanner.nextLine();
				project.setBuildingType(newBuildingType);
				projectDAO.updateProject(project);
				break;
			case 3:
				System.out.println("Please enter new physical address: ");
				String newPhysicalAddress = scanner.nextLine();
				project.setPhysicalAddress(newPhysicalAddress);
				projectDAO.updateProject(project);
				break;
			case 4:
				System.out.println("Please enter new ERF number: ");
				String newErfNumber = scanner.nextLine();
				project.setErfNumber(newErfNumber);
				projectDAO.updateProject(project);
				break;
			case 5:
				System.out.println("Please enter new total fee: ");
				double newTotalFee = scanner.nextDouble();
				scanner.nextLine();
				project.setTotalFee(newTotalFee);
				projectDAO.updateProject(project);
				break;
			case 6:
				System.out.println("Please enter new amount paid: ");
				double newAmountPaid = scanner.nextDouble();
				scanner.nextLine();
				project.setAmountPaid(newAmountPaid);
				projectDAO.updateProject(project);
				break;
			case 7:
				System.out.println("Please enter new deadline date (YYYY-MM-DD): ");
				String newDeadlineDate = scanner.nextLine();
				project.setDeadlineDate(newDeadlineDate);
				projectDAO.updateProject(project);
				break;

			case 8:
				System.out.println("Please enter new project status: 1 - In Progress, 2 - Complete, 3 - On Hold");
				int newProjectStatus = scanner.nextInt();
				scanner.nextLine();
				String updatedStatus = null;
				switch (newProjectStatus) {
					case 1:
						updatedStatus = "In Progress";
						break;
					case 2:
						updatedStatus = "Completed";
						break;
					case 3:
						updatedStatus = "On Hold";
						break;
					default:
						System.out.println("Error! Not a valid command.\n");
						break;
				}
				project.setProjectStatus(updatedStatus);
				projectDAO.updateProject(project);
				break;
			case 9:
				System.out.println("Please enter new completion date (YYYY-MM-DD): ");
				String newCompletionDate = scanner.nextLine();
				project.setCompletionDate(newCompletionDate);
				projectDAO.updateProject(project);
				break;
			case 10:
				System.out.println("Please enter new customer ID: ");
				listAllCustomers();
				int newCustomerId = scanner.nextInt();
				scanner.nextLine();
				project.setCustomerId(newCustomerId);
				projectDAO.updateProject(project);
				break;
			case 11:
				System.out.println("Please enter new architect ID: ");
				listAllArchitects();
				int newArchitectId = scanner.nextInt();
				scanner.nextLine();
				project.setArchitectId(newArchitectId);
				projectDAO.updateProject(project);
				break;
			case 12:
				System.out.println("Please enter new contractor ID: ");
				listAllContractors();
				int newContractorId = scanner.nextInt();
				scanner.nextLine();
				project.setContractorId(newContractorId);
				projectDAO.updateProject(project);
				break;
			case 13:
				System.out.println("Please enter new project manager ID: ");
				listAllProjectManagers();
				int newProjectManagerId = scanner.nextInt();
				scanner.nextLine();
				project.setProjectManagerId(newProjectManagerId);
				projectDAO.updateProject(project);
				break;
		}
	}

	// List overdue projects
	public static void listOverdueProjects() {
		System.out.println("Listing overdue projects...");
		try {
			// Create an instance of ProjectDAO
			ProjectDAO projectDAO = new ProjectDAO();

			// Use the instance to get all overdue projects
			List<Project> overdueProjects = projectDAO.getOverdueProjects();
			for (Project project : overdueProjects) {
				System.out.println(project.getProjectName() + " - Deadline Date: " + project.getDeadlineDate());
			}
			System.out.println("-------------------");
		} catch (SQLException e) {
			System.err.println("An error occurred while fetching overdue projects: " + e.getMessage());
		}
	}

	// List projects not yet complete
	public static void notYetComplete() {
		System.out.println("Listing projects not yet complete...");
		try {
			// Create an instance of ProjectDAO
			ProjectDAO projectDAO = new ProjectDAO();

			// Use the instance to get all overdue projects
			List<Project> notYetComplete = projectDAO.getNotYetComplete();
			for (Project project : notYetComplete) {
				System.out.println(project.getProjectId() + " - " + project.getProjectName() + " - Deadline Date: "
						+ project.getDeadlineDate());
			}
			System.out.println("-------------------");
		} catch (SQLException e) {
			System.err.println("An error occurred while fetching projects not yet complete: " + e.getMessage());
		}
	}

	// Finalise a project (this is done by setting the completion date to the
	// current and the project status to complete)
	public static void finaliseProject() throws SQLException {
		System.out.println("Please select a project ID or name to be finilased :");
		notYetComplete();
		String toBeFinalised = scanner.nextLine();
		Project projectToBeFinalised = viewProjectDetail(toBeFinalised);
		System.out.println(projectToBeFinalised.getProjectName());
		System.out.println("Do you want to proceed? (yes/no)");
		String userInput = scanner.nextLine();
		if (userInput.equalsIgnoreCase("yes")) {
			projectToBeFinalised.finaliseProject();
			ProjectDAO projectDAO = new ProjectDAO();
			projectDAO.updateProject(projectToBeFinalised);
		} else {
			System.out.println("Cancelled!");
		}

	}

	// Delete a project
	public static void deleteProject() {
		System.out.println("Please enter project name or project ID: ");
		String projectNameOrIdToDelete = scanner.nextLine();
		Project projectToDelete = viewProjectDetail(projectNameOrIdToDelete);
		int projectIdToDelete = projectToDelete.getProjectId();
		String projectNameToDelete = projectToDelete.getProjectName();
		System.out.println("Are you sure you want to delete project " + projectIdToDelete + " - "
				+ projectNameToDelete + "? 1 to continue");
		if (getUserInput() == 1) {
			ProjectDAO projectDAO = new ProjectDAO();
			projectDAO.deleteProject(projectToDelete);
			System.out.println("Project deleted.");
		} else {
			System.out.println("Project not deleted.");
		}
	}

}
