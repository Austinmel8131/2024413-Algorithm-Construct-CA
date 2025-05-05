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
}