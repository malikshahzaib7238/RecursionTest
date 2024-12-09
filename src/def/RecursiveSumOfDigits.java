package def;
import java.util.Scanner;

/**
 * The RecursiveSumOfDigits class provides a method to compute the sum of digits
 * of a given integer using recursion.
 * 
 * Specifications:
 * - sumOfDigits: A recursive method to calculate the sum of digits of a non-negative integer.
 * - Handles negative integers by converting them to positive.
 * - Base case: When the number is 0, return 0.
 * 
 * Time Complexity Analysis:
 * - The time complexity of this recursive algorithm is O(n), where n is the number of digits in the input number. This is because the recursion involves splitting the number digit by digit.
 */
public class RecursiveSumOfDigits {

    /**
     * Recursive method to compute the sum of digits of a number.
     * 
     * @param number The integer whose sum of digits is to be computed.
     * @return The sum of the digits.
     */
    public static int sumOfDigits(long number) {
        // Convert negative numbers to positive
        number = Math.abs(number);
        
        // Base case: if the number is 0, return 0
        if (number == 0) {
            return 0;
        }

        // Recursive case: sum the last digit and the sum of the remaining digits
        return (int)(number % 10) + sumOfDigits(number / 10);
    }

    public static void main(String[] args) {
        // Input reading
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a non-negative integer: ");
        long number = scanner.nextLong();  // Change to long to support larger values

        // Compute the sum of digits
        int sum = sumOfDigits(number);

        // Output the result
        System.out.println("The sum of the digits of " + number + " is: " + sum);
    }
}
