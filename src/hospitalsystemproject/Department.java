/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystemproject;

/**
 * @author macbook
 */
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private List<Employee> employees;
    private Manager manager;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Employee> getEmployees() { return employees; }
    public Manager getManager() { return manager; }

    public void setManager(Manager manager) {
        this.manager = manager;
        if (!employees.contains(manager)) {
            employees.add(manager);
        }
    }

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
        }
    }

    public boolean removeEmployee(Employee employee) {
        return employees.remove(employee);
    }

    @Override
    public String toString() {
        return name + " (" + employees.size() + " staff)";
    }

}