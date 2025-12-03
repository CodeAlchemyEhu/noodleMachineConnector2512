package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.machine.NoodleMachineV17;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoodleMachineControllerImplTest {

    private static class FakeMachine implements NoodleMachineV17 {
        private final List<String> received = new ArrayList<>();

        @Override
        public void send(String order) {
            received.add(order);
        }
    }

    private FakeMachine machine;
    private NoodleMachineController controller;

    @BeforeEach
    void setUp() {
        machine = new FakeMachine();
        controller = new NoodleMachineControllerImpl(machine);
    }

    @Test
    void shouldSendCommandsForItalyOrder() {
        String[] order = {"italy", "ramen", "spaghetti", "chowmein"};

        controller.processOrder(order);

        assertEquals(3, machine.received.size());
        assertEquals("100g 250ml 150ml 40g", machine.received.get(0));
        assertEquals("150g 100ml 50ml 30g", machine.received.get(1));
        assertEquals("120g 150ml 40ml 30g", machine.received.get(2));
    }

    @Test
    void shouldUseItalyAsDefaultRegionWhenNotSpecified() {
        String[] order = {"ramen"};

        controller.processOrder(order);

        assertEquals(1, machine.received.size());
        assertEquals("100g 250ml 150ml 40g", machine.received.get(0));
    }

    @Test
    void shouldThrowOnNullOrder() {
        assertThrows(IllegalArgumentException.class,
                () -> controller.processOrder(null));
    }

    @Test
    void shouldThrowOnEmptyOrder() {
        assertThrows(IllegalArgumentException.class,
                () -> controller.processOrder(new String[0]));
    }
}
