package com.sampleservice.demo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MiscTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSum() {
        // Arrange, Act and Assert
        assertEquals(2, Misc.sum(1, 1));
    }

    @Test
    public void testDivide() {
        // Arrange, Act and Assert
        thrown.expect(RuntimeException.class);
        Misc.divide(1, 0);
    }

    @Test
    public void testDivide2() {
        // Arrange, Act and Assert
        assertEquals(1.0, Misc.divide(1, 1), 0.0);
    }

    @Test
    public void testIsColorSupported() {
        // Arrange, Act and Assert
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
    }

    @Test
    public void testCalculateFactorial() {
        // Arrange, Act and Assert
        assertEquals(3628800L, Misc.calculateFactorial(10));
    }

    @Test
    public void testIsPrime() {
        // Arrange, Act and Assert
        assertTrue(Misc.isPrime(2, 1));
        assertFalse(Misc.isPrime(1, 1));
    }

    @Test
    public void testIsEven() {
        // Arrange, Act and Assert
        assertTrue(Misc.isEven(0));
        assertFalse(Misc.isEven(1));
    }
}

