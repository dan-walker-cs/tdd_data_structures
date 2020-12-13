package treeTests;

import org.junit.jupiter.api.*;
import trees.BinarySearchTree;

public class BinarySearchTreeTest {

    BinarySearchTree test_tree;

    @BeforeEach
    void init() {
        test_tree = new BinarySearchTree();
    }

    @Test
    @DisplayName("test for empty tree")
    void testEmptyTree() {
        Assertions.assertNull(test_tree.root, " root should be null before insertion");
    }

    @Nested
    @DisplayName("tests for insert method")
    class testInsert {

        @Test
        @DisplayName("single insert into empty tree")
        void testSingleInsert() {
            test_tree.insert(100);
            Assertions.assertNotNull(test_tree.root, " root should be non-null after insertion");
        }

        @Test
        @DisplayName("multiple insertions")
        void testMultiInsert() {
            for(int i = 0; i < 100; i++) {
                test_tree.insert(i);
            }
            Assertions.assertNotNull(test_tree.root, " root should be non-null after insertion");
        }

        @Test
        @DisplayName("multiple insertions, some negatives")
        void testVariedInsert() {
            for(int i = 0; i < 100; i++) {
                if(i % 15 == 0) {
                    test_tree.insert(i * -1);
                } else {
                    test_tree.insert(i);
                }
            }
        }

        @Test
        @DisplayName("large set of insertions")
        void testLargeInsert() {
            for(int i = 0; i < 10000; i++) {
                test_tree.insert(i);
            }
            Assertions.assertNotNull(test_tree.root, " root should be non-null after insertion");
        }
    }

    @Nested
    @DisplayName("tests for search method")
    class testSearch {

        @Test
        @DisplayName("search empty tree")
        void testSearchEmptyTree() {
            Assertions.assertFalse(test_tree.search(100), " should return false");
        }

        @Test
        @DisplayName("searching for data not in single-element-tree")
        void testInvalidSearchSingleElement() {
            test_tree.insert(100);
            Assertions.assertFalse(test_tree.search(101), " should return false");
        }

        @Test
        @DisplayName("searching for data contained in single-element-tree")
        void testValidSearchSingleElement() {
            test_tree.insert(100);
            Assertions.assertTrue(test_tree.search(100), " should return true");
        }

        @Test
        @DisplayName("searching for data not in multi-element-tree")
        void testInvalidSingleSearchMultiElement() {
            for(int i = 0; i < 100; i++) {
                test_tree.insert(i);
            }
            Assertions.assertFalse(test_tree.search(100), " should return false");
        }

        @Test
        @DisplayName("searching for data contained in multi-element-tree")
        void testValidSingleSearchMultiElement() {
            for(int i = 0; i < 100; i++) {
                test_tree.insert(i);
            }
            Assertions.assertTrue(test_tree.search(50), " should return true");
        }

        @Test
        @DisplayName("multiple valid data searches")
        void testValidMultiSearch() {
            for(int i = 0; i < 100; i++) {
                test_tree.insert(i);
            }
            Assertions.assertAll(
                    () -> Assertions.assertTrue(test_tree.search(99), " (99) should return true"),
                    () -> Assertions.assertTrue(test_tree.search(50), " (50) should return true"),
                    () -> Assertions.assertTrue(test_tree.search(63), " (63) should return true"),
                    () -> Assertions.assertTrue(test_tree.search(0), " (0) should return true"),
                    () -> Assertions.assertTrue(test_tree.search(18), " (18) should return true"),
                    () -> Assertions.assertTrue(test_tree.search(27), " (27) should return true"),
                    () -> Assertions.assertTrue(test_tree.search(88), " (88) should return true")
            );
        }

        @Test
        @DisplayName("multiple searches, varied validity")
        void testVariedValiditySearch() {
            for(int i = 0; i < 100; i++) {
                test_tree.insert(i);
            }
            Assertions.assertAll(
                    () -> Assertions.assertTrue(test_tree.search(99), " (99) should return true"),
                    () -> Assertions.assertTrue(test_tree.search(50), " (50) should return true"),
                    () -> Assertions.assertFalse(test_tree.search(100), " (100) should return false"),
                    () -> Assertions.assertTrue(test_tree.search(0), " (0) should return true"),
                    () -> Assertions.assertTrue(test_tree.search(18), " (18) should return true"),
                    () -> Assertions.assertFalse(test_tree.search(-1), " (-1) should return false"),
                    () -> Assertions.assertTrue(test_tree.search(88), " (88) should return true")
            );
        }

        @Test
        @DisplayName("search every valid element in tree")
        void testFullSearch() {
            for(int i = 0; i < 100; i++) {
                test_tree.insert(i);
            }

            boolean[] true_array = new boolean[100];
            boolean[] search_results = new boolean[100];
            for(int i = 0; i < 100; i++) {
                true_array[i] = true;
                search_results[i] = test_tree.search(i);
            }
            Assertions.assertArrayEquals(true_array, search_results, " should return true for all searches.");
        }
    }

    @Nested
    @DisplayName("tests for remove method")
    class testRemove {

        @Test
        @DisplayName("remove from empty tree")
        void testRemoveEmptyTree() {
            Assertions.assertDoesNotThrow(() -> test_tree.remove(100)
            , " should not throw an exception.");
        }

        @Test
        @DisplayName("removing data not in single-element-tree")
        void testInvalidRemoveSingleElement() {
            test_tree.insert(100);
            Assertions.assertDoesNotThrow(() -> test_tree.remove(100)
                    , " should not throw an exception.");
        }

        @Test
        @DisplayName("removing data contained in single-element-tree")
        void testValidRemoveSingleElement() {
            test_tree.insert(100);
            Assertions.assertAll(
                    () -> Assertions.assertTrue(test_tree.search(100),
                            " should return true before removal"),
                    () -> {
                        test_tree.remove(100);
                        Assertions.assertFalse(test_tree.search(100), " and false after removal.");
                    }
            );
        }

        @Test
        @DisplayName("removing data not in multi-element-tree")
        void testInvalidSingleRemoveMultiElement() {
            for(int i = 0; i < 100; i++) {
                test_tree.insert(i);
            }
            Assertions.assertDoesNotThrow(() -> test_tree.remove(100)
                    , " should not throw an exception.");
        }

        @Test
        @DisplayName("removing data contained in multi-element-tree")
        void testValidSingleRemoveMultiElement() {
            for(int i = 0; i < 100; i++) {
                test_tree.insert(i);
            }
            Assertions.assertAll(
                    () -> Assertions.assertTrue(test_tree.search(50),
                            " should return true before removal"),
                    () -> {
                        test_tree.remove(50);
                        Assertions.assertFalse(test_tree.search(50), " and false after removal.");
                    }
            );
        }

        @Test
        @DisplayName("multiple valid data removals")
        void testValidMultiRemove() {
            for(int i = 0; i < 100; i++) {
                test_tree.insert(i);
            }
            Assertions.assertAll(
                    () -> Assertions.assertTrue(test_tree.search(99), " (99) should return true"),
                    () -> {
                        test_tree.remove(99);
                        Assertions.assertFalse(test_tree.search(99), " (99) should return false");
                    },
                    () -> Assertions.assertTrue(test_tree.search(50), " (50) should return true"),
                    () -> {
                        test_tree.remove(50);
                        Assertions.assertFalse(test_tree.search(50), " (50) should return false");
                    },
                    () -> Assertions.assertTrue(test_tree.search(63), " (63) should return true"),
                    () -> {
                        test_tree.remove(63);
                        Assertions.assertFalse(test_tree.search(63), " (63) should return false");
                    },
                    () -> Assertions.assertTrue(test_tree.search(0), " (0) should return true"),
                    () -> {
                        test_tree.remove(0);
                        Assertions.assertFalse(test_tree.search(0), " (0) should return false");
                    },
                    () -> Assertions.assertTrue(test_tree.search(18), " (18) should return true"),
                    () -> {
                        test_tree.remove(18);
                        Assertions.assertFalse(test_tree.search(18), " (18) should return false");
                    },
                    () -> Assertions.assertTrue(test_tree.search(27), " (27) should return true"),
                    () -> {
                        test_tree.remove(27);
                        Assertions.assertFalse(test_tree.search(27), " (27) should return false");
                    },
                    () -> Assertions.assertTrue(test_tree.search(88), " (88) should return true"),
                    () -> {
                        test_tree.remove(88);
                        Assertions.assertFalse(test_tree.search(88), " (88) should return false");
                    }
            );
        }

        @Test
        @DisplayName("remove every element in tree")
        void testFullRemove() {
            for(int i = 0; i < 100; i++) {
                test_tree.insert(i);
            }

            boolean[] true_array = new boolean[100];
            boolean[] false_array = new boolean[100];
            boolean[] search_results_before = new boolean[100];
            boolean[] search_results_after = new boolean[100];
            for(int i = 0; i < 100; i++) {
                true_array[i] = true;
                false_array[i] = false;
                search_results_before[i] = test_tree.search(i);
            }
            Assertions.assertAll(
                    () -> Assertions.assertArrayEquals(true_array, search_results_before,
                            " should return true for all searches."),
                    () -> {
                        for(int i = 0; i < 100; i++) {
                            test_tree.remove(i);
                        }
                        for(int i = 0; i < 100; i++) {
                            search_results_after[i] = test_tree.search(i);
                        }
                        Assertions.assertArrayEquals(false_array, search_results_after,
                                " should return false for all searches");
                    }
            );
        }
    }
}
