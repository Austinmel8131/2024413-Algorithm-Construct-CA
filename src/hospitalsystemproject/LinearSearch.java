/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalsystemproject;

import java.util.*;
import java.util.ArrayList;
/**
 *
 * @author macbook
 */
public class LinearSearch<ElemType> extends ArrayList<ElemType> {

     public int binarySearch(ElemType key, int low, int high) {
        if (high < low) return -1;
        
        int mid = (low + high) / 2;
        int comparison = ((Comparable)get(mid)).compareTo(key);
        
        if (comparison == 0) return mid;
        else if (comparison > 0) return binarySearch(key, low, mid-1);
        else return binarySearch(key, mid+1, high);
    }
    
    public ArrayList<ElemType> linearSearch(ElemType key) {
        ArrayList<ElemType> results = new ArrayList<>();
        for (ElemType elem : this) {
            if (((Comparable)elem).compareTo(key) == 0) {
                results.add(elem);
            }
        }
        return results;
    }
}
