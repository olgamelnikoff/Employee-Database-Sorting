import java.util.ArrayList;

public class HeapSort {

    private ArrayList<Employee> list = new ArrayList<Employee>();

    public HeapSort() {

    }

    public HeapSort(ArrayList<Employee> list) {

    }

    public void setArray(ArrayList<Employee> list) {
        this.list = list;
    }

    private void initialHeap(ArrayList<Employee> list) {

        //Last internal node is at position (arr.length-1)/2
        //we start from this position to do the first re-heap of the elements

        for (int i = (list.size() - 1) / 2; i >= 0; i--) {
            reHeap(list, i, list.size() - 1);
        }
    }

    // Do re-heap of the array starting from subRoot

    private void reHeap(ArrayList<Employee> list, int subRoot, int size) {
        int leftChildPos = 2 * subRoot + 1;
        int rightChildPos = 2 * subRoot + 2;
        int max;

        //If leftChild is bigger than subRoot, change max to leftChildPosition
        if (leftChildPos <= size && list.get(leftChildPos).getEmployeeId() > list.get(subRoot).getEmployeeId()) {
            max = leftChildPos;
        }

        //else, keep max subRoot
        else {
            max = subRoot;
        }

        //If rightChild is bigger than max (that is either subRoot or left Child), change max to rightChildPosition
        if (rightChildPos <= size && list.get(rightChildPos).getEmployeeId() > list.get(max).getEmployeeId()) {
            max = rightChildPos;
        }

        // If max is not subRoot, swap subRoot with the max (left or right child)

        if (max != subRoot) {
            swap(list, subRoot, max);
            reHeap(list, max, size);
        }
    }

    private void swap(ArrayList<Employee> list, int i, int j) {
        Employee temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    //Method for heapSort

    public ArrayList<Employee> heapSort(ArrayList<Employee> list) {

        initialHeap(list);
        int arraySize = list.size() - 1;

        for (int i = arraySize; i > 0; i--) {
            swap(list, 0, i);
            arraySize = arraySize - 1;
            reHeap(list, 0, arraySize);
        }
        return list;
    }
}