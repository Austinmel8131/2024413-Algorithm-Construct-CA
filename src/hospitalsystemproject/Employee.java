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

    public Employee(String name, String employeeId, Department department, String gender) {
        this.name = name;
        this.employeeId = employeeId;
        this.gender = gender;
        this.department = department;
        if (department != null) department.addEmployee(this);
    }

    public String getName() { return name; }
    public String getEmployeeId() { return employeeId; }
    public String getGender() { return gender; }
    public Department getDepartment() { return department; }

    public void setDepartment(Department department) {
        if (this.department != null) this.department.removeEmployee(this);
        this.department = department;
        if (department != null) department.addEmployee(this);
    }
}
