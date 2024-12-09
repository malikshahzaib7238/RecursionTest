package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import def.RecursiveSumOfDigits;

/**
 * The RecursiveSumOfDigitsTest class contains test cases to verify the functionality
 * of the sumOfDigits method from the RecursiveSumOfDigits class.
 * 
 * Test Cases:
 * - Various numbers including edge cases like 0, large numbers, and negative numbers.
 */
class RecursiveSumOfDigitsTest {

    /**
     * Test case for a small positive number.
     */
    @Test
    void testPositiveNumber() {
        assertEquals(6, RecursiveSumOfDigits.sumOfDigits(123));  // 1 + 2 + 3 = 6
    }

    /**
     * Test case for a number with a single digit.
     */
    @Test
    void testSingleDigit() {
        assertEquals(5, RecursiveSumOfDigits.sumOfDigits(5));  // Single digit, sum is the number itself
    }

    /**
     * Test case for 0.
     */
    @Test
    void testZero() {
        assertEquals(0, RecursiveSumOfDigits.sumOfDigits(0));  // The sum of digits of 0 is 0
    }

    /**
     * Test case for a large number.
     */
    @Test
    void testLargeNumber() {
        assertEquals(45, RecursiveSumOfDigits.sumOfDigits(9876543210L));  // 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 + 0 = 45
    }

    /**
     * Test case for a negative number.
     */
    @Test
    void testNegativeNumber() {
        assertEquals(10, RecursiveSumOfDigits.sumOfDigits(-1234));  // 1 + 2 + 3 + 4 = 10
    }

    /**
     * Test case for a very small negative number.
     */
    @Test
    void testNegativeSingleDigit() {
        assertEquals(7, RecursiveSumOfDigits.sumOfDigits(-7));  // Single negative digit is treated as positive
    }
}
