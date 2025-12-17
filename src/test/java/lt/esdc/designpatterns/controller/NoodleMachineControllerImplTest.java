package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.machine.NoodleMachineV17;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoodleMachineControllerImplTest {

    private static final class FakeMachine implements NoodleMachineV17 {
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
    void should_send_commands_for_each_dish() {
        controller.processOrder(new String[]{"italy", "ramen", "spaghetti", "chowmein"});

        assertEquals(3, machine.received.size());
        assertTrue(machine.received.get(0).matches("^\\d+g \\d+ml \\d+ml \\d+g$"));
        assertTrue(machine.received.get(1).matches("^\\d+g \\d+ml \\d+ml \\d+g$"));
        assertTrue(machine.received.get(2).matches("^\\d+g \\d+ml \\d+ml \\d+g$"));
    }

    @Test
    void should_default_to_italy_when_region_not_specified() {
        controller.processOrder(new String[]{"ramen"});

        assertEquals(1, machine.received.size());
        assertTrue(machine.received.get(0).matches("^\\d+g \\d+ml \\d+ml \\d+g$"));
    }

    @Test
    void should_append_toppings_to_command() {
        controller.processOrder(new String[]{"italy", "ramen sesame tofu"});

        assertEquals(1, machine.received.size());
        assertTrue(machine.received.get(0).matches("(?i)^\\d+g \\d+ml \\d+ml \\d+g sesame tofu$"));
    }

    @Test
    void should_throw_on_null_order() {
        assertThrows(IllegalArgumentException.class, () -> controller.processOrder(null));
    }

    @Test
    void should_throw_on_empty_order() {
        assertThrows(IllegalArgumentException.class, () -> controller.processOrder(new String[0]));
    }
}
