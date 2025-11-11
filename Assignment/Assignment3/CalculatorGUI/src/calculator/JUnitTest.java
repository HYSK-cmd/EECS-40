package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {
    private Calculator calc;
    @BeforeEach
    public void setUp() {
        calc = new Calculator(); // each test gets a new calculator
    }
    @Test
    public void testAddition() {
        assertEquals(12.0, calc.add(5.0, 7.0), 0.0001);
    }
    @Test
    void subtraction() {
        assertEquals(2.0, calc.subtract(5.0, 3.0), 0.0001);
    }

    @Test
    void multiplication() {
        assertEquals(15.0, calc.multiply(5.0, 3.0), 0.0001);
    }

    @Test
    void division() {
        assertEquals(2.0, calc.divide(6.0, 3.0), 0.0001);
    }

    @Test
    void power() {
        assertEquals(8.0, calc.power(2.0, 3.0), 0.0001);
    }

    @Test
    void reciprocal() {
        assertEquals(0.25, calc.reciprocal(4.0), 0.0001);
    }

    @Test
    void sqrt() {
        assertEquals(3.0, calc.sqrt(9.0), 0.0001);
    }

    @Test
    void log() {
        assertEquals(0.0, calc.log(1), 0.0001);
    }

    @Test
    void factorial() {
        assertEquals(120, calc.factorial(5), 0.0001);
    }

    @Test
    void toggleSign() {
        assertEquals(-5.0, calc.toggleSign(5), 0.0001);
    }

}
