package test;

import org.junit.jupiter.api.Test;

import def.RecursiveBinarySearch;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecursiveBinarySearchTest {

    @Test
    public void testBinarySearchRecursiveIntegersFound() {
        int[] array = {1, 2, 3, 4, 5, 6};
        int target = 4;
        int result = RecursiveBinarySearch.binarySearchRecursive(array, target, 0, array.length - 1);
        assertEquals(3, result);
    }

    @Test
    public void testBinarySearchRecursiveIntegersNotFound() {
        int[] array = {1, 2, 3, 4, 5, 6};
        int target = 7;
        int result = RecursiveBinarySearch.binarySearchRecursive(array, target, 0, array.length - 1);
        assertEquals(-1, result);
    }

    @Test
    public void testBinarySearchRecursiveEmptyArray() {
        int[] array = {};
        int target = 4;
        assertThrows(IllegalArgumentException.class, () ->
                RecursiveBinarySearch.binarySearchRecursive(array, target, 0, array.length - 1));
    }

    @Test
    public void testBinarySearchRecursiveNullArray() {
        int[] array = null;
        int target = 4;
        assertThrows(IllegalArgumentException.class, () ->
                RecursiveBinarySearch.binarySearchRecursive(array, target, 0, 0));
    }

    @Test
    public void testBinarySearchRecursiveStringsFound() {
        String[] array = {"apple", "banana", "cherry", "date"};
        String target = "cherry";
        int result = RecursiveBinarySearch.binarySearchRecursive(array, target, 0, array.length - 1);
        assertEquals(2, result);
    }

    @Test
    public void testBinarySearchRecursiveStringsNotFound() {
        String[] array = {"apple", "banana", "cherry", "date"};
        String target = "fig";
        int result = RecursiveBinarySearch.binarySearchRecursive(array, target, 0, array.length - 1);
        assertEquals(-1, result);
    }

    @Test
    public void testBinarySearchAllIndicesSingleTarget() {
        int[] array = {1, 2, 3, 4, 4, 5, 6};
        int target = 4;
        List<Integer> result = RecursiveBinarySearch.binarySearchAllIndices(array, target, 0, array.length - 1);
        assertEquals(Arrays.asList(3, 4), result);
    }

    @Test
    public void testBinarySearchAllIndicesNoMatch() {
        int[] array = {1, 2, 3, 4, 5, 6};
        int target = 7;
        List<Integer> result = RecursiveBinarySearch.binarySearchAllIndices(array, target, 0, array.length - 1);
        assertTrue(result.isEmpty());
    }
}

