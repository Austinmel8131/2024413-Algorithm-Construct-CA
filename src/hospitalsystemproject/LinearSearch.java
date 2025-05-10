/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalsystemproject;


import java.util.ArrayList; // responsible for importing ArrayList
/**
 *
 * @author macbook
 */
// manages the searching using linear search or binary search
public class LinearSearch<ElemType> extends ArrayList<ElemType> { 

     public int binarySearch(ElemType key, int low, int high) { //Method for recursive Binary search
        if (high < low) return -1; // 
        
        int mid = (low + high) / 2; // calculate the index
        int comparison = ((Comparable)get(mid)).compareTo(key); // this compares the word entered with the list of record
        
        if (comparison == 0) return mid; // finds the match of the word entered if found
        else if (comparison > 0) return binarySearch(key, low, mid-1); // search the remaining left list of record
        else return binarySearch(key, mid+1, high);// dearch the remaing list of data on the right
    }
    
    public ArrayList<ElemType> linearSearch(ElemType key) { // Method for Linearch search
        ArrayList<ElemType> results = new ArrayList<>(); // this arraylist olds the search march found
        for (ElemType elem : this) { // repeats over all elements
            if (((Comparable)elem).compareTo(key) == 0) { // check for the march
                results.add(elem); // adds the match to the search results
            }
        }
        return results; // returns every match found
    }
}
