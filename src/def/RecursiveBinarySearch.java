package def;

import java.util.ArrayList;
import java.util.List;

/**
 * The RecursiveBinarySearch class provides methods to perform binary search
 * using recursion. It includes functionality for searching integers, strings, 
 * and finding all occurrences of a target value in a sorted array.
 * 
 * Features:
 * - Binary search for integers.
 * - Binary search for strings.
 * - Finding all indices of a target value in a sorted integer array.
 * 
 * Note:
 * - The input arrays must be sorted for the binary search to work correctly.
 */
public class RecursiveBinarySearch {

    /**
     * Performs a recursive binary search on a sorted integer array to find the index of the target value.
     * 
     * @param array The sorted array of integers.
     * @param target The target value to search for.
     * @param left The starting index of the current search range.
     * @param right The ending index of the current search range.
     * @return The index of the target value if found; -1 otherwise.
     * @throws IllegalArgumentException If the input array is null or empty.
     * 
     * Behavior:
     * - Recursively divides the search range into halves.
     * - Searches the left or right half based on the target's value relative to the middle element.
     * - Returns -1 if the search range becomes invalid (left > right).
     */
    public static int binarySearchRecursive(int[] array, int target, int left, int right) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        if (left > right) {
            return -1; // Base case: target not found
        }
        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return binarySearchRecursive(array, target, mid + 1, right); // Search right half
        } else {
            return binarySearchRecursive(array, target, left, mid - 1); // Search left half
        }
    }

    /**
     * Performs a recursive binary search on a sorted string array to find the index of the target value.
     * 
     * @param array The sorted array of strings.
     * @param target The target string to search for.
     * @param left The starting index of the current search range.
     * @param right The ending index of the current search range.
     * @return The index of the target string if found; -1 otherwise.
     * @throws IllegalArgumentException If the input array is null or empty.
     * 
     * Behavior:
     * - Recursively divides the search range into halves.
     * - Searches the left or right half based on the target's value relative to the middle element.
     * - Uses String's `compareTo` method for comparisons.
     * - Returns -1 if the search range becomes invalid (left > right).
     */
    public static int binarySearchRecursive(String[] array, String target, int left, int right) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        if (left > right) {
            return -1; // Base case: target not found
        }
        int mid = left + (right - left) / 2;

        int comparison = target.compareTo(array[mid]);
        if (comparison == 0) {
            return mid;
        } else if (comparison > 0) {
            return binarySearchRecursive(array, target, mid + 1, right); // Search right half
        } else {
            return binarySearchRecursive(array, target, left, mid - 1); // Search left half
        }
    }

    /**
     * Finds all indices of a target value in a sorted integer array using recursion.
     * 
     * @param array The sorted array of integers.
     * @param target The target value to search for.
     * @param left The starting index of the current search range.
     * @param right The ending index of the current search range.
     * @return A list of all indices where the target value occurs in the array.
     * @throws IllegalArgumentException If the input array is null or empty.
     * 
     * Behavior:
     * - Recursively searches both the left and right halves for additional occurrences
     *   of the target value when a match is found.
     * - Returns an empty list if the search range becomes invalid (left > right).
     */
    public static List<Integer> binarySearchAllIndices(int[] array, int target, int left, int right) {
        List<Integer> indices = new ArrayList<>();
        if (left > right) {
            return indices; // Base case
        }
        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            indices.add(mid); // Add index of target
            indices.addAll(binarySearchAllIndices(array, target, left, mid - 1)); // Check left side
            indices.addAll(binarySearchAllIndices(array, target, mid + 1, right)); // Check right side
        } else if (array[mid] < target) {
            return binarySearchAllIndices(array, target, mid + 1, right); // Search right half
        } else {
            return binarySearchAllIndices(array, target, left, mid - 1); // Search left half
        }
        return indices;
    }

    /**
     * Main method to test the recursive binary search methods.
     * 
     * @param args Command-line arguments (not used).
     * 
     * Demonstrates:
     * - Integer binary search for a single occurrence.
     * - String binary search.
     * - Finding all indices of a target in an integer array.
     */
    public static void main(String[] args) {
        int[] intArray = {1, 2, 3, 4, 4, 5, 6};
        String[] strArray = {"apple", "banana", "cherry", "date"};
        
        // Test integer binary search
        System.out.println(binarySearchRecursive(intArray, 4, 0, intArray.length - 1)); // Output: 3
        
        // Test string binary search
        System.out.println(binarySearchRecursive(strArray, "cherry", 0, strArray.length - 1)); // Output: 2
        
        // Test finding all indices
        System.out.println(binarySearchAllIndices(intArray, 4, 0, intArray.length - 1)); // Output: [3, 4]
    }
}
