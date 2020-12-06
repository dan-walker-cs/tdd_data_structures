package stackTests;

import org.junit.jupiter.api.*;
import stacks.ListStack;

import java.util.EmptyStackException;

public class ListStackTest {

    ListStack test_stack;

    @BeforeEach
    void init() {
        this.test_stack = new ListStack();
    }

    @Test
    @DisplayName("after calling the stacks.ListStack constructor ")
    void testStackConstructor() {
        Assertions.assertNotNull(test_stack,
                "test_stack should not be initialized");
    }


    @Nested
    @DisplayName("toString method tests")
    class testToString {

        @Test
        @DisplayName("displaying an empty stack ")
        void testEmptyStackString() {
            Assertions.assertEquals("Stack Empty", test_stack.toString(),
                    "should display Stack Empty");
        }

        @Test
        @DisplayName("displaying a stack with a single member ")
        void testSingleElementStackString() {
            test_stack.push(0);
            Assertions.assertEquals("top: " + 0 + "\nv\n" + "null", test_stack.toString());
        }

        @Test
        @DisplayName("displaying a stack with multiple members ")
        void testMultiElementStackString() {
            for(int i = 0; i < 5; i++) {
                test_stack.push(i);
            }
                Assertions.assertEquals("top: " + 4 + "\nv\n" + 3 + "\nv\n"
                        + 2 + "\nv\n" + 1 + "\nv\n" + 0 + "\nv\n" + "null", test_stack.toString());
        }
    }

    @Nested
    @DisplayName("push method tests")
    class testPush {

        @Test
        @DisplayName("pushing a single integer ")
        void testPushInt() {
            test_stack.push(0);
            Assertions.assertEquals(1, test_stack.getSize(),
                    "size should now be 1");
        }

        @Test
        @DisplayName("pushing a single String ")
        void testPushString() {
            test_stack.push("hello");
            Assertions.assertEquals(1, test_stack.getSize(),
                    "size should now be 1");
        }

        @Test
        @DisplayName("pushing (10) elements of same type ")
        void testPushMultiSingleType() {
            for(int i = 0; i < 10; i++) {
                test_stack.push(i);
            }
            Assertions.assertEquals(10, test_stack.getSize(),
                    "size should be (10)");
        }

        @Test
        @DisplayName("pushing (10) elements of varying type ")
        void testPushMultiManyType() {
            for(int i = 0; i < 10; i++) {
                if(i % 2 == 0) {
                    test_stack.push(Double.valueOf(i));
                } else if(i % 7 == 0){
                    test_stack.push(i);
                } else {
                    test_stack.push(String.valueOf(i));
                }
            }
            Assertions.assertEquals(10, test_stack.getSize(),
                    "size should equal 10 with no failures");
        }

        @Test
        @DisplayName("pushing 10,000 elements ")
        void testPushLargeStack() {
            for(int i = 0; i < 10000; i++) {
                test_stack.push(i);
            }
            Assertions.assertEquals(10000, test_stack.getSize(),
                    "size should equal 10,000");
        }
    }

    @Nested
    @DisplayName("peek method tests")
    class testPeek {

        @Test
        @DisplayName("peeking at empty stack ")
        void testPeakEmptyStack() {
            Assertions.assertThrows(EmptyStackException.class, () -> {test_stack.peek();},
                    "should throw an EmptyStackException");
        }

        @Test
        @DisplayName("peeking after single push ")
        void testPeekSinglePush() {
            test_stack.push("hello");
            Assertions.assertAll(
                    () -> Assertions.assertEquals("hello", test_stack.peek(),
                        "should return \"hello\""),
                    () -> Assertions.assertEquals(1, test_stack.getSize(),
                            " and should not delete the element")
            );
        }

        @Test
        @DisplayName("peeking after multiple pushes ")
        void testPeekMultiPush() {
            for(int i = 0; i < 10; i++) {
                test_stack.push(i);
            }
            Assertions.assertAll(
                    () -> Assertions.assertEquals(9, test_stack.peek(),
                            "should return the last value pushed (9)"),
                    () -> Assertions.assertEquals(10, test_stack.getSize(),
                            " and should not delete the element")
            );
        }
    }

    @Nested
    @DisplayName("pop method tests")
    class testPop {

        @Test
        @DisplayName("popping from empty stack ")
        void testPopEmptyStack() {
            Assertions.assertThrows(EmptyStackException.class, () -> {test_stack.pop();},
                    " should throw EmptyStackException");
        }

        @Test
        @DisplayName("single pop after single push ")
        void testSinglePopSinglePush() {
            test_stack.push("hello");
            Assertions.assertAll(
                    () -> Assertions.assertEquals("hello", test_stack.pop(),
                            "should return \"hello\""),
                    () -> Assertions.assertEquals(0, test_stack.getSize(),
                            " and delete the element from the stack.")
            );
        }

        @Test
        @DisplayName("multiple pops after single push ")
        void testMultiPopSinglePush() {
            test_stack.push("hello");
            test_stack.pop();
            Assertions.assertThrows(EmptyStackException.class, () -> {test_stack.pop();},
                    " should throw EmptyStackException");
        }

        @Test
        @DisplayName("single pop after multiple pushes")
        void testSinglePopMultiPush() {
            for(int i = 0; i < 10; i++) {
                test_stack.push(i);
            }
            Assertions.assertAll(
                    () -> Assertions.assertEquals(9, test_stack.pop(),
                            " should return last element pushed (9)"),
                    () -> Assertions.assertEquals(9, test_stack.getSize(),
                            " and delete it from the stack.")
            );
        }

        @Test
        @DisplayName("multiple pops after multiple pushes")
        void testMultiPopMultiPush() {
            for(int i = 0; i < 10; i++) {
                test_stack.push(i);
            }
            for(int i = 9; i >= 0; i--) {
                Assertions.assertEquals(i, test_stack.pop(),
                        " should pop in reverse order of push");
            }
            Assertions.assertEquals(0, test_stack.getSize(),
                    " and result in an empty stack");
        }
    }
}
