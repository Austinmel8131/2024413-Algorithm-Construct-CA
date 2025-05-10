/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystemproject;
/**
 * @author macbook
 */


    public class Employee implements Comparable<Employee> { // class for employee
    private String name, employeeId, gender; // deeclaring variables
        private Department department; // decides what department they belongs to 
    private HospitalSystem.EmploymentType employmentType; // decides what employment type they have.

    public Employee(String name, String employeeId, Department department, String gender, HospitalSystem.EmploymentType employmentType) {// delaring what tio be added to emloyee record
        this.name = name; // holds name of employee
        this.employeeId = employeeId; // holds employee id
        this.gender = gender; // holds employee gender
        this.department = department; // holds employee department
        this.employmentType = employmentType; // holds employee employment type
        if (department != null) department.addEmployee(this); // adds a department if departmemnt wasn'st selected
    }

    public String getName() { return name; } // gets employee name
    public String getEmployeeId() { return employeeId; } // get employee id
    public String getGender() { return gender; } // gets employee gender
    public Department getDepartment() { return department; } // get employee department
    public HospitalSystem.EmploymentType getEmploymentType() { return employmentType; } // get employee employement type


    public void setDepartment(Department department) { // manages department if left empty
        if (this.department != null) this.department.removeEmployee(this); // remove employee record is department is not selected
        this.department = department; // holds new department selected
        if (department != null) department.addEmployee(this); // add selected department to department
    }
    public String toTxt() { // write to .txt file with all of the below data
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
    public int compareTo(Employee other) { // compares employe reocrd from having same name on records
        return name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object o) { // compares employe reocrd from having same employeeID on records
        return (o instanceof Employee e) && employeeId.equals(e.employeeId);
    }

    @Override
    public int hashCode() { // manages the numbering of employee ID
        return employeeId.hashCode();
    }

    @Override
    public String toString() { // set the string and arranges how they will be displayed.
        return name + " (" + employeeId + ", " + gender + ", " + employmentType + ") - " + (department != null ? department.getName() : "No Dept");
    }

}
