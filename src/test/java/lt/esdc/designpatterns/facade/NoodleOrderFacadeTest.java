package lt.esdc.designpatterns.facade;

import lt.esdc.designpatterns.machine.NoodleMachineV17;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoodleOrderFacadeTest {
    @Test
    void should_create_facade_with_legacy_machine() {
        assertNotNull(NoodleOrderFacade.withLegacyMachine());
    }

    @Test
    void should_create_facade_with_new_machine() {
        assertNotNull(NoodleOrderFacade.withNewMachine());
    }

    @Test
    void should_send_prepared_command_to_machine() {
        FakeMachine machine = new FakeMachine();
        NoodleOrderFacade facade = NoodleOrderFacade.withMachine(machine);

        facade.order("italy", "ramen sesame");

        assertNotNull(machine.lastOrder);
        assertTrue(machine.lastOrder.endsWith("sesame"));
    }

    @Test
    void should_throw_exception_when_items_null() {
        NoodleOrderFacade facade = NoodleOrderFacade.withMachine(order -> {});

        assertThrows(NullPointerException.class, () -> facade.order((String[]) null));
    }

    private static final class FakeMachine implements NoodleMachineV17 {
        String lastOrder;

        @Override
        public void send(String order) {
            lastOrder = order;
        }
    }
}
