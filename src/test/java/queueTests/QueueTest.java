package queueTests;

import org.junit.jupiter.api.*;
import queues.Queue;

import java.util.NoSuchElementException;

public class QueueTest {

    Queue test_queue;

    @BeforeEach
    public void init() {
        test_queue = new Queue();
    }

    @Test
    @DisplayName("queue initialization")
    void testQueueConstructor() {
                Assertions.assertNotNull(test_queue, " should result in non-null object");
    }

    @Nested
    @DisplayName("enqueue method tests")
    class testEnqueue {

        @Test
        @DisplayName("enqueuing single element")
        void testSingleEnqueue() {
            test_queue.enqueue("hello");
            Assertions.assertEquals(1, test_queue.getLength(),
                    " should result in length size of 1.");
        }

        @Test
        @DisplayName("enqueuing multiple elements (10)")
        void testMultiEnqueue() {
            for(int i = 0; i < 10; i++) {
                test_queue.enqueue(i);
            }
            Assertions.assertEquals(10, test_queue.getLength(),
                    " should result in queue length of (10).");
        }
    }

    @Nested
    @DisplayName("toString method tests")
    class testToString {

        @Test
        @DisplayName("toString on empty queue")
        void testToStringEmptyQueue() {
            Assertions.assertEquals("Empty Queue", test_queue.toString(),
                    " should display text \"Empty Queue\"");
        }

        @Test
        @DisplayName("toString on populated queue")
        void testToStringPopulatedQueue() {
            for(int i = 0; i < 5; i++) {
                test_queue.enqueue(i);
            }
            Assertions.assertEquals("head -> 4 -> 3 -> 2 -> 1 -> 0 -> tail", test_queue.toString(),
                    " should result in head -> ...queue_data... -> tail");
        }
    }

    @Nested
    @DisplayName("peek method tests")
    class testPeek {

        @Test
        @DisplayName("peeking empty queue")
        void testPeekEmptyQueue() {
            Assertions.assertThrows(NoSuchElementException.class, () -> test_queue.peek(),
                    " should throw NoSuchElementException.");
        }

        @Test
        @DisplayName("peeking single element queue")
        void testPeekSingleElementQueue() {
            test_queue.enqueue("hello");
            Assertions.assertAll(
                    () -> Assertions.assertEquals("hello", test_queue.peek(),
                            " should reveal that element (\"hello\")"),
                    () -> Assertions.assertEquals(1, test_queue.getLength(),
                            " and should have a length (1)"),
                    () -> Assertions.assertFalse(test_queue.isEmpty(),
                            " and should be nonempty.")
            );
        }

        @Test
        @DisplayName("peeking a multi-element queue")
        void testPeekMultiElementQueue() {
            for(int i = 0; i < 10; i++) {
                test_queue.enqueue(i);
            }
            Assertions.assertAll(
                    () -> Assertions.assertEquals(0, test_queue.peek(),
                            " should return the first element enqueued (0)"),
                    () -> Assertions.assertEquals(10, test_queue.getLength(),
                            " and result in correct length (10).")
            );
        }
    }

    @Nested
    @DisplayName("dequeue method tests")
    class testDequeue {

        @Test
        @DisplayName("dequeuing an empty queue")
        void testDequeueEmptyQueue() {
            Assertions.assertThrows(NoSuchElementException.class, () -> test_queue.dequeue(),
                    " should throw NoSuchElementException.");
        }

        @Test
        @DisplayName("singly-dequeuing a queue with single element")
        void testSingleDequeueSingleElementQueue() {
            test_queue.enqueue("hello");
            Assertions.assertAll(
                    () -> Assertions.assertEquals("hello", test_queue.dequeue(),
                            " should return that element (\"hello\")"),
                    () -> Assertions.assertEquals(0, test_queue.getLength(),
                            ", result in a queue of length 0"),
                    () -> Assertions.assertEquals("Empty Queue", test_queue.toString(),
                            ", and properly remove that element.")
            );
        }

        @Test
        @DisplayName("multi-dequeuing a queue with single element")
        void testMultiDequeueSingleElementQueue() {
            test_queue.enqueue("hello");
            test_queue.dequeue();
            Assertions.assertThrows(NoSuchElementException.class, () -> test_queue.dequeue(),
                    " should result in a NoSuchElementException.");
        }

        @Test
        @DisplayName("singly-dequeuing a queue with multiple elements")
        void testSingleDequeueMultiElementQueue() {
            for(int i = 0; i < 5; i++) {
                test_queue.enqueue(i);
            }
            Assertions.assertAll(
                    () -> Assertions.assertEquals(0, test_queue.dequeue(),
                            " should return the first element enqueued (0)"),
                    () -> Assertions.assertEquals(4, test_queue.getLength(),
                            ", decrease the length of the queue by 1 (4)"),
                    () -> Assertions.assertEquals("head -> 4 -> 3 -> 2 -> 1 -> tail", test_queue.toString(),
                            ", and still contain the other elements properly linked.")
            );
        }

        @Test
        @DisplayName("multi-dequeue a queue with multiple elements")
        void testMultiDequeueMultiElementQueue() {
            for(int i = 0; i < 10; i++) {
                test_queue.enqueue(i);
            }
            for(int i = 0; i < 10; i++) {
                test_queue.dequeue();
            }
            Assertions.assertAll(
                    () -> Assertions.assertEquals(0, test_queue.getLength(),
                            " should result in queue of length (0)"),
                    () -> Assertions.assertEquals("Empty Queue", test_queue.toString(),
                            ", and be displayed as (\"Empty Queue\").")
            );
        }
    }
}
