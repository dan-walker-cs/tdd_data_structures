package stackTests;

import org.junit.jupiter.api.*;
import stacks.MinStack;

import java.util.EmptyStackException;

public class MinStackTest {

    MinStack test_stack;

    @BeforeEach
    void init() {
        this.test_stack = new MinStack();
    }

    @Test
    @DisplayName("after calling the MinStack constructor ")
    void testStackConstructor() {
        Assertions.assertNotNull(test_stack,
                "test_stack should not be initialized");
    }

    @Nested
    @DisplayName("push method tests")
    class testPush {

        @Test
        @DisplayName("pushing single element ")
        void testSinglePush() {
            test_stack.push(0);
            Assertions.assertEquals(1, test_stack.getSize(),
                    "should increase stack size to 1");
        }

        @Test
        @DisplayName("pushing multiple elements (10) ")
        void testMultiPush() {
            for(int i = 0; i < 10; i++) {
                test_stack.push(i);
            }
            Assertions.assertEquals(10, test_stack.getSize(),
                    "should increase stack size to (10)");
        }
    }

    @Nested
    @DisplayName("min method tests")
    class testMin {

        @Test
        @DisplayName("min calls on empty stack ")
        void testMinEmptyStack() {
            Assertions.assertThrows(EmptyStackException.class, () -> test_stack.min(),
                    "should throw an EmptyStackException.");
        }

        @Test
        @DisplayName("min called on stack with single element (10)")
        void testMinSinglePush() {
            test_stack.push(10);
            Assertions.assertEquals(10, test_stack.min(),
                    " should return that element (10).");
        }

        @Test
        @DisplayName("min called on stack with multiple elements (0 - 9)")
        void testMinMultiPush() {
            for(int i = 0; i < 10; i++) {
                test_stack.push(i);
            }
            Assertions.assertEquals(0, test_stack.min(),
                    " should return the minimum element in the stack (0).");
        }

        @Test
        @DisplayName("min called on stack with all same elements (0)")
        void testMinMultiMins() {
            for(int i = 0; i < 10; i++) {
                test_stack.push(0);
            }
            Assertions.assertEquals(0, test_stack.min(),
                    " should return the minimium regardless (0).");
        }
    }
}
