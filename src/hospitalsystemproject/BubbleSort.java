/*
 * 
 */
package hospitalsystemproject;

import java.util.ArrayList; // responsible for importing arraylist

// Recursive Bubble sort algorithm for t=sorting records
public class BubbleSort<ElemType> extends ArrayList<ElemType> { // defining the bubble class that extends to the Arraylist
    public void recursiveBubbleSort(int n) { // the recursive method for bubble sort
        if (n == 1) return; // no sorting needed if there is just one element.
        for (int i = 0; i < n - 1; i++) { // looping thorugh all the list of items n-1
            if (((Comparable) get(i)).compareTo(get(i + 1)) > 0) { // comparing adjacent items
                swap(i, i + 1); // responsible for swapping if they are not in the right order
            }
        }
        recursiveBubbleSort(n - 1); // sort the list of items or elements 
    }

    private void swap(int i, int j) { //a swap method that swap two elements
        ElemType temp = get(i); // keep element temporary in i
        set(i, get(j)); //// keep element temporary in j
        set(j, temp); // responding for replacing element in i with element in j
    }
}
         


