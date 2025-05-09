/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalsystemproject;
/**
 * @author macbook
 */
import java.util.*;

public class HospitalSystem {
    private BubbleSort<Employee> employees = new BubbleSort<>();
    private Map<DepartmentType, Department> departmentMap = new EnumMap<>(DepartmentType.class);
    private Random random = new Random();

    public enum MenuOption {
        SORT(1), SEARCH(2), ADD(3), GENERATE_RANDOM(4), EXIT(5);
        private final int value;
        MenuOption(int value) { this.value = value; }
        public int getValue() { return value; }
        public static MenuOption fromInt(int input) {
            for (MenuOption option : values()) {
                if (option.getValue() == input) return option;
            }
            return null;
        }
    }

    public enum DepartmentType {
        EMERGENCY("Emergency Department"),
        SURGERY("Surgery Department"),
        PEDIATRICS("Pediatrics Department"),
        RADIOLOGY("Radiology Department"),
        CARDIOLOGY("Cardiology Department");

        private final String displayName;
        DepartmentType(String displayName) { this.displayName = displayName; }
        public String getDisplayName() { return displayName; }
        public static DepartmentType fromInt(int input) {
            return values()[input - 1];
        }
    }
    public enum EmploymentType {
        PERMANENT,
        CONTRACT
    }
    public enum ManagerType {
        CHIEF_PHYSICIAN("Chief Physician"),
        HEAD_NURSE("Head Nurse"),
        DEPARTMENT_ADMIN("Department Administrator");

        private final String displayName;
        ManagerType(String displayName) { this.displayName = displayName; }
        public String getDisplayName() { return displayName; }
        public static ManagerType fromInt(int input) {
            return values()[input - 1];
        }
    }
    public HospitalSystem() {
        initializeDepartments();
    }

    private void initializeDepartments() {
        for (DepartmentType type : DepartmentType.values()) {
            departmentMap.put(type, new Department(type.getDisplayName()));
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> searchEmployees(String name) {
        List<Employee> results = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getName().equalsIgnoreCase(name)) {
                results.add(emp);
            }
        }
        return results;
    }

    public void sortEmployees() {
        employees.recursiveBubbleSort(employees.size());
    }

    public void generateRandomEmployees(int count) {
        String[] firstNames = {"John", "Jane", "Michael", "Emily", "David", "Sarah"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller"};
        String[] genders = {"Male", "Female", "Other"};

        for (int i = 0; i < count; i++) {
            String name = firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
            String empId = "EMP" + (1000 + employees.size());
            String gender = genders[random.nextInt(genders.length)];
            DepartmentType deptType = DepartmentType.values()[random.nextInt(DepartmentType.values().length)];
            Department dept = departmentMap.get(deptType);
            EmploymentType empType = random.nextBoolean() ? EmploymentType.PERMANENT : EmploymentType.CONTRACT;

            if (random.nextBoolean()) {
                Manager.ManagerType type = Manager.ManagerType.values()[random.nextInt(Manager.ManagerType.values().length)];
                Manager manager = new Manager(name, empId, dept, gender, type, empType);
                dept.setManager(manager);
                employees.add(manager);
                System.out.println("Added: " + manager.toString());
            } else {
                Employee emp = new Employee(name, empId, dept, gender, empType);
                employees.add(emp);
                System.out.println("Added: " + emp.toString());
            }
        }
        System.out.println(count + " random employees generated:");
        employees.stream().skip(employees.size() - count).forEach(System.out::println);
    }
    public static void main(String[] args) {
        HospitalSystem hospitalSystem = new HospitalSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Hospital System Menu ---");
            for (MenuOption option : MenuOption.values()) {
                System.out.println(option.getValue() + ". " + option.name());
            }
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            MenuOption selected = MenuOption.fromInt(choice);
            if (selected == null) {
                System.out.println("Invalid choice. Please select a valid option.");
                continue;
            }

            switch (selected) {
                case SORT:
                    hospitalSystem.sortEmployees();
                    System.out.println("First 20 sorted names:");
                    hospitalSystem.employees.stream().limit(20).forEach(System.out::println);
                    break;
                case SEARCH:
                    System.out.print("Enter name to search: ");
                    String name = scanner.nextLine();
                    List<Employee> results = hospitalSystem.searchEmployees(name);
                    if (results.isEmpty()) {
                        System.out.println("No matches found for '" + name + "'.");
                    } else {
                        System.out.println("Search results for '" + name + "':");
                        results.forEach(System.out::println);
                    }
                    break;
                case ADD:
                    System.out.print("Enter name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter employee ID: ");
                    String empId = scanner.nextLine();
                    System.out.print("Enter gender (Male/Female/Other): ");
                    String gender = scanner.nextLine();
                    
                    System.out.print("Enter employment type (1. Permanent, 2. Contract): ");
                    int empTypeInput = Integer.parseInt(scanner.nextLine());
                    EmploymentType empType = (empTypeInput == 1) ? EmploymentType.PERMANENT : EmploymentType.CONTRACT;

                    System.out.println("Select Manager Type:");
                    for (int i = 0; i < ManagerType.values().length; i++) {
                        System.out.println((i + 1) + ". " + ManagerType.values()[i].getDisplayName());
                    }
                    int mgrChoice;
                    try {
                        mgrChoice = Integer.parseInt(scanner.nextLine());
                        if (mgrChoice < 1 || mgrChoice > ManagerType.values().length) throw new Exception();
                    } catch (Exception e) {
                        System.out.println("Invalid manager type selection.");
                        continue;
                    }
                    ManagerType mgrTypeEnum = ManagerType.fromInt(mgrChoice);
                    Manager.ManagerType mgrType = Manager.ManagerType.valueOf(mgrTypeEnum.name());

                    System.out.println("Select Department:");
                    for (int i = 0; i < DepartmentType.values().length; i++) {
                        System.out.println((i + 1) + ". " + DepartmentType.values()[i].getDisplayName());
                    }
                    int deptChoice;
                    try {
                        deptChoice = Integer.parseInt(scanner.nextLine());
                        if (deptChoice < 1 || deptChoice > DepartmentType.values().length) throw new Exception();
                    } catch (Exception e) {
                        System.out.println("Invalid department selection.");
                        continue;
                    }
                    DepartmentType deptType = DepartmentType.fromInt(deptChoice);
                    Department dept = hospitalSystem.departmentMap.get(deptType);

                    Manager manager = new Manager(newName, empId, dept, gender, mgrType, empType);
                    hospitalSystem.addEmployee(manager);
                    dept.setManager(manager);
                    System.out.println(newName + " has been added as " + mgrType + " to " + dept.getName());
                    break;
                    case GENERATE_RANDOM:
                    System.out.print("How many random employees? ");
                    int count = Integer.parseInt(scanner.nextLine());
                    hospitalSystem.generateRandomEmployees(count);
                    Utils.writeToTXT(hospitalSystem.employees, "Applicants_Form.txt");
                    break;
                case EXIT:
                    System.out.println("Exiting application.");
                    return;
//               
            }

        }
    }
}