package heapTests;

import heaps.MinHeap;
import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

public class MinHeapTest {

    MinHeap test_heap;

    @BeforeEach
    void init() {
        test_heap = new MinHeap();
    }

    @Test
    @DisplayName("constructing a new MinHeap")
    void testMinheapConstructor () {
        Assertions.assertNotNull(test_heap, " should result in non-null value.");
    }

    @Nested
    @DisplayName("insert method tests")
    class testInsert {

        @Test
        @DisplayName("inserting single element into empty heap")
        void testSingleInsertEmptyHeap() {
            test_heap.insert(0);
            Assertions.assertEquals("[0, 0, 0, 0, 0]", test_heap.toString(),
                    " should show that element within the heap");
        }

        @Test
        @DisplayName("inserting multiple elements into empty heap")
        void testMultiInsertEmptyHeap() {
            for(int i = 0; i < 5; i++) {
                test_heap.insert(i);
            }
            Assertions.assertEquals("[0, 1, 2, 3, 4]", test_heap.toString(),
                    " should show those elements within the heap");
        }

        @Test
        @DisplayName("inserting more elements than can fit into heap")
        void testMultiInsertHeapOverflow() {
            for(int i = 0; i < 5; i++) {
                test_heap.insert(i);
            }
            Assertions.assertThrows(NoSuchElementException.class, () -> test_heap.insert(5),
                    " should throw NoSuchElementException");
        }

        @Test
        @DisplayName("inserting multiple unordered elements")
        void testUnorderedMultiInsert() {
            test_heap.insert(8);
            test_heap.insert(0);
            test_heap.insert(20);
            test_heap.insert(-6);
            test_heap.insert(2);
            Assertions.assertEquals("[-6, 0, 20, 8, 2]", test_heap.toString(),
                    " should preserve the MinHeap structure.");
        }
    }

    @Nested
    @DisplayName("remove method tests")
    class testRemove {

        @Test
        @DisplayName("remove from empty heap")
        void testRemoveEmptyHeap() {
            Assertions.assertThrows(NoSuchElementException.class, () -> test_heap.remove(),
                    " should throw NoSuchElementException");
        }

        @Test
        @DisplayName("remove from heap with single element")
        void testRemoveSingleElementHeap() {
            test_heap.insert(20);
            int testRemove = test_heap.remove();
            Assertions.assertAll(
                    () -> Assertions.assertEquals(20, testRemove,
                            " should return that element"),
                    () -> Assertions.assertEquals("[0, 0, 0, 0, 0]", test_heap.toString(),
                            " and result in an empty heap")
            );
        }

        @Test
        @DisplayName("single remove from full heap")
        void testSingleRemoveMultiElementHeap() {
            for(int i = 0; i < 5; i++) {
                test_heap.insert(i);
            }
            int testRemove = test_heap.remove();
            Assertions.assertAll(
                    () -> Assertions.assertEquals(0, testRemove,
                            " should remove the root element"),
                    () -> Assertions.assertEquals("[1, 3, 2, 4, 0]", test_heap.toString(),
                            " and preserve the heap structure.")
            );
        }

        @Test
        @DisplayName("complete remove from full heap")
        void testFullRemoveFullHeap() {
            for(int i = 0; i < 5; i++) {
                test_heap.insert(i);
            }
            int[] testRemove = new int[5];
            for(int i = 0; i < 5; i++) {
                testRemove[i] = test_heap.remove();
            }
            int[] expectedValues = {0, 1, 2, 3, 4};
            Assertions.assertArrayEquals(expectedValues, testRemove,
                    " should pop elements in increasing value.");
        }
    }
}
