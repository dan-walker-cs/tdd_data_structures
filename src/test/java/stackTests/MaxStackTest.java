package stackTests;

import org.junit.jupiter.api.*;
import stacks.MaxStack;

import java.util.EmptyStackException;

public class MaxStackTest {
    MaxStack test_stack;

    @BeforeEach
    void init() {
        this.test_stack = new MaxStack();
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
    @DisplayName("max method tests")
    class testMax {

        @Test
        @DisplayName("max calls on empty stack ")
        void testMaxEmptyStack() {
            Assertions.assertThrows(EmptyStackException.class, () -> test_stack.max(),
                    "should throw an EmptyStackException.");
        }

        @Test
        @DisplayName("max called on stack with single element (10)")
        void testMaxSinglePush() {
            test_stack.push(10);
            Assertions.assertEquals(10, test_stack.max(),
                    " should return that element (10).");
        }

        @Test
        @DisplayName("max called on stack with multiple elements (0 - 9)")
        void testMaxMultiPush() {
            for(int i = 0; i < 10; i++) {
                test_stack.push(i);
            }
            Assertions.assertEquals(9, test_stack.max(),
                    " should return the maximum element in the stack (9).");
        }

        @Test
        @DisplayName("max called on stack with all same elements (0)")
        void testMaxMultiMaxes() {
            for(int i = 0; i < 10; i++) {
                test_stack.push(0);
            }
            Assertions.assertEquals(0, test_stack.max(),
                    " should return the maximum regardless (0).");
        }
    }
}
