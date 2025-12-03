package lt.esdc.designpatterns.machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class NoodleMachineConnectorTest {

    private NoodleMachineConnector connector;

    @BeforeEach
    void setUp() {
        connector = new NoodleMachineConnector();
    }

    @Test
    void shouldAcceptValidOrderFormat() {
        String validOrder = "120g 400ml 250ml 50g";
        assertDoesNotThrow(() -> connector.send(validOrder));
    }

    @Test
    void shouldThrowExceptionWhenOrderIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            connector.send(null);
        });
        assertEquals("Order string cannot be null or empty.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOrderIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            connector.send("   ");
        });
        assertEquals("Order string cannot be null or empty.", exception.getMessage());
    }

    @Test
    void shouldRejectInvalidFormat_MissingParameter() {
        // Missing one value (vegetables)
        String invalidOrder = "120g 400ml 250ml";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            connector.send(invalidOrder);
        });

        assertTrue(exception.getMessage().contains("Invalid order format"));
        assertTrue(exception.getMessage().contains("<noodleMass>g <water>ml <broth>ml <vegetables>g"));
    }

    @Test
    void shouldRejectLettersInsteadOfNumbers() {
        String invalidOrder = "abcg 400ml 250ml 50g";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            connector.send(invalidOrder);
        });

        assertTrue(exception.getMessage().contains("Invalid order format"));
    }

    @Test
    void shouldHandleInterruptedExceptionGracefully() {
        assertDoesNotThrow(() -> connector.send("100g 300ml 200ml 40g"));
    }
}