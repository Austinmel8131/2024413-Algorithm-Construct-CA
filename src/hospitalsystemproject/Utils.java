/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystemproject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author macbook
 */
public class Utils {// Method to write users to a CSV file
    public static void writeToCSV(List<Employee> employees, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Name,ID,Department,Gender,Role\n");
            for (Employee e : employees) {
                writer.write(e.toCsv() + "\n");
            }
            System.out.println("Saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}

