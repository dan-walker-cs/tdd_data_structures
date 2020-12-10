package queueTests;

import org.junit.jupiter.api.*;
import queues.PriorityQueue;

import java.util.NoSuchElementException;

public class PriorityQueueTest<T> {

    PriorityQueue test_pq;

    @BeforeEach
    void init() {
        test_pq = new PriorityQueue();
    }

    @Nested
    @DisplayName("tests for insert method")
    class testInsert {

        @Test
        @DisplayName("generics test: inserting int")
        void testSingleInsertInt() {
            test_pq.insert(5);
            Assertions.assertEquals("[(5,1), null, null, null, null]", test_pq.toString(),
                    " should store a pair (data, priority) at the root node.");
        }

        @Test
        @DisplayName("generics test: inserting String")
        void testSingleInsertString() {
            test_pq.insert("hello");
            Assertions.assertEquals("[(hello,1), null, null, null, null]", test_pq.toString(),
                    " should store a pair (data, priority) at the root node.");
        }

        @Test
        @DisplayName("insert to fill queue, all same priority")
        void testMultiInsertSamePriority() {
            for(int i = 1; i < 6; i++) {
                test_pq.insert(i);
            }
            Assertions.assertEquals("[(1,1), (2,1), (3,1), (4,1), (5,1)]", test_pq.toString(),
                    " should store pairs in same order they are inserted.");
        }

        @Test
        @DisplayName("insert to fill queue, varying priority")
        void testMultiInsertMultiPriority() {
            for(int i = 1; i < 6; i++) {
                test_pq.insert(i/2+1, i);
            }
            Assertions.assertEquals("[(3,5), (3,4), (2,2), (1,1), (2,3)]", test_pq.toString(),
                    " should store pairs such that priority is maximized in heap structure.");
        }

        @Test
        @DisplayName("insert into full priority queue")
        void testInsertFullQueue() {
            for(int i = 0; i < 5; i++) {
                test_pq.insert(i);
            }
            Assertions.assertThrows(NoSuchElementException.class, () -> test_pq.insert(5),
                    " should throw NoSuchElementException.");
        }
    }

    @Nested
    @DisplayName("tests for remove method")
    class testRemove {

        @Test
        @DisplayName("removal from empty queue")
        void testRemoveEmptyQueue() {
            Assertions.assertThrows(NoSuchElementException.class, () -> test_pq.remove(),
                " should throw NoSuchElementException.");
        }

        @Test
        @DisplayName("removal from single element queue")
        void testSingleRemoveSingleElementQueue() {
            test_pq.insert("hello");
            String data = test_pq.remove().toString();

            Assertions.assertAll(
                    () -> Assertions.assertEquals("hello", data,
                            " should return the root element"),
                    () -> Assertions.assertEquals("[null, null, null, null, null]", test_pq.toString(),
                            " and result in an empty queue.")
            );
        }

        @Test
        @DisplayName("emptying a full queue")
        void testFullRemovalQueue() {
            for(int i = 1; i < 6; i++) {
                test_pq.insert(i, i);
            }
            String[] data = new String[5];
            String[] expected = {"5", "4", "3", "2", "1"};

            for(int i = 0; i < 5; i++) {
                data[i] = test_pq.remove().toString();
            }
            Assertions.assertAll(
                    () -> Assertions.assertArrayEquals(expected, data,
                            " should return the elements in order of decreasing priority"),
                    () -> Assertions.assertEquals("[null, null, null, null, null]", test_pq.toString(),
                            " and result in an empty queue.")
            );
        }
    }
}
