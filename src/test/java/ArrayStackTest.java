import org.junit.jupiter.api.*;

class ArrayStackTest {
    ArrayStack test_stack;
    int capacity;

    @BeforeEach
    public void init() {
        capacity = 10;
        test_stack = new ArrayStack(capacity);
    }

    @Nested
    @DisplayName("pop method")
    class PopTest {

        @Test
        @DisplayName("when pop called on empty stack ")
        void testPopEmpty() {
            Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,() -> {test_stack.pop();},
                    "should throw an arrayIndexOutOfBounds exception");
        }

        @Test
        @DisplayName("when pop called on valid stack with single member (5) ")
        void testPopSingleValid() {
            test_stack.push(5);
            Assertions.assertAll(
                    () -> Assertions.assertEquals(5, test_stack.pop(),
                        "should return top integer (5)"),
                    () -> Assertions.assertTrue(test_stack.isEmpty(),
                        " and and result in an empty stack.")
                );
        }

        @Test
        @DisplayName("when pop called multiple times on valid stack with multiple data members (0-5) ")
        void testPopMultiValid() {
            test_stack.push(0);
            test_stack.push(1);
            test_stack.push(2);
            test_stack.push(3);
            test_stack.push(4);
            test_stack.push(5);

            Assertions.assertAll(
                    () -> Assertions.assertEquals(5, test_stack.pop(),
                        "should return top integer (5)"),
                    () -> Assertions.assertEquals(4, test_stack.pop(),
                            ", then next integer (4)"),
                    () -> Assertions.assertEquals(3, test_stack.pop(),
                            ", then next integer (3)"),
                    () -> Assertions.assertEquals(2, test_stack.pop(),
                            ", then next integer (2)"),
                    () -> Assertions.assertEquals(1, test_stack.pop(),
                            ", then next integer (1)"),
                    () -> Assertions.assertEquals(0, test_stack.pop(),
                            ", then next integer (0)"),
                    () -> Assertions.assertTrue(test_stack.isEmpty(),
                            " - and should then be empty.")
            );
        }
    }

    @Nested
    @DisplayName("peek method")
    class PeekTest {

        @Test
        @DisplayName("when peek called on empty stack ")
        void testPeekEmpty() {
            Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,() -> {test_stack.peek();},
                    "should throw an arrayIndexOutOfBounds exception");
        }

        @Test
        @DisplayName("when peek called on valid stack with single member (5) ")
        void testPeekSingleValid() {
            test_stack.push(5);
            Assertions.assertEquals(5, test_stack.peek(),
                    "should return top integer (5)");
        }

        @Test
        @DisplayName("when peek called on valid stack with multiple data members (0-5) ")
        void testPeekMultiValid() {
            test_stack.push(0);
            test_stack.push(1);
            test_stack.push(2);
            test_stack.push(3);
            test_stack.push(4);
            test_stack.push(5);

            Assertions.assertEquals(5, test_stack.peek(),
                    "should return top integer (5)");
        }
    }

    @Nested
    @DisplayName("push method")
    class PushTest {

        @Test
        @DisplayName("when an integer (5) is pushed ")
        void testPushSingle() {
            test_stack.push(5);
            Assertions.assertEquals("[5]", test_stack.toString(), "(5) should be seen in stack");
        }

        @Test
        @DisplayName("when pushes occur until the stack is full ")
        void testPushMulti() {
            for(int i = 0; i < capacity; i++) {
                test_stack.push(i);}
            Assertions.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", test_stack.toString(),
                    "(10) elements 0-9 should be shown");
        }

        @Test
        @DisplayName("when pushes occur enough times to overflow array ")
        void testPushOverCapacity() {
            for(int i = 0; i < capacity+1; i++) {
                test_stack.push(i);}
            Assertions.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", test_stack.toString(),
                    "(11) elements 0-10 should be shown");
        }
    }

    @Nested
    @DisplayName("isEmpty method")
    class EmptyTest {

        @Test
        @DisplayName("when the stack is empty ")
        void emptyTest() {
            Assertions.assertTrue(test_stack.isEmpty(), "should return true");
        }

        @Test
        @DisplayName("when the stack is nonempty ")
        void nonemptyTest() {
            test_stack.push(1);
            Assertions.assertFalse(test_stack.isEmpty(), "should return false");
        }
    }
}
