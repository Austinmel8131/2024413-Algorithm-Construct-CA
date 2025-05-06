/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalsystemproject;

import java.util.ArrayList;

public class BubbleSort<ElemType> extends ArrayList<ElemType> {
    public void recursiveBubbleSort(int n) {
        if (n == 1) return;
        for (int i = 0; i < n - 1; i++) {
            if (((Comparable) get(i)).compareTo(get(i + 1)) > 0) {
                swap(i, i + 1);
            }
        }
        recursiveBubbleSort(n - 1);
    }

    private void swap(int i, int j) {
        ElemType temp = get(i);
        set(i, get(j));
        set(j, temp);
    }
}
         


