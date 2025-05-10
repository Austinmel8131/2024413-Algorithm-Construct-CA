/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystemproject;
/**
 *
 * @author macbook
 */

public class Manager extends Employee { 

    public enum ManagerType { // enums for managing managers on the menu
        CHIEF_PHYSICIAN("Chief Physician"),
        HEAD_NURSE("Head Nurse"),
        DEPARTMENT_ADMIN("Department Administrator");

        private final String displayName; // responsible for displaying manager type
        ManagerType(String displayName) { this.displayName = displayName; } // 
        @Override
        public String toString() { return displayName; } // converts entered number from enums menu string
    }

    private ManagerType managerType; // Manages manager's role type

    public Manager(String name, String employeeId, Department department, String gender, ManagerType managerType, HospitalSystem.EmploymentType employmentType) {
        super(name, employeeId, department, gender, employmentType); // calls for the employee deatils to be displayed
        this.managerType = managerType; // Assisgns the selected manager type to the main employee record
    }

    public ManagerType getManagerType() { return managerType; } // Get the manager type
    public void setManagerType(ManagerType managerType) { this.managerType = managerType; }// Set the employee type to the selected done.

   @Override
    public String toTxt() { // updates txt file with the new manager type
        return super.toTxt().replace(getClass().getSimpleName(), managerType.toString());
    }

    @Override
    public String toString() { // responsible for overiding the string conversion
        return super.toString() + " - " + managerType;
    }
}
