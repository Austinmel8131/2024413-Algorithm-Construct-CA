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

            if (random.nextBoolean()) {
                ManagerType type = ManagerType.values()[random.nextInt(ManagerType.values().length)];
                Manager manager = new Manager(name, empId, dept, gender, Manager.ManagerType.valueOf(type.name()));
                dept.setManager(manager);
                employees.add(manager);
            } else {
                Employee emp = new Employee(name, empId, dept, gender);
                employees.add(emp);
            }
        }
        System.out.println(count + " random employees generated:");
        employees.stream().skip(employees.size() - count).forEach(System.out::println);
    }
}