/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystemproject;
/**
 * @author macbook
 */
import java.util.Objects;

    public class Employee implements Comparable<Employee> {
    private String name, employeeId, gender;
        private Department department;
    private HospitalSystem.EmploymentType employmentType;

    public Employee(String name, String employeeId, Department department, String gender, HospitalSystem.EmploymentType employmentType) {
        this.name = name;
        this.employeeId = employeeId;
        this.gender = gender;
        this.department = department;
        this.employmentType = employmentType;
        if (department != null) department.addEmployee(this);
    }

    public String getName() { return name; }
    public String getEmployeeId() { return employeeId; }
    public String getGender() { return gender; }
    public Department getDepartment() { return department; }
    public HospitalSystem.EmploymentType getEmploymentType() { return employmentType; }


    public void setDepartment(Department department) {
        if (this.department != null) this.department.removeEmployee(this);
        this.department = department;
        if (department != null) department.addEmployee(this);
    }
    public String toTxt() {
        return String.join(",",
                name,
                employeeId,
                department != null ? department.getName() : "No Dept",
                gender,
                getClass().getSimpleName(),
                employmentType.name()
        );
    }
     @Override
    public int compareTo(Employee other) {
        return name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Employee e) && employeeId.equals(e.employeeId);
    }

    @Override
    public int hashCode() {
        return employeeId.hashCode();
    }

    @Override
    public String toString() {
        return name + " (" + employeeId + ", " + gender + ", " + employmentType + ") - " + (department != null ? department.getName() : "No Dept");
    }

}
