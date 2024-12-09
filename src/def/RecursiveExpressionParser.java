package def;


import java.util.Stack;

/**
 * The RecursiveExpressionParser class provides methods to parse and evaluate
 * simple mathematical expressions using recursion. It supports addition, subtraction,
 * multiplication, division, and parenthesis handling.
 * 
 * Features:
 * - Handles operator precedence (multiplication/division first, addition/subtraction second).
 * - Supports parentheses for controlling precedence.
 * - Extensible to handle floating-point numbers.
 */
public class RecursiveExpressionParser {

    /**
     * Evaluates a mathematical expression represented as a string.
     * 
     * @param expression The mathematical expression as a string.
     * @return The evaluated result of the expression as a double.
     * @throws IllegalArgumentException If the expression is invalid or null.
     * 
     * Behavior:
     * - Parses the string recursively to compute the result.
     * - Handles operator precedence for multiplication/division and addition/subtraction.
     * - Supports parentheses to override operator precedence.
     */
    public static double evaluateExpression(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty.");
        }
        return evaluate(expression.replaceAll("\\s+", ""), 0, expression.length() - 1);
    }

    // Helper method to evaluate the expression recursively
    private static double evaluate(String expr, int left, int right) {
        // Ensure bounds are valid
        if (left < 0 || right >= expr.length() || left > right) {
            throw new IllegalArgumentException("Invalid bounds for expression evaluation: left=" + left + ", right=" + right);
        }

        // Debugging log to check the bounds
        System.out.println("Evaluating: " + expr.substring(left, right + 1));

        // Check for parentheses
        if (expr.charAt(left) == '(' && findMatchingParenthesis(expr, left) == right) {
            return evaluate(expr, left + 1, right - 1); // Evaluate inside parentheses
        }

        // If no operator is found, return the number (base case)
        if (left == right) {
            return parseNumber(expr.substring(left, right + 1));
        }

        // Find the operator with the lowest precedence
        int splitIndex = findLowestPrecedenceOperator(expr, left, right);

        // If no operator is found, this is just a number, so return it
        if (splitIndex == -1) {
            return parseNumber(expr.substring(left, right + 1));
        }

        // Evaluate left and right parts of the expression recursively
        double leftResult = evaluate(expr, left, splitIndex - 1);
        double rightResult = evaluate(expr, splitIndex + 1, right);
        char operator = expr.charAt(splitIndex);

        // Apply the operator to the results
        return applyOperator(leftResult, rightResult, operator);
    }



    // Helper to apply a mathematical operator
    private static double applyOperator(double left, double right, char operator) {
        return switch (operator) {
            case '+' -> left + right;
            case '-' -> left - right;
            case '*' -> left * right;
            case '/' -> {
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield left / right;
            }
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }


    // Helper to parse a number from a string
    private static double parseNumber(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + str, e);
        }
    }

    // Helper to find the matching parenthesis
    private static int findMatchingParenthesis(String expr, int start) {
        Stack<Character> stack = new Stack<>();
        for (int i = start; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') {
                stack.push('(');
            } else if (c == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    return i; // Return the index of the matching closing parenthesis
                }
            }
        }
        throw new IllegalArgumentException("Unmatched parentheses in the expression.");
    }


    // Helper to find the lowest precedence operator outside parentheses
    private static int findLowestPrecedenceOperator(String expr, int left, int right) {
        // Check for valid bounds
        if (left < 0 || right >= expr.length() || left > right) {
            throw new IllegalArgumentException("Invalid bounds in findLowestPrecedenceOperator: left=" + left + ", right=" + right);
        }

        int index = -1;
        int parenthesesDepth = 0;
        int lowestPrecedence = Integer.MAX_VALUE;

        // Traverse the expression to find the lowest precedence operator outside parentheses
        for (int i = left; i <= right; i++) {
            char c = expr.charAt(i);

            if (c == '(') {
                parenthesesDepth++; // Increase depth when '(' is encountered
            } else if (c == ')') {
                parenthesesDepth--; // Decrease depth when ')' is encountered
            } else if (parenthesesDepth == 0) { // Consider operator only outside parentheses
                int precedence = getOperatorPrecedence(c);
                if (precedence <= lowestPrecedence) {
                    lowestPrecedence = precedence;
                    index = i;
                }
            }
        }

        return index; // Return index of the lowest precedence operator or -1 if none found
    }


    // Helper to determine operator precedence
    private static int getOperatorPrecedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1; // Lowest precedence
            case '*', '/' -> 2; // Higher precedence
            default -> Integer.MAX_VALUE; // Not an operator
        };
    }

    /**
     * Main method to test the recursive parser.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Add a test case with debugging output
        System.out.println(evaluateExpression("3-2")); // Expected: 8.0
    }

}
