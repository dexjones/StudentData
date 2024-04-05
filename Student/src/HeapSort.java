import java.util.Comparator;
import java.util.LinkedList;

public class HeapSort {
    public static void heapSort(LinkedList<Student> students, Comparator<Student> comparator) {
        int size = students.size();
        LinkedList<Student> heap = new LinkedList<>();

        // Initialize heap with nulls
        for (int i = 0; i < size; i++) {
            heap.add(null);
        }

        // Add each student to the heap
        for (int i = 0; i < size; i++) {
            add(students.get(i), heap, i, comparator);
        }

        // Remove students from the heap and store them back in the list
        for (int i = size - 1; i >= 0; i--) {
            students.set(i, remove(heap, i, comparator));
        }
    }
    private static void add(Student student, LinkedList<Student> heap, int index, Comparator<Student> comparator) {
        heap.set(index, student);
        int parent = (index - 1) / 2;
        while (index > 0 && comparator.compare(heap.get(index), heap.get(parent)) > 0) {
            // Swap heap[index] and heap[parent]
            Student temp = heap.get(index);
            heap.set(index, heap.get(parent));
            heap.set(parent, temp);

            index = parent;
            parent = (index - 1) / 2;
        }
    }
    private static Student remove(LinkedList<Student> heap, int last, Comparator<Student> comparator) {
        Student root = heap.get(0);
        heap.set(0, heap.get(last));
        heap.set(last, null); // Clear the last element
        last--; // Adjust the heap size

        int i = 0;
        while (i <= last) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left > last) break; // No more children

            // Find the larger child
            int max = left;
            if (right <= last && comparator.compare(heap.get(right), heap.get(left)) > 0) {
                max = right;
            }

            // If the larger child is larger than the current node, swap them
            if (comparator.compare(heap.get(i), heap.get(max)) < 0) {
                // Swap heap[i] and heap[max]
                Student temp = heap.get(i);
                heap.set(i, heap.get(max));
                heap.set(max, temp);
                i = max; // Move down to the child
            } else {
                break; // The heap is in order
            }
        }
        return root;
    }
}
