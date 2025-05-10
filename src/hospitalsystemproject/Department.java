/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystemproject;

/**
 * @author macbook
 */
import java.util.ArrayList; // imports arraylist
import java.util.List; // imports list

public class Department { // class for department
    private String name; // declaring variable name for departements
    private List<Employee> employees; // manages the list of employee
    private Manager manager; //manages  employee manager

    public Department(String name) { //method for department
        this.name = name; // assigns department name
        this.employees = new ArrayList<>(); // employee list
    }

    public String getName() { return name; } // get department name
    public List<Employee> getEmployees() { return employees; } // get employee list
    public Manager getManager() { return manager; }// get manager tppe

    public void setManager(Manager manager) { // set manager selected
        this.manager = manager; // assignes maanager selected.
        if (!employees.contains(manager)) { // compare employee list with selected manager
            employees.add(manager);// add manager type to employee list
        }
    }

    public void addEmployee(Employee employee) { // method for adding employee
        if (!employees.contains(employee)) { // checkes to avoid duplicated employee record
            employees.add(employee); // adds employee to list
        }
    }

    public boolean removeEmployee(Employee employee) { // remove employee
        return employees.remove(employee); // comfirmation message of employee removed from employee list
    }

    @Override
    public String toString() { // set the string for departmemt 
        return name + " (" + employees.size() + " staff)";
    }

}