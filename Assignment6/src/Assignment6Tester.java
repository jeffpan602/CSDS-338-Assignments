import org.junit.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class Assignment6Tester {

    @Test
    public void testAlmostSortedListSort() {
        List<Integer> list1 = new LinkedList<>(Arrays.asList(2, 1, 3, 5, 4, 7, 6));
        List<Integer> sortedList1 = Assignment6.almostSortedListSort(list1, 1);

        int i = 1;
        for(Integer element: sortedList1) {
            assertTrue(i == element);
            i++;
        }

        List<Integer> list2 = new LinkedList<>(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        List<Integer> sortedList2 = Assignment6.almostSortedListSort(list2, 9);

        int j = 1;
        for(Integer element: sortedList2) {
            assertTrue(j == element);
            j++;
        }
    }
    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<>(Arrays.asList(2, 1, 3, 5, 4, 7, 6));
        List<Integer> sortedList1 = Assignment6.almostSortedListSort(list1, 1);

        System.out.println("List 1: " + list1);
        System.out.println("Sorted List 1: " + sortedList1);

        System.out.println();

        List<Integer> list2 = new LinkedList<>(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        List<Integer> sortedList2 = Assignment6.almostSortedListSort(list2, 9);

        System.out.println("List 2: " + list2);
        System.out.println("Sorted List 2: " + sortedList2);
    }
}
