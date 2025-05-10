/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalsystemproject;
/**
 * @author macbook
 */
import java.util.*; // import for java utility for handling list, arraylist ......

// Hospital system class for managing employee department manager type with a user interactive menu for a better user experience
public class HospitalSystem {
    private BubbleSort<Employee> employees = new BubbleSort<>(); // sorting algorithm for sorting rmployee record
    private Map<DepartmentType, Department> departmentMap = new EnumMap<>(DepartmentType.class); // responsible for matching selected department to the right department
    private Random random = new Random();// responsible for generating random employee data 

    //Enums for managing menu options to be more interactive
    public enum MenuOption {
        SORT(1), SEARCH(2), ADD(3), GENERATE_RANDOM(4), EXIT(5);
        private final int value; // responsible fo assisgning number to represents menu options
        MenuOption(int value) { this.value = value; }
        public int getValue() { return value; }
        public static MenuOption fromInt(int input) { // assigns int enterd from the menu option to the right menu option 
            for (MenuOption option : values()) {
                if (option.getValue() == input) return option;
            }
            return null;
        }
    }
    //Enums for managing department type on the menu
    public enum DepartmentType {
        EMERGENCY("Emergency Department"),
        SURGERY("Surgery Department"),
        PEDIATRICS("Pediatrics Department"),
        RADIOLOGY("Radiology Department"),
        CARDIOLOGY("Cardiology Department");

        private final String displayName; // displays the department type after it has been selcted
        DepartmentType(String displayName) { this.displayName = displayName; }
        public String getDisplayName() { return displayName; }
        public static DepartmentType fromInt(int input) {
            return values()[input - 1]; // manages the indexing of the department type on the menu options
        }
    }
    public enum EmploymentType { // Enums for managing employment type on the menu option
        PERMANENT,
        CONTRACT
    }
    public enum ManagerType {  // Enums for managing management type on the menu option
        CHIEF_PHYSICIAN("Chief Physician"),
        HEAD_NURSE("Head Nurse"),
        DEPARTMENT_ADMIN("Department Administrator");

        private final String displayName;// displays the manager type type after it has been selcted
        ManagerType(String displayName) { this.displayName = displayName; }
        public String getDisplayName() { return displayName; }
        public static ManagerType fromInt(int input) {
            return values()[input - 1];  // manages the indexing of the department type on the menu options
        }
    }
    public HospitalSystem() { 
        initializeDepartments();// responsible for initializing the deaprtment with the system 
    }

    private void initializeDepartments() { // responsible for assigning the selected department from the menu to the right department
        for (DepartmentType type : DepartmentType.values()) { 
            departmentMap.put(type, new Department(type.getDisplayName()));
        }
    }

    public void addEmployee(Employee employee) { // manages the adding of employee from the menu
        employees.add(employee);
    }

    public List<Employee> searchEmployees(String name) { // manages the searching of employee record from the menu option with the entered name 
        List<Employee> results = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getName().equalsIgnoreCase(name)) {
                results.add(emp);
            }
        }
        return results;
    }

    public void sortEmployees() { // manages the sorting of employee record from the menu option 
        employees.recursiveBubbleSort(employees.size());
    }
    // responsible for the generating of a given number of empoyee manager type, department and employment ttpe and them to the employee record
    public void generateRandomEmployees(int count) {
        String[] firstNames = {"John", "Jane", "Michael", "Emily", "David", "Sarah"}; // name of random firstnames for the data to be generated fromm
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller"}; // name of random last names for the data to be generated fromm
        String[] genders = {"Male", "Female", "Other"}; // name of gender type to generate from

        for (int i = 0; i < count; i++) { // responsible for generating multiple employee records 
            String name = firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
            String empId = "EMP" + (1000 + employees.size()); // manages the assigning of empoyment id to the generated data
            String gender = genders[random.nextInt(genders.length)];
            DepartmentType deptType = DepartmentType.values()[random.nextInt(DepartmentType.values().length)];
            Department dept = departmentMap.get(deptType); // get department and randomly selct deparmtment
            EmploymentType empType = random.nextBoolean() ? EmploymentType.PERMANENT : EmploymentType.CONTRACT;

            if (random.nextBoolean()) {
                Manager.ManagerType type = Manager.ManagerType.values()[random.nextInt(Manager.ManagerType.values().length)];
                Manager manager = new Manager(name, empId, dept, gender, type, empType);
                dept.setManager(manager);
                employees.add(manager); // add manager type to employee record
                System.out.println("Added: " + manager.toString());
            } else {
                Employee emp = new Employee(name, empId, dept, gender, empType);
                employees.add(emp);
                System.out.println("Added: " + emp.toString());
            }
        }
        System.out.println(count + " random employees generated:"); // prints out the name of every every records generated
        employees.stream().skip(employees.size() - count).forEach(System.out::println);
    }
    
    // System main responsble for displaying an interactive text user menu for the hospital system
    public static void main(String[] args) {
        HospitalSystem hospitalSystem = new HospitalSystem();
        Scanner scanner = new Scanner(System.in); // a scanner that reads users input

        while (true) { // a loop to guide users to enter the rigth input
            System.out.println("\n--- Hospital System Menu ---"); // Print out the heading of the menu
            for (MenuOption option : MenuOption.values()) { // list all options n the menu
                System.out.println(option.getValue() + ". " + option.name()); 
            }
            System.out.print("Enter your choice: "); //isplays a prompt for user to enter input

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine()); // read and parse user input
            } catch (NumberFormatException e) { // catch errors from entering the wrong input
                System.out.println("Invalid input. Please enter a number."); // prompts users that they have entered the wrong input
                continue; // displays menu options
            }

            MenuOption selected = MenuOption.fromInt(choice); // get selected menu option from user input
            if (selected == null) {
                System.out.println("Invalid choice. Please select a valid option."); // prompts user that they have entered the wrong input
                continue; //displays menu options
            }

            switch (selected) { //  carry out the selected action from the menu
                case SORT: // handles the sorting of employees
                    hospitalSystem.sortEmployees(); // sort employee 
                    System.out.println("First 20 sorted names:"); // isplays the name the name of the sorted employeees
                    hospitalSystem.employees.stream().limit(20).forEach(System.out::println);
                    break;
                case SEARCH:// handles the searching of employees from the record
                    System.out.print("Enter name to search: "); // prompt the user to enter the name to search
                    String name = scanner.nextLine();// reads input the the keybpard
                    List<Employee> results = hospitalSystem.searchEmployees(name); // list the name of the sorted employee
                    if (results.isEmpty()) {// confirms if there is a match
                        System.out.println("No matches found for '" + name + "'."); // displays a prompt, that there is no match
                    } else {
                        System.out.println("Search results for '" + name + "':"); // dispalys the name of the search results
                        results.forEach(System.out::println);
                    }
                    break;
                case ADD: // Handles the adding of employees
                    System.out.print("Enter name: "); // prompt users to enter name and other details
                    String newName = scanner.nextLine();
                    System.out.print("Enter employee ID: ");
                    String empId = scanner.nextLine();
                    System.out.print("Enter gender (Male/Female/Other): "); // prompt the users to enter the gender type
                    String gender = scanner.nextLine();
                    
                    System.out.print("Enter employment type (1. Permanent, 2. Contract): "); // prompt users to select employment type
                    int empTypeInput = Integer.parseInt(scanner.nextLine()); // reads employment type entered from the option
                    EmploymentType empType = (empTypeInput == 1) ? EmploymentType.PERMANENT : EmploymentType.CONTRACT;

                    System.out.println("Select Manager Type:"); // promt users to select manager type 
                    for (int i = 0; i < ManagerType.values().length; i++) { // reads manager type entered from the option
                        System.out.println((i + 1) + ". " + ManagerType.values()[i].getDisplayName());
                    }
                    int mgrChoice;
                    try {
                        mgrChoice = Integer.parseInt(scanner.nextLine());
                        if (mgrChoice < 1 || mgrChoice > ManagerType.values().length) throw new Exception();
                    } catch (Exception e) {// manages error entered
                        System.out.println("Invalid manager type selection."); // prompt user that they have entered the wrong input
                        continue;
                    }
                    ManagerType mgrTypeEnum = ManagerType.fromInt(mgrChoice);// handles the int of manager type
                    Manager.ManagerType mgrType = Manager.ManagerType.valueOf(mgrTypeEnum.name());

                    System.out.println("Select Department:"); // prompt users to select department
                    for (int i = 0; i < DepartmentType.values().length; i++) { 
                        System.out.println((i + 1) + ". " + DepartmentType.values()[i].getDisplayName()); // displays the options for manager type
                    }
                    int deptChoice;
                    try {
                        deptChoice = Integer.parseInt(scanner.nextLine());
                        if (deptChoice < 1 || deptChoice > DepartmentType.values().length) throw new Exception();
                    } catch (Exception e) { // handles error
                        System.out.println("Invalid department selection."); // prompt the users that they have entered the wrong input
                        continue; // display this menu
                    }
                    DepartmentType deptType = DepartmentType.fromInt(deptChoice);
                    Department dept = hospitalSystem.departmentMap.get(deptType);

                    Manager manager = new Manager(newName, empId, dept, gender, mgrType, empType);
                    hospitalSystem.addEmployee(manager);
                    dept.setManager(manager);
                    System.out.println(newName + " has been added as " + mgrType + " to " + dept.getName());
                    break;
                    case GENERATE_RANDOM: // handles the generating of random employee
                    System.out.print("How many random employees? "); // prompt the users to enter the number of employee they want to generate
                    int count = Integer.parseInt(scanner.nextLine()); // reads the number of inputed from the keyboard
                    hospitalSystem.generateRandomEmployees(count);
                    Utils.writeToTXT(hospitalSystem.employees, "Applicants_Form.txt"); // write the list of employeegenerated the .txt file
                    break;
                case EXIT: // handles exiting of the ssytem
                    System.out.println("Exiting application."); // prompt user that the program has been exited
                    return;
//               
            }

        }
    }
}