package test;

import def.RecursiveExpressionParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecursiveExpressionParserTest {

    @Test
    void testSimpleExpressions() {
        assertEquals(2.0, RecursiveExpressionParser.evaluateExpression("5-3"));
//        assertEquals(16.0, RecursiveExpressionParser.evaluateExpression("(3 + 5) * 2"));
    }

    @Test
    void testDivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> 
            RecursiveExpressionParser.evaluateExpression("10 / 0")
        );
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void testInvalidExpressions() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            RecursiveExpressionParser.evaluateExpression("3 + * 2")
        );
        assertTrue(exception.getMessage().contains("Invalid operator"));

        IllegalArgumentException unmatchedParentheses = assertThrows(IllegalArgumentException.class, () -> 
            RecursiveExpressionParser.evaluateExpression("(3 + 2")
        );
        assertTrue(unmatchedParentheses.getMessage().contains("Unmatched parentheses"));
    }

    @Test
    void testParenthesesExpressions() {
        assertEquals(20.0, RecursiveExpressionParser.evaluateExpression("((3 + 2) * 4)"));
        assertEquals(8.0, RecursiveExpressionParser.evaluateExpression("((3 + 5) / 1)"));
    }

    @Test
    void testEdgeCases() {
        assertEquals(0.0, RecursiveExpressionParser.evaluateExpression("0"));
        assertEquals(1.0, RecursiveExpressionParser.evaluateExpression("1"));
        assertEquals(50.0, RecursiveExpressionParser.evaluateExpression("50"));
    }
}
