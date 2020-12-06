import org.junit.jupiter.api.*;

public class ListStackTest {

    ListStack test_stack;

    @BeforeEach
    void init() {
        this.test_stack = new ListStack();
    }

    @Test
    @DisplayName("after calling the ListStack constructor ")
    void testStackConstructor() {
        Assertions.assertNotNull(test_stack, "test_stack should not be initialized");
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

        @Disabled
        @Test
        @DisplayName("displaying a stack with multiple members ")
        void testMultiElementStackString() {

        }
    }
}
