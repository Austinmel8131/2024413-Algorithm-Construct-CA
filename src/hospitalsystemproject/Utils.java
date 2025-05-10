/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystemproject;

import java.io.FileWriter; // responsible for importing file writer for recording data's 
import java.io.IOException; // responsible for importing IOexception for error handling
import java.util.List;// responsible for importing a list

/**
 * @author macbook
 */
public class Utils {// Method to write employee records to a txt file
    public static void writeToTXT(List<Employee> employees, String filename) { // write to employee file(.txt file)
        try (FileWriter writer = new FileWriter(filename)) { //this helps to open and write to txt file writer
            writer.write("Name,ID,Department,Gender,Role,EmploymentType\n"); // responsible for writing the headings of each column in the .txt file
            for (Employee e : employees) {
                writer.write(e.toTxt() + "\n"); // write every every data
            }
            System.out.println("Saved to " + filename); // saves the .txt file
        } catch (IOException e) { // manage every error 
            System.out.println("Error writing file: " + e.getMessage()); // prints out every error detected
        }
    }
}

