package heapTests;

import heaps.MaxHeap;
import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

public class MaxHeapTest {

    MaxHeap test_heap;

    @BeforeEach
    void init() {
        test_heap = new MaxHeap();
    }

    @Nested
    @DisplayName("tests for insert method")
    class testInsert {

        @Test
        @DisplayName("single insert")
        void testSingleInsert() {
            test_heap.insert(1);
            Assertions.assertEquals("[1, 0, 0, 0, 0]", test_heap.toString(),
                    " should result in that element being stored at the root.");
        }

        @Test
        @DisplayName("multi ordered insert")
        void testMultiOrderedInsert() {
            for(int i = 0; i < 5; i++) {
                test_heap.insert(i * 4);
            }
            Assertions.assertEquals("[16, 12, 4, 0, 8]", test_heap.toString(),
                    " should result in elements being added and heap structure being preserved.");
        }

        @Test
        @DisplayName("multi jumbled insert")
        void testMultiUnorderedInsert() {
            test_heap.insert(0);
            test_heap.insert(20);
            test_heap.insert(5);
            test_heap.insert(8);
            test_heap.insert(50);

            Assertions.assertEquals("[50, 20, 5, 0, 8]", test_heap.toString(),
                    " should result in elements being added and heap structure being preserved.");
        }

        @Test
        @DisplayName("insert on full heap")
        void testInsertHeapOverflow() {
            for(int i = 0; i < 5; i++) {
                test_heap.insert(i);
            }
            Assertions.assertThrows(NoSuchElementException.class, () -> test_heap.insert(5),
                    " should throw NoSuchElementException.");
        }
    }

    @Nested
    @DisplayName("tests for remove method")
    class testRemove {

        @Test
        @DisplayName("single remove from populated heap")
        void testSingleRemove() {
            for(int i = 1; i < 6; i++) {
                test_heap.insert(i);
            }
            int test_remove = test_heap.remove();
            Assertions.assertAll(
                    () -> Assertions.assertEquals(5, test_remove,
                            " should return the root element"),
                    () -> Assertions.assertEquals("[4, 3, 2, 1, 0]", test_heap.toString(),
                            " and preserve heap properties.")
            );
        }

        @Test
        @DisplayName("remove from empty heap")
        void testRemoveEmptyHeap() {
            Assertions.assertThrows(NoSuchElementException.class, () -> test_heap.remove(),
                    " should throw NoSuchElementException");
        }

        @Test
        @DisplayName("multi-remove from populated heap")
        void testMultiRemove() {
            for(int i = 1; i < 6; i++) {
                test_heap.insert(i);
            }
            int[] test_remove = new int[5];
            int[] expected = {5, 4, 3, 2, 1};
            for(int i = 0; i < 5; i++) {
                test_remove[i] = test_heap.remove();
            }
            Assertions.assertAll(
                    () -> Assertions.assertArrayEquals(expected, test_remove,
                            " should result in elements received in descending order"),
                    () -> Assertions.assertEquals("[0, 0, 0, 0, 0]", test_heap.toString(),
                            " and the heap becoming empty")
            );
        }
    }
}
