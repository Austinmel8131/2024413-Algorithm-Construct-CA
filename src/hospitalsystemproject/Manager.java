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

    public enum ManagerType {
        CHIEF_PHYSICIAN("Chief Physician"),
        HEAD_NURSE("Head Nurse"),
        DEPARTMENT_ADMIN("Department Administrator");

        private final String displayName;
        ManagerType(String displayName) { this.displayName = displayName; }
        @Override
        public String toString() { return displayName; }
    }

    private ManagerType managerType;

    public Manager(String name, String employeeId, Department department, String gender, ManagerType managerType) {
        super(name, employeeId, department, gender);
        this.managerType = managerType;
    }

    public ManagerType getManagerType() { return managerType; }
    public void setManagerType(ManagerType managerType) { this.managerType = managerType; }

    @Override
    public String toCsv() {
        return super.toCsv().replace(getClass().getSimpleName(), managerType.toString());
    }

    @Override
    public String toString() {
        return super.toString() + " - " + managerType;
    }
}
